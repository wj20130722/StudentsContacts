package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.AluassociationConfig;
import com.linkage.contacts.server.vo.FormAluassociation;
import com.linkage.mybatis.util.CriteriaManager;

public interface AluassociationConfigMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int aluassociation_id);

    int insert(AluassociationConfig aluassociationConfig);

    int insertSelective(AluassociationConfig aluassociationConfig);

    List<AluassociationConfig> selectByWhereCondition(CriteriaManager criteriaManager);

    AluassociationConfig selectByPrimaryKey(int aluassociation_id);

    int updateByWhereConditionSelective(@Param("aluassociationConfig") AluassociationConfig aluassociationConfig, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("aluassociationConfig") AluassociationConfig aluassociationConfig, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(AluassociationConfig aluassociationConfig);

    int updateByPrimaryKey(AluassociationConfig aluassociationConfig);
    
    List<FormAluassociation> getAluInfosByUser(@Param("user_id") int user_id,@Param("university_id") int university_id);
    
    List<FormAluassociation> getAluInfosByAdmin(int university_id);
    
}