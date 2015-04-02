package com.carfinance.module.statisticsmanage.controller;

import com.carfinance.module.common.domain.City;
import com.carfinance.module.common.domain.Org;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.statisticsmanage.domain.Achievement;
import com.carfinance.module.statisticsmanage.domain.AchievementRowMapper;
import com.carfinance.module.statisticsmanage.service.StatisticsManageService;
import com.carfinance.module.storemanage.service.StoreManageService;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceVehsInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleReservationInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/statistics")
public class StatisticsManageController {
	final Logger logger = LoggerFactory.getLogger(StatisticsManageController.class);
	
	@Autowired
	private CommonService commonService;
    @Autowired
    private StatisticsManageService statisticsManageService;
    @Autowired
    private InitService initService;
	@Autowired
	private Properties appProps;

    /**
     * 报表，分日报表，周报表，月报表，季度报表，年报表
     * @param model
     * @param request
     * @param response
     * @return
     */
//    @RequestMapping(value = "/reportform" , method = {RequestMethod.GET , RequestMethod.POST})
//    public String reportform(Model model , HttpServletRequest request , HttpServletResponse response) {
//
//
//
//
//
//    }

    /**
     * 车辆租用/收入信息
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/vehicleincom" , method = {RequestMethod.GET , RequestMethod.POST})
    public String vehicleincom(Model model , HttpServletRequest request , HttpServletResponse response) {
        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("store.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String vehicle_model = request.getParameter("model");
        String license_plate = request.getParameter("license_plate");
        String begin_date= request.getParameter("begin_date");
        String end_date= request.getParameter("end_date");

        String method = request.getMethod();
        if("GET".equals(method.toUpperCase())) {//get请求，进行编码格式转换
            vehicle_model = this.commonService.characterFormat(vehicle_model , "ISO8859-1" , "UTF-8");
            license_plate = this.commonService.characterFormat(license_plate , "ISO8859-1" , "UTF-8");
        }

        Map<String , Object> map = this.statisticsManageService.getVehicleList(vehicle_model, license_plate, begin_date , end_date ,  start, size);
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
        String condition = "";
        if(vehicle_model != null) {
            condition = condition + "&model="+vehicle_model;
        }
        if(license_plate != null) {
            condition = condition + "&license_plate="+license_plate;
        }
        if(begin_date != null) {
            condition = condition + "&begin_date="+begin_date;
        }
        if(end_date != null) {
            condition = condition + "&end_date="+end_date;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("vehicle_model" , vehicle_model);
        model.addAttribute("license_plate" , license_plate);
        model.addAttribute("begin_date" , begin_date);
        model.addAttribute("end_date" , end_date);

        model.addAttribute("vehicle_list" , vehicle_list);

        return "/module/statistics/vehicleincom/index";
    }

    /**
     * 业务员绩效
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/achievement" , method = {RequestMethod.GET , RequestMethod.POST})
    public String achievement(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("vehicle.reservation.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String original_org_str = request.getParameter("original_org");
        String employee_id_name = request.getParameter("employee_id_name");
        String employee_id = request.getParameter("employee_id");
        String begin_date= request.getParameter("begin_date");
        String end_date= request.getParameter("end_date");

        String method = request.getMethod();
        if("GET".equals(method.toUpperCase())) {//get请求，进行编码格式转换
            employee_id_name = this.commonService.characterFormat(employee_id_name , "ISO8859-1" , "UTF-8");
        }

        List<Org> user_all_org_list = this.commonService.getUserAllOrgList(user.getUser_id());
        long original_org = (original_org_str == null || "".equals(original_org_str.trim())) ? user_all_org_list.get(0).getOrg_id() : Long.valueOf(original_org_str);

        String user_employee_id_name_json = this.commonService.getAllEmployeeIdAndName();

        Map<String , Object> map = this.statisticsManageService.getOrgEmployeeList(original_org, employee_id , begin_date , end_date , start, size);
        long total = (Long)map.get("total");;
        List<Achievement> achievement_list = (List<Achievement>)map.get("achievement_list");

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
        if(original_org_str != null) {
            condition = condition + "&original_org="+original_org_str;
        }
        if(employee_id_name != null) {
            condition = condition + "&employee_id_name="+employee_id_name;
        }
        if(begin_date != null) {
            condition = condition + "&begin_date="+begin_date;
        }
        if(end_date != null) {
            condition = condition + "&end_date="+end_date;
        }
        model.addAttribute("condition" , condition);

        model.addAttribute("original_org" , original_org);
        model.addAttribute("employee_id_name" , employee_id_name);
        model.addAttribute("begin_date" , begin_date);
        model.addAttribute("end_date" , end_date);

        model.addAttribute("user_employee_id_name_json" , user_employee_id_name_json);
        model.addAttribute("user_all_org_list" , user_all_org_list);
        model.addAttribute("achievement_list" , achievement_list);

        return "/module/statistics/achievement/index";
    }


    /**
     * 车辆管理－车辆入库登记
     * @param model
     * @param request
     * @param response
     * @return
     */
//    @RequestMapping(value = "/standingbook/index" , method = {RequestMethod.GET , RequestMethod.POST})
//    public String registerIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
//        User user = (User)request.getSession().getAttribute("user");
//
//        String pageindexStr = request.getParameter("page_index");//第几页
//        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
//        int size = Integer.valueOf(appProps.get("store.query.size").toString());//每页显示条数
//        int start = (page_index - 1) * size;
//
//        Map<String , Object> map = this.statisticsManageService.getVehicleCountList(start , size);
//
//        long total = (Long)map.get("total");;
//        List<VehicleContraceInfo> contrace_list = (List<VehicleContraceInfo>)map.get("contrace_list");
//
//        long temp = (total - 1) <= 0 ? 0 : (total - 1);
//        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
//        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
//        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);
//
//        model.addAttribute("current_page" , page_index);
//        model.addAttribute("pages" , pages);
//        model.addAttribute("prepage" , prepages);
//        model.addAttribute("nextpage" , nextpages);
//        model.addAttribute("page_url" , request.getRequestURI());
//        model.addAttribute("condition" , "");
//        model.addAttribute("contrace_list" , contrace_list);
//        return "/module/statistics/standingbook/index";
//    }
}