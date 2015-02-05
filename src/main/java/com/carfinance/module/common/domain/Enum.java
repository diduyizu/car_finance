package com.carfinance.module.common.domain;

import java.io.Serializable;

public class Enum implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private String fiel_name;
	private long enum_value;
	private String enum_desc;
	private String enum_memo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFiel_name() {
        return fiel_name;
    }

    public void setFiel_name(String fiel_name) {
        this.fiel_name = fiel_name;
    }

    public long getEnum_value() {
        return enum_value;
    }

    public void setEnum_value(long enum_value) {
        this.enum_value = enum_value;
    }

    public String getEnum_desc() {
        return enum_desc;
    }

    public void setEnum_desc(String enum_desc) {
        this.enum_desc = enum_desc;
    }

    public String getEnum_memo() {
        return enum_memo;
    }

    public void setEnum_memo(String enum_memo) {
        this.enum_memo = enum_memo;
    }
}
