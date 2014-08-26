package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.SystemPushMessage;
import com.linkage.mybatis.util.CriteriaManager;

public interface SystemPushMessageMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int system_push_id);

    int insert(SystemPushMessage systemPushMessage);

    int insertSelective(SystemPushMessage systemPushMessage);

    List<SystemPushMessage> selectByWhereCondition(CriteriaManager criteriaManager);

    SystemPushMessage selectByPrimaryKey(int system_push_id);

    int updateByWhereConditionSelective(@Param("systemPushMessage") SystemPushMessage systemPushMessage, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("systemPushMessage") SystemPushMessage systemPushMessage, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(SystemPushMessage systemPushMessage);

    int updateByPrimaryKey(SystemPushMessage systemPushMessage);
}