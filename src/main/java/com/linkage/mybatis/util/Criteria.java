package com.linkage.mybatis.util;

/**
 * <p>Title:基于Mybatic框架查询的封装主要是基于目前主流数据库的封装支持oracle,mysql,sybase....</p>
 * <p>Description:该类主要封装了通过mybatis框架实现数据层操作时查询条件的主要功能操作,不同的数据库可能有不同的实现,只要实现该接口</p>
 * <p>Company: xwtech.com</p>
 * @author wangjie
 * @version 1.0
 * @Createtime 2013-6-17 14:17
 *
 */
public interface Criteria {
	/**
	 * 增加一个查询条件
	 * @param criterion
	 * @return
	 */
	public Criteria add(Criterion criterion);
	
	/**
	 * 删除一个查询条件
	 * @param criterion
	 * @return
	 */
	public Criteria remove(Criterion criterion);
	
	/**
	 * 删除所有的查询条件
	 * @return
	 */
	public Criteria clear();
}
