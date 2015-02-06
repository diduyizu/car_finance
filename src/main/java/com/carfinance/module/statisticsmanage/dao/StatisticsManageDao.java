package com.carfinance.module.statisticsmanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class StatisticsManageDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(StatisticsManageDao.class);

    @Autowired
    private CommonDao commonDao;

}
