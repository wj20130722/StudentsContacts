package com.linkage.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * ClassName : Utility Description : 通用函数的类
 * 
 */
public class Utility
{
	private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Utility.class);

	static int i = 1;

	/**
	 * 将字符串转化为sql字符串格式，比如 SDFG 转化后变成了 'SDFG',null，空字符串则变成null，a'd变成'a''d'
	 * 
	 * @param sql
	 *          待转义的sql语句
	 * @return 转义后的sql语句
	 */
	public static final String convertSQLString(String str)
	{
		if (StringUtils.isBlank(str))
			return null;
		else
			return "'" + org.apache.commons.lang3.StringUtils.replace(str.trim(), "'", "''") + "'";
	}

	/**
	 * 将字符串转化为sql可入库的日期格式，区分不同数据库 oracle to_date('2012-09-12 20:04:36','yyyy-mm-dd
	 * HH24:MI:SS') sybase convert(datetime,zgordertime)
	 * 
	 * @param sql
	 *          待转义的sql语句
	 * @return 转义后的sql语句
	 */
	/*
	 * public static final String convertSQLTime(String str) { if
	 * (StringUtils.isBlank(str)) return null; else if
	 * ("oracle".equalsIgnoreCase(ApplicationObject.dbDriverType)) return
	 * "to_date('" + StringUtils.replace(str.trim().substring(0, 19), "'", "''") +
	 * "','yyyy-mm-dd HH24:MI:SS')"; else return "convert(datetime,'" +
	 * StringUtils.replace(str.trim(), "'", "''") + "')"; }
	 */

	/**
	 * 将时间转换成yyyy-mm-dd HH:MM:SS的标准格式
	 * 
	 * @param str
	 * @return
	 */
	public static final String formatTimeString(String str)
	{
		if (StringUtils.isBlank(str))
			return null;
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try
			{
				return sdf.format(sdf.parse(str));
			}
			catch (ParseException e)
			{
				return str;
			}
		}
	}

	public static final String formatTimeString(long timestamp)
	{
		Date date = new Date(timestamp);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static void main(String[] args)
  {
	  System.out.println(formatTimeString(System.currentTimeMillis()));
  }
	

	public static String formatDateAndTime(String timestr)
	{
		if (null == timestr)
			return "";
		String result = "";
		try
		{
			if (timestr.length() >= 8)
			{
				result = timestr.substring(0, 2) + "" + timestr.substring(2, 4) + "-" + timestr.substring(4, 6) + "-" + timestr.substring(6, 8) + " ";
			}
			if (timestr.length() == 12)
			{
				result = result + timestr.substring(8, 10) + ":" + timestr.substring(10, 12);
			}
			if (timestr.length() == 14)
			{
				result = result + timestr.substring(8, 10) + ":" + timestr.substring(10, 12) + ":" + timestr.substring(12, 14);
			}
		}
		catch (Exception e)
		{
			log.error("", e);
		}
		return result;
	}

	public static String formatDateAndFTime(String timestr)
	{
		if (null == timestr)
			return "";
		String result = "";
		try
		{
			if (timestr.length() >= 8)
			{
				result = timestr.substring(2, 4) + "-" + timestr.substring(4, 6) + "-" + timestr.substring(6, 8) + " ";
			}
			if (timestr.length() == 12)
			{
				result = result + timestr.substring(8, 10) + ":" + timestr.substring(10, 12);
			}
			if (timestr.length() == 14)
			{
				result = result + timestr.substring(8, 10) + ":" + timestr.substring(10, 12);
			}
		}
		catch (Exception e)
		{
			log.error("", e);
		}
		return result;
	}

	/**
	 * 类的方法定义: split(); 方法的说明:按@param2字符把@param1分割成字符串数组
	 * 
	 * @param source
	 *          要分割的字符串
	 * @param c
	 *          分割字符
	 * @return String[];
	 */
	public static String[] split(String source, char c)
	{
		String[] result = null;
		String temp = "";
		Vector v = new Vector();
		if (source == null)
			return result;
		for (int i = 0; i < source.length(); i++)
		{
			if (source.charAt(i) == c)
			{
				v.addElement(temp);
				temp = "";
			}
			else
				temp += source.charAt(i);
		}
		v.addElement(temp);
		result = new String[v.size()];
		for (int i = 0; i < v.size(); i++)
			result[i] = v.elementAt(i).toString();
		return result;
	}

	/**
	 * 类的方法定义: chgNull(); 方法的说明: 将 String 对象转换 如果对象为Null转换为""字符串
	 * 
	 * @param source
	 *          被处理的字符串
	 * @return 处理后的字符串
	 */
	public static String chgNull(Object source)
	{
		String result = "";
		if (source != null)
			result = source.toString();
		return result;
	}

	public static String chgNull(String source)
	{
		String result = "";
		if (source != null)
			result = source.toString().trim();
		return result;
	}

	/**
	 * 类的方法定义:toNull(); 方法的说明: 将 String 对象转换 如果String长度为0 转换为null
	 * 
	 * @param source
	 *          被处理的字符串
	 * @return 处理后的字符串
	 */
	public static String toNull(String source)
	{
		if (source != null)
			if (source.length() == 0)
				source = null;
		return source;
	}

	/**
	 * sql语句转义为String
	 * 
	 * @param sql
	 *          待转义的sql语句
	 * @return 转义后的sql语句
	 */
	public static final String sqlToString(String sql)
	{
		String targetString = "";
		if (sql != null)
			targetString = Utility.replaceAll(sql, "'", "''");
		return targetString;
	}

	/**
	 * 将字符串中的字符串替换为另一个字符串
	 * 
	 * @param sourceString
	 *          源字符串
	 * @param replaceString
	 *          被替换的字符串
	 * @param newString
	 *          替换的新字符串
	 * @return 替换后的字符串
	 */
	public static final String replaceAll(String sourceString, String replaceString, String newString)
	{
		StringBuffer resultString = new StringBuffer();
		for (int i = 0; i < sourceString.length(); i++)
		{
			int j;
			for (j = 0; j < replaceString.length(); j++)
			{
				if (i + j > sourceString.length())
					break;
				if (sourceString.charAt(i + j) != replaceString.charAt(j))
					break;
			}
			if (j == replaceString.length())
			{
				i = i + j - 1;
				resultString.append(newString);
			}
			else
				resultString.append(sourceString.charAt(i));
		}
		return resultString.toString();
	}

	/**
	 * 类的方法定义: chgZero(); 方法的说明: 将 String 对象转换 如果对象为Null转换为"0"字符串,用于数字
	 * 
	 * @param source
	 *          被处理的字符串
	 * @return 处理后的字符串
	 */
	public static String chgZero(Object source)
	{
		String result = "0";
		if (chgNull(source).length() > 0)
			result = source.toString();
		return result;
	}

	/**
	 * get current date eg."20021205"
	 * 
	 * @return
	 */
	public static String getEightCurDate()
	{
		String month = "00", day = "00";
		Calendar cal = Calendar.getInstance();
		String str = "";
		int n = 0;
		str += cal.get(Calendar.YEAR);
		n = cal.get(Calendar.MONTH) + 1;
		month += n;
		str += month.substring(month.length() - 2);
		day += cal.get(Calendar.DAY_OF_MONTH);
		str += day.substring(day.length() - 2);
		return str;
	}

	public static String getResultOfSQL(Connection conn, String sql)
	{
		Statement stmt = null;
		ResultSet rs = null;
		String result = null;
		try
		{
			rs = conn.createStatement().executeQuery(sql);
			if (rs.next())
				result = (rs.getString(1));
		}
		catch (Exception ex)
		{
			log.error("", ex);
		}
		finally
		{
			try
			{
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			}
			catch (SQLException e)
			{
				log.error("", e);
			}
		}
		return result;
	}

	public static String getEightCurTime()
	{
		Calendar cal = Calendar.getInstance();
		String hh = "00", mm = "00", ss = "00";
		hh = hh + cal.get(Calendar.HOUR_OF_DAY);
		mm = mm + cal.get(Calendar.MINUTE);
		ss = ss + cal.get(Calendar.SECOND);
		hh = hh.substring(hh.length() - 2, hh.length());
		mm = mm.substring(mm.length() - 2, mm.length());
		ss = ss.substring(ss.length() - 2, ss.length());
		return hh + mm + ss;
	}

	public static String[] getnum()
	{
		int year = Integer.parseInt(Utility.getEightCurDate().substring(0, 4));
		String[] ss = new String[]
		{};
		int beginyear = year;
		int endyear = year + 1;
		Calendar c_begin = new GregorianCalendar();
		Calendar c_end = new GregorianCalendar();
		// DateFormatSymbols dfs = new DateFormatSymbols();
		// String[] weeks = dfs.getWeekdays();// 周一到周日
		// int year = c_begin.get(Calendar.YEAR);
		c_begin.set(beginyear, 0, 1); // Calendar的月从0-11，所以4月是3. 2013-01-01
		c_end.set(endyear, 0, 1);

		int count = 1; // 周
		int month = -1; // 获取月份，0表示1月份
		int day = -1; // 获取当前天数
		while (c_begin.before(c_end))
		{
			if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY || (count == 1 && c_begin.get(Calendar.MONTH) == 0 && c_begin.get(Calendar.DAY_OF_MONTH) == 1))
			{
				month = c_begin.get(Calendar.MONTH) + 1; // 获取月份，0表示1月份
				day = c_begin.get(Calendar.DAY_OF_MONTH); // 获取当前天数
			}
			if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			{
				int q = c_begin.get(Calendar.MONTH) + 1; // 获取月份，0表示1月份
				int a = c_begin.get(Calendar.DAY_OF_MONTH); // 获取当前天数
				// + "月" + day + "日~" + q + "月" + a + "日");
				String s = beginyear + "年第" + count + "周  " + month + "月" + day + "日~" + q + "月" + a + "日";
				ss = (String[]) Arrays.copyOf(ss, ss.length + 1);
				ss[ss.length - 1] = s;

				count++;
				month = -1;
				day = -1;
			}

			c_begin.add(Calendar.DAY_OF_YEAR, 1);// 开始时间加上1天
		}
		if (c_begin.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
		{// 来年的1月1号是周四
			int xx = c_begin.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY - 1;
			c_begin.add(Calendar.DAY_OF_YEAR, (-1) * xx);
			month = c_begin.get(Calendar.MONTH) + 1;
			day = c_begin.get(Calendar.DAY_OF_MONTH);
			String s = beginyear + "年第" + count + "周  " + month + "月" + day + "日~" + 12 + "月" + 31 + "日";
			ss = (String[]) Arrays.copyOf(ss, ss.length + 1);
			ss[ss.length - 1] = s;
			count++;
		}
		return ss;
	}

	public static double getFormatedDouble(double dbl)
	{
		double result = 0.00;
		try
		{
			DecimalFormat df = new DecimalFormat("##0.00"); // 保留两位小数点
			result = new Double(df.format(dbl)).doubleValue();
		}
		catch (Exception e)
		{
			result = dbl;
		}
		return result;
	}

	// 格式化长浮点数，避免出现科学计数的表示方法
	public static String getFormatedFloat(double dbl)
	{
		try
		{
			java.text.DecimalFormat df = new java.text.DecimalFormat("##0.00");
			String str = df.format(dbl);
			return str;
		}
		catch (Exception e)
		{
			try
			{
				return new Double(dbl).toString();
			}
			catch (Exception se)
			{
				return "";
			}
		}
	}

	// 格式化长浮点数，避免出现科学计数的表示方法
	public static String getFormatedFloat(float dbl)
	{
		try
		{
			java.text.DecimalFormat df = new java.text.DecimalFormat("##0.00");
			String str = df.format(dbl);
			return str;
		}
		catch (Exception e)
		{
			try
			{
				return new Double(dbl).toString();
			}
			catch (Exception se)
			{
				return "";
			}
		}
	}

	/**
	 * 
	 * @param dbl
	 * @param type
	 *          :保留小数点位数
	 * @return
	 */
	public static String getFormatedFloat(double dbl, int type)
	{
		try
		{
			String decimal = "##0.00";
			switch (type)
			{
			case 0:
				decimal = "##0";
				break;
			case 1:
				decimal = "##0.0";
				break;
			case 2:
				decimal = "##0.00";
				break;
			case 3:
				decimal = "##0.000";
				break;
			case 4:
				decimal = "##0.0000";
				break;
			case 6:
				decimal = "##0.000000";
				break;
			case 7:
				decimal = "##0.0000000";
				break;
			}
			java.text.DecimalFormat df = new java.text.DecimalFormat(decimal);
			String str = df.format(dbl);
			return str;
		}
		catch (Exception e)
		{
			try
			{
				return new Double(dbl).toString();
			}
			catch (Exception se)
			{
				return "";
			}
		}
	}

	public static String subStringCN(final String str, final int maxLength)
	{

		if (str == null)
		{
			return str;
		}
		String suffix = "...";
		int suffixLen = suffix.length();
		final StringBuffer sbuffer = new StringBuffer();
		final char[] chr = str.trim().toCharArray();
		int len = 0;
		for (int i = 0; i < chr.length; i++)
		{
			if (chr[i] >= 0xa1)
			{
				len += 2;
			}
			else
			{
				len++;
			}
		}

		if (len <= maxLength)
		{
			return str;
		}
		len = 0;
		for (int i = 0; i < chr.length; i++)
		{
			if (chr[i] >= 0xa1)
			{
				len += 2;
				if (len + suffixLen > maxLength)
				{
					break;
				}
				else
				{
					sbuffer.append(chr[i]);
				}
			}
			else
			{
				len++;
				if (len + suffixLen > maxLength)
				{
					break;
				}
				else
				{
					sbuffer.append(chr[i]);
				}
			}
		}
		sbuffer.append(suffix);
		return sbuffer.toString();
	}

	/**
	 * 转化月份以及日期为 03 , 22 格式
	 * 
	 * @param str
	 * @return
	 */
	public static String subStr(String str)
	{
		String m = "00";
		str = m + str;
		str = str.substring(str.length() - 2, str.length());
		return str;
	}

	/**
	 * 
	 * 获取事项编号--流水号 （日期+2位流水号）
	 * 
	 * @return
	 */
	public static String getWpitemCode(String result)
	{
		if (StringUtils.isBlank(result))
		{
			String date = getEightCurDate();
			return date + "01";
		}
		else
		{
			return (Integer.parseInt(result) + 1) + "";
		}
	}

	/**
	 * <title>对通知日期的处理</title>
	 * <p>
	 * 1.当天日期只显示时间 09:30 2.昨天只显示昨天 3.其他显示日期和时间 2013-08-30
	 * </p>
	 * 
	 * @param datetime
	 *          通知日期
	 * @return
	 */
	public static String formaterDatetime(String datetime)
	{
		if (!"".equals(Utility.chgNull(datetime)) && datetime != null && !"NULL".equals(datetime))
		{
			String datetimes = datetime.substring(0, 10);
			DateBean datebean = new DateBean();
			String nowdate = datebean.getYearMonthDay();// 2013-08-30
			String yesterdaydate = datebean.getYesterdayDate("-"); // 2013-08-29
			if (datetimes.equals(nowdate))
				return datetime.substring(11, 16);
			else if (datetimes.equals(yesterdaydate))
				return "昨天";
			else
				return datetime.substring(0, 10);
		}
		else
		{
			return "NULL";
		}
	}

	public static String getNodeText(Element element, String nodeName) throws Exception
	{
		String result = "";
		if (null == nodeName || 0 == nodeName.trim().length())
			throw new Exception("节点名称不能为空！");
		NodeList nodelist = element.getElementsByTagName(nodeName);
		if (0 == nodelist.getLength())
			throw new Exception("没有找到" + nodeName + "节点");
		if (null == nodelist.item(0).getFirstChild())
			result = "";
		else
			result = nodelist.item(0).getFirstChild().getNodeValue();
		return result;
	}

	// 判断字符串是否是数字
	public static boolean isNumeric(String str)
	{
		for (int i = str.length(); --i >= 0;)
		{
			if (!Character.isDigit(str.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}

	// 正则表达式判断字符串是否是数字
	public static boolean isNumeric2(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

}
