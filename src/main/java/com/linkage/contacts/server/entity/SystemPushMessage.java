package com.linkage.contacts.server.entity;

import java.util.Date;

public class SystemPushMessage {
    private int system_push_id;

    private int message_type_id;

    private int from_user_id;

    private int to_user_id;

    private Date push_time;

    private String message;

    private int push_state;

    public int getSystem_push_id() {
        return system_push_id;
    }

    public void setSystem_push_id(int system_push_id) {
        this.system_push_id = system_push_id;
    }

    public int getMessage_type_id() {
        return message_type_id;
    }

    public void setMessage_type_id(int message_type_id) {
        this.message_type_id = message_type_id;
    }

    public int getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(int from_user_id) {
        this.from_user_id = from_user_id;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public Date getPush_time() {
        return push_time;
    }

    public void setPush_time(Date push_time) {
        this.push_time = push_time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPush_state() {
        return push_state;
    }

    public void setPush_state(int push_state) {
        this.push_state = push_state;
    }
}