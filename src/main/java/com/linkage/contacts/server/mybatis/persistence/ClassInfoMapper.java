package com.linkage.contacts.server.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.contacts.server.entity.ClassInfo;
import com.linkage.contacts.server.vo.FormClassCreateInfo;
import com.linkage.contacts.server.vo.FormClassInfo;
import com.linkage.mybatis.util.CriteriaManager;

public interface ClassInfoMapper {
    int countByWhereCondition(CriteriaManager criteriaManager);

    int deleteByWhereCondition(CriteriaManager criteriaManager);

    int deleteByPrimaryKey(int class_id);

    int insert(ClassInfo classInfo);

    int insertSelective(ClassInfo classInfo);

    List<ClassInfo> selectByWhereCondition(CriteriaManager criteriaManager);

    ClassInfo selectByPrimaryKey(int class_id);

    int updateByWhereConditionSelective(@Param("classInfo") ClassInfo classInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByWhereCondition(@Param("classInfo") ClassInfo classInfo, @Param("criteriaManager") CriteriaManager criteriaManager);

    int updateByPrimaryKeySelective(ClassInfo classInfo);

    int updateByPrimaryKey(ClassInfo classInfo);
    
    //普通用户和校班级管理员获取加入的班级信息
    List<FormClassInfo> getClassInfosByUser(@Param("user_id") int user_id,@Param("university_id") int university_id);
    //管理员获取所有的班级信息
    List<FormClassInfo> getClassInfosByAdmin(int university_id);
    
    //FormClassInfo getClassInfoByUser(@Param("user_id") int user_id,@Param("class_id") int class_id);

		List<FormClassCreateInfo> getCreateClassInfo(@Param("user_id") int user_id,@Param("university_id") int university_id);

		List<FormClassCreateInfo> getCreateClassInfoByAdmin(int university_id);
}