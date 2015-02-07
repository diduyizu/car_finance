package com.carfinance.module.vehiclemanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.module.storemanage.domain.Store;
import com.carfinance.module.vehiclemanage.dao.VehicleManageDao;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
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
public class VehicleManageService {
	
	static Logger logger = LoggerFactory.getLogger(VehicleManageService.class);
	
	@Autowired
	private ManageMemcacdedClient memcachedClient;
	@Autowired
	private VehicleManageDao vehicleManageDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitService initService;
    @Autowired
    private CommonDao commonDao;

    public Map<String , Object> getVehicleList(long original_org , String brand , String carframe_no , String engine_no , String license_plate ,  int start , int size) {
        long total = 1;
        if(brand == null || "".equals(brand) || carframe_no == null || "".equals(carframe_no) || engine_no == null || "".equals(engine_no) || license_plate == null || "".equals(license_plate)) {
            total = this.vehicleManageDao.getSehicleCount(original_org);//门店车辆总数
        }
        List<VehicleInfo> vehicle_list = this.vehicleManageDao.getVehicleList(original_org , brand , carframe_no , engine_no , license_plate , start , size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicle_list" , vehicle_list);
        return map;
    }

}
