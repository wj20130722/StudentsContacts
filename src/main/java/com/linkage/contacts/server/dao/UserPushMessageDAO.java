package com.linkage.contacts.server.dao;

import java.util.List;

import com.linkage.contacts.server.entity.UserPushMessage;
import com.linkage.contacts.server.vo.Contacts;

public interface UserPushMessageDAO
{
	public abstract void insert(UserPushMessage userPushMessage);
	
	public abstract void update(UserPushMessage userPushMessage);
	
	public abstract void delete(UserPushMessage userPushMessage);
	
	public abstract UserPushMessage selectByPrimaryKey(int push_id);
	
	public abstract List<UserPushMessage> selectFromUserMessage(int from_user_id,int to_user_id, String timeStr);
	
	public abstract int insertBak(int from_user_id,int to_user_id, String timeStr);

	public abstract int deleteBak(int from_user_id, int to_user_id, String timeStr);

	public abstract List<Contacts> getContactsFromUser(int user_id);
}
