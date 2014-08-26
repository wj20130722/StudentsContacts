package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.ClassCallShare;

public interface ClassCallShareDAO
{
	public abstract void insert(ClassCallShare classCallShare);
	
	public abstract void update(ClassCallShare classCallShare);
	
	public abstract void delete(ClassCallShare classCallShare);
	
	public abstract ClassCallShare selectByPrimaryKey(int share_id);
}
