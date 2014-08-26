package com.linkage.contacts.server.dao.mybatis.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.SystemPushMessageDAO;
import com.linkage.contacts.server.entity.SystemPushMessage;
import com.linkage.contacts.server.mybatis.persistence.SystemPushMessageMapper;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;

@Repository("mybatisSystemPushMessageDao")
public class MybatisSystemPushMessageDaoImpl implements SystemPushMessageDAO
{
	@Autowired
	private SystemPushMessageMapper systemPushMessageMapper;
	
	@Override
	public void insert(SystemPushMessage systemPushMessage)
	{
		int count = systemPushMessageMapper.insert(systemPushMessage);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(SystemPushMessage systemPushMessage)
	{
		int count = systemPushMessageMapper.updateByPrimaryKey(systemPushMessage);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(SystemPushMessage systemPushMessage)
	{
		int count = systemPushMessageMapper.deleteByPrimaryKey(systemPushMessage.getSystem_push_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public SystemPushMessage selectByPrimaryKey(int system_push_id)
	{
		return systemPushMessageMapper.selectByPrimaryKey(system_push_id);
	}

	@Override
  public List<SystemPushMessage> getSystemMessage(int user_id)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("to_user_id", user_id))
		.add(Restrictions.equalTo("push_state", 0));
		cri.setOrderByClause("push_time desc");
		List<SystemPushMessage> list = systemPushMessageMapper.selectByWhereCondition(cri);
		if(null == list)
			list = new ArrayList<SystemPushMessage>();
	  return list;
  }

}
