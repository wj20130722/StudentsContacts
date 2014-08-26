package com.linkage.contacts.server.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.UserPushMessageDAO;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.entity.UserPushMessage;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.vo.Contacts;
import com.linkage.contacts.server.vo.FormContacts;
import com.linkage.contacts.server.vo.FormMessage;
import com.linkage.contacts.server.vo.ReturnMessage;
import com.linkage.emojicon.emoji.EmojiconHandler;
import com.linkage.push.xinge.XingeAppPushUtil;
import com.linkage.util.StringUtils;
import com.linkage.util.Utility;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;

@Service
public class UserPushMessageBO
{
	private static final Log log = LogFactory.getLog(UserPushMessageBO.class);
	
	@Autowired
	@Qualifier("mybatisUserPushMessageDao")
	private UserPushMessageDAO userPushMessageDAO;
	
	@Autowired
	private UserInfoBO userInfoBO;
	
	@Autowired
	private UserMessageMarkBO userMessageMarkBO;
	
	@Autowired
	private CodeBO code;
	
	@Autowired
	private CommonMapper commonMapper;
	
	public void save(UserPushMessage userPushMessage)
	{
		if(0==userPushMessage.getPush_id())
		{
			userPushMessage.setPush_id((int)code.newCode("user_push_message.push_id", "推送信息", 1));
			this.userPushMessageDAO.insert(userPushMessage);
			log.info("用户推送消息保存成功。");
		}
		else
		{
			this.userPushMessageDAO.update(userPushMessage);
			log.info("用户推送信息更新成功。");
		}
		
	}

	public boolean sendMessage(UserInfo userInfo, FormMessage formMessage)
  {
		boolean result = false;
		try
		{
			//TODO WJ 针对带表情符号的文本做特殊处理
			String emojiStr = EmojiconHandler.replaceEmoji(formMessage.getContent());
			String emojiStr2 = EmojiconHandler.replaceUBBALL(emojiStr);
			//获取接收方用户信息
			UserInfo toUserInfo = userInfoBO.selectByPrimaryKey(formMessage.getTo_user_id());
			Date now = new Date();
			//发送用户信息给对方用户
			Map<String, Object> custom = new HashMap<String, Object>();
			custom.put("type", "1");
			custom.put("user_id", userInfo.getUser_id());
			custom.put("to_user_id", formMessage.getTo_user_id());
			custom.put("user_name", userInfo.getUser_name());
			custom.put("avatar_large", StringUtils.isNotBlank(userInfo.getAvatar_large())?userInfo.getAvatar_large():"null");
			custom.put("content", formMessage.getContent());
			custom.put("time", now.getTime());
			//安卓推送
			
			Message message = XingeAppPushUtil.constructMessageAndriod(Message.TYPE_NOTIFICATION, userInfo.getUser_id(), ClickAction.TYPE_ACTIVITY, userInfo.getUser_name(), emojiStr2,null, custom);
			JSONObject jsonObject = XingeAppPushUtil.pushSingleAccountAndriod(message, toUserInfo.getAccess_token());
			int push_state = jsonObject.getInt("ret_code");
			int push_state2 = 0;
			if(1 == toUserInfo.getOnline_status())
			{
				Map<String, Object> custom2 = new HashMap<String, Object>();
				custom2.put("type", "1");
				custom2.put("uid", userInfo.getUser_id());
				custom2.put("uname", userInfo.getUser_name());
				custom2.put("ava", StringUtils.isNotBlank(userInfo.getAvatar_large())?userInfo.getAvatar_large():"null");
				custom2.put("time", now.getTime());
				//IOS推送
				MessageIOS message2 = XingeAppPushUtil.constructMessageIOS(1, userInfo.getUser_name()+":"+formMessage.getContent(), custom2);
				JSONObject jsonObject2 = XingeAppPushUtil.pushSingleAccountIOS(message2, toUserInfo.getAccess_token());
				push_state2 = jsonObject2.getInt("ret_code");
			}
			else
			{
				push_state2 = 77;//用户不在线
			}
			log.info("安卓推送状态："+push_state+" IOS推送状态："+push_state2);
			//保存用户发送的信息
			UserPushMessage userPushMessage = new UserPushMessage();
			if(0 == formMessage.getFrom_user_id())
				userPushMessage.setFrom_user_id(userInfo.getUser_id());
			else
				userPushMessage.setFrom_user_id(formMessage.getFrom_user_id());
			userPushMessage.setTo_user_id(formMessage.getTo_user_id());
			userPushMessage.setMessage(emojiStr);
			userPushMessage.setPush_state(push_state & push_state2);
			userPushMessage.setPush_time(now);
			this.save(userPushMessage);
			result = true;
		}
		catch(Exception e)
		{
			log.info(e.getMessage(), e);
			result = false;
		}
	  return result;
  }

