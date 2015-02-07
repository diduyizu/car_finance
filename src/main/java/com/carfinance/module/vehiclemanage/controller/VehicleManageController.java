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
     * 点“新增”
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/register/add" , method = RequestMethod.GET)
    public String registerAddIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        //获取用户角色列表
        List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
        model.addAttribute("user_role_list" , user_role_list);
        return "/module/vehiclemanage/register/add";
    }






}