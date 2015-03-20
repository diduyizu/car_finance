package com.carfinance.module.common.domain;

import java.io.Serializable;

public class Org implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long org_id;
	private String org_name;
	private long pid;
	private long org_type;
    private long org_province;
    private long org_city;
    private long org_country;
    private String org_address;

    private String org_type_name;
    private String org_province_name;
    private String org_city_name;
    private String org_country_name;

    public String getOrg_type_name() {
        return org_type_name;
    }

    public void setOrg_type_name(String org_type_name) {
        this.org_type_name = org_type_name;
    }

    public String getOrg_province_name() {
        return org_province_name;
    }

    public void setOrg_province_name(String org_province_name) {
        this.org_province_name = org_province_name;
    }

    public String getOrg_city_name() {
        return org_city_name;
    }

    public void setOrg_city_name(String org_city_name) {
        this.org_city_name = org_city_name;
    }

    public String getOrg_country_name() {
        return org_country_name;
    }

    public void setOrg_country_name(String org_country_name) {
        this.org_country_name = org_country_name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getOrg_type() {
        return org_type;
    }

    public void setOrg_type(long org_type) {
        this.org_type = org_type;
    }

    public long getOrg_province() {
        return org_province;
    }

    public void setOrg_province(long org_province) {
        this.org_province = org_province;
    }

    public long getOrg_city() {
        return org_city;
    }

    public void setOrg_city(long org_city) {
        this.org_city = org_city;
    }

    public long getOrg_country() {
        return org_country;
    }

    public void setOrg_country(long org_country) {
        this.org_country = org_country;
    }

    public String getOrg_address() {
        return org_address;
    }

    public void setOrg_address(String org_address) {
        this.org_address = org_address;
    }
}
