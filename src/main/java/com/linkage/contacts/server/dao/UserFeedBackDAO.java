package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.UserFeedBack;

public interface UserFeedBackDAO
{
	public abstract void insert(UserFeedBack userFeedBack);
	
	public abstract void update(UserFeedBack userFeedBack);
	
	public abstract void delete(UserFeedBack userFeedBack);
	
	public abstract UserFeedBack selectByPrimaryKey(int feedback_id);
}
