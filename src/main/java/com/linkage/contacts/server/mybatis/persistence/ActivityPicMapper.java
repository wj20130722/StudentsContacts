package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.ActivityPic;
import com.linkage.mybatis.util.CriteriaManager;

public interface ActivityPicMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int pic_id);

    int insert(ActivityPic activityPic);

    int insertSelective(ActivityPic activityPic);

    List<ActivityPic> selectByWhereCondition(CriteriaManager criteriaManager);

    ActivityPic selectByPrimaryKey(int pic_id);

    int updateByWhereConditionSelective(@Param("activityPic") ActivityPic activityPic, @Param("WhereCondition") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("activityPic") ActivityPic activityPic, @Param("WhereCondition") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(ActivityPic activityPic);

    int updateByPrimaryKey(ActivityPic activityPic);
}