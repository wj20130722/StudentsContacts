package com.linkage.contacts.server.entity;

import java.util.Date;

public class ClassCallShare {
    private int share_id;

    private int class_id;

    private int user_id;

    private int share_type;

    private Date share_time;

    public int getShare_id() {
        return share_id;
    }

    public void setShare_id(int share_id) {
        this.share_id = share_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getShare_type() {
        return share_type;
    }

    public void setShare_type(int share_type) {
        this.share_type = share_type;
    }

    public Date getShare_time() {
        return share_time;
    }

    public void setShare_time(Date share_time) {
        this.share_time = share_time;
    }
}