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


    public Map<String , Object> getCustomerList(String customer_name , String dn , String certificate_no , int start , int size) {
        long total = this.customerManageDao.getCustomerCount(customer_name , dn , certificate_no);
        List<CustomerInfo> customer_list = this.customerManageDao.getCustomerList(customer_name , dn , certificate_no, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("customer_list" , customer_list);
        return map;
    }


    public int addCustomerInfo(String certificate_type , String certificate_no , String customer_name , String customer_dn , String customer_email , String customer_type , String customer_house , String customer_vehicle , String customer_guarantee , long create_by) {
        try{
            long customer_count = this.customerManageDao.getCustomerCount(null , null , certificate_no);//根据身份证件号码，查询是否有重复
            if(customer_count > 0) return -1;//表示该证件号码，已经被使用
            return this.customerManageDao.addCustomerInfo(certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , create_by);
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }
    }

    public CustomerInfo getCustomrInfobyId(long id) {
        return this.customerManageDao.getCustomrInfobyId(id);
    }

    public int modifyCustomerInfo(long id , String certificate_type , String certificate_no , String customer_name , String customer_dn , String customer_email , String customer_type , String customer_house , String customer_vehicle , String customer_guarantee , long create_by) {
        try{
            long customer_count = this.customerManageDao.getCustomerCount(null , null , certificate_no);//根据身份证件号码，查询是否有重复
            if(customer_count > 0) return -1;//表示该证件号码，已经被使用
            return this.customerManageDao.modifyCustomerInfo(id , certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , create_by);
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
            return 0;
        }
    }

}
