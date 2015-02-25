package com.carfinance.module.vehiclemanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInfoRowMapper;
import com.carfinance.module.vehiclemanage.domain.VehicleInsurance;
import com.carfinance.module.vehiclemanage.domain.VehicleInsuranceRowMapper;
import com.carfinance.module.vehiclemanage.domain.VehiclePeccancy;
import com.carfinance.module.vehiclemanage.domain.VehiclePeccancyRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class VehicleManageDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(VehicleManageDao.class);

    @Autowired
    private CommonDao commonDao;

    /**
     * 某个门店,或者某品牌下车辆总数
     * @param original_org
     * @param brand
     * @return
     */
    public long getVehicleCount(long original_org , String brand) {
        Object[] o;
        String sql;
        if(brand == null) {
            sql = "select count(1) from vehicle_info where original_org = ?";
            o = new Object[] { original_org };
        } else {
            sql = "select count(1) from vehicle_info where original_org = ? and brand = ? ";
            o = new Object[] { original_org , brand };
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取某一门店下所有车辆信息
     * @param original_org
     * @param start
     * @param size
     * @return
     */
    public List<VehicleInfo> getVehicleList(long original_org , String brand , String carframe_no , String engine_no , String license_plate ,  int start , int size) {
        String sql = "select * from vehicle_info where original_org = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

        if(brand != null && !"".equals(brand.trim())) {
            sql = sql + " and brand = ? ";
            param.add(brand);
        }
        if(carframe_no != null && !"".equals(carframe_no.trim())) {
            sql = sql + " and carframe_no = ? ";
            param.add(carframe_no);
        }
        if(engine_no != null && !"".equals(engine_no.trim())) {
            sql = sql + " and engine_no = ? ";
            param.add(engine_no);
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate = ? ";
            param.add(license_plate);
        }
        sql = sql + " order by id desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleInfoRowMapper());
    }

    /**
     * 新增车辆入库
     * @param archive_no
     * @param inventory_no
     * @param brand
     * @param model
     * @param color
     * @param carframe_no
     * @param engine_no
     * @param registry_certificate
     * @param certificate_direction
     * @param loan_bank
     * @param consistency_cer
     * @param check_list
     * @param duty_paid_proof
     * @param record
     * @param buy_at
     * @param supplier
     * @param license_plate
     * @param card_at
     * @param limited_at
     * @param guide_price
     * @param vehicle_price
     * @param vehicle_tax
     * @param insurance_company
     * @param strong_insurance
     * @param vehicle_vessel_tax
     * @param strong_insurance_expire_at
     * @param business_insurance
     * @param business_insurance_expire_at
     * @param remark
     * @param create_by
     * @param original_org
     * @return
     */
    public int addVehicle(String archive_no , String inventory_no , String brand , String model , String color , String carframe_no , String engine_no ,
                          String registry_certificate , String certificate_direction , String loan_bank , String consistency_cer , String check_list ,
                          String duty_paid_proof , String record , Date buy_at , String supplier , String license_plate , Date card_at ,
                          Date limited_at , double guide_price , double vehicle_price , double vehicle_tax , String insurance_company ,
                          double strong_insurance , double vehicle_vessel_tax , Date strong_insurance_expire_at , double business_insurance ,
                          Date business_insurance_expire_at , String remark , long create_by , long original_org) {

        String sql = "insert into vehicle_info(archive_no , inventory_no , brand , model , color , carframe_no , engine_no , registry_certificate , " +
                "certificate_direction , loan_bank , consistency_cer , check_list , duty_paid_proof , record , buy_at , supplier , " +
                "license_plate , card_at , limited_at , guide_price , vehicle_price , vehicle_tax , insurance_company , strong_insurance , " +
                "vehicle_vessel_tax , strong_insurance_expire_at , business_insurance , business_insurance_expire_at , remark , create_by , original_org) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] o = new Object[] { archive_no , inventory_no , brand , model , color , carframe_no , engine_no , registry_certificate ,
                certificate_direction , loan_bank , consistency_cer , check_list , duty_paid_proof , record , buy_at , supplier ,
                license_plate , card_at , limited_at , guide_price , vehicle_price , vehicle_tax , insurance_company , strong_insurance ,
                vehicle_vessel_tax , strong_insurance_expire_at , business_insurance , business_insurance_expire_at , remark , create_by , original_org };
        return this.getJdbcTemplate().update(sql , o);
    }

    /**
     * 某车辆等保险录入总数
     * @param carframe_no
     * @return
     */
    public long getVehicleInsuranceCount(String carframe_no) {
        String sql = "select count(1) from vehicle_insurance where carframe_no = ?";
        Object[] o = new Object[] { carframe_no };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取车辆保险详细
     * @param carframe_no
     * @param start
     * @param size
     * @return
     */
    public List<VehicleInsurance> getVehicleInsuranceList(String carframe_no , int start , int size) {
        String sql = "select * from vehicle_insurance where carframe_no = ? order by id desc limit ?,?";
        Object[] o = new Object[] {carframe_no , start , size};
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleInsuranceRowMapper());
    }

    /**
     * 车辆保险录入
     * @param carframe_no
     * @param engine_no
     * @param license_plate
     * @param insurance_company
     * @param strong_insurance
     * @param vehicle_vessel_tax
     * @param strong_insurance_expire_at
     * @param business_insurance
     * @param business_insurance_expire_at
     * @param remark
     * @param create_by
     * @return
     */
    public int addVehicleInsurance(String carframe_no , String engine_no , String license_plate , String insurance_company ,
                                   double strong_insurance , double vehicle_vessel_tax , Date strong_insurance_expire_at ,
                                   double business_insurance , Date business_insurance_expire_at , String remark , long create_by) {
        String sql = "insert into vehicle_insurance(carframe_no , engine_no , license_plate , insurance_company , strong_insurance , " +
                "vehicle_vessel_tax , strong_insurance_expire_at , business_insurance , business_insurance_expire_at , remark , create_by) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";
        Object[] o = new Object[] { carframe_no , engine_no , license_plate , insurance_company , strong_insurance ,
                vehicle_vessel_tax , strong_insurance_expire_at , business_insurance , business_insurance_expire_at , remark , create_by };
        return this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 更新汽车主表的保险纪录
     * @param carframe_no
     * @param engine_no
     * @param license_plate
     * @param insurance_company
     * @param strong_insurance
     * @param vehicle_vessel_tax
     * @param strong_insurance_expire_at
     * @param business_insurance
     * @param business_insurance_expire_at
     * @return
     */
    public int updateVehicleInsurance(String carframe_no , String engine_no , String license_plate , String insurance_company ,
                                 double strong_insurance , double vehicle_vessel_tax , Date strong_insurance_expire_at ,
                                 double business_insurance , Date business_insurance_expire_at) {

        String sql = "update vehicle_info t " +
                "set t.insurance_company = ? , strong_insurance = ? , vehicle_vessel_tax = ? , strong_insurance_expire_at = ? , business_insurance = ? , business_insurance_expire_at = ? " +
                "where carframe_no = ? and engine_no = ? and license_plate = ?";
        Object[] o = new Object[] { insurance_company , strong_insurance , vehicle_vessel_tax , strong_insurance_expire_at , business_insurance , business_insurance_expire_at ,
                carframe_no , engine_no , license_plate};
        return this.getJdbcTemplate().update(sql, o);

     }

    /**
     * 获取车辆违章记录总数
     * @param carframe_no
     * @return
     */
    public long getVehiclePeccancyCount(String carframe_no) {
        String sql = "select count(1) from vehicle_peccancy where carframe_no = ?";
        Object[] o = new Object[] { carframe_no };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取车辆违章详细
     * @param carframe_no
     * @param start
     * @param size
     * @return
     */
    public List<VehiclePeccancy> getVehiclePeccancyList(String carframe_no , int start , int size) {
        String sql = "select * from vehicle_peccancy where carframe_no = ? order by id desc limit ?,?";
        Object[] o = new Object[] {carframe_no , start , size};
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehiclePeccancyRowMapper());
    }

    /**
     * 车辆违章录入
     * @param carframe_no
     * @param engine_no
     * @param license_plate
     * @param peccancy_at
     * @param peccancy_place
     * @param peccancy_reason
     * @param score
     * @param status
     * @param create_by
     * @return
     */
    public int addVehiclePeccancy(String carframe_no , String engine_no , String license_plate , Date peccancy_at ,
                                  String peccancy_place , String peccancy_reason , long score , int status , long create_by) {
        String sql = "insert into vehicle_peccancy(carframe_no , engine_no , license_plate , peccancy_at , " +
                "peccancy_place , peccancy_reason , score , status , create_by) " +
                "values (?,?,?,?,?,?,?,?,?)";
        Object[] o = new Object[] { carframe_no , engine_no , license_plate , peccancy_at , peccancy_place ,
                peccancy_reason , score , status , create_by };
        return this.getJdbcTemplate().update(sql , o);
    }

    /**
     * 获取车辆保险到期需要提醒总数
     * @param original_org
     * @param remind_day
     * @return
     */
    public long getInsuranceRemindCount(long original_org , int remind_day) {
        String sql = "select count(1) from vehicle_info where original_org = ? and TO_DAYS(NOW()) - TO_DAYS(strong_insurance_expire_at) <= ?";
        Object[] o = new Object[] { original_org , remind_day };

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取车辆保险到期提醒列表
     * @param original_org
     * @param brand
     * @param carframe_no
     * @param engine_no
     * @param license_plate
     * @param start
     * @param size
     * @return
     */
    public List<VehicleInfo> getVehicleInsuranceList(long original_org , String carframe_no , String engine_no , String license_plate , int remind_day , int start , int size) {
        String sql = "select * from vehicle_info where original_org = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

        if(carframe_no != null && !"".equals(carframe_no.trim())) {
            sql = sql + " and carframe_no = ? ";
            param.add(carframe_no);
        }
        if(engine_no != null && !"".equals(engine_no.trim())) {
            sql = sql + " and engine_no = ? ";
            param.add(engine_no);
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate = ? ";
            param.add(license_plate);
        }
        sql = sql + " and TO_DAYS(NOW()) - TO_DAYS(strong_insurance_expire_at) <= ? order by strong_insurance_expire_at desc limit ?,?";
        param.add(remind_day);
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleInfoRowMapper());
    }
}
