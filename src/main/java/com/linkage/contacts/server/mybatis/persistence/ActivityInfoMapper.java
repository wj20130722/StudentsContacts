package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.ActivityInfo;
import com.linkage.mybatis.util.CriteriaManager;

public interface ActivityInfoMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int activity_id);

    int insert(ActivityInfo activityInfo);

    int insertSelective(ActivityInfo activityInfo);

    List<ActivityInfo> selectByWhereCondition(CriteriaManager criteriaManager);

    ActivityInfo selectByPrimaryKey(int activity_id);

    int updateByWhereConditionSelective(@Param("activityInfo") ActivityInfo activityInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("activityInfo") ActivityInfo activityInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(ActivityInfo activityInfo);

    int updateByPrimaryKey(ActivityInfo activityInfo);

		List<ActivityInfo> getActivityInfo(@Param("university_id") int university_id,@Param("index") int index,@Param("pagesize") int pagesize);

		List<ActivityInfo> getActivityInfo2(@Param("university_id") int university_id,@Param("user_id") int user_id,@Param("index") int index,@Param("pagesize") int pagesize);

		List<ActivityInfo> getActivityInfo3(@Param("university_id") int university_id,@Param("index") int index,@Param("pagesize") int pagesize);

		List<ActivityInfo> getActivityInfo4(@Param("university_id") int university_id,@Param("index") int index,@Param("pagesize") int pagesize);

		List<ActivityInfo> getMyActivity(@Param("university_id") int university_id,@Param("user_id") int user_id,@Param("index") int index,@Param("pagesize") int pagesize);
}