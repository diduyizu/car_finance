package com.carfinance.module.vehiclemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class VehicleInsurance implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
    private String carframe_no;
    private String engine_no;
    private String license_plate;
    private String insurance_company;
    private Double strong_insurance;
    private Double vehicle_vessel_tax;
    private Date strong_insurance_expire_at;
    private Double business_insurance;
    private Date business_insurance_expire_at;
    private String remark;
    private long create_by;
    private Date create_at;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarframe_no() {
        return carframe_no;
    }

    public void setCarframe_no(String carframe_no) {
        this.carframe_no = carframe_no;
    }

    public String getEngine_no() {
        return engine_no;
    }

    public void setEngine_no(String engine_no) {
        this.engine_no = engine_no;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getInsurance_company() {
        return insurance_company;
    }

    public void setInsurance_company(String insurance_company) {
        this.insurance_company = insurance_company;
    }

    public Double getStrong_insurance() {
        return strong_insurance;
    }

    public void setStrong_insurance(Double strong_insurance) {
        this.strong_insurance = strong_insurance;
    }

    public Double getVehicle_vessel_tax() {
        return vehicle_vessel_tax;
    }

    public void setVehicle_vessel_tax(Double vehicle_vessel_tax) {
        this.vehicle_vessel_tax = vehicle_vessel_tax;
    }

    public Date getStrong_insurance_expire_at() {
        return strong_insurance_expire_at;
    }

    public void setStrong_insurance_expire_at(Date strong_insurance_expire_at) {
        this.strong_insurance_expire_at = strong_insurance_expire_at;
    }

    public Double getBusiness_insurance() {
        return business_insurance;
    }

    public void setBusiness_insurance(Double business_insurance) {
        this.business_insurance = business_insurance;
    }

    public Date getBusiness_insurance_expire_at() {
        return business_insurance_expire_at;
    }

    public void setBusiness_insurance_expire_at(Date business_insurance_expire_at) {
        this.business_insurance_expire_at = business_insurance_expire_at;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}

