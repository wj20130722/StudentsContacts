package com.linkage.contacts.server.dao.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.ClassCallShareDAO;
import com.linkage.contacts.server.entity.ClassCallShare;
import com.linkage.contacts.server.mybatis.persistence.ClassCallShareMapper;

@Repository("mybatisClassCallShareDao")
public class MybatisClassCallShareDaoImpl implements ClassCallShareDAO
{
	@Autowired
	private ClassCallShareMapper classCallShareMapper;
	
	@Override
	public void insert(ClassCallShare classCallShare)
	{
		int count = classCallShareMapper.insert(classCallShare);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(ClassCallShare classCallShare)
	{
		int count = classCallShareMapper.updateByPrimaryKey(classCallShare);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(ClassCallShare classCallShare)
	{
		int count = classCallShareMapper.deleteByPrimaryKey(classCallShare.getShare_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public ClassCallShare selectByPrimaryKey(int share_id)
	{
		return classCallShareMapper.selectByPrimaryKey(share_id);
	}

}
