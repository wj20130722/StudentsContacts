package com.linkage.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.linkage.core.ApplicationObject;
import com.linkage.util.Utility;

public class RowSet
{

	private static final Log log = LogFactory.getLog(RowSet.class);
	private List rows = new ArrayList();// 用于存放数据行，每一个行的数据以map方式存储
	private int next = 0;// 下一行
	private String[] fields;// 字段数组，可以实现行数据中对字段的索引，从1开始
	private Map fieldMap;// 字段对应的索引map
	
	private String sql;

	public RowSet()
	{
	}

	public RowSet(String[] arrf)
	{
		this.fields = arrf;
		fieldMap = new HashMap(this.fields.length);
		for (int i = 0; i < this.fields.length; i++)
		{
			fieldMap.put(formatField(this.fields[i]), Integer.toString(i + 1));
		}
	}

	public boolean next()
	{
		if ((next++) < rows.size())
			return true;
		return false;
	}

	public void addRow(Map map)
	{
		this.rows.add(map);
	}

	/**
	 * 重新读取，设置下一行为0
	 */
	public void reread()
	{
		this.next = 0;
	}

	private String formatField(String field)
	{
		// oracle数据库，field变成大写
		if (null != field && "oracle".equals(ApplicationObject.dbDriverType))
			return field.toUpperCase();
		return field;
	}

	public Object getObject(int index)
	{
		return getIndexObject(index);
	}

	private Object getIndexObject(int index)
	{
		return ((Map) rows.get(next - 1)).get(Integer.toString(index - 1));
	}

	public Object getObject(String field)
	{
		String index = (String) this.fieldMap.get(formatField(field));
		if (null == index)
		{
			log.warn("缺少字段"+field+",对应sql="+sql);
			return null;
		}
		else
			return getIndexObject(Integer.parseInt(index));
	}

	public String getString(String field)
	{
		// 先找到field对应的字段索引
		return (String) getObject(field);
	}
	
	public String getString(int index)
	{
		// String field = this.fields[index-1];
		// return getString(field);

		return (String) getIndexObject(index);
	}
	
	/**
	 * 获取时间格式的字符串，默认标准格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param field
	 * @return
	 */
	public String getTimeString(String field)
	{
		// 先找到field对应的字段索引
		return Utility.formatTimeString((String) getObject(field));
	}

	/**
	 * 获取时间格式的字符串，默认标准格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param index
	 * @return
	 */
	public String getTimeString(int index)
	{
		// String field = this.fields[index-1];
		// return getString(field);

		return Utility.formatTimeString((String) getIndexObject(index));
	}

	public int getInt(String field)
	{
		Object o = getObject(field);
		if (null == o)
			return 0;
		else
			return Integer.parseInt((String) o);
	}

	public int getInt(int index)
	{
		Object o = getIndexObject(index);
		if (null == o)
			return 0;
		else
			return Integer.parseInt((String) o);
		// String field = this.fields[index - 1];
		// return getInt(field);
	}

	public void addRow(ResultSet rs) throws SQLException
	{
		Map map = new HashMap(fields.length);
		for (int i = 0; i < fields.length; i++)
		{
			// //field[i]有可能是空，当这样的sql出现时：select 1,'是' from dual union select
			// 0,'否' from dual
			/*
			 * if (null != fields[i] && fields[i].trim().length() > 0) { String
			 * field = fields[i].trim(); map.put(formatField(field),
			 * rs.getString(field)); }
			 */
			// 改用索引，而非字段名
			map.put(Integer.toString(i), rs.getString(i + 1));
		}
		addRow(map);
	}

	public String getSql()
    {
    	return sql;
    }

	public void setSql(String sql)
    {
    	this.sql = sql;
    }

	public String[] getFields()
    {
    	return fields;
    }

	public Map getFieldMap()
    {
    	return fieldMap;
    }
}
