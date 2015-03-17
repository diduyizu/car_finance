package com.carfinance.module.vehicleservicemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class PropertyPaymentDetail implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
    private long contrace_id;
    private double should_payment	;
    private double actual_payment;
    private Date create_at;
    private long create_by;
    private String create_name;

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContrace_id() {
        return contrace_id;
    }

    public void setContrace_id(long contrace_id) {
        this.contrace_id = contrace_id;
    }

    public double getShould_payment() {
        return should_payment;
    }

    public void setShould_payment(double should_payment) {
        this.should_payment = should_payment;
    }

    public double getActual_payment() {
        return actual_payment;
    }

    public void setActual_payment(double actual_payment) {
        this.actual_payment = actual_payment;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public long getCreate_by() {
        return create_by;
    }

    public void setCreate_by(long create_by) {
        this.create_by = create_by;
    }
}

