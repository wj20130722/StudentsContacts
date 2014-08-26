package com.linkage.contacts.server.bo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.core.exception.InternalException;
import com.linkage.contacts.server.dao.UserInfoDAO;
import com.linkage.contacts.server.entity.UserCards;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.util.AubDigestUtils;
import com.linkage.contacts.server.util.AuthorizationConfig;
import com.linkage.contacts.server.util.Base64;
import com.linkage.contacts.server.util.EncodingUtils;
import com.linkage.contacts.server.vo.FormUserInfo;
import com.linkage.contacts.server.vo.FormUserInfo2;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;
import com.linkage.util.MybatisUtil;
import com.linkage.util.StringUtils;
import com.linkage.util.Utility;

@Service
public class UserInfoBO
{
	private static final Log log = LogFactory.getLog(UserInfoBO.class);
	
	private static final String EMAILREGEX = "^\\w+@\\w+(\\.com|\\.cn|\\.com.\\cn){1}$";
	
	private static final String PHONEREGEX = "^(\\+86|86|0086)?\\d{11}$";
	
public static void main(String[] args)
  {
	/*	Pattern regex = Pattern.compile(PHONEREGEX2);
		Matcher matcher = regex.matcher("8618351448461");
		boolean flag = matcher.matches();
		System.out.println(flag);*/
  }
	
	
	@Autowired
	@Qualifier("mybatisUserInfoDao")
	private UserInfoDAO userInfoDAO;
	
	@Autowired
	private CodeBO code;
	
	@Autowired
	private UserCardsBO userCardsBO;
	
	@Autowired
	private CommonMapper commonMapper;

	public UserInfo login(String authorization)
  {
			UserInfo userInfo = null;
		//Baisc认证
		if(null!=authorization && authorization.indexOf(AuthorizationConfig.BASICPRE)==0)
		{
			try
			{
			//获取加密字符串
	      String baiscAuthor = authorization.substring(AuthorizationConfig.BASICPRE.length());
	      //解密加密的字符串
	      String basicDecode = new String(EncodingUtils.fromBase64(baiscAuthor),Base64.CHARSET_UTF8);
	      //获取用户名
	      String loginname = basicDecode.substring(0, basicDecode.indexOf(':'));
	      //获取加密的密码
	      String encodePassword = basicDecode.substring(basicDecode.indexOf(':')+1);
	      //判断用户名是邮箱还是手机
	      if(null!=loginname && Utility.chgNull(loginname).matches(EMAILREGEX))
	      {
	      	//用户以邮箱的方式登录
	      	userInfo = this.selectByEmail(loginname);
	      	 if(null==userInfo || 0==userInfo.getUser_id())
	 	      	throw new ContactException("登录所填邮箱不存在！");
	      	 //TODO WJ 数据库中的密码使用加密处理
	      	// String encodePassword2 = AubDigestUtils.encodeSHA256Hex(AuthorizationConfig.PSWPRE+userInfo.getPassword());
	 	      if(encodePassword.equals(userInfo.getPassword()))
	 	      {
	 	      	log.info("用户登录认证成功！");
	 	      }
	 	      else
	 	      {
	 	      	throw new ContactException("密码不正确！");
	 	      }
	      	
	      }
	      else if(null!=loginname && Utility.chgNull(loginname).matches(PHONEREGEX))
	      {
	      	//用户以手机方式登录
	      	userInfo = this.selectByPhoneNum(loginname);
	      	 if(null==userInfo || 0==userInfo.getUser_id())
		 	      	throw new ContactException("登录所填手机号码不存在！");
	      	 //TODO WJ 数据库中的密码使用加密处理
		      	// String encodePassword2 = AubDigestUtils.encodeSHA256Hex(AuthorizationConfig.PSWPRE+userInfo.getPassword());
		 	      if(encodePassword.equals(userInfo.getPassword()))
		 	      {
		 	      	log.info("用户登录认证成功！");
		 	      }
		 	      else
		 	      {
		 	      	throw new ContactException("密码不正确！");
		 	      }
	      }
	      else
	      {
	      	
	      	throw new ContactException("登录方式不支持！");
	      	//用户名的登录方式暂时去掉目前只支持邮箱与手机号的方式登录
	      	//用户以用户名方式登录
	      	/*userInfo = this.selectByUserName(loginname);
	      	if(null==userInfo || 0==userInfo.getUser_id())
	 	      	throw new ContactException("用户名不存在！");
	      	 String encodePassword2 = AubDigestUtils.encodeSHA256Hex(AuthorizationConfig.PSWPRE+userInfo.getPassword());
	 	      if(encodePassword.equals(encodePassword2))
	 	      {
	 	      	log.info("用户登录认证成功！");
	 	      }
	 	      else
	 	      {
	 	      	throw new ContactException("密码不正确！");
	 	      }*/
	      }
			} 
			 catch (UnsupportedEncodingException e)
	      {
		      log.info("编码不支持", e);
		      throw new InternalException("编码不支持");
	      }
				catch (ContactException e)
	      {
		      log.info(e.getMessage(), e);
		      throw new ContactException(e.getMessage());
	      }
				catch (InternalException e)
	      {
		      log.info(e.getMessage(), e);
		      throw new InternalException(e.getMessage());
	      }
				catch(Exception e)
				{
					log.info(e);
					throw new InternalException("系统异常错误");
				}
		}
		else
		{
			log.info("登录认证出错！");
			throw new ContactException("登录认证出错！");
		}
		return userInfo;
  }
	
