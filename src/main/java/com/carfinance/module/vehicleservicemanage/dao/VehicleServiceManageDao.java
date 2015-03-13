package com.carfinance.module.vehicleservicemanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInfoRowMapper;
import com.carfinance.module.vehicleservicemanage.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class VehicleServiceManageDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(VehicleServiceManageDao.class);

    @Autowired
    private CommonDao commonDao;

    public long getOrgReservationCount(long org_id , String customer_name , String dn) {
        String sql = "select count(1) from vehicle_reservation where org_id = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(org_id);

        if(customer_name != null && !"".equals(customer_name.trim())) {
            sql = sql + " and customer_name = ? ";
            param.add(customer_name);
        }
        if(dn != null && !"".equals(dn.trim())) {
            sql = sql + " and customer_dn = ? ";
            param.add(dn);
        }

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<VehicleReservationInfo> getOrgReservationList(long org_id , String customer_name , String dn , int start, int size) {
//        String sql = " order by id desc limit ?,? ";
//        Object[] o = new Object[] { org_id , start , size };
//        logger.info(sql.replaceAll("\\?", "{}"), o);
//        return this.getJdbcTemplate().query(sql, o, new VehicleReservationInfoRowMapper());
        String sql = "select * from vehicle_reservation where org_id = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(org_id);

        if(customer_name != null && !"".equals(customer_name.trim())) {
            sql = sql + " and customer_name = ? ";
            param.add(customer_name);
        }
        if(dn != null && !"".equals(dn.trim())) {
            sql = sql + " and customer_dn = ? ";
            param.add(dn);
        }
        sql = sql + " order by id desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleReservationInfoRowMapper());
    }

    public long getOrgReservationRemindCount(long org_id , int remind_days , String customer_name , String dn) {
//        String sql = "select count(1) from vehicle_reservation where  ";
//        Object[] o = new Object[] { org_id , remind_days };
//        logger.info(sql.replaceAll("\\?", "{}"), o);
//        return this.getJdbcTemplate().queryForLong(sql, o);
        String sql = "select count(1) from vehicle_reservation where org_id = ? and TO_DAYS(use_begin) - TO_DAYS(NOW()) <= ? and status = 0 ";
        List<Object> param = new ArrayList<Object>();
        param.add(org_id);
        param.add(remind_days);

        if(customer_name != null && !"".equals(customer_name.trim())) {
            sql = sql + " and customer_name = ? ";
            param.add(customer_name);
        }
        if(dn != null && !"".equals(dn.trim())) {
            sql = sql + " and customer_dn = ? ";
            param.add(dn);
        }

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);


    }

    public List<VehicleReservationInfo> getOrgReservationRemindList(long org_id , int remind_days , String customer_name , String dn , int start, int size) {
//        String sql = "select * from vehicle_reservation where org_id = ? and TO_DAYS(use_begin) - TO_DAYS(NOW()) <= ? and status = 0 limit ?,? ";
//        Object[] o = new Object[] { org_id , remind_days , start , size };
//        logger.info(sql.replaceAll("\\?", "{}"), o);
//        return this.getJdbcTemplate().query(sql, o, new VehicleReservationInfoRowMapper());
        String sql = "select * from vehicle_reservation where org_id = ? and TO_DAYS(use_begin) - TO_DAYS(NOW()) <= ? and status = 0 ";
        List<Object> param = new ArrayList<Object>();
        param.add(org_id);
        param.add(remind_days);

        if(customer_name != null && !"".equals(customer_name.trim())) {
            sql = sql + " and customer_name = ? ";
            param.add(customer_name);
        }
        if(dn != null && !"".equals(dn.trim())) {
            sql = sql + " and customer_dn = ? ";
            param.add(dn);
        }
        sql = sql + " order by id desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleReservationInfoRowMapper());
    }

    public int addReservation(String original_org ,String customer_name , String customer_dn , Date use_begin_date , Date use_end_date ,
                              String employee_id , String employee_name , String remark , long user_id) {

        String sql = "insert into vehicle_reservation(customer_name , customer_dn , use_begin , use_end , " +
                "employee_id , employee_name , org_id , remark , create_by) " +
                "values (?,?,?,?,?,?,?,?,?)";
        Object[] o = new Object[] { customer_name , customer_dn , use_begin_date , use_end_date , employee_id , employee_name , original_org , remark , user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    public long getOrgReservationCount(long org_id , String status) {
        String sql;
        Object[] o;
        if(status == null || "".equals(status)) {
//            sql = "select count(1) from vehicle_reservation where org_id = ? and status in ('待审核','风控通过','风控不通过')";
            sql = "select count(1) from vehicle_reservation where org_id = ? ";
            o = new Object[] { org_id };
        } else {
            sql = "select count(1) from vehicle_reservation where org_id = ? and status = ? ";
            o = new Object[] { org_id , status };
        }
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取某个门店下，风控列表
     */
    public List<VehicleReservationInfo> getOrgReservationList(long org_id , String status , int start, int size) {
        String sql;
        Object[] o;
        if(status == null || "".equals(status)) {
//            sql = "select * from vehicle_reservation where org_id = ? and status in ('待审核','风控通过','风控不通过') limit ?,? ";
            sql = "select * from vehicle_reservation where org_id = ? limit ?,? ";
            o = new Object[] { org_id , start , size };
        } else {
            sql = "select * from vehicle_reservation where org_id = ? and status = ? limit ?,? ";
            o = new Object[] { org_id , status , start , size};
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleReservationInfoRowMapper());
    }

    /**
     * 业务员更新预订单
     *
     * @param reservation_id
     * @param user_id
     * @param status 1-完结；2-取消
     * @return
     */
    public int reservationDoCancel(long reservation_id , long user_id , int status) {
        String sql = "update vehicle_reservation set status = ? where id = ? and create_by = ?";
        Object[] o = new Object[] { status , reservation_id , user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    public VehicleReservationInfo getVehicleReservationInfoById(long reservation_id)  {
        try{
            String sql = "select * from vehicle_reservation where id = ?";
            Object[] o = new Object[] { reservation_id };
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return this.getJdbcTemplate().queryForObject(sql, o, new VehicleReservationInfoRowMapper());
        } catch(EmptyResultDataAccessException e) {
            logger.error(e.getMessage() , e);
            return null;
        }
    }


    public long getOrgContraceCount(long org_id , String status , boolean over_top) {
//        String sql;
//        Object[] o;
//        if(status == null || "".equals(status)) {
//            sql = "select count(1) from vehicle_contrace where org_id = ?  ";
//            o = new Object[] { org_id };
//        } else {
//            sql = "select count(1) from vehicle_contrace where org_id = ? and status = ? ";
//            o = new Object[] { org_id , Long.valueOf(status) };
//        }
//        logger.info(sql.replaceAll("\\?", "{}"), o);
//        return this.getJdbcTemplate().queryForLong(sql, o);
        String sql = "select count(1) from vehicle_contrace where org_id = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(org_id);

        if(status != null && !"".equals(status.trim())) {
            sql = sql + " and status = ? ";
            param.add(Long.valueOf(status));
        }
        if(over_top) {
            sql = sql + " and isovertop = 1 ";
        }

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<VehicleContraceInfo> getOrgContraceList(long org_id , String status , boolean over_top , int start, int size) {
//        String sql;
//        Object[] o;
//        if(status == null || "".equals(status)) {
//            sql = "select * from vehicle_contrace where org_id = ? limit ?,? ";
//            o = new Object[] { org_id , start , size };
//        } else {
//            sql = "select * from vehicle_contrace where org_id = ? and status = ? limit ?,? ";
//            o = new Object[] { org_id , Long.valueOf(status) , start , size};
//        }
//
//        logger.info(sql.replaceAll("\\?", "{}"), o);
//        return this.getJdbcTemplate().query(sql, o, new VehicleContraceInfoRowMapper());
        String sql = "select * from vehicle_contrace where org_id = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(org_id);

        if(status != null && !"".equals(status.trim())) {
            sql = sql + " and status = ? ";
            param.add(Long.valueOf(status));
        }
        if(over_top) {
            sql = sql + " and isovertop = 1 ";
        }
        sql = sql + " order by id desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleContraceInfoRowMapper());
    }

    public long addContrace(long reservation_id , long org_id , long user_id) {
        long contrace_id = this.commonDao.getNextVal("ContraceSeq");
        String sql = "insert into vehicle_contrace(id , reservation_id , org_id , create_by) values (?,?,?,?)";
        Object[] o = new Object[] { contrace_id , reservation_id , org_id , user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        long result = this.getJdbcTemplate().update(sql, o);
        if(result > 0) {
            result = contrace_id;
        }
        return result;
    }

    public long modifyContrace(long contrace_id , long original_org , String customer_name , String customer_type , String customer_dn ,
                               String certificate_type , String certificate_no , Date use_begin_date , Date use_end_date , String employee_id ,
                               String employee_name , String remark , long user_id) {
        String sql = "update vehicle_contrace set customer_name = ? , customer_type = ? , customer_dn = ? , customer_cer_type = ? , customer_cer_no = ? , " +
                " remark = ? , employee_id = ? , employee_name = ? , org_id = ? , use_begin = ? , use_end = ? where id = ? and create_by = ?";
        Object[] o = new Object[] { customer_name , customer_type , customer_dn , certificate_type , certificate_no ,
                remark , employee_id , employee_name , original_org , use_begin_date , use_end_date , contrace_id , user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        long result = this.getJdbcTemplate().update(sql, o);
        if(result > 0) {
            result = contrace_id;
        }
        return result;
    }

    public VehicleContraceInfo getVehicleContraceInfoById(long contrace_id) {
        try{
            String sql = "select * from vehicle_contrace where id = ?";
            Object[] o = new Object[] { contrace_id };
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return this.getJdbcTemplate().queryForObject(sql , o , new VehicleContraceInfoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage() , e);
            return null;
        }
    }

    /**
     * 获取某合同下对应的车辆总数
     * @param contrace_id
     * @return
     */
    public long getContraceVehsCount(long contrace_id) {
        String sql = "select count(1) from vehicle_contrace_vehs where contrace_id = ? ";
        Object[] o = new Object[] { contrace_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /***
     * 获取某合同下车辆列表
     * @param contrace_id
     * @return
     */
    public List<VehicleContraceVehsInfo> getContraceVehsList(long contrace_id) {
        String sql = "select * from vehicle_contrace_vehs where contrace_id = ?";
        Object[] o = new Object[] { contrace_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleContraceVehsInfoRowMapper());
    }

    public VehicleContraceVehsInfo getContraceVehicleByid(long id) {
        try{
            String sql = "select * from vehicle_contrace_vehs where id = ?";
            Object[] o = new Object[] { id };
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return this.getJdbcTemplate().queryForObject(sql, o, new VehicleContraceVehsInfoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage() , e);
            return null;
        }

    }

    /**
     * 业务员提交合同，到门店店长审核
     * @return
     */
    public int contraceToShopAudit(long contrace_id , long user_id , boolean isSysadmin) {
        String sql;
        Object[] o;
        if(isSysadmin) {
            sql = "update vehicle_contrace set status = 1 where id = ? and status in (0,-1) ";
            o = new Object[] { contrace_id };
        } else {
            sql = "update vehicle_contrace set status = 1 where id = ? and create_by = ? and status in (0,-1) ";
            o = new Object[] { contrace_id , user_id };
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 获取某一合同中车辆总价
     * @param contrace_id
     * @return
     */
    public double getContraceVehicleTotalPrice(long contrace_id) {
        String sql = "select coalesce(sum(vehicle_price) , 0) from vehicle_contrace_vehs where contrace_id = ? ";
        Object[] o = new Object[] { contrace_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForObject(sql, o, Double.class);
    }

    /**
     * 需要市公司店长审核
     * @param contrace_id
     * @param user_id
     * @return
     */
    public int contraceNeedCityAudit(long contrace_id , long user_id) {
        String sql = "update vehicle_contrace set isovertop = 1 where id = ? and create_by = ?";
        Object[] o = new Object[] { contrace_id , user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 门店店长审核
     * @param id
     * @param status
     * @param user_id
     * @return
     */
    public int shopownerDoAudit(long id , String status , long user_id) {
        String sql = "update vehicle_contrace set status = ? , shopowner_update_by = ? , shopowner_update_at = now() where id = ?";
        Object[] o = new Object[] { status , user_id , id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 市门店店长审核
     * @param id
     * @param status
     * @param user_id
     * @return
     */
    public int cityShopownerDoAudit(long id , String status , long user_id) {
        String sql = "update vehicle_contrace set status = ? , city_shopowner_update_by = ? , city_shopowner_update_at = now() where id = ?";
        Object[] o = new Object[] { status , user_id , id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 区域经理审核
     * @param id
     * @param status
     * @param user_id
     * @return
     */
    public int regionalManagerDoAudit(long id , String status , long user_id) {
        String sql = "update vehicle_contrace set status = ? , regional_manager_update_by = ? , regional_manager_update_at = now() where id = ?";
        Object[] o = new Object[] { status , user_id , id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 财务审核
     * @param id
     * @param status
     * @param user_id
     * @return
     */
    public int financeDoAudit(long id , String status , long user_id) {
        String sql = "update vehicle_contrace set status = ? , finance_update_by = ? , finance_update_at = now() where id = ?";
        Object[] o = new Object[] { status , user_id , id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 业务员结单
     * @param id
     * @param status
     * @param user_id
     * @return
     */
    public int contraceDoFinish(long id , String status , long user_id) {
        String sql = "update vehicle_contrace set status = ? where id = ? and (create_by = ? or update_by = ?)";
        Object[] o = new Object[] { status , id , user_id , user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 获取合同能够使用的车辆
     * @param original_org
     * @param current_city
     * @param brand
     * @param vehicle_model
     * @param license_plate
     * @return
     */
    public long getContraceCanUseVehicleCount(long original_org , String current_city , String brand , String vehicle_model , String license_plate) {
        String sql = "select count(1) from vehicle_info where original_org = ? and lease_status = '在库' ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        if(brand != null && !"".equals(brand.trim())) {
            sql = sql + " and brand like ? ";
            param.add("%"+brand+"%");
        }
        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate = ? ";
            param.add(license_plate);
        }

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<VehicleInfo> getContraceCanUseVehicleList(long original_org , String current_city , String brand , String vehicle_model , String license_plate , int start , int size) {
        String sql = "select * from vehicle_info where original_org = ? and lease_status = '在库' ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

        if(current_city != null && !"".equals(current_city.trim())) {
            sql = sql + " and current_city = ? ";
            param.add(Long.valueOf(current_city));
        }
        if(brand != null && !"".equals(brand.trim())) {
            sql = sql + " and brand like ? ";
            param.add("%"+brand+"%");
        }
        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model+"%");
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
     *
     * @param contrace_id
     * @param vehicle_id
     * @param user_id
     * @return
     */
    public int contraceDoChooseVech(long contrace_id , long vehicle_id , long user_id , double vehicle_price , String license_plate , String model) {
        String sql = "insert into vehicle_contrace_vehs (contrace_id , vehicle_id , create_by , vehicle_price , license_plate , model) values (?,?,?,?,?,?)";
        Object[] o = new Object[] { contrace_id , vehicle_id , user_id , vehicle_price  , license_plate , model };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 更新车辆状态
     * @param vehicle_id
     * @param status
     * @return
     */
    public int updateVehicleStatus(long vehicle_id , String status) {
        String sql = "update vehicle_info set lease_status = ? where id = ?";
        Object[] o = new Object[] { status , vehicle_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    public long getContraceVechCount(long contrace_id, String brand, String vehicle_model, String license_plate) {
        String sql = "select count(1) from vehicle_contrace_vehs where contrace_id = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(contrace_id);

        if(brand != null && !"".equals(brand.trim())) {
            sql = sql + " and brand like ? ";
            param.add("%"+brand+"%");
        }
        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model+"%");
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate = ? ";
            param.add(license_plate);
        }

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<VehicleContraceVehsInfo> getContraceVech(long contrace_id , String brand , String vehicle_model , String license_plate , int start , int size) {
        String sql = "select * from vehicle_contrace_vehs where contrace_id = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(contrace_id);

        if(brand != null && !"".equals(brand.trim())) {
            sql = sql + " and brand like ? ";
            param.add("%"+brand+"%");
        }
        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
            sql = sql + " and model like ? ";
            param.add("%"+vehicle_model+"%");
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
        return this.getJdbcTemplate().query(sql, o, new VehicleContraceVehsInfoRowMapper());
    }

    public long getcontraceVechDriverCount(long original_org) {
        String sql = "select count(1) from users a , user_role b where a.user_id = b.user_id and b.org_id = ? and a.driver_status = 0 and b.role_id = 20007 ";
        Object[] o = new Object[] { original_org };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<UserDriver> getcontraceVechDriverList(long original_org , int start , int size) {
        String sql = "select a.user_id , a.user_name , a.employee_id , a.driver_status , a.driver_license_no " +
                "from users a , user_role b " +
                "where a.user_id = b.user_id and b.org_id = ? and a.driver_status = 0 and b.role_id = 20007 ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

//        if(brand != null && !"".equals(brand.trim())) {
//            sql = sql + " and brand like ? ";
//            param.add("%"+brand+"%");
//        }
//        if(vehicle_model != null && !"".equals(vehicle_model.trim())) {
//            sql = sql + " and model like ? ";
//            param.add("%"+vehicle_model+"%");
//        }
//        if(license_plate != null && !"".equals(license_plate.trim())) {
//            sql = sql + " and license_plate = ? ";
//            param.add(license_plate);
//        }
        sql = sql + " order by user_id desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new UserDriverRowMapper());
    }

    public int contraceDoChooseDriver(long veh_contrace_vehs_id, long driver_user_id , String driving_user_name , long user_id) {
        String sql = "update vehicle_contrace_vehs set driving_user_id = ? , driving_user_name = ? , update_by = ?  where id = ?";
        Object[] o = new Object[] { driver_user_id , driving_user_name , user_id , veh_contrace_vehs_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    public int updateUserDriverStatus(long driver_user_id , int status) {
        String sql = "update users set driver_status = ? where user_id = ?";
        Object[] o = new Object[] { status , driver_user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    public int contraceCancelChooseVehicle(long veh_contrace_vehs_id , long user_id) {
        String sql = "delete from vehicle_contrace_vehs  where id = ? and create_by = ?";
        Object[] o = new Object[] { veh_contrace_vehs_id , user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }
}
