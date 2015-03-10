package com.carfinance.module.vehicleservicemanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.vehicleservicemanage.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public class VehicleServiceManageDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(VehicleServiceManageDao.class);

    @Autowired
    private CommonDao commonDao;

    public long getOrgReservationCount(long org_id) {
        String sql = "select count(1) from vehicle_reservation where org_id = ? ";
        Object[] o = new Object[] { org_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<VehicleReservationInfo> getOrgReservationList(long org_id , int start, int size) {
        String sql = "select * from vehicle_reservation where org_id = ? order by id desc limit ?,? ";
        Object[] o = new Object[] { org_id , start , size };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleReservationInfoRowMapper());
    }

    public long getOrgReservationRemindCount(long org_id , int remind_days) {
        String sql = "select count(1) from vehicle_reservation where org_id = ? and TO_DAYS(use_begin) - TO_DAYS(NOW()) <= ? and status = 0 ";
        Object[] o = new Object[] { org_id , remind_days };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<VehicleReservationInfo> getOrgReservationRemindList(long org_id , int remind_days , int start, int size) {
        String sql = "select * from vehicle_reservation where org_id = ? and TO_DAYS(use_begin) - TO_DAYS(NOW()) <= ? and status = 0 limit ?,? ";
        Object[] o = new Object[] { org_id , remind_days , start , size };
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

    public int riskcontrolAudit(long id , String status , long user_id) {
        String sql = "update vehicle_reservation set status = ? , risk_control_update_by = ? , risk_control_update_at = now() where id = ?";
        Object[] o = new Object[] { status , user_id , id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
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


    public long getOrgContraceCount(long org_id , String status) {
        String sql;
        Object[] o;
        if(status == null || "".equals(status)) {
            sql = "select count(1) from vehicle_contrace where org_id = ?  ";
            o = new Object[] { org_id };
        } else {
            sql = "select count(1) from vehicle_contrace where org_id = ? and status = ? ";
            o = new Object[] { org_id , Long.valueOf(status) };
        }
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取某个门店下，风控列表
     */
    public List<VehicleContraceInfo> getOrgContraceList(long org_id , String status , int start, int size) {
        String sql;
        Object[] o;
        if(status == null || "".equals(status)) {
            sql = "select * from vehicle_contrace where org_id = ? limit ?,? ";
            o = new Object[] { org_id , start , size };
        } else {
            sql = "select * from vehicle_contrace where org_id = ? and status = ? limit ?,? ";
            o = new Object[] { org_id , Long.valueOf(status) , start , size};
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
        if(result > 1) {
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
        Object o = new Object[] { contrace_id };
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

    /**
     *
     * @return
     */
    public int contraceToShopAudit(long contrace_id , long user_id) {
        String sql = "update vehicle_contrace set status = 1 where id = ? and create_by = ? and status in (0,-1) ";
        Object o = new Object[] { contrace_id , user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }
}
