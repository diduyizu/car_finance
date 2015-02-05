package com.carfinance.core.cache;

public class CacheKey {
    public CacheKey() {
    }

    /**
     * 系统角色
     * @return
     */
    public static String getRoleKey() {
        return "SYS_ROLE";
 	}
    
    /**
     * 系统菜单
     * @return
     */
    public static String getMenuKey() {
        return "SYS_MENU";
 	}
    
    /*
     * 系统需要验证的资源URL
     * 数据结构：MAP<ROLEID,LIST<RESOURCE>>
     */
	public static String getRoleMenuKey() {
       return "SYS_ROLE_MENU";
	}
	
	/**
	 * 系统枚举
	 * @return
	 */
	public static String getEnumKey() {
        return "SYS_ENUM";
 	}

    //某一省份对应的地市列表
    public static String getProvinceCityKey(long province_id) {
        return "PROVINCE_CITY_" + province_id;
    }

    //某一地市对应的区县列表
    public static String geCityCountryKey(long city_id) {
        return "CITY_COUNTRY_" + city_id;
    }

    //获取某一用户等角色列表
    public static String getUserRoleListKey(long user_id) {
        return "USER_ROLE_LIST_" + user_id;
    }

    //获取某一角色可操作的顶层菜单
    public static String getRoleMenuListKey(long role_id) {
        return "ROLE_MENU_LIST_" + role_id;
    }

    //获取系统中所有管理员
    public static String getSysUserListKey() {
        return "SYS_USER_LIST";
    }

    //获取某组织下的所有成员
    public static String getOrgUserListKey(long org_id) {
        return "ORG_USER_LIST_" + org_id;
    }
	
}
