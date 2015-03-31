package com.carfinance.module.vehiclemanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.vehiclemanage.domain.*;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceVehsInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceVehsInfoRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public long getVehicleCount(long original_org , String current_city , String brand , String vehicle_model , String license_plate , String gps , String km_begin , String km_end , String lease_status , String color) {
        String sql = "select count(1) from vehicle_info where original_org = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        if(brand != null && !"".equals(brand.trim())) {
            sql = sql + " and brand like ? ";
            param.add("%"+brand.trim()+"%");
        }
        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model.trim()+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate like ? ";
            param.add("%"+license_plate.toUpperCase().trim()+"%");
        }
        if(gps != null && !"".equals(gps.trim())) {
            sql = sql + " and gps = ? ";
            param.add(gps);
        }
//        if(km_begin != null && !"".equals(km_begin.trim()) && km_end != null && !"".equals(km_end)) {
//            sql = sql + " and km between ? and ? ";
//            param.add(km_begin);
//            param.add(km_end);
//        } else {
//            if(km_begin != null && !"".equals(km_begin.trim())) {
//                sql = sql + " and km > ? ";
//                param.add(km_begin);
//            }
//            if(km_end != null && !"".equals(km_end)) {
//                sql = sql + " and km < ? ";
//                param.add(km_end);
//            }
//        }
        if(lease_status != null && !"".equals(lease_status.trim())) {
            sql = sql + " and lease_status = ? ";
            param.add(lease_status.trim());
        }
        if(color != null && !"".equals(color.trim())) {
            sql = sql + " and color like ? ";
            param.add("%"+color.trim()+"%");
        }

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
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
    public List<VehicleInfo> getVehicleList(long original_org , String current_city , String brand , String vehicle_model , String license_plate , String gps , String km_begin , String km_end , String lease_status , String color ,  int start , int size) {
        String sql = "select * from vehicle_info where original_org = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        if(brand != null && !"".equals(brand.trim())) {
            sql = sql + " and brand like ? ";
            param.add("%"+brand.trim()+"%");
        }
        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model.trim()+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate like ? ";
            param.add("%"+license_plate.toUpperCase().trim()+"%");
        }
        if(gps != null && !"".equals(gps.trim())) {
            sql = sql + " and gps = ? ";
            param.add(gps);
        }
