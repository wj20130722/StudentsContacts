package com.linkage.contacts.server.dao.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.UserFeedBackDAO;
import com.linkage.contacts.server.entity.UserFeedBack;
import com.linkage.contacts.server.mybatis.persistence.UserFeedBackMapper;

@Repository("mybatisUserFeedBackDao")
public class MybatisUserFeedBackDaoImpl implements UserFeedBackDAO
{
	@Autowired
	private UserFeedBackMapper userFeedBackMapper;
	
	@Override
	public void insert(UserFeedBack userFeedBack)
	{
		int count = userFeedBackMapper.insert(userFeedBack);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(UserFeedBack userFeedBack)
	{
		int count = userFeedBackMapper.updateByPrimaryKey(userFeedBack);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(UserFeedBack userFeedBack)
	{
		int count = userFeedBackMapper.deleteByPrimaryKey(userFeedBack.getFeedback_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public UserFeedBack selectByPrimaryKey(int feedback_id)
	{
		return userFeedBackMapper.selectByPrimaryKey(feedback_id);
	}

}
