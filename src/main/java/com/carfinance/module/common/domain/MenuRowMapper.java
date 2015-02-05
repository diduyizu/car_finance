package com.carfinance.module.common.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MenuRowMapper implements RowMapper<Menu>{
	public Menu mapRow(ResultSet rs, int arg1) throws SQLException {
		Menu menu = new Menu();
		
        menu.setMenu_id(rs.getLong("menu_id"));
        menu.setMenu_name(rs.getString("menu_name"));
        menu.setMenu_url(rs.getString("menu_url"));
        menu.setPid(rs.getLong("pid"));
        menu.setLevel(rs.getLong("level"));
        menu.setMenu_desc(rs.getString("menu_desc"));
        menu.setType(rs.getString("type"));
        menu.setCss(rs.getString("css"));
        menu.setStatus(rs.getString("status"));
		
		return menu; 
	}
}
