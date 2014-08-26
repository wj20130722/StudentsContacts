package com.linkage.contacts.server.entity;

import java.util.Date;

public class ActivityApply extends ActivityApplyKey {
    private Date apply_time;

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }
}