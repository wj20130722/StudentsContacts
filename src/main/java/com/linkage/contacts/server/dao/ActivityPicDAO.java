package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.ActivityPic;

public interface ActivityPicDAO
{
	public abstract void insert(ActivityPic activityPic);
	
	public abstract void update(ActivityPic activityPic);
	
	public abstract void delete(ActivityPic activityPic);
	
	public abstract void deleteByActivityId(int activity_id);
	
	public abstract ActivityPic selectByPrimaryKey(int pic_id);
}
