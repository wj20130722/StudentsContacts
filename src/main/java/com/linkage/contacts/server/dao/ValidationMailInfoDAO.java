package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.ValidationMailInfo;

public interface ValidationMailInfoDAO
{
	public abstract void insert(ValidationMailInfo validationMailInfo);
	
	public abstract void update(ValidationMailInfo validationMailInfo);
	
	public abstract void delete(ValidationMailInfo validationMailInfo);
	
	public abstract ValidationMailInfo selectByPrimaryKey(int validate_id);

	public abstract ValidationMailInfo selectByKey(String key);

	public abstract ValidationMailInfo selectByUser(int validate_man_id, int user_id);
}
