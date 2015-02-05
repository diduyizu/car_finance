package com.carfinance.module.common.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoleRowMapper implements RowMapper<Role>{
	public Role mapRow(ResultSet rs, int arg1) throws SQLException {
		Role role=new Role(); 
		
        role.setRole_id(rs.getLong("role_id"));
        role.setRole_name(rs.getString("role_name"));
        role.setStatus(rs.getInt("status"));
        role.setRemark(rs.getString("remark"));
		
		return role;
	}
}
