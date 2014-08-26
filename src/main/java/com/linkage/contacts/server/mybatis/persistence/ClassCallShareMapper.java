package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.ClassCallShare;
import com.linkage.mybatis.util.CriteriaManager;

public interface ClassCallShareMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int share_id);

    int insert(ClassCallShare classCallShare);

    int insertSelective(ClassCallShare classCallShare);

    List<ClassCallShare> selectByWhereCondition(CriteriaManager criteriaManager);

    ClassCallShare selectByPrimaryKey(int share_id);

    int updateByWhereConditionSelective(@Param("classCallShare") ClassCallShare classCallShare, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("classCallShare") ClassCallShare classCallShare, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(ClassCallShare classCallShare);

    int updateByPrimaryKey(ClassCallShare classCallShare);
}