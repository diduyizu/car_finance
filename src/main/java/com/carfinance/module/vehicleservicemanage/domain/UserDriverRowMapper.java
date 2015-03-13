package com.carfinance.module.vehicleservicemanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDriverRowMapper implements RowMapper<UserDriver>{
	public UserDriver mapRow(ResultSet rs, int arg1) throws SQLException {
        UserDriver userDriver=new UserDriver();

        userDriver.setUser_id(rs.getLong("user_id"));
        userDriver.setUser_name(rs.getString("user_name"));
        userDriver.setEmployee_id(rs.getString("employee_id"));
        userDriver.setStatus(rs.getInt("driver_status"));
        userDriver.setDriver_license_no(rs.getString("driver_license_no"));

        return userDriver;
	}
}
