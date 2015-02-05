package com.carfinance.module.storemanage.service;

import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.domain.Menu;
import com.carfinance.module.common.domain.Org;
import com.carfinance.module.common.domain.Role;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.peoplemanage.dao.PeopleManageDao;
import com.carfinance.module.peoplemanage.domain.OrgUserRole;
import com.carfinance.module.storemanage.dao.StoreManageDao;
import com.carfinance.utils.DateUtil;
import com.carfinance.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 
 * @author jiangyin
 */
@Service
public class StoreManageService {
	
	static Logger logger = LoggerFactory.getLogger(StoreManageService.class);
	
	@Autowired
	private ManageMemcacdedClient memcachedClient;
	@Autowired
	private StoreManageDao storeManageDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitService initService;
    @Autowired
    private CommonDao commonDao;


}
