package com.carfinance.module.statisticsmanage.controller;

import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.storemanage.service.StoreManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Properties;

@Controller
@RequestMapping("/statistics")
public class StatisticsManageController {
	final Logger logger = LoggerFactory.getLogger(StatisticsManageController.class);
	
	@Autowired
	private CommonService commonService;
    @Autowired
    private StoreManageService storeManageService;
    @Autowired
    private InitService initService;
	@Autowired
	private Properties appProps;


}