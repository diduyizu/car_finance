package com.carfinance.module.vehicleservicemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class VehicleContraceVehsInfo implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
    private long contrace_id;
    private String license_plate;
    private String model;
    private String company;
    private int isother;
    private String driving_user_id;
    private String driving_user_name;

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

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getIsother() {
        return isother;
    }

    public void setIsother(int isother) {
        this.isother = isother;
    }

    public String getDriving_user_id() {
        return driving_user_id;
    }

    public void setDriving_user_id(String driving_user_id) {
        this.driving_user_id = driving_user_id;
    }

    public String getDriving_user_name() {
        return driving_user_name;
    }

    public void setDriving_user_name(String driving_user_name) {
        this.driving_user_name = driving_user_name;
    }
}

