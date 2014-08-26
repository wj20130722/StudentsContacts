package com.linkage.contacts.server.dao.mybatis.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.AluassociationConfigDAO;
import com.linkage.contacts.server.entity.AluassociationConfig;
import com.linkage.contacts.server.mybatis.persistence.AluassociationConfigMapper;
import com.linkage.contacts.server.vo.FormAluassociation;

@Repository("mybatisAluassociationConfigDao")
public class MybatisAluassociationConfigDaoImpl implements AluassociationConfigDAO
{
	@Autowired
	private AluassociationConfigMapper aluassociationConfigMapper;
	
	@Override
	public void insert(AluassociationConfig aluassociationConfig)
	{
		int count = aluassociationConfigMapper.insert(aluassociationConfig);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(AluassociationConfig aluassociationConfig)
	{
		int count = aluassociationConfigMapper.updateByPrimaryKey(aluassociationConfig);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(AluassociationConfig aluassociationConfig)
	{
		int count = aluassociationConfigMapper.deleteByPrimaryKey(aluassociationConfig.getAluassociation_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public AluassociationConfig selectByPrimaryKey(int aluassociation_id)
	{
		return aluassociationConfigMapper.selectByPrimaryKey(aluassociation_id);
	}

	@Override
  public List<FormAluassociation> getAluInfosByUser(int user_id,int university_id)
  {
		List<FormAluassociation> list = aluassociationConfigMapper.getAluInfosByUser(user_id,university_id);
		if(null == list)
			list = new ArrayList<FormAluassociation>();
	  return list;
  }

	@Override
  public List<FormAluassociation> getAluInfosByAdmin(int university_id)
  {
		List<FormAluassociation> list = aluassociationConfigMapper.getAluInfosByAdmin(university_id);
		if(null == list)
			list = new ArrayList<FormAluassociation>();
	  return list;
  }

}
