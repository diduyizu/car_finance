package com.carfinance.module.vehicleservicemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class PropertyContraceInfo implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
    private String contrace_no;
    private String customer_name;
    private String customer_type;
    private String customer_dn;
    private String customer_cer_type;
    private String customer_cer_no;
    private String remark;
    private String employee_id;
    private String employee_name;
    private long create_by;
    private Date create_at;
    private long update_by;
    private Date update_at;
    private long status;
    private long reservation_id;
    private long shopowner_update_by;
    private Date shopowner_update_at;
    private long city_shopowner_update_by;
    private Date city_shopowner_update_at;
    private long finance_update_by;
    private Date finance_update_at;
    private long org_id;
    private long regional_manager_update_by;
    private Date regional_manager_update_at;
    private int isovertop;//是否超额，如果超额，需要市门店经理及以上审核
    private Date sign_at;
    private long period_number;
    private double down_payment;
    private double lease_price;
    private double monthly_payment;
    private double arrange_payment;
    private int monthly_day;
    private double final_payment;
    private int received_periods;
    private double already_back_amount;
    private String payment_type;
    private int reserv_to_contrace_status;

    public int getReserv_to_contrace_status() {
        return reserv_to_contrace_status;
    }

    public void setReserv_to_contrace_status(int reserv_to_contrace_status) {
        this.reserv_to_contrace_status = reserv_to_contrace_status;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
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

    public String getContrace_no() {
        return contrace_no;
    }

    public void setContrace_no(String contrace_no) {
        this.contrace_no = contrace_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getCustomer_dn() {
        return customer_dn;
    }

    public void setCustomer_dn(String customer_dn) {
        this.customer_dn = customer_dn;
    }

    public String getCustomer_cer_type() {
        return customer_cer_type;
    }

    public void setCustomer_cer_type(String customer_cer_type) {
        this.customer_cer_type = customer_cer_type;
    }

    public String getCustomer_cer_no() {
        return customer_cer_no;
    }

    public void setCustomer_cer_no(String customer_cer_no) {
        this.customer_cer_no = customer_cer_no;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public long getShopowner_update_by() {
        return shopowner_update_by;
    }

    public void setShopowner_update_by(long shopowner_update_by) {
        this.shopowner_update_by = shopowner_update_by;
    }

    public Date getShopowner_update_at() {
        return shopowner_update_at;
    }

    public void setShopowner_update_at(Date shopowner_update_at) {
        this.shopowner_update_at = shopowner_update_at;
    }

    public long getCity_shopowner_update_by() {
        return city_shopowner_update_by;
    }

    public void setCity_shopowner_update_by(long city_shopowner_update_by) {
        this.city_shopowner_update_by = city_shopowner_update_by;
    }

    public Date getCity_shopowner_update_at() {
        return city_shopowner_update_at;
    }

    public void setCity_shopowner_update_at(Date city_shopowner_update_at) {
        this.city_shopowner_update_at = city_shopowner_update_at;
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

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public long getRegional_manager_update_by() {
        return regional_manager_update_by;
    }

    public void setRegional_manager_update_by(long regional_manager_update_by) {
        this.regional_manager_update_by = regional_manager_update_by;
    }

    public Date getRegional_manager_update_at() {
        return regional_manager_update_at;
    }

    public void setRegional_manager_update_at(Date regional_manager_update_at) {
        this.regional_manager_update_at = regional_manager_update_at;
    }

    public int getIsovertop() {
        return isovertop;
    }

    public void setIsovertop(int isovertop) {
        this.isovertop = isovertop;
    }

    public Date getSign_at() {
        return sign_at;
    }

    public void setSign_at(Date sign_at) {
        this.sign_at = sign_at;
    }

    public long getPeriod_number() {
        return period_number;
    }

    public void setPeriod_number(long period_number) {
        this.period_number = period_number;
    }

    public double getDown_payment() {
        return down_payment;
    }

    public void setDown_payment(double down_payment) {
        this.down_payment = down_payment;
    }

    public double getLease_price() {
        return lease_price;
    }

    public void setLease_price(double lease_price) {
        this.lease_price = lease_price;
    }

    public double getMonthly_payment() {
        return monthly_payment;
    }

    public void setMonthly_payment(double monthly_payment) {
        this.monthly_payment = monthly_payment;
    }

    public double getArrange_payment() {
        return arrange_payment;
    }

    public void setArrange_payment(double arrange_payment) {
        this.arrange_payment = arrange_payment;
    }

    public int getMonthly_day() {
        return monthly_day;
    }

    public void setMonthly_day(int monthly_day) {
        this.monthly_day = monthly_day;
    }

    public double getFinal_payment() {
        return final_payment;
    }

    public void setFinal_payment(double final_payment) {
        this.final_payment = final_payment;
    }

    public int getReceived_periods() {
        return received_periods;
    }

    public void setReceived_periods(int received_periods) {
        this.received_periods = received_periods;
    }

    public double getAlready_back_amount() {
        return already_back_amount;
    }

    public void setAlready_back_amount(double already_back_amount) {
        this.already_back_amount = already_back_amount;
    }
}

