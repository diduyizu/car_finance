package com.carfinance.module.vehiclemanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.vehiclemanage.dao.VehicleManageDao;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInsurance;
import com.carfinance.module.vehiclemanage.domain.VehiclePeccancy;
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
    @Autowired
    private Properties appProps;

    /**
     * 获取某组织下车辆列表
     * @param original_org
     * @param brand
     * @param carframe_no
     * @param engine_no
     * @param license_plate
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getVehicleList(long original_org , String brand , String vehicle_model , String license_plate , String gps , String km_begin , String km_end ,  int start , int size) {
        long total = this.vehicleManageDao.getVehicleCount(original_org , brand , vehicle_model , license_plate , gps , km_begin , km_end);//门店品牌车辆总数
        List<VehicleInfo> vehicle_list = this.vehicleManageDao.getVehicleList(original_org , brand , vehicle_model , license_plate , gps , km_begin , km_end , start , size);
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
                          String business_insurance_expire_at , String remark , long create_by , long original_org ,
                          long km , long maintian_on_km , String gps , long current_city , long current_shop , String lease_status , String peccancy_status) {

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
                    business_insurance_expire_at_date , remark , create_by , original_org ,
                    km , maintian_on_km , gps , current_city , current_shop , lease_status , peccancy_status);
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

    /**
     * 获取某车辆保险纪录
     * @param carframe_no
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getVehicleInsuranceList(String carframe_no , int start , int size) {
        long total = this.vehicleManageDao.getVehicleInsuranceCount(carframe_no);
        List<VehicleInsurance> vehicleInsurance_list = this.vehicleManageDao.getVehicleInsuranceList(carframe_no, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicleInsurance_list" , vehicleInsurance_list);
        return map;
    }

    /**
     * 获取某车辆违章记录
     * @param carframe_no
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getVehiclePeccancyList(String carframe_no , int start , int size) {
        long total = this.vehicleManageDao.getVehiclePeccancyCount(carframe_no);
        List<VehiclePeccancy> vehiclePeccancy_list = this.vehicleManageDao.getVehiclePeccancyList(carframe_no, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehiclePeccancy_list" , vehiclePeccancy_list);
        return map;
    }

    /**
     * 录入车辆保险
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
    public int addInsurance(String carframe_no , String engine_no , String license_plate , String insurance_company ,
                            double strong_insurance , double vehicle_vessel_tax , String strong_insurance_expire_at , double business_insurance ,
                            String business_insurance_expire_at , String remark , long create_by) {

        try{
            Date strong_insurance_expire_at_date = DateUtil.string2Date(strong_insurance_expire_at);
            Date business_insurance_expire_at_date = DateUtil.string2Date(business_insurance_expire_at);

            int i = this.vehicleManageDao.addVehicleInsurance(carframe_no , engine_no , license_plate , insurance_company , strong_insurance ,
                        vehicle_vessel_tax , strong_insurance_expire_at_date , business_insurance , business_insurance_expire_at_date , remark , create_by);

            //更新汽车主表，关于保险公司、交强险、车船税、交强险到期日期、商业险、商业险到期日期等信息
            if(i > 0) {//插入汽车保险纪录表成功，再更新汽车主表
                return this.vehicleManageDao.updateVehicleInsurance(carframe_no , engine_no , license_plate , insurance_company , strong_insurance ,
                        vehicle_vessel_tax , strong_insurance_expire_at_date , business_insurance , business_insurance_expire_at_date);
            }
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
        }
        return 0;
    }

    public int addVehiclePeccancy(String carframe_no , String engine_no , String license_plate , String peccancy_at , String peccancy_place ,
                                  String peccancy_reason , long score , int status , long create_by , double peccancy_price , String arbitration) {

        try{
            Date peccancy_at_date = DateUtil.string2Date(peccancy_at);
            return this.vehicleManageDao.addVehiclePeccancy(carframe_no , engine_no , license_plate , peccancy_at_date ,
                    peccancy_place , peccancy_reason , score , status , create_by , peccancy_price , arbitration);
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }
    }

    /**
     * 获取车辆保险到期提醒
     * @param original_org
     * @param carframe_no
     * @param engine_no
     * @param license_plate
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getVehicleInsuranceRemindList(long original_org , String carframe_no , String engine_no , String license_plate , int start , int size) {
        long total = 1;
        int remind_day = Integer.valueOf(appProps.get("vehicle.insurance.remind.day").toString());//还有多少天需要提醒
        if((carframe_no == null || "".equals(carframe_no)) && (engine_no == null || "".equals(engine_no)) && (license_plate == null || "".equals(license_plate))) {
            total = this.vehicleManageDao.getInsuranceRemindCount(original_org , remind_day);//需要提醒保险到期车辆总数
        }
        List<VehicleInfo> vehicle_insurance_remind_list = this.vehicleManageDao.getVehicleInsuranceList(original_org  , carframe_no , engine_no , license_plate , remind_day , start , size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicle_insurance_remind_list" , vehicle_insurance_remind_list);
        return map;
    }

    /**
     *
     * @param original_org
     * @param lease_status
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getVehicleLeaseStatusList(long original_org , String lease_status , int start , int size) {
        long total = this.vehicleManageDao.getVehicleLeaseStatusCount(original_org , lease_status);//某门店下，某状态下的车辆总数
        List<VehicleInfo> vehicle_lease_status_list = this.vehicleManageDao.getVehicleLeaseStatusList(original_org, lease_status , start, size);//某门店下，某状态下的车辆列表
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicle_lease_status_list" , vehicle_lease_status_list);
        return map;
    }

}
