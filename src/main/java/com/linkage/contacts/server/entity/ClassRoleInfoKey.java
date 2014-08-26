package com.linkage.contacts.server.entity;

public class ClassRoleInfoKey {
    private int class_id;

    private int user_id;
    
    
    public ClassRoleInfoKey()
    {
    	
    }

		public ClassRoleInfoKey(int class_id, int user_id)
    {
	    this.class_id = class_id;
	    this.user_id = user_id;
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
}