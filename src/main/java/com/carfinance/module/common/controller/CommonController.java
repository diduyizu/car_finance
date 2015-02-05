package com.carfinance.module.common.controller;

import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.log.service.LogService;
import com.carfinance.module.login.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}