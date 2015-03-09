package com.carfinance.module.vehicleservicemanage.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class VehicleReservationInfo implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
    private String customer_name;
    private String customer_dn;
    private String use_begin;
    private String use_end;
    private String model;
    private double unit_price;
    private long quantity;
    private int with_driver;
    private int expenses_self;
    private String employee_id;
    private String employee_name;
    private long org_id;
    private long status;
    private long create_by;
    private Date create_at;
    private long risk_control_update_by;
    private Date risk_control_update_at;
    private long business_manager_update_by;
    private Date business_manager_update_at;
    private long finance_update_by;
    private Date finance_update_at;
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getUse_begin() {
        return use_begin;
    }

    public void setUse_begin(String use_begin) {
        this.use_begin = use_begin;
    }

    public String getUse_end() {
        return use_end;
    }

    public void setUse_end(String use_end) {
        this.use_end = use_end;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public int getWith_driver() {
        return with_driver;
    }

    public void setWith_driver(int with_driver) {
        this.with_driver = with_driver;
    }

    public int getExpenses_self() {
        return expenses_self;
    }

    public void setExpenses_self(int expenses_self) {
        this.expenses_self = expenses_self;
    }

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

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getRisk_control_update_by() {
        return risk_control_update_by;
    }

    public void setRisk_control_update_by(long risk_control_update_by) {
        this.risk_control_update_by = risk_control_update_by;
    }

    public Date getRisk_control_update_at() {
        return risk_control_update_at;
    }

    public void setRisk_control_update_at(Date risk_control_update_at) {
        this.risk_control_update_at = risk_control_update_at;
    }

    public long getBusiness_manager_update_by() {
        return business_manager_update_by;
    }

    public void setBusiness_manager_update_by(long business_manager_update_by) {
        this.business_manager_update_by = business_manager_update_by;
    }

    public Date getBusiness_manager_update_at() {
        return business_manager_update_at;
    }

    public void setBusiness_manager_update_at(Date business_manager_update_at) {
        this.business_manager_update_at = business_manager_update_at;
    }

    public long getFinance_update_by() {
        return finance_update_by;
    }

    public void setFinance_update_by(long finance_update_by) {
        this.finance_update_by = finance_update_by;
    }

    public Date getFinance_update_at() {
        return finance_update_at;
    }

    public void setFinance_update_at(Date finance_update_at) {
        this.finance_update_at = finance_update_at;
    }
}

