package com.linkage.contacts.server.bo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.UserVersionDAO;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.entity.UserVersion;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.util.Utility;

@Service
public class UserVersionBO
{
	private static final Log log = LogFactory.getLog(UserVersionBO.class);
	
	@Autowired
	@Qualifier("mybatisUserVersionDao")
	private UserVersionDAO userVersionDAO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private CodeBO code;
	
	public void save(UserVersion userVersion)
	{
		if(0==userVersion.getId())
		{
			this.userVersionDAO.insert(userVersion);
			log.info("用户版本信息保存成功。");
		}
		else
		{
			this.userVersionDAO.update(userVersion);
			log.info("用户版本信息更新成功。");
		}
		
	}

	public void saveUserVersion(UserInfo userInfo, int platform, String version_id, String version_info)
  {
		UserVersion userVersion = new UserVersion();
		userVersion.setUser_id(userInfo.getUser_id());
		userVersion.setPlatform(platform);
	  if(1==platform)
	  {
	  	userVersion.setVersion_id(Integer.parseInt(Utility.chgZero(version_id)));
	  	int count = this.countByUserId(platform, userVersion.getUser_id());
	  	if(count==0)
	  	{
	  		this.save(userVersion);
	  	}
	  	else
	  	{
	  		int count2 = this.countByVersionId(platform, userVersion.getUser_id(), userVersion.getVersion_id());
	  		if(count2==0)
	  		{
	  			String sql = "update user_version set version_id="+userVersion.getVersion_id()+" where platform="+platform+" and user_id="+userVersion.getUser_id();
	  			this.commonMapper.updateDataBySql(sql);
	  		}
	  	}
	  }
	  else
	  {
	  	userVersion.setVersion_info(version_info);
	  	int count = this.countByUserId(platform, userVersion.getUser_id());
	  	if(count==0)
	  	{
	  		this.save(userVersion);
	  	}
	  	else
	  	{
	  		int count2 = this.countByVersionInfo(platform, userVersion.getUser_id(), userVersion.getVersion_info());
	  		if(count2==0)
	  		{
	  			String sql = "update user_version set version_info='"+userVersion.getVersion_info()+"' where platform="+platform+" and user_id="+userVersion.getUser_id();
	  			this.commonMapper.updateDataBySql(sql);
	  		}
	  	}
	  }
  }
	
	public int countByUserId(int platform,int user_id)
	{
		return this.userVersionDAO.countByUserId(platform,user_id);
	}
	
	public int countByVersionId(int platform,int user_id,int version_id)
	{
		return this.userVersionDAO.countByVersionId(platform,user_id,version_id);
	}
	
	public int countByVersionInfo(int platform,int user_id,String version_info)
	{
		return this.userVersionDAO.countByVersionInfo(platform,user_id,version_info);
	}
	
	
	
	
	
}