	public void save(FormUserInfo userInfo)
	{
		if(0==userInfo.getUser_id())
		{
			userInfo.setUser_id((int)code.newCode("user_info.user_id", "用户信息", 1));
			this.userInfoDAO.saveUserInfo(userInfo);
		}

	}
	
	public UserInfo selectByTokenAnthorization(String authorization)
	{
		UserInfo userInfo = null;
		if(null!=authorization && authorization.indexOf(AuthorizationConfig.TOKENPRE)==0)
		{
			//获取加密字符串
      String tokenAuthor = authorization.substring(AuthorizationConfig.TOKENPRE.length());
      userInfo = userInfoDAO.selectByTokenid(tokenAuthor);
		}
//		else
//		{
//			log.info("目前暂不支持其他认证");
//    	throw new ContactException("目前暂不支持其他认证");
//		}
		return userInfo;
	}
	
	public UserInfo selectByTokenid(String tokenid)
	{
		return userInfoDAO.selectByTokenid(tokenid);
	}

	private UserInfo selectByUserName(String username)
  {
	  return this.userInfoDAO.selectByUserName(username);
  }
	
	public UserInfo selectByXuehao(String xuehao)
  {
	  return this.userInfoDAO.selectByXuehao(xuehao);
  }

	private UserInfo selectByPhoneNum(String phonenum)
  {
	  return this.userInfoDAO.selectByPhoneNum(phonenum);
  }

	private UserInfo selectByEmail(String email)
  {
	  return this.userInfoDAO.selectByEmail(email);
  }
	
	public int countByEmail(String email)
	{
		return this.userInfoDAO.countByEmail(email);
	}
	
	public int countByEmailUser(String email,int user_id)
	{
		return this.userInfoDAO.countByEmailUser(email,user_id);
	}
	
	public int countByPhoneNum(String phonenum)
	{
		return this.userInfoDAO.countByPhoneNum(phonenum);
	}
	
	public int countByPhoneNumUser(String phonenum,int user_id)
	{
		return this.userInfoDAO.countByPhoneNumUser(phonenum,user_id);
	}
	
	public int countByQQ(String qq)
	{
		return this.userInfoDAO.countByQQ(qq);
	}
	
	public int countByQQUser(String qq,int user_id)
	{
		return this.userInfoDAO.countByQQUser(qq,user_id);
	}
	
	public boolean existsByXuehao(String user_name, String choosexuehao, int university_id, int year)
  {
		boolean exists = false;
		int count2 = userInfoDAO.count2ByUserInfo(user_name,choosexuehao,university_id,year);
		if(count2 > 0)
		{
			log.info("该用户信息已经被注册！");
			exists = true;
		}
		else
		{
			log.info("该用户信息没有被注册可以使用！");
			exists = false;
		}
	  return exists;
  }
	
