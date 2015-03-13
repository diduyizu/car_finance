package com.carfinance.module.vehicleservicemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class UserDriver implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long user_id;
    private String user_name;
    private String employee_id;
    private int status;
    private String driver_license_no;

    public String getDriver_license_no() {
        return driver_license_no;
    }

    public void setDriver_license_no(String driver_license_no) {
        this.driver_license_no = driver_license_no;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

