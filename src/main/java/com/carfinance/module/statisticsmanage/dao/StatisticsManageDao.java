package com.carfinance.module.statisticsmanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.statisticsmanage.domain.Achievement;
import com.carfinance.module.statisticsmanage.domain.AchievementRowMapper;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInfoRowMapper;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfoRowMapper;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceVehsInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceVehsInfoRowMapper;
import org.apache.commons.lang3.StringUtils;
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

//    public long getContraceCount() {
//        String sql = "select count(1) from vehicle_contrace where status = 6";
//        Object[] o = new Object[]{};
//        logger.info(sql.replaceAll("\\?", "{}"), o);
//        return this.getJdbcTemplate().queryForLong(sql, o);
//    }
//
//    public List<VehicleContraceInfo> getCountraceList(int start , int size) {
//        String sql = "select * from vehicle_contrace where status = 6 order by id desc limit ?,?";
//        Object[] o = new Object[]{ start , size };
//        logger.info(sql.replaceAll("\\?", "{}"), o);
//        return this.getJdbcTemplate().query(sql, o, new VehicleContraceInfoRowMapper());
//    }

    public long getVehicleCount(String vehicle_model , String license_plate) {
        String sql = "select count(1) from (select count(1) from vehicle_contrace_vehs where 1 = 1 ";
        List<Object> param = new ArrayList<Object>();

        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model.trim()+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate like ? ";
            param.add("%"+license_plate.toUpperCase().trim()+"%");
        }
        sql = sql + " group by vehicle_id) as name ";

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取某一门店下所有车辆信息
     * @return
     */
    public List<VehicleContraceVehsInfo> getVehicleList(String vehicle_model , String license_plate , int start , int size) {
        String sql = "select a.*,b.actually_price total_actually from vehicle_contrace_vehs a , (select vehicle_id,sum(actually_price) actually_price from vehicle_contrace_vehs where 1=1 ";
        List<Object> param = new ArrayList<Object>();

        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model.trim()+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate like ? ";
            param.add("%"+license_plate.toUpperCase().trim()+"%");
        }
        sql = sql + " group by vehicle_id order by vehicle_id) b where a.vehicle_id = b.vehicle_id order by id desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleContraceVehsInfoRowMapper());
    }


    /**
     * 获取门店、业务员的合同收入
     * @param org_id
     * @param employee_id
     * @return
     */
    public long getOrgEmployeeCount(long org_id , String employee_id) {
        String sql = "select count(1) from (select distinct employee_id from vehicle_contrace where 1 = 1 ";
        List<Object> param = new ArrayList<Object>();

        if(org_id != 0) {
            sql = sql + " and org_id = ? ";
            param.add(org_id);
        }
        if(!StringUtils.isBlank(employee_id)) {
            sql = sql + " and employee_id = ? ";
            param.add(employee_id);
        }
        sql = sql + " ) as name ";

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<Achievement> getOrgEmployeeList(long org_id , String employee_id , int start , int size) {
        String sql = "select employee_id , employee_name , sum(actual_price) total_actually  from vehicle_contrace  where 1=1 ";
        List<Object> param = new ArrayList<Object>();

        if(org_id != 0) {
            sql = sql + " and org_id = ? ";
            param.add(org_id);
        }
        if(!StringUtils.isBlank(employee_id)) {
            sql = sql + " and employee_id = ? ";
            param.add(employee_id);
        }
        sql = sql + " group by employee_id order by total_actually desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new AchievementRowMapper());
    }

}
