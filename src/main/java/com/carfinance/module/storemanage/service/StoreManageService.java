package com.carfinance.module.storemanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.domain.*;
import com.carfinance.module.common.domain.Enum;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.peoplemanage.dao.PeopleManageDao;
import com.carfinance.module.peoplemanage.domain.OrgUserRole;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.module.storemanage.domain.Store;
import com.carfinance.utils.DateUtil;
import com.carfinance.utils.MD5Util;
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
public class StoreManageService {
	
	static Logger logger = LoggerFactory.getLogger(StoreManageService.class);
	
	@Autowired
	private ManageMemcacdedClient memcachedClient;
	@Autowired
	private StoreManageDao storeManageDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitService initService;
    @Autowired
    private CommonDao commonDao;

    public Map<String , Object> getStoreList(String store_name , int start , int size) {
        long total = this.storeManageDao.getStoreCount(store_name);//门店总数
        List<Store> store_list = this.storeManageDao.getStoreList(store_name , start , size);

        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("store_list" , store_list);
        return map;
    }

    public int createStore(long province_id , long city_id , long country_id , long store_type , long pid ,  String store_name , String store_address) {
        List<Enum> org_type_list = this.commonService.getEnumFielList("ORG_TYPE");
        List<Enum> province_list = this.commonService.getEnumFielList("SYS_PROVINCE");
        List<City> city_list = this.commonService.getProvinceCityList(province_id);
        List<Country> country_list = this.commonService.getCityCountryList(city_id);
        String org_type_name = "";
        String org_province_name = "";
        String org_city_name = "";
        String org_country_name = "";
        for(Enum e : org_type_list) {
            if(e.getEnum_value() == store_type) {
                org_type_name = e.getEnum_desc();
                break;
            }
        }
        for(Enum e : province_list) {
            if(e.getEnum_value() == province_id) {
                org_province_name = e.getEnum_desc();
                break;
            }
        }
        for(City city : city_list) {
            if(city.getCity_id() == city_id) {
                org_city_name = city.getCity_name();
                break;
            }
        }
        for(Country country : country_list) {
            if(country.getCountry_id() == country_id) {
                org_country_name = country.getCountry_name();
                break;
            }
        }



        return this.storeManageDao.createStore(province_id , city_id , country_id , store_type , pid ,  store_name , store_address , org_type_name , org_province_name , org_city_name , org_country_name);
    }

}
