package com.linkage.contacts.filter;

/**
 * 在浏览器客户端进行对象的缓存，一般对图片，js，css文件进行缓存
 * @author dht
 *
 */
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleCacheFilter implements javax.servlet.Filter
{
	private static final Log LOG = LogFactory.getLog(SimpleCacheFilter.class.getName());
	FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig)
	{
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
	{
		String sCache = filterConfig.getInitParameter("cache");
		HttpServletRequest httpReq = (HttpServletRequest) req;
		if ("no-cache".equalsIgnoreCase(sCache))
		{
			((HttpServletResponse) res).setHeader("Cache-Control", "no-cache");
			((HttpServletResponse) res).setHeader("Pragma", "no-cache"); // HTTP1.0
			((HttpServletResponse) res).setDateHeader("Expires", 0);
		}
		else
		{
			((HttpServletResponse) res).setHeader("Cache-Control", sCache);
		}
		if (LOG.isDebugEnabled())
		{
			LOG.debug(httpReq.getRequestURL() + "文件添加了Cache-Control=" + sCache);
		}
		chain.doFilter(req, res);
	}

	public void destroy()
	{
		this.filterConfig = null;
	}
}
