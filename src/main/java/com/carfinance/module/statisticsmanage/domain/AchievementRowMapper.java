package com.carfinance.module.statisticsmanage.domain;

import com.carfinance.module.vehicleservicemanage.domain.PropertyContraceInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AchievementRowMapper implements RowMapper<Achievement>{
	public Achievement mapRow(ResultSet rs, int arg1) throws SQLException {
        Achievement achievement=new Achievement();

        achievement.setEmployee_id(rs.getString("employee_id"));
        achievement.setEmployee_name(rs.getString("employee_name"));
        achievement.setTotal_actually(rs.getDouble("total_actually"));
        return achievement;
	}
}
