package com.linkage.contacts.server.bo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.UserMessageMarkDAO;
import com.linkage.contacts.server.entity.UserMessageMark;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;

@Service
public class UserMessageMarkBO
{
	private static final Log log = LogFactory.getLog(UserMessageMarkBO.class);
	
	@Autowired
	@Qualifier("mybatisUserMessageMarkDao")
	private UserMessageMarkDAO userMessageMarkDAO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	//发送给用户一条消息
	public void addUserMessageMark(int user_id)
	{
		boolean exists = userMessageMarkDAO.existsByUserId(user_id);
		if(exists) //更新我的消息标识
		{
			String sql = "update user_message_mark set has_message=1 where user_id="+user_id;
			commonMapper.updateDataBySql(sql);
		}
		else //新增消息
		{
			UserMessageMark userMessageMark = new UserMessageMark();
			userMessageMark.setUser_id(user_id);
			userMessageMark.setHas_message(1);
			userMessageMark.setHas_sys_message(0);
			userMessageMarkDAO.insert(userMessageMark);
		}
	}
	
	public void addUserSysMessageMark(int user_id)
	{
		boolean exists = userMessageMarkDAO.existsByUserId(user_id);
		if(exists) //更新我的消息标识
		{
			String sql = "update user_message_mark set has_message=1,has_sys_message=1 where user_id="+user_id;
			commonMapper.updateDataBySql(sql);
		}
		else //新增消息
		{
			UserMessageMark userMessageMark = new UserMessageMark();
			userMessageMark.setUser_id(user_id);
			userMessageMark.setHas_message(1);
			userMessageMark.setHas_sys_message(1);
			userMessageMarkDAO.insert(userMessageMark);
		}
	}
	
	public void clearUserMessageMark(int user_id)
	{
		UserMessageMark userMessage = userMessageMarkDAO.selectByPrimaryKey(user_id);
		if(null!=userMessage && userMessage.getUser_id()!=0)
		{
			if(0 == userMessage.getHas_sys_message())
			{
				String sql = "update user_message_mark set has_message=0 where user_id="+user_id;
				commonMapper.updateDataBySql(sql);
			}
		}
	}
	
	public void clearUserSysMessageMark(int user_id)
	{
		UserMessageMark userMessage = userMessageMarkDAO.selectByPrimaryKey(user_id);
		if(null!=userMessage && userMessage.getUser_id()!=0)
		{
				String sql = "update user_message_mark set has_message=0,has_sys_message=0 where user_id="+user_id;
				commonMapper.updateDataBySql(sql);
		}
	}
	
	
}
