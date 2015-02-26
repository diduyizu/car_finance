package com.carfinance.module.storemanage.controller;

import com.carfinance.module.common.domain.City;
import com.carfinance.module.common.domain.Country;
import com.carfinance.module.common.domain.Enum;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.storemanage.domain.Store;
import com.carfinance.module.storemanage.service.StoreManageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/store")
public class StoreManageController {
	final Logger logger = LoggerFactory.getLogger(StoreManageController.class);
	
	@Autowired
	private CommonService commonService;
    @Autowired
    private StoreManageService storeManageService;
    @Autowired
    private InitService initService;
	@Autowired
	private Properties appProps;

    /**
     * 门店管理-门店查询
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/query/index" , method = {RequestMethod.GET , RequestMethod.POST})
    public String queryIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("store.query.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String store_name = request.getParameter("store_name");
        Map<String , Object> map = this.storeManageService.getStoreList(store_name , start , size);

        long total = (Long)map.get("total");;
        List<Store> store_List = (List<Store>)map.get("store_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("page_url" , request.getRequestURI());

        model.addAttribute("store_name" , store_name);
        model.addAttribute("store_List" , store_List);
        return "/module/storemanage/query/index";
    }

    @RequestMapping(value = "/add/index" , method = RequestMethod.GET)
    public String addIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        List<Enum> org_type_list = this.commonService.getEnumFielList("ORG_TYPE");
        List<Enum> province_list = this.commonService.getEnumFielList("SYS_PROVINCE");

        model.addAttribute("province_list" , province_list);
        model.addAttribute("org_type_list" , org_type_list);
        return "/module/storemanage/add/index";
    }

    /**
     * 根据省份id，获取该省份下的所有地市
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add/provincecitylist" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> provinceCityList(Model model , HttpServletRequest request , HttpServletResponse response) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/plain;charset=utf-8");
        User user = (User)request.getSession().getAttribute("user");

        long province_id = Long.valueOf(request.getParameter("province_id"));
        List<City> city_list = this.commonService.getProvinceCityList(province_id);

        String json = JSONArray.fromObject(city_list).toString();
        return new ResponseEntity<String>(json , responseHeaders, HttpStatus.OK);
    }

    /**
     * 根据地市id，获取该地市下所有区县
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add/citycountrylist" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> cityCountryList(Model model , HttpServletRequest request , HttpServletResponse response) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/plain;charset=utf-8");
        User user = (User)request.getSession().getAttribute("user");

        long city_id = Long.valueOf(request.getParameter("city_id"));
        List<Country> country_list = this.commonService.getCityCountryList(city_id);

        String json = JSONArray.fromObject(country_list).toString();
        return new ResponseEntity<String>(json , responseHeaders, HttpStatus.OK);
    }

    /**
     * 执行新增操作
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add/doadd" , method = RequestMethod.POST)
    @ResponseBody
    public int addDoAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        long province_id = Long.valueOf(request.getParameter("province_id"));
        long city_id = Long.valueOf(request.getParameter("city_id"));
        long country_id = Long.valueOf(request.getParameter("country_id"));
        long store_type = Long.valueOf(request.getParameter("store_type"));
        String store_name = request.getParameter("store_name");
        String store_address = request.getParameter("store_address");

        return this.storeManageService.createStore(province_id , city_id , country_id , store_type , 0 ,  store_name , store_address);
    }
}