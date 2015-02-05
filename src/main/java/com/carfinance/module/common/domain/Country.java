package com.carfinance.module.common.domain;

import java.io.Serializable;

public class Country implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long country_id;
	private String country_name;
	private long city_id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(long country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }
}
