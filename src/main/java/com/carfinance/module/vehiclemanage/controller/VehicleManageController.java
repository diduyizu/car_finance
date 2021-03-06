package com.carfinance.module.vehiclemanage.controller;

import com.carfinance.module.common.domain.*;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInsurance;
import com.carfinance.module.vehiclemanage.domain.VehicleMaintail;
import com.carfinance.module.vehiclemanage.service.VehicleManageService;
import com.carfinance.module.vehiclemanage.domain.VehiclePeccancy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.Struct;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/vehicle")
public class VehicleManageController {
	final Logger logger = LoggerFactory.getLogger(VehicleManageController.class);
	
	@Autowired
	private CommonService commonService;
    @Autowired
    private VehicleManageService vehicleManageService;
	@Autowired
	private Properties appProps;

    /**
     * 车辆管理－车辆入库登记
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/register/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String registerIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("store.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String vehicle_current_org = request.getParameter("original_org");//车辆当前所在门店
        String current_city = request.getParameter("current_city");
        String brand = request.getParameter("brand");
        String vehicle_model = request.getParameter("model");
        String license_plate = request.getParameter("license_plate");
        String gps = request.getParameter("gps");
        String km_begin = request.getParameter("km_begin");
        String km_end = request.getParameter("km_end");
        String lease_status = request.getParameter("lease_status");
        String color = request.getParameter("color");

        String method = request.getMethod();
        if("GET".equals(method.toUpperCase())) {//get请求，进行编码格式转换
            brand = this.commonService.characterFormat(brand , "ISO8859-1" , "UTF-8");
            vehicle_model = this.commonService.characterFormat(vehicle_model , "ISO8859-1" , "UTF-8");
            license_plate = this.commonService.characterFormat(license_plate , "ISO8859-1" , "UTF-8");
            color = this.commonService.characterFormat(color , "ISO8859-1" , "UTF-8");
        }

//        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<Org> user_all_org_list = this.commonService.getSysAllOrgList();

        //获取用户角色列表
//        List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_all_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }

        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
//        Map<String , Object> map = this.vehicleManageService.getVehicleList(original_org , current_city , brand , vehicle_model , license_plate , gps , km_begin , km_end , lease_status , color  , start , size);
        Map<String , Object> map = this.vehicleManageService.getCurrentShopVehicleList(original_org, current_city, brand, vehicle_model, license_plate, gps, km_begin, km_end, lease_status, color, start, size);
        
        long total = (Long)map.get("total");;
        List<VehicleInfo> vehicle_list = (List<VehicleInfo>)map.get("vehicle_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        String vehicle_brand_json = this.commonService.getAllVehicleBrand();
        String vehicle_model_json = this.commonService.getAllVehicleModel();
        String vehicle_licene_plate_json = this.commonService.getAllVehicleLicensePlate();
        model.addAttribute("vehicle_brand_json" , vehicle_brand_json);
        model.addAttribute("vehicle_model_json" , vehicle_model_json);
        model.addAttribute("vehicle_licene_plate_json" , vehicle_licene_plate_json);


        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());
        String condition = "&original_org="+original_org;
        if(current_city != null) {
            condition = condition + "&current_city="+current_city;
        }
        if(brand != null) {
            condition = condition + "&brand="+brand;
        }
        if(vehicle_model != null) {
            condition = condition + "&model="+vehicle_model;
        }
        if(license_plate != null) {
            condition = condition + "&license_plate="+license_plate;
        }
        if(gps != null) {
            condition = condition + "&gps="+gps;
        }
        if(lease_status != null) {
            condition = condition + "&lease_status="+lease_status;
        }
        if(color != null) {
            condition = condition + "&color="+color;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("current_city" , current_city);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("original_org_name" , original_org_name);
        model.addAttribute("brand" , brand);
        model.addAttribute("model" , vehicle_model);
        model.addAttribute("license_plate" , license_plate);
        model.addAttribute("gps" , gps);
        model.addAttribute("km_begin" , km_begin);
        model.addAttribute("km_end" , km_end);
        model.addAttribute("color" , color);

        model.addAttribute("sys_used_city_list" , sys_used_city_list);
//        model.addAttribute("user_role_list" , user_role_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_list" , vehicle_list);
        return "/module/vehiclemanage/register/index";
    }

    /**
     * 车辆管理－车辆入库登记
     * 点“新增”，跳转新增页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/register/add" , method = RequestMethod.GET)
    public String registerAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();

        String vehicle_brand_json = this.commonService.getAllVehicleBrand();
        String vehicle_model_json = this.commonService.getAllVehicleModel();
        String vehicle_licene_plate_json = this.commonService.getAllVehicleLicensePlate();
        model.addAttribute("vehicle_brand_json" , vehicle_brand_json);
        model.addAttribute("vehicle_model_json" , vehicle_model_json);
        model.addAttribute("vehicle_licene_plate_json" , vehicle_licene_plate_json);

        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        return "/module/vehiclemanage/register/add";
    }

    /**
     * 新增页面，执行新增操作
     * @param model1
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/register/doadd" , method = RequestMethod.POST)
    @ResponseBody
    public int registerDoAdd(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        String carframe_no = request.getParameter("carframe_no");
        String engine_no = request.getParameter("engine_no");
        String buy_at = request.getParameter("buy_at");
        String supplier= request.getParameter("supplier");
        String license_plate = request.getParameter("license_plate");
        String card_at = request.getParameter("card_at");
        String limited_at = request.getParameter("limited_at");
        double guide_price = Double.valueOf(request.getParameter("guide_price"));
        double vehicle_price = Double.valueOf(request.getParameter("vehicle_price"));
        double vehicle_tax = Double.valueOf(request.getParameter("vehicle_tax"));
        String insurance_company= request.getParameter("insurance_company");
        double strong_insurance = Double.valueOf(request.getParameter("strong_insurance"));
        String strong_insurance_expire_at= request.getParameter("strong_insurance_expire_at");
        double vehicle_vessel_tax = Double.valueOf(request.getParameter("vehicle_vessel_tax"));
        double business_insurance = Double.valueOf(request.getParameter("business_insurance"));
        String business_insurance_expire_at  = request.getParameter("business_insurance_expire_at");

        long km = Long.valueOf(request.getParameter("km"));
//        long maintian_on_km = Long.valueOf(request.getParameter("maintian_on_km"));
        String gps = request.getParameter("gps");
        long current_city = Long.valueOf(request.getParameter("current_city"));
        long current_shop  = Long.valueOf(request.getParameter("current_shop"));
        String lease_status = request.getParameter("lease_status");
        String peccancy_status = request.getParameter("peccancy_status");
        String archive_no = request.getParameter("archive_no");
        String inventory_no = request.getParameter("inventory_no");
        String registry_certificate = request.getParameter("registry_certificate");
        String certificate_direction = request.getParameter("certificate_direction");
        String loan_bank = request.getParameter("loan_bank");
        String consistency_cer = request.getParameter("consistency_cer");
        String check_list  = request.getParameter("check_list");
        String duty_paid_proof = request.getParameter("duty_paid_proof");
        String record = request.getParameter("record");
        String remark = request.getParameter("remark");
        long original_org = Long.valueOf(request.getParameter("original_org"));
        long next_main_km = Long.valueOf(request.getParameter("next_main_km"));

        String financing_rent_company = request.getParameter("financing_rent_company");
        String financing_rent_price_str = request.getParameter("financing_rent_price");
        double financing_rent_price =  StringUtils.isBlank(financing_rent_price_str) ? 0 : Double.valueOf(financing_rent_price_str);
        String bail_str = request.getParameter("bail");
        double bail = StringUtils.isBlank(bail_str) ? 0 : Double.valueOf(bail_str);
        String monthly_payment_str = request.getParameter("monthly_payment");
        double monthly_payment = StringUtils.isBlank(monthly_payment_str) ? 0 : Double.valueOf(monthly_payment_str);

        String etc = request.getParameter("etc");
        String ect_money_str = request.getParameter("etc_money");
        double etc_money = StringUtils.isBlank(ect_money_str) ? 0 : Double.valueOf(ect_money_str);
        String oil_percent_str = request.getParameter("oil_percent");
        int oil_percent = StringUtils.isBlank(oil_percent_str) ? 0 : Integer.valueOf(oil_percent_str);

        String daily_price_str = request.getParameter("daily_price");
        double daily_price = StringUtils.isBlank(daily_price_str) ? 0 : Double.valueOf(daily_price_str);


        return this.vehicleManageService.addVehicle(archive_no , inventory_no , brand , model , color , carframe_no , engine_no ,
                registry_certificate , certificate_direction , loan_bank , consistency_cer , check_list ,
                duty_paid_proof , record , buy_at , supplier , license_plate , card_at ,
                limited_at , guide_price , vehicle_price , vehicle_tax , insurance_company ,
                strong_insurance , vehicle_vessel_tax , strong_insurance_expire_at , business_insurance ,
                business_insurance_expire_at , remark , user.getUser_id() , original_org ,
                km  , gps , current_city , current_shop , lease_status , peccancy_status , next_main_km ,
                financing_rent_company , financing_rent_price , bail , monthly_payment ,
                etc , etc_money , oil_percent , daily_price);
    }

    /**
     * 车辆详细信息
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/info/detail" , method = RequestMethod.GET)
    public String InfoDetail(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");


        long vehicle_id = Long.valueOf(request.getParameter("vehicle_id"));
        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();

        VehicleInfo vehicle_info = this.vehicleManageService.getVehicleInfoByid(vehicle_id);

        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_info" , vehicle_info);
        return "/module/vehiclemanage/info/detail";
    }

    @RequestMapping(value = "/vehicle/modify" , method = RequestMethod.GET)
    public String vehicleModify(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long vehicle_id = Long.valueOf(request.getParameter("vehicle_id"));
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();
        VehicleInfo vehicleInfo = this.vehicleManageService.getVehicleInfoByid(vehicle_id);

        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_info" , vehicleInfo);
        model.addAttribute("vehicle_id" , vehicle_id);
        return "/module/vehiclemanage/register/modify";
    }

    @RequestMapping(value = "/vehicle/domodify" , method = RequestMethod.POST)
    @ResponseBody
    public int vehicleDoModify(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long vehicle_id = Long.valueOf(request.getParameter("vehicle_id"));
        String brand = request.getParameter("brand");
        String vehicle_model = request.getParameter("model");
        String color = request.getParameter("color");
        String carframe_no = request.getParameter("carframe_no");
        String engine_no = request.getParameter("engine_no");
        String buy_at = request.getParameter("buy_at");
        String supplier= request.getParameter("supplier");
        String license_plate = request.getParameter("license_plate");
        String card_at = request.getParameter("card_at");
        String limited_at = request.getParameter("limited_at");
        double guide_price = Double.valueOf(request.getParameter("guide_price"));
        double vehicle_price = Double.valueOf(request.getParameter("vehicle_price"));
        double vehicle_tax = Double.valueOf(request.getParameter("vehicle_tax"));
        String insurance_company= request.getParameter("insurance_company");
        double strong_insurance = Double.valueOf(request.getParameter("strong_insurance"));
        String strong_insurance_expire_at= request.getParameter("strong_insurance_expire_at");
        double vehicle_vessel_tax = Double.valueOf(request.getParameter("vehicle_vessel_tax"));
        double business_insurance = Double.valueOf(request.getParameter("business_insurance"));
        String business_insurance_expire_at  = request.getParameter("business_insurance_expire_at");

        long km = Long.valueOf(request.getParameter("km"));
//        long maintian_on_km = Long.valueOf(request.getParameter("maintian_on_km"));
        String gps = request.getParameter("gps");
        long current_city = Long.valueOf(request.getParameter("current_city"));
        long current_shop  = Long.valueOf(request.getParameter("current_shop"));
        String lease_status = request.getParameter("lease_status");
        String peccancy_status = request.getParameter("peccancy_status");
        String archive_no = request.getParameter("archive_no");
        String inventory_no = request.getParameter("inventory_no");
        String registry_certificate = request.getParameter("registry_certificate");
        String certificate_direction = request.getParameter("certificate_direction");
        String loan_bank = request.getParameter("loan_bank");
        String consistency_cer = request.getParameter("consistency_cer");
        String check_list  = request.getParameter("check_list");
        String duty_paid_proof = request.getParameter("duty_paid_proof");
        String record = request.getParameter("record");
        String remark = request.getParameter("remark");
        long original_org = Long.valueOf(request.getParameter("original_org"));
        long next_main_km = Long.valueOf(request.getParameter("next_main_km"));

        String financing_rent_company = request.getParameter("financing_rent_company");
        String financing_rent_price_str = request.getParameter("financing_rent_price");
        double financing_rent_price =  StringUtils.isBlank(financing_rent_price_str) ? 0 : Double.valueOf(financing_rent_price_str);
        String bail_str = request.getParameter("bail");
        double bail = StringUtils.isBlank(bail_str) ? 0 : Double.valueOf(bail_str);
        String monthly_payment_str = request.getParameter("monthly_payment");
        double monthly_payment = StringUtils.isBlank(monthly_payment_str) ? 0 : Double.valueOf(monthly_payment_str);

        String etc = request.getParameter("etc");
        String ect_money_str = request.getParameter("etc_money");
        double etc_money = StringUtils.isBlank(ect_money_str) ? 0 : Double.valueOf(ect_money_str);
        String oil_percent_str = request.getParameter("oil_percent");
        int oil_percent = StringUtils.isBlank(oil_percent_str) ? 0 : Integer.valueOf(oil_percent_str);

        String daily_price_str = request.getParameter("daily_price");
        double daily_price = StringUtils.isBlank(daily_price_str) ? 0 : Double.valueOf(daily_price_str);


        return this.vehicleManageService.modifyVehicle(vehicle_id , archive_no , inventory_no , brand , vehicle_model , color , carframe_no , engine_no ,
                registry_certificate , certificate_direction , loan_bank , consistency_cer , check_list ,
                duty_paid_proof , record , buy_at , supplier , license_plate , card_at ,
                limited_at , guide_price , vehicle_price , vehicle_tax , insurance_company ,
                strong_insurance , vehicle_vessel_tax , strong_insurance_expire_at , business_insurance ,
                business_insurance_expire_at , remark , user.getUser_id() , original_org ,
                km , gps , current_city , current_shop , lease_status , peccancy_status , next_main_km ,
                financing_rent_company , financing_rent_price , bail , monthly_payment ,
                etc , etc_money , oil_percent , daily_price);
    }



    /**
     * 车辆保险录入
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/insurance/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String insuranceIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("store.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String brand = request.getParameter("brand");
        String license_plate = request.getParameter("license_plate");

        String method = request.getMethod();
        if("GET".equals(method.toUpperCase())) {//get请求，进行编码格式转换
            brand = this.commonService.characterFormat(brand , "ISO8859-1" , "UTF-8");
            license_plate = this.commonService.characterFormat(license_plate , "ISO8859-1" , "UTF-8");
        }

        //获取用户角色列表
//        List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
//        long original_org;
//        if(original_org_str == null || "".equals(original_org_str)) {
//            original_org = user_role_list.get(0).getOrg_id();
//        } else {
//            original_org = Long.valueOf(original_org_str);
//        }
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);

        Map<String , Object> map = this.vehicleManageService.getVehicleList(original_org , null , brand , null , license_plate , null , null , null , null , null , start , size);

        long total = (Long)map.get("total");;
        List<VehicleInfo> vehicle_list = (List<VehicleInfo>)map.get("vehicle_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

//        String vehicle_brand_json = this.commonService.getAllVehicleBrand();
//        String vehicle_model_json = this.commonService.getAllVehicleModel();
        String vehicle_licene_plate_json = this.commonService.getAllVehicleLicensePlate();
//        model.addAttribute("vehicle_brand_json" , vehicle_brand_json);
//        model.addAttribute("vehicle_model_json" , vehicle_model_json);
        model.addAttribute("vehicle_licene_plate_json" , vehicle_licene_plate_json);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        String condition = "&original_org="+original_org;
        if(brand != null) {
            condition = condition + "&brand="+brand;
        }
        if(license_plate != null) {
            condition = condition + "&license_plate="+license_plate;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("original_org" , original_org);
        model.addAttribute("brand" , brand);
        model.addAttribute("license_plate" , license_plate);

//        model.addAttribute("user_role_list" , user_role_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_list" , vehicle_list);
        return "/module/vehiclemanage/insurance/index";
    }

    /**
     * 点击某个车辆点车架号，进入该车辆保险详细
     * 获取该车辆录入保险详细列表
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/insurance/detail" , method = RequestMethod.GET)
    public String insuranceDetail(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("store.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String carframe_no = request.getParameter("carframe_no");//车架号
        Map<String , Object> map = this.vehicleManageService.getVehicleInsuranceList(carframe_no , start , size);

        long total = (Long)map.get("total");;
        List<VehicleInsurance> vehicleInsurance_list = (List<VehicleInsurance>)map.get("vehicleInsurance_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());
        String condition = "";
        if(carframe_no != null) {
            condition = "&carframe_no="+carframe_no;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("carframe_no" , carframe_no);
        model.addAttribute("vehicleInsurance_list" , vehicleInsurance_list);
        return "/module/vehiclemanage/insurance/detail";
    }

    /**
     * 车辆保险录入，进入录入页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/insurance/add" , method = RequestMethod.GET)
    public String insuranceAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long vehicle_id = Long.valueOf(request.getParameter("vehicle_id"));//车辆id
        VehicleInfo vehicleInfo = this.vehicleManageService.getVehicleInfoByid(vehicle_id);

        model.addAttribute("vehicle_info" , vehicleInfo);
        return "/module/vehiclemanage/insurance/add";
    }

    /**
     * 新增车辆保险
     * @param model1
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/insurance/doadd" , method = RequestMethod.POST)
    @ResponseBody
    public int insuranceDoAdd(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String carframe_no = request.getParameter("carframe_no");
        String engine_no = request.getParameter("engine_no");
        String license_plate = request.getParameter("license_plate");
        String insurance_company= request.getParameter("insurance_company");
        double strong_insurance = Double.valueOf(request.getParameter("strong_insurance"));
        double vehicle_vessel_tax = Double.valueOf(request.getParameter("vehicle_vessel_tax"));
        String strong_insurance_expire_at= request.getParameter("strong_insurance_expire_at");
        double business_insurance = Double.valueOf(request.getParameter("business_insurance"));
        String business_insurance_expire_at  = request.getParameter("business_insurance_expire_at");
        String remark = request.getParameter("remark");

        return this.vehicleManageService.addInsurance(carframe_no , engine_no , license_plate , insurance_company ,
                strong_insurance , vehicle_vessel_tax , strong_insurance_expire_at , business_insurance ,
                business_insurance_expire_at , remark , user.getUser_id());
    }


    /**
     * 车辆违章录入
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/peccancy/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String peccancyIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("store.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String brand = request.getParameter("brand");
        String license_plate = request.getParameter("license_plate");

        String method = request.getMethod();
        if("GET".equals(method.toUpperCase())) {//get请求，进行编码格式转换
            brand = this.commonService.characterFormat(brand , "ISO8859-1" , "UTF-8");
            license_plate = this.commonService.characterFormat(license_plate , "ISO8859-1" , "UTF-8");
        }

        //获取用户角色列表
//        List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
//        long original_org;
//        if(original_org_str == null || "".equals(original_org_str)) {
//            original_org = user_role_list.get(0).getOrg_id();
//        } else {
//            original_org = Long.valueOf(original_org_str);
//        }
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);

        Map<String , Object> map = this.vehicleManageService.getVehicleList(original_org , null , brand , null , license_plate , null , null , null , null , null , start , size);

        long total = (Long)map.get("total");;
        List<VehicleInfo> vehicle_list = (List<VehicleInfo>)map.get("vehicle_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        //        String vehicle_brand_json = this.commonService.getAllVehicleBrand();
//        String vehicle_model_json = this.commonService.getAllVehicleModel();
        String vehicle_licene_plate_json = this.commonService.getAllVehicleLicensePlate();
//        model.addAttribute("vehicle_brand_json" , vehicle_brand_json);
//        model.addAttribute("vehicle_model_json" , vehicle_model_json);
        model.addAttribute("vehicle_licene_plate_json" , vehicle_licene_plate_json);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        String condition = "&original_org="+original_org;
        if(brand != null) {
            condition = condition + "&brand="+brand;
        }
        if(license_plate != null) {
            condition = condition + "&license_plate="+license_plate;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("original_org" , original_org);
        model.addAttribute("brand" , brand);
        model.addAttribute("license_plate" , license_plate);

//        model.addAttribute("user_role_list" , user_role_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_list" , vehicle_list);
        return "/module/vehiclemanage/peccancy/index";
    }

    /**
     * 点击某个车辆点车架号，进入该车辆违章记录详细
     * 获取该车辆违章记录详细列表
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/peccancy/detail" , method = RequestMethod.GET)
    public String PeccancyDetail(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("store.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String carframe_no = request.getParameter("carframe_no");//车架号

        Map<String , Object> map = this.vehicleManageService.getVehiclePeccancyList(carframe_no, start, size);

        long total = (Long)map.get("total");;
        List<VehiclePeccancy> vehiclePeccancy_list = (List<VehiclePeccancy>)map.get("vehiclePeccancy_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());
        String condition = "";
        if(carframe_no != null) {
            condition = condition + "&carframe_no="+carframe_no;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("carframe_no" , carframe_no);
        model.addAttribute("vehiclePeccancy_list" , vehiclePeccancy_list);
        return "/module/vehiclemanage/peccancy/detail";
    }

    /**
     * 新增车辆违章记录页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/peccancy/add" , method = RequestMethod.GET)
    public String peccancyAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long vehicle_id = Long.valueOf(request.getParameter("vehicle_id"));//车辆id
        VehicleInfo vehicleInfo = this.vehicleManageService.getVehicleInfoByid(vehicle_id);

        String customer_name_certification_no_json = this.commonService.getAllCustomerNameAndCertificateNo();
        String user_employee_id_name_json = this.commonService.getAllEmployeeIdAndName();

        model.addAttribute("vehicle_info" , vehicleInfo);
        model.addAttribute("customer_name_certification_no_json" , customer_name_certification_no_json);
        model.addAttribute("user_employee_id_name_json" , user_employee_id_name_json);
        return "/module/vehiclemanage/peccancy/add";
    }

    /**
     * 新增车辆违章
     * @param model1
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/peccancy/doadd" , method = RequestMethod.POST)
    @ResponseBody
    public int peccancyDoAdd(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String carframe_no = request.getParameter("carframe_no");
        String engine_no = request.getParameter("engine_no");
        String license_plate = request.getParameter("license_plate");
        String peccancy_at = request.getParameter("peccancy_at_date");
        String peccancy_place  = request.getParameter("peccancy_place");
        String peccancy_reason = request.getParameter("peccancy_reason");
        long score = Long.valueOf(request.getParameter("score"));
        int status = Integer.valueOf(request.getParameter("status"));

        double peccancy_price = Double.valueOf(request.getParameter("peccancy_price"));
        String arbitration = request.getParameter("arbitration");

        String employee_id = request.getParameter("employee_id");
        String employee_name = request.getParameter("employee_name");
        long customer_id  = Long.valueOf(request.getParameter("customer_id"));
        String customer_name = request.getParameter("customer_name");

        return this.vehicleManageService.addVehiclePeccancy(carframe_no , engine_no , license_plate , peccancy_at ,
                peccancy_place , peccancy_reason , score , status , user.getUser_id() , peccancy_price , arbitration ,
                employee_id , employee_name , customer_id , customer_name);
    }

    /**
     * 车辆保险到期提醒
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/insuranceremind/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String InsuranceRemind(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.insurance.remind.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String current_city = request.getParameter("current_city");
//        String carframe_no = request.getParameter("carframe_no");
//        String engine_no = request.getParameter("engine_no");
        String license_plate = request.getParameter("license_plate");

        String method = request.getMethod();
        if("GET".equals(method.toUpperCase())) {//get请求，进行编码格式转换
            license_plate = this.commonService.characterFormat(license_plate , "ISO8859-1" , "UTF-8");
        }

        //获取用户角色列表
//        List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
//        long original_org;
//        if(original_org_str == null || "".equals(original_org_str)) {
//            original_org = user_role_list.get(0).getOrg_id();
//        } else {
//            original_org = Long.valueOf(original_org_str);
//        }
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
        Map<String , Object> map = this.vehicleManageService.getVehicleInsuranceRemindList(original_org , null , null , current_city , license_plate , start , size);

        long total = (Long)map.get("total");
        List<VehicleInfo> vehicle_insurance_remind_list = (List<VehicleInfo>)map.get("vehicle_insurance_remind_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        //        String vehicle_brand_json = this.commonService.getAllVehicleBrand();
//        String vehicle_model_json = this.commonService.getAllVehicleModel();
        String vehicle_licene_plate_json = this.commonService.getAllVehicleLicensePlate();
//        model.addAttribute("vehicle_brand_json" , vehicle_brand_json);
//        model.addAttribute("vehicle_model_json" , vehicle_model_json);
        model.addAttribute("vehicle_licene_plate_json" , vehicle_licene_plate_json);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        String condition = "&original_org="+original_org;
        if(current_city != null) {
            condition = condition + "&current_city="+current_city;
        }
        if(license_plate != null) {
            condition = condition + "&license_plate="+license_plate;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("original_org" , original_org);
        model.addAttribute("license_plate" , license_plate);
        model.addAttribute("current_city" , current_city);

        model.addAttribute("sys_used_city_list" , sys_used_city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_insurance_remind_list" , vehicle_insurance_remind_list);
        return "/module/vehiclemanage/insuranceremind/index";
    }

    /**
     * 车辆违章记录提醒
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/peccancyremind/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String PeccancyRemind(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.insurance.remind.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String current_city = request.getParameter("current_city");
        String license_plate = request.getParameter("license_plate");

        String method = request.getMethod();
        if("GET".equals(method.toUpperCase())) {//get请求，进行编码格式转换
            license_plate = this.commonService.characterFormat(license_plate , "ISO8859-1" , "UTF-8");
        }

        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
        Map<String , Object> map = this.vehicleManageService.getVehiclePeccancyRemindList(original_org, current_city, license_plate, start, size);

        long total = (Long)map.get("total");
        List<VehicleInfo> vehicle_peccancy_remind_list = (List<VehicleInfo>)map.get("vehicle_peccancy_remind_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        //        String vehicle_brand_json = this.commonService.getAllVehicleBrand();
//        String vehicle_model_json = this.commonService.getAllVehicleModel();
        String vehicle_licene_plate_json = this.commonService.getAllVehicleLicensePlate();
//        model.addAttribute("vehicle_brand_json" , vehicle_brand_json);
//        model.addAttribute("vehicle_model_json" , vehicle_model_json);
        model.addAttribute("vehicle_licene_plate_json" , vehicle_licene_plate_json);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        String condition = "&original_org="+original_org;
        if(current_city != null) {
            condition = condition + "&current_city="+current_city;
        }
        if(license_plate != null) {
            condition = condition + "&license_plate="+license_plate;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("original_org" , original_org);
        model.addAttribute("license_plate" , license_plate);
        model.addAttribute("current_city" , current_city);

        model.addAttribute("sys_used_city_list" , sys_used_city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_peccancy_remind_list" , vehicle_peccancy_remind_list);
        return "/module/vehiclemanage/peccancyremind/index";
    }

    /**
     * 车辆状态查询，车辆状态有：在库 ，已出库（已出库 里面有 零租出库的，产权租赁出库的） ，待出库
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/leasestatus/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String leaseStatusIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.leasestatus.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String lease_status = request.getParameter("leasestatus");//状态

        String method = request.getMethod();
        if("GET".equals(method.toUpperCase())) {//get请求，进行编码格式转换
            lease_status = this.commonService.characterFormat(lease_status , "ISO8859-1" , "UTF-8");
        }

        //获取用户角色列表
        List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
        long original_org;
        if(original_org_str == null || "".equals(original_org_str)) {
            original_org = user_role_list.get(0).getOrg_id();
        } else {
            original_org = Long.valueOf(original_org_str);
        }

        Map<String , Object> map = this.vehicleManageService.getVehicleLeaseStatusList(original_org, lease_status, start, size);
        long total = (Long)map.get("total");
        List<VehicleInfo> vehicle_list = (List<VehicleInfo>)map.get("vehicle_lease_status_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        String condition = "&original_org="+original_org;
        if(lease_status != null) {
            condition = condition + "&leasestatus="+lease_status;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("original_org" , original_org);
        model.addAttribute("lease_status" , lease_status);

        model.addAttribute("user_role_list" , user_role_list);
        model.addAttribute("vehicle_list" , vehicle_list);
        return "/module/vehiclemanage/leasestatus/index";
    }

    /**
     * 违章详情页，点击处理，跳转到违章处理页
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/peccancy/handle" , method = RequestMethod.GET)
    public String peccancyHandle(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");
         
        long id = Long.valueOf(request.getParameter("id"));
        VehiclePeccancy vehiclePeccancy = this.vehicleManageService.getVehiclePeccancy(id);

        String customer_name_certification_no_json = this.commonService.getAllCustomerNameAndCertificateNo();
        String user_employee_id_name_json = this.commonService.getAllEmployeeIdAndName();

        model.addAttribute("vehicle_peccancy" , vehiclePeccancy);
        model.addAttribute("customer_name_certification_no_json" , customer_name_certification_no_json);
        model.addAttribute("user_employee_id_name_json" , user_employee_id_name_json);
        return "/module/vehiclemanage/peccancy/handle";
    }

    /**
     * 处理车辆违章
     * @param model1
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/peccancy/dohandle" , method = RequestMethod.POST)
    @ResponseBody
    public int peccancyDoHandle(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        String carframe_no = request.getParameter("carframe_no");
        String engine_no = request.getParameter("engine_no");
        String license_plate = request.getParameter("license_plate");
        String peccancy_at = request.getParameter("peccancy_at_date");
        String peccancy_place  = request.getParameter("peccancy_place");
        String peccancy_reason = request.getParameter("peccancy_reason");
        long score = Long.valueOf(request.getParameter("score"));
        int status = Integer.valueOf(request.getParameter("status"));
        double peccancy_price = Double.valueOf(request.getParameter("peccancy_price"));
        String arbitration = request.getParameter("arbitration");

        return this.vehicleManageService.peccancyDoHandle(id , carframe_no , engine_no , license_plate , peccancy_at ,
                peccancy_place , peccancy_reason , score , status , peccancy_price , arbitration , user.getUser_id());
    }

    /**
     * 车辆保养提醒
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/maintainremind/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String maintainRemind(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.maintain.remind.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String current_city = request.getParameter("current_city");
        String license_plate = request.getParameter("license_plate");

        String method = request.getMethod();
        if("GET".equals(method.toUpperCase())) {//get请求，进行编码格式转换
            license_plate = this.commonService.characterFormat(license_plate , "ISO8859-1" , "UTF-8");
        }

        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
        Map<String , Object> map = this.vehicleManageService.getVehicleMaintainRemindList(original_org, current_city, license_plate, start, size);

        long total = (Long)map.get("total");
        List<VehicleInfo> vehicle_maintain_remind_list = (List<VehicleInfo>)map.get("vehicle_maintain_remind_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        //        String vehicle_brand_json = this.commonService.getAllVehicleBrand();
//        String vehicle_model_json = this.commonService.getAllVehicleModel();
        String vehicle_licene_plate_json = this.commonService.getAllVehicleLicensePlate();
//        model.addAttribute("vehicle_brand_json" , vehicle_brand_json);
//        model.addAttribute("vehicle_model_json" , vehicle_model_json);
        model.addAttribute("vehicle_licene_plate_json" , vehicle_licene_plate_json);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        String condition = "&original_org="+original_org;
        if(current_city != null) {
            condition = condition + "&current_city="+current_city;
        }
        if(license_plate != null) {
            condition = condition + "&license_plate="+license_plate;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("original_org" , original_org);
        model.addAttribute("license_plate" , license_plate);
        model.addAttribute("current_city" , current_city);

        model.addAttribute("sys_used_city_list" , sys_used_city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_maintain_remind_list" , vehicle_maintain_remind_list);
        return "/module/vehiclemanage/maintainremind/index";
    }

    /**
     * 增加保养记录，跳转至录入页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/maintainremind/add" , method = RequestMethod.GET)
    public String maintainRecordAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long vehicle_id = Long.valueOf(request.getParameter("vehicle_id"));
        VehicleInfo vehicleInfo = this.vehicleManageService.getVehicleInfoByid(vehicle_id);
        String user_employee_id_name_json = this.commonService.getAllEmployeeIdAndName();

        model.addAttribute("vehicle_info" , vehicleInfo);
        model.addAttribute("user_employee_id_name_json" , user_employee_id_name_json);
        return "/module/vehiclemanage/maintainremind/add";
    }

    @RequestMapping(value = "/maintainremind/doadd" , method = RequestMethod.POST)
    @ResponseBody
    public int maintainRecordDoAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String carframe_no = request.getParameter("carframe_no");
        String engine_no = request.getParameter("engine_no");
        String license_plate = request.getParameter("license_plate");
        String maintain_at = request.getParameter("maintain_at_date");
        String maintain_content  = request.getParameter("maintain_content");
        double maintain_price = Double.valueOf(request.getParameter("maintain_price"));
        long current_km = Long.valueOf(request.getParameter("current_km"));
        long next_maintain_km = Integer.valueOf(request.getParameter("next_maintain_km"));
        long user_id = Long.valueOf(request.getParameter("user_id"));
        String user_name = request.getParameter("user_name");

        return this.vehicleManageService.maintainRecordDoAdd(carframe_no, engine_no, license_plate, maintain_at,
                maintain_content, maintain_price, current_km, next_maintain_km, user_id, user_name, user.getUser_id());
    }

    @RequestMapping(value = "/maintainremind/detail" , method = RequestMethod.GET)
    public String maintainremindDetail(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("store.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String carframe_no = request.getParameter("carframe_no");//车架号
        Map<String , Object> map = this.vehicleManageService.getVehicleMaintainDetai(carframe_no, start, size);

        long total = (Long)map.get("total");;
        List<VehicleMaintail> vehicle_maintain_list = (List<VehicleMaintail>)map.get("vehicle_maintain_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        String condition = "";
        if(carframe_no != null) {
            condition = condition + "&carframe_no="+carframe_no;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("carframe_no" , carframe_no);
        model.addAttribute("vehicle_maintain_list" , vehicle_maintain_list);
        return "/module/vehiclemanage/maintainremind/detail";
    }

    /**
     * 根据录入违章时间，获取该时间段租赁合同的客户姓名/证件号
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/peccancy/timegetcustomer" , method = RequestMethod.POST)
    @ResponseBody
    public String peccancyTimeGetCustomer(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String license_plate = request.getParameter("license_plate");
        String peccancy_at = request.getParameter("peccancy_at_date");

        return this.vehicleManageService.peccancyTimeGetCustomer(license_plate, peccancy_at);
    }

}