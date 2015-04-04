package com.carfinance.module.vehicleservicemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class ContraceAnnex implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
    private long contrace_id;
    private String annex_name;
    private String annex_url;
    private Date create_at;
    private long create_by;

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

    public String getAnnex_name() {
        return annex_name;
    }

    public void setAnnex_name(String annex_name) {
        this.annex_name = annex_name;
    }

    public String getAnnex_url() {
        return annex_url;
    }

    public void setAnnex_url(String annex_url) {
        this.annex_url = annex_url;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public long getCreate_by() {
        return create_by;
    }

    public void setCreate_by(long create_by) {
        this.create_by = create_by;
    }
}

