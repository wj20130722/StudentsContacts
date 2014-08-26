package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.ClassRoleInfo;
import com.linkage.contacts.server.entity.ClassRoleInfoKey;
import com.linkage.mybatis.util.CriteriaManager;

public interface ClassRoleInfoMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(ClassRoleInfoKey classRoleInfoKey);

    int insert(ClassRoleInfo classRoleInfo);

    int insertSelective(ClassRoleInfo classRoleInfo);

    List<ClassRoleInfo> selectByWhereCondition(CriteriaManager criteriaManager);

    ClassRoleInfo selectByPrimaryKey(ClassRoleInfoKey classRoleInfoKey);

    int updateByWhereConditionSelective(@Param("classRoleInfo") ClassRoleInfo classRoleInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("classRoleInfo") ClassRoleInfo classRoleInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(ClassRoleInfo classRoleInfo);

    int updateByPrimaryKey(ClassRoleInfo classRoleInfo);
}