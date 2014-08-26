package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.ReportInfo;

public interface ReportInfoDAO
{
	public abstract void insert(ReportInfo reportInfo);
	
	public abstract void update(ReportInfo reportInfo);
	
	public abstract void delete(ReportInfo reportInfo);
	
	public abstract ReportInfo selectByPrimaryKey(int report_id);
}
