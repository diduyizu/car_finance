package com.carfinance.module.peoplemanage.service;

import com.carfinance.core.cache.CacheKey;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.domain.Menu;
import com.carfinance.module.common.domain.Org;
import com.carfinance.module.common.domain.Role;
import com.carfinance.module.common.domain.UserRole;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.common.service.ManageMemcacdedClient;
import com.carfinance.module.common.vo.MenuVo;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.dao.LoginDao;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.peoplemanage.dao.PeopleManageDao;
import com.carfinance.module.peoplemanage.domain.OrgUserRole;
import com.carfinance.utils.DateUtil;
import com.carfinance.utils.MD5Util;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class PeopleManageService {
	
	static Logger logger = LoggerFactory.getLogger(PeopleManageService.class);
	
	@Autowired
	private ManageMemcacdedClient memcachedClient;
	@Autowired
	private PeopleManageDao peopleManageDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitService initService;
    @Autowired
    private CommonDao commonDao;

    public Role roleQuery(String role_name) {
        return this.peopleManageDao.roleQuery(role_name);
    }

    /**
     * 人员管理－人员管理
     * 应该获取当前用户所在部门的所有用户列表
     * @return
     */
    public Map<String , Object> getOrgUserlist(long org_id , String user_name , int start ,  int size) {
        long total = 1;
        if(user_name == null) {
            total = peopleManageDao.getOrgUserCount(org_id);//某组织下用户总数
        }
        List<User> org_user_list = peopleManageDao.getOrgUserlist(org_id , user_name , start , size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("org_user_list" , org_user_list);
        return map;
    }

    /**
     * 增加用户
     * @param userid 操作人员id
     * @param login_name
     * @param login_pwd
     * @param user_name
     * @param nick_name
     * @param org_id
     * @param role_id
     * @return
     */
    public int peopleDoAdd(long userid , String login_name, String login_pwd, String user_name, String nick_name , String org_id , String role_id , String employee_id) {
        int check = this.peopleManageDao.checkUserName(login_name);
        int result = 0;
        if(check == 0) {
            String login_pwd_md5 = MD5Util.MD5Encrypt(login_pwd);
            long add_userid = commonDao.getNextVal("UsersSeq");
            result = this.peopleManageDao.inertUser(add_userid , login_name , login_pwd_md5 , user_name , nick_name , userid , DateUtil.date2String(new Date()) , employee_id);
            if(result == 1) {//成功以后，将该用户角色和组织对应起来
                this.peopleManageDao.insertUserRole(add_userid , Long.valueOf(role_id) , Long.valueOf(org_id));
            }
        }
        return result;
    }

    /**
     * 修改用户
     * @param user_name
     * @return
     */
    public int peopleDoEdit(long userid , String user_name , long edited_user_id) {
        return this.peopleManageDao.editUser(edited_user_id , user_name , userid);
    }

    /**
     * 删除用户
     * @param userid
     * @param edited_user_id
     * @return
     */
    public int peopleDoDelete(long userid , long edited_user_id) {
        return this.peopleManageDao.deleteUser(edited_user_id);
    }

    /**
     * 获取角色对应权限列表
     * @return
     */
    public Map<String , List<Menu>> getRoleMenu(long role_id) {
        Map<String , List<Menu>> map = new HashMap<String, List<Menu>>();
        List<Menu> top_menu_list = new ArrayList<Menu>();//顶层菜单
        List<Menu> sub_menu_list = new ArrayList<Menu>();//子菜单

        List<Menu> sys_menu_list = initService.getSysMenu();//系统全部菜单
        List<Menu> role_menu_list = this.commonService.getRoleMenuList(role_id);//角色对应菜单
        for(Menu sys_menu : sys_menu_list) {
            for(Menu role_menu : role_menu_list) {
                if(sys_menu.getMenu_id() == role_menu.getMenu_id()) {
                    sys_menu.setRole_menu_status(1);
                }
            }
        }
        for(Menu menu : sys_menu_list) {
            if(menu.getPid() == 0) {//顶层菜单
                if(!top_menu_list.contains(menu)) {//顶层菜单列表中没有该菜单，将该菜单加入到顶层菜单列表中
                    top_menu_list.add(menu);
                }
            } else {
                sub_menu_list.add(menu);
            }
        }
        map.put("top_menu_list" , top_menu_list);
        map.put("sub_menu_list" , sub_menu_list);

        return map;
    }

    public Map<String , Object> getOrgUserRolelist(long org_id , String user_name , int start ,  int size) {
        long total = 1;
        if(user_name == null) {
            total = peopleManageDao.getOrgUserRolelist(org_id);//某组织下用户角色总数
        }
        List<OrgUserRole> org_user_list = peopleManageDao.getOrgUserRolelist(org_id, user_name , start, size);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("total" , total);
        map.put("org_user_role_list" , org_user_list);
        return map;
    }

    /**
     * 获取某用户在某组织下所有角色
     * @param org_id
     * @param user_id
     * @return
     */
    public List<OrgUserRole> getUserOrgRoleList(long org_id , long user_id) {
        List<Role> role_list = this.initService.getRole();
        List<OrgUserRole> user_role_list = this.peopleManageDao.getUserOrgRoleList(org_id, user_id);
        List<OrgUserRole> org_user_role_list = new ArrayList<OrgUserRole>();
        for(Role role : role_list) {
            int user_role_status = 0;
            for(OrgUserRole user_role : user_role_list) {
                if(user_role.getRole_id() == role.getRole_id()) {
                    user_role_status = 1;
                    break;
                }
            }
            OrgUserRole org_user_role = new OrgUserRole(user_id , role.getRole_id() , org_id , user_role_list.get(0).getUser_name()  , role.getRole_name() , user_role_list.get(0).getOrg_name() , user_role_status);
            org_user_role_list.add(org_user_role);
        }
        return org_user_role_list;
    }

    /**
     * 根据org_id获取org信息
     * @param org_id
     * @return
     */
    public Org getOrgByOrgId(long org_id) {
        return this.peopleManageDao.getOrgByOrgId(org_id);
    }

    /**
     * 执行角色对应菜单权限配置
     * @param sub_menu_ids
     * @param role_id
     * @return
     */
    public int roleMenuDoConfig(String sub_menu_ids , long role_id) {
        //找到子菜单对应的父菜单
        String[] sub_menu_arr = sub_menu_ids.substring(0 , sub_menu_ids.length()-1).split(",");
        List<Long> top_menu_list = new ArrayList<Long>();
        for(int i = 0 ; i < sub_menu_arr.length ; i++) {
            String sub_menu_id_str = sub_menu_arr[i];
            String top_menu_id_str = sub_menu_id_str.substring(0 , sub_menu_id_str.length()-2) + "00";
            Long top_menu_id = Long.valueOf(top_menu_id_str);
            if(!top_menu_list.contains(top_menu_id)) {
                top_menu_list.add(top_menu_id);
            }
        }
        String all_menu_id = sub_menu_ids.substring(0 , sub_menu_ids.length()-1);
        for(int i = 0 ; i < top_menu_list.size() ; i++) {
            all_menu_id = all_menu_id + "," + top_menu_list.get(i);
        }

        this.peopleManageDao.roleMenuDoConfig(all_menu_id.split(",") , role_id);
        String key = CacheKey.getRoleMenuListKey(role_id);
        memcachedClient.delete(key);

        return 1;
    }

    public int peopleroleDoEdit(long edited_user_id , long org_id , String role_ids) {
        role_ids = role_ids.substring(0 , role_ids.length()-1);
        this.peopleManageDao.peopleroleDoEdit(edited_user_id, org_id, role_ids.split(","));
        return 1;
    }
}
