package com.linkage.contacts.server.entity;

public class UserMessageMark {
    private int user_id;

    private int has_message;

    private int has_sys_message;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHas_message() {
        return has_message;
    }

    public void setHas_message(int has_message) {
        this.has_message = has_message;
    }

    public int getHas_sys_message() {
        return has_sys_message;
    }

    public void setHas_sys_message(int has_sys_message) {
        this.has_sys_message = has_sys_message;
    }
}