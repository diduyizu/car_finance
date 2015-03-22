package com.carfinance.module.vehiclemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class VehicleMaintail implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
    private String carframe_no;
    private String engine_no;
    private String license_plate;
    private Date maintain_date;
    private String maintain_content;
    private Double maintain_price;
    private long current_km;
    private long next_maintain_km;
    private long user_id;
    private String user_name;
    private long create_by;
    private Date create_at;
    private long update_by;
    private Date update_at;

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

    public Date getMaintain_date() {
        return maintain_date;
    }

    public void setMaintain_date(Date maintain_date) {
        this.maintain_date = maintain_date;
    }

    public String getMaintain_content() {
        return maintain_content;
    }

    public void setMaintain_content(String maintain_content) {
        this.maintain_content = maintain_content;
    }

    public Double getMaintain_price() {
        return maintain_price;
    }

    public void setMaintain_price(Double maintain_price) {
        this.maintain_price = maintain_price;
    }

    public long getCurrent_km() {
        return current_km;
    }

    public void setCurrent_km(long current_km) {
        this.current_km = current_km;
    }

    public long getNext_maintain_km() {
        return next_maintain_km;
    }

    public void setNext_maintain_km(long next_maintain_km) {
        this.next_maintain_km = next_maintain_km;
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
}

