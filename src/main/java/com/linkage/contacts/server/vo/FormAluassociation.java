package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class FormAluassociation implements Serializable
{
  private static final long serialVersionUID = 167571456944141128L;
  
	private int aluassociation_id;//校友会标识
	private String aluassociation_name;//校友会名称
	private String admin_name;//管理员 (有可能一个班级有多个管理员)
	private int aluassociation_num;//加入数量
	private int aluassociation_apply;//校友会申请状态的人数
	private int aluassociation_role_state;//当前用户申请加入校友会状态
	private int aluassociation_admin;//当前用户是否校友管理员
	private String aluassociation_pic;//校友会图标url
	
	public int getAluassociation_id()
	{
		return aluassociation_id;
	}
	public void setAluassociation_id(int aluassociation_id)
	{
		this.aluassociation_id = aluassociation_id;
	}
	public String getAluassociation_name()
	{
		return aluassociation_name;
	}
	public void setAluassociation_name(String aluassociation_name)
	{
		this.aluassociation_name = aluassociation_name;
	}
	public String getAdmin_name()
	{
		return admin_name;
	}
	public void setAdmin_name(String admin_name)
	{
		this.admin_name = admin_name;
	}
	public int getAluassociation_num()
	{
		return aluassociation_num;
	}
	public void setAluassociation_num(int aluassociation_num)
	{
		this.aluassociation_num = aluassociation_num;
	}
	public int getAluassociation_role_state()
	{
		return aluassociation_role_state;
	}
	public void setAluassociation_role_state(int aluassociation_role_state)
	{
		this.aluassociation_role_state = aluassociation_role_state;
	}
	public String getAluassociation_pic()
	{
		return aluassociation_pic;
	}
	public void setAluassociation_pic(String aluassociation_pic)
	{
		this.aluassociation_pic = aluassociation_pic;
	}
	public int getAluassociation_admin()
	{
		return aluassociation_admin;
	}
	public void setAluassociation_admin(int aluassociation_admin)
	{
		this.aluassociation_admin = aluassociation_admin;
	}
	
	public int getAluassociation_apply()
	{
		return aluassociation_apply;
	}
	public void setAluassociation_apply(int aluassociation_apply)
	{
		this.aluassociation_apply = aluassociation_apply;
	}
	@Override
  public String toString()
  {
	  return "FormAluassociation [aluassociation_id="
	         + aluassociation_id
	         + ", aluassociation_name="
	         + aluassociation_name
	         + ", admin_name="
	         + admin_name
	         + ", aluassociation_num="
	         + aluassociation_num
	         + ", aluassociation_apply="
	         + aluassociation_apply
	         + ", aluassociation_role_state="
	         + aluassociation_role_state
	         + ", aluassociation_admin="
	         + aluassociation_admin
	         + ", aluassociation_pic="
	         + aluassociation_pic
	         + "]";
  }
	
	
}
