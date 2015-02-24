package com.carfinance.module.vehiclemanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiclePeccancyRowMapper implements RowMapper<VehiclePeccancy>{
	public VehiclePeccancy mapRow(ResultSet rs, int arg1) throws SQLException {
        VehiclePeccancy vehiclePeccancy = new VehiclePeccancy();

        vehiclePeccancy.setId(rs.getLong("id"));
        vehiclePeccancy.setCarframe_no(rs.getString("carframe_no"));
        vehiclePeccancy.setEngine_no(rs.getString("engine_no"));
        vehiclePeccancy.setLicense_plate(rs.getString("license_plate"));
        vehiclePeccancy.setPeccancy_at(rs.getDate("peccancy_at"));
        vehiclePeccancy.setPeccancy_place(rs.getString("peccancy_place"));
        vehiclePeccancy.setPeccancy_reason(rs.getString("peccancy_reason"));
        vehiclePeccancy.setScore(rs.getInt("score"));
        vehiclePeccancy.setStatus(rs.getInt("status"));
        vehiclePeccancy.setCreate_at(rs.getDate("create_at"));
        vehiclePeccancy.setCreate_by(rs.getLong("create_by"));


        return vehiclePeccancy;
	}
}
