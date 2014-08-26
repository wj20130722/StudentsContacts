package com.linkage.contacts.server.core.controller;

import java.util.Date;
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

import com.linkage.contacts.server.bo.SystemPushMessageBO;
import com.linkage.contacts.server.bo.UserInfoBO;
import com.linkage.contacts.server.bo.UserMessageMarkBO;
import com.linkage.contacts.server.bo.UserPushMessageBO;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.util.AuthorizationConfig;
import com.linkage.contacts.server.vo.FormContacts;
import com.linkage.contacts.server.vo.FormMessage;
import com.linkage.contacts.server.vo.ReturnMessage;

/**
 * 消息推送接口
 * 私信发送接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/push/sendmessage
 * 私信获取接口(修改)：http://127.0.0.1:7080/StudentsContacts/contactsapi/push/getmessage?from_user_id=xxxx&timestamp=xxxxx
 * 私信清除接口(新增)：http://127.0.0.1:7080/StudentsContacts/contactsapi/push/deletemessage?from_user_id=xxxx&timestamp=xxxxx
 * 用户系统消息获取接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/push/getsysmessage
 * 系统时间获取接口：http://127.0.0.1:7080/StudentsContacts/contactsapi/push/getsystemtime?client_time=xxxxxx
 * 获取联系人列表接口(IOS新增)：http://127.0.0.1:7080/StudentsContacts/contactsapi/push/getcontactslist
 * @author wangjie
 *
 */
@Controller
@RequestMapping(value="/push")
public class PushController
{
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserInfoBO userInfoBO;
	
	@Autowired
	private UserPushMessageBO userPushMessageBO;
	
	@Autowired
	private SystemPushMessageBO systemPushMessageBO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private UserMessageMarkBO userMessageMarkBO;
	
	@Value("${switch_of_validaiton}")
	private String switch_of_validaiton;
	
	@ModelAttribute
	public void initModelAttribute(@RequestHeader(value=AuthorizationConfig.HEADER_AUTHORIZATION,required=false) String authorization, Model model)
	{
		
		UserInfo userInfo = userInfoBO.selectByTokenAnthorization(authorization);
		model.addAttribute("userInfo", userInfo);
	}
	
	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> sendMessage(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestBody FormMessage formMessage)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "sendmessage",userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		boolean result = userPushMessageBO.sendMessage(userInfo,formMessage);
		if(result)
		{
			userMessageMarkBO.addUserMessageMark(formMessage.getTo_user_id());
			map.put("result", 1);
			map.put("message", "消息发送成功！");
		}
		else
		{
			map.put("result", 0);
			map.put("message", "消息发送失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/getmessage", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getMessage(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("from_user_id") int from_user_id,@RequestParam("timestamp") long timestamp)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "getmessage",userInfo.getAccess_token());
		return userPushMessageBO.getMessage(userInfo,from_user_id,timestamp);
	}
	
	@RequestMapping(value = "/deletemessage", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> deleteMessage(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo,@RequestParam("from_user_id") int from_user_id,@RequestParam("timestamp") long timestamp)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "deletemessage",userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			userPushMessageBO.deleteMessage(userInfo,from_user_id,timestamp);
			map.put("result", 1);
			map.put("message", "消息删除成功！");
		}
		catch(Exception e)
		{
			map.put("result", 0);
			map.put("message", "消息删除失败！");
		}
		return map;
	}
	
	@RequestMapping(value = "/getcontactslist", method = RequestMethod.GET)
	@ResponseBody
	public List<FormContacts> getContactsList(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "getcontactslist",userInfo.getAccess_token());
		return userPushMessageBO.getContactsList(userInfo);
	}
	
	
	@RequestMapping(value = "/getsysmessage", method = RequestMethod.GET)
	@ResponseBody
	public List<ReturnMessage> getSysMessage(HttpServletRequest request,@ModelAttribute("userInfo") UserInfo userInfo)
	{
		AuthorizationConfig.validation(switch_of_validaiton, request, "getsysmessage",userInfo.getAccess_token());
		return systemPushMessageBO.getSystemMessage(userInfo);
	}
	
	@RequestMapping(value = "/getsystemtime", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getMessage(@RequestParam("client_time") long client_time)
	{
		//AuthorizationConfig.validation(switch_of_validaiton, request, "getsystemtime",null==userInfo?null:userInfo.getAccess_token());
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		Date now = new Date();
		map.put("systimestamp", now.getTime());
		map.put("systimediff", now.getTime()-client_time);
		return map;
	}
	
	
	
}
