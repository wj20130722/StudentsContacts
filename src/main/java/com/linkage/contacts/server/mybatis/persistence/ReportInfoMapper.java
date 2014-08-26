package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.ReportInfo;
import com.linkage.mybatis.util.CriteriaManager;

public interface ReportInfoMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int report_id);

    int insert(ReportInfo reportInfo);

    int insertSelective(ReportInfo reportInfo);

    List<ReportInfo> selectByWhereCondition(CriteriaManager criteriaManager);

    ReportInfo selectByPrimaryKey(int report_id);

    int updateByWhereConditionSelective(@Param("reportInfo") ReportInfo reportInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("reportInfo") ReportInfo reportInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(ReportInfo reportInfo);

    int updateByPrimaryKey(ReportInfo reportInfo);
}