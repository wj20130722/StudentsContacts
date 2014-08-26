package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.UserCards;
import com.linkage.mybatis.util.CriteriaManager;

public interface UserCardsMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int card_id);

    int insert(UserCards userCards);

    int insertSelective(UserCards userCards);

    List<UserCards> selectByWhereCondition(CriteriaManager criteriaManager);

    UserCards selectByPrimaryKey(int card_id);

    int updateByWhereConditionSelective(@Param("userCards") UserCards userCards, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("userCards") UserCards userCards, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(UserCards userCards);

    int updateByPrimaryKey(UserCards userCards);
}