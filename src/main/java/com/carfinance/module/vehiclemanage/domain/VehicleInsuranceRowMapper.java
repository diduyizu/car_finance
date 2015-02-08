package com.carfinance.module.vehiclemanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleInsuranceRowMapper implements RowMapper<VehicleInsurance>{
	public VehicleInsurance mapRow(ResultSet rs, int arg1) throws SQLException {
        VehicleInsurance vehicleInfo = new VehicleInsurance();

        vehicleInfo.setId(rs.getLong("id"));
        vehicleInfo.setCarframe_no(rs.getString("carframe_no"));
        vehicleInfo.setEngine_no(rs.getString("engine_no"));
        vehicleInfo.setLicense_plate(rs.getString("license_plate"));
        vehicleInfo.setInsurance_company(rs.getString("insurance_company"));
        vehicleInfo.setStrong_insurance(rs.getDouble("strong_insurance"));
        vehicleInfo.setVehicle_vessel_tax(rs.getDouble("vehicle_vessel_tax"));
        vehicleInfo.setStrong_insurance_expire_at(rs.getDate("strong_insurance_expire_at"));
        vehicleInfo.setBusiness_insurance(rs.getDouble("business_insurance"));
        vehicleInfo.setBusiness_insurance_expire_at(rs.getDate("business_insurance_expire_at"));
        vehicleInfo.setRemark(rs.getString("remark"));
        vehicleInfo.setCreate_by(rs.getLong("create_by"));
        vehicleInfo.setCreate_at(rs.getDate("create_at"));

        return vehicleInfo;
	}
}