	/**
	 * 判断用户信息是否已经被其他注册
	 * @param user_name 用户姓名
	 * @param college_id 院系标识
	 * @param university_id 学校标识
	 * @return
	 */
	public boolean existsByUserInfo(String user_name, String xuehao,String profession, int university_id,int year)
  {
		boolean exists = false;
		//学号不为空先匹配学号和姓名
		if(StringUtils.isNotBlank(xuehao))
		{
			int count2 = userInfoDAO.count2ByUserInfo(user_name,xuehao,university_id,year);
			if(count2 > 0)
			{
				log.info("该用户信息已经被注册！");
				exists = true;
			}
			else
			{
				log.info("该用户信息没有被注册可以使用！");
				exists = false;
			}
		}
		else //学号为空的处理 匹配 姓名+学籍+专业关键字
		{
			String sql = "select count(1) from user_info u join ustc_data us on u.xuehao=us.xuehao "
					+" where u.user_name='"+user_name+"' and u.year="+year+" and u.university_id="+university_id
					+" and concat(ifnull(us.xibie,''),ifnull(us.fangxiang,'')) like '%"+profession+"%'";
			int count3 = Integer.parseInt(commonMapper.getColumnValueBySql(sql));
			if(count3 > 0)
			{
				log.info("该用户信息已经被注册！");
				exists = true;
			}
			else
			{
				log.info("该用户信息没有被注册可以使用！");
				exists = false;
			}
		}
	/*	int count = userInfoDAO.countByUserInfo(user_name, college_id,university_id,year);
		if(count>0)
		{
			log.info("该用户信息已经被注册！");
			exists = true;
		}
		else
		{
			log.info("该用户信息没有被注册可以使用！");
			exists = false;
		}*/
		return exists;
  }

	public boolean validateUserInfo(String user_name, String xuehao, String profession, int university_id, int year)
  {
		boolean valid = false;
		//学号不为空先匹配学号和姓名
		if(StringUtils.isNotBlank(xuehao))
		{
			CriteriaManager cri = new CriteriaManager();
			cri.or().add(Restrictions.equalTo("xingming", user_name)).add(Restrictions.equalTo("xuehao", xuehao))
			.add(Restrictions.equalTo("university_id", university_id)).add(Restrictions.equalTo("begin_year", year));
			int count = commonMapper.countByWhereCondition("ustc_data", cri);
			if(count > 0)
			{
				log.info("用户注册合法！");
				valid = true;
			}
			else
			{
				log.info("用户注册不合法！");
				valid = false;
			}
		}
		else //学号为空的处理 匹配 姓名+学籍+专业关键字
		{
			String sql = "select count(1) from ustc_data "
					+" where xingming='"+user_name+"' and begin_year="+year+" and university_id="+university_id
					+" and concat(ifnull(xibie,''),ifnull(fangxiang,'')) like '%"+profession+"%'";
			int count = Integer.parseInt(commonMapper.getColumnValueBySql(sql));
			if(count > 0)
			{
				log.info("用户注册合法！");
				valid = true;
			}
			else
			{
				log.info("用户注册不合法！");
				valid = false;
			}
			
		}
		/*CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("student_name", user_name)).add(Restrictions.equalTo("college_id", college_id))
		.add(Restrictions.equalTo("university_id", university_id)).add(Restrictions.equalTo("year", year));
		int count = commonMapper.countByWhereCondition("student_info_conf", cri);
		if(count>0)
		{
			log.info("用户注册合法！");
			valid = true;
		}
		else
		{
			log.info("用户注册不合法！");
			valid = false;
		}*/
	  return valid;
  }

	public void update(FormUserInfo formuserInfo)
  {
		this.userInfoDAO.updateUserInfo(formuserInfo);
  }
	
	public void updatePersonalInfo(FormUserInfo2 formuserInfo)
  {
		this.userInfoDAO.updatePersonalInfo(formuserInfo);
  }

