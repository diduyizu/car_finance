package com.carfinance.module.vehicleservicemanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.module.vehicleservicemanage.dao.VehicleServiceManageDao;
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

    public int addReservation(String original_org , String carframe_model , String customer_name , String customer_dn , String use_begin , String use_end ,
                              double unit_price , long quantity , int with_driver , int expenses_self , String employee_id , String employee_name , long user_id) {
        try{
            Date use_begin_date = DateUtil.string2Date(use_begin , "yyyy-mm-dd HH:MM");
            Date use_end_date = DateUtil.string2Date(use_end , "yyyy-mm-dd HH:MM");

            return this.vehicleServiceManageDao.addReservation(original_org , carframe_model , customer_name , customer_dn , use_begin_date , use_end_date ,
                    unit_price , quantity , with_driver , expenses_self , employee_id , employee_name , user_id);
        } catch(Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }
    }

    /**
     * 获取某组织下，风控需要审核预约单列表
     * @param org_id
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getOrgRiskControlList(long org_id , String status , int start, int size) {
        long total = this.vehicleServiceManageDao.getOrgRiskControlCount(org_id , status);
        List<VehicleReservationInfo> reservation_list = this.vehicleServiceManageDao.getOrgRiskControlList(org_id , status , start , size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("reservation_list", reservation_list);
        return map;
    }
}
