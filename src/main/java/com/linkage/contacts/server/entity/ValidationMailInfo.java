package com.linkage.contacts.server.entity;

import java.util.Date;

public class ValidationMailInfo {
    private int validate_id;

    private int validate_man_id;

    private int user_id;

    private String validate_key;

    private Date validate_time;

    private int is_validate;

    public int getValidate_id() {
        return validate_id;
    }

    public void setValidate_id(int validate_id) {
        this.validate_id = validate_id;
    }

    public int getValidate_man_id() {
        return validate_man_id;
    }

    public void setValidate_man_id(int validate_man_id) {
        this.validate_man_id = validate_man_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getValidate_key() {
        return validate_key;
    }

    public void setValidate_key(String validate_key) {
        this.validate_key = validate_key == null ? null : validate_key.trim();
    }

    public Date getValidate_time() {
        return validate_time;
    }

    public void setValidate_time(Date validate_time) {
        this.validate_time = validate_time;
    }

    public int getIs_validate() {
        return is_validate;
    }

    public void setIs_validate(int is_validate) {
        this.is_validate = is_validate;
    }
}