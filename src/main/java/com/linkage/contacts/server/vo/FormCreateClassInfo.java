package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class FormCreateClassInfo implements Serializable
{
  private static final long serialVersionUID = 7203573207859899860L;
	private int class_id;//班级标识
	private int type;//操作类型:1 同意  0拒绝
	private int create_user_id;//班级创建者id
	private String refusereason;//拒绝理由
	
	public int getClass_id()
	{
		return class_id;
	}
	public void setClass_id(int class_id)
	{
		this.class_id = class_id;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public int getCreate_user_id()
	{
		return create_user_id;
	}
	public void setCreate_user_id(int create_user_id)
	{
		this.create_user_id = create_user_id;
	}
	public String getRefusereason()
	{
		return refusereason;
	}
	public void setRefusereason(String refusereason)
	{
		this.refusereason = refusereason;
	}
	@Override
  public String toString()
  {
	  return "FormCreateClassInfo [class_id=" + class_id + ", type=" + type + ", create_user_id=" + create_user_id + ", refusereason=" + refusereason + "]";
  }
	
	
}
