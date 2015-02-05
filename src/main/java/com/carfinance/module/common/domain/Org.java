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