	public HashMap<String, Object> getMessage(UserInfo userInfo, int from_user_id, long timestamp)
  {
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		//获取发送方的用户信息
		UserInfo fromUserInfo = userInfoBO.selectByPrimaryKey(from_user_id);
		map.put("from_user_id", fromUserInfo.getUser_id());
		map.put("from_user_name", fromUserInfo.getUser_name());
		map.put("from_avatar_large", fromUserInfo.getAvatar_large());
		map.put("to_user_id", userInfo.getUser_id());
		List<ReturnMessage> list = new ArrayList<ReturnMessage>();
		ReturnMessage message = null;
		String timeStr = Utility.formatTimeString(timestamp);
		List<UserPushMessage> messageList = userPushMessageDAO.selectFromUserMessage(from_user_id, userInfo.getUser_id(),timeStr);
		for (final UserPushMessage userPushMessage : messageList)
    {
			message = new ReturnMessage();
			message.setPush_id(userPushMessage.getPush_id());
			message.setPush_time(userPushMessage.getPush_time().getTime());
			message.setMessage(EmojiconHandler.replaceUBB(userPushMessage.getMessage()));
			list.add(message);
    }
		map.put("messages", list);
		
		//备份用户信息
		//int count = userPushMessageDAO.insertBak(from_user_id, userInfo.getUser_id());
		//log.info("本次备份"+count+"条信息。");
		//int count2 = userPushMessageDAO.deleteBak(from_user_id, userInfo.getUser_id());
		//log.info("本次删除"+count2+"条信息。");
	  return map;
  }

	public List<FormContacts> getContactsList(UserInfo userInfo)
  {
		//清空我的消息标记
		userMessageMarkBO.clearUserMessageMark(userInfo.getUser_id());
		List<FormContacts> list = new ArrayList<FormContacts>();
		List<Contacts> messageList = userPushMessageDAO.getContactsFromUser(userInfo.getUser_id());
		FormContacts formContacts = null; 
		for (final Contacts contacts : messageList)
    {
			formContacts = new FormContacts();
			formContacts.setUser_id(contacts.getUser_id());
			formContacts.setUser_name(contacts.getUser_name());
			formContacts.setAvatar_large(contacts.getAvatar_large());
			formContacts.setPush_time(contacts.getPush_time().getTime());
			formContacts.setMessage(EmojiconHandler.replaceUBB(contacts.getMessage()));
			list.add(formContacts);
    }
	  return list;
  }

	public int deleteMessage(UserInfo userInfo, int from_user_id, long timestamp)
  {
		String timeStr = Utility.formatTimeString(timestamp);
		//备份用户信息
		int count = userPushMessageDAO.insertBak(from_user_id, userInfo.getUser_id(), timeStr);
		log.info("本次备份"+count+"条信息。");
		int count2 = userPushMessageDAO.deleteBak(from_user_id, userInfo.getUser_id(),timeStr);
		log.info("本次删除"+count2+"条信息。");
	  return count2;
  }
	
	
}
