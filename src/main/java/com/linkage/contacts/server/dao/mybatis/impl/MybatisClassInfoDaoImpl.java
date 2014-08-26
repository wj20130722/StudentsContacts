package com.linkage.contacts.server.dao.mybatis.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.ClassInfoDAO;
import com.linkage.contacts.server.entity.ClassInfo;
import com.linkage.contacts.server.mybatis.persistence.ClassInfoMapper;
import com.linkage.contacts.server.vo.FormClassCreateInfo;
import com.linkage.contacts.server.vo.FormClassInfo;

@Repository("mybatisClassInfoDao")
public class MybatisClassInfoDaoImpl implements ClassInfoDAO
{
	@Autowired
	private ClassInfoMapper classInfoMapper;
	
	@Override
	public void insert(ClassInfo classInfo)
	{
		int count = classInfoMapper.insert(classInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(ClassInfo classInfo)
	{
		int count = classInfoMapper.updateByPrimaryKey(classInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(ClassInfo classInfo)
	{
		int count = classInfoMapper.deleteByPrimaryKey(classInfo.getClass_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public ClassInfo selectByPrimaryKey(int class_id)
	{
		return classInfoMapper.selectByPrimaryKey(class_id);
	}

	@Override
  public List<FormClassInfo> getClassInfosByUser(int user_id, int university_id)
  {
		List<FormClassInfo> list = classInfoMapper.getClassInfosByUser(user_id, university_id);
		if(null == list)
			list = new ArrayList<FormClassInfo>();
	  return list;
  }

	@Override
  public List<FormClassInfo> getClassInfosByAdmin(int university_id)
  {
		List<FormClassInfo> list = classInfoMapper.getClassInfosByAdmin(university_id);
		if(null == list)
			list = new ArrayList<FormClassInfo>();
	  return list;
  }

	@Override
  public List<FormClassCreateInfo> getCreateClassInfo(int user_id, int university_id)
  {
		List<FormClassCreateInfo> list = classInfoMapper.getCreateClassInfo(user_id, university_id);
		if(null == list)
			list = new ArrayList<FormClassCreateInfo>();
	  return list;
  }

	@Override
  public List<FormClassCreateInfo> getCreateClassInfoByAdmin(int university_id)
  {
		List<FormClassCreateInfo> list = classInfoMapper.getCreateClassInfoByAdmin(university_id);
		if(null == list)
			list = new ArrayList<FormClassCreateInfo>();
	  return list;
  }

}
