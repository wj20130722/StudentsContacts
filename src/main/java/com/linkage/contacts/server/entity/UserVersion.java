package com.linkage.contacts.server.entity;

public class UserVersion {
    private int id;

    private int version_id;
    
    private String version_info;

    private int user_id;

    private int platform;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion_id() {
        return version_id;
    }

    public void setVersion_id(int version_id) {
        this.version_id = version_id;
    }
    
    public String getVersion_info() {
      return version_info;
  }

  public void setVersion_info(String version_info) {
      this.version_info = version_info == null ? null : version_info.trim();
  }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }
}