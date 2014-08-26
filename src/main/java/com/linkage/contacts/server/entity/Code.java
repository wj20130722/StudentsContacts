package com.linkage.contacts.server.entity;

import java.io.Serializable;

public class Code implements Serializable
{
  private static final long serialVersionUID = 7696771063959867819L;

	private String codeid;

	private String title;

	private int nextnbr;

	private String codedate;

	private String codetime;

	private int poolnum;

	public String getCodeid()
	{
		return codeid;
	}

	public void setCodeid(String codeid)
	{
		this.codeid = codeid == null ? null : codeid.trim();
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title == null ? null : title.trim();
	}

	public int getNextnbr()
	{
		return nextnbr;
	}

	public void setNextnbr(int nextnbr)
	{
		this.nextnbr = nextnbr;
	}

	public String getCodedate()
	{
		return codedate;
	}

	public void setCodedate(String codedate)
	{
		this.codedate = codedate == null ? null : codedate.trim();
	}

	public String getCodetime()
	{
		return codetime;
	}

	public void setCodetime(String codetime)
	{
		this.codetime = codetime == null ? null : codetime.trim();
	}

	public int getPoolnum()
	{
		return poolnum;
	}

	public void setPoolnum(int poolnum)
	{
		this.poolnum = poolnum;
	}
}