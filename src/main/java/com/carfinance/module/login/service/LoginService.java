package com.carfinance.module.login.service;

import java.util.List;
import java.util.Map;

import com.carfinance.module.common.domain.UserRole;
import com.carfinance.module.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.login.dao.LoginDao;
import com.carfinance.module.login.domain.User;
import com.carfinance.utils.MD5Util;

/**
 * 
 * @author jiangyin
 */
@Service
public class LoginService {
	
	static Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private ManageMemcacdedClient memcachedClient;
	@Autowired
	private LoginDao loginDao;
    @Autowired
    private CommonService commonService;
	
	/**
	 * 
	 * @return
	 */
	public User index(String name , String pwd) {
		User user = loginDao.getUserByName(name);
		if(user != null) {
			if(!user.getLogin_pwd().equals(MD5Util.MD5Encrypt(pwd))) {
				user = null;
			}
		}
		return user;
	}
}
