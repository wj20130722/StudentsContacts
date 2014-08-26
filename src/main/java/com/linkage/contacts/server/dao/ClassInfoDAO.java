package com.linkage.contacts.server.dao;

import java.util.List;

import com.linkage.contacts.server.entity.ClassInfo;
import com.linkage.contacts.server.vo.FormClassCreateInfo;
import com.linkage.contacts.server.vo.FormClassInfo;

public interface ClassInfoDAO
{
	public abstract void insert(ClassInfo classInfo);
	
	public abstract void update(ClassInfo classInfo);
	
	public abstract void delete(ClassInfo classInfo);
	
	public abstract ClassInfo selectByPrimaryKey(int class_id);
	
	public abstract List<FormClassInfo> getClassInfosByUser(int user_id,int university_id);
	
	public abstract List<FormClassInfo> getClassInfosByAdmin(int university_id);

	public abstract List<FormClassCreateInfo> getCreateClassInfo(int user_id, int university_id);

	public abstract List<FormClassCreateInfo> getCreateClassInfoByAdmin(int university_id);
}
