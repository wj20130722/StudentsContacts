package com.linkage.contacts.server.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkage.contacts.server.bo.UserInfoBO;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.util.AubDigestUtils;
import com.linkage.contacts.server.util.AuthorizationConfig;
import com.linkage.contacts.server.vo.FormUserInfo;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;
import com.linkage.util.MybatisUtil;
import com.linkage.util.StringUtils;
import com.linkage.util.UUID;
import com.linkage.util.Utility;

/**
 * 个人中心模块接口说明： http://developmentweixin.duapp.com/StudentContacts/contactsapi/user/login
 * 用户登录接口：http://127.0.0.1:7080/StudentContacts/contactsapi/user/login
 * (修改 *)用户注册接口 第一步(必填信息填写)：http://127.0.0.1:7080/StudentsContacts/contactsapi/user/registerone
 * 用户注册接口 第二步(非必填信息填写)：http://127.0.0.1:7080/StudentsContacts/contactsapi/user/registertwo
 * 根据入学年份管理学院信息 ：http://127.0.0.1:7080/StudentsContacts/contactsapi/user/college/2005
 * (新增 *)注册第一步判断邮箱是否已经被注册以及匹配成功相关人员列表：http://127.0.0.1:7080/StudentsContacts/contactsapi/user/check?mail=xxxx&user_name=xx&xuehao=xxx&profession=xxx&year=xxx
 * @author wangjie
 *
 */
@Controller
@RequestMapping(value="/user")
public class UserController
{
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserInfoBO userInfoBO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Value("${switch_of_validaiton}")
	private String switch_of_validaiton;
	
