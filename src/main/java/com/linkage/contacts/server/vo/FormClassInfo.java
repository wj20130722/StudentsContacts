package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class FormClassInfo implements Serializable
{
  private static final long serialVersionUID = -4523671935172474287L;
  
	private int class_id;//班级标识
	private String class_name;//班级名称 (包含学籍,院系,班级名称)
	private String admin_name;//管理员 (有可能一个班级有多个管理员)
	private int student_num;//加入数量
	private int student_apply;//待评审用户
	private int class_role_state;//当前用户申请加入班级状态
	private int class_admin;//当前用户是否班级管理员
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
	public String getAdmin_name()
	{
		return admin_name;
	}
	public void setAdmin_name(String admin_name)
	{
		this.admin_name = admin_name;
	}
	public int getStudent_num()
	{
		return student_num;
	}
	public void setStudent_num(int student_num)
	{
		this.student_num = student_num;
	}
	public int getClass_role_state()
	{
		return class_role_state;
	}
	public void setClass_role_state(int class_role_state)
	{
		this.class_role_state = class_role_state;
	}
	public String getClass_pic()
	{
		return class_pic;
	}
	public void setClass_pic(String class_pic)
	{
		this.class_pic = class_pic;
	}
	
	public int getClass_admin()
	{
		return class_admin;
	}
	public void setClass_admin(int class_admin)
	{
		this.class_admin = class_admin;
	}
	
	public int getStudent_apply()
	{
		return student_apply;
	}
	public void setStudent_apply(int student_apply)
	{
		this.student_apply = student_apply;
	}
	
	@Override
  public String toString()
  {
	  return "FormClassInfo [class_id="
	         + class_id
	         + ", class_name="
	         + class_name
	         + ", admin_name="
	         + admin_name
	         + ", student_num="
	         + student_num
	         + ", student_apply="
	         + student_apply
	         + ", class_role_state="
	         + class_role_state
	         + ", class_admin="
	         + class_admin
	         + ", class_pic="
	         + class_pic
	         + "]";
  }

}
