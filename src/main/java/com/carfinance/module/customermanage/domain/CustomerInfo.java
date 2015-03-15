package com.carfinance.module.customermanage.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CustomerInfo implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
    private String customer_name;
    private String certificate_type;//证件类型
    private String certificate_no;//证件号码
    private String customer_dn;
    private String customer_email;
    private Date create_at;
    private long create_by;
    private Date update_at;
    private long update_by;
    private String customer_type;
    private String customer_house;
    private String customer_vehicle;
    private String customer_guarantee;

    private String vip_no;
    private List<CustomerAnnex> customer_annex_list;

    public List<CustomerAnnex> getCustomer_annex_list() {
        return customer_annex_list;
    }

    public void setCustomer_annex_list(List<CustomerAnnex> customer_annex_list) {
        this.customer_annex_list = customer_annex_list;
    }

    public String getVip_no() {
        return vip_no;
    }

    public void setVip_no(String vip_no) {
        this.vip_no = vip_no;
    }

    public String getCustomer_house() {
        return customer_house;
    }

    public void setCustomer_house(String customer_house) {
        this.customer_house = customer_house;
    }

    public String getCustomer_vehicle() {
        return customer_vehicle;
    }

    public void setCustomer_vehicle(String customer_vehicle) {
        this.customer_vehicle = customer_vehicle;
    }

    public String getCustomer_guarantee() {
        return customer_guarantee;
    }

    public void setCustomer_guarantee(String customer_guarantee) {
        this.customer_guarantee = customer_guarantee;
    }

    public String getCertificate_type() {
        return certificate_type;
    }

    public void setCertificate_type(String certificate_type) {
        this.certificate_type = certificate_type;
    }

    public String getCertificate_no() {
        return certificate_no;
    }

    public void setCertificate_no(String certificate_no) {
        this.certificate_no = certificate_no;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
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

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public long getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(long update_by) {
        this.update_by = update_by;
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

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_dn() {
        return customer_dn;
    }

    public void setCustomer_dn(String customer_dn) {
        this.customer_dn = customer_dn;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }
}

