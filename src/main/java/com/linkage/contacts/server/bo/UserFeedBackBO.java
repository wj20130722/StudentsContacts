package com.linkage.contacts.server.bo;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.UserFeedBackDAO;
import com.linkage.contacts.server.entity.UserFeedBack;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;

@Service
public class UserFeedBackBO
{
	private static final Log log = LogFactory.getLog(UserFeedBackBO.class);
	
	@Autowired
	@Qualifier("mybatisUserFeedBackDao")
	private UserFeedBackDAO userFeedBackDAO;
	
	@Autowired
	private CodeBO code;
	
	@Autowired
	private CommonMapper commonMapper;
	
	
	public void save(UserFeedBack userFeedBack)
	{
		if(0==userFeedBack.getFeedback_id())
		{
			userFeedBack.setFeedback_id((int)code.newCode("user_feed_back.feedback_id", "用户意见反馈信息", 1));
			this.userFeedBackDAO.insert(userFeedBack);
			log.info("用户反馈意见保存成功。");
		}
		else
		{
			this.userFeedBackDAO.update(userFeedBack);
			log.info("用户反馈意见更新成功。");
		}
		
	}


	public void saveAdviceinfo(UserFeedBack userFeedBack)
  {
		userFeedBack.setFeedback_time(new Date());
		this.save(userFeedBack);
  }
	
	
}
