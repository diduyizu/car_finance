package com.carfinance.module.init.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.carfinance.module.common.domain.Enum;
import com.carfinance.module.common.domain.Menu;
import com.carfinance.module.common.domain.Role;
import com.carfinance.module.init.dao.InitDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.carfinance.core.cache.CacheKey;
import com.carfinance.module.common.service.ManageMemcacdedClient;


@Service
public class InitService {

	final Logger logger = LoggerFactory.getLogger(InitService.class);
	private InitDao initDao;
	private ManageMemcacdedClient memcachedClient;
	private Properties appProps;
	public InitDao getInitDao() {
		return initDao;
	}
	public void setInitDao(InitDao initDao) {
		this.initDao = initDao;
	}
	public ManageMemcacdedClient getMemcachedClient() {
		return memcachedClient;
	}
	public void setMemcachedClient(ManageMemcacdedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	public Properties getApplicationProps() {
		return appProps;
	}
	public void setApplicationProps(Properties applicationProps) {
		this.appProps = applicationProps;
	}

	@Autowired
	public InitService(InitDao initDao,ManageMemcacdedClient memcachedClient,@Qualifier("appProps")Properties applicationProps){
		this.initDao=initDao;
		this.appProps=applicationProps;
		try{
			this.memcachedClient=memcachedClient;
			init();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void init(){
		initRole();
//		initMenu();
		initEnum();//初始化枚举表
	}

	//初始化角色
	private List<Role> initRole(){
		logger.info("开始初始化系统角色数据......");
		String key = CacheKey.getRoleKey();
		List<Role> roleList = initDao.initRole();
		memcachedClient.set(key, 0, roleList);
		logger.info("完成初始化系统角色数据！");
        return roleList;
	}

	//初始化菜单
	private List<Menu> initMenu(){
		logger.info("开始初始化系统菜单数据......");
		String key = CacheKey.getMenuKey();
		List<Menu> menuList = initDao.initMenu();
		memcachedClient.set(key, 0, menuList);
		logger.info("完成初始化系统菜单数据！");
		return menuList;
	}

	//初始化枚举表
	private List<Enum> initEnum(){
		logger.info("开始初始化系统枚举数据......");
		String key = CacheKey.getEnumKey();
		List<Enum> enumList = initDao.initEnum();
		memcachedClient.set(key, 0, enumList);
		logger.info("完成初始化系统枚举数据！");
        return enumList;
	}

    public List<Role> getRole() {
        return this.initRole();
    }

    public List<Enum> getEnum() {
        return this.initEnum();
    }

    public List<Menu> getSysMenu() {
        return this.initMenu();
    }
}