	public HashMap<String, Object> getUserInfoById(int user_id,int store_user_id)
  {
		HashMap<String, Object> result = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("select u.user_id,u.user_name,u.avatar_large,ifnull(convert(CONCAT(card.store_state,''),UNSIGNED),0) store_state,ifnull(u.companyname,'') companyname,(convert(CONCAT(u.company_secret,''),UNSIGNED)) company_secret,ifnull(u.company_department,'') company_department,(convert(CONCAT(u.department_secret,''),UNSIGNED)) department_secret,");
		sb.append("ifnull(u.company_position,'') company_position,(convert(CONCAT(u.position_secret,''),UNSIGNED)) position_secret,ifnull(u.qq,'') qq,(convert(CONCAT(u.qq_secret,''),UNSIGNED)) qq_secret,ifnull(u.mail,'') mail,(convert(CONCAT(u.mail_secret,''),UNSIGNED)) mail_secret,ifnull(u.phonenum,'') phonenum,(convert(CONCAT(u.phonenum_secret,''),UNSIGNED)) phonenum_secret,");
		sb.append("ifnull(u.city_name,'') city_name,(convert(CONCAT(u.city_secret,''),UNSIGNED)) city_secret,");
		//增加用户所属行业资料
		sb.append("ifnull(u.industry,'') industry,(convert(CONCAT(u.industry_secret,''),UNSIGNED)) industry_secret,");
		sb.append("(convert(CONCAT(u.is_authentication,''),UNSIGNED)) is_authentication,");
		sb.append("ifnull(u.service_name,'') service_name,(convert(CONCAT(u.service_secret,''),UNSIGNED)) service_secret    \n");
		sb.append("from user_info u     \n");
		sb.append("left outer join user_cards card on card.store_user_id=u.user_id and card.user_id="+user_id+" \n");
		sb.append("where u.user_id="+store_user_id);
		List<HashMap<String, Object>> list = commonMapper.getTableRowBySql(sb.toString());
		if(null!=list && list.size()>0)
		{
			result = list.get(0);
			//TODO WJ 用户名片详情显示处理
			//判断用户和查看用户是否属于同一班级
			if(user_id!=store_user_id)
			{
				sb = new StringBuffer("");
				sb.append("select count(class_id) num1,count(distinct class_id) num2   \n");
				sb.append("from class_role_info   \n");
				sb.append("where user_id in ("+user_id+","+store_user_id+")");
				HashMap<String, Object> numMap = commonMapper.getTableRowBySql(sb.toString()).get(0);
				if(Integer.parseInt(Utility.chgZero(numMap.get("num1")))==Integer.parseInt(Utility.chgZero(numMap.get("num2"))))
				{
					//当前用户和查看的用户不属于同一班级,隐藏用户保密信息
					if(Integer.parseInt(Utility.chgZero(result.get("company_secret")))==0)
						result.put("companyname", "公司保密");
					if(Integer.parseInt(Utility.chgZero(result.get("department_secret")))==0)
						result.put("company_department", "部门保密");
					if(Integer.parseInt(Utility.chgZero(result.get("position_secret")))==0)
						result.put("company_position", "职位保密");
					if(Integer.parseInt(Utility.chgZero(result.get("qq_secret")))==0)
						result.put("qq", "qq保密");
					if(Integer.parseInt(Utility.chgZero(result.get("mail_secret")))==0)
						result.put("mail", "邮箱保密");
					if(Integer.parseInt(Utility.chgZero(result.get("phonenum_secret")))==0)
						result.put("phonenum", "手机号码保密");
					if(Integer.parseInt(Utility.chgZero(result.get("city_secret")))==0)
						result.put("city_name", "城市保密");
					if(Integer.parseInt(Utility.chgZero(result.get("industry_secret")))==0)
						result.put("industry", "行业保密");
				}
				
			}
			sb = new StringBuffer("");
			//sb.append("select CONCAT(c.year,'级',(select college_name from college_config where college_id=c.college_id),c.class_name) class_name  \n");
			sb.append("select c.class_name  \n");
			sb.append("from class_role_info cc  \n");
			sb.append("join class_info c on c.class_id=cc.class_id  \n");
			sb.append("where cc.class_role_state=1 and cc.user_id="+store_user_id);
			List<String> classinfo = commonMapper.getTableColumnValueBySql(sb.toString());
			if(null == classinfo)
				classinfo = new ArrayList<String>();
			result.put("classinfo", classinfo);
			sb = new StringBuffer();
			sb.append("select aa.aluassociation_name  \n");
			sb.append("from aluassociation_role_info a  \n");
			sb.append("join aluassociation_config aa on aa.aluassociation_id=a.aluassociation_id  \n");
			sb.append("where a.aluassociation_role_state=1 and a.user_id="+store_user_id);
			List<String> aluassociationinfo = commonMapper.getTableColumnValueBySql(sb.toString());
			if(null == aluassociationinfo)
				aluassociationinfo = new ArrayList<String>();
			result.put("aluassociationinfo", aluassociationinfo);
		}
		result = MybatisUtil.converColumnNull(result, new String[]{"user_name","avatar_large","industry","companyname","company_department","company_position","qq","mail","phonenum","city_name","service_name"});
	  return result;
  }

