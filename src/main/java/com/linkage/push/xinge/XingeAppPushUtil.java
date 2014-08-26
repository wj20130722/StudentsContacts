package com.linkage.push.xinge;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;

public class XingeAppPushUtil
{
	//ANDROID 下发单个设备 
	public static JSONObject pushSingleDeviceAndroid(Message message,String deviceToken) 
	{
		XingeApp xinge = LinkageXingeApp.getInstance(1);
		JSONObject ret = xinge.pushSingleDevice(deviceToken, message);
		return (ret);
	}
	
	//IOS 下发单个设备
	public static JSONObject pushSingleDeviceIOS(MessageIOS message,String deviceToken) 
	{
		XingeApp xinge = LinkageXingeApp.getInstance(2);
		JSONObject ret = xinge.pushSingleDevice(deviceToken, message,XingeApp.IOSENV_DEV);
		return (ret);
	}

	//ANDRIOD 下发单个账号
	//{"ret_code":0}
	public static JSONObject pushSingleAccountAndriod(Message message,String tokenid) 
	{
		XingeApp xinge = LinkageXingeApp.getInstance(1);
		JSONObject ret = xinge.pushSingleAccount(XingeApp.DEVICE_ANDROID, tokenid, message);
		return (ret);
	}
	
	//IOS 下发单个账号
	public static JSONObject pushSingleAccountIOS(MessageIOS message,String tokenid) 
	{
		XingeApp xinge = LinkageXingeApp.getInstance(2);
		JSONObject ret = xinge.pushSingleAccount(XingeApp.DEVICE_IOS, tokenid, message,XingeApp.IOSENV_DEV);
		return (ret);
	}
	
	
	//ANDRIOD 下发所有设备
	public static JSONObject pushAllDeviceAndriod(Message message)
	{
		XingeApp xinge = LinkageXingeApp.getInstance(1);
		JSONObject ret = xinge.pushAllDevice(XingeApp.DEVICE_ALL, message);
		return (ret);
	}
	
	//IOS 下发所有设备
	public static JSONObject pushAllDeviceIOS(MessageIOS message)
	{
		XingeApp xinge = LinkageXingeApp.getInstance(2);
		JSONObject ret = xinge.pushAllDevice(XingeApp.DEVICE_ALL, message, XingeApp.IOSENV_DEV);
		return (ret);
	}
	
	public static Message constructMessageAndriod (int type,int nid,int actionType,String title,String content,String intent,Map<String, Object> custom)
	{
		Message message = new Message();
		message.setType(type);
		Style style = new Style(3,1,1,1,nid);
		ClickAction action = new ClickAction();
		action.setActionType(actionType);
		//设置intent消息
		if(actionType == ClickAction.TYPE_INTENT)
				action.setIntent(intent);
		message.setTitle(title); //
		message.setContent(content);
		message.setStyle(style);
		message.setAction(action);
		message.setCustom(custom);
		TimeInterval acceptTime = new TimeInterval(0,0,23,59);
		message.addAcceptTime(acceptTime);
		message.setExpireTime(3*24*60*60);
		return message;
	}
	
	public static MessageIOS constructMessageIOS (int badge,String body,Map<String, Object> custom)
	{
		MessageIOS message = new MessageIOS();
		message.setAlert(body);
		message.setBadge(badge);
		message.setSound("default");
		message.setCustom(custom);
		TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
		message.addAcceptTime(acceptTime1);
		message.setExpireTime(3*24*60*60);
		return message;
	}
	
	public static void main(String[] args)
  {
		Map<String, Object> custom = new HashMap<String, Object>();
		custom.put("type", "1");
		custom.put("uid", 3);
		//custom.put("to_user_id",3);
		custom.put("uname", "Jack");
		custom.put("ava", "http://sdsdsd.sssss");
		//custom.put("content", "校友会：测试");
		custom.put("time", new Date().getTime()); 
		//Message message = constructMessageAndriod(Message.TYPE_NOTIFICATION, 1, ClickAction.TYPE_ACTIVITY, "校友会", "群发Test", custom);
		//MessageIOS message2 = constructMessageIOS(1, "Test", custom);
		//JSONObject jsonObject = demoPushSingleAccount(message, "787c5599-23db-4d3f-8ebc-c0111b29e555");
		//JSONObject jsonObject = pushAllDeviceAndriod(message);
		//JSONObject jsonObject = pushSingleAccountIOS(message2, "787c5599-23db-4d3f-8ebc-c0111b29e555");
		MessageIOS message = constructMessageIOS(1, "校友会：测试", custom);
		JSONObject jsonObject = pushSingleAccountIOS(message, "b04a035e-cf31-45f6-b585-734c9c375508");
		System.out.println(jsonObject.toString());
  }
}
