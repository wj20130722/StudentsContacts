package com.linkage.contacts.server.entity;

import java.util.Date;

public class UserFeedBack {
    private int feedback_id;

    private Date feedback_time;

    private String advice;

    private String contacts_way;

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public Date getFeedback_time() {
        return feedback_time;
    }

    public void setFeedback_time(Date feedback_time) {
        this.feedback_time = feedback_time;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getContacts_way() {
        return contacts_way;
    }

    public void setContacts_way(String contacts_way) {
        this.contacts_way = contacts_way == null ? null : contacts_way.trim();
    }
}