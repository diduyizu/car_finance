package com.carfinance.module.vehicleservicemanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyPaymentDetailRowMapper implements RowMapper<PropertyPaymentDetail>{
	public PropertyPaymentDetail mapRow(ResultSet rs, int arg1) throws SQLException {
        PropertyPaymentDetail propertyPaymentDetail=new PropertyPaymentDetail();

        propertyPaymentDetail.setId(rs.getLong("id"));
        propertyPaymentDetail.setContrace_id(rs.getLong("contrace_id"));
        propertyPaymentDetail.setShould_payment(rs.getDouble("should_payment"));
        propertyPaymentDetail.setActual_payment(rs.getDouble("actual_payment"));
        propertyPaymentDetail.setCreate_at(rs.getDate("create_at"));
        propertyPaymentDetail.setCreate_by(rs.getLong("create_by"));
        propertyPaymentDetail.setCreate_name(rs.getString("user_name"));



        return propertyPaymentDetail;
	}
}
