package com.linkage.contacts.server.vo;

public class ActivityImage
{
	private int pic_id;
	private String pic_url;
	private String pic_desc;
	private int pic_state;
	
	public int getPic_id()
	{
		return pic_id;
	}
	public void setPic_id(int pic_id)
	{
		this.pic_id = pic_id;
	}
	public String getPic_url()
	{
		return pic_url;
	}
	public void setPic_url(String pic_url)
	{
		this.pic_url = pic_url;
	}
	public String getPic_desc()
	{
		return pic_desc;
	}
	public void setPic_desc(String pic_desc)
	{
		this.pic_desc = pic_desc;
	}
	public int getPic_state()
	{
		return pic_state;
	}
	public void setPic_state(int pic_state)
	{
		this.pic_state = pic_state;
	}
	@Override
  public String toString()
  {
	  return "ActivityImage [pic_id=" + pic_id + ", pic_url=" + pic_url + ", pic_desc=" + pic_desc + ", pic_state=" + pic_state + "]";
  }
	
	
}
