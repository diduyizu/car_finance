package com.carfinance.module.vehicleservicemanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleContraceVehsInfoRowMapper implements RowMapper<VehicleContraceVehsInfo>{
	public VehicleContraceVehsInfo mapRow(ResultSet rs, int arg1) throws SQLException {
        VehicleContraceVehsInfo vehicleContraceVehsInfo=new VehicleContraceVehsInfo();

        vehicleContraceVehsInfo.setId(rs.getLong("id"));
        vehicleContraceVehsInfo.setContrace_id(rs.getLong("contrace_id"));
        vehicleContraceVehsInfo.setVehicle_id(rs.getLong("vehicle_id"));
        vehicleContraceVehsInfo.setLicense_plate(rs.getString("license_plate"));
        vehicleContraceVehsInfo.setModel(rs.getString("model"));
        vehicleContraceVehsInfo.setCompany(rs.getString("company"));
        vehicleContraceVehsInfo.setIsother(rs.getInt("isother"));
        vehicleContraceVehsInfo.setDriving_user_id(rs.getLong("driving_user_id"));
        vehicleContraceVehsInfo.setDriving_user_name(rs.getString("driving_user_name"));
        vehicleContraceVehsInfo.setDriving_user_license_no(rs.getString("driving_user_license_no"));
        vehicleContraceVehsInfo.setVehicle_price(rs.getDouble("vehicle_price"));
        vehicleContraceVehsInfo.setCreate_by(rs.getLong("create_by"));
        vehicleContraceVehsInfo.setCreate_at(rs.getDate("create_at"));
        vehicleContraceVehsInfo.setUpdate_by(rs.getLong("update_by"));
        vehicleContraceVehsInfo.setUpdate_at(rs.getDate("update_at"));

        try{
            vehicleContraceVehsInfo.setKm(rs.getLong("km"));
        } catch (Exception e){}

        return vehicleContraceVehsInfo;
	}
}
