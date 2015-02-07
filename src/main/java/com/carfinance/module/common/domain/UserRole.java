package com.carfinance.module.common.domain;

import java.io.Serializable;

public class UserRole implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long user_id;
	private long role_id;
	private long org_id;
    private String org_name;
	private long status;

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
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

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
