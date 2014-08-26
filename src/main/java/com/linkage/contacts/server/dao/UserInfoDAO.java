package com.linkage.contacts.server.dao;

import java.util.List;

import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.vo.FormUserInfo;
import com.linkage.contacts.server.vo.FormUserInfo2;

public interface UserInfoDAO
{
	public abstract void insert(UserInfo userInfo);
	
	public abstract void update(UserInfo userInfo);
	
	public abstract void delete(UserInfo userInfo);
	
	public abstract UserInfo selectByPrimaryKey(int user_id);

	public abstract UserInfo selectByTokenid(String tokenid);

	public abstract UserInfo selectByUserName(String username);

	public abstract UserInfo selectByPhoneNum(String phonenum);

	public abstract UserInfo selectByEmail(String email);

	public abstract void saveUserInfo(FormUserInfo userInfo);

	public abstract int countByUserInfo(String user_name, int college_id, int university_id, int year);

	public abstract void updateUserInfo(FormUserInfo userInfo);
	
	public abstract void updatePersonalInfo(FormUserInfo2 userInfo);

	public abstract List<UserInfo> getSysAdmin(int university_id);

	public abstract int count2ByUserInfo(String user_name, String xuehao, int university_id, int year);

	public abstract int countByEmail(String email);

	public abstract int countByPhoneNum(String phonenum);

	public abstract UserInfo selectByXuehao(String xuehao);

	public abstract int countByQQ(String qq);

	public abstract int countByEmailUser(String email, int user_id);

	public abstract int countByPhoneNumUser(String phonenum, int user_id);

	public abstract int countByQQUser(String qq, int user_id);
	
}
