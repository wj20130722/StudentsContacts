package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class FormUserAdvice implements Serializable
{
  private static final long serialVersionUID = 452806743934577745L;
  
	private String advice;//反馈意见
	private String contacts_way;//联系方式
	
	public String getAdvice()
	{
		return advice;
	}
	public void setAdvice(String advice)
	{
		this.advice = advice;
	}
	public String getContacts_way()
	{
		return contacts_way;
	}
	public void setContacts_way(String contacts_way)
	{
		this.contacts_way = contacts_way;
	}
	@Override
  public String toString()
  {
	  return "FormUserAdvice [advice=" + advice + ", contacts_way=" + contacts_way + "]";
  }
	
	
}
