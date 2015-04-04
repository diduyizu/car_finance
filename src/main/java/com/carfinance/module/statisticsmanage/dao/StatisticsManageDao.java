package com.carfinance.module.statisticsmanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.statisticsmanage.domain.Achievement;
import com.carfinance.module.statisticsmanage.domain.AchievementRowMapper;
import com.carfinance.module.statisticsmanage.domain.VehicleIncom;
import com.carfinance.module.statisticsmanage.domain.VehicleIncomRowMapper;
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
import java.util.Date;
import java.util.List;


@Repository
public class StatisticsManageDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(StatisticsManageDao.class);

    @Autowired
    private CommonDao commonDao;

    public long getVehicleCount(String vehicle_model , String license_plate , Date begin_date , Date end_date) {
        String sql = "select count(1) from (select distinct vehicle_id from payment_statistics where 1 = 1 ";
        List<Object> param = new ArrayList<Object>();

        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model.trim()+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate like ? ";
            param.add("%"+license_plate.toUpperCase().trim()+"%");
        }
        if(begin_date != null) {
            sql = sql + " and return_at > ? ";
            param.add(begin_date);
        }
        if(end_date != null) {
            sql = sql + " and return_at < ? ";
            param.add(end_date);
        }
        sql = sql + " ) as name ";

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
    public List<VehicleIncom> getVehicleList(String vehicle_model , String license_plate , Date begin_date , Date end_date , int start , int size) {
        String sql = "select license_plate , model , sum(over_price) over_price , sum(actually_price) actually_price from payment_statistics where 1=1 ";
        List<Object> param = new ArrayList<Object>();

        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model.trim()+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate like ? ";
            param.add("%"+license_plate.toUpperCase().trim()+"%");
        }
        if(begin_date != null) {
            sql = sql + " and return_at > ? ";
            param.add(begin_date);
        }
        if(end_date != null) {
            sql = sql + " and return_at < ? ";
            param.add(end_date);
        }
        sql = sql + " group by vehicle_id order by actually_price desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleIncomRowMapper());
    }


    /**
     * 获取门店、业务员的合同收入
     * @param org_id
     * @param employee_id
     * @return
     */
    public long getOrgEmployeeCount(long org_id , String employee_id , Date begin_date , Date end_date) {
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
        if(begin_date != null) {
            sql = sql + " and create_at > ? ";
            param.add(begin_date);
        }
        if(end_date != null) {
            sql = sql + " and create_at < ? ";
            param.add(end_date);
        }
        sql = sql + " ) as name ";

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<Achievement> getOrgEmployeeList(long org_id , String employee_id , Date begin_date , Date end_date , int start , int size) {
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
        if(begin_date != null) {
            sql = sql + " and create_at > ? ";
            param.add(begin_date);
        }
        if(end_date != null) {
            sql = sql + " and create_at < ? ";
            param.add(end_date);
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


    /**
     *
     * 报表，日、周、月、季、年
     * @param vehicle_model
     * @param license_plate
     * @param begin_date
     * @param end_date
     * @return
     */
    public long getReoprtCount(String vehicle_model , String license_plate , Date begin_date , Date end_date) {
        String sql = "select count(1) from (select distinct vehicle_id from payment_statistics where 1 = 1 ";
        List<Object> param = new ArrayList<Object>();

        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model.trim()+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate like ? ";
            param.add("%"+license_plate.toUpperCase().trim()+"%");
        }
        if(begin_date != null) {
            sql = sql + " and return_at > ? ";
            param.add(begin_date);
        }
        if(end_date != null) {
            sql = sql + " and return_at < ? ";
            param.add(end_date);
        }
        sql = sql + " ) as name ";

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<VehicleIncom> getReoprtList(String vehicle_model , String license_plate , Date begin_date , Date end_date , int start , int size) {
        String sql = "select license_plate , model , sum(over_price) over_price , sum(actually_price) actually_price from payment_statistics where 1=1 ";
        List<Object> param = new ArrayList<Object>();

        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model.trim()+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate like ? ";
            param.add("%"+license_plate.toUpperCase().trim()+"%");
        }
        if(begin_date != null) {
            sql = sql + " and return_at > ? ";
            param.add(begin_date);
        }
        if(end_date != null) {
            sql = sql + " and return_at < ? ";
            param.add(end_date);
        }
        sql = sql + " group by vehicle_id order by actually_price desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleIncomRowMapper());
    }
}
