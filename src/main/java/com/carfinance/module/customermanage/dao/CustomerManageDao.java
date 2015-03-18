package com.carfinance.module.customermanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.customermanage.domain.CustomerAnnex;
import com.carfinance.module.customermanage.domain.CustomerAnnexRowMapper;
import com.carfinance.module.customermanage.domain.CustomerInfo;
import com.carfinance.module.customermanage.domain.CustomerInfoRowMapper;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInfoRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class CustomerManageDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(CustomerManageDao.class);

    @Autowired
    private CommonDao commonDao;

    /**
     * 获取客户总数
     * @return
     */
    public long getCustomerCount(String customer_name , String dn , String certificate_no) {
        String sql = "select count(1) from customer_info where 1=1 ";
        List<Object> param = new ArrayList<Object>();

        if(customer_name != null && !"".equals(customer_name.trim())) {
            sql = sql + " and customer_name = ? ";
            param.add(customer_name);
        }
        if(dn != null && !"".equals(dn.trim())) {
            sql = sql + " and customer_dn = ? ";
            param.add(dn);
        }
        if(certificate_no != null && !"".equals(certificate_no.trim())) {
            sql = sql + " and certificate_no = ? ";
            param.add(certificate_no);
        }
        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取客户列表
     * @param certificate_no
     * @param start
     * @param size
     * @return
     */
    public List<CustomerInfo> getCustomerList(String customer_name , String dn , String certificate_no , int start , int size) {
        String sql = "select * from customer_info where 1=1 ";
        List<Object> param = new ArrayList<Object>();

        if(customer_name != null && !"".equals(customer_name.trim())) {
            sql = sql + " and customer_name = ? ";
            param.add(customer_name);
        }
        if(dn != null && !"".equals(dn.trim())) {
            sql = sql + " and customer_dn = ? ";
            param.add(dn);
        }
        if(certificate_no != null && !"".equals(certificate_no.trim())) {
            sql = sql + " and certificate_no = ? ";
            param.add(certificate_no);
        }
        sql = sql + " order by id desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new CustomerInfoRowMapper());
    }

    public long addCustomerInfo(String certificate_type , String certificate_no , String customer_name , String customer_dn , String customer_email , String customer_type , String customer_house , String customer_vehicle , String customer_guarantee , String vip_no , long create_by) {
        long customer_id = this.commonDao.getNextVal("CustomerSeq");
        String sql = "insert into customer_info(id , certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , vip_no , create_by) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] o = new Object[] { customer_id , certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , vip_no , create_by };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        long result = this.getJdbcTemplate().update(sql , o);
        if(result > 0) {
            result = customer_id;
        }
        return result;
    }

    public CustomerInfo getCustomrInfobyId(long id) {
        try{
            String sql = "select * from customer_info where id = ?";
            Object[] o = new Object[] { id };
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return this.getJdbcTemplate().queryForObject(sql , o , new CustomerInfoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int modifyCustomerInfo(long id , String certificate_type , String certificate_no , String customer_name , String customer_dn , String customer_email , String customer_type , String customer_house , String customer_vehicle , String customer_guarantee , String vip_no , long update_by) {
        String sql = "update customer_info t set t.certificate_type = ? , t.certificate_no = ? , t.customer_name = ? , t.customer_dn = ? , t.customer_email = ? , t.customer_type = ? , customer_house = ? , customer_vehicle = ? , customer_guarantee = ? , t.update_by = ? , t.vip_no = ? where t.id = ?";
        Object[] o = new Object[] { certificate_type , certificate_no , customer_name , customer_dn , customer_email , customer_type , customer_house , customer_vehicle , customer_guarantee , update_by , vip_no , id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql , o);
    }

    public List<CustomerAnnex> getCustomrAnnexListbyCustomerId(long customer_id) {
        String sql = "select * from  customer_annex where customer_id = ?";
        Object[] o = new Object[] { customer_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new CustomerAnnexRowMapper());
    }

    public void updateCustomerAnnex(long customer_id , String annex_name , String url) {
        String sql = "insert into customer_annex (customer_id , annex_name , url) values (?,?,?)";
        Object[] o = new Object[] { customer_id , annex_name , url };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        this.getJdbcTemplate().update(sql, o);
    }

    public int addCustomerCertificateUrl(long customer_id , String annex_name , String certificate_url) {
        String sql = "update customer_info set certificate_url = ? , certificate_name = ?  where id = ?";
        Object[] o = new Object[] { certificate_url , annex_name , customer_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

}
