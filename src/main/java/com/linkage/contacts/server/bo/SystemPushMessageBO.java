package com.linkage.contacts.server.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.SystemPushMessageDAO;
import com.linkage.contacts.server.entity.SystemPushMessage;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.vo.ReturnMessage;

@Service
public class SystemPushMessageBO
{
	private static final Log log = LogFactory.getLog(SystemPushMessageBO.class);
	
	@Autowired
	@Qualifier("mybatisSystemPushMessageDao")
	private SystemPushMessageDAO systemPushMessageDAO;
	
	@Autowired
	private UserInfoBO userInfoBO;
	
	@Autowired
	private UserMessageMarkBO userMessageMarkBO;
	
	@Autowired
	private CodeBO code;
	
	@Autowired
	private CommonMapper commonMapper;
	
	public void save(SystemPushMessage systemPushMessage)
	{
		if(0==systemPushMessage.getSystem_push_id())
		{
			systemPushMessage.setSystem_push_id((int)code.newCode("system_push_message.system_push_id", "系统消息推送信息", 1));
			this.systemPushMessageDAO.insert(systemPushMessage);
			log.info("系统推送消息保存成功。");
		}
		else
		{
			this.systemPushMessageDAO.update(systemPushMessage);
			log.info("系统推送信息更新成功。");
		}
		
	}

	public List<ReturnMessage> getSystemMessage(UserInfo userInfo)
  {
		//清空标识
		userMessageMarkBO.clearUserSysMessageMark(userInfo.getUser_id());
		List<ReturnMessage> list = new ArrayList<ReturnMessage>();
		List<SystemPushMessage> messageList = systemPushMessageDAO.getSystemMessage(userInfo.getUser_id());
		ReturnMessage message = null;
		for (final SystemPushMessage systemPushMessage : messageList)
    {
			message = new ReturnMessage();
			message.setPush_id(systemPushMessage.getSystem_push_id());
			message.setPush_time(systemPushMessage.getPush_time().getTime());
			message.setMessage(systemPushMessage.getMessage());
			list.add(message);
    }
	  return list;
  }
	
}
