package com.carfinance.module.peoplemanage.domain;

import com.carfinance.module.common.domain.Role;

import java.io.Serializable;

public class OrgUserRole implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long user_id;
	private long role_id;
	private long org_id;
    private String user_name;
    private String role_name;
    private String org_name;
    private int user_role_status = 0;//用户是否拥有此角色，0-不拥有；1-拥有

    public OrgUserRole() {
    }

    public OrgUserRole(long user_id , long role_id , long org_id , String user_name , String role_name , String org_name , int user_role_status) {
        this.user_id = user_id;
        this.role_id = role_id;
        this.org_id = org_id;
        this.user_name = user_name;
        this.role_name = role_name;
        this.org_name = org_name;
        this.user_role_status = user_role_status;
    }

    public int getUser_role_status() {
        return user_role_status;
    }

    public void setUser_role_status(int user_role_status) {
        this.user_role_status = user_role_status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }
}
