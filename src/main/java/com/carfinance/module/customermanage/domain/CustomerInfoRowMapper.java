package com.carfinance.module.customermanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerInfoRowMapper implements RowMapper<CustomerInfo>{
	public CustomerInfo mapRow(ResultSet rs, int arg1) throws SQLException {
        CustomerInfo customerInfo=new CustomerInfo();

        customerInfo.setId(rs.getLong("id"));
        customerInfo.setCertificate_type(rs.getString("certificate_type"));
        customerInfo.setCertificate_no(rs.getString("certificate_no"));
        customerInfo.setCustomer_name(rs.getString("customer_name"));
        customerInfo.setCustomer_dn(rs.getString("customer_dn"));
        customerInfo.setCustomer_email(rs.getString("customer_email"));
        customerInfo.setCreate_at(rs.getDate("create_at"));
        customerInfo.setCreate_by(rs.getLong("create_by"));
        customerInfo.setUpdate_at(rs.getDate("update_at"));
        customerInfo.setUpdate_by(rs.getLong("update_at"));
        customerInfo.setCustomer_type(rs.getString("customer_type"));

        customerInfo.setCustomer_house(rs.getString("customer_house"));
        customerInfo.setCustomer_vehicle(rs.getString("customer_vehicle"));
        customerInfo.setCustomer_guarantee(rs.getString("customer_guarantee"));

        customerInfo.setVip_no(rs.getString("vip_no"));

        customerInfo.setCertificate_url(rs.getString("certificate_url"));
        customerInfo.setCertificate_name(rs.getString("certificate_name"));


        try{
            customerInfo.setIdentity_name(rs.getString("identity_name"));
        } catch(Exception e) {}
        try{
            customerInfo.setIdentity_url(rs.getString("identity_url"));
        } catch(Exception e) {}
        try{
            customerInfo.setHouse_property_name(rs.getString("house_property_name"));
        } catch(Exception e) {}
        try{
            customerInfo.setHouse_property_url(rs.getString("house_property_url"));
        } catch(Exception e) {}
        try{
            customerInfo.setDriving_license_name(rs.getString("driving_license_name"));
        } catch(Exception e) {}
        try{
            customerInfo.setDriving_license_url(rs.getString("driving_license_url"));
        } catch(Exception e) {}
        try{
            customerInfo.setOther_name(rs.getString("other_name"));
        } catch(Exception e) {}
        try{
            customerInfo.setOther_url(rs.getString("other_url"));
        } catch(Exception e) {}


        return customerInfo;
	}
}
