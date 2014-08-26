package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.ClassRoleInfo;
import com.linkage.contacts.server.entity.ClassRoleInfoKey;

public interface ClassRoleInfoDAO
{
	public abstract void insert(ClassRoleInfo classRoleInfo);
	
	public abstract void update(ClassRoleInfo classRoleInfo);
	
	public abstract void delete(ClassRoleInfo classRoleInfo);
	
	public abstract ClassRoleInfo selectByPrimaryKey(ClassRoleInfoKey classRoleInfoKey);
	
	public abstract int countByUserClass(int user_id,int class_id);
	
	public abstract int deleteByUserClass(int user_id,int class_id);
}
