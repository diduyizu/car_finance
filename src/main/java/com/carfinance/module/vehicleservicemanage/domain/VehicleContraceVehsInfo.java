package com.carfinance.module.vehicleservicemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class VehicleContraceVehsInfo implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
    private long contrace_id;
    private long vehicle_id;
    private String license_plate;
    private String model;
    private String company;
    private int isother;
    private long driving_user_id;
    private String driving_user_name;
    private String driving_user_license_no;
    private double vehicle_price;
    private long create_by;
    private Date create_at;
    private long update_by;
    private Date update_at;

    private long km;
    private long other_vehicle_km;

    private String return_time;
    private long return_km;
    private long return_org;
    private double over_price;
    private int status;

    private String etc;
    private double etc_money;
    private int oil_percent;
    private int revert_oil_percent;
    private double revert_etc_money;

    private double daily_price;
    private String settlement_way;
    private double fixed_price;

    private double system_price;
    private double reduction_price;
    private double actually_price;
    private double total_actually;

    private int dispatch_status;

    public int getDispatch_status() {
        return dispatch_status;
    }

    public void setDispatch_status(int dispatch_status) {
        this.dispatch_status = dispatch_status;
    }

    public double getSystem_price() {
        return system_price;
    }

    public void setSystem_price(double system_price) {
        this.system_price = system_price;
    }

    public double getReduction_price() {
        return reduction_price;
    }

    public void setReduction_price(double reduction_price) {
        this.reduction_price = reduction_price;
    }

    public double getActually_price() {
        return actually_price;
    }

    public void setActually_price(double actually_price) {
        this.actually_price = actually_price;
    }

    public double getTotal_actually() {
        return total_actually;
    }

    public void setTotal_actually(double total_actually) {
        this.total_actually = total_actually;
    }

    public String getSettlement_way() {
        return settlement_way;
    }

    public void setSettlement_way(String settlement_way) {
        this.settlement_way = settlement_way;
    }

    public double getFixed_price() {
        return fixed_price;
    }

    public void setFixed_price(double fixed_price) {
        this.fixed_price = fixed_price;
    }

    public double getDaily_price() {
        return daily_price;
    }

    public void setDaily_price(double daily_price) {
        this.daily_price = daily_price;
    }

    public double getRevert_etc_money() {
        return revert_etc_money;
    }

    public void setRevert_etc_money(double revert_etc_money) {
        this.revert_etc_money = revert_etc_money;
    }

    public int getRevert_oil_percent() {
        return revert_oil_percent;
    }

    public void setRevert_oil_percent(int revert_oil_percent) {
        this.revert_oil_percent = revert_oil_percent;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public double getEtc_money() {
        return etc_money;
    }

    public void setEtc_money(double etc_money) {
        this.etc_money = etc_money;
    }

    public int getOil_percent() {
        return oil_percent;
    }

    public void setOil_percent(int oil_percent) {
        this.oil_percent = oil_percent;
    }

    public long getReturn_org() {
        return return_org;
    }

    public void setReturn_org(long return_org) {
        this.return_org = return_org;
    }

    public long getOther_vehicle_km() {
        return other_vehicle_km;
    }

    public void setOther_vehicle_km(long other_vehicle_km) {
        this.other_vehicle_km = other_vehicle_km;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReturn_time() {
        return return_time;
    }

    public void setReturn_time(String return_time) {
        this.return_time = return_time;
    }

    public long getReturn_km() {
        return return_km;
    }

    public void setReturn_km(long return_km) {
        this.return_km = return_km;
    }

    public double getOver_price() {
        return over_price;
    }

    public void setOver_price(double over_price) {
        this.over_price = over_price;
    }

    public long getKm() {
        return km;
    }

    public void setKm(long km) {
        this.km = km;
    }

    public String getDriving_user_license_no() {
        return driving_user_license_no;
    }

    public void setDriving_user_license_no(String driving_user_license_no) {
        this.driving_user_license_no = driving_user_license_no;
    }

    public long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public double getVehicle_price() {
        return vehicle_price;
    }

    public void setVehicle_price(double vehicle_price) {
        this.vehicle_price = vehicle_price;
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

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getIsother() {
        return isother;
    }

    public void setIsother(int isother) {
        this.isother = isother;
    }

    public long getDriving_user_id() {
        return driving_user_id;
    }

    public void setDriving_user_id(long driving_user_id) {
        this.driving_user_id = driving_user_id;
    }

    public String getDriving_user_name() {
        return driving_user_name;
    }

    public void setDriving_user_name(String driving_user_name) {
        this.driving_user_name = driving_user_name;
    }
}

