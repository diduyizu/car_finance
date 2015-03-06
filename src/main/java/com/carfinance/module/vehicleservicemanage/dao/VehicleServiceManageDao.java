package com.carfinance.module.vehicleservicemanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.storemanage.domain.Store;
import com.carfinance.module.storemanage.domain.StoreRowMapper;
import com.carfinance.module.vehicleservicemanage.domain.VehicleReservationInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleReservationInfoRowMapper;
import com.carfinance.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 获取某个门店下，
     */
    public List<VehicleReservationInfo> getOrgReservationList(long org_id , int start, int size) {
        String sql = "select * from vehicle_reservation where org_id = ? limit ?,? ";
        Object[] o = new Object[] { org_id , start , size };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleReservationInfoRowMapper());
    }

    public int addReservation(String original_org , String carframe_model , String customer_name , String customer_dn , Date use_begin_date , Date use_end_date ,
                              double unit_price , long quantity , int with_driver , int expenses_self , String employee_id , String employee_name , long user_id) {

        String sql = "insert into vehicle_reservation(customer_name , customer_dn , use_begin , use_end , model , unit_price , " +
                "quantity , with_driver , expenses_self , employee_id , employee_name , org_id , create_by) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] o = new Object[] { customer_name , customer_dn , use_begin_date , use_end_date , carframe_model , unit_price ,
                quantity , with_driver , expenses_self , employee_id , employee_name , original_org , user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);

    }



}
