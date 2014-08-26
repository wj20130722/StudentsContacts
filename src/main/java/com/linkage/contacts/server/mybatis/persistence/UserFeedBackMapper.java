package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.UserFeedBack;
import com.linkage.mybatis.util.CriteriaManager;

public interface UserFeedBackMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int feedback_id);

    int insert(UserFeedBack userFeedBack);

    int insertSelective(UserFeedBack userFeedBack);

    List<UserFeedBack> selectByWhereCondition(CriteriaManager criteriaManager);

    UserFeedBack selectByPrimaryKey(int feedback_id);

    int updateByWhereConditionSelective(@Param("userFeedBack") UserFeedBack userFeedBack, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("userFeedBack") UserFeedBack userFeedBack, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(UserFeedBack userFeedBack);

    int updateByPrimaryKey(UserFeedBack userFeedBack);
}