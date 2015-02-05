package com.carfinance.module.common.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleRowMapper implements RowMapper<UserRole>{
	public UserRole mapRow(ResultSet rs, int arg1) throws SQLException {
        UserRole role=new UserRole();

        role.setUser_id(rs.getLong("user_id"));
        role.setRole_id(rs.getLong("role_id"));
        role.setOrg_id(rs.getLong("org_id"));
        role.setStatus(rs.getLong("status"));
		return role;
	}
}
