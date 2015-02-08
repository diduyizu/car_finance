package com.carfinance.module.vehiclemanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.module.storemanage.domain.Store;
import com.carfinance.module.vehiclemanage.dao.VehicleManageDao;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInsurance;
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

    public int addVehicle(String archive_no , String inventory_no , String brand , String model , String color , String carframe_no , String engine_no ,
                          String registry_certificate , String certificate_direction , String loan_bank , String consistency_cer , String check_list ,
                          String duty_paid_proof , String record , String buy_at , String supplier , String license_plate , String card_at ,
                          String limited_at , double guide_price , double vehicle_price , double vehicle_tax , String insurance_company ,
                          double strong_insurance , double vehicle_vessel_tax , String strong_insurance_expire_at , double business_insurance ,
                          String business_insurance_expire_at , String remark , long create_by , long original_org) {

        try{
            Date buy_at_date = DateUtil.string2Date(buy_at);
            Date card_at_date = DateUtil.string2Date(card_at);
            Date limited_at_date = DateUtil.string2Date(limited_at);
            Date strong_insurance_expire_at_date = DateUtil.string2Date(strong_insurance_expire_at);
            Date business_insurance_expire_at_date = DateUtil.string2Date(business_insurance_expire_at);
            int result = this.vehicleManageDao.addVehicle(archive_no , inventory_no , brand , model , color , carframe_no , engine_no ,
                    registry_certificate , certificate_direction , loan_bank , consistency_cer , check_list ,
                    duty_paid_proof , record , buy_at_date , supplier , license_plate , card_at_date ,
                    limited_at_date , guide_price , vehicle_price , vehicle_tax , insurance_company ,
                    strong_insurance , vehicle_vessel_tax , strong_insurance_expire_at_date , business_insurance ,
                    business_insurance_expire_at_date , remark , create_by , original_org);
            if(result > 0) {
                this.vehicleManageDao.addVehicleInsurance(carframe_no , engine_no , license_plate , insurance_company , strong_insurance ,
                        vehicle_vessel_tax , strong_insurance_expire_at_date , business_insurance , business_insurance_expire_at_date , remark , create_by);
            }
            return result;
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }

    }

    public Map<String , Object> getVehicleInsuranceList(String carframe_no , int start , int size) {
        long total = this.vehicleManageDao.getVehicleInsuranceCount(carframe_no);
        List<VehicleInsurance> vehicleInsurance_list = this.vehicleManageDao.getVehicleInsuranceList(carframe_no, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicleInsurance_list" , vehicleInsurance_list);
        return map;
    }

}
