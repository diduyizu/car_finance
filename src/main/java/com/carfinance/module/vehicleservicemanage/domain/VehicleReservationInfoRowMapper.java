package com.carfinance.module.vehicleservicemanage.domain;

import com.carfinance.utils.DateTimeUtil;
import com.carfinance.utils.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class VehicleReservationInfoRowMapper implements RowMapper<VehicleReservationInfo>{
	public VehicleReservationInfo mapRow(ResultSet rs, int arg1) throws SQLException {
        VehicleReservationInfo vehicleReservationInfo=new VehicleReservationInfo();

        vehicleReservationInfo.setId(rs.getLong("id"));
        vehicleReservationInfo.setCustomer_name(rs.getString("customer_name"));
        vehicleReservationInfo.setCustomer_dn(rs.getString("customer_dn"));

        String use_begin_str = rs.getTimestamp("use_begin").toString();
        String use_end_str = rs.getTimestamp("use_end").toString();
        vehicleReservationInfo.setUse_begin(use_begin_str.substring(0 , use_begin_str.length()-2));
        vehicleReservationInfo.setUse_end(use_end_str.substring(0 , use_end_str.length()-2));

        vehicleReservationInfo.setCreate_by(rs.getLong("create_by"));
        vehicleReservationInfo.setCreate_at(rs.getDate("create_at"));

//        vehicleReservationInfo.setModel(rs.getString("model"));
//        vehicleReservationInfo.setUnit_price(rs.getDouble("unit_price"));
//        vehicleReservationInfo.setQuantity(rs.getLong("quantity"));
//        vehicleReservationInfo.setWith_driver(rs.getInt("with_driver"));
//        vehicleReservationInfo.setExpenses_self(rs.getInt("expenses_self"));
        vehicleReservationInfo.setEmployee_id(rs.getString("employee_id"));
        vehicleReservationInfo.setEmployee_name(rs.getString("employee_name"));
        vehicleReservationInfo.setOrg_id(rs.getLong("org_id"));
        vehicleReservationInfo.setStatus(rs.getLong("status"));
//        vehicleReservationInfo.setRisk_control_update_by(rs.getLong("risk_control_update_by"));
//        vehicleReservationInfo.setRisk_control_update_at(rs.getDate("risk_control_update_at"));
//        vehicleReservationInfo.setBusiness_manager_update_by(rs.getLong("business_manager_update_by"));
//        vehicleReservationInfo.setBusiness_manager_update_at(rs.getDate("business_manager_update_at"));
//        vehicleReservationInfo.setFinance_update_by(rs.getLong("finance_update_by"));
//        vehicleReservationInfo.setFinance_update_at(rs.getDate("finance_update_at"));

        vehicleReservationInfo.setRemark(rs.getString("remark"));

        return vehicleReservationInfo;
	}
}
