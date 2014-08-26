package com.linkage.contacts.server.entity;

import java.util.Date;

public class ReportInfo {
    private int report_id;

    private int report_user_id;

    private int user_id;

    private String report_type_str;

    private String report_content;

    private Date report_time;

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getReport_user_id() {
        return report_user_id;
    }

    public void setReport_user_id(int report_user_id) {
        this.report_user_id = report_user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getReport_type_str() {
        return report_type_str;
    }

    public void setReport_type_str(String report_type_str) {
        this.report_type_str = report_type_str == null ? null : report_type_str.trim();
    }

    public String getReport_content() {
        return report_content;
    }

    public void setReport_content(String report_content) {
        this.report_content = report_content;
    }

    public Date getReport_time() {
        return report_time;
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }
}