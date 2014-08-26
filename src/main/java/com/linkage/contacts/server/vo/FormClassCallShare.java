package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class FormClassCallShare implements Serializable
{
  private static final long serialVersionUID = 1077986281943429291L;

	private int class_id; //班级标识
	
	private int share_type;//分享类型 ：1:短信;2:微信;3:微信朋友圈;4:QQ;5:QQ空间

	public int getClass_id()
	{
		return class_id;
	}

	public void setClass_id(int class_id)
	{
		this.class_id = class_id;
	}

	public int getShare_type()
	{
		return share_type;
	}

	public void setShare_type(int share_type)
	{
		this.share_type = share_type;
	}

	@Override
  public String toString()
  {
	  return "FormClassCallShare [class_id=" + class_id + ", share_type=" + share_type + "]";
  }
	
}
