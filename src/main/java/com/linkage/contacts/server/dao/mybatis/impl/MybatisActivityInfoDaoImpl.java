package com.linkage.contacts.server.dao.mybatis.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.ActivityInfoDAO;
import com.linkage.contacts.server.entity.ActivityInfo;
import com.linkage.contacts.server.mybatis.persistence.ActivityInfoMapper;

@Repository("mybatisActivityInfoDao")
public class MybatisActivityInfoDaoImpl implements ActivityInfoDAO
{
	@Autowired
	private ActivityInfoMapper activityInfoMapper;
	
	@Override
	public void insert(ActivityInfo activityInfo)
	{
		int count = activityInfoMapper.insertSelective(activityInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(ActivityInfo activityInfo)
	{
		int count = activityInfoMapper.updateByPrimaryKeySelective(activityInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(ActivityInfo activityInfo)
	{
		int count = activityInfoMapper.deleteByPrimaryKey(activityInfo.getActivity_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public ActivityInfo selectByPrimaryKey(int activity_id)
	{
		return activityInfoMapper.selectByPrimaryKey(activity_id);
	}

	@Override
  public List<ActivityInfo> getActivityInfo(int university_id, int index, int pagesize)
  {
		List<ActivityInfo> list = activityInfoMapper.getActivityInfo(university_id,index,pagesize);
		if(null==list)
		{
			list = new ArrayList<ActivityInfo>();
		}
	  return list;
  }

	@Override
  public List<ActivityInfo> getActivityInfo2(int university_id, int user_id,int index, int pagesize)
  {
		List<ActivityInfo> list = activityInfoMapper.getActivityInfo2(university_id,user_id,index,pagesize);
		if(null==list)
		{
			list = new ArrayList<ActivityInfo>();
		}
	  return list;
  }

	@Override
  public List<ActivityInfo> getActivityInfo3(int university_id, int index, int pagesize)
  {
		List<ActivityInfo> list = activityInfoMapper.getActivityInfo3(university_id,index,pagesize);
		if(null==list)
		{
			list = new ArrayList<ActivityInfo>();
		}
	  return list;
  }

	@Override
  public List<ActivityInfo> getActivityInfo4(int university_id, int index, int pagesize)
  {
		List<ActivityInfo> list = activityInfoMapper.getActivityInfo4(university_id,index,pagesize);
		if(null==list)
		{
			list = new ArrayList<ActivityInfo>();
		}
	  return list;
  }

	@Override
  public List<ActivityInfo> getMyActivity(int university_id, int user_id, int index, int pagesize)
  {
		List<ActivityInfo> list = activityInfoMapper.getMyActivity(university_id,user_id,index,pagesize);
		if(null==list)
		{
			list = new ArrayList<ActivityInfo>();
		}
	  return list;
  }

}
