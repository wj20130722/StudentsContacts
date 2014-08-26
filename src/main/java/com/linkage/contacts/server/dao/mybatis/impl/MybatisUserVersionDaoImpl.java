package com.linkage.contacts.server.dao.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.UserVersionDAO;
import com.linkage.contacts.server.entity.UserVersion;
import com.linkage.contacts.server.mybatis.persistence.UserVersionMapper;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;

@Repository("mybatisUserVersionDao")
public class MybatisUserVersionDaoImpl implements UserVersionDAO
{
	@Autowired
	private UserVersionMapper userVersionMapper;
	
	@Override
	public void insert(UserVersion userVersion)
	{
		int count = userVersionMapper.insert(userVersion);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(UserVersion userVersion)
	{
		int count = userVersionMapper.updateByPrimaryKey(userVersion);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(UserVersion userVersion)
	{
		int count = userVersionMapper.deleteByPrimaryKey(userVersion.getId());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public UserVersion selectByPrimaryKey(int id)
	{
		return userVersionMapper.selectByPrimaryKey(id);
	}

	@Override
  public int countByVersionId(int platform,int user_id, int version_id)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("platform", platform)).add(Restrictions.equalTo("user_id", user_id)).add(Restrictions.equalTo("version_id", version_id));
	  return userVersionMapper.countByWhereCondition(cri);
  }

	@Override
  public int countByVersionInfo(int platform,int user_id, String version_info)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("platform", platform)).add(Restrictions.equalTo("user_id", user_id)).add(Restrictions.equalTo("version_info", version_info));
	  return userVersionMapper.countByWhereCondition(cri);
  }

	@Override
  public int countByUserId(int platform, int user_id)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("platform", platform)).add(Restrictions.equalTo("user_id", user_id));
	  return userVersionMapper.countByWhereCondition(cri);
  }

}
