package com.linkage.contacts.server.core.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkage.contacts.server.bo.ActivityInfoBO;
import com.linkage.contacts.server.bo.UserInfoBO;
import com.linkage.contacts.server.bo.ValidationMailInfoBO;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.util.AuthorizationConfig;
import com.linkage.contacts.server.vo.FormActivityInfo;
import com.linkage.contacts.server.vo.FormUserInfo2;
import com.linkage.util.MybatisUtil;
import com.linkage.util.Utility;

/**
 * 个人中心模块接口说明：
 * 个人资料修改接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/personalcenter/update
 * (修改 * 认证)个人资料查看接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/personalcenter/getuserinfo
 * 用户上传头像接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/personalcenter/upload?avatar_large=xxxx
 * 我的活动接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/personalcenter/myactivity?page=xx&pagesize=xxx
 * (修改*)收藏/取消收藏用户名片接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/personalcenter/store?store_user_id=xxx&type=xxx
 * (修改 * 认证)获取我的名片接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/personalcenter/getmycards?page=xx&pagesize=xxx
 * 用户修改密码接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/personalcenter/changepwd?oldpassword=xxx&newpassword=xxx&confirmpassword=xxx
 * 获取证明人相关列表接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/personalcenter/getcertifier?certifier_name=xxxx
 * 发送邮件给证明人接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/personalcenter/sendmail?xuehao=xxxx
 * (新增 *)用户是否认证接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/personalcenter/authentication
 * 用户注销接口：http://127.0.0.1:7080/StudentContacts/contactsapi/personalcenter/logout
 * @author wangjie
 *
 */
@Controller
@RequestMapping(value="/personalcenter")
public class PersonalCenterController
{
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserInfoBO userInfoBO;
	
	@Autowired
	private ValidationMailInfoBO validationMailInfoBO;
	
	@Autowired
	private ActivityInfoBO activityInfoBO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Value("${switch_of_validaiton}")
	private String switch_of_validaiton;
	
	@ModelAttribute
	public void initModelAttribute(@RequestHeader(value=AuthorizationConfig.HEADER_AUTHORIZATION) String authorization, Model model)
	{
		UserInfo userInfo = userInfoBO.selectByTokenAnthorization(authorization);
		model.addAttribute("userInfo", userInfo);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/update")
	@ResponseBody
	public HashMap<String, Object> updatePersonalInfo(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestBody FormUserInfo2 formuserInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "update",userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		if(0==formuserInfo.getUser_id())
			formuserInfo.setUser_id(userInfo.getUser_id());
		//TODO WJ 更新邮箱或者手机号码的时候检查唯一性
		if(StringUtils.isNotBlank(formuserInfo.getMail()))
		{
			int count = userInfoBO.countByEmailUser(formuserInfo.getMail(),userInfo.getUser_id());
			if(count > 0) //邮箱已经被注册
			{
				map.put("result", 0);
				map.put("message", "该邮箱已经被注册使用！");
				return map;
			}
		}
		if(StringUtils.isNotBlank(formuserInfo.getPhonenum()))
		{
			if(!Utility.isNumeric(formuserInfo.getPhonenum()))
			{
				map.put("result", 0);
				map.put("message", "输入的手机号码必须是数字！");
				return map;
			}
			int count = userInfoBO.countByPhoneNumUser(formuserInfo.getPhonenum(),userInfo.getUser_id());
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
			int count = userInfoBO.countByQQUser(formuserInfo.getQq(),userInfo.getUser_id());
			if(count > 0) //QQ号码已经被注册
			{
				map.put("result", 0);
				map.put("message", "该QQ号码已经被注册使用！");
				return map;
			}
		}
		try
		{
			userInfoBO.updatePersonalInfo(formuserInfo);
			map.put("result", 1);
			map.put("message", "用户修改个人信息成功！");
		}
		catch(Exception e)
		{
			log.info(e.getMessage(),e);
			map.put("result", 0);
			map.put("message", "用户修改个人信息失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/getuserinfo", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getPersonalInfoById(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "getuserinfo",userInfo.getAccess_token());
		return userInfoBO.getPersonalInfoById(userInfo.getUser_id());
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> uploadUserImage(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("avatar_large") String avatar_large)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "upload",userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String sql = "update user_info set avatar_large='"+avatar_large+"' where user_id="+userInfo.getUser_id();
		int count = commonMapper.updateDataBySql(sql);
		if(count==1)
		{
			map.put("result", 1);
			map.put("message", "用户头像上传成功！");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "用户头像上传失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> logout(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("avatar_large") String avatar_large)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "logout",userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String sql = "update user_info set online_status=0 where user_id="+userInfo.getUser_id();
		int count = commonMapper.updateDataBySql(sql);
		if(count==1)
		{
			map.put("result", 1);
			map.put("message", "用户注销成功！");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "用户注销失败！");
		}
		return map;
	}
	
	
	@RequestMapping(value = "/myactivity", method = RequestMethod.GET)
	@ResponseBody
	public List<FormActivityInfo> getAllActivity(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("page") int page,@RequestParam("pagesize") int pagesize) throws Exception
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "myactivity",userInfo.getAccess_token());
		return activityInfoBO.getMyActivity(userInfo.getUniversity_id(),userInfo.getUser_id(),page,pagesize);
	}
	
	@RequestMapping(value = "/store", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> storeCards(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("store_user_id") int store_user_id,@RequestParam("type") int type)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "store",userInfo.getAccess_token());
		return userInfoBO.storeCards(userInfo.getUser_id(),store_user_id,type);
	}
	
