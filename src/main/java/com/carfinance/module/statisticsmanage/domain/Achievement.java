package com.carfinance.module.statisticsmanage.domain;

import java.io.Serializable;
import java.util.Date;

public class Achievement implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
    private String employee_id;
    private String employee_name;
    private double total_actually;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public double getTotal_actually() {
        return total_actually;
    }

    public void setTotal_actually(double total_actually) {
        this.total_actually = total_actually;
    }
}

