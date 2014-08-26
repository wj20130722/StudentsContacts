package com.linkage.contacts.server.dao.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.UserCardsDAO;
import com.linkage.contacts.server.entity.UserCards;
import com.linkage.contacts.server.mybatis.persistence.UserCardsMapper;

@Repository("mybatisUserCardsDao")
public class MybatisUserCardsDaoImpl implements UserCardsDAO
{
	@Autowired
	private UserCardsMapper userCardsMapper;
	
	@Override
	public void insert(UserCards userCards)
	{
		int count = userCardsMapper.insert(userCards);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(UserCards userCards)
	{
		int count = userCardsMapper.updateByPrimaryKey(userCards);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(UserCards userCards)
	{
		int count = userCardsMapper.deleteByPrimaryKey(userCards.getCard_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public UserCards selectByPrimaryKey(int card_id)
	{
		return userCardsMapper.selectByPrimaryKey(card_id);
	}

}
