package com.linkage.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.linkage.core.ApplicationObject;
import com.linkage.util.Utility;

public class CacheObject
{

	private static final Log log = LogFactory.getLog(CacheObject.class);

	public final static String CACHECONFIG_CACHENAME = "CACHECONFIG_CACHENAME";// 缓存名称（用于缓存配置信息）
	// ,其他缓存不能使用这个缓存
	public final static String CACHECONFIG_CACHEELEMENTNAME = "CACHECONFIG_CACHEELEMENTNAME";// 缓存名称（用于缓存配置信息）
	// ,其他缓存不能使用这个缓存

	protected final static CacheManager cacheManager = new CacheManager();

	/**
	 * 获取缓存的值
	 * 
	 * @param conn
	 * @param cacheCode
	 * @param cek
	 * @return
	 */
	protected Object getCachedValue(Connection conn, String cacheCode, CacheElementKey cek)
	{
		// 从cacheconfig表中获取cacheName，
		String cacheName = getCacheNameByCode(conn, cacheCode);
		// 如果cacheName为空，则不进行缓存
		if (null != cacheName && cacheName.trim().length() > 0 && cacheManager.cacheExists(cacheName))
		{
			
			Element e = cacheManager.getCache(cacheName).get(cek);
			if (null != e)
			{
				log.debug("从缓存" + cacheName + "中获取到" + cek + "的值。");
				return e.getObjectValue();
			}
		}
		return null;
	}

	/**
	 * 根据缓存编码，从配置表中获取使用的缓存名称
	 * 
	 * @param conn
	 * @param cacheCode
	 * @return
	 */
	protected String getCacheNameByCode(Connection conn, String cacheCode)
	{
		// 从缓存中获取
		if (!cacheManager.cacheExists(CACHECONFIG_CACHENAME))
		{
			cacheManager.addCache(CACHECONFIG_CACHENAME);
		}
//		Element e = cacheManager.getCache(CACHECONFIG_CACHENAME).get(CACHECONFIG_CACHEELEMENTNAME);
		return null;
	}

	/**
	 * 设置缓存的值
	 * 
	 * @param conn
	 * @param cacheCode
	 * @param elementName
	 * @param value
	 */
	protected void setCachedValue(Connection conn, String cacheCode, CacheElementKey cek, Object value)
	{
		// 从cacheconfig表中获取cacheName，
		String cacheName = getCacheNameByCode(conn, cacheCode);
		// 如果cacheName为空，则不进行缓存
		if (null != cacheName && cacheName.trim().length() > 0)
		{
			if (cacheManager.cacheExists(cacheName))
				cacheManager.getCache(cacheName).put(new Element(cek, value));
			else
			{
				// 创建
				cacheManager.addCache(cacheName);
				cacheManager.getCache(cacheName).put(new Element(cek, value));
			}
			log.debug("将" + cek + "的值放入缓存" + cacheName + "中。");
		}
	}

	/**
	 * 清除缓存
	 * 
	 * @param conn
	 * @param thecachecode
	 */
	public void cleanCache(Connection conn, String cacheCode)
	{
		if (ApplicationObject.isCacheable())
		{
			// 从cacheconfig表中获取cacheName，
			String cacheName = getCacheNameByCode(conn, cacheCode);
			// 如果cacheName不为空,清除缓存内容
			if (null != cacheName && cacheName.trim().length() > 0)
			{
				if (cacheManager.cacheExists(cacheName))
				{
					// 清楚缓存
					cacheManager.getCache(cacheName).removeAll();
				}
			}
		}
	}

	/**
	 * 生成数据行集
	 * <p>
	 * 有缓存，则从缓存中读取；否则读取原sql数据，并放入缓存
	 * 
	 * @param conn
	 * @param cacheCode
	 * @param sql
	 * @param fields
	 * @param cacheable
	 *            是否启用cache
	 * @return
	 */
	public RowSet generateRowSet(Connection conn, String cacheCode, String sql, String fields, boolean cacheable)
	{

		if (null == sql || sql.trim().length() == 0)
			return null;
		CacheElementKey cek = new CacheElementKey(sql,fields);
		if (cacheable && ApplicationObject.isCacheable())
		{
			// 从缓存中获取
			
			java.lang.Object cacheObject = getCachedValue(conn, cacheCode, cek);
			if (null != cacheObject)
			{
				if (cacheObject instanceof RowSet)
				{
					RowSet rs = (RowSet) cacheObject;
					rs.reread();
					return (RowSet) cacheObject;
				}
			}
		}

		Statement stmt = null;
		ResultSet rs = null;
		RowSet rowset = null;
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			// 没有指定的fields则从数据库元数据中获取
			String[] arrf;
			if (null == fields || fields.trim().length() == 0)
			{
				ResultSetMetaData rsmd = rs.getMetaData();
				arrf = new String[rsmd.getColumnCount()];
				for (int i = 0; i < rsmd.getColumnCount(); i++)
				{
					arrf[i] = rsmd.getColumnLabel(i + 1);
				}
			}
			else{
				arrf = Utility.split(fields, ',');
			}
			rowset = new RowSet(arrf);
			rowset.setSql(sql);
			while (rs.next())
			{
				rowset.addRow(rs);
			}
		}
		/**
		 * CPMIS-1125 添加错误信息输出，辅助查找错误
		 */
		catch (SQLException e)
		{
			log.error("从数据库获取数据到缓存中报错，conn="+conn+",stmt="+stmt+",rs="+rs, e);
			throw new RuntimeException("SQL 语句错误，请检查SQL = \n" + sql,e);
		}
		catch (RuntimeException e)
		{
			log.error("从数据库获取数据到缓存中报错，conn="+conn+",stmt="+stmt+",rs="+rs, e);
			throw e;
		}
		finally
		{
			try
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			}
			catch (Exception e)
			{
				log.error("SQL finally处理异常", e);
			}
		}
		if (cacheable && ApplicationObject.isCacheable())
		{
			// 增加到缓存中
			setCachedValue(conn, cacheCode, cek, rowset);
		}
		return rowset;

	}

	public static CacheManager getCacheManager()
	{
		return cacheManager;
	}

	public RowSet generateRowSet(Connection conn, String cacheCode, String sql, String fields)
	{
		return this.generateRowSet(conn, cacheCode, sql, fields, true);
	}

	public RowSet generateRowSet(Connection conn, String cacheCode, String sql)
    {
	    return this.generateRowSet(conn, cacheCode, sql, null);
    }

}
