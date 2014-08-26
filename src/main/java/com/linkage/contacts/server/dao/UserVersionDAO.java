package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.UserVersion;

public interface UserVersionDAO
{
	public abstract void insert(UserVersion userVersion);
	
	public abstract void update(UserVersion userVersion);
	
	public abstract void delete(UserVersion userVersion);
	
	public abstract UserVersion selectByPrimaryKey(int id);

	public abstract int countByVersionId(int platform,int user_id, int version_id);

	public abstract int countByVersionInfo(int platform,int user_id, String version_info);

	public abstract int countByUserId(int platform, int user_id);
}
