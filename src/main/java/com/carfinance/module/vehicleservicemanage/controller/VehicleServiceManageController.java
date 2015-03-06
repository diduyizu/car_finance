package com.carfinance.module.vehicleservicemanage.controller;

import com.carfinance.module.common.domain.City;
import com.carfinance.module.common.domain.Org;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.storemanage.service.StoreManageService;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
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

    /**
     * 车辆预约单列表首页
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/reservation/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String registerIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
//        String current_city = request.getParameter("current_city");
//        String brand = request.getParameter("brand");
//        String vehicle_model = request.getParameter("model");
//        String license_plate = request.getParameter("license_plate");
//        String lease_status = request.getParameter("lease_status");

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
        Map<String , Object> map = this.vehicleServiceManageService.getOrgReservationList(original_org, start, size);

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

//        model.addAttribute("current_city" , current_city);
        model.addAttribute("original_org" , original_org);
        model.addAttribute("original_org_name" , original_org_name);
//        model.addAttribute("brand" , brand);
//        model.addAttribute("model" , vehicle_model);
//        model.addAttribute("license_plate" , license_plate);

        model.addAttribute("sys_used_city_list" , sys_used_city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("reservation_list" , reservation_list);
        return "/module/vehicleservicemanage/reservation/index";
    }

    /**
     * 跳转至车辆预约单录入页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/reservation/add" , method = RequestMethod.GET)
    public String registerAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        //获取用户角色列表
        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        List<City> city_list = this.commonService.getSysUsedCityList();

        model.addAttribute("city_list" , city_list);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        return "/module/vehicleservicemanage/reservation/add";
    }

    @RequestMapping(value = "/reservation/doadd" , method = RequestMethod.POST)
    @ResponseBody
    public int ReservationDoAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String original_org = request.getParameter("original_org");
        String carframe_model = request.getParameter("model");
        String customer_name = request.getParameter("customer_name");
        String customer_dn= request.getParameter("customer_dn");
        String use_begin= request.getParameter("use_begin");
        String use_end= request.getParameter("use_end");
        double unit_price= Double.valueOf( request.getParameter("unit_price"));
        long quantity= Long.valueOf(request.getParameter("quantity"));
        int with_driver= Integer.valueOf(request.getParameter("with_driver"));
        int expenses_self= Integer.valueOf(request.getParameter("expenses_self"));
        String employee_id= request.getParameter("employee_id");
        String employee_name= request.getParameter("employee_name");

        return this.vehicleServiceManageService.addReservation(original_org , carframe_model , customer_name , customer_dn ,
                use_begin , use_end , unit_price , quantity , with_driver , expenses_self , employee_id , employee_name , user.getUser_id());
    }
}