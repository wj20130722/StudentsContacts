package com.linkage.contacts.server.dao.mybatis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.ValidationMailInfoDAO;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.entity.ValidationMailInfo;
import com.linkage.contacts.server.mybatis.persistence.ValidationMailInfoMapper;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;

@Repository("mybatisValidationMailInfoDao")
public class MybatisValidationMailInfoDaoImpl implements ValidationMailInfoDAO
{

	@Autowired
	private ValidationMailInfoMapper validationMailInfoMapper;
	
	@Override
	public void insert(ValidationMailInfo validationMailInfo)
	{
		int count = validationMailInfoMapper.insert(validationMailInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(ValidationMailInfo validationMailInfo)
	{
		int count = validationMailInfoMapper.updateByPrimaryKey(validationMailInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(ValidationMailInfo validationMailInfo)
	{
		int count = validationMailInfoMapper.deleteByPrimaryKey(validationMailInfo.getValidate_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public ValidationMailInfo selectByPrimaryKey(int validate_id)
	{
		return validationMailInfoMapper.selectByPrimaryKey(validate_id);
	}

	@Override
  public ValidationMailInfo selectByKey(String key)
  {
		ValidationMailInfo validationMailInfo = new ValidationMailInfo();
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("validate_key", key));
		List<ValidationMailInfo> validationLists = validationMailInfoMapper.selectByWhereCondition(cri);
		if(null!=validationLists && validationLists.size()>0)
			validationMailInfo = validationLists.get(0);
	  return validationMailInfo;
  }

	@Override
  public ValidationMailInfo selectByUser(int validate_man_id, int user_id)
  {
		ValidationMailInfo validationMailInfo = new ValidationMailInfo();
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("validate_man_id", validate_man_id))
		.add(Restrictions.equalTo("user_id", user_id));
		List<ValidationMailInfo> validationLists = validationMailInfoMapper.selectByWhereCondition(cri);
		if(null!=validationLists && validationLists.size()>0)
			validationMailInfo = validationLists.get(0);
	  return validationMailInfo;
  }

}
