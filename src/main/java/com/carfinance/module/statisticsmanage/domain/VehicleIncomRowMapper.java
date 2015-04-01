package com.carfinance.module.statisticsmanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleIncomRowMapper implements RowMapper<VehicleIncom>{
	public VehicleIncom mapRow(ResultSet rs, int arg1) throws SQLException {
        VehicleIncom vehicleIncom=new VehicleIncom();

        vehicleIncom.setLicense_plate(rs.getString("license_plate"));
        vehicleIncom.setModel(rs.getString("model"));
        vehicleIncom.setOver_price(rs.getDouble("over_price"));
        vehicleIncom.setActually_price(rs.getDouble("actually_price"));



        return vehicleIncom;
	}
}
