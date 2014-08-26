package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class FormClassCreateInfo implements Serializable
{
  private static final long serialVersionUID = 8860278451925120151L;
	private int class_id;//班级标识
	private String class_name;//班级名称 (包含学籍,院系,班级名称)
	private int user_id;//班级创建者
	private String create_username;//班级创建者
	private int class_state;//班级创建状态
	private String class_pic;//班级图标url
	
	public int getClass_id()
	{
		return class_id;
	}
	public void setClass_id(int class_id)
	{
		this.class_id = class_id;
	}
	public String getClass_name()
	{
		return class_name;
	}
	public void setClass_name(String class_name)
	{
		this.class_name = class_name;
	}
	public String getCreate_username()
	{
		return create_username;
	}
	public void setCreate_username(String create_username)
	{
		this.create_username = create_username;
	}
	public int getClass_state()
	{
		return class_state;
	}
	public void setClass_state(int class_state)
	{
		this.class_state = class_state;
	}
	public String getClass_pic()
	{
		return class_pic;
	}
	public void setClass_pic(String class_pic)
	{
		this.class_pic = class_pic;
	}
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	@Override
  public String toString()
  {
	  return "FormClassCreateInfo [class_id=" + class_id + ", class_name=" + class_name + ", user_id=" + user_id + ", create_username=" + create_username + ", class_state=" + class_state + ", class_pic=" + class_pic + "]";
  }
	
}
