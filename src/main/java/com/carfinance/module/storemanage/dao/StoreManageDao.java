package com.carfinance.module.storemanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.domain.Org;
import com.carfinance.module.common.domain.OrgRowMapper;
import com.carfinance.module.common.domain.Role;
import com.carfinance.module.common.domain.RoleRowMapper;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.login.domain.UserRowMapper;
import com.carfinance.module.peoplemanage.domain.OrgUserRole;
import com.carfinance.module.peoplemanage.domain.OrgUserRoleRowMapper;
import com.carfinance.module.storemanage.domain.Store;
import com.carfinance.module.storemanage.domain.StoreRowMapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Repository
public class StoreManageDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(StoreManageDao.class);

    @Autowired
    private CommonDao commonDao;

    public long getStoreCount(String store_name) {
        String sql;
        Object[] o;
        if(store_name != null && !"".equals(store_name.trim())) {
            sql = "select count(1) from sys_org where org_name = ?";
            o = new Object[] { store_name };
        } else {
            sql = "select count(1) from sys_org";
            o = new Object[] { };
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    public List<Store> getStoreList(String store_name , int start , int size) {
        String sql;
        Object[] o;
        if(store_name != null && !"".equals(store_name.trim())) {
            sql = "select * from sys_org where org_name = ? order by org_type , org_id limit ?,?";
            o = new Object[] { store_name , start , size };
        } else {
            sql = "select * from sys_org order by org_type , org_id limit ?,?";
            o = new Object[] { start , size };
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new StoreRowMapper());
    }


    public int createStore(long province_id , long city_id , long country_id , long store_type , long pid ,  String store_name , String store_address , String  org_type_name , String org_province_name , String org_city_name , String org_country_name) {
        long store_id = this.commonDao.getNextVal("StoreSeq");
        String sql = "insert into sys_org(org_id , org_name , pid , org_type , org_province , org_city , org_country , org_address , org_type_name , org_province_name , org_city_name , org_country_name) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] o = new Object[] { store_id , store_name , pid , store_type ,  province_id , city_id , country_id , store_address , org_type_name , org_province_name , org_city_name , org_country_name };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql , o);
    }

    public Org getCityOrgInfo(long city_id) {
        try{
            String sql = "select * from sys_org where org_city = ? and org_type = 13";
            Object[] o = new Object[] { city_id };
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return this.getJdbcTemplate().queryForObject(sql, o, new OrgRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Org getProvinceOrgInfo(long province_id) {
        try{
            String sql = "select * from sys_org where org_province = ? and org_type = 12";
            Object[] o = new Object[] { province_id };
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return this.getJdbcTemplate().queryForObject(sql, o, new OrgRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateCityUsedStatus(long city_id , long province_id) {
        String sql = "update sys_city set status = 1 where city_id = ? and province_id = ?";
        Object[] o = new Object[] { city_id , province_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        this.getJdbcTemplate().update(sql, o);
    }

}
