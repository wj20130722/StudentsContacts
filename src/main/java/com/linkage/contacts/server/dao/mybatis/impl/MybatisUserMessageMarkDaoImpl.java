package com.linkage.contacts.server.dao.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.UserMessageMarkDAO;
import com.linkage.contacts.server.entity.UserMessageMark;
import com.linkage.contacts.server.mybatis.persistence.UserMessageMarkMapper;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;

@Repository("mybatisUserMessageMarkDao")
public class MybatisUserMessageMarkDaoImpl implements UserMessageMarkDAO
{

	@Autowired
	private UserMessageMarkMapper userMessageMarkMapper;
	
	@Override
  public void insert(UserMessageMark userMessageMark)
  {
		int count = userMessageMarkMapper.insert(userMessageMark);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
  }

	@Override
  public void update(UserMessageMark userMessageMark)
  {
		int count = userMessageMarkMapper.updateByPrimaryKey(userMessageMark);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
  }

	@Override
  public void delete(UserMessageMark userMessageMark)
  {
		int count = userMessageMarkMapper.deleteByPrimaryKey(userMessageMark.getUser_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
  }

	@Override
  public UserMessageMark selectByPrimaryKey(int user_id)
  {
		return userMessageMarkMapper.selectByPrimaryKey(user_id);
  }

	@Override
  public boolean existsByUserId(int user_id)
  {
		boolean exists = false;
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("user_id", user_id));
		int count = userMessageMarkMapper.countByWhereCondition(cri);
		if(count > 0)
			exists = true;
	  return exists;
  }

	

}
