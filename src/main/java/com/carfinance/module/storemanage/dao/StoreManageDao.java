package com.carfinance.module.storemanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.domain.Org;
import com.carfinance.module.common.domain.OrgRowMapper;
import com.carfinance.module.common.domain.Role;
import com.carfinance.module.common.domain.RoleRowMapper;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.login.domain.UserRowMapper;
import com.carfinance.module.peoplemanage.domain.OrgUserRole;
import com.carfinance.module.peoplemanage.domain.OrgUserRoleRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Repository
public class StoreManageDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(StoreManageDao.class);


}
