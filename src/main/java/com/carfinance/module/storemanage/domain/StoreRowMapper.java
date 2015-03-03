package com.carfinance.module.storemanage.domain;

import com.carfinance.module.peoplemanage.domain.OrgUserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreRowMapper implements RowMapper<Store>{
	public Store mapRow(ResultSet rs, int arg1) throws SQLException {
        Store store=new Store();

        store.setOrg_id(rs.getLong("org_id"));
        store.setOrg_name(rs.getString("org_name"));
        store.setPid(rs.getLong("pid"));
        store.setOrg_type(rs.getLong("org_type"));
        store.setOrg_province(rs.getLong("org_province"));
        store.setOrg_city(rs.getLong("org_city"));
        store.setOrg_country(rs.getLong("org_country"));
        store.setOrg_address(rs.getString("org_address"));

        try {
            store.setOrg_type_name(rs.getString("org_type_name"));
        } catch(Exception e) {
        }
        try {
            store.setOrg_province_name(rs.getString("org_province_name"));
        } catch(Exception e) {
        }
        try {
            store.setOrg_city_name(rs.getString("org_city_name"));
        } catch(Exception e) {
        }
        try {
            store.setOrg_country_name(rs.getString("org_country_name"));
        } catch(Exception e) {
        }
		return store;
	}
}
