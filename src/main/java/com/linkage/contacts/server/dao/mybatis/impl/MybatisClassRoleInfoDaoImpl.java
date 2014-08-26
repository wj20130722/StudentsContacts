package com.linkage.contacts.server.dao.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.ClassRoleInfoDAO;
import com.linkage.contacts.server.entity.ClassRoleInfo;
import com.linkage.contacts.server.entity.ClassRoleInfoKey;
import com.linkage.contacts.server.mybatis.persistence.ClassRoleInfoMapper;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;

@Repository("mybatisClassRoleInfoDao")
public class MybatisClassRoleInfoDaoImpl implements ClassRoleInfoDAO
{
	@Autowired
	private ClassRoleInfoMapper classRoleInfoMapper;
	
	@Override
	public void insert(ClassRoleInfo classRoleInfo)
	{
		int count = classRoleInfoMapper.insert(classRoleInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(ClassRoleInfo classRoleInfo)
	{
		int count = classRoleInfoMapper.updateByPrimaryKey(classRoleInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(ClassRoleInfo classRoleInfo)
	{
		int count = classRoleInfoMapper.deleteByPrimaryKey(new ClassRoleInfoKey(classRoleInfo.getClass_id(),classRoleInfo.getUser_id()));
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public ClassRoleInfo selectByPrimaryKey(ClassRoleInfoKey classRoleInfoKey)
	{
		return classRoleInfoMapper.selectByPrimaryKey(classRoleInfoKey);
	}

	@Override
  public int countByUserClass(int user_id, int class_id)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("user_id", user_id)).add(Restrictions.equalTo("class_id", class_id));
	  return classRoleInfoMapper.countByWhereCondition(cri);
  }

	@Override
  public int deleteByUserClass(int user_id, int class_id)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("user_id", user_id)).add(Restrictions.equalTo("class_id", class_id));
	  return classRoleInfoMapper.deleteByWhereCondition(cri);
  }

}