	@RequestMapping(value = "/getmycards", method = RequestMethod.GET)
	@ResponseBody
	public List<HashMap<String, Object>> getMyCards(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("page") int page,@RequestParam("pagesize") int pagesize)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "getmycards",userInfo.getAccess_token());
		return userInfoBO.getMyCards(userInfo.getUser_id(),page,pagesize);
	}
	
	@RequestMapping(value = "/changepwd", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> changePassword(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("oldpassword") String oldpassword,@RequestParam("newpassword") String newpassword,@RequestParam("confirmpassword") String confirmpassword)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "changepwd",userInfo.getAccess_token());
		return userInfoBO.changePassword(userInfo,oldpassword,newpassword,confirmpassword);
	}
	
	@RequestMapping(value = "/getcertifier", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getCertifier(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("certifier_name") String certifier_name) throws Exception
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "getcertifier",userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		certifier_name = new String(certifier_name.getBytes("ISO8859-1"),"UTF-8");
		StringBuffer sb = new StringBuffer("");
		sb.append("select u.xuehao,us.xingming,concat(us.begin_year,'级',ifnull(us.xibie,''),ifnull(us.fangxiang,'')) college_name   \n");
		sb.append("from user_info u  \n");
		sb.append("join ustc_data us on us.xuehao=u.xuehao and u.is_authentication=1  \n");
		//TODO WJ 只有同一届的证明人可选的限制去掉
		//sb.append("where us.xingming='"+certifier_name+"' and u.year="+userInfo.getYear()+" and u.university_id="+userInfo.getUniversity_id());
		sb.append("where us.xingming='"+certifier_name+"' and u.university_id="+userInfo.getUniversity_id());
		List<HashMap<String, Object>> maps = commonMapper.getTableRowBySql(sb.toString());
		maps = MybatisUtil.converColumnNullList(maps, new String[]{"xuehao","xingming","college_name"});
		map.put("personinfos", maps);
		return map;
	}
	
	@RequestMapping(value = "/sendmail", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> sendMail(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("xuehao") String xuehao)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "sendmail",userInfo.getAccess_token());
		return validationMailInfoBO.sendMail(userInfo,xuehao);
	}
	
	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> authentication(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "authentication",userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		if(1==userInfo.getIs_authentication())
		{
			map.put("result", 1);
			map.put("message", "用户信息已经认证过！");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "用户信息未认证过！");
		}
		return map;
	}
	
	
}
