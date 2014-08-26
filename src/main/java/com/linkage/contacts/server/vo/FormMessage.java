package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class FormMessage implements Serializable
{
  private static final long serialVersionUID = -5220526298853208621L;
  
	private int from_user_id; //发送方的user_id
	private int to_user_id; //接收方的user_id
	private String content; //发送内容
	private long push_time;//推送时间
	
	public int getFrom_user_id()
	{
		return from_user_id;
	}
	public void setFrom_user_id(int from_user_id)
	{
		this.from_user_id = from_user_id;
	}
	public int getTo_user_id()
	{
		return to_user_id;
	}
	public void setTo_user_id(int to_user_id)
	{
		this.to_user_id = to_user_id;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public long getPush_time()
	{
		return push_time;
	}
	public void setPush_time(long push_time)
	{
		this.push_time = push_time;
	}
	@Override
  public String toString()
  {
	  return "FormMessage [from_user_id=" + from_user_id + ", to_user_id=" + to_user_id + ", content=" + content + ", push_time=" + push_time + "]";
  }
	
}
