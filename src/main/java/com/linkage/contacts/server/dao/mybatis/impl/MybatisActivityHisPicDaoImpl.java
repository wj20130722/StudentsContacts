package com.linkage.contacts.server.dao.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.ActivityHisPicDAO;
import com.linkage.contacts.server.entity.ActivityHisPic;
import com.linkage.contacts.server.mybatis.persistence.ActivityHisPicMapper;

@Repository("mybatisActivityHisPicDao")
public class MybatisActivityHisPicDaoImpl implements ActivityHisPicDAO
{
	@Autowired
	private ActivityHisPicMapper activityHisPicMapper;
	
	@Override
	public void insert(ActivityHisPic activityHisPic)
	{
		int count = activityHisPicMapper.insert(activityHisPic);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(ActivityHisPic activityHisPic)
	{
		int count = activityHisPicMapper.updateByPrimaryKeySelective(activityHisPic);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(ActivityHisPic activityHisPic)
	{
		int count = activityHisPicMapper.deleteByPrimaryKey(activityHisPic.getPic_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public ActivityHisPic selectByPrimaryKey(int pic_id)
	{
		return activityHisPicMapper.selectByPrimaryKey(pic_id);
	}

}
