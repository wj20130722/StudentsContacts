package com.linkage.contacts.server.entity;

import java.io.Serializable;

public class JobExperience implements Serializable{
	
  	private static final long serialVersionUID = -6216628341797730581L;

		private int experience_id;

    private int user_id;

    private int startyear;

    private int endyear;

    private String company_info;

    private int experience_secret;

    private int order_num;

    public int getExperience_id() {
        return experience_id;
    }

    public void setExperience_id(int experience_id) {
        this.experience_id = experience_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStartyear() {
        return startyear;
    }

    public void setStartyear(int startyear) {
        this.startyear = startyear;
    }

    public int getEndyear() {
        return endyear;
    }

    public void setEndyear(int endyear) {
        this.endyear = endyear;
    }

    public String getCompany_info() {
        return company_info;
    }

    public void setCompany_info(String company_info) {
        this.company_info = company_info == null ? null : company_info.trim();
    }

    public int getExperience_secret() {
        return experience_secret;
    }

    public void setExperience_secret(int experience_secret) {
        this.experience_secret = experience_secret;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }
}