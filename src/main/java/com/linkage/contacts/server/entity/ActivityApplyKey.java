package com.linkage.contacts.server.entity;

public class ActivityApplyKey {
	
  public ActivityApplyKey()
  {
  	
  }

	public ActivityApplyKey(int activity_id, int user_id)
  {
	  this.activity_id = activity_id;
	  this.user_id = user_id;
  }

		private int activity_id;

    private int user_id;

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}