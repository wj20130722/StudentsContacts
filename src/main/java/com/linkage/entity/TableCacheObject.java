package com.linkage.entity;

import java.sql.Connection;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.linkage.core.ApplicationObject;

/**
 * 分表做缓存，利用cachecode作为表名
 * 
 * @author dht
 * 
 */
public class TableCacheObject extends CacheObject
{
	private static final Log log = LogFactory.getLog(TableCacheObject.class);

	/**
	 * 获取缓存的值
	 * 
	 * @param conn
	 * @param cacheCode
	 * @param elementName
	 * @return
	 */
	protected Object getCachedValue(Connection conn, String cacheCode, CacheElementKey cek)
	{
		Cache cache = getCache(conn, cacheCode);
		if (null != cache)
		{
			Element e = cache.get(cek);
			if (null != e)
			{
				log.debug("从缓存" + cacheCode + "中获取到" + cek + "的值。");
				return e.getObjectValue();
			}
		}
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
		Cache cache = getCache(conn, cacheCode);
		if (null != cache)
		{
			cache.put(new Element(cek, value));
			log.debug("将" + cek + "的值放入缓存" + cacheCode + "中。");
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
			Cache cache = getCache(conn, cacheCode);
			if (null == cache)
			{
				String[] cacheNames = cacheManager.getCacheNames();
				for (int i = 0; i < cacheNames.length; i++)
				{
					if (cacheCode.equals(cacheNames[i]))
						cache=cacheManager.getCache(cacheCode);
				}
			}
			if (null != cache)
			{
				cache.removeAll();
				log.debug("清除了缓存" + cacheCode + "中的所有对象。");
			}
		}
	}

	private Cache getCache(Connection conn, String cacheCode)
	{
		// 从cacheconfig表中获取cacheName，
		String cacheName = getCacheNameByCode(conn, cacheCode);
		// 如果cacheName为空，则不进行缓存
		if (null == cacheName || cacheName.trim().length() == 0)
			return null;
		// 如果存在 cacheCode的cache，则使用cacheCode；否则，克隆一个cacheCode
		Cache cache = null;
		if (cacheManager.cacheExists(cacheCode))
		{
			cache = cacheManager.getCache(cacheCode);
		}
		else
		{
			if (cacheManager.cacheExists(cacheName))
			{
				Cache t = cacheManager.getCache(cacheName);
				CacheConfiguration c = t.getCacheConfiguration();
				// 继承cacheName的属性，创建新的cache
				cache = new Cache(cacheCode, c.getMaxElementsInMemory(), c.isOverflowToDisk(), c.isEternal(), c.getTimeToLiveSeconds(), c.getTimeToIdleSeconds());
				cacheManager.addCache(cache);
			}
			if (null == cache)
			{
				// 创建
				cacheManager.addCache(cacheCode);
				cache = cacheManager.getCache(cacheCode);
			}
		}
		return cache;
	}

}
