package com.carfinance.module.peoplemanage.domain;

import com.carfinance.module.common.domain.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrgUserRoleRowMapper implements RowMapper<OrgUserRole>{
	public OrgUserRole mapRow(ResultSet rs, int arg1) throws SQLException {
        OrgUserRole orgUserRole=new OrgUserRole();

        orgUserRole.setUser_id(rs.getLong("user_id"));
        orgUserRole.setRole_id(rs.getLong("role_id"));
        orgUserRole.setOrg_id(rs.getLong("org_id"));
        orgUserRole.setUser_name(rs.getString("user_name"));
        orgUserRole.setRole_name(rs.getString("role_name"));
        orgUserRole.setOrg_name(rs.getString("org_name"));
		
		return orgUserRole;
	}
}
