package com.carfinance.module.common.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrgRowMapper implements RowMapper<Org>{
	public Org mapRow(ResultSet rs, int arg1) throws SQLException {
        Org org = new Org();

        org.setOrg_id(rs.getLong("org_id"));
        org.setOrg_name(rs.getString("org_name"));
        org.setPid(rs.getLong("pid"));
        org.setOrg_type(rs.getLong("org_type"));
        org.setOrg_province(rs.getLong("org_province"));
        org.setOrg_city(rs.getLong("org_city"));
        org.setOrg_country(rs.getLong("org_country"));
        org.setOrg_address(rs.getString("org_address"));

		return org;
	}
}
