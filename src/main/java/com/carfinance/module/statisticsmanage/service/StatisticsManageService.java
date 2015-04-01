package com.carfinance.module.statisticsmanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.statisticsmanage.dao.StatisticsManageDao;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceVehsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author jiangyin
 */
@Service
public class StatisticsManageService {
	
	static Logger logger = LoggerFactory.getLogger(StatisticsManageService.class);
	
	@Autowired
	private ManageMemcacdedClient memcachedClient;
	@Autowired
	private StatisticsManageDao statisticsManageDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitService initService;
    @Autowired
    private CommonDao commonDao;

    public Map<String , Object> getVehicleList(String vehicle_model , String license_plate , int start , int size) {
        long total = this.statisticsManageDao.getVehicleCount(vehicle_model , license_plate);
        List<VehicleContraceVehsInfo> vehicleList =  this.statisticsManageDao.getVehicleList(vehicle_model, license_plate, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicle_list" , vehicleList);
        return map;
    }




}
