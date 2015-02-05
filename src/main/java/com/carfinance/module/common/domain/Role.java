package com.carfinance.module.common.domain;

import java.io.Serializable;

public class Role implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long role_id;
	private String role_name;
	private int status;
	private String remark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
