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
		return store;
	}
}
