package com.linkage.contacts.server.vo;

import java.io.Serializable;
import java.util.Date;

public class FormActivityInfo implements Serializable
{
  private static final long serialVersionUID = 8757292468246339387L;
  
	private int activity_id;//活动标识
	private String activity_name;//活动标题
	private long begin_time;//活动开始时间 yyyy-MM-dd HH:mm:ss
	private long end_time;//活动结束时间 yyyy-MM-dd HH:mm:ss
	private String address;//活动地址
	private long apply_time;//报名截止时间 yyyy-MM-dd HH:mm:ss
	private String create_org;//活动组织者
	private int view_times;//浏览人数
	private int apply_number;//参加人数
	private String offdate;//距离报名还剩时间  超过1天的显示天数  在一天以内的显示小时   在1小时以内的显示分钟(3天    3小时     3分钟)
	private String activity_pic;//活动首页缩略图url
	private int activity_state;//0:待审核(活动报名截止日期还没到) 1：可报名(审核通过活动报名截止日期没到)  2：报名已结束(审核通过报名截止时间到了活动开始日期没到)  3：审核不通过   4： 活动进行中(审核通过 在活动开始时间到活动结束时间) 5：活动已截止(审核通过活动结束日期到了) 6：活动已截止(待审核活动报名截止日期到了)  7 活动已经归档(已经上传花絮)
	private int user_id;//活动发布者
	
  //新增的字段
  private long generate_time;//发布时间
  
  private int activity_type;//活动类型
  
  private String activity_url;//活动链接
  
  private String begin_time2;//type=2的活动开始时间
	
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
	public int getView_times()
	{
		return view_times;
	}
	public void setView_times(int view_times)
	{
		this.view_times = view_times;
	}
	public int getApply_number()
	{
		return apply_number;
	}
	public void setApply_number(int apply_number)
	{
		this.apply_number = apply_number;
	}
	public String getOffdate()
	{
		return offdate;
	}
	public void setOffdate(String offdate)
	{
		this.offdate = offdate;
	}
	public String getActivity_pic()
	{
		return activity_pic;
	}
	public void setActivity_pic(String activity_pic)
	{
		this.activity_pic = activity_pic;
	}
	public int getActivity_state()
	{
		return activity_state;
	}
	public void setActivity_state(int activity_state)
	{
		this.activity_state = activity_state;
	}
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public long getEnd_time()
	{
		return end_time;
	}
	public void setEnd_time(long end_time)
	{
		this.end_time = end_time;
	}
	
	public long getGenerate_time()
	{
		return generate_time;
	}
	public void setGenerate_time(long generate_time)
	{
		this.generate_time = generate_time;
	}
	public int getActivity_type()
	{
		return activity_type;
	}
	public void setActivity_type(int activity_type)
	{
		this.activity_type = activity_type;
	}
	public String getActivity_url()
	{
		return activity_url;
	}
	public void setActivity_url(String activity_url)
	{
		this.activity_url = activity_url;
	}
	public String getBegin_time2()
	{
		return begin_time2;
	}
	public void setBegin_time2(String begin_time2)
	{
		this.begin_time2 = begin_time2;
	}
	@Override
  public String toString()
  {
	  return "FormActivityInfo [activity_id="
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
	         + ", view_times="
	         + view_times
	         + ", apply_number="
	         + apply_number
	         + ", offdate="
	         + offdate
	         + ", activity_pic="
	         + activity_pic
	         + ", activity_state="
	         + activity_state
	         + ", user_id="
	         + user_id
	         + ", generate_time="
	         + generate_time
	         + ", activity_type="
	         + activity_type
	         + ", activity_url="
	         + activity_url
	         + ", begin_time2="
	         + begin_time2
	         + "]";
  }
	
}
