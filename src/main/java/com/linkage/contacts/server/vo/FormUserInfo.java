package com.linkage.contacts.server.vo;

import java.io.Serializable;

public class FormUserInfo implements Serializable
{
  private static final long serialVersionUID = -3288206180329461475L;
	private int user_id; //用户标识
	//必填信息
	private String user_name; //用户姓名
	private String mail; //用户邮箱
	private String password;//密码
	private int year;//学籍
	//private int college_id;//院系标识 暂时不需要此字段
	private String xuehao;//学生学号
	private String profession;//输入的专业关键字
	private String choosexuehao;//用户选择的匹配用户   没有选该字段不需要赋值为NULL
	
	//选填信息
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
	private int mail_secret;//邮箱是否公开
	private String city_code;//城市编码(第一版暂时不考虑,直接传city_name)
	private String city_name;//城市名称
	private int city_secret;//是否公开
	private String service_name;//提供的服务名称
	private int service_secret;//是否公开
	//新增字段
  private String industry;//所属行业
  private int industry_secret;//所属行业保密状态
	
	
	//附件字段
	private int university_id;//学校标识
	private int super_admin;//是否超级管理员
	private int is_authentication;//是否已经认证
	
	public String getIndustry()
	{
		return industry;
	}
	public void setIndustry(String industry)
	{
		this.industry = industry;
	}
	public int getIndustry_secret()
	{
		return industry_secret;
	}
	public void setIndustry_secret(int industry_secret)
	{
		this.industry_secret = industry_secret;
	}
	public int getIs_authentication()
	{
		return is_authentication;
	}
	public void setIs_authentication(int is_authentication)
	{
		this.is_authentication = is_authentication;
	}
	public String getChoosexuehao()
	{
		return choosexuehao;
	}
	public void setChoosexuehao(String choosexuehao)
	{
		this.choosexuehao = choosexuehao;
	}
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public String getMail()
	{
		return mail;
	}
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year = year;
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
	public String getCity_code()
	{
		return city_code;
	}
	public void setCity_code(String city_code)
	{
		this.city_code = city_code;
	}
	public int getCity_secret()
	{
		return city_secret;
	}
	public void setCity_secret(int city_secret)
	{
		this.city_secret = city_secret;
	}
	
	public int getUniversity_id()
	{
		return university_id;
	}
	public void setUniversity_id(int university_id)
	{
		this.university_id = university_id;
	}
	public int getSuper_admin()
	{
		return super_admin;
	}
	public void setSuper_admin(int super_admin)
	{
		this.super_admin = super_admin;
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
	
	public String getCity_name()
	{
		return city_name;
	}
	public void setCity_name(String city_name)
	{
		this.city_name = city_name;
	}
	
	public String getXuehao()
	{
		return xuehao;
	}
	public void setXuehao(String xuehao)
	{
		this.xuehao = xuehao;
	}
	public String getProfession()
	{
		return profession;
	}
	public void setProfession(String profession)
	{
		this.profession = profession;
	}
	@Override
  public String toString()
  {
	  return "FormUserInfo [user_id="
	         + user_id
	         + ", user_name="
	         + user_name
	         + ", mail="
	         + mail
	         + ", password="
	         + password
	         + ", year="
	         + year
	         + ", xuehao="
	         + xuehao
	         + ", profession="
	         + profession
	         + ", choosexuehao="
	         + choosexuehao
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
	         + ", mail_secret="
	         + mail_secret
	         + ", city_code="
	         + city_code
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
	         + ", university_id="
	         + university_id
	         + ", super_admin="
	         + super_admin
	         + ", is_authentication="
	         + is_authentication
	         + "]";
  }
	
}
