package com.carfinance.module.vehiclemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class VehiclePeccancy implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
    private String carframe_no;
    private String engine_no;
    private String license_plate;
    private Date peccancy_at;
    private String peccancy_place;
    private String peccancy_reason;
    private double peccancy_price;
    private int score;
    private int status;
    private String arbitration;
    private String employee_id;
    private String employee_name;
    private long customer_id;
    private String customer_name;
    private long create_by;
    private Date create_at;
    private long update_by;
    private Date update_at;

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

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getArbitration() {
        return arbitration;
    }

    public void setArbitration(String arbitration) {
        this.arbitration = arbitration;
    }

    public double getPeccancy_price() {
        return peccancy_price;
    }

    public void setPeccancy_price(double peccancy_price) {
        this.peccancy_price = peccancy_price;
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

    public Date getPeccancy_at() {
        return peccancy_at;
    }

    public void setPeccancy_at(Date peccancy_at) {
        this.peccancy_at = peccancy_at;
    }

    public String getPeccancy_place() {
        return peccancy_place;
    }

    public void setPeccancy_place(String peccancy_place) {
        this.peccancy_place = peccancy_place;
    }

    public String getPeccancy_reason() {
        return peccancy_reason;
    }

    public void setPeccancy_reason(String peccancy_reason) {
        this.peccancy_reason = peccancy_reason;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

