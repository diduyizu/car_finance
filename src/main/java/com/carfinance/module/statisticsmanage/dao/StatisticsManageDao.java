package com.carfinance.module.statisticsmanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInfoRowMapper;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfoRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class StatisticsManageDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(StatisticsManageDao.class);

    @Autowired
    private CommonDao commonDao;

    public long getContraceCount() {
        String sql = "select count(1) from vehicle_contrace where status = 6";
        Object[] o = new Object[]{};
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<VehicleContraceInfo> getCountraceList(int start , int size) {
        String sql = "select * from vehicle_contrace where status = 6 order by id desc limit ?,?";
        Object[] o = new Object[]{ start , size };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleContraceInfoRowMapper());
    }

}
