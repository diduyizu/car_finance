package com.carfinance.module.customermanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.customermanage.domain.CustomerInfo;
import com.carfinance.module.customermanage.domain.CustomerInfoRowMapper;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInfoRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public long getCustomerCount() {
        String sql = "select count(1) from customer_info ";
        Object[] o = new Object[] { };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取客户列表
     * @param identity_id
     * @param start
     * @param size
     * @return
     */
    public List<CustomerInfo> getCustomerList(String identity_id , int start , int size) {
        String sql = "select * from customer_info ";
        List<Object> param = new ArrayList<Object>();

        if(identity_id != null && !"".equals(identity_id.trim())) {
            sql = sql + " where identity_id = ? ";
            param.add(identity_id);
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

    public int addCustomerInfo(String identity_id , String customer_name , String customer_dn , String customer_email , long create_by) {
        String sql = "insert into customer_info(identity_id , customer_name , customer_dn , customer_email , create_by) values (?,?,?,?,?)";
        Object[] o = new Object[] { identity_id , customer_name , customer_dn , customer_email , create_by };
        return this.getJdbcTemplate().update(sql , o);
    }

}
