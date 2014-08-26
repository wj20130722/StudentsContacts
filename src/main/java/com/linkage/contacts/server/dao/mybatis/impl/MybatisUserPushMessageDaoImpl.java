package com.linkage.contacts.server.dao.mybatis.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.UserPushMessageDAO;
import com.linkage.contacts.server.entity.UserPushMessage;
import com.linkage.contacts.server.mybatis.persistence.UserPushMessageMapper;
import com.linkage.contacts.server.vo.Contacts;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;

@Repository("mybatisUserPushMessageDao")
public class MybatisUserPushMessageDaoImpl implements UserPushMessageDAO
{
	@Autowired
	private UserPushMessageMapper userPushMessageMapper;
	
	@Override
	public void insert(UserPushMessage userPushMessage)
	{
		int count = userPushMessageMapper.insert(userPushMessage);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(UserPushMessage userPushMessage)
	{
		int count = userPushMessageMapper.updateByPrimaryKey(userPushMessage);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(UserPushMessage userPushMessage)
	{
		int count = userPushMessageMapper.deleteByPrimaryKey(userPushMessage.getPush_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public UserPushMessage selectByPrimaryKey(int push_id)
	{
		return userPushMessageMapper.selectByPrimaryKey(push_id);
	}

	@Override
  public List<UserPushMessage> selectFromUserMessage(int from_user_id, int to_user_id, String timeStr)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("from_user_id", from_user_id))
		.add(Restrictions.equalTo("to_user_id", to_user_id))
		.add(Restrictions.gatherThan("push_time", timeStr))
		.add(Restrictions.equalTo("push_state", 0));
		cri.setOrderByClause("push_time");
		List<UserPushMessage> list = userPushMessageMapper.selectByWhereCondition(cri);
		if(null == list)
			list = new ArrayList<UserPushMessage>();
	  return list;
  }

	@Override
  public int insertBak(int from_user_id, int to_user_id, String timeStr)
  {
		int count = userPushMessageMapper.insertBak(from_user_id,to_user_id,timeStr);
		return count;
  }

	@Override
  public int deleteBak(int from_user_id, int to_user_id, String timeStr)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("from_user_id", from_user_id))
		.add(Restrictions.equalTo("to_user_id", to_user_id))
		.add(Restrictions.lessThanOrEqualTo("push_time", timeStr))
		.add(Restrictions.equalTo("push_state", 0));
		int count = userPushMessageMapper.deleteByWhereCondition(cri);
	  return count;
  }

	@Override
  public List<Contacts> getContactsFromUser(int user_id)
  {
		List<Contacts> list = userPushMessageMapper.getContactsFromUser(user_id);
		if(null == list)
			list = new ArrayList<Contacts>();
	  return list;
  }

}
