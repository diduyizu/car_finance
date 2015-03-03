package com.carfinance.module.customermanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerInfoRowMapper implements RowMapper<CustomerInfo>{
	public CustomerInfo mapRow(ResultSet rs, int arg1) throws SQLException {
        CustomerInfo customerInfo=new CustomerInfo();

        customerInfo.setId(rs.getLong("id"));
        customerInfo.setIdentity_id(rs.getString("identity_id"));
        customerInfo.setCustomer_name(rs.getString("customer_name"));
        customerInfo.setCustomer_dn(rs.getString("customer_dn"));
        customerInfo.setCustomer_email(rs.getString("customer_email"));
        customerInfo.setCreate_at(rs.getDate("create_at"));
        customerInfo.setCreate_by(rs.getLong("create_by"));
        customerInfo.setUpdate_at(rs.getDate("update_at"));
        customerInfo.setUpdate_by(rs.getLong("update_at"));
        customerInfo.setCustomer_type(rs.getString("customer_type"));

        return customerInfo;
	}
}
