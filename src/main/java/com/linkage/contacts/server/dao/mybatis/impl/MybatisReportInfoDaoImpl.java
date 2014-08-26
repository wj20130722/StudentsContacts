package com.linkage.contacts.server.dao.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.ReportInfoDAO;
import com.linkage.contacts.server.entity.ReportInfo;
import com.linkage.contacts.server.mybatis.persistence.ReportInfoMapper;

@Repository("mybatisReportInfoDao")
public class MybatisReportInfoDaoImpl implements ReportInfoDAO
{
	@Autowired
	private ReportInfoMapper reportInfoMapper;
	
	@Override
	public void insert(ReportInfo reportInfo)
	{
		int count = reportInfoMapper.insert(reportInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(ReportInfo reportInfo)
	{
		int count = reportInfoMapper.updateByPrimaryKey(reportInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(ReportInfo reportInfo)
	{
		int count = reportInfoMapper.deleteByPrimaryKey(reportInfo.getReport_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public ReportInfo selectByPrimaryKey(int report_id)
	{
		return reportInfoMapper.selectByPrimaryKey(report_id);
	}

}
