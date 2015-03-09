package com.carfinance.module.vehicleservicemanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.module.vehicleservicemanage.dao.VehicleServiceManageDao;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleReservationInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleReservationInfoRowMapper;
import com.carfinance.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author jiangyin
 */
@Service
public class VehicleServiceManageService {
	
	static Logger logger = LoggerFactory.getLogger(VehicleServiceManageService.class);
	
	@Autowired
	private ManageMemcacdedClient memcachedClient;
	@Autowired
	private VehicleServiceManageDao vehicleServiceManageDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitService initService;
    @Autowired
    private CommonDao commonDao;

    public Map<String , Object> getOrgReservationList(long org_id , int start, int size) {
        long total = this.vehicleServiceManageDao.getOrgReservationCount(org_id);
        List<VehicleReservationInfo> reservation_list = this.vehicleServiceManageDao.getOrgReservationList(org_id , start , size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("reservation_list", reservation_list);
        return map;
    }

    public Map<String , Object> getOrgReservationRemindList(long org_id , int remind_days , int start, int size) {
        long total = this.vehicleServiceManageDao.getOrgReservationRemindCount(org_id , remind_days);
        List<VehicleReservationInfo> reservation_list = this.vehicleServiceManageDao.getOrgReservationRemindList(org_id , remind_days , start , size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("reservation_list", reservation_list);
        return map;
    }

    public int addReservation(String original_org , String customer_name , String customer_dn , String use_begin , String use_end ,
                             String employee_id , String employee_name , String remark , long user_id) {
        try{
            Date use_begin_date = DateUtil.string2Date(use_begin , "yyyy-mm-dd HH:MM");
            Date use_end_date = DateUtil.string2Date(use_end , "yyyy-mm-dd HH:MM");

            return this.vehicleServiceManageDao.addReservation(original_org , customer_name , customer_dn , use_begin_date , use_end_date ,
                    employee_id , employee_name , remark , user_id);
        } catch(Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }
    }

    /**
     * 获取某组织下，某状态下的预约单列表
     * @param org_id
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getOrgReservationList(long org_id , String status , int start, int size) {
        long total = this.vehicleServiceManageDao.getOrgReservationCount(org_id, status);
        List<VehicleReservationInfo> reservation_list = this.vehicleServiceManageDao.getOrgReservationList(org_id, status, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("reservation_list", reservation_list);
        return map;
    }

    public int reservationDoCancel(long reservation_id , long user_id , int status) {
        return this.vehicleServiceManageDao.reservationDoCancel(reservation_id, user_id, status);
    }


    public Map<String , Object> getOrgContraceList(long org_id , int start, int size) {
        long total = this.vehicleServiceManageDao.getOrgContraceCount(org_id);
        List<VehicleContraceInfo> contrace_list = this.vehicleServiceManageDao.getOrgContraceList(org_id, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("contrace_list", contrace_list);
        return map;
    }


    public long addContrace(long reservation_id , long user_id) {
        try{
            return this.vehicleServiceManageDao.addContrace(reservation_id , user_id);
        } catch(Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }
    }

    public long modifycontrace(long contrace_id , long original_org , String customer_name , String customer_type , String customer_dn ,
                               String certificate_type , String certificate_no , String use_begin , String use_end , String employee_id ,
                               String employee_name , String remark , long user_id) {
        return this.vehicleServiceManageDao.modifyContrace(contrace_id , original_org , customer_name , customer_type , customer_dn ,
                certificate_type , certificate_no , use_begin , use_end , employee_id , employee_name , remark , user_id);
    }
































    public int riskcontrolAudit(long id , String status , long user_id) {
        return this.vehicleServiceManageDao.riskcontrolAudit(id , status , user_id);
    }



}
