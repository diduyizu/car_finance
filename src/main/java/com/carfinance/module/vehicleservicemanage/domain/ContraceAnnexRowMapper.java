package com.carfinance.module.vehicleservicemanage.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContraceAnnexRowMapper implements RowMapper<ContraceAnnex>{
	public ContraceAnnex mapRow(ResultSet rs, int arg1) throws SQLException {
        ContraceAnnex contraceAnnex=new ContraceAnnex();

        contraceAnnex.setId(rs.getLong("id"));
        contraceAnnex.setContrace_id(rs.getLong("contrace_id"));
        contraceAnnex.setAnnex_name(rs.getString("annex_name"));
        contraceAnnex.setAnnex_url(rs.getString("annex_url"));
        contraceAnnex.setCreate_at(rs.getDate("create_at"));
        contraceAnnex.setCreate_by(rs.getLong("create_by"));

        return contraceAnnex;
	}
}
