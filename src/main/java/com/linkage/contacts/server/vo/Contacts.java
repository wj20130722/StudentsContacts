package com.linkage.contacts.server.vo;

import java.io.Serializable;
import java.util.Date;

public class Contacts implements Serializable
{
  private static final long serialVersionUID = -4862751758578478591L;
  
	private int push_id;//推送标识
	private int user_id;//联系人标识
	private String user_name;//联系人名称
	private String avatar_large;//联系人头像
	private Date push_time;//推送时间
	private String message;//消息内容
	
	public int getPush_id()
	{
		return push_id;
	}
	public void setPush_id(int push_id)
	{
		this.push_id = push_id;
	}
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public String getAvatar_large()
	{
		return avatar_large;
	}
	public void setAvatar_large(String avatar_large)
	{
		this.avatar_large = avatar_large;
	}
	public Date getPush_time()
	{
		return push_time;
	}
	public void setPush_time(Date push_time)
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
	  return "Contacts [push_id=" + push_id + ", user_id=" + user_id + ", user_name=" + user_name + ", avatar_large=" + avatar_large + ", push_time=" + push_time + ", message=" + message + "]";
  }
	
}
