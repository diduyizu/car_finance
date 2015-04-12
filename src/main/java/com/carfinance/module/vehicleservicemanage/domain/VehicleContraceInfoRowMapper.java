package com.carfinance.module.vehicleservicemanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class VehicleContraceInfoRowMapper implements RowMapper<VehicleContraceInfo>{
	public VehicleContraceInfo mapRow(ResultSet rs, int arg1) throws SQLException {
        VehicleContraceInfo vehicleContraceInfo=new VehicleContraceInfo();

        vehicleContraceInfo.setId(rs.getLong("id"));
        vehicleContraceInfo.setContrace_no(rs.getString("contrace_no"));
        vehicleContraceInfo.setContrace_type(rs.getLong("contrace_type"));
        vehicleContraceInfo.setCustomer_name(rs.getString("customer_name"));
        vehicleContraceInfo.setCustomer_type(rs.getString("customer_type"));
        vehicleContraceInfo.setCustomer_dn(rs.getString("customer_dn"));
        vehicleContraceInfo.setCustomer_cer_type(rs.getString("customer_cer_type"));
        vehicleContraceInfo.setCustomer_cer_no(rs.getString("customer_cer_no"));
        vehicleContraceInfo.setRemark(rs.getString("remark"));
        vehicleContraceInfo.setEmployee_id(rs.getString("employee_id"));
        vehicleContraceInfo.setEmployee_name(rs.getString("employee_name"));
        vehicleContraceInfo.setCreate_by(rs.getLong("create_by"));
        vehicleContraceInfo.setCreate_at(rs.getDate("create_at"));
        vehicleContraceInfo.setUpdate_by(rs.getLong("update_by"));
        vehicleContraceInfo.setUpdate_at(rs.getDate("update_at"));
        vehicleContraceInfo.setStatus(rs.getLong("status"));
        vehicleContraceInfo.setReservation_id(rs.getLong("reservation_id"));
        String use_begin_str = rs.getTimestamp("use_begin").toString();
        String use_end_str = rs.getTimestamp("use_end").toString();
        vehicleContraceInfo.setUse_begin(use_begin_str.substring(0 , use_begin_str.length()-2));
        vehicleContraceInfo.setUse_end(use_end_str.substring(0 , use_end_str.length()-2));
        vehicleContraceInfo.setOrg_id(rs.getLong("org_id"));
        vehicleContraceInfo.setShopowner_update_by(rs.getLong("shopowner_update_by"));
        vehicleContraceInfo.setShopowner_update_at(rs.getDate("shopowner_update_at"));
        vehicleContraceInfo.setCity_shopowner_update_by(rs.getLong("city_shopowner_update_by"));
        vehicleContraceInfo.setCity_shopowner_update_at(rs.getDate("city_shopowner_update_at"));
        vehicleContraceInfo.setRegional_manager_update_by(rs.getLong("regional_manager_update_by"));
        vehicleContraceInfo.setRegional_manager_update_at(rs.getDate("regional_manager_update_at"));
        vehicleContraceInfo.setFinance_update_by(rs.getLong("finance_update_by"));
        vehicleContraceInfo.setFinance_update_at(rs.getDate("finance_update_at"));
        vehicleContraceInfo.setIsovertop(rs.getInt("isovertop"));

        vehicleContraceInfo.setDaily_price(rs.getDouble("daily_price"));
        vehicleContraceInfo.setDaily_available_km(rs.getLong("daily_available_km"));
        vehicleContraceInfo.setOver_km_price(rs.getDouble("over_km_price"));
        vehicleContraceInfo.setOver_hour_price(rs.getDouble("over_hour_price"));
        vehicleContraceInfo.setMonth_price(rs.getDouble("month_price"));
        vehicleContraceInfo.setMonth_available_km(rs.getLong("month_available_km"));
        vehicleContraceInfo.setPre_payment(rs.getDouble("pre_payment"));
        vehicleContraceInfo.setMonthly_day(rs.getDate("monthly_day"));
        vehicleContraceInfo.setDeposit(rs.getDouble("deposit"));
        vehicleContraceInfo.setPeccancy_deposit(rs.getDouble("peccancy_deposit"));


        vehicleContraceInfo.setSystem_total_price(rs.getDouble("system_total_price"));
        vehicleContraceInfo.setArrange_price(rs.getDouble("arrange_price"));
        vehicleContraceInfo.setActual_price(rs.getDouble("actual_price"));
        vehicleContraceInfo.setLate_fee(rs.getDouble("late_fee"));
        vehicleContraceInfo.setIs_arrearage(rs.getInt("is_arrearage"));

        String arrearage_date_str = rs.getTimestamp("arrearage_date").toString();
        vehicleContraceInfo.setArrearage_date(arrearage_date_str.substring(0 , arrearage_date_str.length()-2));

        try{
            vehicleContraceInfo.setTotal_actually(rs.getDouble("total_actually"));
        } catch (Exception e){}

        try{
            vehicleContraceInfo.setRegionalmanager_audit_status(rs.getInt("regionalmanager_audit_status"));
        } catch (Exception e){}

        try{
            vehicleContraceInfo.setGeneralmanager_audit_status(rs.getInt("generalmanager_audit_status"));
        } catch (Exception e){}

        return vehicleContraceInfo;
	}
}
