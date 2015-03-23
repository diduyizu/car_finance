package com.carfinance.module.common.service;

import java.io.File;
import java.io.IOException;
import java.security.Timestamp;
import java.util.*;

import com.carfinance.module.common.domain.*;
import com.carfinance.module.common.domain.Enum;
import com.carfinance.module.common.vo.MenuVo;
import com.carfinance.module.customermanage.domain.CustomerInfo;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.utils.DateUtil;
import net.sf.json.JSONArray;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carfinance.core.cache.CacheKey;
import com.carfinance.module.common.dao.CommonDao;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class CommonService {
	
	static Logger logger = LoggerFactory.getLogger(CommonService.class);
	
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private ManageMemcacdedClient memcachedClient;
    @Autowired
    private Properties appProps;
    @Autowired
    private InitService initService;

    /**
     * 获取用户可操作的菜单列表
     * 一个用户可以对应多个角色
     * 这里获取的是用户所有的菜单，包括顶层菜单，以及其各层子菜单
     * @return
     */
    public Map<String , Object> getUserMenuList(List<UserRole> user_role_list) {
        Map<String , Object> result_map = new HashMap<String, Object>();
        try {
            List<Menu> top_menu_list = new ArrayList<Menu>();//用户顶层菜单
            List<Map<String , Object>> return_list = new ArrayList<Map<String , Object>>();//用户树状结构菜单列表，需要转换成json格式返回到页面
            List<Menu> user_all_menu_list = new ArrayList<Menu>();//用户所有角色下的所有权限

            for(UserRole role : user_role_list) {
                long role_id = role.getRole_id();
                //根据role_id获取该角色对应点菜单
                List<Menu> role_menu_list = this.getRoleMenuList(role_id);//这个是顶层菜单，还需要查找该顶层菜单下点所有二级菜单
                user_all_menu_list.addAll(role_menu_list);
            }

            for(Menu menu : user_all_menu_list) {
                Map<String , Object> aaa = new HashMap<String, Object>();
                if(menu.getPid() == 0) {//顶层菜单，需要组装子菜单
                    boolean isContainTopMenu = false;
                    long menu_id = menu.getMenu_id();//顶层菜单id
                    for(Menu top_menu : top_menu_list) {
                        if(top_menu.getMenu_id() == menu_id) {
                            isContainTopMenu = true;
                            break;
                        }
                    }
                    if(!isContainTopMenu) {//顶层菜单列表中没有该菜单，将该菜单加入到顶层菜单列表中
                        top_menu_list.add(menu);
                    }

                    List<Menu> sub_menu_list = new ArrayList<Menu>();//子菜单
                    for(Menu sub_menu : user_all_menu_list) {
                        if(sub_menu.getPid() == menu_id) {
                            boolean isContain = false;
                            for(Menu tmp : sub_menu_list) {
                                if(sub_menu.getMenu_id() == tmp.getMenu_id()) {
                                    isContain = true;
                                    break;
                                }
                            }
                            if(!isContain) {//不包含，才将本次的sub_menu加入
                                sub_menu_list.add(sub_menu);
                            }
                        }
                    }

//                    List<Menu> sub_menu_list = this.commonDao.getMenuListByTopMenuid(menu_id);//子菜单列表
                    //将上一步得到的菜单列表，组合成页面所需的格式
                    List<MenuVo> sub_menu_vo_list = new ArrayList<MenuVo>();
                    for(Menu sub_menu : sub_menu_list) {
                        MenuVo menu_vo = new MenuVo();
                        menu_vo.setId(sub_menu.getMenu_id());
                        menu_vo.setText(sub_menu.getMenu_name());
                        menu_vo.setHref(appProps.getProperty("base.url") + sub_menu.getMenu_url());
                        sub_menu_vo_list.add(menu_vo);
                    }

                    Map<String , Object> tmp = new HashMap<String, Object>();
                    tmp.put("text" , menu.getMenu_name());
                    tmp.put("items" , sub_menu_vo_list);

                    List<Map<String , Object>> tmp_list = new ArrayList<Map<String, Object>>();
                    tmp_list.add(tmp);
                    aaa.put("id" , menu_id);
                    aaa.put("homePage" , menu.getHome_page_id());
                    aaa.put("menu" , tmp_list);

                    return_list.add(aaa);
                }

            }
            result_map.put("top_menu_ist" , top_menu_list);
            result_map.put("menu_list" , new ObjectMapper().writeValueAsString(return_list));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result_map;
    }

    /**
     * 获取单个角色对应的菜单
     * @param role_id
     * @return
     */
    public List<Menu> getRoleMenuList(long role_id) {
        String key = CacheKey.getRoleMenuListKey(role_id);
        List<Menu> role_menu_list = (List<Menu>)memcachedClient.get(key);
        if(role_menu_list == null) {
            role_menu_list = commonDao.getRoleMenuList(role_id);
            memcachedClient.set(key, 60*60, role_menu_list);
        }
        return role_menu_list;
    }

    /**
     * 获取省份、地市对应关系信息
     * @param province_id
     * @return
     */
    public List<City> getProvinceCityList(long province_id) {
        String key = CacheKey.getProvinceCityKey(province_id);
        List<City> city_list = (List<City>)memcachedClient.get(key);
        if(city_list == null) {
            city_list = commonDao.getProvinceCityList(province_id);
            memcachedClient.set(key, 60*60, city_list);
        }
        return city_list;
    }

    /**
     * 获取地市、区县对应关系信息
     * @param city_id
     * @return
     */
    public List<Country> getCityCountryList(long city_id) {
        String key = CacheKey.geCityCountryKey(city_id);
        List<Country> country_list = (List<Country>)memcachedClient.get(key);
        if(country_list == null) {
            country_list = commonDao.getCityCountryList(city_id);
            memcachedClient.set(key, 60*60, country_list);
        }
        return country_list;
    }

    /**
     * 获取用户角色列表，一个用户可能又多个角色
     * @param user_id
     * @return
     */
    public List<UserRole> getUserRoleList(long user_id) {
        String key = CacheKey.getUserRoleListKey(user_id);
        List<UserRole> user_role_list = (List<UserRole>)memcachedClient.get(key);
        if(user_role_list == null) {
            user_role_list = commonDao.getUserRoleList(user_id);
            memcachedClient.set(key, 60*60, user_role_list);
        }
        return user_role_list;
    }

    /**
     * 根据user_id获取用户
     * @param user_id
     * @return
     */
    public User getUserById(long user_id) {
        return this.commonDao.getUserById(user_id);
    }

    /**
     * 获取用户所在组织
     * @param user_id
     * @return
     */
//    public List<Org> getUserOrgList(long user_id) {
//        return this.commonDao.getUserOrgList(user_id);
//    }

    /**
     * 获取枚举表中某一类型的枚举值
     * @param fiel_name
     * @return
     */
    public List<Enum> getEnumFielList(String fiel_name) {
        List<Enum> enum_list = this.initService.getEnum();
        Map<String , List<Enum>> map = new HashMap<String, List<Enum>>();

        for(Enum en : enum_list) {
            List<Enum> tmp_list = new ArrayList<Enum>();
            if(map.get(en.getFiel_name()) != null) {
                tmp_list = map.get(en.getFiel_name());
            }
            tmp_list.add(en);
            map.put(en.getFiel_name() , tmp_list);
        }
        return map.get(fiel_name);
    }

    /**
     * 获取系统中已经被使用过的地市
     * 已经被使用过的判断为：
     *
     * @return
     */
    public List<City> getSysUsedCityList() {
        String key = CacheKey.getSysUsedCityList();
        List<City> city_list = (List<City>)memcachedClient.get(key);
        if(city_list == null) {
            city_list = commonDao.getSysUsedCityList();
            memcachedClient.set(key, 60*60, city_list);
        }
        return city_list;
    }

    /**
     * 递归查找org_list下所有org，放到sub_org_list中返回
     * @param org_list
     * @param sub_org_list
     * @return
     */
    private List<Org> getUserSubOrgList(List<Org> org_list , List<Org> sub_org_list) {
        String all_orgid = "";
        for(Org org : org_list) {
            if(org.getOrg_type() < 14) {//小于14的，才会存在子门店
                if("".equals(all_orgid)) {
                    all_orgid = org.getOrg_id()+"";
                } else {
                    all_orgid = all_orgid + "," + org.getOrg_id();
                }
            }
        }
        //获取当前用户的所在组织的所有子门店
        if(!"".equals(all_orgid)) {
            List<Org> sub_tmp = this.commonDao.getUserSubOrgList(all_orgid);
            sub_org_list.addAll(sub_tmp);
            this.getUserSubOrgList(sub_tmp, sub_org_list);
        }
        return sub_org_list;
    }

    /**
     * 获取用户可操作的所有门店列表
     * 树状结构关系
     * 如果用户所在某个门店，下面有管辖子门店，则子门店一并返回
     * @return
     */
    public List<Org> getUserAllOrgList(long user_id) {
        //首先，先要获取该用户所在组织，然后判断该组织是否有子门店
        List<Org> org_list = this.commonDao.getUserOrgList(user_id);//用户当前所在门店
        List<Org> sub_list = new ArrayList<Org>();
        sub_list = this.getUserSubOrgList(org_list , sub_list);//用户所在门店的所有子门店

        //整合所有该用户的门店，包括父门店、子门店
        List<Org> user_all_org_list = new ArrayList<Org>();
        user_all_org_list.addAll(org_list);
        for(Org sub_org : sub_list) {
            boolean flag = false;//是否有重复的
            for(Org org : org_list) {
                if(org.getOrg_id() == sub_org.getOrg_id()) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                user_all_org_list.add(sub_org);
            }
        }

        return user_all_org_list;
    }

    /**
     * 根据用户、角色，获取该用户所有组织列表
     * @param user_id
     * @param role_id
     * @return
     */
    public List<Org> getUserRoleOrgList(long user_id , long role_id) {
        return this.commonDao.getUserRoleOrgList(user_id , role_id);
    }

    /**
     * 判断某用户是否是管理员
     * @param user_id
     * @return
     */
    public boolean isSysadmin(long user_id) {
        boolean isSysadmin = false;
        List<UserRole> user_role_list = this.getUserRoleList(user_id);
        for(UserRole user_role : user_role_list) {
            if(user_role.getRole_id() == 10000) {
                isSysadmin = true;
                break;
            }
        }
        return isSysadmin;
    }

    //TODO 需要修改为从缓存中获取，同时客户资料增加、修改、删除时，需要删除
    private List<CustomerInfo> getAllCustomerInfo() {
        return this.commonDao.getAllCustomerInfo();
    }

    //TODO 需要修改为从缓存中获取，同时车辆资料增加、修改、删除时，需要删除缓存
    private List<VehicleInfo> getAllVehicles() {
        return this.commonDao.getAllVehicles();
    }

    public List<User> getAllEmployees() {
        return this.commonDao.getAllEmployees();
    }

    public String getAllCustomerName() {
        List<CustomerInfo> customer_info_list = this.getAllCustomerInfo();
        List<String> result = new ArrayList<String>();
        for(CustomerInfo customerInfo : customer_info_list) {
            if(!result.contains(customerInfo.getCustomer_name())) result.add(customerInfo.getCustomer_name());
        }
        String return_json = JSONArray.fromObject(result).toString();
        logger.info("customer_name_json :" + return_json);
        return return_json;
    }

    public String getAllCustomerDn() {
        List<CustomerInfo> customer_info_list = this.getAllCustomerInfo();
        List<String> result = new ArrayList<String>();
        for(CustomerInfo customerInfo : customer_info_list) {
            if(!result.contains(customerInfo.getCustomer_dn())) result.add(customerInfo.getCustomer_dn());
        }
        String return_json = JSONArray.fromObject(result).toString();
        logger.info("customer_dn_json :" + return_json);
        return return_json;
    }

    public String getAllCustomerNameAndCertificateNo() {
        List<CustomerInfo> customer_info_list = this.getAllCustomerInfo();
        List<String> result = new ArrayList<String>();
        for(CustomerInfo customerInfo : customer_info_list) {
            result.add(customerInfo.getCustomer_name() + "|" + customerInfo.getCertificate_no());
        }
        String return_json = JSONArray.fromObject(result).toString();
        logger.info("customer_name_certification_no_json :" + return_json);
        return return_json;
    }

    public String getAllVehicleBrand() {
        List<VehicleInfo> vehicle_list = this.getAllVehicles();
        List<String> result = new ArrayList<String>();
        for(VehicleInfo vehicleInfo : vehicle_list) {
            if(!result.contains(vehicleInfo.getBrand())) result.add(vehicleInfo.getBrand());
        }
        String return_json = JSONArray.fromObject(result).toString();
        logger.info("vehicle_brand_json :" + return_json);
        return return_json;
    }

    public String getAllVehicleModel() {
        List<VehicleInfo> vehicle_list = this.getAllVehicles();
        List<String> result = new ArrayList<String>();
        for(VehicleInfo vehicleInfo : vehicle_list) {
            if(!result.contains(vehicleInfo.getModel())) result.add(vehicleInfo.getModel());
        }
        String return_json = JSONArray.fromObject(result).toString();
        logger.info("vehicle_model_json :" + return_json);
        return return_json;
    }

    public String getAllVehicleLicensePlate() {
        List<VehicleInfo> vehicle_list = this.getAllVehicles();
        List<String> result = new ArrayList<String>();
        for(VehicleInfo vehicleInfo : vehicle_list) {
            if(!result.contains(vehicleInfo.getLicense_plate())) result.add(vehicleInfo.getLicense_plate());
        }
        String return_json = JSONArray.fromObject(result).toString();
        logger.info("vehicle_license_plate_json :" + return_json);
        return return_json;
    }

    public String getAllEmployeeIdAndName() {
        List<User> user_list = this.getAllEmployees();
        List<String> result = new ArrayList<String>();
        for(User user : user_list) {
            result.add(user.getUser_name() + "|" + user.getEmployee_id());
        }
        String return_json = JSONArray.fromObject(result).toString();
        logger.info("user_employee_id_name_json :" + return_json);
        return return_json;
    }


    public Map<String , Object> saveFile(CommonsMultipartFile upload_file , String save_path) {
        try{
            File f = new File(save_path);
            if (!f.exists()) {
                logger.info("创建路径" + save_path);
                f.mkdirs();
            }
            String name = upload_file.getFileItem().getName();
            String extName = "";
            String annexName = "";
            if (name.lastIndexOf(".") >= 0) {
                name = name.substring(name.lastIndexOf("\\") + 1);
                extName = name.substring(name.lastIndexOf("."));
                annexName = name.substring(0, name.lastIndexOf("."));
            }
            if(!"".equals(extName)) {
                name = UUID.randomUUID().toString();
                File file = new File(save_path + name + extName);
                upload_file.getFileItem().write(file);

                String file_name = name + extName;
                Map<String , Object> map = new HashMap<String, Object>();
                map.put("annexName" , annexName);
                map.put("file_name" , file_name);
                return map;
            }
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
        }
        return null;
    }

    public List<Org> getSysAllOrgList() {
        return this.commonDao.getSysAllOrgList();
    }

    /**
     * 根据客户姓名，获取客户证件号码
     * 优先获取身份证，如果身份证没有，随机捞取用户证件号码
     * @param customer_name
     * @param customer_dn
     * @return
     */
    public Map<String , String> getCertificateNoByNameAndDn(String customer_name , String customer_dn) {
        String certificate_type = "";
        String certificate_no = "";
        //优先使用身份证获取
        List<CustomerInfo> customerInfoList = this.commonDao.getCertificateNoByNameAndDn(customer_name , customer_dn , "身份证");
        if(customerInfoList.size() > 0) {//根据身份证查到数据
            certificate_type = customerInfoList.get(0).getCertificate_type();
            certificate_no = customerInfoList.get(0).getCertificate_no();
            for(CustomerInfo customerInfo : customerInfoList) {//优先使用18位身份证号码
                if(customerInfo.getCertificate_no().length() == 18) {
                    certificate_type = customerInfo.getCertificate_type();
                    certificate_no = customerInfo.getCertificate_no();
                    break;
                }
            }
        } else {
            customerInfoList = this.commonDao.getCertificateNoByNameAndDn(customer_name, customer_dn, null);
            if(customerInfoList.size() > 0) {
                certificate_type = customerInfoList.get(0).getCertificate_type();
                certificate_no = customerInfoList.get(0).getCertificate_no();
            }
        }

        Map<String , String> map = new HashMap<String, String>();
        map.put("certificate_type" , certificate_type);
        map.put("certificate_no" , certificate_no);

        return map;
    }

}
