package com.carfinance.module.common.dao;

import com.carfinance.module.common.domain.*;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.login.domain.UserRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.carfinance.core.dao.BaseJdbcDaoImpl;

import java.util.List;


@Repository
public class CommonDao extends BaseJdbcDaoImpl{

	final Logger logger = LoggerFactory.getLogger(CommonDao.class);
	
	/**
	 * 获取序列
	 * @param seq_name
	 * @return
	 */
	public long getNextVal(String seq_name) {
		String sql="select nextval(?)";
		Object[] o = new Object[] {seq_name};
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().queryForLong(sql , o);
	}

    /**
     * 获取某一省份的地市列表
     * @param province_id
     * @return
     */
    public List<City> getProvinceCityList(long province_id) {
        String sql="select * from sys_city where province_id = ?";
        Object[] o = new Object[] { province_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o ,new CityRowMapper());
    }

    /**
     * 获取某一省份的地市列表
     * @param city_id
     * @return
     */
    public List<Country> getCityCountryList(long city_id) {
        String sql="select * from sys_country where city_id = ?";
        Object[] o = new Object[] { city_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o , new CountryRowMapper());
    }

    /**
     * 获取某一用户的角色列表
     * @param user_id
     * @return
     */
    public List<UserRole> getUserRoleList(long user_id) {
        String sql="select a.* , b.org_name from user_role a , sys_org b where a.org_id = b.org_id and a.user_id = ? order by a.role_id";
        Object[] o = new Object[] { user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql , o  , new UserRoleRowMapper());
    }

    /**
     * 根据某菜单id，获取该菜单的所有子菜单
     * @param  parent_meun_id
     * @return
     */
    public List<Menu> getMenuListByTopMenuid(long parent_meun_id) {
        String sql="select * from sys_menu where pid = ? and status = 1 order by menu_id";
        Object[] o = new Object[] { parent_meun_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql , o  , new MenuRowMapper());
    }

    /**
     * 获取某一角色可操作的菜单
     * 包括顶层菜单，和子菜单
     * @param role_id
     * @return
     */
    public List<Menu> getRoleMenuList(long role_id) {
        String sql="select b.* from sys_role_menu a , sys_menu b where a.menu_id = b.menu_id and a.status = 1 and a.role_id = ? order by a.menu_id";
        Object[] o = new Object[] { role_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql , o  , new MenuRowMapper());
    }

    /**
     * 根据user_id获取用户
     * @param user_id
     * @return
     */
    public User getUserById(long user_id) {
        String sql = "select * from users t WHERE t.user_id = ?";
        Object[] o = new Object[] { user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        try {
            return this.getJdbcTemplate().queryForObject(sql, o, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.info("database is null for query.");
            return null;
        }
    }

    /**
     * 获取用户组织列表
     * @param user_id
     * @return
     */
    public List<Org> getUserOrgList(long user_id) {
        String sql="select distinct b.org_id ,  b.org_name , b.pid , b.org_type , b.org_province , b.org_city , b.org_country , " +
                "b.org_address , b.org_type_name , b.org_province_name , b.org_city_name , b.org_country_name  " +
                "from user_role a , sys_org b where a.org_id = b.org_id and a.user_id = ? order by b.pid , b.org_type";
        Object[] o = new Object[] { user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql , o  , new OrgRowMapper());
    }

    /**
     * 获取系统中已经被使用过的地市
     * @return
     */
    public List<City> getSysUsedCityList() {
        String sql="select * from sys_city where status = 1 order by province_id , city_id";
        Object[] o = new Object[] { };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql , o  , new CityRowMapper());
    }

    /**
     * 获取子组织
     * @param parent_org_ids
     * @return
     */
    public List<Org> getUserSubOrgList(String parent_org_ids) {
        String sql="SELECT b.* FROM sys_org b where pid in (?) order by b.pid , b.org_type";
        Object[] o = new Object[] { parent_org_ids };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql , o  , new OrgRowMapper());
    }

    /**
     * 根据用户、角色，获取该用户所有组织列表
     * @param user_id
     * @param role_id
     * @return
     */
    public List<Org> getUserRoleOrgList(long user_id , long role_id) {
        String sql="select b.* from user_role a , sys_org b where a.org_id = b.org_id and a.user_id = ? and role_id = ? order by b.pid , b.org_type";
        Object[] o = new Object[] { user_id , role_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql , o  , new OrgRowMapper());
    }
}
