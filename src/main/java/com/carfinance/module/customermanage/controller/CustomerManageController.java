package com.carfinance.module.customermanage.controller;

import com.carfinance.module.common.domain.UserRole;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.customermanage.domain.CustomerInfo;
import com.carfinance.module.customermanage.service.CustomerManageService;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.storemanage.service.StoreManageService;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
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
@RequestMapping("/customer")
public class CustomerManageController {
	final Logger logger = LoggerFactory.getLogger(CustomerManageController.class);
	
	@Autowired
	private CommonService commonService;
    @Autowired
    private CustomerManageService customerManageService;
    @Autowired
    private InitService initService;
	@Autowired
	private Properties appProps;

    /**
     * 客户资料管理
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/info/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String customerInfoIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("customer.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String identity_id = request.getParameter("identity_id");
        Map<String , Object> map = this.customerManageService.getCustomerList(identity_id, start, size);

        long total = (Long)map.get("total");;
        List<CustomerInfo> customer_list = (List<CustomerInfo>)map.get("customer_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("identity_id" , identity_id);
        model.addAttribute("customer_list" , customer_list);
        return "/module/customermanage/index";
    }

    @RequestMapping(value = "/info/add" , method = RequestMethod.GET)
    public String customerInfoAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");
        return "/module/customermanage/add";
    }

    @RequestMapping(value = "/info/doadd" , method = RequestMethod.POST)
    @ResponseBody
    public int customerInfoDoAdd(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String identity_id = request.getParameter("identity_id");
        String customer_name = request.getParameter("customer_name");
        String customer_dn = request.getParameter("customer_dn");
        String customer_email= request.getParameter("customer_email");
        String customer_type= request.getParameter("customer_type");

        return this.customerManageService.addCustomerInfo(identity_id , customer_name , customer_dn , customer_email , customer_type ,  user.getUser_id());
    }

    @RequestMapping(value = "/info/modify" , method = RequestMethod.GET)
    public String customerInfoModify(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        CustomerInfo customerInfo = this.customerManageService.getCustomrInfobyId(id);

        model.addAttribute("customer_info" , customerInfo);
        return "/module/customermanage/modify";
    }

    @RequestMapping(value = "/info/domodify" , method = RequestMethod.POST)
    @ResponseBody
    public int customerInfoDoModify(Model model1 , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        String identity_id = request.getParameter("identity_id");
        String customer_name = request.getParameter("customer_name");
        String customer_dn = request.getParameter("customer_dn");
        String customer_email= request.getParameter("customer_email");
        String customer_type= request.getParameter("customer_type");

        return this.customerManageService.modifyCustomerInfo(id , identity_id , customer_name , customer_dn , customer_email , customer_type , user.getUser_id());
    }
}