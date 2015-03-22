package com.carfinance.module.vehiclemanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class VehicleMaintailRowMapper implements RowMapper<VehicleMaintail>{
	public VehicleMaintail mapRow(ResultSet rs, int arg1) throws SQLException {
        VehicleMaintail vehicleMaintail=new VehicleMaintail();

        vehicleMaintail.setId(rs.getLong("id"));
        vehicleMaintail.setCarframe_no(rs.getString("carframe_no"));
        vehicleMaintail.setEngine_no(rs.getString("engine_no"));
        vehicleMaintail.setLicense_plate(rs.getString("license_plate"));
        vehicleMaintail.setMaintain_date(rs.getDate("maintain_date"));
        vehicleMaintail.setMaintain_content(rs.getString("maintain_content"));
        vehicleMaintail.setMaintain_price(rs.getDouble("maintain_price"));
        vehicleMaintail.setCurrent_km(rs.getLong("current_km"));
        vehicleMaintail.setNext_maintain_km(rs.getLong("next_maintain_km"));
        vehicleMaintail.setUser_id(rs.getLong("user_id"));
        vehicleMaintail.setUser_name(rs.getString("user_name"));
        vehicleMaintail.setCreate_by(rs.getLong("create_by"));
        vehicleMaintail.setCreate_at(rs.getDate("create_at"));
        vehicleMaintail.setUpdate_by(rs.getLong("update_by"));
        vehicleMaintail.setUpdate_at(rs.getDate("update_at"));

        return vehicleMaintail;
	}
}
