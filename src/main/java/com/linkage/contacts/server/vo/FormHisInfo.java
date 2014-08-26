package com.linkage.contacts.server.vo;

import java.io.Serializable;
import java.util.List;

public class FormHisInfo implements Serializable
{
  private static final long serialVersionUID = -9157685631961928579L;
  
	private int activity_id;//活动标识
	private String his_desc;//花絮描述
	private List<ActivityImage> images;//花絮图片
	
	public int getActivity_id()
	{
		return activity_id;
	}
	public void setActivity_id(int activity_id)
	{
		this.activity_id = activity_id;
	}
	public String getHis_desc()
	{
		return his_desc;
	}
	public void setHis_desc(String his_desc)
	{
		this.his_desc = his_desc;
	}
	public List<ActivityImage> getImages()
	{
		return images;
	}
	public void setImages(List<ActivityImage> images)
	{
		this.images = images;
	}
	@Override
  public String toString()
  {
	  return "FormHisInfo [activity_id=" + activity_id + ", his_desc=" + his_desc + ", images=" + images + "]";
  }
	
	
	
	
}
