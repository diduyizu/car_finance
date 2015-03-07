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
import net.sf.json.JSONArray;
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

    public int createStore(long province_id , long city_id , long store_type , String store_name , String store_address) {
        //根据store_type计算出pid
        //如果store_type == 13，那么根据找到province_id对应的省公司id
        //如果store_type > 13，那么根据city_id找到对应的市公司id
        long pid = -1;
        String org_type_name = "";
        if(store_type == 13) {//市公司，那么pid需要找省公司
            Org org = this.storeManageDao.getProvinceOrgInfo(province_id);
            org_type_name = "市公司（一类门店）";
            pid = org.getOrg_id();
        } else if(store_type > 13) {//二三级公司，那么需要找市公司
            Org org = this.storeManageDao.getCityOrgInfo(city_id);
            pid = org.getOrg_id();
            if(store_type == 14) {
                org_type_name = "二类门店";
            } else if(store_type == 15) {
                org_type_name = "三类门店";
            }
        }

        List<Enum> province_list = this.commonService.getEnumFielList("SYS_PROVINCE");
        List<City> city_list = this.commonService.getProvinceCityList(province_id);

        String org_province_name = "";
        String org_city_name = "";
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

        int result = this.storeManageDao.createStore(province_id , city_id , 0 , store_type , pid ,  store_name , store_address , org_type_name , org_province_name , org_city_name , "");
        if(result > 0) {//成功，更新使用地市
            this.storeManageDao.updateCityUsedStatus(city_id , province_id);
        }

        return result;
    }

    /**
     * 根据地市id，获取该归属该地市的门店信息
     * @param city_id
     * @return
     */
    public String getCityOrgInfo(long city_id) {
        Map<String , Object> map = new HashMap<String, Object>();
        Org org = this.storeManageDao.getCityOrgInfo(city_id);
        int city_have_org = 0;
        if(org != null) {
            city_have_org = 1;
            map.put("org" , org);
        }
        map.put("city_have_org" , city_have_org);
        return JSONArray.fromObject(map).toString();
    }

}
