package com.carfinance.module.login.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.login.domain.UserRowMapper;



@Repository
public class LoginDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(LoginDao.class);

	public User getUserByName(String loginName) {
		String sql = "select * from users t WHERE t.login_name = ? and status = 1";
		Object[] o = new Object[] { loginName };
		logger.info(sql.replaceAll("\\?", "{}"), o);
		try {
			return this.getJdbcTemplate().queryForObject(sql, o, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.info("database is null for query.");
			return null;
		}
	}
}
