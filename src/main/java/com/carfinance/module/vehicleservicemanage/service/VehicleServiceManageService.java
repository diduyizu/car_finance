package com.carfinance.module.vehicleservicemanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.domain.City;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehicleservicemanage.dao.VehicleServiceManageDao;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceVehsInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleReservationInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleReservationInfoRowMapper;
import com.carfinance.utils.DateTimeUtil;
import com.carfinance.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
            Date use_begin_date = DateUtil.string2Date(use_begin , "yyyy-MM-dd HH:mm");
            Date use_end_date = DateUtil.string2Date(use_end , "yyyy-MM-dd HH:mm");

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


//    public Map<String , Object> getOrgContraceList(long org_id , int start, int size) {
//        long total = this.vehicleServiceManageDao.getOrgContraceCount(org_id , null);
//        List<VehicleContraceInfo> contrace_list = this.vehicleServiceManageDao.getOrgContraceList(org_id, start, size);
//        Map<String , Object> map = new HashMap<String, Object>();
//        map.put("total" , total);
//        map.put("contrace_list", contrace_list);
//        return map;
//    }
    public Map<String , Object> getOrgContraceList(long original_org, String status, int start, int size) {
        long total = this.vehicleServiceManageDao.getOrgContraceCount(original_org, status);
        List<VehicleContraceInfo> contrace_list = this.vehicleServiceManageDao.getOrgContraceList(original_org, status, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("contrace_list", contrace_list);
        return map;
    }


    public long addContrace(long reservation_id , long org_id , long user_id) {
        try{
            return this.vehicleServiceManageDao.addContrace(reservation_id , org_id , user_id);
        } catch(Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }
    }

    public long modifycontrace(long contrace_id , long original_org , String customer_name , String customer_type , String customer_dn ,
                               String certificate_type , String certificate_no , String use_begin , String use_end , String employee_id ,
                               String employee_name , String remark , long user_id) {
        try{
            Date use_begin_date = DateUtil.string2Date(use_begin , "yyyy-MM-dd HH:mm");
            Date use_end_date = DateUtil.string2Date(use_end , "yyyy-MM-dd HH:mm");
            return this.vehicleServiceManageDao.modifyContrace(contrace_id , original_org , customer_name , customer_type , customer_dn ,
                    certificate_type , certificate_no , use_begin_date , use_end_date , employee_id , employee_name , remark , user_id);
        } catch(Exception e) {
            logger.error(e.getMessage() , e);
            return 0;
        }
    }

    public VehicleContraceInfo getVehicleContraceInfoById(long contrace_id) {
        return this.vehicleServiceManageDao.getVehicleContraceInfoById(contrace_id);
    }

    /**
     * 提交店长审核
     * @return
     */
    public long contraceToShopAudit(long contrace_id , long user_id) {
        //首先判断该合同，是否存在对应的车辆关系；如果不存在，直接返回，提示业务员先要录入车辆
        long contraceVehsCount = this.vehicleServiceManageDao.getContraceVehsCount(contrace_id);
        if(contraceVehsCount == 0) {
            return -1;//该合同没有录入车辆，请先增加车辆
        }
        //如果有车辆，那么更新合同表状态为待审核
        int result = this.vehicleServiceManageDao.contraceToShopAudit(contrace_id , user_id);

        if(result > 0) {
            //TODO
            //判断该合同下所属车辆，是否超过一定金额，如果超过，则需要市店长、区域经理审核
        }


        return result;
    }

    /**
     * 根据合同id，获取该合同所在时间内，能够使用的车辆
     * @param contrace_id
     * @param original_org
     * @param current_city
     * @param brand
     * @param vehicle_model
     * @param license_plate
     * @param gps
     * @param km_begin
     * @param km_end
     * @param lease_status
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getVehicleList(long contrace_id , long original_org , String current_city , String brand , String vehicle_model , String license_plate , String gps , String km_begin , String km_end , String lease_status ,  int start , int size) {
//        long total = this.vehicleManageDao.getVehicleCount(original_org , current_city , brand , vehicle_model , license_plate , gps , km_begin , km_end , lease_status);//门店品牌车辆总数
//        List<VehicleInfo> vehicle_list = this.vehicleManageDao.getVehicleList(original_org , current_city , brand , vehicle_model , license_plate , gps , km_begin , km_end , lease_status , start , size);
//        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
//        for(VehicleInfo vehicleInfo : vehicle_list) {
//            for(City city : sys_used_city_list) {
//                if(vehicleInfo.getCurrent_city() == city.getCity_id()) {
//                    vehicleInfo.setCurrent_city_name(city.getCity_name());
//                    break;
//                }
//            }
//        }

        Map<String , Object> map = new HashMap<String, Object>();
//        map.put("total" , total);
//        map.put("vehicle_list" , vehicle_list);
        return map;
    }




























    public int riskcontrolAudit(long id , String status , long user_id) {
        return this.vehicleServiceManageDao.riskcontrolAudit(id , status , user_id);
    }



}
