package com.linkage.contacts.server.entity;

import java.util.Date;

public class UserCards {
    private int card_id;

    private int user_id;

    private int store_user_id;

    private Date store_time;

    private int store_state;

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStore_user_id() {
        return store_user_id;
    }

    public void setStore_user_id(int store_user_id) {
        this.store_user_id = store_user_id;
    }

    public Date getStore_time() {
        return store_time;
    }

    public void setStore_time(Date store_time) {
        this.store_time = store_time;
    }

    public int getStore_state() {
        return store_state;
    }

    public void setStore_state(int store_state) {
        this.store_state = store_state;
    }
}