	public HashMap<String, Object> getPersonalInfoById(int user_id)
  {
		HashMap<String, Object> result = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("select u.user_id,u.user_name,u.avatar_large,u.industry,(convert(CONCAT(u.industry_secret,''),UNSIGNED)) industry_secret,u.companyname,(convert(CONCAT(u.company_secret,''),UNSIGNED)) company_secret,u.company_department,(convert(CONCAT(u.department_secret,''),UNSIGNED)) department_secret,");
		sb.append("u.company_position,(convert(CONCAT(u.position_secret,''),UNSIGNED)) position_secret,u.qq,(convert(CONCAT(u.qq_secret,''),UNSIGNED)) qq_secret,u.mail,(convert(CONCAT(u.mail_secret,''),UNSIGNED)) mail_secret,u.phonenum,(convert(CONCAT(u.phonenum_secret,''),UNSIGNED)) phonenum_secret,");
		sb.append("u.city_name,(convert(CONCAT(u.city_secret,''),UNSIGNED)) city_secret,");
		sb.append("(convert(CONCAT(u.is_authentication,''),UNSIGNED)) is_authentication,");
		sb.append("u.service_name,(convert(CONCAT(u.service_secret,''),UNSIGNED)) service_secret    \n");
		sb.append("from user_info u     \n");
		sb.append("where u.user_id="+user_id);
		List<HashMap<String, Object>> list = commonMapper.getTableRowBySql(sb.toString());
		if(null!=list && list.size()>0)
		{
			result = list.get(0);
		}
		result = MybatisUtil.converColumnNull(result, new String[]{"user_name","avatar_large","industry","companyname","company_department","company_position","qq","mail","phonenum","city_name","service_name"});
	  return result;
  }

	public HashMap<String, Object> storeCards(int user_id, int store_user_id, int type)
  {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(1 == type) //收藏用户
		{
			CriteriaManager cri = new CriteriaManager();
			cri.or().add(Restrictions.equalTo("user_id", user_id)).add(Restrictions.equalTo("store_user_id", store_user_id));
			int count = commonMapper.countByWhereCondition("user_cards", cri);
			if(count == 0) //第一次收藏需要插入数据
			{
				UserCards card = new UserCards();
				card.setUser_id(user_id);
				card.setStore_user_id(store_user_id);
				card.setStore_time(new Date());
				card.setStore_state(1);
				userCardsBO.save(card);
			}
			else //更新对应状态即可
			{
				String updateSql = "update user_cards set store_state=1,store_time=now() where user_id="+user_id+" and store_user_id="+store_user_id;
				commonMapper.updateDataBySql(updateSql);
			}
			result.put("result", 1);
			result.put("message", "收藏成功。");
		}
		else if(0 == type) //取消收藏用户
		{
			String updateSql = "update user_cards set store_state=0 where user_id="+user_id+" and store_user_id="+store_user_id;
			commonMapper.updateDataBySql(updateSql);
			result.put("result", 1);
			result.put("message", "取消收藏成功。");
		}
		else
		{
				result.put("result", 0);
				result.put("comments", "未知的操作类型！");
		}
	  return result;
  }

