package com.carfinance.module.customermanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.customermanage.dao.CustomerManageDao;
import com.carfinance.module.customermanage.domain.CustomerInfo;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
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
public class CustomerManageService {
	
	static Logger logger = LoggerFactory.getLogger(CustomerManageService.class);
	
	@Autowired
	private ManageMemcacdedClient memcachedClient;
	@Autowired
	private CustomerManageDao customerManageDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitService initService;
    @Autowired
    private CommonDao commonDao;


    public Map<String , Object> getCustomerList(String identity_id , int start , int size) {
        long total = (identity_id == null || "".equals(identity_id)) ? this.customerManageDao.getCustomerCount() : 1;
        List<CustomerInfo> customer_list = this.customerManageDao.getCustomerList(identity_id, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("customer_list" , customer_list);
        return map;
    }


    public int addCustomerInfo(String identity_id , String customer_name , String customer_dn , String customer_email , long create_by) {

        try{
            return this.customerManageDao.addCustomerInfo(identity_id , customer_name , customer_dn , customer_email , create_by);
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }

    }

}
