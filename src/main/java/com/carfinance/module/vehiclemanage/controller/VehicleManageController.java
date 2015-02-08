package com.carfinance.module.vehiclemanage.controller;

import com.carfinance.module.common.domain.City;
import com.carfinance.module.common.domain.Country;
import com.carfinance.module.common.domain.Enum;
import com.carfinance.module.common.domain.UserRole;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.storemanage.domain.Store;
import com.carfinance.module.storemanage.service.StoreManageService;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.service.VehicleManageService;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        String archive_no = request.getParameter("archive_no");
        String inventory_no = request.getParameter("inventory_no");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        String carframe_no = request.getParameter("carframe_no");
        String engine_no = request.getParameter("engine_no");
        String registry_certificate = request.getParameter("registry_certificate");
        String certificate_direction = request.getParameter("certificate_direction");
        String loan_bank = request.getParameter("loan_bank");
        String consistency_cer = request.getParameter("consistency_cer");
        String check_list  = request.getParameter("check_list");
        String duty_paid_proof = request.getParameter("duty_paid_proof");
        String record = request.getParameter("record");
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
        double vehicle_vessel_tax = Double.valueOf(request.getParameter("vehicle_vessel_tax"));
        String strong_insurance_expire_at= request.getParameter("strong_insurance_expire_at");
        double business_insurance = Double.valueOf(request.getParameter("business_insurance"));
        String business_insurance_expire_at  = request.getParameter("business_insurance_expire_at");
        String remark = request.getParameter("remark");
        long original_org = Long.valueOf(request.getParameter("original_org"));

        return this.vehicleManageService.addVehicle(archive_no , inventory_no , brand , model , color , carframe_no , engine_no ,
                registry_certificate , certificate_direction , loan_bank , consistency_cer , check_list ,
                duty_paid_proof , record , buy_at , supplier , license_plate , card_at ,
                limited_at , guide_price , vehicle_price , vehicle_tax , insurance_company ,
                strong_insurance , vehicle_vessel_tax , strong_insurance_expire_at , business_insurance ,
                business_insurance_expire_at , remark , user.getUser_id() , original_org);
    }






}