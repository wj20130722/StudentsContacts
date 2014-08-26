package com.linkage.contacts.server.dao;

import java.util.List;

import com.linkage.contacts.server.entity.ActivityInfo;

public interface ActivityInfoDAO
{
	public abstract void insert(ActivityInfo activityInfo);
	
	public abstract void update(ActivityInfo activityInfo);
	
	public abstract void delete(ActivityInfo activityInfo);
	
	public abstract ActivityInfo selectByPrimaryKey(int activity_id);

	public abstract List<ActivityInfo> getActivityInfo(int univisity_id, int index, int pagesize);

	public abstract List<ActivityInfo> getActivityInfo2(int university_id,int user_id, int index, int pagesize);

	public abstract List<ActivityInfo> getActivityInfo3(int university_id, int index, int pagesize);

	public abstract List<ActivityInfo> getActivityInfo4(int university_id, int index, int pagesize);

	public abstract List<ActivityInfo> getMyActivity(int university_id, int user_id, int index, int pagesize);
}