	/**
	 * 用户登录认证
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/login")
	@ResponseBody
	public HashMap<String, Object> login(@RequestHeader(AuthorizationConfig.HEADER_AUTHORIZATION) String authorization,HttpServletRequest request)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "login", null);
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		UserInfo userInfo = userInfoBO.login(authorization);
		if(null != userInfo && 0 != userInfo.getUser_id())
		{
			//用户登录成功修改用户在线状态
			commonMapper.updateDataBySql("update user_info set online_status=1 where user_id="+userInfo.getUser_id());
			//TODO WJ 生成access_token返回给客户端
			//判断token是否在数据库中已经持久化
			String tokenid = "";
			if(StringUtils.isBlank(userInfo.getAccess_token()))
			{
				//不存在则持久化到数据库中
				tokenid = UUID.randomUUID().toString();
				//更新生成的access_token
				commonMapper.updateDataBySql("update user_info set access_token='"+tokenid+"' where user_id="+userInfo.getUser_id());
			}
			else
			{
				tokenid = userInfo.getAccess_token();
			}
			map.put("result", 1);
			map.put("message", "用户登录成功！");
			map.put("user_id", userInfo.getUser_id());
			map.put("user_name", userInfo.getUser_name());
			map.put("avatar_large", userInfo.getAvatar_large());
			map.put("token", tokenid);
		}
//		Employeeinfo employeeinfo = employeeinfoBO.authorization(authorization);
//		
//		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
//		
//		map.put("id", employeeinfo.getEmployeeid());
//		map.put("login", employeeinfo.getLoginname());
//		map.put("name", employeeinfo.getEmployeename());
//		map.put("type", "User");
//		map.put("created_at", new DateBean().getIOS8601DataTime());
		return map;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/check")
	@ResponseBody
	public HashMap<String, Object> check(HttpServletRequest request,@RequestParam("mail") String mail,@RequestParam("user_name") String user_name,@RequestParam("xuehao") String xuehao,@RequestParam("profession") String profession,@RequestParam("year") int year) throws Exception
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "check", null);
		user_name = new String(user_name.getBytes("ISO8859-1"),"UTF-8");
		profession = new String(profession.getBytes("ISO8859-1"),"UTF-8");
		List<HashMap<String, Object>> maps = null;
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		int count = userInfoBO.countByEmail(mail);
		if(count > 0) //邮箱已经被注册
		{
			map.put("result", 1);
			map.put("message", "该邮箱已经被注册使用！");
		}
		else
		{
			//TODO WJ  邮箱没有被注册使用,返回用户匹配的相关人员列表
			//学号不为空先匹配学号和姓名
			if(StringUtils.isNotBlank(xuehao))
			{
				String sql = "select xuehao,xingming,concat(begin_year,'级',ifnull(xibie,''),ifnull(fangxiang,'')) college_name from ustc_data";
				CriteriaManager cri = new CriteriaManager();
				cri.or().add(Restrictions.equalTo("xingming", user_name)).add(Restrictions.equalToIngoreCase("xuehao", xuehao))
				.add(Restrictions.equalTo("university_id", 1)).add(Restrictions.equalTo("begin_year", year));
				maps = commonMapper.getTableRowByWhereCondition(sql, cri);
			}
			else //学号为空的处理 匹配 姓名+学籍+专业关键字
			{
				StringBuffer sb = new StringBuffer("");
				sb.append("select xuehao,xingming,concat(begin_year,'级',ifnull(xibie,''),ifnull(fangxiang,'')) college_name   \n");
				sb.append("from ustc_data   \n");
				sb.append("where xingming='"+user_name+"' and begin_year="+year+" and university_id=1   ");
				sb.append("and concat(ifnull(xibie,''),ifnull(fangxiang,'')) like '%"+profession+"%'");
				maps = commonMapper.getTableRowBySql(sb.toString());
			}
			maps = MybatisUtil.converColumnNullList(maps, new String[]{"xuehao","xingming","college_name"});
			if(null==maps || maps.size()==0) //没有匹配到用户信息
			{
				map.put("result", 2);
				map.put("message", "没有匹配到用户信息！");
			}
			else
			{
				map.put("result", 3);
				map.put("message", "匹配到用户信息！");
				map.put("personinfos", maps);
			}
		}
		return map;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/registerone")
	@ResponseBody
	public HashMap<String, Object> registerone(HttpServletRequest request,@RequestBody FormUserInfo formuserInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "registerone", null);
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		int count = userInfoBO.countByEmail(formuserInfo.getMail());
		if(count > 0) //邮箱已经被注册
		{
			map.put("result", 3);
			map.put("message", "你已经提交注册信息！");
		}
		else
		{
		//TODO WJ 判断用户名在数据库中是否已被注册
			if(StringUtils.isBlank(formuserInfo.getChoosexuehao())) //没有匹配的用户信息,保存用户信息,标识用户未认证   需要用户去个人中心填写证明信息
			{
				//对密码做加密处理
				formuserInfo.setPassword(AubDigestUtils.encodeSHA256Hex(AuthorizationConfig.PSWPRE+Utility.chgNull(formuserInfo.getPassword())));
				formuserInfo.setUniversity_id(1);//暂时写死默认中国科技大学学校标识有待后续扩展
				formuserInfo.setXuehao(null);//设置用户所选学号为空
				formuserInfo.setIs_authentication(0);//未认证用户
				userInfoBO.save(formuserInfo);
				//用户登录成功修改用户在线状态
				//commonMapper.updateDataBySql("update user_info set online_status=1 where user_id="+userInfo.getUser_id());
				//生成userToken持久化到数据库中
				String tokenid = UUID.randomUUID().toString();
				//更新生成的access_token
				commonMapper.updateDataBySql("update user_info set online_status=1,access_token='"+tokenid+"' where user_id="+formuserInfo.getUser_id());
				map.put("result", 2);
				map.put("user_id", formuserInfo.getUser_id());
				map.put("user_name", formuserInfo.getUser_name());
				map.put("avatar_large", null);
				map.put("token",tokenid);
				map.put("message", "用户注册成功,但还未验证,请到个人中心进行认证。");
			}
			else //用户选择了匹配的用户,判断选择的匹配用户信息是否已经被注册了,如果被注册了走申诉流程
			{
				boolean exists = userInfoBO.existsByXuehao(formuserInfo.getUser_name(), formuserInfo.getChoosexuehao(), 1, formuserInfo.getYear());
				if(exists)
				{
					map.put("result", 0);
					map.put("message", "用户信息已经被注册！");
				}
				else
				{
					//用户信息没有被注册则直接保存用户信息
					//TODO WJ 保存用户信息
					//对密码做加密处理
					formuserInfo.setPassword(AubDigestUtils.encodeSHA256Hex(AuthorizationConfig.PSWPRE+Utility.chgNull(formuserInfo.getPassword())));
					formuserInfo.setUniversity_id(1);//暂时写死默认中国科技大学学校标识有待后续扩展
					formuserInfo.setXuehao(formuserInfo.getChoosexuehao());//设置用户所选学号
					formuserInfo.setIs_authentication(1);//认证用户
					userInfoBO.save(formuserInfo);
					//生成userToken持久化到数据库中
					String tokenid = UUID.randomUUID().toString();
					//更新生成的access_token
					commonMapper.updateDataBySql("update user_info set online_status=1,access_token='"+tokenid+"' where user_id="+formuserInfo.getUser_id());
					map.put("result", 1);
					map.put("user_id", formuserInfo.getUser_id());
					map.put("user_name", formuserInfo.getUser_name());
					map.put("avatar_large", null);
					map.put("token",tokenid);
					map.put("message", "用户注册成功。");
				}
			}
			
		}
		
		
			/*	boolean exist = userInfoBO.existsByUserInfo(formuserInfo.getUser_name(),formuserInfo.getXuehao(),formuserInfo.getProfession(),1,formuserInfo.getYear());
				if(exist) //用户信息已经被注册 可以走申诉流程解决
				{
					map.put("result", 0);
					map.put("message", "用户信息已经被注册！");
				}
				else
				{
					//TODO WJ 判断用户信息注册的合法性 即需要与基础数据中学生基础数据进行匹配
					boolean valid = userInfoBO.validateUserInfo(formuserInfo.getUser_name(),formuserInfo.getXuehao(),formuserInfo.getProfession(),1,formuserInfo.getYear());
					if(valid) //用户注册合法
					{
						//获取注册用户学号信息
						if(StringUtils.isBlank(formuserInfo.getXuehao()))
						{
							//设置用户的学号信息
							String sql = "select xuehao from ustc_data "
									+" where xingming='"+formuserInfo.getUser_name()+"' and begin_year="+formuserInfo.getYear()+" and university_id=1"
									+" and concat(ifnull(xibie,''),ifnull(fangxiang,'')) like '%"+formuserInfo.getProfession()+"%'";
							List<String> xuehaoList = commonMapper.getTableColumnValueBySql(sql);
							formuserInfo.setXuehao(xuehaoList.get(0));
						}
						//TODO WJ 保存用户信息
						formuserInfo.setUniversity_id(1);//暂时写死默认中国科技大学学校标识有待后续扩展
						
						userInfoBO.save(formuserInfo);
						//生成userToken持久化到数据库中
						String tokenid = UUID.randomUUID().toString();
						//更新生成的access_token
						commonMapper.updateDataBySql("update user_info set access_token='"+tokenid+"' where user_id="+formuserInfo.getUser_id());
						map.put("result", 1);
						map.put("user_id", formuserInfo.getUser_id());
						map.put("user_name", formuserInfo.getUser_name());
						map.put("avatar_large", null);
						map.put("token",tokenid);
						map.put("message", "用户注册成功。");
					}
					else //用户注册不合法 可以走证明流程解决
					{
						map.put("result", 2);
						map.put("message", "用户注册信息不合法！");
					}
				}*/
		return map;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/registertwo")
	@ResponseBody
	public HashMap<String, Object> registertwo(HttpServletRequest request,@RequestBody FormUserInfo formuserInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "registertwo", null);
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		if(StringUtils.isNotBlank(formuserInfo.getPhonenum()))
		{
			if(!Utility.isNumeric(formuserInfo.getPhonenum()))
			{
				map.put("result", 0);
				map.put("message", "输入的手机号码必须是数字！");
				return map;
			}
			int count = userInfoBO.countByPhoneNum(formuserInfo.getPhonenum());
			if(count > 0) //手机号码已经被注册
			{
				map.put("result", 0);
				map.put("message", "该手机号码已经被注册使用！");
				return map;
			}
		}
		//TODO WJ qq修改时判断唯一性
		if(StringUtils.isNotBlank(formuserInfo.getQq()))
		{
			if(!Utility.isNumeric(formuserInfo.getQq()))
			{
				map.put("result", 0);
				map.put("message", "输入的QQ号码必须是数字！");
				return map;
			}
			int count = userInfoBO.countByQQ(formuserInfo.getQq());
			if(count > 0) //QQ号码已经被注册
			{
				map.put("result", 0);
				map.put("message", "该QQ号码已经被注册使用！");
				return map;
			}
		}
		//TODO WJ 保存其他非必填信息
		userInfoBO.update(formuserInfo);
		map.put("result", 1);
		map.put("message", "用户完善注册信息成功！");
		
