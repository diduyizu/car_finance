package com.carfinance.module.common.service;

import java.io.IOException;
import java.util.*;

import com.carfinance.module.common.domain.*;
import com.carfinance.module.common.vo.MenuVo;
import com.carfinance.module.login.domain.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carfinance.core.cache.CacheKey;
import com.carfinance.module.common.dao.CommonDao;

@Service
public class CommonService {
	
	static Logger logger = LoggerFactory.getLogger(CommonService.class);
	
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private ManageMemcacdedClient memcachedClient;
    @Autowired
    private Properties appProps;

    /**
     * 获取用户可操作的菜单列表
     * 一个用户可以对应多个角色
     * 这里获取的是用户所有的菜单，包括顶层菜单，以及其各层子菜单
     * @return
     */
    public Map<String , Object> getUserMenuList(List<UserRole> user_role_list) {
        Map<String , Object> result_map = new HashMap<String, Object>();
        try {
            List<Menu> top_menu_list = new ArrayList<Menu>();//用户顶层菜单
            List<Map<String , Object>> return_list = new ArrayList<Map<String , Object>>();//用户树状结构菜单列表，需要转换成json格式返回到页面
            List<Menu> user_all_menu_list = new ArrayList<Menu>();//用户所有角色下的所有权限

            for(UserRole role : user_role_list) {
                long role_id = role.getRole_id();
                //根据role_id获取该角色对应点菜单
                List<Menu> role_menu_list = this.getRoleMenuList(role_id);//这个是顶层菜单，还需要查找该顶层菜单下点所有二级菜单
                user_all_menu_list.addAll(role_menu_list);
            }

            for(Menu menu : user_all_menu_list) {
                Map<String , Object> aaa = new HashMap<String, Object>();
                if(menu.getPid() == 0) {//顶层菜单，需要组装子菜单
                    if(!top_menu_list.contains(menu)) {//顶层菜单列表中没有该菜单，将该菜单加入到顶层菜单列表中
                        top_menu_list.add(menu);
                    }
                    long menu_id = menu.getMenu_id();//顶层菜单id
                    List<Menu> sub_menu_list = new ArrayList<Menu>();//子菜单
                    for(Menu sub_menu : user_all_menu_list) {
                        if(sub_menu.getPid() == menu_id && !sub_menu_list.contains(sub_menu)) {
                            sub_menu_list.add(sub_menu);
                        }
                    }

//                    List<Menu> sub_menu_list = this.commonDao.getMenuListByTopMenuid(menu_id);//子菜单列表
                    //将上一步得到的菜单列表，组合成页面所需的格式
                    List<MenuVo> sub_menu_vo_list = new ArrayList<MenuVo>();
                    for(Menu sub_menu : sub_menu_list) {
                        MenuVo menu_vo = new MenuVo();
                        menu_vo.setId(sub_menu.getMenu_id());
                        menu_vo.setText(sub_menu.getMenu_name());
                        menu_vo.setHref(appProps.getProperty("base.url") + sub_menu.getMenu_url());
                        sub_menu_vo_list.add(menu_vo);
                    }


                    Map<String , Object> tmp = new HashMap<String, Object>();
                    tmp.put("text" , menu.getMenu_name());
                    tmp.put("items" , sub_menu_vo_list);

                    List<Map<String , Object>> tmp_list = new ArrayList<Map<String, Object>>();
                    tmp_list.add(tmp);
                    aaa.put("id" , menu_id);
                    aaa.put("menu" , tmp_list);

                    return_list.add(aaa);
                }

            }
            result_map.put("top_menu_ist" , top_menu_list);
            result_map.put("menu_list" , new ObjectMapper().writeValueAsString(return_list));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result_map;
    }

    /**
     * 获取单个角色对应的菜单
     * @param role_id
     * @return
     */
    public List<Menu> getRoleMenuList(long role_id) {
        String key = CacheKey.getRoleMenuListKey(role_id);
        List<Menu> role_menu_list = (List<Menu>)memcachedClient.get(key);
        if(role_menu_list == null) {
            role_menu_list = commonDao.getRoleMenuList(role_id);
            memcachedClient.set(key, 60*60, role_menu_list);
        }
        return role_menu_list;
    }

    /**
     * 获取省份、地市对应关系信息
     * @param province_id
     * @return
     */
    public List<City> getProvinceCityList(long province_id) {
        String key = CacheKey.getProvinceCityKey(province_id);
        List<City> city_list = (List<City>)memcachedClient.get(key);
        if(city_list == null) {
            city_list = commonDao.getProvinceCityList(province_id);
            memcachedClient.set(key, 60*60, city_list);
        }
        return city_list;
    }

    /**
     * 获取地市、区县对应关系信息
     * @param city_id
     * @return
     */
    public List<Country> getCityCountryList(long city_id) {
        String key = CacheKey.geCityCountryKey(city_id);
        List<Country> country_list = (List<Country>)memcachedClient.get(key);
        if(country_list == null) {
            country_list = commonDao.getCityCountryList(city_id);
            memcachedClient.set(key, 60*60, country_list);
        }
        return country_list;
    }

    /**
     * 获取用户角色列表，一个用户可能又多个角色
     * @param user_id
     * @return
     */
    public List<UserRole> getUserRoleList(long user_id) {
        String key = CacheKey.getUserRoleListKey(user_id);
        List<UserRole> user_role_list = (List<UserRole>)memcachedClient.get(key);
        if(user_role_list == null) {
            user_role_list = commonDao.getUserRoleList(user_id);
            memcachedClient.set(key, 60*60, user_role_list);
        }
        return user_role_list;
    }

    /**
     * 根据user_id获取用户
     * @param user_id
     * @return
     */
    public User getUserById(long user_id) {
        return this.commonDao.getUserById(user_id);
    }

    /**
     * 获取用户所在组织
     * @param user_id
     * @return
     */
    public List<Org> getUserOrgList(long user_id) {
        return this.commonDao.getUserOrgList(user_id);
    }
}
