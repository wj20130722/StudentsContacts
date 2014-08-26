package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.UserCards;

public interface UserCardsDAO
{
public abstract void insert(UserCards userCards);
	
	public abstract void update(UserCards userCards);
	
	public abstract void delete(UserCards userCards);
	
	public abstract UserCards selectByPrimaryKey(int card_id);
}
