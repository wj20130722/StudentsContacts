package com.linkage.contacts.server.bo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.UserCardsDAO;
import com.linkage.contacts.server.entity.UserCards;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;

@Service
public class UserCardsBO
{
	private static final Log log = LogFactory.getLog(UserCardsBO.class);
	
	@Autowired
	@Qualifier("mybatisUserCardsDao")
	private UserCardsDAO userCardsDAO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private CodeBO code;
	
	public void save(UserCards userCards)
	{
		if(0==userCards.getCard_id())
		{
			userCards.setCard_id((int)code.newCode("user_cards.card_id", "用户名片信息", 1));
			this.userCardsDAO.insert(userCards);
			log.info("用户名片信息保存成功。");
		}
		else
		{
			this.userCardsDAO.update(userCards);
			log.info("用户名片信息更新成功。");
		}
	}
	
	
	
	
	
}
