package com.carfinance.module.customermanage.controller;

import com.carfinance.module.common.domain.UserRole;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.customermanage.domain.CustomerAnnex;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

        String customer_name = request.getParameter("customer_name");
        String dn = request.getParameter("dn");
        String certificate_no = request.getParameter("certificate_no");
        Map<String , Object> map = this.customerManageService.getCustomerList(customer_name , dn , certificate_no, start, size);

        String customer_name_json = this.commonService.getAllCustomerName();

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

        model.addAttribute("customer_name" , customer_name);
        model.addAttribute("dn" , dn);
        model.addAttribute("certificate_no" , certificate_no);
        model.addAttribute("customer_list" , customer_list);
        model.addAttribute("customer_name_json" , customer_name_json);
        return "/module/customermanage/index";
    }

    @RequestMapping(value = "/info/add" , method = RequestMethod.GET)
    public String customerInfoAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");
        return "/module/customermanage/add";
    }

//    @RequestMapping(value = "/info/doadd" , method = RequestMethod.POST)
//    @ResponseBody
//    public int customerInfoDoAdd(Model model1 , HttpServletRequest request , HttpServletResponse response) {
//        User user = (User)request.getSession().getAttribute("user");
//
//        String certificate_type = request.getParameter("certificate_type");
//        String certificate_no = request.getParameter("certificate_no");
//        String customer_name = request.getParameter("customer_name");
//        String customer_dn = request.getParameter("customer_dn");
//        String customer_email= request.getParameter("customer_email");
//        String customer_type= request.getParameter("customer_type");
//        String customer_house = request.getParameter("customer_house") == null ? "无" : request.getParameter("customer_house");
//        String customer_vehicle= request.getParameter("customer_vehicle") == null ? "无" : request.getParameter("customer_vehicle");
//        String customer_guarantee= request.getParameter("customer_guarantee") == null ? "无" : request.getParameter("customer_guarantee");
//        String vip_no = request.getParameter("vip_no");
//
//        return this.customerManageService.addCustomerInfo(certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , vip_no , user.getUser_id());
//    }

    @RequestMapping(value = "/info/doadd" , method = RequestMethod.POST)
    public String customerInfoDoAdd(Model model1 , HttpServletRequest request , HttpServletResponse response , @RequestParam("files") CommonsMultipartFile file_upload) {
        User user = (User)request.getSession().getAttribute("user");

        String certificate_type = request.getParameter("certificate_type");
        String certificate_no = request.getParameter("certificate_no");
        String customer_name = request.getParameter("customer_name");
        String customer_dn = request.getParameter("customer_dn");
        String customer_email= request.getParameter("customer_email");
        String customer_type= request.getParameter("customer_type");
        String customer_house = request.getParameter("customer_house") == null ? "无" : request.getParameter("customer_house");
        String customer_vehicle= request.getParameter("customer_vehicle") == null ? "无" : request.getParameter("customer_vehicle");
        String customer_guarantee= request.getParameter("customer_guarantee") == null ? "无" : request.getParameter("customer_guarantee");
        String vip_no = request.getParameter("vip_no");

        this.customerManageService.addCustomerInfo(request , file_upload , certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , vip_no , user.getUser_id());

        return "redirect:/customer/info/index";
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
        String certificate_type = request.getParameter("certificate_type");
        String certificate_no = request.getParameter("certificate_no");
        String customer_name = request.getParameter("customer_name");
        String customer_dn = request.getParameter("customer_dn");
        String customer_email= request.getParameter("customer_email");
        String customer_type= request.getParameter("customer_type");
        String customer_house = request.getParameter("customer_house") == null ? "无" : request.getParameter("customer_house");
        String customer_vehicle= request.getParameter("customer_vehicle") == null ? "无" : request.getParameter("customer_vehicle");
        String customer_guarantee= request.getParameter("customer_guarantee") == null ? "无" : request.getParameter("customer_guarantee");
        String vip_no = request.getParameter("vip_no");

        return this.customerManageService.modifyCustomerInfo(id , certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , vip_no , user.getUser_id());
    }

    /**
     * 跳转至编辑附件列表页
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/info/modifyannex" , method = RequestMethod.GET)
    public String customerInfoModifyAnnex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long id = Long.valueOf(request.getParameter("id"));
        List<CustomerAnnex> customer_annex_list = this.customerManageService.getCustomrAnnexListbyCustomerId(id);

        model.addAttribute("customer_annex_list" , customer_annex_list);
        model.addAttribute("customer_id" , id);
        return "/module/customermanage/modifyannex";
    }

    @RequestMapping(value = "/annex/upload", method = RequestMethod.POST)
    public String annexUpload(HttpServletRequest request, @RequestParam("files") CommonsMultipartFile[] file_upload) {
        User user = (User) request.getSession().getAttribute("user");

        long customer_id = Long.valueOf(request.getParameter("customer_id"));
        this.customerManageService.annexUpload(request , file_upload , customer_id , user.getUser_id());

        return "redirect:/customer/info/detail?id=" + customer_id;
    }


    @RequestMapping(value = "/info/detail", method = RequestMethod.GET)
    public String annexUpload(Model model , HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        long customer_id = Long.valueOf(request.getParameter("id"));
        CustomerInfo customerInfo = this.customerManageService.getCustomrInfobyId(customer_id);
        List<CustomerAnnex> customer_annex_list = this.customerManageService.getCustomrAnnexListbyCustomerId(customer_id);

        model.addAttribute("customer_info" , customerInfo);
        model.addAttribute("customer_annex_list" , customer_annex_list);
        return "/module/customermanage/detail";
    }
}