package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.SkillInfo;
import com.linkage.mybatis.util.CriteriaManager;

public interface SkillInfoMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int skill_id);

    int insert(SkillInfo skillInfo);

    int insertSelective(SkillInfo skillInfo);

    List<SkillInfo> selectByWhereCondition(CriteriaManager criteriaManager);

    SkillInfo selectByPrimaryKey(int skill_id);

    int updateByWhereConditionSelective(@Param("skillInfo") SkillInfo skillInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("skillInfo") SkillInfo skillInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(SkillInfo record);

    int updateByPrimaryKey(SkillInfo record);
}