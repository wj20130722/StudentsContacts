package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.ValidationMailInfo;
import com.linkage.mybatis.util.CriteriaManager;

public interface ValidationMailInfoMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int validate_id);

    int insert(ValidationMailInfo validationMailInfo);

    int insertSelective(ValidationMailInfo validationMailInfo);

    List<ValidationMailInfo> selectByWhereCondition(CriteriaManager criteriaManager);

    ValidationMailInfo selectByPrimaryKey(int validate_id);

    int updateByWhereConditionSelective(@Param("validationMailInfo") ValidationMailInfo validationMailInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("validationMailInfo") ValidationMailInfo validationMailInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(ValidationMailInfo validationMailInfo);

    int updateByPrimaryKey(ValidationMailInfo validationMailInfo);
}