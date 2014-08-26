package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.ActivityHisPic;
import com.linkage.mybatis.util.CriteriaManager;

public interface ActivityHisPicMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int pic_id);

    int insert(ActivityHisPic activityHisPic);

    int insertSelective(ActivityHisPic activityHisPic);

    List<ActivityHisPic> selectByWhereCondition(CriteriaManager criteriaManager);

    ActivityHisPic selectByPrimaryKey(int pic_id);

    int updateByWhereConditionSelective(@Param("activityHisPic") ActivityHisPic activityHisPic, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("activityHisPic") ActivityHisPic activityHisPic, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(ActivityHisPic activityHisPic);

    int updateByPrimaryKey(ActivityHisPic activityHisPic);
}