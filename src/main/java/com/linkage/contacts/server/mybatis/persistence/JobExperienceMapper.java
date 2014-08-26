package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.JobExperience;
import com.linkage.mybatis.util.CriteriaManager;

public interface JobExperienceMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int experience_id);

    int insert(JobExperience jobExperience);

    int insertSelective(JobExperience jobExperience);

    List<JobExperience> selectByWhereCondition(CriteriaManager criteriaManager);

    JobExperience selectByPrimaryKey(int experience_id);

    int updateByWhereConditionSelective(@Param("jobExperience") JobExperience jobExperience, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("jobExperience") JobExperience jobExperience, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(JobExperience jobExperience);

    int updateByPrimaryKey(JobExperience jobExperience);
}