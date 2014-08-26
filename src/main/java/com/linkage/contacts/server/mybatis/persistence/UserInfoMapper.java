package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.vo.FormUserInfo;
import com.linkage.contacts.server.vo.FormUserInfo2;
import com.linkage.mybatis.util.CriteriaManager;

public interface UserInfoMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int user_id);

    int insert(UserInfo userInfo);

    int insertSelective(UserInfo userInfo);

    List<UserInfo> selectByWhereCondition(CriteriaManager criteriaManager);

    UserInfo selectByPrimaryKey(int user_id);

    int updateByWhereConditionSelective(@Param("userInfo") UserInfo userInfo, @Param("criteriaManager") CriteriaManager WhereCondition);

    int updateByWhereCondition(@Param("userInfo") UserInfo userInfo, @Param("criteriaManager") CriteriaManager WhereCondition);

    int updateByPrimaryKeySelective(UserInfo userInfo);

    int updateByPrimaryKey(UserInfo userInfo);

		int insertUserInfo(FormUserInfo userInfo);
		
		int updateUserInfo(FormUserInfo userInfo);
		
		int updatePersonalInfo(FormUserInfo2 userInfo);
}