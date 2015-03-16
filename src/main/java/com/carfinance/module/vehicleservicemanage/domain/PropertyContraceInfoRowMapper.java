package com.carfinance.module.vehicleservicemanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PropertyContraceInfoRowMapper implements RowMapper<PropertyContraceInfo>{
	public PropertyContraceInfo mapRow(ResultSet rs, int arg1) throws SQLException {
        PropertyContraceInfo propertyContraceInfo=new PropertyContraceInfo();

        propertyContraceInfo.setId(rs.getLong("id"));
        propertyContraceInfo.setContrace_no(rs.getString("contrace_no"));
        propertyContraceInfo.setCustomer_name(rs.getString("customer_name"));
        propertyContraceInfo.setCustomer_type(rs.getString("customer_type"));
        propertyContraceInfo.setCustomer_dn(rs.getString("customer_dn"));
        propertyContraceInfo.setCustomer_cer_type(rs.getString("customer_cer_type"));
        propertyContraceInfo.setCustomer_cer_no(rs.getString("customer_cer_no"));
        propertyContraceInfo.setRemark(rs.getString("remark"));
        propertyContraceInfo.setEmployee_id(rs.getString("employee_id"));
        propertyContraceInfo.setEmployee_name(rs.getString("employee_name"));
        propertyContraceInfo.setCreate_by(rs.getLong("create_by"));
        propertyContraceInfo.setCreate_at(rs.getDate("create_at"));
        propertyContraceInfo.setUpdate_by(rs.getLong("update_by"));
        propertyContraceInfo.setUpdate_at(rs.getDate("update_at"));
        propertyContraceInfo.setStatus(rs.getLong("status"));
        propertyContraceInfo.setReservation_id(rs.getLong("reservation_id"));
        propertyContraceInfo.setOrg_id(rs.getLong("org_id"));
        propertyContraceInfo.setShopowner_update_by(rs.getLong("shopowner_update_by"));
        propertyContraceInfo.setShopowner_update_at(rs.getDate("shopowner_update_at"));
        propertyContraceInfo.setCity_shopowner_update_by(rs.getLong("city_shopowner_update_by"));
        propertyContraceInfo.setCity_shopowner_update_at(rs.getDate("city_shopowner_update_at"));
        propertyContraceInfo.setRegional_manager_update_by(rs.getLong("regional_manager_update_by"));
        propertyContraceInfo.setRegional_manager_update_at(rs.getDate("regional_manager_update_at"));
        propertyContraceInfo.setFinance_update_by(rs.getLong("finance_update_by"));
        propertyContraceInfo.setFinance_update_at(rs.getDate("finance_update_at"));
        propertyContraceInfo.setIsovertop(rs.getInt("isovertop"));

        propertyContraceInfo.setSign_at(rs.getDate("sign_at"));
        propertyContraceInfo.setPeriod_number(rs.getLong("period_number"));
        propertyContraceInfo.setDown_payment(rs.getDouble("down_payment"));
        propertyContraceInfo.setLease_price(rs.getDouble("lease_price"));
        propertyContraceInfo.setMonthly_payment(rs.getDouble("montyly_payment"));
        propertyContraceInfo.setArrange_payment(rs.getDouble("arrange_payment"));
        propertyContraceInfo.setMonthly_day(rs.getInt("monthly_day"));
        propertyContraceInfo.setFinal_payment(rs.getDouble("final_payment"));
        propertyContraceInfo.setReceived_periods(rs.getInt("received_periods"));
        propertyContraceInfo.setAlready_back_amount(rs.getDouble("already_back_amount"));
        propertyContraceInfo.setPayment_type(rs.getString("payment_type"));

        return propertyContraceInfo;
	}
}
