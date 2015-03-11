package com.carfinance.module.vehicleservicemanage.controller;

import com.carfinance.module.common.domain.City;
import com.carfinance.module.common.domain.Org;
import com.carfinance.module.common.domain.UserRole;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.storemanage.service.StoreManageService;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.service.VehicleManageService;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleReservationInfo;
import com.carfinance.module.vehicleservicemanage.service.VehicleServiceManageService;
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
    private InitService initService;
	@Autowired
	private Properties appProps;
    @Autowired
    private VehicleManageService vehicleManageService;

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

        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
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
        String customer_name = request.getParameter("customer_name");
        String customer_dn= request.getParameter("customer_dn");
        String use_begin= request.getParameter("use_begin");
        String use_end= request.getParameter("use_end");
        String employee_id= request.getParameter("employee_id");
        String employee_name= request.getParameter("employee_name");
        String remark= request.getParameter("remark");

        return this.vehicleServiceManageService.addReservation(original_org, customer_name, customer_dn,
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
        Map<String , Object> map = this.vehicleServiceManageService.getOrgContraceList(original_org, null , start, size , false);

        long total = (Long)map.get("total");;
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
     * 同时，将预约单状态，改为完结
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/add" , method = RequestMethod.GET)
    public String contraceAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String reservation_org_id = request.getParameter("reservation_org_id");
        long reservation_id = Long.valueOf(reservation_org_id.split(",")[0]);
        long org_id = Long.valueOf(reservation_org_id.split(",")[1]);

        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();

        this.vehicleServiceManageService.reservationDoCancel(reservation_id, user.getUser_id(), 1);//将预约单状态改为完结
        //同时，生成合同，此时合同是空合同
        //跳转至合同页面，输入内容，点击提交，其实是对现在生成的合同内容进行更新
        long contrace_id = this.vehicleServiceManageService.addContrace(reservation_id, org_id, user.getUser_id());
        VehicleReservationInfo vehicleReservationInfo = this.vehicleServiceManageService.getVehicleReservationInfoById(reservation_id);

        model.addAttribute("contrace_id" , contrace_id);
        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("vehicleReservationInfo" , vehicleReservationInfo);
        return "/module/vehicleservicemanage/contrace/add";
    }

    /**
     * 新增合同，其实是更新、修改合同
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

        return this.vehicleServiceManageService.modifycontrace(contrace_id, original_org, customer_name, customer_type, customer_dn,
               certificate_type, certificate_no, use_begin, use_end, employee_id, employee_name, remark, user.getUser_id());
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

        long contrace_id = Long.valueOf(request.getParameter("contrace_id"));
        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();

        VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageService.getVehicleContraceInfoById(contrace_id);//获取合同详情

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
        String vehicle_id_price = request.getParameter("vehicle_id_price");
        long vehicle_id = Long.valueOf(vehicle_id_price.split(",")[0]);
        double vehicle_price = Double.valueOf(vehicle_id_price.split(",")[1]);
        return this.vehicleServiceManageService.contraceDoChooseVech(contrace_id, vehicle_id , user.getUser_id() , vehicle_price);
    }

    //TODO 业务员选择配驾员，分配给某车辆

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
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String status = request.getParameter("status") == null ? "1" : request.getParameter("status");//店长默认查看待审核状态的列表

        //TODO 管理员获取全部组织列表
        //获取当前用户存在店长角色的组织列表，角色表中20008对应店长
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
        if(isSysadmin) {
            user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        } else {
            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20008);
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

        Map<String , Object> map = this.vehicleServiceManageService.getOrgContraceList(original_org, status, start, size , false);
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
        String status = request.getParameter("status") == null ? "2" : request.getParameter("status");//市店长默认查看店长审核通过的列表

        //获取当前用户存在市门店的组织列表，管理员获取全部组织列表
        //TODO 管理员获取全部组织列表
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
        if(isSysadmin) {
            user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        } else {
            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20009);
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

        Map<String , Object> map = this.vehicleServiceManageService.getOrgContraceList(original_org, status, start, size , true);
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
        String status = request.getParameter("status") == null ? "3" : request.getParameter("status");//区域经理默认查看市店长审核通过的列表

        //TODO 管理员获取全部组织列表
        //获取当前用户存在区域经理的组织列表，管理员获取全部组织列表
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
        if(isSysadmin) {
            user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        } else {
            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20010);
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

        Map<String , Object> map = this.vehicleServiceManageService.getOrgContraceList(original_org, status, start, size , true);
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
        String status = request.getParameter("status") == null ? "4" : request.getParameter("status");//财务默认查看区域经理审核通过的列表

        //获取当前用户存在风控的组织列表，管理员获取全部组织列表
        //TODO 管理员获取全部组织列表
        boolean isSysadmin = this.commonService.isSysadmin(user.getUser_id());
        List<Org> user_role_org_list;
        if(isSysadmin) {
            user_role_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        } else {
            user_role_org_list = this.commonService.getUserRoleOrgList(user.getUser_id() , 20005);
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

        Map<String , Object> map = this.vehicleServiceManageService.getOrgContraceList(original_org, status, start, size, true);
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
     * 业务员结单
     * @param model1
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/contrace/dofinish" , method = RequestMethod.POST)
    @ResponseBody
    public int contraceDoFinish(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");

        return this.vehicleServiceManageService.contraceDoFinish(id, status, user.getUser_id());
    }
}