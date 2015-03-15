package com.carfinance.module.vehicleservicemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class VehicleContraceVehsInfo implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
    private long contrace_id;
    private long vehicle_id;
    private String license_plate;
    private String model;
    private String company;
    private int isother;
    private long driving_user_id;
    private String driving_user_name;
    private String driving_user_license_no;
    private double vehicle_price;
    private long create_by;
    private Date create_at;
    private long update_by;
    private Date update_at;

    private long km;

    private String return_time;
    private long return_km;
    private double over_price;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReturn_time() {
        return return_time;
    }

    public void setReturn_time(String return_time) {
        this.return_time = return_time;
    }

    public long getReturn_km() {
        return return_km;
    }

    public void setReturn_km(long return_km) {
        this.return_km = return_km;
    }

    public double getOver_price() {
        return over_price;
    }

    public void setOver_price(double over_price) {
        this.over_price = over_price;
    }

    public long getKm() {
        return km;
    }

    public void setKm(long km) {
        this.km = km;
    }

    public String getDriving_user_license_no() {
        return driving_user_license_no;
    }

    public void setDriving_user_license_no(String driving_user_license_no) {
        this.driving_user_license_no = driving_user_license_no;
    }

    public long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public double getVehicle_price() {
        return vehicle_price;
    }

    public void setVehicle_price(double vehicle_price) {
        this.vehicle_price = vehicle_price;
    }

    public long getCreate_by() {
        return create_by;
    }

    public void setCreate_by(long create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public long getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(long update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
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

    public long getDriving_user_id() {
        return driving_user_id;
    }

    public void setDriving_user_id(long driving_user_id) {
        this.driving_user_id = driving_user_id;
    }

    public String getDriving_user_name() {
        return driving_user_name;
    }

    public void setDriving_user_name(String driving_user_name) {
        this.driving_user_name = driving_user_name;
    }
}

