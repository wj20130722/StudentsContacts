package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class FormContacts implements Serializable
{
  private static final long serialVersionUID = -4207919842555380714L;
  
	private int user_id;//联系人标识
	private String user_name;//联系人名称
	private String avatar_large;//联系人头像
	private long push_time;//推送时间
	private String message;//消息内容
	
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
	  return "FormContacts [user_id=" + user_id + ", user_name=" + user_name + ", avatar_large=" + avatar_large + ", push_time=" + push_time + ", message=" + message + "]";
  }
	
}
