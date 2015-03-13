package com.carfinance.module.vehicleservicemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class VehicleContraceInfo implements Serializable{
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
    private String use_begin;
    private String use_end;
    private int isovertop;//是否超额，如果超额，需要市门店经理及以上审核

    private double daily_price;
    private long daily_available_km;
    private double over_km_price;
    private double over_hour_price;
    private double month_price;
    private long month_available_km;
    private double pre_payment;
    private Date monthly_day;
    private double deposit;
    private double peccancy_deposit;

    public double getDaily_price() {
        return daily_price;
    }

    public void setDaily_price(double daily_price) {
        this.daily_price = daily_price;
    }

    public long getDaily_available_km() {
        return daily_available_km;
    }

    public void setDaily_available_km(long daily_available_km) {
        this.daily_available_km = daily_available_km;
    }

    public double getOver_km_price() {
        return over_km_price;
    }

    public void setOver_km_price(double over_km_price) {
        this.over_km_price = over_km_price;
    }

    public double getOver_hour_price() {
        return over_hour_price;
    }

    public void setOver_hour_price(double over_hour_price) {
        this.over_hour_price = over_hour_price;
    }

    public double getMonth_price() {
        return month_price;
    }

    public void setMonth_price(double month_price) {
        this.month_price = month_price;
    }

    public long getMonth_available_km() {
        return month_available_km;
    }

    public void setMonth_available_km(long month_available_km) {
        this.month_available_km = month_available_km;
    }

    public double getPre_payment() {
        return pre_payment;
    }

    public void setPre_payment(double pre_payment) {
        this.pre_payment = pre_payment;
    }

    public Date getMonthly_day() {
        return monthly_day;
    }

    public void setMonthly_day(Date monthly_day) {
        this.monthly_day = monthly_day;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getPeccancy_deposit() {
        return peccancy_deposit;
    }

    public void setPeccancy_deposit(double peccancy_deposit) {
        this.peccancy_deposit = peccancy_deposit;
    }

    public String getContrace_no() {
        return contrace_no;
    }

    public void setContrace_no(String contrace_no) {
        this.contrace_no = contrace_no;
    }

    public int getIsovertop() {
        return isovertop;
    }

    public void setIsovertop(int isovertop) {
        this.isovertop = isovertop;
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
}

