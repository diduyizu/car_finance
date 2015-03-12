package com.carfinance.module.common.controller;

import com.carfinance.module.common.domain.City;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.customermanage.domain.CustomerInfo;
import com.carfinance.module.log.service.LogService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.login.service.LoginService;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import net.sf.json.JSONArray;
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
import java.util.Properties;


@Controller
@RequestMapping("/common")
public class CommonController {
	final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private LogService logService;
	@Autowired
	private Properties appProps;

    /**
     * jquery Autocomplete
     * 获取客户列表
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getcustomer" , method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getAllCustomerInfo(Model model , HttpServletRequest request , HttpServletResponse response) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/plain;charset=utf-8");
        User user = (User)request.getSession().getAttribute("user");

        List<CustomerInfo> customer_list = this.commonService.getAllCustomerInfo();
        String json = JSONArray.fromObject(customer_list).toString();
        return new ResponseEntity<String>(json , responseHeaders, HttpStatus.OK);
    }

    /**
     * jquery Autocomplete
     * 获取车辆列表
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getvehicle" , method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> allVehicles(Model model , HttpServletRequest request , HttpServletResponse response) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/plain;charset=utf-8");
        User user = (User)request.getSession().getAttribute("user");

        List<VehicleInfo> customer_list = this.commonService.getAllVehicles();
        String json = JSONArray.fromObject(customer_list).toString();
        return new ResponseEntity<String>(json , responseHeaders, HttpStatus.OK);
    }


}