package com.linkage.mybatis.util;

import java.util.ArrayList;
import java.util.List;




/**
 * <p>Title:Mybatis的数据查询和操作功能的辅助类</p>
 * <p>Description:该类主要封装了通过mybatis框架实现数据层操作时复杂的过滤条件的功能,简化了手动拼写查询和过滤条件所带来的麻烦。</p>
 * <p>Company: linkage.com</p>
 * @author wangjie
 * @version 1.0
 * @Createtime 2013-6-16 21:01
 *
 */
public class CriteriaManager {
	/**
	 * 数据库类型
	 */
	public static final String DATABASE_TYPE = "sybase";
	
	/**
	 * 筛选方式：1.(condtion1 and contion2) or (condtion3 and contion4)
	 */
	public static final int WHERE_OR_AND = 1;
	
	/**
	 * 筛选方式：2.(condtion1 or contion2) and (condtion3 or contion4)
	 */
	public static final int WHERE_AND_OR = 2;
	
	/**
	 * 排序字段
	 */
	protected String orderByClause;
	
	/**
	 * 是否使用distinct字段
	 */
	protected boolean distinct;
	
	protected int whereType = WHERE_OR_AND;
	
	/**
	 * 一组查询,查询之间以or分割如：(a=1 and b=2) or (c between 1 and 2)
	 */
	protected List<Criteria> oredCriteria;
	
	public CriteriaManager()
	{
		oredCriteria = new ArrayList<Criteria>();
	}

	public int getWhereType()
	{
		return whereType;
	}

	public void setWhereType(int whereType)
	{
		this.whereType = whereType;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void setOredCriteria(List<Criteria> oredCriteria) {
		this.oredCriteria = oredCriteria;
	}
	
	/**
	 * 创建单一的一个查询(一组查询条件的集合)
	 * @return
	 */
	public Criteria createCriteria()
	{
		Criteria criteria = CriteriaFactory.getCriteriaBean(DATABASE_TYPE);
		if(oredCriteria.size()==0)
		{
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	/**
	 * 创建一个查询(一组查询条件的集合)
	 * @return
	 */
	public Criteria or()
	{
		this.whereType = WHERE_OR_AND;
		Criteria criteria = CriteriaFactory.getCriteriaBean(DATABASE_TYPE);
		oredCriteria.add(criteria);
		return criteria;
	}
	
	public Criteria or(Criteria criteria)
	{
		this.whereType = WHERE_OR_AND;
		oredCriteria.add(criteria);
		return criteria;
	}
	
	public Criteria and()
	{
		this.whereType = WHERE_AND_OR;
		Criteria criteria = CriteriaFactory.getCriteriaBean(DATABASE_TYPE);
		oredCriteria.add(criteria);
		return criteria;
	}
	
	public Criteria and(Criteria criteria)
	{
		this.whereType = WHERE_AND_OR;
		oredCriteria.add(criteria);
		return criteria;
	}
	
	
	public void clear()
	{
		oredCriteria.clear();
		distinct = false;
		orderByClause = null;
	}

}
