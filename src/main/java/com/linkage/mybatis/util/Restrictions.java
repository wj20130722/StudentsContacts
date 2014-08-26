package com.linkage.mybatis.util;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>Title:基于Mybatic框架sql常用的表达式</p>
 * <p>Description:该类主要封装了通过mybatis框架实现数据层操作时常用的sql表达式如=,>,<,>=,<=,<>.....</p>
 * <p>Company: linkage.com</p>
 * @author wangjie
 * @version 1.0
 * @Createtime 2013-6-17 14:17
 *
 */
public class Restrictions {
	
	/**
	 * 前缀%匹配 如:%abc
	 */
	public static final int PREFIX_LIKE_PERCENT = 1;
	
	/**
	 * 后缀%匹配 如：abc%
	 */
	public static final int SUFFIX_LIKE_PERCENT = 2;
	
	/**
	 * 前缀和后缀%匹配 如：%abc%
	 */
	public static final int PREFIXANDSUFFIX_LIKE_PERCENT = 3;
	
	/**
	 * 前缀_匹配 如:_abc
	 */
	public static final int PREFIX_LIKE_UNDERLINE = 4;
	
	/**
	 * 后缀_匹配 如:abc_
	 */
	public static final int SUFFIX_LIKE_UNDERLINE = 5;
	
	/**
	 * 前缀和后缀_匹配 如:_abc_
	 */
	public static final int PREFIXANDSUFFIX_LIKE_UNDERLINE = 6;
	
	/**
	 * 自定义的匹配类型
	 */
	public static final int LIKE_CUSTOM = 0;
	
	public static void valid(String propertyName)
	{
		if(propertyName==null || "".equals(propertyName.trim()))
			throw new RuntimeException("propertyName cannot be null");
	}
	
	public static void valid(String propertyName,Object value)
	{
		valid(propertyName);
		if(value==null)
			throw new RuntimeException("Value for " + propertyName + " cannot be null");
		else
		{
			//判断是多个参数的时候是否有值
			if(value instanceof List<?>)
			{
				if(((List<?>)value).size()==0)
					throw new RuntimeException("Value for " + propertyName + " cannot be null");
			}
			else
			{
				//判断一个参数的时候是否有值
				if("".equals(value.toString().trim()))
					throw new RuntimeException("Value for " + propertyName + " cannot be null");
			}
		}
	}
	
	public static void valid(String propertyName,Object value1,Object value2)
	{
		valid(propertyName);
		if((value1==null || "".equals(value1.toString().trim()))
				||(value2==null || "".equals(value2.toString().trim())))
			throw new RuntimeException("Between values for " + propertyName + " cannot be null");
	}
	
	/**
	 * 将模糊匹配的字段中含有特殊字符'%'或者'_'进行转义
	 * @param keyword
	 * @return
	 */
	public static String transfer(String keyword) 
	{
        if(keyword.contains("%") || keyword.contains("_"))
        { 
            keyword = keyword.replaceAll("\\\\", "\\\\\\\\") 
                             .replaceAll("\\%", "\\\\%") 
                             .replaceAll("\\_", "\\\\_");
        }
        return keyword;
    }
	
	public static Criterion isNull(String propertyName)
	{
		valid(propertyName);
		return new Criterion(propertyName+" is null");
	}
	
	public static Criterion isNotNull(String propertyName)
	{
		valid(propertyName);
		return new Criterion(propertyName+" is not null");
	}
	
	public static Criterion equalTo(String propertyName,Object value)
	{
		valid(propertyName,value);
		return new Criterion(propertyName+" =",value);
	}
	
	public static Criterion notEqualTo(String propertyName,Object value)
	{
		valid(propertyName,value);
		return new Criterion(propertyName+" <>",value);
	}
	
	public static Criterion gatherThan(String propertyName,Object value)
	{
		valid(propertyName,value);
		return new Criterion(propertyName+" >",value);
	}
	
	public static Criterion gatherThanOrEqualTo(String propertyName,Object value)
	{
		valid(propertyName,value);
		return new Criterion(propertyName+" >=",value);
	}
	
