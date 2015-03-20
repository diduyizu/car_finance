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

        try{
            org.setOrg_type_name(rs.getString("org_type_name"));
        } catch (Exception e) {}
        try{
            org.setOrg_province_name(rs.getString("org_province_name"));
        } catch (Exception e) {}
        try{
            org.setOrg_city_name(rs.getString("org_city_name"));
        } catch (Exception e) {}
        try{
            org.setOrg_country_name(rs.getString("org_country_name"));
        } catch (Exception e) {}

		return org;
	}
}
