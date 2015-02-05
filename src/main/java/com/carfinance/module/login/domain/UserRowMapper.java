package com.carfinance.module.login.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User>{
	public User mapRow(ResultSet rs, int arg1) throws SQLException
	{
		User user = new User();

        user.setUser_id(rs.getLong("user_id"));
        user.setLogin_name(rs.getString("login_name"));
        user.setLogin_pwd(rs.getString("login_pwd"));
        user.setUser_name(rs.getString("user_name"));
        user.setNick_name(rs.getString("login_name"));
        user.setHead_url(rs.getString("head_url"));
		user.setBirthday(rs.getString("birthday"));
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));

		return user;
	}
}
