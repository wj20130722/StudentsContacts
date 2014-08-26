package com.linkage.contacts.server.entity;

public class AluassociationConfig {
    private int aluassociation_id;

    private int type_id;

    private String aluassociation_name;

    private String province_code;

    private String city_code;

    private int university_id;

    private int aluassociation_state;

    private int aluassociation_num;

    private String aluassociation_pic;
    
    //增加校友会简介
    private String aluassociation_desc;

    public int getAluassociation_id() {
        return aluassociation_id;
    }

    public void setAluassociation_id(int aluassociation_id) {
        this.aluassociation_id = aluassociation_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getAluassociation_name() {
        return aluassociation_name;
    }

    public void setAluassociation_name(String aluassociation_name) {
        this.aluassociation_name = aluassociation_name == null ? null : aluassociation_name.trim();
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code == null ? null : province_code.trim();
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code == null ? null : city_code.trim();
    }

    public int getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public int getAluassociation_state() {
        return aluassociation_state;
    }

    public void setAluassociation_state(int aluassociation_state) {
        this.aluassociation_state = aluassociation_state;
    }

    public int getAluassociation_num() {
        return aluassociation_num;
    }

    public void setAluassociation_num(int aluassociation_num) {
        this.aluassociation_num = aluassociation_num;
    }

    public String getAluassociation_pic() {
        return aluassociation_pic;
    }

    public void setAluassociation_pic(String aluassociation_pic) {
        this.aluassociation_pic = aluassociation_pic == null ? null : aluassociation_pic.trim();
    }

		public String getAluassociation_desc()
		{
			return aluassociation_desc;
		}

		public void setAluassociation_desc(String aluassociation_desc)
		{
			this.aluassociation_desc = aluassociation_desc == null ? null : aluassociation_desc.trim();
		}
    
    
}