package com.linkage.contacts.server.bo;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.ClassCallShareDAO;
import com.linkage.contacts.server.entity.ClassCallShare;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.vo.FormClassCallShare;

@Service
public class ClassCallShareBO
{
	private static final Log log = LogFactory.getLog(ClassCallShareBO.class);
	
	@Autowired
	@Qualifier("mybatisClassCallShareDao")
	private ClassCallShareDAO classCallShareDAO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private CodeBO code;
	
	public void save(ClassCallShare classCallShare)
	{
		if(0==classCallShare.getShare_id())
		{
			this.classCallShareDAO.insert(classCallShare);
			log.info("召唤分享信息保存成功。");
		}
		else
		{
			this.classCallShareDAO.update(classCallShare);
			log.info("召唤分享信息更新成功。");
		}
		
	}

	public void saveShareInfo(UserInfo userInfo, FormClassCallShare formClassCallShare)
  {
		ClassCallShare classCallShare = new ClassCallShare();
		classCallShare.setClass_id(formClassCallShare.getClass_id());
		classCallShare.setShare_type(formClassCallShare.getShare_type());
		classCallShare.setUser_id(userInfo.getUser_id());
		classCallShare.setShare_time(new Date());
		this.save(classCallShare);
  }
	
	
}
