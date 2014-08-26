package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.ActivityApply;
import com.linkage.contacts.server.entity.ActivityApplyKey;

public interface ActivityApplyDAO
{
	public abstract void insert(ActivityApply activityApply);
	
	public abstract void update(ActivityApply activityApply);
	
	public abstract void delete(ActivityApply activityApply);
	
	public abstract void deleteByUserId(int activity_id,int user_id);
	
	public abstract ActivityApply selectByPrimaryKey(ActivityApplyKey activityApplyKey);
}
