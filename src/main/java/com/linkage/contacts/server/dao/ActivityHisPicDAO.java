package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.ActivityHisPic;

public interface ActivityHisPicDAO
{
	public abstract void insert(ActivityHisPic activityHisPic);
	
	public abstract void update(ActivityHisPic activityHisPic);
	
	public abstract void delete(ActivityHisPic activityHisPic);
	
	public abstract ActivityHisPic selectByPrimaryKey(int pic_id);
}
