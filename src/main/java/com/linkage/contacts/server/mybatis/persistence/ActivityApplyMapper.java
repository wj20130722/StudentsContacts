package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.ActivityApply;
import com.linkage.contacts.server.entity.ActivityApplyKey;
import com.linkage.mybatis.util.CriteriaManager;

public interface ActivityApplyMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(ActivityApplyKey activityApplyKey);

    int insert(ActivityApply activityApply);

    int insertSelective(ActivityApply activityApply);

    List<ActivityApply> selectByWhereCondition(CriteriaManager criteriaManager);

    ActivityApply selectByPrimaryKey(ActivityApplyKey activityApplyKey);

    int updateByWhereConditionSelective(@Param("activityApply") ActivityApply activityApply, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("activityApply") ActivityApply activityApply, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(ActivityApply activityApply);

    int updateByPrimaryKey(ActivityApply activityApply);
}