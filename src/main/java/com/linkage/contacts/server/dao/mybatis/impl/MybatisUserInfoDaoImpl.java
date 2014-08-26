package com.linkage.contacts.server.dao.mybatis.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linkage.contacts.core.exception.ContactException;
import com.linkage.contacts.server.dao.UserInfoDAO;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.UserInfoMapper;
import com.linkage.contacts.server.vo.FormUserInfo;
import com.linkage.contacts.server.vo.FormUserInfo2;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;

@Repository("mybatisUserInfoDao")
public class MybatisUserInfoDaoImpl implements UserInfoDAO
{
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public void insert(UserInfo userInfo)
	{
		int count = userInfoMapper.insert(userInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void update(UserInfo userInfo)
	{
		int count = userInfoMapper.updateByPrimaryKey(userInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public void delete(UserInfo userInfo)
	{
		int count = userInfoMapper.deleteByPrimaryKey(userInfo.getUser_id());
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
	}

	@Override
	public UserInfo selectByPrimaryKey(int user_id)
	{
		return userInfoMapper.selectByPrimaryKey(user_id);
	}

	@Override
  public UserInfo selectByTokenid(String tokenid)
  {
		UserInfo userInfo = new UserInfo();
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("access_token", tokenid));
		List<UserInfo> userinfos = userInfoMapper.selectByWhereCondition(cri);
		if(null!=userinfos && userinfos.size()>0)
			userInfo = userinfos.get(0);
	  return userInfo;
  }

	@Override
  public UserInfo selectByUserName(String username)
  {
		UserInfo userInfo = new UserInfo();
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("user_name", username));
		List<UserInfo> userinfos = userInfoMapper.selectByWhereCondition(cri);
		if(null!=userinfos && userinfos.size()>0)
			userInfo = userinfos.get(0);
	  return userInfo;
  }

	@Override
  public UserInfo selectByPhoneNum(String phonenum)
  {
		UserInfo userInfo = new UserInfo();
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("phonenum", phonenum));
		List<UserInfo> userinfos = userInfoMapper.selectByWhereCondition(cri);
		if(null!=userinfos && userinfos.size()>0)
			userInfo = userinfos.get(0);
	  return userInfo;
  }

	@Override
  public UserInfo selectByEmail(String email)
  {
		UserInfo userInfo = new UserInfo();
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("mail", email));
		List<UserInfo> userinfos = userInfoMapper.selectByWhereCondition(cri);
		if(null!=userinfos && userinfos.size()>0)
			userInfo = userinfos.get(0);
	  return userInfo;
  }

	@Override
  public void saveUserInfo(FormUserInfo userInfo)
  {
		int count = userInfoMapper.insertUserInfo(userInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
  }

	@Override
  public int countByUserInfo(String user_name, int college_id, int university_id, int year)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("user_name", user_name)).add(Restrictions.equalTo("college_id", college_id))
		.add(Restrictions.equalTo("university_id", university_id)).add(Restrictions.equalTo("year", year));
	  return userInfoMapper.countByWhereCondition(cri);
  }

	@Override
  public void updateUserInfo(FormUserInfo userInfo)
  {
		int count = userInfoMapper.updateUserInfo(userInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
  }

	@Override
  public void updatePersonalInfo(FormUserInfo2 userInfo)
  {
		int count = userInfoMapper.updatePersonalInfo(userInfo);
		if (count != 1)
			throw new ContactException("影响了" + count + "条记录（正确的应该是1条），可能是Mybatis映射SQL语句错误，请检查SQL\n");
  }

	@Override
  public List<UserInfo> getSysAdmin(int university_id)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("university_id", university_id))
		.add(Restrictions.equalTo("super_admin", 1));
		List<UserInfo> list = userInfoMapper.selectByWhereCondition(cri);
		if(null == list)
			list = new ArrayList<UserInfo>();
	  return list;
  }

	@Override
  public int count2ByUserInfo(String user_name, String xuehao, int university_id,int year)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("user_name", user_name)).add(Restrictions.equalTo("xuehao", xuehao))
		.add(Restrictions.equalTo("university_id", university_id)).add(Restrictions.equalTo("year", year));
	  return userInfoMapper.countByWhereCondition(cri);
  }

	@Override
  public int countByEmail(String email)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("mail", email));
	  return userInfoMapper.countByWhereCondition(cri);
  }

	@Override
  public int countByPhoneNum(String phonenum)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("phonenum", phonenum));
	  return userInfoMapper.countByWhereCondition(cri);
  }
	

	@Override
  public int countByQQ(String qq)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("qq", qq));
	  return userInfoMapper.countByWhereCondition(cri);
  }

	@Override
  public UserInfo selectByXuehao(String xuehao)
  {
		UserInfo userInfo = new UserInfo();
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("xuehao", xuehao))
		.add(Restrictions.equalTo("is_authentication", 1));
		List<UserInfo> userinfos = userInfoMapper.selectByWhereCondition(cri);
		if(null!=userinfos && userinfos.size()>0)
			userInfo = userinfos.get(0);
	  return userInfo;
  }

	@Override
  public int countByEmailUser(String email, int user_id)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("mail", email)).add(Restrictions.notEqualTo("user_id", user_id));
	  return userInfoMapper.countByWhereCondition(cri);
  }

	@Override
  public int countByPhoneNumUser(String phonenum, int user_id)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("phonenum", phonenum)).add(Restrictions.notEqualTo("user_id", user_id));
	  return userInfoMapper.countByWhereCondition(cri);
  }

	@Override
  public int countByQQUser(String qq, int user_id)
  {
		CriteriaManager cri = new CriteriaManager();
		cri.or().add(Restrictions.equalTo("qq", qq)).add(Restrictions.notEqualTo("user_id", user_id));
	  return userInfoMapper.countByWhereCondition(cri);
  }


}
