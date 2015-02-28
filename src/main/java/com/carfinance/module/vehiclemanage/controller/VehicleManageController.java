package com.carfinance.module.vehiclemanage.controller;

import com.carfinance.module.common.domain.*;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInsurance;
import com.carfinance.module.vehiclemanage.service.VehicleManageService;
import com.carfinance.module.vehiclemanage.domain.VehiclePeccancy;
import com.carfinance.module.common.domain.Enum;
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
    private InitService initService;
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
        String brand = request.getParameter("brand");
        String carframe_no = request.getParameter("carframe_no");
        String engine_no = request.getParameter("engine_no");
        String license_plate = request.getParameter("license_plate");

        //获取用户角色列表
        List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
        long original_org;
        if(original_org_str == null || "".equals(original_org_str)) {
            original_org = user_role_list.get(0).getOrg_id();
        } else {
            original_org = Long.valueOf(original_org_str);
        }

        Map<String , Object> map = this.vehicleManageService.getVehicleList(original_org , brand , carframe_no , engine_no , license_plate , start , size);

        long total = (Long)map.get("total");;
        List<VehicleInfo> vehicle_list = (List<VehicleInfo>)map.get("vehicle_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("original_org" , original_org);
        model.addAttribute("brand" , brand);
        model.addAttribute("carframe_no" , carframe_no);
        model.addAttribute("engine_no" , engine_no);
        model.addAttribute("license_plate" , license_plate);

        model.addAttribute("user_role_list" , user_role_list);
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
        List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
        List<Enum> province_list = this.commonService.getEnumFielList("SYS_PROVINCE");

        model.addAttribute("province_list" , province_list);
        model.addAttribute("user_role_list" , user_role_list);
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
        long maintian_on_km = Long.valueOf(request.getParameter("maintian_on_km"));
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

        return this.vehicleManageService.addVehicle(archive_no , inventory_no , brand , model , color , carframe_no , engine_no ,
                registry_certificate , certificate_direction , loan_bank , consistency_cer , check_list ,
                duty_paid_proof , record , buy_at , supplier , license_plate , card_at ,
                limited_at , guide_price , vehicle_price , vehicle_tax , insurance_company ,
                strong_insurance , vehicle_vessel_tax , strong_insurance_expire_at , business_insurance ,
                business_insurance_expire_at , remark , user.getUser_id() , original_org ,
                km , maintian_on_km , gps , current_city , current_shop , lease_status , peccancy_status);
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
        String carframe_no = request.getParameter("carframe_no");
        String engine_no = request.getParameter("engine_no");
        String license_plate = request.getParameter("license_plate");

        //获取用户角色列表
        List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
        long original_org;
        if(original_org_str == null || "".equals(original_org_str)) {
            original_org = user_role_list.get(0).getOrg_id();
        } else {
            original_org = Long.valueOf(original_org_str);
        }

        Map<String , Object> map = this.vehicleManageService.getVehicleList(original_org , brand , carframe_no , engine_no , license_plate , start , size);

        long total = (Long)map.get("total");;
        List<VehicleInfo> vehicle_list = (List<VehicleInfo>)map.get("vehicle_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("original_org" , original_org);
        model.addAttribute("brand" , brand);
        model.addAttribute("carframe_no" , carframe_no);
        model.addAttribute("engine_no" , engine_no);
        model.addAttribute("license_plate" , license_plate);

        model.addAttribute("user_role_list" , user_role_list);
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
        String carframe_no = request.getParameter("carframe_no");
        String engine_no = request.getParameter("engine_no");
        String license_plate = request.getParameter("license_plate");

        //获取用户角色列表
        List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
        long original_org;
        if(original_org_str == null || "".equals(original_org_str)) {
            original_org = user_role_list.get(0).getOrg_id();
        } else {
            original_org = Long.valueOf(original_org_str);
        }

        Map<String , Object> map = this.vehicleManageService.getVehicleList(original_org , brand , carframe_no , engine_no , license_plate , start , size);

        long total = (Long)map.get("total");;
        List<VehicleInfo> vehicle_list = (List<VehicleInfo>)map.get("vehicle_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("original_org" , original_org);
        model.addAttribute("brand" , brand);
        model.addAttribute("carframe_no" , carframe_no);
        model.addAttribute("engine_no" , engine_no);
        model.addAttribute("license_plate" , license_plate);

        model.addAttribute("user_role_list" , user_role_list);
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

        return this.vehicleManageService.addVehiclePeccancy(carframe_no , engine_no , license_plate , peccancy_at ,
                peccancy_place , peccancy_reason , score , status , user.getUser_id());
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
        String carframe_no = request.getParameter("carframe_no");
        String engine_no = request.getParameter("engine_no");
        String license_plate = request.getParameter("license_plate");

        //获取用户角色列表
        List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
        long original_org;
        if(original_org_str == null || "".equals(original_org_str)) {
            original_org = user_role_list.get(0).getOrg_id();
        } else {
            original_org = Long.valueOf(original_org_str);
        }

        Map<String , Object> map = this.vehicleManageService.getVehicleInsuranceRemindList(original_org , carframe_no , engine_no , license_plate , start , size);

        long total = (Long)map.get("total");
        List<VehicleInfo> vehicle_insurance_remind_list = (List<VehicleInfo>)map.get("vehicle_insurance_remind_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("original_org" , original_org);
        model.addAttribute("carframe_no" , carframe_no);
        model.addAttribute("engine_no" , engine_no);
        model.addAttribute("license_plate" , license_plate);

        model.addAttribute("user_role_list" , user_role_list);
        model.addAttribute("vehicle_insurance_remind_list" , vehicle_insurance_remind_list);
        return "/module/vehiclemanage/insuranceremind/index";
    }

}