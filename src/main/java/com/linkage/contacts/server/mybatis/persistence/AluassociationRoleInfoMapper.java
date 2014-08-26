package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.AluassociationRoleInfo;
import com.linkage.contacts.server.entity.AluassociationRoleInfoKey;
import com.linkage.mybatis.util.CriteriaManager;

public interface AluassociationRoleInfoMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(AluassociationRoleInfoKey aluassociationRoleInfoKey);

    int insert(AluassociationRoleInfo aluassociationRoleInfo);

    int insertSelective(AluassociationRoleInfo aluassociationRoleInfo);

    List<AluassociationRoleInfo> selectByWhereCondition(CriteriaManager criteriaManager);

    AluassociationRoleInfo selectByPrimaryKey(AluassociationRoleInfoKey aluassociationRoleInfoKey);

    int updateByWhereConditionSelective(@Param("aluassociationRoleInfo") AluassociationRoleInfo aluassociationRoleInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("aluassociationRoleInfo") AluassociationRoleInfo aluassociationRoleInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(AluassociationRoleInfo aluassociationRoleInfo);

    int updateByPrimaryKey(AluassociationRoleInfo aluassociationRoleInfo);
}