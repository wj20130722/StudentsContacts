package com.linkage.mybatis.util;
/**
 * <p>Title:基于Mybatic框架查询生成的工厂类</p>
 * <p>Description:查询生成的工厂类</p>
 * <p>Company: linkage.com</p>
 * @author wangjie
 * @version 1.0
 * @Createtime 2013-6-17 14:17
 *
 */
public class CriteriaFactory {
	
	public CriteriaFactory()
	{
		
	}
	
	/**
	 * 查询生成的工厂类的方法,不同数据库可生成不同的bean,默认支持oracle
	 * @param databaseType
	 * @return
	 */
	public static Criteria getCriteriaBean(String databaseType)
	{
		if(null==databaseType || "".equals(databaseType.trim()))
			throw new RuntimeException("数据库类型不能为空");
		Criteria criteria = null;
		if("oracle".equals(databaseType))
			criteria = new CriteriaImpl();
		else
			criteria = new CriteriaImpl();
		return criteria;
	}
}
