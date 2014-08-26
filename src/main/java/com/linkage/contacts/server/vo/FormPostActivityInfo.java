package com.linkage.contacts.server.vo;

import java.io.Serializable;
import java.util.List;

public class FormPostActivityInfo implements Serializable
{
  private static final long serialVersionUID = 8757292468246339387L;
  
	private int activity_id;//活动标识
	private String activity_name;//活动标题
	private long begin_time;//活动开始时间 yyyy-MM-dd HH:mm:ss
	private long end_time;//活动结束时间 yyyy-MM-dd HH:mm:ss
	private String address;//活动地址
	private long apply_time;//报名截止时间 yyyy-MM-dd HH:mm:ss
	private String create_org;//活动组织者
	private String activity_pic;//活动首页缩略图url
	private String activity_desc;//活动描述
	private List<ActivityImage> images;
	
	public int getActivity_id()
	{
		return activity_id;
	}
	public void setActivity_id(int activity_id)
	{
		this.activity_id = activity_id;
	}
	public String getActivity_name()
	{
		return activity_name;
	}
	public void setActivity_name(String activity_name)
	{
		this.activity_name = activity_name;
	}
	public long getBegin_time()
	{
		return begin_time;
	}
	public void setBegin_time(long begin_time)
	{
		this.begin_time = begin_time;
	}
	public long getEnd_time()
	{
		return end_time;
	}
	public void setEnd_time(long end_time)
	{
		this.end_time = end_time;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public long getApply_time()
	{
		return apply_time;
	}
	public void setApply_time(long apply_time)
	{
		this.apply_time = apply_time;
	}
	public String getCreate_org()
	{
		return create_org;
	}
	public void setCreate_org(String create_org)
	{
		this.create_org = create_org;
	}
	public String getActivity_pic()
	{
		return activity_pic;
	}
	public void setActivity_pic(String activity_pic)
	{
		this.activity_pic = activity_pic;
	}
	public String getActivity_desc()
	{
		return activity_desc;
	}
	public void setActivity_desc(String activity_desc)
	{
		this.activity_desc = activity_desc;
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
	  return "FormPostActivityInfo [activity_id="
	         + activity_id
	         + ", activity_name="
	         + activity_name
	         + ", begin_time="
	         + begin_time
	         + ", end_time="
	         + end_time
	         + ", address="
	         + address
	         + ", apply_time="
	         + apply_time
	         + ", create_org="
	         + create_org
	         + ", activity_pic="
	         + activity_pic
	         + ", activity_desc="
	         + activity_desc
	         + ", images="
	         + images
	         + "]";
  }
	
}
