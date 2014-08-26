package com.linkage.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存元素关键字，key 由sql和要获取的字段内容组成
 * 
 * @author dht
 * 
 */
public class CacheElementKey implements Serializable
{

	private String sql;
	private String fields;

	public CacheElementKey(String sql, String fields)
	{
		this.sql = sql;
		this.fields = fields;
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}

	public String getFields()
	{
		return fields;
	}

	public void setFields(String fields)
	{
		this.fields = fields;
	}

	public String toString()
	{
		return "[sql]=" + this.sql + ",[fields]=" + fields;
	}

	public boolean equals1(Object obj)
	{
		if (obj instanceof CacheElementKey)
		{
			CacheElementKey c = (CacheElementKey) obj;
			boolean bSql = false;
			if (null == this.sql)
			{
				if (null == c.sql)
					bSql = true;
			}
			else
			{
				bSql = this.sql.equals(c.sql);
			}

			boolean bFields = false;
			if (null == this.fields)
			{
				if (null == c.fields)
					bFields = true;
			}
			else
			{
				bFields = this.fields.equals(c.fields);
			}
			return bSql && bFields;
		}
		return super.equals(obj);
	}

	public boolean equals(Object other)
	{
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CacheElementKey))
			return false;
		CacheElementKey castOther = (CacheElementKey) other;

		return ((this.getSql() == castOther.getSql()) || (this.getSql() != null && castOther.getSql() != null && this.getSql().equals(castOther.getSql())))
		       && ((this.getFields() == castOther.getFields()) || (this.getFields() != null && castOther.getFields() != null && this.getFields().equals(castOther.getFields())));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result + (getSql() == null ? 0 : this.getSql().hashCode());
		result = 37 * result + (getFields() == null ? 0 : this.getFields().hashCode());
		return result;
	}

	public static void main(String[] s)
	{
		CacheElementKey c1 = new CacheElementKey("sql", "fields");

		CacheElementKey c2 = new CacheElementKey("sql", "fields");

		System.out.println(c1.equals(c2));
		
		System.out.println(c1.hashCode());
		System.out.println(c2.hashCode());

		Map m = new HashMap();
		m.put(c1, "1");
		System.out.println(m.get(c1));
		System.out.println(m.get(c2));

		String s1 = "ssq";
		String s2 = "ssq";
		m.put(s1, "1");
		System.out.println(m.get(s1));
		System.out.println(m.get(s2));

	}

}
