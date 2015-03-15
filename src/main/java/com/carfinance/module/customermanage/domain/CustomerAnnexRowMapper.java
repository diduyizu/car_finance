package com.carfinance.module.customermanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerAnnexRowMapper implements RowMapper<CustomerAnnex>{
	public CustomerAnnex mapRow(ResultSet rs, int arg1) throws SQLException {
        CustomerAnnex customerAnnex=new CustomerAnnex();

        customerAnnex.setId(rs.getLong("id"));
        customerAnnex.setCustomer_id(rs.getString("customer_id"));
        customerAnnex.setAnnex_name(rs.getString("annex_name"));
        customerAnnex.setUrl(rs.getString("url"));

        return customerAnnex;
	}
}
