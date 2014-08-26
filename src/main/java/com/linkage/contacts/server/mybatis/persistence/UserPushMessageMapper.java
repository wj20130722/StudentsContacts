package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.UserPushMessage;
import com.linkage.contacts.server.vo.Contacts;
import com.linkage.mybatis.util.CriteriaManager;

public interface UserPushMessageMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int push_id);

    int insert(UserPushMessage userPushMessage);

    int insertSelective(UserPushMessage userPushMessage);

    List<UserPushMessage> selectByWhereCondition(CriteriaManager criteriaManager);

    UserPushMessage selectByPrimaryKey(int push_id);

    int updateByWhereConditionSelective(@Param("userPushMessage") UserPushMessage userPushMessage, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("userPushMessage") UserPushMessage userPushMessage, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(UserPushMessage userPushMessage);

    int updateByPrimaryKey(UserPushMessage userPushMessage);

		int insertBak(@Param("from_user_id") int from_user_id,@Param("to_user_id") int to_user_id, @Param("timeStr") String timeStr);

		List<Contacts> getContactsFromUser(int user_id);
}