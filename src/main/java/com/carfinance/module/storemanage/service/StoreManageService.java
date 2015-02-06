package com.carfinance.module.storemanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.domain.Menu;
import com.carfinance.module.common.domain.Org;
import com.carfinance.module.common.domain.Role;
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
        return this.storeManageDao.createStore(province_id , city_id , country_id , store_type , pid ,  store_name , store_address);
    }

}
