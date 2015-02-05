package com.carfinance.module.common.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRowMapper implements RowMapper<City>{
	public City mapRow(ResultSet rs, int arg1) throws SQLException {
        City city=new City();

        city.setCity_id(rs.getLong("city_id"));
        city.setCity_name(rs.getString("city_name"));
        city.setProvince_id(rs.getLong("province_id"));
		
		return city;
	}
}
