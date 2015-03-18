package com.carfinance.module.vehicleservicemanage.controller;

import com.carfinance.module.common.domain.City;
import com.carfinance.module.common.domain.Org;
import com.carfinance.module.common.domain.UserRole;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.customermanage.domain.CustomerInfo;
import com.carfinance.module.customermanage.service.CustomerManageService;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.storemanage.service.StoreManageService;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.service.VehicleManageService;
import com.carfinance.module.vehicleservicemanage.domain.*;
import com.carfinance.module.vehicleservicemanage.service.VehicleServiceManageService;
import net.sf.json.JSONArray;
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
@RequestMapping("/vehicleservice")
public class VehicleServiceManageController {
	final Logger logger = LoggerFactory.getLogger(VehicleServiceManageController.class);
	
	@Autowired
	private CommonService commonService;
    @Autowired
    private VehicleServiceManageService vehicleServiceManageService;
	@Autowired
	private Properties appProps;
    @Autowired
    private CustomerManageService customerManageService;

    /**
     * 车辆预约单列表首页
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/reservation/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String reservationIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String customer_name = request.getParameter("customer_name");
        String dn = request.getParameter("dn");

        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());

        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
        Map<String , Object> map = this.vehicleServiceManageService.getOrgReservationList(original_org, customer_name , dn ,  start, size);

        long total = (Long)map.get("total");;
        List<VehicleReservationInfo> reservation_list = (List<VehicleReservationInfo>)map.get("reservation_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        String customer_name_json = this.commonService.getAllCustomerName();
        model.addAttribute("customer_name_json" , customer_name_json);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("original_org" , original_org);
        model.addAttribute("customer_name" , customer_name);
        model.addAttribute("dn" , dn);

        model.addAttribute("sys_used_city_list" , sys_used_city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("reservation_list" , reservation_list);
        return "/module/vehicleservicemanage/reservation/index";
    }

    /**
     * 车辆预约单提醒
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/reservation/remind" , method = {RequestMethod.GET , RequestMethod.POST})
    public String reservationRemind(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String customer_name = request.getParameter("customer_name");
        String dn = request.getParameter("dn");

        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());

        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        int remind_days = Integer.valueOf(appProps.get("vehicle.reservation.remind.day").toString());//多少天之内，提醒
        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
        Map<String , Object> map = this.vehicleServiceManageService.getOrgReservationRemindList(original_org, remind_days , customer_name , dn ,  start, size);

        long total = (Long)map.get("total");;
        List<VehicleReservationInfo> reservation_list = (List<VehicleReservationInfo>)map.get("reservation_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        String customer_name_json = this.commonService.getAllCustomerName();
        model.addAttribute("customer_name_json" , customer_name_json);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("original_org" , original_org);
        model.addAttribute("customer_name" , customer_name);
        model.addAttribute("dn" , dn);

        model.addAttribute("sys_used_city_list" , sys_used_city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("reservation_list" , reservation_list);
        return "/module/vehicleservicemanage/reservation/remind";
    }

    /**
     * 跳转至车辆预约单录入页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/reservation/add" , method = RequestMethod.GET)
    public String reservationAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();

        String customer_name_json = this.commonService.getAllCustomerName();

        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("customer_name_json" , customer_name_json);
        return "/module/vehicleservicemanage/reservation/add";
    }

    /**
     * 预约单执行新增
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/reservation/doadd" , method = RequestMethod.POST)
    @ResponseBody
    public int reservationDoAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String original_org = request.getParameter("original_org");
        long contrace_type = Long.valueOf(request.getParameter("contrace_type"));
        String customer_name = request.getParameter("customer_name");
        String customer_dn= request.getParameter("customer_dn");
        String use_begin= request.getParameter("use_begin");
        String use_end= request.getParameter("use_end");
        String employee_id= request.getParameter("employee_id");
        String employee_name= request.getParameter("employee_name");
        String remark= request.getParameter("remark");

        return this.vehicleServiceManageService.addReservation(original_org,contrace_type, customer_name, customer_dn,
                use_begin, use_end, employee_id, employee_name, remark, user.getUser_id());
    }

    /**
     * 取消预约单
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/reservation/docancel" , method = RequestMethod.POST)
    @ResponseBody
    public int reservationDoCancel(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long reservation_id = Long.valueOf(request.getParameter("reservation_id"));
        int status = Integer.valueOf(request.getParameter("status"));
        return this.vehicleServiceManageService.reservationDoCancel(reservation_id, user.getUser_id(), status);
    }

    /**
     * 合同管理首页，合同列表
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String contraceIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());

        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_all_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }

        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
        Map<String , Object> map = this.vehicleServiceManageService.getOrgContraceList(original_org, null , start, size , null);

        long total = (Long)map.get("total");
        List<VehicleContraceInfo> contrace_list = (List<VehicleContraceInfo>)map.get("contrace_list");

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
        model.addAttribute("original_org_name" , original_org_name);

        model.addAttribute("sys_used_city_list" , sys_used_city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("contrace_list" , contrace_list);
        return "/module/vehicleservicemanage/contrace/index";
    }

    /**
     * 预约单转正式合同，跳转至新增合同页
     * 需要根据预约单合同类型，跳转至不同合同页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/add" , method = RequestMethod.GET)
    public String contraceAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        String reservation_org_id = request.getParameter("reservation_org_id");
        long reservation_id = Long.valueOf(reservation_org_id.split(",")[0]);

        VehicleReservationInfo reservation_info = this.vehicleServiceManageService.getVehicleReservationInfoById(reservation_id);
        if(reservation_info.getContrace_type() == 1) {//零租
            return "redirect:/vehicleservice/contrace/temporary/add?reservation_org_id=" + reservation_org_id;
        } else if(reservation_info.getContrace_type() == 2) {//产权租
            return "redirect:/vehicleservice/contrace/property/add?reservation_org_id=" + reservation_org_id;
        }
        return null;
    }

    /**
     * 零租合同
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/temporary/add" , method = RequestMethod.GET)
    public String contraceTemporaryAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String reservation_org_id = request.getParameter("reservation_org_id");
        long reservation_id = Long.valueOf(reservation_org_id.split(",")[0]);
        long org_id = Long.valueOf(reservation_org_id.split(",")[1]);

        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();

        long contrace_id;
        //根据预约单id，获取合同信息。如果有，则直接使用；如果没有，就新增
        VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageService.getVehicleContraceInfoByReservationId(reservation_id);
        if(vehicleContraceInfo == null) {
            //根据预约单id，获取不到合同信息，此时生成合同，此时合同是空合同
            //跳转至合同页面，输入内容，点击提交，其实是对现在生成的合同内容进行更新
            contrace_id = this.vehicleServiceManageService.addContrace(reservation_id, org_id, user.getUser_id());
        } else {
            contrace_id = vehicleContraceInfo.getId();
        }

//        this.vehicleServiceManageService.reservationDoCancel(reservation_id, user.getUser_id(), 1);//将预约单状态改为完结
        VehicleReservationInfo vehicleReservationInfo = this.vehicleServiceManageService.getVehicleReservationInfoById(reservation_id);

        String customer_name_json = this.commonService.getAllCustomerName();
        String user_employee_id_name_json = this.commonService.getAllEmployeeIdAndName();

        model.addAttribute("customer_name_json" , customer_name_json);
        model.addAttribute("user_employee_id_name_json" , user_employee_id_name_json);
        model.addAttribute("contrace_id" , contrace_id);
        model.addAttribute("reservation_id" , reservation_id);
        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicleReservationInfo" , vehicleReservationInfo);
        return "/module/vehicleservicemanage/contrace/add";
    }

    /**
     * 新增合同，其实是更新、修改合同
     * 同时，将预约单状态，修改为结单
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/domodify" , method = RequestMethod.POST)
    @ResponseBody
    public long contraceDoAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        long reservation_id = Long.valueOf(request.getParameter("reservation_id"));
        long original_org = Long.valueOf(request.getParameter("original_org"));
        String customer_name = request.getParameter("customer_name");
        String customer_type = request.getParameter("customer_type");
        String customer_dn= request.getParameter("customer_dn");
        String certificate_type= request.getParameter("certificate_type");
        String certificate_no= request.getParameter("certificate_no");
        String use_begin= request.getParameter("use_begin");
        String use_end= request.getParameter("use_end");
        String employee_id= request.getParameter("employee_id");
        String employee_name= request.getParameter("employee_name");
        String remark= request.getParameter("remark");

        String contrace_no =  request.getParameter("contrace_no");
        double daily_price = Double.valueOf(request.getParameter("daily_price"));
        long daily_available_km = Long.valueOf(request.getParameter("daily_available_km"));
        double over_km_price = Double.valueOf(request.getParameter("over_km_price"));
        double over_hour_price = Double.valueOf(request.getParameter("over_hour_price"));
        String month_price_str = request.getParameter("month_price");
        double month_price = (month_price_str == null || "".equals(month_price_str.trim())) ? 0 : Double.valueOf(month_price_str.trim());
        String month_available_km_str = request.getParameter("month_available_km");
        long month_available_km = (month_available_km_str == null || "".equals(month_available_km_str.trim())) ? 0 : Long.valueOf(month_available_km_str.trim());
        String monthly_day_date = request.getParameter("monthly_day_date");
        double pre_payment = Double.valueOf(request.getParameter("pre_payment"));
        double deposit = Double.valueOf(request.getParameter("deposit"));
        double peccancy_deposit = Double.valueOf(request.getParameter("peccancy_deposit"));

        return this.vehicleServiceManageService.modifycontrace(contrace_id, reservation_id, original_org, contrace_no , customer_name, customer_type, customer_dn,
                certificate_type, certificate_no, use_begin, use_end, employee_id, employee_name, remark, user.getUser_id() ,
                daily_price , daily_available_km , over_km_price , over_hour_price , month_price , month_available_km , monthly_day_date ,
                pre_payment , deposit , peccancy_deposit);
    }

    /**
     * 业务员修改合同
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/modify" , method = RequestMethod.GET)
    public String contraceModify(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long current_page = Long.valueOf(request.getParameter("current_page"));
        long original_org = Long.valueOf(request.getParameter("original_org"));
        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();

        VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageService.getVehicleContraceInfoById(contrace_id);//获取合同详情

        String customer_name_json = this.commonService.getAllCustomerName();
        String user_employee_id_name_json = this.commonService.getAllEmployeeIdAndName();
        model.addAttribute("customer_name_json" , customer_name_json);
        model.addAttribute("user_employee_id_name_json" , user_employee_id_name_json);

        model.addAttribute("current_page" , current_page);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("contrace_id" , contrace_id);
        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_contrace_info" , vehicleContraceInfo);
        return "/module/vehicleservicemanage/contrace/modify";
    }

    /**
     * 业务员，给 合同增加车辆，进入车辆选择列表
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/addvech" , method = {RequestMethod.GET , RequestMethod.POST})
    public String contraceAddVech(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        String original_org_str = request.getParameter("original_org");
        String current_city = request.getParameter("current_city");
        String brand = request.getParameter("brand");
        String vehicle_model = request.getParameter("model");
        String license_plate = request.getParameter("license_plate");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("store.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);

        Map<String , Object> map = this.vehicleServiceManageService.getContraceCanUseVehicleList(original_org , current_city , brand , vehicle_model , license_plate , start , size);

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

        model.addAttribute("contrace_id" , contrace_id);
        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_list" , vehicle_list);


        model.addAttribute("original_org" , original_org);
        model.addAttribute("current_city" , current_city);
        model.addAttribute("brand" , brand);
        model.addAttribute("vehicle_model" , vehicle_model);
        model.addAttribute("license_plate" , license_plate);
        return "/module/vehicleservicemanage/contrace/addvech";
    }

    //TODO 业务员选择车辆，分配给某合同
    /**
     * 业务员选择某车辆，执行分配
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/dochoosevech" , method = RequestMethod.POST)
    @ResponseBody
    public long contraceDoChooseVech(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        long vehicle_id = Long.valueOf(request.getParameter("vehicle_id"));
        return this.vehicleServiceManageService.contraceDoChooseVech(contrace_id, vehicle_id , user.getUser_id());
    }

    /**
     * 新增外援车辆
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/addforeignvehicle" , method = RequestMethod.GET)
    public String contraceAddForeignVehicle(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));

        model.addAttribute("contrace_id" , contrace_id);
        return "/module/vehicleservicemanage/contrace/addforeignvehicle";
    }

    @RequestMapping(value = "/contrace/doaddforeignvehicle" , method = RequestMethod.POST)
    @ResponseBody
    public long contraceDoAddForeignVehicle(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        String license_plate = request.getParameter("license_plate");
        String vehicle_model = request.getParameter("model");
        double vehicle_price = Double.valueOf(request.getParameter("vehicle_price"));
        String company = request.getParameter("company");
        long other_vehicle_km = Long.valueOf(request.getParameter("other_vehicle_km"));
        return this.vehicleServiceManageService.contraceDoAddForeignVehicle(contrace_id, license_plate , vehicle_model , vehicle_price , company , other_vehicle_km , user.getUser_id());
    }







    //TODO 业务员查看合同车辆信息
    @RequestMapping(value = "/contrace/vech/list" , method = {RequestMethod.GET , RequestMethod.POST})
    public String contraceVechList(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        String brand = request.getParameter("brand");
        String vehicle_model = request.getParameter("model");
        String license_plate = request.getParameter("license_plate");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        //获取合同车辆列表
        VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageService.getVehicleContraceInfoById(contrace_id);
        Map<String , Object> map = this.vehicleServiceManageService.getContraceVechList(contrace_id, brand, vehicle_model, license_plate, start, size);

        long total = (Long)map.get("total");;
        List<VehicleContraceVehsInfo> vehicle_list = (List<VehicleContraceVehsInfo>)map.get("vehicle_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("contrace_id" , contrace_id);
        model.addAttribute("vehicle_list" , vehicle_list);
        model.addAttribute("brand" , brand);
        model.addAttribute("vehicle_model" , vehicle_model);
        model.addAttribute("license_plate" , license_plate);
        model.addAttribute("vehicleContraceInfo" , vehicleContraceInfo);
        return "/module/vehicleservicemanage/contrace/vehiclelist";
    }

    //TODO 业务员查看配驾列表
    @RequestMapping(value = "/contrace/vechdriver/list" , method = {RequestMethod.GET , RequestMethod.POST})
    public String contraceVechDriverList(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long veh_contrace_vehs_id = Long.valueOf(request.getParameter("veh_contrace_vehs_id"));//合同车辆id
        String original_org_str = request.getParameter("original_org");

        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        //获取可选择配驾员列表
        Map<String , Object> map = this.vehicleServiceManageService.contraceVechDriverList(original_org, start, size);

        long total = (Long)map.get("total");;
        List<UserDriver> driver_list = (List<UserDriver>)map.get("driver_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("veh_contrace_vehs_id" , veh_contrace_vehs_id);
        model.addAttribute("driver_list" , driver_list);
        return "/module/vehicleservicemanage/contrace/driverlist";
    }

    //TODO 业务员选择配驾员，分配给某车辆
    @RequestMapping(value = "/contrace/dochoosedriver" , method = RequestMethod.POST)
    @ResponseBody
    public long contraceDoChooseDriver(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long veh_contrace_vehs_id = Long.valueOf(request.getParameter("veh_contrace_vehs_id"));
        long driver_user_id = Long.valueOf(request.getParameter("driver_user_id"));
        return this.vehicleServiceManageService.contraceDoChooseDriver(veh_contrace_vehs_id, driver_user_id , user.getUser_id());
    }

    /**
     * 业务员取消合同车辆
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/cancelchoosevehicle" , method = RequestMethod.POST)
    @ResponseBody
    public long contraceCancelChooseVehicle(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long veh_contrace_vehs_id = Long.valueOf(request.getParameter("veh_contrace_vehs_id"));
        return this.vehicleServiceManageService.contraceCancelChooseVehicle(veh_contrace_vehs_id, user.getUser_id());
    }



    /**
     * 业务员提交店长审核合同
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/toshopaudit" , method = RequestMethod.GET)
    @ResponseBody
    public long contraceToShopAudit(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        return this.vehicleServiceManageService.contraceToShopAudit(contrace_id, user.getUser_id());
    }

    /**
     * 店长审核列表
     * 同时，系统管理员能够查看到全部
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/shopowner/audit" , method = {RequestMethod.GET , RequestMethod.POST})
    public String contraceShopownerAudit(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        String original_org_str = request.getParameter("original_org");
        String status_str = request.getParameter("status");

        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String status = status_str == null ? "1" : status_str;//店长默认查看待审核状态的列表
//        if("-99".equals(status)) status = null;//-99表示查看全部

        //判断如果是产权租，跳转至产权租的控制器
        String contrace_type_str = request.getParameter("contrace_type");
        int contrace_type = (contrace_type_str == null || "".equals(contrace_type_str.trim())) ? 1 : Integer.valueOf(contrace_type_str);//合同类型：1-零租；2-产权租
        if(contrace_type == 2) {
            return "redirect:/vehicleservice/contrace/property/shopowner/audit?original_org="+original_org_str+"&status="+status;
        }
        if("-99".equals(status)) status = null;//-99表示查看全部

        //TODO 管理员获取全部组织列表
        //获取当前用户存在店长角色的组织列表，角色表中20008对应店长
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
        if(isSysadmin) {
            user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        } else {
            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20201);
        }


        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_role_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_role_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }

        Map<String , Object> map = this.vehicleServiceManageService.getOrgContraceList(original_org, status, start, size , null);
        long total = (Long)map.get("total");
        List<VehicleContraceInfo> contrace_list = (List<VehicleContraceInfo>)map.get("contrace_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("status" , status);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("original_org_name" , original_org_name);

        model.addAttribute("user_role_org_list" , user_role_org_list);
        model.addAttribute("contrace_list" , contrace_list);
        model.addAttribute("contrace_type" , contrace_type);
        return "/module/vehicleservicemanage/contrace/shopownerauditlist";
    }

    /**
     * 门店店长执行审核
     * @param model1
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/shopowner/doaudit" , method = RequestMethod.POST)
    @ResponseBody
    public int shopownerDoAudit(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");

        return this.vehicleServiceManageService.shopownerDoAudit(id , status , user.getUser_id());
    }

    /**
     * 市店长审核列表
     * 同时，系统管理员能够查看到全部
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/cityshopowner/audit" , method = {RequestMethod.GET , RequestMethod.POST})
    public String serviceManagerAudit(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String status = (request.getParameter("status") == null || "".equals(request.getParameter("status").trim())) ? "2" : request.getParameter("status");//市店长默认查看店长审核通过的列表
//        if("-99".equals(status)) status = null;//-99表示查看全部

        String contrace_type_str = request.getParameter("contrace_type");
        int contrace_type = (contrace_type_str == null || "".equals(contrace_type_str.trim())) ? 1 : Integer.valueOf(contrace_type_str);//合同类型：1-零租；2-产权租
        if(contrace_type == 2) {
            return "redirect:/vehicleservice/contrace/property/cityshopowner/audit?original_org="+original_org_str+"&status="+status;
        }
        if("-99".equals(status)) status = null;//-99表示查看全部

        //获取当前用户存在市门店的组织列表，管理员获取全部组织列表
        //TODO 管理员获取全部组织列表
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
//        if(isSysadmin) {
            user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
//        } else {
//            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20009);
//        }

        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_role_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_role_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }

        Map<String , Object> map = this.vehicleServiceManageService.getOrgContraceList(original_org, status, start, size , "1");
        long total = (Long)map.get("total");
        List<VehicleContraceInfo> contrace_list = (List<VehicleContraceInfo>)map.get("contrace_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("status" , status);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("original_org_name" , original_org_name);

        model.addAttribute("user_role_org_list" , user_role_org_list);
        model.addAttribute("contrace_list" , contrace_list);
        model.addAttribute("contrace_type" , contrace_type);
        return "/module/vehicleservicemanage/contrace/cityshopownerauditlist";
    }

    /**
     * 市店长执行审核
     * @param model1
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/cityshopowner/doaudit" , method = RequestMethod.POST)
    @ResponseBody
    public int serviceManagerDoAudit(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");

        return this.vehicleServiceManageService.cityShopownerDoAudit(id , status , user.getUser_id());
    }


    /**
     * 区域经理审核列表
     * 同时，系统管理员能够查看到全部
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/regionalmanager/audit" , method = {RequestMethod.GET , RequestMethod.POST})
    public String regionalManagerAudit(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String status = (request.getParameter("status") == null || "".equals(request.getParameter("status").trim())) ? "3" : request.getParameter("status");//区域经理默认查看市店长审核通过的列表
//        if("-99".equals(status)) status = null;//-99表示查看全部

        String contrace_type_str = request.getParameter("contrace_type");
        int contrace_type = (contrace_type_str == null || "".equals(contrace_type_str.trim())) ? 1 : Integer.valueOf(contrace_type_str);//合同类型：1-零租；2-产权租
        if(contrace_type == 2) {
            return "redirect:/vehicleservice/contrace/property/regionalmanager/audit?original_org="+original_org_str+"&status="+status;
        }
        if("-99".equals(status)) status = null;//-99表示查看全部

        //TODO 管理员获取全部组织列表
        //获取当前用户存在区域经理的组织列表，管理员获取全部组织列表
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
//        if(isSysadmin) {
            user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
//        } else {
//            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20010);
//        }

        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_role_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_role_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }

        Map<String , Object> map = this.vehicleServiceManageService.getOrgContraceList(original_org, status, start, size , "1");
        long total = (Long)map.get("total");
        List<VehicleContraceInfo> contrace_list = (List<VehicleContraceInfo>)map.get("contrace_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("status" , status);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("original_org_name" , original_org_name);

        model.addAttribute("user_role_org_list" , user_role_org_list);
        model.addAttribute("contrace_list" , contrace_list);
        model.addAttribute("contrace_type" , contrace_type);
        return "/module/vehicleservicemanage/contrace/regionalmanagerauditlist";
    }

    /**
     * 区域经理执行审核
     * @param model1
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/regionalmanager/doaudit" , method = RequestMethod.POST)
    @ResponseBody
    public int regionalManagerDoAudit(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");

        return this.vehicleServiceManageService.regionalManagerDoAudit(id, status, user.getUser_id());
    }

    /**
     * 财务审核列表
     * 同时，系统管理员能够查看到全部
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/finance/audit" , method = {RequestMethod.GET , RequestMethod.POST})
    public String financeAudit(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String status = (request.getParameter("status") == null || "".equals(request.getParameter("status").trim())) ? "4" : request.getParameter("status");//财务默认查看区域经理审核通过的列表
//        if("-99".equals(status)) status = null;//-99表示查看全部

        String contrace_type_str = request.getParameter("contrace_type");
        int contrace_type = (contrace_type_str == null || "".equals(contrace_type_str.trim())) ? 1 : Integer.valueOf(contrace_type_str);//合同类型：1-零租；2-产权租
        if(contrace_type == 2) {
            return "redirect:/vehicleservice/contrace/property/finance/audit?original_org="+original_org_str+"&status="+status;
        }
        if("-99".equals(status)) status = null;//-99表示查看全部

        //获取当前用户存在风控的组织列表，管理员获取全部组织列表
        //TODO 管理员获取全部组织列表
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
        if(isSysadmin) {
            user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        } else {
            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20206);
        }

        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_role_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_role_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }

        String over_top = null;
        if("4".equals(status))  over_top = "1";//区域店长审核，over_top为1-true
        if("2".equals(status))  over_top = "0";//店长审核，over_top为0-false
        Map<String , Object> map = this.vehicleServiceManageService.getOrgContraceList(original_org, status, start, size, over_top  );
        long total = (Long)map.get("total");
        List<VehicleContraceInfo> contrace_list = (List<VehicleContraceInfo>)map.get("contrace_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("status" , status);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("original_org_name" , original_org_name);

        model.addAttribute("user_role_org_list" , user_role_org_list);
        model.addAttribute("contrace_list" , contrace_list);
        model.addAttribute("contrace_type" , contrace_type);
        return "/module/vehicleservicemanage/contrace/financeauditlist";
    }

    /**
     * 财务执行审核
     * @param model1
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/finance/doaudit" , method = RequestMethod.POST)
    @ResponseBody
    public int financeDoAudit(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");

        return this.vehicleServiceManageService.financeDoAudit(id, status, user.getUser_id());
    }

    /**
     * 业务员结单，跳转至结单页面，录入必要信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/finish" , method = RequestMethod.GET)
    public String contraceFinish(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageService.getVehicleContraceInfoById(contrace_id);
        List<VehicleContraceVehsInfo> vehicleContraceVehsInfoList = this.vehicleServiceManageService.getVehicleContraceVehsListByContraceId(contrace_id);
        double system_total_price = this.vehicleServiceManageService.getContraceIncom(contrace_id);

        model.addAttribute("system_total_price" , system_total_price);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_contrace_info" , vehicleContraceInfo);
        model.addAttribute("vehicle_contrace_vehs_list" , vehicleContraceVehsInfoList);
        return "/module/vehicleservicemanage/contrace/finish";
    }

    /**
     * 计算超时时长
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/calculateovertime" , method = RequestMethod.POST)
    @ResponseBody
    public String contraceCalculateOvertime(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String use_begin = request.getParameter("use_begin");
        String use_end = request.getParameter("use_end");
        String return_time = request.getParameter("return_time")+":00";//还车时间
        long return_km = Long.valueOf(request.getParameter("return_km"));//还车公里数

        long daily_available_km = Long.valueOf(request.getParameter("daily_available_km"));//每天允许公里数
        double over_km_price = Double.valueOf(request.getParameter("over_km_price"));//超里程，每公里价格
        double over_hour_price = Double.valueOf(request.getParameter("over_hour_price"));//超时间，每小时价格

        long vehicle_id = Long.valueOf(request.getParameter("vehicle_id"));//车辆id

        Map<String , Object> map = this.vehicleServiceManageService.calculateOvertimeAndKm(use_begin , use_end , return_time , vehicle_id ,  return_km , daily_available_km , over_hour_price , over_km_price);
        String return_json = JSONArray.fromObject(map).toString();
        logger.info("over_km_hour :" + return_json);
        return return_json;
    }

    /**
     * 合同结单，还车
     * @return
     */
    @RequestMapping(value = "/contrace/returnvahicle" , method = RequestMethod.POST)
    @ResponseBody
    public int returnVehicle(Model model , HttpServletRequest request , HttpServletResponse response) {

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        String return_time = request.getParameter("return_time");
        long return_km = Long.valueOf(request.getParameter("return_km"));
        long vehicle_id = Long.valueOf(request.getParameter("vehicle_id"));
        double over_price = Long.valueOf(request.getParameter("over_price"));
        long vehicle_contrace_id = Long.valueOf(request.getParameter("vehicle_contrace_id"));

        int result = this.vehicleServiceManageService.returnVehicle(vehicle_contrace_id , contrace_id , return_time , return_km , vehicle_id , over_price);
        return result;
//        return "redirect:/vehicleservice/contrace/finish?contrace_id=" + contrace_id;
    }

