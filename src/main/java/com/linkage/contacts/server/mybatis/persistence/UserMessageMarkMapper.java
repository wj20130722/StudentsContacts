package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.UserMessageMark;
import com.linkage.mybatis.util.CriteriaManager;

public interface UserMessageMarkMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int user_id);

    int insert(UserMessageMark userMessageMark);

    int insertSelective(UserMessageMark userMessageMark);

    List<UserMessageMark> selectByWhereCondition(CriteriaManager criteriaManager);

    UserMessageMark selectByPrimaryKey(int user_id);

    int updateByWhereConditionSelective(@Param("userMessageMark") UserMessageMark userMessageMark, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("userMessageMark") UserMessageMark userMessageMark, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(UserMessageMark userMessageMark);

    int updateByPrimaryKey(UserMessageMark userMessageMark);
}