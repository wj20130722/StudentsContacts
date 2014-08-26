package com.linkage.contacts.server.entity;

public class ClassInfo {
    private int class_id;

    private int college_id;

    private int university_id;

    private int user_id;

    private String class_name;

    private int class_degree;

    private int year;

    private int class_state;

    private int student_num;

    private String class_pic;
    
    private String teacher;  //班主任姓名

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getCollege_id() {
        return college_id;
    }

    public void setCollege_id(int college_id) {
        this.college_id = college_id;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name == null ? null : class_name.trim();
    }

    public int getClass_degree() {
        return class_degree;
    }

    public void setClass_degree(int class_degree) {
        this.class_degree = class_degree;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getClass_state() {
        return class_state;
    }

    public void setClass_state(int class_state) {
        this.class_state = class_state;
    }

    public int getStudent_num() {
        return student_num;
    }

    public void setStudent_num(int student_num) {
        this.student_num = student_num;
    }

    public String getClass_pic() {
        return class_pic;
    }

    public void setClass_pic(String class_pic) {
        this.class_pic = class_pic == null ? null : class_pic.trim();
    }

		public String getTeacher()
		{
			return teacher;
		}

		public void setTeacher(String teacher)
		{
			this.teacher = teacher;
		}
    
}