	public List<HashMap<String, Object>> getMyCards(int user_id, int page, int pagesize)
  {
		List<HashMap<String,Object>> result = null;
		int index = (page-1)*pagesize;
		StringBuffer sb = new StringBuffer();
		sb.append("select    \n");
		sb.append("u.user_id,u.user_name,u.avatar_large,\n");
		//增加城市、行业、公司信息
		sb.append("u.city_name,(convert(CONCAT(u.city_secret,''),UNSIGNED)) city_secret,  \n");
		sb.append("u.industry,(convert(CONCAT(u.industry_secret,''),UNSIGNED)) industry_secret,   \n");
		sb.append("u.companyname,(convert(CONCAT(u.company_secret,''),UNSIGNED)) company_secret,   \n");
		sb.append("(convert(CONCAT(u.is_authentication,''),UNSIGNED)) is_authentication  \n");
		sb.append("from user_cards card   \n");
		sb.append("join user_info u on u.user_id=card.store_user_id   \n");
		sb.append("where card.user_id="+user_id+" and store_state=1  \n");
		sb.append("order by convert(u.user_name USING gbk) COLLATE gbk_chinese_ci asc \n");
		sb.append("limit "+index+","+pagesize);
		result = commonMapper.getTableRowBySql(sb.toString());
		//TODO WJ 增加班级信息
		String sql = "";
		for (final HashMap<String, Object> hashMap : result)
    {
			//CONCAT((select college_name from college_config where college_id=c.college_id),c.class_name) class_name
      sql = "select c.year,c.class_name "
      		+" from class_role_info cc join class_info c on c.class_id = cc.class_id and c.class_state=1 join user_info u on u.user_id = cc.user_id " +
      		" where cc.user_id="+Utility.chgZero(hashMap.get("user_id"))+" and c.university_id=1 and cc.class_role_state=1 ";
      List<HashMap<String, Object>> maps = commonMapper.getTableRowBySql(sql);
      if(null!=maps && maps.size()>0)
      {
      	hashMap.put("year", maps.get(0).get("year"));
      	hashMap.put("class_name", maps.get(0).get("class_name"));
      }
      else
      {
      	hashMap.put("year", null);
      	hashMap.put("class_name", null);
      }
    }
		result = MybatisUtil.converColumnNullList(result, new String[]{"user_name","avatar_large","city_name","industry","companyname"});
	  return result;
  }
	
	public UserInfo selectByPrimaryKey(int user_id)
	{
		return userInfoDAO.selectByPrimaryKey(user_id);
	}
	
	public List<UserInfo> getSysAdmin(int university_id)
	{
		return userInfoDAO.getSysAdmin(university_id);
	}

	public HashMap<String, Object> changePassword(UserInfo userInfo, String oldpassword, String newpassword, String confirmpassword)
  {
		HashMap<String, Object> result = new HashMap<String, Object>();
		//判断用户输入的旧密码是否正确
		if(!Utility.chgNull(userInfo.getPassword()).equals(AubDigestUtils.encodeSHA256Hex(AuthorizationConfig.PSWPRE+oldpassword)))
		{
			result.put("result", 0);
			result.put("message", "旧密码输入不正确!");
		}
		else
		{
			if(!Utility.chgNull(newpassword).equals(confirmpassword))
			{
				result.put("result", 0);
				result.put("message", "新密码和确认密码输入不一致,请重新输入!");
			}
			else
			{
				//更新用户密码
				String updateSql = "update user_info set password='"+AubDigestUtils.encodeSHA256Hex(AuthorizationConfig.PSWPRE+newpassword)+"' where user_id="+userInfo.getUser_id();
				commonMapper.updateDataBySql(updateSql);
				result.put("result", 1);
				result.put("message", "密码修改成功。");
			}
		}
	  return result;
  }

	
	
}
