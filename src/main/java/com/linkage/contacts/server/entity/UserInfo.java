package com.linkage.contacts.server.entity;

import java.io.Serializable;
import java.util.Date;

public class UserInfo  implements Serializable{
	
  	private static final long serialVersionUID = 5056435629998638843L;

		private int user_id;

    private int university_id;

    private int college_id;
    
    private String xuehao;

    private String user_name;

    private String user_sex;

    private int year;

    private String mail;

    private int mail_secret;

    private String password;

    private String phonenum;

    private int phonenum_secret;

    private String qq;

    private int qq_secret;

    private String city_code;
    
    private String city_name;

    private int city_secret;
    
    private String industry;//所属行业
    
    private int industry_secret;//所属行业保密状态

    private String companyname;

    private int company_secret;

    private String company_department;

    private int department_secret;

    private String company_position;

    private int position_secret;

    private int super_admin;

    private Date generate_time;

    private int allow_all_act_msg;

    private String avatar_large;

    private String avatar_hd;

    private int online_status;

    private String access_token;
    
    private String service_name;
    
    private int service_secret;
    
    private int is_authentication;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public int getCollege_id() {
        return college_id;
    }

    public void setCollege_id(int college_id) {
        this.college_id = college_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex == null ? null : user_sex.trim();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public int getMail_secret() {
        return mail_secret;
    }

    public void setMail_secret(int mail_secret) {
        this.mail_secret = mail_secret;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public int getPhonenum_secret() {
        return phonenum_secret;
    }

    public void setPhonenum_secret(int phonenum_secret) {
        this.phonenum_secret = phonenum_secret;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public int getQq_secret() {
        return qq_secret;
    }

    public void setQq_secret(int qq_secret) {
        this.qq_secret = qq_secret;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code == null ? null : city_code.trim();
    }
    
    public String getCity_name()
		{
			return city_name;
		}

		public void setCity_name(String city_name)
		{
			this.city_name = city_name == null ? null :city_name.trim();
		}

		public int getCity_secret() {
        return city_secret;
    }

    public void setCity_secret(int city_secret) {
        this.city_secret = city_secret;
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

		public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public int getCompany_secret() {
        return company_secret;
    }

    public void setCompany_secret(int company_secret) {
        this.company_secret = company_secret;
    }

    public String getCompany_department() {
        return company_department;
    }

    public void setCompany_department(String company_department) {
        this.company_department = company_department == null ? null : company_department.trim();
    }

    public int getDepartment_secret() {
        return department_secret;
    }

    public void setDepartment_secret(int department_secret) {
        this.department_secret = department_secret;
    }

    public String getCompany_position() {
        return company_position;
    }

    public void setCompany_position(String company_position) {
        this.company_position = company_position == null ? null : company_position.trim();
    }

    public int getPosition_secret() {
        return position_secret;
    }

    public void setPosition_secret(int position_secret) {
        this.position_secret = position_secret;
    }

    public int getSuper_admin() {
        return super_admin;
    }

    public void setSuper_admin(int super_admin) {
        this.super_admin = super_admin;
    }

    public Date getGenerate_time() {
        return generate_time;
    }

    public void setGenerate_time(Date generate_time) {
        this.generate_time = generate_time;
    }

    public int getAllow_all_act_msg() {
        return allow_all_act_msg;
    }

    public void setAllow_all_act_msg(int allow_all_act_msg) {
        this.allow_all_act_msg = allow_all_act_msg;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large == null ? null : avatar_large.trim();
    }

    public String getAvatar_hd() {
        return avatar_hd;
    }

    public void setAvatar_hd(String avatar_hd) {
        this.avatar_hd = avatar_hd == null ? null : avatar_hd.trim();
    }

    public int getOnline_status() {
        return online_status;
    }

    public void setOnline_status(int online_status) {
        this.online_status = online_status;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token == null ? null : access_token.trim();
    }

		public String getService_name()
		{
			return service_name;
		}

		public void setService_name(String service_name)
		{
			 this.service_name = service_name == null ? null : service_name.trim();
		}

		public int getService_secret()
		{
			return service_secret;
		}

		public void setService_secret(int service_secret)
		{
			this.service_secret = service_secret;
		}

		public String getXuehao()
		{
			return xuehao;
		}

		public void setXuehao(String xuehao)
		{
			this.xuehao = xuehao == null ? null : xuehao.trim();
		}

		public int getIs_authentication()
		{
			return is_authentication;
		}

		public void setIs_authentication(int is_authentication)
		{
			this.is_authentication = is_authentication;
		}
		
		
		
}