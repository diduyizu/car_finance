package com.carfinance.module.vehiclemanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.domain.City;
import com.carfinance.module.common.domain.Org;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.vehiclemanage.dao.VehicleManageDao;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInsurance;
import com.carfinance.module.vehiclemanage.domain.VehicleMaintail;
import com.carfinance.module.vehiclemanage.domain.VehiclePeccancy;
import com.carfinance.module.vehicleservicemanage.dao.VehicleServiceManageDao;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceVehsInfo;
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
    @Autowired
    private VehicleServiceManageDao vehicleServiceManageDao;

    /**
     * 获取某组织下车辆列表
     * @param original_org
     * @param brand
     * @param license_plate
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getVehicleList(long original_org , String current_city , String brand , String vehicle_model , String license_plate , String gps , String km_begin , String km_end , String lease_status , String color  , int start , int size) {
        long total = this.vehicleManageDao.getVehicleCount(original_org , current_city , brand , vehicle_model , license_plate , gps , km_begin , km_end , lease_status , color);//门店品牌车辆总数
        List<VehicleInfo> vehicle_list = this.vehicleManageDao.getVehicleList(original_org , current_city , brand , vehicle_model , license_plate , gps , km_begin , km_end , lease_status , color  , start , size);
        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
        List<Org> org_list = this.commonService.getSysAllOrgList();
//        for(VehicleInfo vehicleInfo : vehicle_list) {
//            for(City city : sys_used_city_list) {
//                if(vehicleInfo.getCurrent_city() == city.getCity_id()) {
//                    vehicleInfo.setCurrent_city_name(city.getCity_name());
//                    break;
//                }
//            }
//        }
        for(VehicleInfo vehicleInfo : vehicle_list) {
            for(Org org : org_list) {
                if(vehicleInfo.getCurrent_shop() == org.getOrg_id()) {
                    String current_shop_name = "";
                    if(org.getOrg_type() > 12) {
                        current_shop_name = org.getOrg_city_name() + " " + org.getOrg_name();
                    } else {
                        current_shop_name = org.getOrg_name();
                    }
                    vehicleInfo.setCurrent_shop_name(current_shop_name);
                    break;
                }
            }
        }


        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicle_list" , vehicle_list);
        return map;
    }

    /**
     * 获取某组织下车辆列表，车辆列表页，选择的是当前所在门店
     * 上面一个方法，获取的是车辆归属门店
     * @param current_shop
     * @param brand
     * @param license_plate
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getCurrentShopVehicleList(long current_shop , String current_city , String brand , String vehicle_model , String license_plate , String gps , String km_begin , String km_end , String lease_status , String color  , int start , int size) {
        long total = this.vehicleManageDao.getCurrentShopVehicleCount(current_shop , current_city , brand , vehicle_model , license_plate , gps , km_begin , km_end , lease_status , color);//门店品牌车辆总数
        List<VehicleInfo> vehicle_list = this.vehicleManageDao.getCurrentShopVehicleList(current_shop , current_city , brand , vehicle_model , license_plate , gps , km_begin , km_end , lease_status , color  , start , size);
        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
        List<Org> org_list = this.commonService.getSysAllOrgList();
        for(VehicleInfo vehicleInfo : vehicle_list) {
            for(Org org : org_list) {
                if(vehicleInfo.getCurrent_shop() == org.getOrg_id()) {
                    String current_shop_name = "";
                    if(org.getOrg_type() > 12) {
                        current_shop_name = org.getOrg_city_name() + " " + org.getOrg_name();
                    } else {
                        current_shop_name = org.getOrg_name();
                    }
                    vehicleInfo.setCurrent_shop_name(current_shop_name);
                    break;
                }
            }
        }


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
                          long km , String gps , long current_city , long current_shop , String lease_status , String peccancy_status , long next_main_km ,
                          String financing_rent_company , double financing_rent_price , double bail , double monthly_payment) {

        try{
            Date buy_at_date = DateUtil.string2Date(buy_at , "yyyy-MM-dd");
            Date card_at_date = DateUtil.string2Date(card_at , "yyyy-MM-dd");
            Date limited_at_date = DateUtil.string2Date(limited_at , "yyyy-MM-dd");
            Date strong_insurance_expire_at_date = DateUtil.string2Date(strong_insurance_expire_at , "yyyy-MM-dd");
            Date business_insurance_expire_at_date = DateUtil.string2Date(business_insurance_expire_at , "yyyy-MM-dd");
            int result = this.vehicleManageDao.addVehicle(archive_no , inventory_no , brand , model , color , carframe_no , engine_no ,
                    registry_certificate , certificate_direction , loan_bank , consistency_cer , check_list ,
                    duty_paid_proof , record , buy_at_date , supplier , license_plate , card_at_date ,
                    limited_at_date , guide_price , vehicle_price , vehicle_tax , insurance_company ,
                    strong_insurance , vehicle_vessel_tax , strong_insurance_expire_at_date , business_insurance ,
                    business_insurance_expire_at_date , remark , create_by , original_org ,
                    km  , gps , current_city , current_shop , lease_status , peccancy_status , next_main_km ,
                    financing_rent_company , financing_rent_price , bail , monthly_payment);
            if(result > 0) {
                //增加车辆保险详细
                this.vehicleManageDao.addVehicleInsurance(carframe_no , engine_no , license_plate , insurance_company , strong_insurance ,
                        vehicle_vessel_tax , strong_insurance_expire_at_date , business_insurance , business_insurance_expire_at_date , remark , create_by);
//                //增加车辆保养详细
//                this.vehicleManageDao.addVehicleMaintain(carframe_no , engine_no , license_plate , "" , 0 , km , next_main_km , 0 , "" , create_by);
            }
            return result;
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }

    }



    public int modifyVehicle(long vehicle_id , String archive_no , String inventory_no , String brand , String model , String color , String carframe_no , String engine_no ,
                          String registry_certificate , String certificate_direction , String loan_bank , String consistency_cer , String check_list ,
                          String duty_paid_proof , String record , String buy_at , String supplier , String license_plate , String card_at ,
                          String limited_at , double guide_price , double vehicle_price , double vehicle_tax , String insurance_company ,
                          double strong_insurance , double vehicle_vessel_tax , String strong_insurance_expire_at , double business_insurance ,
                          String business_insurance_expire_at , String remark , long update_by , long original_org ,
                          long km , String gps , long current_city , long current_shop , String lease_status , String peccancy_status , long next_main_km ,
                          String financing_rent_company , double financing_rent_price , double bail , double monthly_payment) {

        try{
            Date buy_at_date = DateUtil.string2Date(buy_at , "yyyy-MM-dd");
            Date card_at_date = DateUtil.string2Date(card_at , "yyyy-MM-dd");
            Date limited_at_date = DateUtil.string2Date(limited_at , "yyyy-MM-dd");
            Date strong_insurance_expire_at_date = DateUtil.string2Date(strong_insurance_expire_at , "yyyy-MM-dd");
            Date business_insurance_expire_at_date = DateUtil.string2Date(business_insurance_expire_at , "yyyy-MM-dd");
            int result = this.vehicleManageDao.modifyVehicle(vehicle_id , archive_no , inventory_no , brand , model , color , carframe_no , engine_no ,
                    registry_certificate , certificate_direction , loan_bank , consistency_cer , check_list ,
                    duty_paid_proof , record , buy_at_date , supplier , license_plate , card_at_date ,
                    limited_at_date , guide_price , vehicle_price , vehicle_tax , insurance_company ,
                    strong_insurance , vehicle_vessel_tax , strong_insurance_expire_at_date , business_insurance ,
                    business_insurance_expire_at_date , remark , update_by , original_org ,
                    km , gps , current_city , current_shop , lease_status , peccancy_status , next_main_km ,
                    financing_rent_company , financing_rent_price , bail , monthly_payment);
            if(result > 0) {
                //增加车辆保险详细
                this.vehicleManageDao.addVehicleInsurance(carframe_no , engine_no , license_plate , insurance_company , strong_insurance ,
                        vehicle_vessel_tax , strong_insurance_expire_at_date , business_insurance , business_insurance_expire_at_date , remark , update_by);
//                //增加车辆保养详细
//                this.vehicleManageDao.addVehicleMaintain(carframe_no , engine_no , license_plate , "" , 0 , km , next_main_km , 0 , "" , create_by);
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
            Date strong_insurance_expire_at_date = DateUtil.string2Date(strong_insurance_expire_at , "yyyy-MM-dd");
            Date business_insurance_expire_at_date = DateUtil.string2Date(business_insurance_expire_at , "yyyy-MM-dd");

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
                                  String peccancy_reason , long score , int status , long create_by , double peccancy_price , String arbitration ,
                                  String employee_id , String employee_name , long customer_id , String customer_name) {

        try{
            Date peccancy_at_date = DateUtil.string2Date(peccancy_at , "yyyy-MM-dd HH:mm");
            int result = this.vehicleManageDao.addVehiclePeccancy(carframe_no , engine_no , license_plate , peccancy_at_date ,
                    peccancy_place , peccancy_reason , score , status , create_by , peccancy_price , arbitration ,
                    employee_id , employee_name , customer_id , customer_name);
            if(result > 0) {//插入违章记录表成功后，需要判断该车辆是否还有未处理违章，如果有，需要更新车辆主表
                boolean has_peccancy = false;
                if(status == 1) {//本次录入的，就是未处理的违章，直接更新表
                    has_peccancy = true;
                } else {//根据车牌、发动机号、车架号，查询违章详细表，查找该车辆是否有未处理的违章
                    long peccancy_coutn = this.vehicleManageDao.getVehiclePeccancyCount(carframe_no , engine_no , license_plate);
                    if(peccancy_coutn > 0) {
                        has_peccancy = true;
                    }
                }
                if(has_peccancy) {//存在违章，更新车辆主表
                    this.vehicleManageDao.updateVehiclePeccancyStatus(carframe_no , engine_no , license_plate , 1);
                }
            }
            return result;
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
    public Map<String , Object> getVehicleInsuranceRemindList(long original_org , String carframe_no , String engine_no , String current_city , String license_plate , int start , int size) {
        int remind_day = Integer.valueOf(appProps.get("vehicle.insurance.remind.day").toString());//还有多少天需要提醒
        long total = this.vehicleManageDao.getInsuranceRemindCount(original_org , carframe_no , engine_no , current_city , license_plate , remind_day);//需要提醒保险到期车辆总数
        List<VehicleInfo> vehicle_insurance_remind_list = this.vehicleManageDao.getVehicleInsuranceList(original_org  , carframe_no , engine_no , license_plate , current_city , remind_day , start , size);
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

    public Map<String , Object> getVehiclePeccancyRemindList(long original_org , String current_city , String license_plate , int start , int size) {
        long total = this.vehicleManageDao.getVehiclePeccancyRemindCount(original_org, current_city, license_plate);//需要提醒违章处理车辆总数
        List<VehicleInfo> vehicle_peccancy_remind_list = this.vehicleManageDao.getVehiclePeccancyRemindList(original_org, license_plate, current_city, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicle_peccancy_remind_list" , vehicle_peccancy_remind_list);
        return map;
    }
    
    public VehiclePeccancy getVehiclePeccancy(long id) {
        return this.vehicleManageDao.getVehiclePeccancy(id);
    }

    /**
     * 执行处理违章记录
     * @param id
     * @param carframe_no
     * @param engine_no
     * @param license_plate
     * @param peccancy_at
     * @param peccancy_place
     * @param peccancy_reason
     * @param score
     * @param status
     * @param peccancy_price
     * @param arbitration
     * @param userid
     * @return
     */
    public int peccancyDoHandle(long id , String carframe_no , String engine_no , String license_plate , String peccancy_at , String peccancy_place ,
                                  String peccancy_reason , long score , int status , double peccancy_price , String arbitration , long userid) {

        try{
            Date peccancy_at_date = DateUtil.string2Date(peccancy_at , "yyyy-MM-dd");
            int result = this.vehicleManageDao.peccancyDoHandle(id, carframe_no, engine_no, license_plate, peccancy_at_date,
                    peccancy_place, peccancy_reason, score, status, peccancy_price, arbitration, userid);
            if(result > 0) {//更新成功后，判断是否还有违章记录，需要更新汽车主表的违章记录状态
                long peccancy_coutn = this.vehicleManageDao.getVehiclePeccancyCount(carframe_no , engine_no , license_plate);
                if(peccancy_coutn > 0) {//存在违章，更新车辆主表
                    this.vehicleManageDao.updateVehiclePeccancyStatus(carframe_no , engine_no , license_plate , 1);
                } else {
                    this.vehicleManageDao.updateVehiclePeccancyStatus(carframe_no , engine_no , license_plate , 0);
                }
            }
            return result;
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }
    }

    public Map<String , Object> getVehicleMaintainRemindList(long original_org , String current_city , String license_plate , int start , int size) {
        long remind_km = Long.valueOf(appProps.get("vehicle.maintain.remind.km").toString());//保养提醒公里数
        long total = this.vehicleManageDao.getVehicleMaintainRemindCount(original_org, current_city, license_plate, remind_km);//需要提醒保养车辆总数
        List<VehicleInfo> vehicle_maintain_remind_list = this.vehicleManageDao.getVehicleMaintainRemindList(original_org, license_plate, current_city, remind_km, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicle_maintain_remind_list" , vehicle_maintain_remind_list);
        return map;
    }

    public Map<String , Object> getVehicleMaintainDetai(String carframe_no , int start , int size) {
        long total = this.vehicleManageDao.getVehicleMaintainDetail(carframe_no);
        List<VehicleMaintail> vehicleMaintailList = this.vehicleManageDao.getVechicleMaintainDetaiList(carframe_no , start , size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicle_maintain_list" , vehicleMaintailList);
        return map;
    }

    public VehicleInfo getVehicleInfoByid(long id) {
        return this.vehicleManageDao.getVehicleInfoByid(id);
    }

    public int maintainRecordDoAdd(String carframe_no , String engine_no , String license_plate , String maintain_at ,
                                   String maintain_content , double maintain_price , long current_km , long next_maintain_km ,
                                   long user_id , String user_name , long create_by) {
        try{
            Date maintain_at_date = DateUtil.string2Date(maintain_at , "yyyy-MM-dd");
            int result = this.vehicleManageDao.addVehicleMaintain(carframe_no, engine_no, license_plate, maintain_at_date, maintain_content,
                   maintain_price, current_km, next_maintain_km, user_id, user_name, create_by);
            if(result > 0) {//插入保养记录表成功后，需要更新车辆主表
                this.vehicleManageDao.updateVehicleRemind(carframe_no , engine_no , license_plate , current_km , next_maintain_km , next_maintain_km-current_km);
            }
            return result;
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }
    }

    public String peccancyTimeGetCustomer(String license_plate , String peccancy_at) {
        String customer_name_cer_no = "";
        try{
//            Date peccancy_at_date = DateUtil.string2Date(peccancy_at , "yyyy-MM-dd HH:mm");
            //根据车牌，违章时间，查询合同车辆表，得到违章时间所属的合同id
            //然后再根据合同id，到合同主表中查询客户姓名和客户证件号码
            List<VehicleContraceVehsInfo> vehicleContraceVehsInfoList = this.vehicleManageDao.getContraceVehicles(license_plate , peccancy_at);
            VehicleContraceVehsInfo vehicleContraceVehsInfo = vehicleContraceVehsInfoList.get(0);
            if(vehicleContraceVehsInfo != null) {
                long contrace_id = vehicleContraceVehsInfo.getContrace_id();
                VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageDao.getVehicleContraceInfoById(contrace_id);
                customer_name_cer_no = vehicleContraceInfo.getCustomer_name() + "|" + vehicleContraceInfo.getCustomer_cer_no();
            }
        } catch(Exception e) {
            logger.error(e.getMessage() , e);
        }
        return customer_name_cer_no;
    }

}