//        if(km_begin != null && !"".equals(km_begin.trim()) && km_end != null && !"".equals(km_end)) {
//            sql = sql + " and km between ? and ? ";
//            param.add(km_begin);
//            param.add(km_end);
//        } else {
//            if(km_begin != null && !"".equals(km_begin.trim())) {
//                sql = sql + " and km > ? ";
//                param.add(km_begin);
//            }
//            if(km_end != null && !"".equals(km_end)) {
//                sql = sql + " and km < ? ";
//                param.add(km_end);
//            }
//        }
        if(lease_status != null && !"".equals(lease_status.trim())) {
            sql = sql + " and lease_status = ? ";
            param.add(lease_status.trim());
        }
        if(color != null && !"".equals(color.trim())) {
            sql = sql + " and color like ? ";
            param.add("%"+color.trim()+"%");
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
     * 某个门店,或者某品牌下车辆总数
     * @param brand
     * @return
     */
    public long getCurrentShopVehicleCount(long current_shop , String current_city , String brand , String vehicle_model , String license_plate , String gps , String km_begin , String km_end , String lease_status , String color) {
        String sql = "select count(1) from vehicle_info where current_shop = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(current_shop);

        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        if(brand != null && !"".equals(brand.trim())) {
            sql = sql + " and brand like ? ";
            param.add("%"+brand.trim()+"%");
        }
        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model.trim()+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate like ? ";
            param.add("%"+license_plate.toUpperCase().trim()+"%");
        }
        if(gps != null && !"".equals(gps.trim())) {
            sql = sql + " and gps = ? ";
            param.add(gps);
        }
//        if(km_begin != null && !"".equals(km_begin.trim()) && km_end != null && !"".equals(km_end)) {
//            sql = sql + " and km between ? and ? ";
//            param.add(km_begin);
//            param.add(km_end);
//        } else {
//            if(km_begin != null && !"".equals(km_begin.trim())) {
//                sql = sql + " and km > ? ";
//                param.add(km_begin);
//            }
//            if(km_end != null && !"".equals(km_end)) {
//                sql = sql + " and km < ? ";
//                param.add(km_end);
//            }
//        }
        if(lease_status != null && !"".equals(lease_status.trim())) {
            sql = sql + " and lease_status = ? ";
            param.add(lease_status.trim());
        }
        if(color != null && !"".equals(color.trim())) {
            sql = sql + " and color like ? ";
            param.add("%"+color.trim()+"%");
        }

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取某一门店下所有车辆信息
     * @param start
     * @param size
     * @return
     */
    public List<VehicleInfo> getCurrentShopVehicleList(long current_shop , String current_city , String brand , String vehicle_model , String license_plate , String gps , String km_begin , String km_end , String lease_status , String color ,  int start , int size) {
        String sql = "select * from vehicle_info where current_shop = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(current_shop);

        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        if(brand != null && !"".equals(brand.trim())) {
            sql = sql + " and brand like ? ";
            param.add("%"+brand.trim()+"%");
        }
        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model.trim()+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate like ? ";
            param.add("%"+license_plate.toUpperCase().trim()+"%");
        }
        if(gps != null && !"".equals(gps.trim())) {
            sql = sql + " and gps = ? ";
            param.add(gps);
        }
//        if(km_begin != null && !"".equals(km_begin.trim()) && km_end != null && !"".equals(km_end)) {
//            sql = sql + " and km between ? and ? ";
//            param.add(km_begin);
//            param.add(km_end);
//        } else {
//            if(km_begin != null && !"".equals(km_begin.trim())) {
//                sql = sql + " and km > ? ";
//                param.add(km_begin);
//            }
//            if(km_end != null && !"".equals(km_end)) {
//                sql = sql + " and km < ? ";
//                param.add(km_end);
//            }
//        }
        if(lease_status != null && !"".equals(lease_status.trim())) {
            sql = sql + " and lease_status = ? ";
            param.add(lease_status.trim());
        }
        if(color != null && !"".equals(color.trim())) {
            sql = sql + " and color like ? ";
            param.add("%"+color.trim()+"%");
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
                          Date business_insurance_expire_at , String remark , long create_by , long original_org ,
                          long km , String gps , long current_city , long current_shop , String lease_status , String peccancy_status , long next_main_km ,
                          String financing_rent_company , double financing_rent_price , double bail , double monthly_payment ,
                          String etc , double etc_money , int oil_percent , double daily_price) {
        String sql = "insert into vehicle_info(archive_no , inventory_no , brand , model , color , carframe_no , engine_no , registry_certificate , " +
                "certificate_direction , loan_bank , consistency_cer , check_list , duty_paid_proof , record , buy_at , supplier , " +
                "license_plate , card_at , limited_at , guide_price , vehicle_price , vehicle_tax , insurance_company , strong_insurance , " +
                "vehicle_vessel_tax , strong_insurance_expire_at , business_insurance , business_insurance_expire_at , remark , create_by , " +
                "original_org , km , maintian_on_km , gps , current_city , current_shop , lease_status , peccancy_status , next_main_km , " +
                "financing_rent_company , financing_rent_price , bail , monthly_payment , etc , etc_money , oil_percent , daily_price) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] o = new Object[] { archive_no , inventory_no , brand , model , color , carframe_no , engine_no , registry_certificate ,
                certificate_direction , loan_bank , consistency_cer , check_list , duty_paid_proof , record , buy_at , supplier ,
                license_plate.toUpperCase() , card_at , limited_at , guide_price , vehicle_price , vehicle_tax , insurance_company , strong_insurance ,
                vehicle_vessel_tax , strong_insurance_expire_at , business_insurance , business_insurance_expire_at , remark , create_by , original_org ,
                km , next_main_km-km , gps , current_city , current_shop , lease_status , peccancy_status , next_main_km ,
                financing_rent_company , financing_rent_price , bail , monthly_payment ,
                etc , etc_money , oil_percent , daily_price};
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql , o);
    }



    public int modifyVehicle(long vehicle_id , String archive_no , String inventory_no , String brand , String model , String color , String carframe_no , String engine_no ,
                          String registry_certificate , String certificate_direction , String loan_bank , String consistency_cer , String check_list ,
                          String duty_paid_proof , String record , Date buy_at , String supplier , String license_plate , Date card_at ,
                          Date limited_at , double guide_price , double vehicle_price , double vehicle_tax , String insurance_company ,
                          double strong_insurance , double vehicle_vessel_tax , Date strong_insurance_expire_at , double business_insurance ,
                          Date business_insurance_expire_at , String remark , long update_by , long original_org ,
                          long km , String gps , long current_city , long current_shop , String lease_status , String peccancy_status , long next_main_km ,
                          String financing_rent_company , double financing_rent_price , double bail , double monthly_payment ,
                          String etc , double etc_money , int oil_percent , double daily_price) {
        String sql = "update vehicle_info set archive_no = ? , inventory_no = ? , brand = ? , model = ? , color = ? , carframe_no = ? , engine_no = ? , " +
                "registry_certificate = ? , certificate_direction = ? , loan_bank = ? , consistency_cer = ? , check_list = ? , duty_paid_proof = ? , " +
                "record = ? , buy_at = ? , supplier = ? , license_plate = ? , card_at = ? , limited_at = ? , guide_price = ? , vehicle_price = ? , " +
                "vehicle_tax = ? , insurance_company = ? , strong_insurance = ? , vehicle_vessel_tax = ? , strong_insurance_expire_at = ? , " +
                "business_insurance = ? , business_insurance_expire_at = ? , remark = ? , original_org = ? , km = ? , maintian_on_km = ? , gps = ? , " +
                "current_city = ? , current_shop = ? , lease_status = ? , peccancy_status = ? , next_main_km = ? , financing_rent_company = ? , " +
                "financing_rent_price = ? , bail = ? , monthly_payment = ? , update_at = now() , update_by = ? , " +
                "etc = ? , etc_money = ? , oil_percent = ? , daily_price = ? where id = ? ";
        Object[] o = new Object[] { archive_no , inventory_no , brand , model , color , carframe_no , engine_no , registry_certificate ,
                certificate_direction , loan_bank , consistency_cer , check_list , duty_paid_proof , record , buy_at , supplier ,
                license_plate.toUpperCase() , card_at , limited_at , guide_price , vehicle_price , vehicle_tax , insurance_company , strong_insurance ,
                vehicle_vessel_tax , strong_insurance_expire_at , business_insurance , business_insurance_expire_at , remark , original_org ,
                km , next_main_km-km , gps , current_city , current_shop , lease_status , peccancy_status , next_main_km ,
                financing_rent_company , financing_rent_price , bail , monthly_payment , update_by , etc , etc_money , oil_percent , daily_price , vehicle_id};
        logger.info(sql.replaceAll("\\?", "{}"), o);
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
        Object[] o = new Object[] { carframe_no , engine_no , license_plate.toUpperCase() , insurance_company , strong_insurance ,
                vehicle_vessel_tax , strong_insurance_expire_at , business_insurance , business_insurance_expire_at , remark , create_by };
        logger.info(sql.replaceAll("\\?", "{}"), o);
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
                carframe_no , engine_no , license_plate.toUpperCase()};
        logger.info(sql.replaceAll("\\?", "{}"), o);
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
    public int addVehiclePeccancy(String carframe_no , String engine_no , String license_plate , Date peccancy_at , String peccancy_place ,
                                  String peccancy_reason , long score , int status , long create_by , double peccancy_price , String arbitration ,
                                  String employee_id , String employee_name , long customer_id , String customer_name) {
        String sql = "insert into vehicle_peccancy(carframe_no , engine_no , license_plate , peccancy_at , " +
                "peccancy_place , peccancy_reason , score , status , create_by , peccancy_price , arbitration , " +
                "employee_id , employee_name , customer_id , customer_name) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] o = new Object[] { carframe_no , engine_no , license_plate.toUpperCase() , peccancy_at , peccancy_place ,
                peccancy_reason , score , status , create_by , peccancy_price , arbitration ,
                employee_id , employee_name , customer_id , customer_name };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 获取车辆保险到期需要提醒总数
     * @param original_org
     * @param remind_day
     * @return
     */
    public long getInsuranceRemindCount(long original_org , String carframe_no , String engine_no , String current_city , String license_plate , int remind_day) {
//        String sql = "select count(1) from vehicle_info where original_org = ? and TO_DAYS(NOW()) - TO_DAYS(strong_insurance_expire_at) <= ?";
//        Object[] o = new Object[] { original_org , remind_day };
        String sql = "select count(1) from vehicle_info where original_org = ? ";
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
        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate = ? ";
            param.add(license_plate.toUpperCase());
        }
        sql = sql + " and TO_DAYS(strong_insurance_expire_at) - TO_DAYS(NOW()) <= ?";
        param.add(remind_day);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取车辆保险到期提醒列表
     * @param original_org
     * @param carframe_no
     * @param engine_no
     * @param license_plate
     * @param start
     * @param size
     * @return
     */
    public List<VehicleInfo> getVehicleInsuranceList(long original_org , String carframe_no , String engine_no , String license_plate , String current_city , int remind_day , int start , int size) {
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
            param.add(license_plate.toUpperCase());
        }
        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        sql = sql + " and TO_DAYS(strong_insurance_expire_at) - TO_DAYS(NOW()) <= ? order by strong_insurance_expire_at limit ?,?";
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

    /**
     * 获取某组织下，某一车辆状态的车辆总数
     * @param original_org
     * @param lease_status
     * @return
     */
    public long getVehicleLeaseStatusCount(long original_org , String lease_status) {
        String sql;
        Object[] o;
        if(lease_status == null || "".equals(lease_status)) {
            sql = "select count(1) from vehicle_info where original_org = ?";
            o = new Object[] { original_org };
        } else {
            sql = "select count(1) from vehicle_info where original_org = ? and lease_status = ?";
            o = new Object[] { original_org , lease_status };
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取某组织下，某一车辆状态的车辆列表
     * @param original_org
     * @param lease_status
     * @param start
     * @param size
     * @return
     */
    public List<VehicleInfo> getVehicleLeaseStatusList(long original_org , String lease_status , int start , int size) {
        String sql;
        Object[] o;
        if(lease_status == null || "".equals(lease_status)) {
            sql = "select * from vehicle_info where original_org = ? order by id desc limit ?,?";
            o = new Object[] { original_org , start , size };
        } else {
            sql = "select * from vehicle_info where original_org = ? and lease_status = ? order by id desc limit ?,?";
            o = new Object[] { original_org , lease_status , start , size };
        }
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleInfoRowMapper());
    }

    /**
     * 查询车辆未处理违章数量
     * @param carframe_no
     * @param engine_no
     * @param license_plate
     * @return
     */
    public long getVehiclePeccancyCount(String carframe_no , String engine_no , String license_plate) {
        String sql = "select count(1) from vehicle_peccancy where carframe_no = ? and engine_no = ? and license_plate = ? and status = 0 ";
        Object[] o = new Object[] { carframe_no , engine_no , license_plate.toUpperCase() };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 更新车辆主表违章记录状态
     * @return
     */
    public int updateVehiclePeccancyStatus(String carframe_no , String engine_no , String license_plate , int peccancy_status) {
        String sql = "update vehicle_info set peccancy_status = ? where carframe_no = ? and engine_no = ? and license_plate = ?  ";
        Object[] o = new Object[] { peccancy_status , carframe_no , engine_no , license_plate.toUpperCase() };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    public long getVehiclePeccancyRemindCount(long original_org , String current_city , String license_plate) {
        String sql = "select count(1) from vehicle_info where original_org = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate = ? ";
            param.add(license_plate.toUpperCase());
        }
        sql = sql + " and peccancy_status = 1 ";
         Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取车辆违章提醒列表
     * @param original_org
     * @param license_plate
     * @param start
     * @param size
     * @return
     */
    public List<VehicleInfo> getVehiclePeccancyRemindList(long original_org ,  String license_plate , String current_city , int start , int size) {
        String sql = "select * from vehicle_info where original_org = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate = ? ";
            param.add(license_plate.toUpperCase());
        }
        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        sql = sql + " and peccancy_status = 1 order by id desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleInfoRowMapper());
    }

    public VehiclePeccancy getVehiclePeccancy(long id) {
        try{
            String sql = "select * from vehicle_peccancy where id = ? ";
            Object[] o = new Object[] { id };
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return this.getJdbcTemplate().queryForObject(sql, o, new VehiclePeccancyRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int peccancyDoHandle(long id , String carframe_no , String engine_no , String license_plate , Date peccancy_at , String peccancy_place ,
                                  String peccancy_reason , long score , int status , double peccancy_price , String arbitration , long update_by) {

        String sql = "update vehicle_peccancy set peccancy_at = ? , peccancy_place = ? , peccancy_reason = ? , score = ? , peccancy_price = ? , arbitration = ? , status = ? , update_by = ? " +
                "where id = ? and carframe_no = ? and engine_no = ? and license_plate = ? ";
        Object[] o = new Object[] { peccancy_at , peccancy_place , peccancy_reason , score , peccancy_price , arbitration , status , update_by , id , carframe_no , engine_no , license_plate.toUpperCase() };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }


    public long getVehicleMaintainRemindCount(long original_org , String current_city , String license_plate , long remind_km) {
        String sql = "select count(1) from vehicle_info where original_org = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate = ? ";
            param.add(license_plate.toUpperCase());
        }
        sql = sql + " and maintian_on_km < ? ";
        param.add(remind_km);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取车辆违章提醒列表
     * @param original_org
     * @param license_plate
     * @param start
     * @param size
     * @return
     */
    public List<VehicleInfo> getVehicleMaintainRemindList(long original_org ,  String license_plate , String current_city , long remind_km , int start , int size) {
        String sql = "select * from vehicle_info where original_org = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate = ? ";
            param.add(license_plate.toUpperCase());
        }
        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        sql = sql + " and maintian_on_km < ? order by id desc limit ?,?";
        param.add(remind_km);
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleInfoRowMapper());
    }

    public int addVehicleMaintain(String carframe_no , String engine_no , String license_plate , Date maintain_at_date , String maintain_content ,
                                   double maintain_price , long current_km , long next_maintain_km ,
                                   long user_id , String user_name , long create_by) {
        String sql = "insert into vehicle_maintail_record(carframe_no , engine_no , license_plate , maintain_date , maintain_content , maintain_price , " +
                "current_km , next_maintain_km , user_id , user_name , create_by) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";
        Object[] o = new Object[] { carframe_no , engine_no , license_plate.toUpperCase() , maintain_at_date , maintain_content , maintain_price ,
                current_km , next_maintain_km , user_id , user_name , create_by };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    public VehicleInfo getVehicleInfoByid(long id) {
        try{
            String sql = "select * from vehicle_info where id = ?";
            Object[] o = new Object[] { id };
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return this.getJdbcTemplate().queryForObject(sql, o, new VehicleInfoRowMapper());
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int updateVehicleRemind(String carframe_no , String engine_no , String license_plate , long current_km , long next_maintain_km , long maintian_on_km) {
        String sql = "update vehicle_info set km = ? , next_main_km = ? , maintian_on_km = ? where carframe_no = ? and engine_no = ? and license_plate = ?  ";
        Object[] o = new Object[] { current_km , next_maintain_km , maintian_on_km , carframe_no , engine_no , license_plate.toUpperCase() };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    public List<VehicleContraceVehsInfo> getContraceVehicles(String license_plate , String peccancy_at) {
        String sql = "select * from vehicle_contrace_vehs where create_at < date_format(?,'%Y-%m-%d %H:%i') and date_format(?,'%Y-%m-%d %H:%i') < return_time and license_plate = ? ";
        Object[] o = new Object[] { peccancy_at , peccancy_at , license_plate.toUpperCase() };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o , new VehicleContraceVehsInfoRowMapper());
    }

    public long getVehicleMaintainDetail(String carframe_no) {
        String sql = "select count(1) from vehicle_maintail_record where carframe_no = ?";
        Object[] o = new Object[] { carframe_no };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<VehicleMaintail> getVechicleMaintainDetaiList(String carframe_no , int start , int size) {
        String sql = "select * from vehicle_maintail_record where carframe_no = ? limit ? , ?";
        Object[] o = new Object[] { carframe_no , start , size };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleMaintailRowMapper());
    }
}