		/*if(StringUtils.isNotBlank(formuserInfo.getPhonenum()))
		{
			int count = userInfoBO.countByPhoneNum(formuserInfo.getPhonenum());
			if(count > 0) //手机号码已经被注册
			{
				map.put("result", 0);
				map.put("message", "该手机号码已经被注册使用！");
			}
			else
			{
			//TODO WJ 保存其他非必填信息
				userInfoBO.update(formuserInfo);
				map.put("result", 1);
				map.put("message", "用户完善注册信息成功！");
			}
		}
		else
		{
			userInfoBO.update(formuserInfo);
			map.put("result", 1);
			map.put("message", "用户完善注册信息成功！");
		}*/
		return map;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/college/{year}")
	@ResponseBody
	public List<HashMap<String, Object>> getCollegeByYear(HttpServletRequest request,@PathVariable("year") int year)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "college", null);
		//HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String sql = "select college_id,college_name from college_config where ("+year+" between year and end_year) and university_id=1";
	//	CriteriaManager cri = new CriteriaManager();
	//	cri.or().add(Restrictions.equalTo("year", year)).add(Restrictions.equalTo("university_id", 1));
		List<HashMap<String, Object>> collegeinfo = commonMapper.getTableRowBySql(sql);
		if(null == collegeinfo)
			collegeinfo = new ArrayList<HashMap<String, Object>>();
		return collegeinfo;
	}
	

	
	
}