    /**
     * 最终执行合同结单
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/dofinish" , method = RequestMethod.POST)
    @ResponseBody
    public int contraceDoFinish(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        double system_total_price = Double.valueOf(request.getParameter("system_total_price"));
        double arrange_price = Double.valueOf(request.getParameter("arrange_price"));
        double actual_price = Double.valueOf(request.getParameter("actual_price"));
        double late_fee = Double.valueOf(request.getParameter("late_fee"));

        return this.vehicleServiceManageService.contraceDoFinish(contrace_id , system_total_price , arrange_price , actual_price , late_fee , user.getUser_id());
    }

    @RequestMapping(value = "/contrace/detail" , method = RequestMethod.GET)
    public String contraceDetail(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageService.getVehicleContraceInfoById(contrace_id);
        List<VehicleContraceVehsInfo> vehicleContraceVehsInfoList = this.vehicleServiceManageService.getVehicleContraceVehsListByContraceId(contrace_id);

        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_contrace_info" , vehicleContraceInfo);
        model.addAttribute("vehicle_contrace_vehs_list" , vehicleContraceVehsInfoList);
        return "/module/vehicleservicemanage/contrace/detail";
    }

    @RequestMapping(value = "/contrace/arrearage/remind" , method = {RequestMethod.GET , RequestMethod.POST})
    public String arrearageRemind(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());

        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_all_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }

        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
        Map<String , Object> map = this.vehicleServiceManageService.getArrearageRemindContraceList(original_org, start, size);

        long total = (Long)map.get("total");
        List<VehicleContraceInfo> contrace_list = (List<VehicleContraceInfo>)map.get("contrace_list");

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
        model.addAttribute("original_org_name" , original_org_name);

        model.addAttribute("sys_used_city_list" , sys_used_city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("contrace_list" , contrace_list);
        return "/module/vehicleservicemanage/contracearrearageremind/index";
    }

    /**
     * 跳转至合同还款页
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/returnmoney" , method = RequestMethod.GET)
    public String contraceReturnMoney(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageService.getVehicleContraceInfoById(contrace_id);
        List<VehicleContraceVehsInfo> vehicleContraceVehsInfoList = this.vehicleServiceManageService.getVehicleContraceVehsListByContraceId(contrace_id);

        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_contrace_info" , vehicleContraceInfo);
        model.addAttribute("vehicle_contrace_vehs_list" , vehicleContraceVehsInfoList);
        return "/module/vehicleservicemanage/contracearrearageremind/returnmoney";
    }

    @RequestMapping(value = "/contrace/doreturnmoney" , method = RequestMethod.POST)
    @ResponseBody
    public int contraceDoReturnMoney(Model model , HttpServletRequest request , HttpServletResponse response) {

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        double back_lease_price = Double.valueOf(request.getParameter("back_lease_price"));
        double back_overdue_price = Double.valueOf(request.getParameter("back_overdue_price"));

        int result = this.vehicleServiceManageService.contraceDoReturnMoney(contrace_id, back_lease_price, back_overdue_price);
        return result;
//        return "redirect:/vehicleservice/contrace/finish?contrace_id=" + contrace_id;
    }


    /**
     * 预约单转合同，产权租
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/property/add" , method = RequestMethod.GET)
    public String contracePropertyAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String reservation_org_id = request.getParameter("reservation_org_id");
        long reservation_id = Long.valueOf(reservation_org_id.split(",")[0]);
        long org_id = Long.valueOf(reservation_org_id.split(",")[1]);

        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();

        long contrace_id;
        //根据预约单id，获取产权租合同信息。如果有，则直接使用；如果没有，就新增
        PropertyContraceInfo propertyContraceInfo = this.vehicleServiceManageService.getPropertyContraceInfoByreservationid(reservation_id);
        if(propertyContraceInfo == null) {
            //根据预约单id，获取不到合同信息，此时生成合同，此时合同是空合同
            //跳转至合同页面，输入内容，点击提交，其实是对现在生成的合同内容进行更新
            contrace_id = this.vehicleServiceManageService.addPropertyContrace(reservation_id, org_id, user.getUser_id());
        } else {
            contrace_id = propertyContraceInfo.getId();
        }

        VehicleReservationInfo vehicleReservationInfo = this.vehicleServiceManageService.getVehicleReservationInfoById(reservation_id);
        String customer_name_json = this.commonService.getAllCustomerName();
        String user_employee_id_name_json = this.commonService.getAllEmployeeIdAndName();

        model.addAttribute("customer_name_json" , customer_name_json);
        model.addAttribute("user_employee_id_name_json" , user_employee_id_name_json);
        model.addAttribute("contrace_id" , contrace_id);
        model.addAttribute("reservation_id" , reservation_id);
        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicleReservationInfo" , vehicleReservationInfo);
        return "/module/vehicleservicemanage/propertycontrace/add";
    }

    /**
     * 产权租合同管理首页，列表
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/property/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String contracePropertyIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());

        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_all_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }

        List<City> sys_used_city_list = this.commonService.getSysUsedCityList();
        Map<String , Object> map = this.vehicleServiceManageService.getOrgPropertyContraceList(original_org, null , start, size , null);

        long total = (Long)map.get("total");
        List<PropertyContraceInfo> contrace_list = (List<PropertyContraceInfo>)map.get("contrace_list");

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
        model.addAttribute("original_org_name" , original_org_name);

        model.addAttribute("sys_used_city_list" , sys_used_city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("contrace_list" , contrace_list);
        return "/module/vehicleservicemanage/propertycontrace/index";
    }

    /**
     * 新增产权租合同，其实是更新、修改合同
     * 同时，将预约单状态，修改为结单
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/property/domodify" , method = RequestMethod.POST)
    @ResponseBody
    public long contracePropertyDoModify(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        long reservation_id = Long.valueOf(request.getParameter("reservation_id"));
        long original_org = Long.valueOf(request.getParameter("original_org"));
        String contrace_no =  request.getParameter("contrace_no");
        String customer_name = request.getParameter("customer_name");
        String customer_type = request.getParameter("customer_type");
        String customer_dn= request.getParameter("customer_dn");
        String certificate_type= request.getParameter("certificate_type");
        String certificate_no= request.getParameter("certificate_no");
        String sign_at_date= request.getParameter("sign_at_date");
        long period_number = Long.valueOf(request.getParameter("period_number"));
        double down_payment = Double.valueOf(request.getParameter("down_payment"));
        double lease_price = Double.valueOf(request.getParameter("lease_price"));
        double montyly_payment = Double.valueOf(request.getParameter("montyly_payment"));
        double arrange_payment = Double.valueOf(request.getParameter("arrange_payment"));
        int monthly_day = Integer.valueOf(request.getParameter("monthly_day"));
        double final_payment = Double.valueOf(request.getParameter("final_payment"));
        long received_periods = Long.valueOf(request.getParameter("received_periods"));
        double already_back_amount = Double.valueOf(request.getParameter("already_back_amount"));
        String payment_type = request.getParameter("payment_type");
        String employee_id= request.getParameter("employee_id");
        String employee_name= request.getParameter("employee_name");
        String remark= request.getParameter("remark");


        return this.vehicleServiceManageService.modifyPropertyContrace(contrace_id, reservation_id, original_org, contrace_no , customer_name, customer_type, customer_dn,
                certificate_type, certificate_no, sign_at_date, period_number , down_payment , lease_price , montyly_payment , arrange_payment , monthly_day ,
                final_payment , received_periods , already_back_amount , payment_type , employee_id, employee_name, remark, user.getUser_id());
    }

    /**
     * 业务员修改合同
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/property/modify" , method = RequestMethod.GET)
    public String contracePropertyModify(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long current_page = Long.valueOf(request.getParameter("current_page"));
        long original_org = Long.valueOf(request.getParameter("original_org"));
        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();

        PropertyContraceInfo propertyContraceInfo = this.vehicleServiceManageService.getPropertyContraceInfoById(contrace_id);//获取合同详情

        String customer_name_json = this.commonService.getAllCustomerName();
        String user_employee_id_name_json = this.commonService.getAllEmployeeIdAndName();
        model.addAttribute("customer_name_json" , customer_name_json);
        model.addAttribute("user_employee_id_name_json" , user_employee_id_name_json);

        model.addAttribute("current_page" , current_page);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("contrace_id" , contrace_id);
        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("property_contrace_info" , propertyContraceInfo);
        return "/module/vehicleservicemanage/propertycontrace/modify";
    }

    @RequestMapping(value = "/contrace/property/detail" , method = RequestMethod.GET)
    public String contracePropertyDetail(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        PropertyContraceInfo propertyContraceInfo = this.vehicleServiceManageService.getPropertyContraceInfoById(contrace_id);
        List<VehicleContraceVehsInfo> vehicleContraceVehsInfoList = this.vehicleServiceManageService.getVehicleContraceVehsListByContraceId(contrace_id);

        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("property_contrace_info" , propertyContraceInfo);
        model.addAttribute("vehicle_contrace_vehs_list" , vehicleContraceVehsInfoList);
        return "/module/vehicleservicemanage/propertycontrace/detail";
    }

    /**
     * 产权租，分配车辆
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/property/addvech" , method = {RequestMethod.GET , RequestMethod.POST})
    public String contraceAddPropertyVech(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        String original_org_str = request.getParameter("original_org");
        String current_city = request.getParameter("current_city");
        String brand = request.getParameter("brand");
        String vehicle_model = request.getParameter("model");
        String license_plate = request.getParameter("license_plate");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("store.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);

        Map<String , Object> map = this.vehicleServiceManageService.getContraceCanUseVehicleList(original_org , current_city , brand , vehicle_model , license_plate , start , size);

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

        model.addAttribute("contrace_id" , contrace_id);
        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicle_list" , vehicle_list);


        model.addAttribute("original_org" , original_org);
        model.addAttribute("current_city" , current_city);
        model.addAttribute("brand" , brand);
        model.addAttribute("vehicle_model" , vehicle_model);
        model.addAttribute("license_plate" , license_plate);
        return "/module/vehicleservicemanage/propertycontrace/addvech";
    }

    @RequestMapping(value = "/contrace/property/dochoosevech" , method = RequestMethod.POST)
    @ResponseBody
    public long contracePropertyDoChooseVech(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        long vehicle_id = Long.valueOf(request.getParameter("vehicle_id"));
        return this.vehicleServiceManageService.contracePropertyDoChooseVech(contrace_id, vehicle_id, user.getUser_id());
    }

    @RequestMapping(value = "/contrace/property/toshopaudit" , method = RequestMethod.GET)
    @ResponseBody
    public long contracePropertyToShopAudit(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        return this.vehicleServiceManageService.contracePropertyToShopAudit(contrace_id, user.getUser_id());
    }

    @RequestMapping(value = "/contrace/property/shopowner/audit" , method = {RequestMethod.GET , RequestMethod.POST})
    public String contracePropertyShopownerAudit(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        String original_org_str = request.getParameter("original_org");
        String status_str = request.getParameter("status");

        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String status = (status_str == null || "".equals(status_str.trim())) ? "1" : status_str;//店长默认查看待审核状态的列表
//        if("-99".equals(status)) status = null;//-99表示查看全部

        //判断如果是零租租，跳转至零租租的控制器
        String contrace_type_str = request.getParameter("contrace_type");
        int contrace_type = (contrace_type_str == null || "".equals(contrace_type_str.trim())) ? 2 : Integer.valueOf(contrace_type_str);//合同类型：1-零租；2-产权租
        if(contrace_type == 1) {
            return "redirect:/vehicleservice/contrace/shopowner/audit?original_org="+original_org_str+"&status="+status;
        }
        if("-99".equals(status)) status = null;//-99表示查看全部

        //TODO 管理员获取全部组织列表
        //获取当前用户存在店长角色的组织列表，角色表中20008对应店长
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
        if(isSysadmin) {
            user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        } else {
            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20201);
        }


        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_role_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_role_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }

        Map<String , Object> map = this.vehicleServiceManageService.getOrgContracePropertyList(original_org, status, start, size, null);
        long total = (Long)map.get("total");
        List<PropertyContraceInfo> contrace_list = (List<PropertyContraceInfo>)map.get("contrace_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("status" , status);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("original_org_name" , original_org_name);

        model.addAttribute("user_role_org_list" , user_role_org_list);
        model.addAttribute("contrace_list" , contrace_list);
        model.addAttribute("contrace_type" , contrace_type);
        return "/module/vehicleservicemanage/propertycontrace/shopownerauditlist";
    }

    @RequestMapping(value = "/contrace/property/shopowner/doaudit" , method = RequestMethod.POST)
    @ResponseBody
    public int shopownerDoAuditProperty(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");

        return this.vehicleServiceManageService.shopownerDoAuditProperty(id, status, user.getUser_id());
    }


    @RequestMapping(value = "/contrace/property/cityshopowner/audit" , method = {RequestMethod.GET , RequestMethod.POST})
    public String serviceManagerAuditProperty(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String status = (request.getParameter("status") == null || "".equals(request.getParameter("status").trim())) ? "2" : request.getParameter("status");//市店长默认查看店长审核通过的列表

        String contrace_type_str = request.getParameter("contrace_type");
        int contrace_type = (contrace_type_str == null || "".equals(contrace_type_str.trim())) ? 2 : Integer.valueOf(contrace_type_str);//合同类型：1-零租；2-产权租
        if(contrace_type == 1) {
            return "redirect:/vehicleservice/contrace/cityshopowner/audit?original_org="+original_org_str+"&status="+status;
        }
        if("-99".equals(status)) status = null;//-99表示查看全部

        //获取当前用户存在市门店的组织列表，管理员获取全部组织列表
        //TODO 管理员获取全部组织列表
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
//        if(isSysadmin) {
        user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
//        } else {
//            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20009);
//        }

        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_role_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_role_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }

        Map<String , Object> map = this.vehicleServiceManageService.getOrgContracePropertyList(original_org, status, start, size, null);
        long total = (Long)map.get("total");
        List<PropertyContraceInfo> contrace_list = (List<PropertyContraceInfo>)map.get("contrace_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("status" , status);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("original_org_name" , original_org_name);

        model.addAttribute("user_role_org_list" , user_role_org_list);
        model.addAttribute("contrace_list" , contrace_list);
        model.addAttribute("contrace_type" , contrace_type);
        return "/module/vehicleservicemanage/propertycontrace/cityshopownerauditlist";
    }

    @RequestMapping(value = "/contrace/property/cityshopowner/doaudit" , method = RequestMethod.POST)
    @ResponseBody
    public int serviceManagerDoAuditProperty(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");

        return this.vehicleServiceManageService.cityShopownerDoAuditProperty(id, status, user.getUser_id());
    }


    /**
     * 区域经理审核列表
     * 同时，系统管理员能够查看到全部
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/property/regionalmanager/audit" , method = {RequestMethod.GET , RequestMethod.POST})
    public String regionalManagerAuditProperty(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String status = (request.getParameter("status") == null || "".equals(request.getParameter("status").trim())) ? "3" : request.getParameter("status");//区域经理默认查看市店长审核通过的列表
//        if("-99".equals(status)) status = null;//-99表示查看全部

        String contrace_type_str = request.getParameter("contrace_type");
        int contrace_type = (contrace_type_str == null || "".equals(contrace_type_str.trim())) ? 2 : Integer.valueOf(contrace_type_str);//合同类型：1-零租；2-产权租
        if(contrace_type == 1) {
            return "redirect:/vehicleservice/contrace/regionalmanager/audit?original_org="+original_org_str+"&status="+status;
        }
        if("-99".equals(status)) status = null;//-99表示查看全部

        //TODO 管理员获取全部组织列表
        //获取当前用户存在区域经理的组织列表，管理员获取全部组织列表
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
//        if(isSysadmin) {
        user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
//        } else {
//            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20010);
//        }

        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_role_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_role_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }

        Map<String , Object> map = this.vehicleServiceManageService.getOrgContracePropertyList(original_org, status, start, size, null);
        long total = (Long)map.get("total");
        List<PropertyContraceInfo> contrace_list = (List<PropertyContraceInfo>)map.get("contrace_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("status" , status);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("original_org_name" , original_org_name);

        model.addAttribute("user_role_org_list" , user_role_org_list);
        model.addAttribute("contrace_list" , contrace_list);
        model.addAttribute("contrace_type" , contrace_type);
        return "/module/vehicleservicemanage/propertycontrace/regionalmanagerauditlist";
    }

    @RequestMapping(value = "/contrace/property/regionalmanager/doaudit" , method = RequestMethod.POST)
    @ResponseBody
    public int regionalManagerDoAuditProperty(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");

        return this.vehicleServiceManageService.regionalManagerDoAuditProperty(id, status, user.getUser_id());
    }

    @RequestMapping(value = "/contrace/property/finance/audit" , method = {RequestMethod.GET , RequestMethod.POST})
    public String financeAuditProperty(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String status = (request.getParameter("status") == null || "".equals(request.getParameter("status").trim())) ? "4" : request.getParameter("status");//财务默认查看区域经理审核通过的列表
//        if("-99".equals(status)) status = null;//-99表示查看全部

        String contrace_type_str = request.getParameter("contrace_type");
        int contrace_type = (contrace_type_str == null || "".equals(contrace_type_str.trim())) ? 2 : Integer.valueOf(contrace_type_str);//合同类型：1-零租；2-产权租
        if(contrace_type == 1) {
            return "redirect:/vehicleservice/contrace/finance/audit?original_org="+original_org_str+"&status="+status;
        }
        if("-99".equals(status)) status = null;//-99表示查看全部

        //获取当前用户存在风控的组织列表，管理员获取全部组织列表
        //TODO 管理员获取全部组织列表
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
        if(isSysadmin) {
            user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        } else {
            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20206);
        }

        //获取用户角色列表
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_role_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);
        String original_org_name = "";
        for(Org org : user_role_org_list) {
            if(org.getOrg_id() == original_org) {
                original_org_name = org.getOrg_name();
                break;
            }
        }


        String over_top = "0";
        if("4".equals(status))  over_top = "1";//区域店长审核，over_top为1-true
        Map<String , Object> map = this.vehicleServiceManageService.getOrgContracePropertyList(original_org, status, start, size, over_top);
        long total = (Long)map.get("total");
        List<PropertyContraceInfo> contrace_list = (List<PropertyContraceInfo>)map.get("contrace_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("status" , status);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("original_org_name" , original_org_name);

        model.addAttribute("user_role_org_list" , user_role_org_list);
        model.addAttribute("contrace_list" , contrace_list);
        model.addAttribute("contrace_type" , contrace_type);
        return "/module/vehicleservicemanage/propertycontrace/financeauditlist";
    }

    @RequestMapping(value = "/contrace/property/finance/doaudit" , method = RequestMethod.POST)
    @ResponseBody
    public int financeDoAuditProperty(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");

        return this.vehicleServiceManageService.financeDoAuditProperty(id, status, user.getUser_id());
    }

    /**
     * 产权租车辆，还款，跳转至还款页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/property/repayment" , method = RequestMethod.GET)
    public String PropertyContraceRepayment(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        PropertyContraceInfo propertyContraceInfo = this.vehicleServiceManageService.getPropertyContraceInfoById(contrace_id);
        List<VehicleContraceVehsInfo> vehicleContraceVehsInfoList = this.vehicleServiceManageService.getVehicleContraceVehsListByContraceId(contrace_id);

        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("property_contrace_info" , propertyContraceInfo);
        model.addAttribute("vehicle_contrace_vehs_list" , vehicleContraceVehsInfoList);
        return "/module/vehicleservicemanage/propertycontrace/repayment";
    }

    @RequestMapping(value = "/contrace/property/dorepayment" , method = RequestMethod.POST)
    @ResponseBody
    public int PropertyContraceDoRepayment(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        double should_payment = Double.valueOf(request.getParameter("should_payment"));
        double actual_payment = Double.valueOf(request.getParameter("actual_payment"));

        return this.vehicleServiceManageService.PropertyContraceDoRepayment(contrace_id , should_payment , actual_payment , user.getUser_id());
    }

    @RequestMapping(value = "/contrace/property/paymentdetail" , method = {RequestMethod.GET , RequestMethod.POST})
    public String contracePropertyPaymentDetail(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        Map<String , Object> map = this.vehicleServiceManageService.getContracePropertyPaymentDetail(contrace_id, start, size);
        long total = (Long)map.get("total");
        List<PropertyPaymentDetail> detail_list = (List<PropertyPaymentDetail>)map.get("detail_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("detail_list" , detail_list);
        return "/module/vehicleservicemanage/propertycontrace/paymentdetail";
    }

    @RequestMapping(value = "/contrace/audit/tocustomerdetail" , method = RequestMethod.GET)
    public String contraceAuditToCustomerDetail(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String customer_cer_no = request.getParameter("customer_cer_no");
        CustomerInfo customerInfo = this.customerManageService.getCustomrInfobyCertificateNo(customer_cer_no);

        long id = customerInfo == null ? 0 : customerInfo.getId();
        return "redirect:/customer/info/detail?id=" + id;
    }
}