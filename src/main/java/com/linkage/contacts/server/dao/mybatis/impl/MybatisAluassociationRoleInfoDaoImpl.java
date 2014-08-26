package com.linkage.contacts.server.dao.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.AluassociationRoleInfoDAO;
import com.linkage.contacts.server.entity.AluassociationRoleInfo;
import com.linkage.contacts.server.entity.AluassociationRoleInfoKey;
import com.linkage.contacts.server.mybatis.persistence.AluassociationRoleInfoMapper;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;

@Repository("mybatisAluassociationRoleInfoDao")
public class MybatisAluassociationRoleInfoDaoImpl implements AluassociationRoleInfoDAO
{
	@Autowired
	private AluassociationRoleInfoMapper aluassociationRoleInfoMapper;
	
	@Override
	public void insert(AluassociationRoleInfo aluassociationRoleInfo)
	{
		int count = aluassociationRoleInfoMapper.insert(aluassociationRoleInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(AluassociationRoleInfo aluassociationRoleInfo)
	{
		int count = aluassociationRoleInfoMapper.updateByPrimaryKey(aluassociationRoleInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(AluassociationRoleInfo aluassociationRoleInfo)
	{
		int count = aluassociationRoleInfoMapper.deleteByPrimaryKey(new AluassociationRoleInfoKey(aluassociationRoleInfo.getAluassociation_id(),aluassociationRoleInfo.getUser_id()));
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public AluassociationRoleInfo selectByPrimaryKey(AluassociationRoleInfoKey aluassociationRoleInfoKey)
	{
		return aluassociationRoleInfoMapper.selectByPrimaryKey(aluassociationRoleInfoKey);
	}

	@Override
  public int countByUserAlu(int user_id, int aluassociation_id)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("user_id", user_id)).add(Restrictions.equalTo("aluassociation_id", aluassociation_id));
	  return aluassociationRoleInfoMapper.countByWhereCondition(cri);
  }

	@Override
  public int deleteByUserAlu(int user_id, int aluassociation_id)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("user_id", user_id)).add(Restrictions.equalTo("aluassociation_id", aluassociation_id));
	  return aluassociationRoleInfoMapper.deleteByWhereCondition(cri);
  }

}
