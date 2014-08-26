package com.linkage.contacts.server.vo;

import java.io.Serializable;
import java.util.Arrays;

public class FormReportInfo implements Serializable
{
  private static final long serialVersionUID = -658105612373109802L;
	private int user_id;//被举报用户标识
	private int[] report_types;//举报类型
	private String report_content;//举报原因
	
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public int[] getReport_types()
	{
		return report_types;
	}
	public void setReport_types(int[] report_types)
	{
		this.report_types = report_types;
	}
	public String getReport_content()
	{
		return report_content;
	}
	public void setReport_content(String report_content)
	{
		this.report_content = report_content;
	}
	@Override
  public String toString()
  {
	  return "FormReportInfo [user_id=" + user_id + ", report_types=" + Arrays.toString(report_types) + ", report_content=" + report_content + "]";
  }
	
}
