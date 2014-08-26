package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.UserVersion;
import com.linkage.mybatis.util.CriteriaManager;

public interface UserVersionMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int id);

    int insert(UserVersion userVersion);

    int insertSelective(UserVersion userVersion);

    List<UserVersion> selectByWhereCondition(CriteriaManager criteriaManager);

    UserVersion selectByPrimaryKey(int id);

    int updateByWhereConditionSelective(@Param("userVersion") UserVersion userVersion, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("userVersion") UserVersion userVersion, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(UserVersion userVersion);

    int updateByPrimaryKey(UserVersion userVersion);
}