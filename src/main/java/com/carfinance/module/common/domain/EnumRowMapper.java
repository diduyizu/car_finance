package com.carfinance.module.common.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EnumRowMapper implements RowMapper<Enum>{
	public Enum mapRow(ResultSet rs, int arg1) throws SQLException {
		Enum enu = new Enum();
		
        enu.setFiel_name(rs.getString("fiel_name"));
        enu.setEnum_value(rs.getInt("enum_value"));
        enu.setEnum_desc(rs.getString("enum_desc"));
        enu.setEnum_memo(rs.getString("enum_memo"));
		
		return enu; 
	}
}
