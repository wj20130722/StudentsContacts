package com.linkage.contacts.server.entity;

import java.io.Serializable;

public class SkillInfo implements Serializable{
	
  	private static final long serialVersionUID = -8580803697672184147L;

		private int skill_id;

    private int user_id;

    private String skill_name;

    private int skill_level;

    private int skill_secret;

    private int order_num;

    public int getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(int skill_id) {
        this.skill_id = skill_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name == null ? null : skill_name.trim();
    }

    public int getSkill_level() {
        return skill_level;
    }

    public void setSkill_level(int skill_level) {
        this.skill_level = skill_level;
    }

    public int getSkill_secret() {
        return skill_secret;
    }

    public void setSkill_secret(int skill_secret) {
        this.skill_secret = skill_secret;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }
}