package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.UserMessageMark;

public interface UserMessageMarkDAO
{
	public abstract void insert(UserMessageMark userMessageMark);
	
	public abstract void update(UserMessageMark userMessageMark);
	
	public abstract void delete(UserMessageMark userMessageMark);
	
	public abstract UserMessageMark selectByPrimaryKey(int user_id);
	
	public abstract boolean existsByUserId(int user_id);
}
