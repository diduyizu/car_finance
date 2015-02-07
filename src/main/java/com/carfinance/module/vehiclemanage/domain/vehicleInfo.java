package com.carfinance.module.vehiclemanage.domain;

import java.io.Serializable;
import java.util.Date;

public class VehicleInfo implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long id;
	private String archive_no;
	private String inventory_no;
    private String brand;
    private String model;
    private String color;
    private String carframe_no;
    private String engine_no;
    private String registry_certificate;
    private String certificate_direction;
    private String loan_bank;
    private String consistency_cer;
    private String check_list;
    private String duty_paid_proof;
    private String record;
    private Date buy_at;
    private String supplier;
    private String license_plate;
    private Date card_at;
    private Date limited_at;
    private Double guide_price;
    private Double vehicle_price;
    private Double vehicle_tax;
    private String insurance_company;
    private Double strong_insurance;
    private Double vehicle_vessel_tax;
    private Date strong_insurance_expire_at;
    private Double business_insurance;
    private Date business_insurance_expire_at;
    private String remark;
    private long create_by;
    private Date create_at;
    private long original_org;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArchive_no() {
        return archive_no;
    }

    public void setArchive_no(String archive_no) {
        this.archive_no = archive_no;
    }

    public String getInventory_no() {
        return inventory_no;
    }

    public void setInventory_no(String inventory_no) {
        this.inventory_no = inventory_no;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCarframe_no() {
        return carframe_no;
    }

    public void setCarframe_no(String carframe_no) {
        this.carframe_no = carframe_no;
    }

    public String getEngine_no() {
        return engine_no;
    }

    public void setEngine_no(String engine_no) {
        this.engine_no = engine_no;
    }

    public String getRegistry_certificate() {
        return registry_certificate;
    }

    public void setRegistry_certificate(String registry_certificate) {
        this.registry_certificate = registry_certificate;
    }

    public String getCertificate_direction() {
        return certificate_direction;
    }

    public void setCertificate_direction(String certificate_direction) {
        this.certificate_direction = certificate_direction;
    }

    public String getLoan_bank() {
        return loan_bank;
    }

    public void setLoan_bank(String loan_bank) {
        this.loan_bank = loan_bank;
    }

    public String getConsistency_cer() {
        return consistency_cer;
    }

    public void setConsistency_cer(String consistency_cer) {
        this.consistency_cer = consistency_cer;
    }

    public String getCheck_list() {
        return check_list;
    }

    public void setCheck_list(String check_list) {
        this.check_list = check_list;
    }

    public String getDuty_paid_proof() {
        return duty_paid_proof;
    }

    public void setDuty_paid_proof(String duty_paid_proof) {
        this.duty_paid_proof = duty_paid_proof;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Date getBuy_at() {
        return buy_at;
    }

    public void setBuy_at(Date buy_at) {
        this.buy_at = buy_at;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public Date getCard_at() {
        return card_at;
    }

    public void setCard_at(Date card_at) {
        this.card_at = card_at;
    }

    public Date getLimited_at() {
        return limited_at;
    }

    public void setLimited_at(Date limited_at) {
        this.limited_at = limited_at;
    }

    public Double getGuide_price() {
        return guide_price;
    }

    public void setGuide_price(Double guide_price) {
        this.guide_price = guide_price;
    }

    public Double getVehicle_price() {
        return vehicle_price;
    }

    public void setVehicle_price(Double vehicle_price) {
        this.vehicle_price = vehicle_price;
    }

    public Double getVehicle_tax() {
        return vehicle_tax;
    }

    public void setVehicle_tax(Double vehicle_tax) {
        this.vehicle_tax = vehicle_tax;
    }

    public String getInsurance_company() {
        return insurance_company;
    }

    public void setInsurance_company(String insurance_company) {
        this.insurance_company = insurance_company;
    }

    public Double getStrong_insurance() {
        return strong_insurance;
    }

    public void setStrong_insurance(Double strong_insurance) {
        this.strong_insurance = strong_insurance;
    }

    public Double getVehicle_vessel_tax() {
        return vehicle_vessel_tax;
    }

    public void setVehicle_vessel_tax(Double vehicle_vessel_tax) {
        this.vehicle_vessel_tax = vehicle_vessel_tax;
    }

    public Date getStrong_insurance_expire_at() {
        return strong_insurance_expire_at;
    }

    public void setStrong_insurance_expire_at(Date strong_insurance_expire_at) {
        this.strong_insurance_expire_at = strong_insurance_expire_at;
    }

    public Double getBusiness_insurance() {
        return business_insurance;
    }

    public void setBusiness_insurance(Double business_insurance) {
        this.business_insurance = business_insurance;
    }

    public Date getBusiness_insurance_expire_at() {
        return business_insurance_expire_at;
    }

    public void setBusiness_insurance_expire_at(Date business_insurance_expire_at) {
        this.business_insurance_expire_at = business_insurance_expire_at;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public long getOriginal_org() {
        return original_org;
    }

    public void setOriginal_org(long original_org) {
        this.original_org = original_org;
    }
}

