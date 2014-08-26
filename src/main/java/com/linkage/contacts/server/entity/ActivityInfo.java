package com.linkage.contacts.server.entity;

import java.util.Date;

public class ActivityInfo {
    private int activity_id;

    private int user_id;

    private int university_id;

    private String activity_name;

    private Date begin_time;
    
    private Date end_time;

    private String address;

    private int view_times;

    private Date apply_time;

    private int apply_number;

    private int activity_state;

    private String activity_pic;

    private String create_org;

    private String activity_desc;

    private String his_desc;
    
    //新增的字段
    private Date generate_time;//发布时间
    
    private int activity_type;//活动类型
    
    private String activity_url;//活动链接
    
    private String begin_time2;//type=2的活动开始时间

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

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

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name == null ? null : activity_name.trim();
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }
    
    public Date getEnd_time()
		{
			return end_time;
		}

		public void setEnd_time(Date end_time)
		{
			this.end_time = end_time;
		}

		public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public int getView_times() {
        return view_times;
    }

    public void setView_times(int view_times) {
        this.view_times = view_times;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public int getApply_number() {
        return apply_number;
    }

    public void setApply_number(int apply_number) {
        this.apply_number = apply_number;
    }

    public int getActivity_state() {
        return activity_state;
    }

    public void setActivity_state(int activity_state) {
        this.activity_state = activity_state;
    }

    public String getActivity_pic() {
        return activity_pic;
    }

    public void setActivity_pic(String activity_pic) {
        this.activity_pic = activity_pic == null ? null : activity_pic.trim();
    }

    public String getCreate_org() {
        return create_org;
    }

    public void setCreate_org(String create_org) {
        this.create_org = create_org == null ? null : create_org.trim();
    }

    public String getActivity_desc() {
        return activity_desc;
    }

    public void setActivity_desc(String activity_desc) {
        this.activity_desc = activity_desc;
    }

    public String getHis_desc() {
        return his_desc;
    }

    public void setHis_desc(String his_desc) {
        this.his_desc = his_desc;
    }

		public Date getGenerate_time()
		{
			return generate_time;
		}

		public void setGenerate_time(Date generate_time)
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
    
}