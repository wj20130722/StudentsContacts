package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class FormAluDesc implements Serializable
{
  private static final long serialVersionUID = 177889071440033103L;
	private int aluassociation_id;//校友会标识
	private String aluassociation_desc;//校友会简介
	
	public int getAluassociation_id()
	{
		return aluassociation_id;
	}
	public void setAluassociation_id(int aluassociation_id)
	{
		this.aluassociation_id = aluassociation_id;
	}
	public String getAluassociation_desc()
	{
		return aluassociation_desc;
	}
	public void setAluassociation_desc(String aluassociation_desc)
	{
		this.aluassociation_desc = aluassociation_desc;
	}
	@Override
  public String toString()
  {
	  return "FormAluDesc [aluassociation_id=" + aluassociation_id + ", aluassociation_desc=" + aluassociation_desc + "]";
  }
	
}
