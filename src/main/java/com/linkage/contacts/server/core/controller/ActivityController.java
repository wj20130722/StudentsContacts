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
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.util.AuthorizationConfig;
import com.linkage.contacts.server.vo.FormActivityInfo;
import com.linkage.contacts.server.vo.FormHisInfo;
import com.linkage.contacts.server.vo.FormPostActivityInfo;

/**
 * 活动相关接口说明：
 * 活动首页活动显示：http://127.0.0.1:7080/StudentsContacts/contactsapi/activity/getall?type=xx&page=xx&pagesize=xxx
 * 活动详情显示：http://127.0.0.1:7080/StudentsContacts/contactsapi/activity/getone?activity_id=xxx&type=xx
 * (修改 * 认证)查看活动报名人员：http://127.0.0.1:7080/StudentsContacts/contactsapi/activity/getapplyer?activity_id=xxx&activity_state=xxx
 * 创建/修改新活动：(post)http://127.0.0.1:7080/StudentsContacts/contactsapi/activity/add
 * (修改 *)审核活动接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/activity/apply?activity_id=xxx&type=xxx&create_user_id=xxx
 * 发布/修改花絮：(post) http://127.0.0.1:7080/StudentsContacts/contactsapi/activity/addhis
 * 删除活动(get) http://127.0.0.1:7080/StudentsContacts/contactsapi/activity/delete?activity_id=xxx
 * 报名/取消报名接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/activity/join?activity_id=xxx&type=xxx
 * @author wangjie
 *
 */
@Controller
@RequestMapping(value="/activity")
public class ActivityController
{
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserInfoBO userInfoBO;
	
	@Autowired
	private ActivityInfoBO activityInfoBO;
	
	@Value("${switch_of_validaiton}")
	private String switch_of_validaiton;
	
	@ModelAttribute
	public void initModelAttribute(@RequestHeader(value=AuthorizationConfig.HEADER_AUTHORIZATION,required=false) String authorization, Model model)
	{
		UserInfo userInfo = userInfoBO.selectByTokenAnthorization(authorization);
		model.addAttribute("userInfo", userInfo);
	}
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<FormActivityInfo> getAllActivity(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,
	@RequestParam("type") int type,@RequestParam("page") int page,@RequestParam("pagesize") int pagesize) throws Exception
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "getall", null==userInfo?null:userInfo.getAccess_token());
		List<FormActivityInfo> list = new ArrayList<FormActivityInfo>();
		if(1==type)
		{
			if(null==userInfo)//普通用户
			{
				list = activityInfoBO.getActivityInfo(1,page,pagesize);
			}
			else if(null != userInfo && userInfo.getUser_id()!=0 && userInfo.getSuper_admin()!=1)//登录用户
			{
				list = activityInfoBO.getActivityInfo2(1,userInfo.getUser_id(),page,pagesize);
			}
			else //管理员
			{
				list = activityInfoBO.getActivityInfo3(1,page,pagesize);
			}
		}
		else if(2==type) //活动已归档
		{
			list = activityInfoBO.getActivityInfo4(1,page,pagesize);
		}
		return list;
	}
	
	@RequestMapping(value = "/getone", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getActivityById(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("activity_id") int activity_id,@RequestParam("type") int type) throws Exception
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "getone", null==userInfo?null:userInfo.getAccess_token());
		return activityInfoBO.getActivityInfoById(userInfo,activity_id,type);
	}
	
	@RequestMapping(value = "/getapplyer", method = RequestMethod.GET)
	@ResponseBody
	public List<HashMap<String, Object>> getApplyer(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("activity_id") int activity_id,@RequestParam("activity_state") int activity_state)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "getapplyer", null==userInfo?null:userInfo.getAccess_token());
		return activityInfoBO.getApplyerById(activity_id,activity_state,userInfo);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> addActivity(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestBody FormPostActivityInfo formActivityInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "add", null==userInfo?null:userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			activityInfoBO.saveActivityInfo(userInfo,formActivityInfo);
			map.put("result", 1);
			if(formActivityInfo.getActivity_id()!=0)
				map.put("message", "活动更新成功。");
			else
				map.put("message", "活动发布成功。");
		} 
		catch(Exception e)
		{
			log.info(e.getMessage(),e);
			map.put("result", 0);
			if(formActivityInfo.getActivity_id()!=0)
				map.put("message", "活动更新失败！");
			else
				map.put("message", "活动发布失败!");
		}
		return map;
	}
	
	@RequestMapping(value = "/addhis", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> addHis(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestBody FormHisInfo formHisInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "addhis", null==userInfo?null:userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			activityInfoBO.saveHisInfo(userInfo,formHisInfo);
			map.put("result", 1);
			map.put("message", "活动花絮发布/修改成功。");
		} 
		catch(Exception e)
		{
			log.info(e.getMessage(),e);
			map.put("result", 0);
			map.put("message", "活动花絮发布/修改失败！");
		}
		return map;
	}
	
	
	
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> applyActivity(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("activity_id") int activity_id,@RequestParam("type") int type,@RequestParam("create_user_id") int create_user_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "apply", null==userInfo?null:userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		boolean result = activityInfoBO.applyActivity(userInfo,activity_id,type,create_user_id);
		if(result)
		{
			map.put("result", 1);
			if(1==type)
				map.put("message", "同意该用户发布活动成功。");
			else 
				map.put("message", "拒绝该用户发布活动成功。");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "操作失败！");
		}
		return map;
	}
	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> joinActivity(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("activity_id") int activity_id,@RequestParam("type") int type)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "join", null==userInfo?null:userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			activityInfoBO.joinActivity(userInfo,activity_id,type);
			map.put("result", 1);
			if(1==type)
				map.put("message", "用户报名成功。");
			else 
				map.put("message", "用户取消报名成功。");
		} 
		catch(Exception e)
		{
			log.info(e.getMessage(),e);
			map.put("result", 0);
			map.put("message", "操作失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> deleteActivity(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("activity_id") int activity_id)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "delete", null==userInfo?null:userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			activityInfoBO.deleteActivity(userInfo,activity_id);
			map.put("result", 1);
			map.put("message", "删除活动成功。");
		} 
		catch(Exception e)
		{
			log.info(e.getMessage(),e);
			map.put("result", 0);
			map.put("message", "删除活动失败！");
		}
		return map;
	}
	
		
	
}
