package com.carfinance.module.vehicleservicemanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.domain.City;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.module.vehiclemanage.dao.VehicleManageDao;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.service.VehicleManageService;
import com.carfinance.module.vehicleservicemanage.dao.VehicleServiceManageDao;
import com.carfinance.module.vehicleservicemanage.domain.*;
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
    private VehicleManageDao vehicleManageDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitService initService;
    @Autowired
    private CommonDao commonDao;
    @Autowired
    private Properties appProps;

    public Map<String , Object> getOrgReservationList(long org_id , String customer_name , String dn , int start, int size) {
        long total = this.vehicleServiceManageDao.getOrgReservationCount(org_id, customer_name, dn);
        List<VehicleReservationInfo> reservation_list = this.vehicleServiceManageDao.getOrgReservationList(org_id, customer_name, dn, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("reservation_list", reservation_list);
        return map;
    }

    public Map<String , Object> getOrgReservationRemindList(long org_id , int remind_days , String customer_name , String dn , int start, int size) {
        long total = this.vehicleServiceManageDao.getOrgReservationRemindCount(org_id , remind_days , customer_name , dn);
        List<VehicleReservationInfo> reservation_list = this.vehicleServiceManageDao.getOrgReservationRemindList(org_id , remind_days , customer_name , dn ,  start , size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("reservation_list", reservation_list);
        return map;
    }

    public int addReservation(String original_org , long contrace_type , String customer_name , String customer_dn , String use_begin , String use_end ,
                             String employee_id , String employee_name , String remark , long user_id) {
        try{
            Date use_begin_date = DateUtil.string2Date(use_begin , "yyyy-MM-dd HH:mm");
            Date use_end_date = DateUtil.string2Date(use_end , "yyyy-MM-dd HH:mm");

            return this.vehicleServiceManageDao.addReservation(original_org , contrace_type , customer_name , customer_dn , use_begin_date , use_end_date ,
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
    public Map<String , Object> getOrgContraceList(long original_org, String status, int start, int size , boolean over_top) {
        long total = this.vehicleServiceManageDao.getOrgContraceCount(original_org, status , over_top);
        List<VehicleContraceInfo> contrace_list = this.vehicleServiceManageDao.getOrgContraceList(original_org, status, over_top , start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("contrace_list", contrace_list);
        return map;
    }

    public VehicleReservationInfo getVehicleReservationInfoById(long reservation_id)  {
        return this.vehicleServiceManageDao.getVehicleReservationInfoById(reservation_id);
    }


    public long addContrace(long reservation_id , long org_id , long user_id) {
        try{
            return this.vehicleServiceManageDao.addContrace(reservation_id , org_id , user_id);
        } catch(Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }
    }

    public long modifycontrace(long contrace_id , long reservation_id , long original_org , String contrace_no , String customer_name , String customer_type , String customer_dn ,
                               String certificate_type , String certificate_no , String use_begin , String use_end , String employee_id ,
                               String employee_name , String remark , long user_id,
                               double daily_price , long daily_available_km , double over_km_price , double over_hour_price , double month_price ,
                               long month_available_km , String monthly_day , double pre_payment , double deposit , double peccancy_deposit) {
        try{
            Date use_begin_date = DateUtil.string2Date(use_begin , "yyyy-MM-dd HH:mm");
            Date use_end_date = DateUtil.string2Date(use_end , "yyyy-MM-dd HH:mm");
            Date monthly_day_date = (monthly_day != null && !"".equals(monthly_day.trim())) ? DateUtil.string2Date(monthly_day.trim() , "yyyy-MM-dd") : null;
            long result = this.vehicleServiceManageDao.modifyContrace(contrace_id , original_org , contrace_no , customer_name , customer_type , customer_dn ,
                    certificate_type , certificate_no , use_begin_date , use_end_date , employee_id , employee_name , remark , user_id ,
                    daily_price , daily_available_km , over_km_price , over_hour_price , month_price , month_available_km , monthly_day_date ,
                    pre_payment , deposit , peccancy_deposit);
            if(result > 0) {//更新合同成功，将预约单状态修改为完结
                //查询预约单状态，如果是结单，则不更新
                VehicleReservationInfo vehicleReservationInfo = this.getVehicleReservationInfoById(reservation_id);
                if(vehicleReservationInfo.getStatus() != 1) {//预约单非完结状态
                    this.reservationDoCancel(reservation_id, user_id, 1);//将预约单状态改为完结
                }
            }

            return result;
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

    public VehicleContraceInfo getVehicleContraceInfoById(long contrace_id) {
        return this.vehicleServiceManageDao.getVehicleContraceInfoById(contrace_id);
    }

    public VehicleContraceInfo getVehicleContraceInfoByReservationId(long reservation_id) {
        return this.vehicleServiceManageDao.getVehicleContraceInfoByReservationId(reservation_id);
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
        //判断是否是系统管理员，如果是，则不需要匹配create_by
        boolean isSysadmin = this.commonService.isSysadmin(user_id);
        int result = this.vehicleServiceManageDao.contraceToShopAudit(contrace_id , user_id , isSysadmin);
        if(result > 0) {
            //判断该合同下所属车辆，是否超过一定金额，如果超过，则需要市店长、区域经理审核
            //获取该合同对应车辆，算出总价
            double total_price = this.vehicleServiceManageDao.getContraceVehicleTotalPrice(contrace_id);
            //需要市门店经理审批的限额
            double top = Double.valueOf(appProps.get("city.shopowner.audit.top").toString());
            if(total_price >= top) {//车辆总价，大于限额，则需要更新合同主表，市公司经理审核状态为：1-需要审核
                this.vehicleServiceManageDao.contraceNeedCityAudit(contrace_id , user_id);
            }
        }

        return result;
    }

    /**
     * 根据所有能够使用的车辆（车辆状态为：在库）
     * @param original_org
     * @param current_city
     * @param brand
     * @param vehicle_model
     * @param license_plate
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getContraceCanUseVehicleList(long original_org , String current_city , String brand , String vehicle_model , String license_plate ,  int start , int size) {
        long total = this.vehicleServiceManageDao.getContraceCanUseVehicleCount(original_org, current_city, brand, vehicle_model, license_plate);
        List<VehicleInfo> vehicle_list = this.vehicleServiceManageDao.getContraceCanUseVehicleList(original_org , current_city , brand , vehicle_model , license_plate , start , size);
        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
        for(VehicleInfo vehicleInfo : vehicle_list) {
            for(City city : sys_used_city_list) {
                if(vehicleInfo.getCurrent_city() == city.getCity_id()) {
                    vehicleInfo.setCurrent_city_name(city.getCity_name());
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
     * 门店店长审核
     * @param id
     * @param status
     * @param user_id
     * @return
     */
    public int shopownerDoAudit(long id , String status , long user_id) {
        return this.vehicleServiceManageDao.shopownerDoAudit(id , status , user_id);
    }

    /**
     * 市门店店长审核
     * @param id
     * @param status
     * @param user_id
     * @return
     */
    public int cityShopownerDoAudit(long id , String status , long user_id) {
        return this.vehicleServiceManageDao.cityShopownerDoAudit(id , status , user_id);
    }

    /**
     * 区域经理审核
     * @param id
     * @param status
     * @param user_id
     * @return
     */
    public int regionalManagerDoAudit(long id , String status , long user_id) {
        return this.vehicleServiceManageDao.regionalManagerDoAudit(id , status , user_id);
    }

    /**
     * 财务审核
     * @param id
     * @param status
     * @param user_id
     * @return
     */
    public int financeDoAudit(long id , String status , long user_id) {
        return this.vehicleServiceManageDao.financeDoAudit(id , status , user_id);
    }

    /**
     * 业务员选择车辆，加入合同
     * @param contrace_id
     * @param vehicle_id
     * @param user_id
     * @return
     */
    public int contraceDoChooseVech(long contrace_id , long vehicle_id , long user_id) {
        VehicleInfo vehicleInfo = this.vehicleManageDao.getVehicleInfoByid(vehicle_id);
        int result = this.vehicleServiceManageDao.contraceDoChooseVech(contrace_id , vehicle_id , user_id , vehicleInfo.getVehicle_price() , vehicleInfo.getLicense_plate() , vehicleInfo.getModel());
        if(result > 0) {//插入成功，更新该车辆状态为出库中
            this.vehicleServiceManageDao.updateVehicleStatus(vehicle_id , "出库中");
        }
        return result;
    }

    public Map<String , Object> getContraceVechList(long contrace_id , String brand , String vehicle_model , String license_plate , int start , int size) {
        long total = this.vehicleServiceManageDao.getContraceVechCount(contrace_id, brand, vehicle_model, license_plate);
        List<VehicleContraceVehsInfo> vehicle_list = this.vehicleServiceManageDao.getContraceVech(contrace_id, brand, vehicle_model, license_plate, start, size);

        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicle_list" , vehicle_list);
        return map;
    }

    public Map<String , Object> contraceVechDriverList(long original_org , int start , int size) {
        long total = this.vehicleServiceManageDao.getcontraceVechDriverCount(original_org);
        List<UserDriver> driver_list = this.vehicleServiceManageDao.getcontraceVechDriverList(original_org, start, size);

        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("driver_list" , driver_list);
        return map;
    }

    public int contraceDoChooseDriver(long veh_contrace_vehs_id, long driver_user_id , long user_id) {
        User user = this.commonDao.getUserById(driver_user_id);
        int result = this.vehicleServiceManageDao.contraceDoChooseDriver(veh_contrace_vehs_id, driver_user_id, user.getUser_name() , user_id);
        if(result > 0) {//更新配驾成功，更新配驾用户状态为已分配
            this.vehicleServiceManageDao.updateUserDriverStatus(driver_user_id , 1);
        }
        return result;
    }

    public int contraceCancelChooseVehicle(long veh_contrace_vehs_id , long user_id) {
        int result = this.vehicleServiceManageDao.contraceCancelChooseVehicle(veh_contrace_vehs_id, user_id);
        if(result > 0) {//更新取消车辆，更新车辆及配驾人员信息
            VehicleContraceVehsInfo vehicleContraceVehsInfo = this.vehicleServiceManageDao.getContraceVehicleByid(veh_contrace_vehs_id);
            if(vehicleContraceVehsInfo != null) {
                long vehicle_id = vehicleContraceVehsInfo.getVehicle_id();
                long driver_user_id = vehicleContraceVehsInfo.getDriving_user_id();

                this.vehicleServiceManageDao.updateVehicleStatus(vehicle_id , "在库");
                this.vehicleServiceManageDao.updateUserDriverStatus(driver_user_id , 0);
            }
        }
        return result;
    }

    public List<VehicleContraceVehsInfo> getVehicleContraceVehsListByContraceId(long contrace_id) {
        return this.vehicleServiceManageDao.getVehicleContraceVehsListByContraceId(contrace_id);
    }

    //获取合同应该收到的租金
    //包括合同正常行驶情况下的租金；车辆超时、超里程的赔偿金
    public double getContraceIncom(long contrace_id) {
        VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageDao.getVehicleContraceInfoById(contrace_id);

        double contrace_days = DateUtil.getTimeLag(vehicleContraceInfo.getUse_begin(), vehicleContraceInfo.getUse_end(), "day");//合同天数
        double daily_price = vehicleContraceInfo.getDaily_price();//合同约定每天价格

        double normal_price = contrace_days * daily_price;//正常金额，按照合同约定天数，以及每日单价计算得出

        double abnormal_price = 0;//不正常金额，车辆超时、超里程的赔偿金
        List<VehicleContraceVehsInfo> vehicleContraceVehsInfoList = this.vehicleServiceManageDao.getVehicleContraceVehsListByContraceId(contrace_id);
        for(VehicleContraceVehsInfo v : vehicleContraceVehsInfoList) {
            abnormal_price = abnormal_price + v.getOver_price();
        }

        double system_total_price = normal_price + abnormal_price;
        return system_total_price;


    }



    public Map<String , Object> calculateOvertimeAndKm(String use_begin , String use_end , String return_date , long vehicle_id , long return_km , long daily_available_km , double over_hour_price , double over_km_price) {
        VehicleInfo vehicleInfo = this.vehicleManageDao.getVehicleInfoByid(vehicle_id);

        //还车时，实际使用公里数
        long km_lag_tmp = return_km-vehicleInfo.getKm();
        //计算合同租车天数,开始——结束时间算
        double contrace_days = DateUtil.getTimeLag(use_begin, use_end, "day");
        //合同允许总公里数=合同允许每天公里数*合同使用天数
        double allow_km_all = daily_available_km * contrace_days;

        //实际还车时间-合同规定还车时间，不足1h按1h计算，向上取整，如果大于0，表示时间超标
        double time_lag = DateUtil.getTimeLag(use_end, return_date, "hour");
        //实际使用公里数-合同允许使用公里数，如果大于0，表示公里数超标
        double km_lag = km_lag_tmp - allow_km_all;

        //计算时间超标和公里数超标最终需要支付的超期费用
        double time_lag_money = 0;//超时费
        double km_lag_money = 0;//超公里费
        if(time_lag > 0) {
            time_lag_money = time_lag * over_hour_price;
        }
        if(km_lag > 0) {
            km_lag_money = km_lag * over_km_price;
        }
        double all_alg_money = time_lag_money + km_lag_money;

        Map<String , Object> map = new HashMap<String, Object>();
        map.put("time_lag" , time_lag > 0 ? time_lag : 0);
        map.put("km_lag" , km_lag > 0 ? km_lag : 0);
        map.put("all_alg_money" , all_alg_money);
        return map;
    }

    public int returnVehicle(long vehicle_contrace_id , long contrace_id , String return_time , long return_km , long vehicle_id , double over_price) {
        try{
            //更新合同车辆表
            Date return_time_date = DateUtil.string2Date(return_time , "yyyy-MM-dd HH:mm");
            int result = this.vehicleServiceManageDao.returnVehicle(vehicle_contrace_id , return_time_date , return_km , over_price);
            if(result > 0) {
                //更新车辆主表当前公里数为return_km
                this.vehicleServiceManageDao.updateVehicleKM(vehicle_id , return_km);
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
            return 0;
        }
    }


    /**
     * 业务员结单
     * @param contrace_id
     * @param system_total_price
     * @param arrange_price
     * @param actual_price
     * @param late_fee
     * @param user_id
     * @return
     */
    public int contraceDoFinish(long contrace_id , double system_total_price , double arrange_price , double actual_price , double late_fee , long user_id) {
        //判断合同对应的车辆是否都已经归还，如果没有全部归还，合同不能结单
        int count = this.vehicleServiceManageDao.getVehicleReturnStatus(contrace_id);
        if(count > 0) {
            return -1;//还有没有归还的车辆，不能结单
        }
        //结单，更新合同主表状态
        int is_arrearage = 0;
        if((arrange_price-actual_price) > 0 ) is_arrearage = 1;
        return this.vehicleServiceManageDao.contraceDofinish(contrace_id , system_total_price , arrange_price , actual_price , late_fee , is_arrearage , user_id);
    }

    /**
     * 获取合同欠租提醒列表
     * @param original_org
     * @param start
     * @param size
     * @return
     */
    public Map<String , Object> getArrearageRemindContraceList(long original_org, int start, int size) {
        long total = this.vehicleServiceManageDao.getArrearageRemindContraceCount(original_org);
        List<VehicleContraceInfo> contrace_list = this.vehicleServiceManageDao.getArrearageRemindContraceList(original_org, start, size);

        for(VehicleContraceInfo contrace : contrace_list) {
            String current_date = DateUtil.date2String(new Date());
            String arrearage_date = contrace.getArrearage_date();
            double arrearage_days = DateUtil.getTimeLag(arrearage_date , current_date , "day");//欠费天数
            contrace.setArrearage_days((long)arrearage_days);
        }

        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("contrace_list", contrace_list);
        return map;
    }


    public int contraceDoReturnMoney(long contrace_id , double back_lease_price , double back_overdue_price) {
        try{
            //更新合同主表
            VehicleContraceInfo contrace_info = this.vehicleServiceManageDao.getVehicleContraceInfoById(contrace_id);
            int is_arrearage;

            //约定金额 小于等于 实际付款金额+本次交欠款金额
            //同时 滞纳金 减 本次缴纳滞纳金 小于等于 0
            //表示该用户已经不欠款了
            if((contrace_info.getArrange_price() <= (contrace_info.getActual_price() + back_lease_price)) && ((contrace_info.getLate_fee() - back_overdue_price) <= 0) ) {
                is_arrearage = 0;
            } else {
                is_arrearage = 1;
            }

            int result = this.vehicleServiceManageDao.contraceDoReturnMoney(contrace_id, back_lease_price, back_overdue_price , is_arrearage);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
            return 0;
        }
    }


    public PropertyContraceInfo getPropertyContraceInfoByreservationid(long reservation_id) {
        return this.vehicleServiceManageDao.getPropertyContraceInfoByreservationid(reservation_id);
    }

    public long addPropertyContrace(long reservation_id, long org_id, long user_id) {
        return this.vehicleServiceManageDao.addPropertyContrace(reservation_id , org_id , user_id);
    }
}
