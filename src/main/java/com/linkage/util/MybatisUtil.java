package com.linkage.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MybatisUtil
{
	public static int intValue(Integer value)
	{
		return value.intValue();
	}
	
	/**
	 * 获取CommonMapper中行记录对应的字段值.由于oracle或一些数据的字段转换为大写，需要注意兼容处理。
	 * 
	 * @param row 行记录map
	 * @param field 字段名称
	 * @return
	 */
	public static Object getCommonMapperValue(HashMap<String, Object> row, String field)
  {
		if(row.containsKey(field))
			return row.get(field);
		else if(row.containsKey(field.toUpperCase()))
			return row.get(field.toUpperCase());
		return null;
  }
	
	public static HashMap<String, Object> converColumnNull(HashMap<String, Object> map,String[] columns)
	{
		if(null == map)
			map = new HashMap<String, Object>();
		for(int i=0;i<columns.length;i++)
		{
			if(!map.containsKey(columns[i]))
			{
				map.put(columns[i], null);
			}
		}
		return map;
	}
	
	public static List<HashMap<String, Object>> converColumnNullList(List<HashMap<String, Object>> maps,String[] columns)
	{
		if(null == maps)
			maps = new ArrayList<HashMap<String, Object>>();
		for(int i=0;i<maps.size();i++)
		{
			converColumnNull(maps.get(i),columns);
		}
		return maps;
	}
	
	public static void main(String[] args)
  {
	  System.out.println(converColumnNull(null, new String[]{"user_name","avatar_large","city_name","industry","companyname"}));
  }
	
}
