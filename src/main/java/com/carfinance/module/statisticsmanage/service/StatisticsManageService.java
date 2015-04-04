package com.carfinance.module.statisticsmanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.statisticsmanage.dao.StatisticsManageDao;
import com.carfinance.module.statisticsmanage.domain.Achievement;
import com.carfinance.module.statisticsmanage.domain.AchievementRowMapper;
import com.carfinance.module.statisticsmanage.domain.VehicleIncom;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceVehsInfo;
import com.carfinance.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;
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

    private Map<String , Date> getStringToDate(String begin_date , String end_date) {
        Date begin = null;
        Date end = null;
        if(begin_date != null) {
            try{
                begin_date = begin_date + " 00:00:00";
                begin = DateUtil.string2Date(begin_date, "yyyy-MM-dd HH:mm:ss");
            } catch (Exception e) {
                logger.error(e.getMessage() , e);
            }
        }
        if(end_date != null) {
            try{
                end_date = end_date + " 23:59:59";
                end = DateUtil.string2Date(end_date , "yyyy-MM-dd HH:mm:ss");
            } catch (Exception e) {
                logger.error(e.getMessage() , e);
            }
        }
        Map<String , Date> map = new HashMap<String, Date>();
        map.put("begin" , begin);
        map.put("end" , end);

        return map;
    }


    public Map<String , Object> getReoprtList(String vehicle_model , String license_plate , String report_type , int start , int size) {
//        String begin_date = null;
//        String end_date = null;

        Map<String , Date> beginEndMap = this.commonService.getBeginEndDate(report_type);
        Date begin_date = beginEndMap.get("begin");
        Date end_date = beginEndMap.get("end");

        long total = this.statisticsManageDao.getReoprtCount(vehicle_model, license_plate, begin_date, end_date);
        List<VehicleIncom> vehicleList =  this.statisticsManageDao.getReoprtList(vehicle_model, license_plate, begin_date, end_date, start, size);
        for(VehicleIncom vehicleIncom : vehicleList) {
            double total_price = vehicleIncom.getOver_price() + vehicleIncom.getActually_price();
            DecimalFormat df = new DecimalFormat("#.00");
            total_price = Double.valueOf(df.format(total_price));

            vehicleIncom.setTotal_price(total_price);
        }

        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicle_list" , vehicleList);
        return map;
    }

    public Map<String , Object> getVehicleList(String vehicle_model , String license_plate , String begin_date , String end_date , int start , int size) {
        Map<String , Date> map_tmp = getStringToDate(begin_date , end_date);
        Date begin = map_tmp.get("begin");
        Date end =  map_tmp.get("end");

        long total = this.statisticsManageDao.getVehicleCount(vehicle_model , license_plate , begin , end);
        List<VehicleIncom> vehicleList =  this.statisticsManageDao.getVehicleList(vehicle_model, license_plate,  begin , end , start, size);
        for(VehicleIncom vehicleIncom : vehicleList) {
            double total_price = vehicleIncom.getOver_price() + vehicleIncom.getActually_price();
            DecimalFormat df = new DecimalFormat("#.00");
            total_price = Double.valueOf(df.format(total_price));

            vehicleIncom.setTotal_price(total_price);
        }

        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("vehicle_list" , vehicleList);
        return map;
    }

    public Map<String , Object> getOrgEmployeeList(long org_id , String employee_id , String begin_date , String end_date , int start , int size) {
        Map<String , Date> map_tmp = getStringToDate(begin_date , end_date);
        Date begin = map_tmp.get("begin");
        Date end =  map_tmp.get("end");

        long total = this.statisticsManageDao.getOrgEmployeeCount(org_id , employee_id  , begin , end);
        List<Achievement> achievementList =  this.statisticsManageDao.getOrgEmployeeList(org_id, employee_id,  begin , end , start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("achievement_list" , achievementList);
        return map;
    }

}
