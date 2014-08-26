package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class FormUserInfo2 implements Serializable
{
  private static final long serialVersionUID = 5154553366084465255L;
	private int user_id; //用户标识
	private String mail; //用户邮箱
	private int mail_secret;//邮箱是否公开
	private String companyname;//用户所在公司
	private int company_secret;//是否公开 0 不公开 1 公开 默认公开
	private String company_department;//部门
	private int department_secret;//是否公开
	private String company_position;//职位
	private int position_secret;//是否公开
	private String phonenum;//手机号码
	private int phonenum_secret;//是否公开
	private String qq;//qq号
	private int qq_secret;//是否公开
	private String city_name;//城市名称
	private int city_secret;//是否公开
	private String service_name;//提供的服务名称
	private int service_secret;//是否公开
	
	//新增字段
  private String industry;//所属行业
  private int industry_secret;//所属行业保密状态
	
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public String getMail()
	{
		return mail;
	}
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	public String getCompanyname()
	{
		return companyname;
	}
	public void setCompanyname(String companyname)
	{
		this.companyname = companyname;
	}
	public int getCompany_secret()
	{
		return company_secret;
	}
	public void setCompany_secret(int company_secret)
	{
		this.company_secret = company_secret;
	}
	public String getCompany_department()
	{
		return company_department;
	}
	public void setCompany_department(String company_department)
	{
		this.company_department = company_department;
	}
	public int getDepartment_secret()
	{
		return department_secret;
	}
	public void setDepartment_secret(int department_secret)
	{
		this.department_secret = department_secret;
	}
	public String getCompany_position()
	{
		return company_position;
	}
	public void setCompany_position(String company_position)
	{
		this.company_position = company_position;
	}
	public int getPosition_secret()
	{
		return position_secret;
	}
	public void setPosition_secret(int position_secret)
	{
		this.position_secret = position_secret;
	}
	public String getPhonenum()
	{
		return phonenum;
	}
	public void setPhonenum(String phonenum)
	{
		this.phonenum = phonenum;
	}
	public int getPhonenum_secret()
	{
		return phonenum_secret;
	}
	public void setPhonenum_secret(int phonenum_secret)
	{
		this.phonenum_secret = phonenum_secret;
	}
	public String getQq()
	{
		return qq;
	}
	public void setQq(String qq)
	{
		this.qq = qq;
	}
	public int getQq_secret()
	{
		return qq_secret;
	}
	public void setQq_secret(int qq_secret)
	{
		this.qq_secret = qq_secret;
	}
	public int getMail_secret()
	{
		return mail_secret;
	}
	public void setMail_secret(int mail_secret)
	{
		this.mail_secret = mail_secret;
	}
	public String getCity_name()
	{
		return city_name;
	}
	public void setCity_name(String city_name)
	{
		this.city_name = city_name;
	}
	public int getCity_secret()
	{
		return city_secret;
	}
	public void setCity_secret(int city_secret)
	{
		this.city_secret = city_secret;
	}
	public String getService_name()
	{
		return service_name;
	}
	public void setService_name(String service_name)
	{
		this.service_name = service_name;
	}
	public int getService_secret()
	{
		return service_secret;
	}
	public void setService_secret(int service_secret)
	{
		this.service_secret = service_secret;
	}
	
	public String getIndustry()
	{
		return industry;
	}
	public void setIndustry(String industry)
	{
		this.industry = industry == null ? null : industry.trim();
	}
	public int getIndustry_secret()
	{
		return industry_secret;
	}
	public void setIndustry_secret(int industry_secret)
	{
		this.industry_secret = industry_secret;
	}
	@Override
  public String toString()
  {
	  return "FormUserInfo2 [user_id="
	         + user_id
	         + ", mail="
	         + mail
	         + ", mail_secret="
	         + mail_secret
	         + ", companyname="
	         + companyname
	         + ", company_secret="
	         + company_secret
	         + ", company_department="
	         + company_department
	         + ", department_secret="
	         + department_secret
	         + ", company_position="
	         + company_position
	         + ", position_secret="
	         + position_secret
	         + ", phonenum="
	         + phonenum
	         + ", phonenum_secret="
	         + phonenum_secret
	         + ", qq="
	         + qq
	         + ", qq_secret="
	         + qq_secret
	         + ", city_name="
	         + city_name
	         + ", city_secret="
	         + city_secret
	         + ", service_name="
	         + service_name
	         + ", service_secret="
	         + service_secret
	         + ", industry="
	         + industry
	         + ", industry_secret="
	         + industry_secret
	         + "]";
  }
	
}
