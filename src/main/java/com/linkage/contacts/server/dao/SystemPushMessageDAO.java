package com.linkage.contacts.server.dao;

import java.util.List;

import com.linkage.contacts.server.entity.SystemPushMessage;

public interface SystemPushMessageDAO
{
	public abstract void insert(SystemPushMessage systemPushMessage);
	
	public abstract void update(SystemPushMessage systemPushMessage);
	
	public abstract void delete(SystemPushMessage systemPushMessage);
	
	public abstract SystemPushMessage selectByPrimaryKey(int system_push_id);

	public abstract List<SystemPushMessage> getSystemMessage(int user_id);
}
