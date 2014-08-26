package com.linkage.contacts.server.entity;

public class ClassRoleInfo extends ClassRoleInfoKey {
    private int class_admin;

    private int class_role_state;

    public int getClass_admin() {
        return class_admin;
    }

    public void setClass_admin(int class_admin) {
        this.class_admin = class_admin;
    }

    public int getClass_role_state() {
        return class_role_state;
    }

    public void setClass_role_state(int class_role_state) {
        this.class_role_state = class_role_state;
    }
}