	public static Criterion lessThan(String propertyName,Object value)
	{
		valid(propertyName,value);
		return new Criterion(propertyName+" <",value);
	}
	
	public static Criterion lessThanOrEqualTo(String propertyName,Object value)
	{
		valid(propertyName,value);
		return new Criterion(propertyName+" <=",value);
	}
	
	public static Criterion in(String propertyName,List<?> values)
	{
		valid(propertyName,values);
		return new Criterion(propertyName+" in",values);
	}
	
	public static Criterion notIn(String propertyName,List<Object> values)
	{
		valid(propertyName,values);
		return new Criterion(propertyName+" not in",values);
	}
	
	public static Criterion between(String propertyName,Object value1,Object value2)
	{
		valid(propertyName,value1,value2);
		return new Criterion(propertyName+" between",value1,value2);
	}
	
	public static Criterion notBetween(String propertyName,Object value1,Object value2)
	{
		valid(propertyName,value1,value2);
		return new Criterion(propertyName+" not between",value1,value2);
	}
	
	/**
	 * like模糊匹配时忽略大小写的限制 如:lower('ABC') like ?(参数值已转成小写)
	 * @param propertyName
	 * @param value
	 * @param patternType
	 * @return
	 */
	public static Criterion likeIngoreCase(String propertyName,Object value,int patternType)
	{
		return likeHander(propertyName,value,patternType,true);
	}
	
	/**
	 * 模糊匹配需要自己定义匹配的表达式如：参数传入'%abc','abc%','%abc%','_abc','abc_'
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static Criterion like(String propertyName,Object value)
	{
		return like(propertyName,value,Restrictions.LIKE_CUSTOM);
	}
	
	/**
	 * 支持常用常用的模糊匹配
	 * @param propertyName 属性名	
	 * @param value 参数值
	 * @param patternType 匹配方式支持常用的匹配 1-6
	 * @return
	 */
	public static Criterion like(String propertyName,Object value,int patternType)
	{
		return likeHander(propertyName,value,patternType,false);
	}
	
	public static Criterion equalToIngoreCase(String propertyName,Object value)
	{
		return operatorIngoreCaseHander(propertyName, value, "=");
	}
	
	private static Criterion operatorIngoreCaseHander(String propertyName,Object value,String operator)
	{
		valid(propertyName,value);
		if(null==operator || "".equals(operator.trim()))
			throw new RuntimeException("Operator Value for "+propertyName+" can not be null");
		String condtion = "lower("+propertyName+")";
		if((value instanceof String) || (value instanceof Character))
			value = value.toString().toLowerCase();
		return new Criterion(condtion+" "+operator,value);
	}
	
	private static Criterion likeHander(String propertyName,Object value,int patternType,boolean ingoreCase)
	{
		valid(propertyName,value);
		String keyword = "";
		String condtion = propertyName;
		if(ingoreCase)
		{
			condtion = "lower("+condtion+")";
		}
		/**
		 * 数字类型直接匹配不需要加任何前后缀,会报错。此时相当于equal
		 */
		if((value instanceof Integer)||(value instanceof Double)||(value instanceof Float) 
				||(value instanceof Long)||(value instanceof Byte)||(value instanceof BigInteger))
		{
			patternType = 0;
		}
		else
		{
			/**
			 * 其他类型全部当作字符串类型处理
			 */
			keyword = value.toString();
			if(ingoreCase)
			{
				keyword = keyword.toLowerCase();
			}
		}
		switch(patternType)
		{
		case 1:keyword = "%"+transfer(keyword);break;
		case 2:keyword = transfer(keyword)+"%";break;
		case 3:keyword = "%"+transfer(keyword)+"%";break;
		case 4:keyword = "_"+transfer(keyword);break;
		case 5:keyword = transfer(keyword)+"_";break;
		case 6:keyword = "_"+transfer(keyword)+"_";break;
		}
		if(patternType == 0)
			return new Criterion(condtion+" like",value);
		else
			return new Criterion(condtion+" like",keyword);
	}

}
