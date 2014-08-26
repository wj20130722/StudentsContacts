package com.linkage.contacts.server.dao.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.ActivityPicDAO;
import com.linkage.contacts.server.entity.ActivityPic;
import com.linkage.contacts.server.mybatis.persistence.ActivityPicMapper;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;

@Repository("mybatisActivityPicDao")
public class MybatisActivityPicDaoImpl implements ActivityPicDAO
{
	@Autowired
	private ActivityPicMapper activityPicMapper;
	
	@Override
	public void insert(ActivityPic activityPic)
	{
		int count = activityPicMapper.insert(activityPic);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(ActivityPic activityPic)
	{
		int count = activityPicMapper.updateByPrimaryKeySelective(activityPic);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(ActivityPic activityPic)
	{
		int count = activityPicMapper.deleteByPrimaryKey(activityPic.getPic_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public ActivityPic selectByPrimaryKey(int pic_id)
	{
		return activityPicMapper.selectByPrimaryKey(pic_id);
	}

	@Override
  public void deleteByActivityId(int activity_id)
  {
	  CriteriaManager cri = new CriteriaManager();
	  cri.or().add(Restrictions.equalTo("activity_id", activity_id));
	  activityPicMapper.deleteByWhereCondition(cri);
  }

}
