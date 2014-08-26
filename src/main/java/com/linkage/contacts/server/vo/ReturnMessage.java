package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class ReturnMessage implements Serializable
{
  private static final long serialVersionUID = -6125616004931456823L;
  
	private int push_id;//推送标识
	private long push_time;//推送时间
	private String message;//推送的消息
	
	public int getPush_id()
	{
		return push_id;
	}
	public void setPush_id(int push_id)
	{
		this.push_id = push_id;
	}
	public long getPush_time()
	{
		return push_time;
	}
	public void setPush_time(long push_time)
	{
		this.push_time = push_time;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	@Override
  public String toString()
  {
	  return "ReturnMessage [push_id=" + push_id + ", push_time=" + push_time + ", message=" + message + "]";
  }
	
}
