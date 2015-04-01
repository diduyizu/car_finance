package com.carfinance.module.statisticsmanage.domain;

import java.io.Serializable;

public class VehicleIncom implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
    private String license_plate;
    private String model;
    private double over_price;
    private double actually_price;
    private double total_price;

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public double getOver_price() {
        return over_price;
    }

    public void setOver_price(double over_price) {
        this.over_price = over_price;
    }

    public double getActually_price() {
        return actually_price;
    }

    public void setActually_price(double actually_price) {
        this.actually_price = actually_price;
    }
}

