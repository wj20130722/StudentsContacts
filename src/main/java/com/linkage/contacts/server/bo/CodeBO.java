/*
 * @(#)Code.java	1.00 2002-11-19
 * Copyright (c) 2002 by Blue Eidolon Software, Inc. All Rights Reserved.
 * Created by Rose
 */

/**
 * 目的:取得指定名称的ID的唯一值
 */

package com.linkage.contacts.server.bo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import com.linkage.contacts.server.dao.CodeDAO;
import com.linkage.core.SpringContextHolder;

@Service
public class CodeBO{
//	@Autowired
//	private CodeDAO codeDAO;
//	public void setCodeDAO(CodeDAO codeDAO)
//	{
//		this.codeDAO = codeDAO;
//	}
	
	public long newCode(String s_id_name)
	{
		return newCode(s_id_name,s_id_name,1L);
	}
	
	public long newCode(String s_id_name,String title)
	{
		return newCode(s_id_name,title,1L);
	}

	public long newCode(String s_id_name,String title,long quantity)
	{
		//TODO dht 目前：由于code的特殊性，需要单独建立connection执行，并且是创建一个对象就用一次。后面可以改进使用单独的datasource来处理
		DataSource dataSource = null;
		Connection conn = null;
		try
    {
			dataSource = SpringContextHolder.getBean("dataSource");
	    conn = DataSourceUtils.doGetConnection(dataSource);
	    CodeDAO codeDAO = new CodeDAO(conn);
			return codeDAO.newCode(s_id_name,title,quantity);
    }
    catch (SQLException e)
    {
	    throw new RuntimeException("获取数据库连接报错！",e);
    }
		finally{
			if(null!=dataSource && null!=conn)
				DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}
}