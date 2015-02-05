package com.carfinance.module.common.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country>{
	public Country mapRow(ResultSet rs, int arg1) throws SQLException {
        Country country=new Country();

        country.setCountry_id(rs.getLong("country_id"));
        country.setCountry_name(rs.getString("country_name"));
        country.setCity_id(rs.getLong("city_id"));
		
		return country;
	}
}
