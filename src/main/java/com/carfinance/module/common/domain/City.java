package com.carfinance.module.common.domain;

import java.io.Serializable;

public class City implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long city_id;
	private String city_name;
	private long province_id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public long getProvince_id() {
        return province_id;
    }

    public void setProvince_id(long province_id) {
        this.province_id = province_id;
    }
}
