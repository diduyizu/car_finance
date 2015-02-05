package com.carfinance.module.init.dao;

import java.util.List;
import java.util.Map;

import com.carfinance.module.common.domain.*;
import com.carfinance.module.common.domain.Enum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
@Repository
public class InitDao extends BaseJdbcDaoImpl{

	final Logger logger = LoggerFactory.getLogger(InitDao.class);

	public List<Role> initRole(){
		String sql="select * from sys_roles where status = 1 order by role_id";
		return this.getJdbcTemplate().query(sql,new RoleRowMapper());
	}

	public List<Menu> initMenu(){
		String sql="select * from sys_menu where status = 1 order by menu_id";
		return this.getJdbcTemplate().query(sql,new MenuRowMapper());
	}

	public List<Enum> initEnum(){
		String sql = "select * from sys_enum";
		return this.getJdbcTemplate().query(sql,new EnumRowMapper());
	}
}
