package com.linkage.contacts.server.mybatis.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkage.mybatis.util.CriteriaManager;

/**
 * <h1>mybatis公共使用的工具类</h1>
 * <p>该工具类封装了mybatis操作dao的公共的dao的抽象方法</p>
 * @author wangjie
 *
 */
public interface CommonMapper
{
	/**
	 * <h1>根据指定的条件获取指定的属性值</h1>
	 * @param tablename 表名
	 * @param columnname 查询的字段名
	 * @param conditionname 条件字段名
	 * @param conditionvalue 条件字段取值
	 * @return 指定的表中的字段的取值
	 */
	public abstract String getTableColumnValue(@Param("tablename") String tablename,@Param("columnname") String columnname,@Param("conditionname") String conditionname,@Param("conditionvalue") Object conditionvalue);
	
	/**
	 * <h1>根据指定的sql查询结果</h1>
	 * @param sql
	 * @return 指定表的字段的取值
	 */
	public abstract String getColumnValueBySql(@Param("sql") String sql);
	
//	public abstract String getColumnValueBySingleCondition(@Param("sql") String sql,@Param("conditionvalue") Object conditionvalue);
	
	/**
	 * <p>根据指定的sql将查询结果的每行数据封装成map,key为数据库的字段名,value为数据库字段的取值</p>
	 * @param sql
	 * @return
	 */
	public abstract List<HashMap<String, Object>> getTableRowBySql(@Param("sql") String sql);
	
	//public abstract List<HashMap<String, Object>> getTableRowBySingleCondition(@Param("sql") String sql,@Param("conditionvalue") Object conditionvalue);
	
	public abstract List<HashMap<String, Object>> getTableRowByWhereCondition(@Param("sql") String sql,@Param("criteriaManager") CriteriaManager criteriaManager);
	
	public abstract List<String> getTableColumnValueByWhereCondition(@Param("tablename") String tablename,@Param("columnname") String columnname,@Param("criteriaManager") CriteriaManager criteriaManager);
	
	public abstract List<String> getTableColumnValueBySql(@Param("sql") String sql);
	
	public abstract int updateDataBySql(@Param("sql") String sql); 
	
	public abstract int deleteDataBySql(@Param("sql") String sql);
	
	public abstract int countByWhereCondition(@Param("tablename") String tablename,@Param("criteriaManager") CriteriaManager criteriaManager);
}
