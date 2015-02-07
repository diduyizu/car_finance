package com.carfinance.module.vehiclemanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.storemanage.domain.Store;
import com.carfinance.module.storemanage.domain.StoreRowMapper;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.domain.VehicleInfoRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class VehicleManageDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(VehicleManageDao.class);

    @Autowired
    private CommonDao commonDao;

    /**
     * 某个门店下车辆总数
     * @param original_org
     * @return
     */
    public long getSehicleCount(long original_org) {
        String sql = "select count(1) from vehicle_info where original_org = ?";
        Object[] o = new Object[] { original_org };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取某一门店下所有车辆信息
     * @param original_org
     * @param start
     * @param size
     * @return
     */
    public List<VehicleInfo> getVehicleList(long original_org , String brand , String carframe_no , String engine_no , String license_plate ,  int start , int size) {
        String sql = "select * from vehicle_info where original_org = ? ";
        List<Object> param = new ArrayList<Object>();
        param.add(original_org);

        if(brand != null && !"".equals(brand.trim())) {
            sql = sql + " and brand = ? ";
            param.add(brand);
        }
        if(carframe_no != null && !"".equals(carframe_no.trim())) {
            sql = sql + " and carframe_no = ? ";
            param.add(carframe_no);
        }
        if(engine_no != null && !"".equals(engine_no.trim())) {
            sql = sql + " and engine_no = ? ";
            param.add(engine_no);
        }
        if(license_plate != null && !"".equals(license_plate.trim())) {
            sql = sql + " and license_plate = ? ";
            param.add(license_plate);
        }
        sql = sql + " order by id desc limit ?,?";
        param.add(start);
        param.add(size);

        Object[] o = new Object[param.size()];
        for(int i = 0 ; i < param.size() ; i++) {
            o[i] = param.get(i);
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new VehicleInfoRowMapper());
    }

}
