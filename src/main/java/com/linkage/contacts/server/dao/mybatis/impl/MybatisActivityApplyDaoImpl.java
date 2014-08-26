package com.linkage.contacts.server.dao.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.ActivityApplyDAO;
import com.linkage.contacts.server.entity.ActivityApply;
import com.linkage.contacts.server.entity.ActivityApplyKey;
import com.linkage.contacts.server.mybatis.persistence.ActivityApplyMapper;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;

@Repository("mybatisActivityApplyDao")
public class MybatisActivityApplyDaoImpl implements ActivityApplyDAO
{
	@Autowired
	private ActivityApplyMapper activityApplyMapper;
	
	@Override
	public void insert(ActivityApply activityApply)
	{
		int count = activityApplyMapper.insert(activityApply);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(ActivityApply activityApply)
	{
		int count = activityApplyMapper.updateByPrimaryKey(activityApply);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(ActivityApply activityApply)
	{
		int count = activityApplyMapper.deleteByPrimaryKey(new ActivityApplyKey(activityApply.getActivity_id(),activityApply.getUser_id()));
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public ActivityApply selectByPrimaryKey(ActivityApplyKey activityApplyKey)
	{
		return activityApplyMapper.selectByPrimaryKey(activityApplyKey);
	}

	@Override
  public void deleteByUserId(int activity_id, int user_id)
  {
	  CriteriaManager cri = new CriteriaManager();
	  cri.or().add(Restrictions.equalTo("activity_id", activity_id)).add(Restrictions.equalTo("user_id", user_id));
	  int count = activityApplyMapper.deleteByWhereCondition(cri);
	  if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
  }

}
