package com.linkage.mybatis.util;

import java.util.ArrayList;
import java.util.List;



/**
 * <p>Title:基于Mybatic框架查询的封装主要是基于目前主流数据库的封装支持oracle,mysql,sybase....</p>
 * <p>Description:该类主要封装了通过mybatis框架实现数据层操作时查询条件的主要功能操作</p>
 * <p>Company: linkage.com</p>
 * @author wangjie
 * @version 1.0
 * @Createtime 2013-6-17 14:17
 *
 */
public class CriteriaImpl implements Criteria {
	/**
	 * 一组查询条件的集合
	 */
	protected List<Criterion> criteria;
	
	public List<Criterion> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<Criterion> criteria) {
		this.criteria = criteria;
	}
	
	public CriteriaImpl()
	{
		super();
		criteria = new ArrayList<Criterion>();
	}

	/**
	 * 判断是否含有查询条件
	 * @return
	 */
	public boolean isValid() {
        return criteria.size() > 0;
    }
	
	 public List<Criterion> getAllCriteria() {
         return criteria;
     }
	 
	 /**
	  * 增加一个查询条件
	  */
	@Override
	public Criteria add(Criterion criterion) {
		this.criteria.add(criterion);
		return this;
	}
	
	/**
	 * 删除一个查询条件
	 */
	@Override
	public Criteria remove(Criterion criterion) {
		this.criteria.remove(criterion);
		return this;
	}

	/**
	 * 清空所以的查询条件
	 */
	@Override
	public Criteria clear() {
		this.criteria.clear();
		return this;
	}

}
