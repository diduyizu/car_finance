package com.carfinance.module.customermanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.customermanage.dao.CustomerManageDao;
import com.carfinance.module.customermanage.domain.CustomerAnnex;
import com.carfinance.module.customermanage.domain.CustomerInfo;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehicleservicemanage.domain.PropertyContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

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
    @Autowired
    private Properties appProps;


    public Map<String , Object> getCustomerList(String customer_name , String dn , String certificate_no , int start , int size) {
        long total = this.customerManageDao.getCustomerCount(customer_name , dn , certificate_no);
        List<CustomerInfo> customer_list = this.customerManageDao.getCustomerList(customer_name , dn , certificate_no, start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("customer_list" , customer_list);
        return map;
    }


//    public int addCustomerInfo(String certificate_type , String certificate_no , String customer_name , String customer_dn , String customer_email , String customer_type , String customer_house , String customer_vehicle , String customer_guarantee , String vip_no , long create_by) {
//        try{
//            long customer_count = this.customerManageDao.getCustomerCount(null , null , certificate_no);//根据身份证件号码，查询是否有重复
//            if(customer_count > 0) return -1;//表示该证件号码，已经被使用
//            return this.customerManageDao.addCustomerInfo(certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , vip_no , create_by);
//        } catch (Exception e) {
//            logger.info(e.getMessage() , e);
//            return 0;
//        }
//    }
    public long addCustomerInfo(HttpServletRequest request , CommonsMultipartFile file_upload , String certificate_type , String certificate_no , String customer_name , String customer_dn , String customer_email , String customer_type , String customer_house , String customer_vehicle , String customer_guarantee , String vip_no , long create_by) {
        try{
            long customer_count = this.customerManageDao.getCustomerCount(null , null , certificate_no);//根据身份证件号码，查询是否有重复
            if(customer_count > 0) return -1;//表示该证件号码，已经被使用
            long customer_id = this.customerManageDao.addCustomerInfo(certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , vip_no , create_by);
            if(customer_id > 0) {//插入客户表成功，进行附件操作
                if(file_upload != null) {
                    //保存文件
                    String savePath = request.getSession().getServletContext().getRealPath("/");
                    String sharespace = appProps.getProperty("customercertificate.dbpath").replace("${customer_id}", String.valueOf(customer_id));
                    savePath = savePath + sharespace;
                    logger.info("savePath=" + savePath);

                    Map<String , Object> map = this.commonService.saveFile(file_upload , savePath);
                    if(map != null) {
                        String annex_name = (String)map.get("annexName");
                        String file_name = (String)map.get("file_name");
                        String db_url = sharespace + file_name;
                        this.customerManageDao.addCustomerCertificateUrl(customer_id, annex_name, db_url);
                    }
                }
            }
            return customer_id;
       } catch (Exception e) {
            logger.info(e.getMessage() , e);
       }
       return 0;
    }

    public CustomerInfo getCustomrInfobyId(long id) {
        return this.customerManageDao.getCustomrInfobyId(id);
    }

    public CustomerInfo getCustomrInfobyCertificateNo(String certificate_no) {
        return this.customerManageDao.getCustomrInfobyCertificateNo(certificate_no);
    }

//    public int modifyCustomerInfo(long id , String certificate_type , String certificate_no , String customer_name , String customer_dn , String customer_email , String customer_type , String customer_house , String customer_vehicle , String customer_guarantee , String vip_no , long create_by) {
//        try{
//            CustomerInfo customerInfo = this.customerManageDao.getCustomrInfobyId(id);
//            if(customerInfo != null) {
//                if(!certificate_no.equals(customerInfo.getCertificate_no())) {
//                    long customer_count = this.customerManageDao.getCustomerCount(null , null , certificate_no);//根据身份证件号码，查询是否有重复
//                    if(customer_count > 0) return -1;//表示该证件号码，已经被使用
//                }
//                return this.customerManageDao.modifyCustomerInfo(id , certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , vip_no , create_by);
//            }
//        } catch (Exception e) {
//            logger.info(e.getMessage() , e);
//        }
//        return 0;
//    }
    public int modifyCustomerInfo(HttpServletRequest request , CommonsMultipartFile file_upload , long id , String certificate_type , String certificate_no , String customer_name , String customer_dn , String customer_email , String customer_type , String customer_house , String customer_vehicle , String customer_guarantee , String vip_no , long create_by) {
        try{
            CustomerInfo customerInfo = this.customerManageDao.getCustomrInfobyId(id);
            if(customerInfo != null) {
                if(!certificate_no.equals(customerInfo.getCertificate_no())) {
                    long customer_count = this.customerManageDao.getCustomerCount(null , null , certificate_no);//根据身份证件号码，查询是否有重复
                    if(customer_count > 0) return -1;//表示该证件号码，已经被使用
                }

                //保存文件
                String savePath = request.getSession().getServletContext().getRealPath("/");
                String sharespace = appProps.getProperty("customercertificate.dbpath").replace("${customer_id}", String.valueOf(id));
                savePath = savePath + sharespace;
                logger.info("savePath=" + savePath);

                Map<String , Object> map = this.commonService.saveFile(file_upload , savePath);
                if(map != null) {
                    String annex_name = (String)map.get("annexName");
                    String file_name = (String)map.get("file_name");
                    String db_url = sharespace + file_name;
                    this.customerManageDao.addCustomerCertificateUrl(id, annex_name, db_url);
                }

                return this.customerManageDao.modifyCustomerInfo(id , certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , vip_no , create_by);
            }
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
        }
        return 0;
    }

    public List<CustomerAnnex> getCustomrAnnexListbyCustomerId(long customer_id) {
        return this.customerManageDao.getCustomrAnnexListbyCustomerId(customer_id);
    }

    public void annexUpload(HttpServletRequest request , CommonsMultipartFile[] file_upload , long customer_id, long user_id) {
        if(file_upload != null && file_upload.length > 0){
            //循环获取file数组中得文件
            for(int i = 0;i<file_upload.length;i++){
                CommonsMultipartFile file = file_upload[i];
                //保存文件
                String savePath = request.getSession().getServletContext().getRealPath("/");
                String sharespace = appProps.getProperty("customerannex.dbpath").replace("${customer_id}", String.valueOf(customer_id));
                savePath = savePath + sharespace;
                logger.info("savePath=" + savePath);

                Map<String , Object> map = this.commonService.saveFile(file , savePath);
                if(map != null) {
                    String annex_name = (String)map.get("annexName");
                    String file_name = (String)map.get("file_name");
                    String db_url = sharespace + file_name;

                    //根据i都值，确定更新哪一个字段
                    //i从0-3，客户附件总共4个
                    this.customerManageDao.updateCustomerAnnex(customer_id , annex_name , db_url , i);
                }
            }
        }
    }

    /**
     * 根据客户证件号码，查询该客户办理过的零租业务
     * @param certificate_no
     * @return
     */
    public List<VehicleContraceInfo> getVehicleContraceListByCustomerCerNo(String certificate_no) {
        return this.customerManageDao.getVehicleContraceListByCustomerCerNo(certificate_no);
    }

    /**
     * 根据客户证件号码，查询该客户办理过的产权租业务
     * @param certificate_no
     * @return
     */
    public List<PropertyContraceInfo> getPropertyContraceListByCustomerCerNo(String certificate_no) {
        return this.customerManageDao.getPropertyContraceListByCustomerCerNo(certificate_no);
    }

}
