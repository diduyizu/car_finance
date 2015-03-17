package com.carfinance.module.peoplemanage.dao;

import com.carfinance.core.dao.BaseJdbcDaoImpl;
import com.carfinance.module.common.dao.CommonDao;
import com.carfinance.module.common.domain.*;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.login.domain.UserRowMapper;
import com.carfinance.module.peoplemanage.domain.OrgUserRole;
import com.carfinance.module.peoplemanage.domain.OrgUserRoleRowMapper;
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
public class PeopleManageDao extends BaseJdbcDaoImpl {
	final Logger logger = LoggerFactory.getLogger(PeopleManageDao.class);

    public Role roleQuery(String role_name) {
        try{
            String sql = "select * from sys_roles where role_name = ?";
            Object[] o = new Object[] { role_name };
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return this.getJdbcTemplate().queryForObject(sql, o, new RoleRowMapper());
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
            return null;
        }
    }

    /**
     * 获取某组织下用户总数
     * @param org_id
     * @return
     */
    public long getOrgUserCount(long org_id) {
        String sql = "select count(1) from user_role where org_id = ?";
        Object[] o = new Object[] { org_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取某组织的用户成员列表
     * @param org_id
     * @return
     */
    public List<User> getOrgUserlist(long org_id , String user_name , int start ,  int size) {
        String sql;
        Object[] o;
        if(user_name == null || "".equals(user_name.trim())) {
            sql = "select distinct c.user_id  , c.login_name , c.login_pwd , c.user_name , c.nick_name , c.head_url , c.birthday , c.address , c.email " +
                    "from user_role a , sys_org b , users c where a.org_id = b.org_id and a.user_id = c.user_id and a.org_id = ? and b.status = 0 order by c.user_id limit ?,?";
            o = new Object[] { org_id , start , size };
        } else {
            sql = "select distinct c.user_id  , c.login_name , c.login_pwd , c.user_name , c.nick_name , c.head_url , c.birthday , c.address , c.email " +
                    "from user_role a , sys_org b , users c where a.org_id = b.org_id and a.user_id = c.user_id and a.org_id = ? and c.user_name = ? and b.status = 0 order by c.user_id limit ?,?";
            o = new Object[] { org_id , user_name , start , size };
        }
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new UserRowMapper());
    }

    /**
     * 登录名不能重复
     * @param login_name
     * @return 0-没有该登录名；1-有
     */
    public int checkUserName(String login_name) {
        String sql = "select count(1) from users where login_name = ?";
        Object[] o = new Object[] { login_name };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForInt(sql, o);
    }

    /**
     * 增加用户
     * @param login_name
     * @param login_pwd
     * @param user_name
     * @param nick_name
     * @param create_by
     * @param create_at
     * @return
     */
    public int inertUser(long userid , String login_name , String login_pwd , String user_name , String nick_name , long create_by , String create_at , String employee_id , String driver_license_no) {
        String sql = "insert into users (user_id , login_name , login_pwd , user_name , nick_name , create_by , create_at , employee_id , driver_license_no) values (?,?,?,?,?,?,?,?,?)";
        Object[] o = new Object[] { userid , login_name , login_pwd , user_name , nick_name , create_by , create_at , employee_id , driver_license_no };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 增加用户组织角色
     * @param userid
     * @param role_id
     * @param org_id
     * @return
     */
    public int insertUserRole(long userid , long role_id , long org_id) {
        String sql = "insert into user_role (user_id , role_id , org_id) values (?,?,?)";
        Object[] o = new Object[] { userid , role_id , org_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql , o);
    }

    /**
     * 编辑用户
     * @param edit_user_id
     * @param user_name
     * @param userid
     * @return
     */
    public int editUser(long edit_user_id , String user_name , long userid) {
        String sql = "update users t set t.user_name = ? where t.user_id = ?";
        Object[] o = new Object[] { user_name , edit_user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql , o);
    }

    /**
     * 删除用户
     * @param deleted_user_id
     * @return
     */
    public int deleteUser(long deleted_user_id) {
        String sql = "update users t set t.status = 0 where t.user_id = ?";
        Object[] o = new Object[] { deleted_user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql , o);
    }


    /**
     * 获取某组织下用户总数
     * @param org_id
     * @return
     */
    public long getOrgUserRoleCount(long org_id , String user_name) {
        String sql;
        Object[] o;
        if(user_name == null || "".equals(user_name)) {
            sql = "select count(distinct a.user_id) from user_role a , users b , sys_roles c , sys_org d " +
                    "where a.user_id = b.user_id and a.role_id = c.role_id and a.org_id = d.org_id and a.org_id = ? and a.status = 1 and d.status = 0";
            o = new Object[] { org_id };
        } else {
            sql = "select count(distinct a.user_id) from user_role a , users b , sys_roles c , sys_org d " +
                    "where a.user_id = b.user_id and a.role_id = c.role_id and a.org_id = d.org_id and a.org_id = ? and b.user_name = ? and a.status = 1 and d.status = 0";
            o = new Object[] { org_id };
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql, o);
    }

    /**
     * 获取某组织的用户成员列表
     * @param org_id
     * @return
     */
    public List<OrgUserRole> getOrgUserRoleList(long org_id , String user_name , int start ,  int size) {
        String sql;
        Object[] o;
//        if(user_name == null || "".equals(user_name)) {
//            sql = "select a.user_id , a.role_id , a.org_id , b.user_name , c.role_name , d.org_name " +
//                    "from user_role a , users b , sys_roles c , sys_org d " +
//                    "where a.user_id = b.user_id and a.role_id = c.role_id and a.org_id = d.org_id and a.org_id = ? and a.status = 1 " +
//                    "order by a.user_id limit ?,?";
//            o = new Object[] { org_id , start , size };
//        } else {
//            sql = "select a.user_id , a.role_id , a.org_id , b.user_name , c.role_name , d.org_name " +
//                    "from user_role a , users b , sys_roles c , sys_org d " +
//                    "where a.user_id = b.user_id and a.role_id = c.role_id and a.org_id = d.org_id and a.org_id = ? and b.user_name = ? and a.status = 1 " +
//                    "order by a.user_id limit ?,?";
//            o = new Object[] { org_id , user_name , start , size };
//        }

        if(user_name == null || "".equals(user_name)) {
            sql = "select distinct a.user_id , 0 as role_id , a.org_id , b.user_name , '' as role_name , d.org_name " +
                    "from user_role a , users b , sys_roles c , sys_org d " +
                    "where a.user_id = b.user_id and a.role_id = c.role_id and a.org_id = d.org_id and a.org_id = ? and a.status = 1 and d.status = 0 " +
                    "order by a.user_id limit ?,?";
            o = new Object[] { org_id , start , size };
        } else {
            sql = "select distinct a.user_id , 0 as role_id , a.org_id , b.user_name , '' as role_name , d.org_name " +
                    "from user_role a , users b , sys_roles c , sys_org d " +
                    "where a.user_id = b.user_id and a.role_id = c.role_id and a.org_id = d.org_id and a.org_id = ? and b.user_name = ? and a.status = 1 and d.status = 0 " +
                    "order by a.user_id limit ?,?";
            o = new Object[] { org_id , user_name , start , size };
        }

        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql, o, new OrgUserRoleRowMapper());
    }

    public List<OrgUserRole> getUserOrgRoleList(long org_id , long user_id) {
        String sql = "select a.user_id , a.role_id , a.org_id , b.user_name , c.role_name , d.org_name " +
                "from user_role a , users b , sys_roles c , sys_org d " +
                "where a.user_id = b.user_id and a.role_id = c.role_id and a.org_id = d.org_id and a.org_id = ? and a.user_id = ? and a.status = 1 and d.status = 0 order by a.role_id ";
        Object[] o = new Object[] { org_id , user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql , o  , new OrgUserRoleRowMapper());
    }

    public Org getOrgByOrgId(long org_id) {
        try{
            String sql = "select * from sys_org where org_id = ? and status = 0 ";
            Object[] o = new Object[] { org_id };
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return this.getJdbcTemplate().queryForObject(sql, o, new OrgRowMapper());
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
            return null;
        }
    }

    /**
     * 删除角色对应菜单
     * @param role_id
     * @return
     */
    public void deleteRoleMenu(long role_id) {
        String sql = "delete from sys_role_menu where role_id = ?";
        Object[] o = new Object[] { role_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        this.getJdbcTemplate().update(sql, o);
    }

    /**
     * 执行角色对应菜单权限配置
     * @param all_menu_ids
     * @param role_id
     */
    public void roleMenuDoConfig(final String[] all_menu_ids , final long role_id) {
        this.deleteRoleMenu(role_id);
        String sql = "insert into sys_role_menu (role_id , menu_id) values (?,?)";
        try {
            this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setLong(1, role_id);
                    ps.setLong(2, Long.valueOf(all_menu_ids[i]));
                }
                public int getBatchSize() {
                    return all_menu_ids.length;//这个方法设定更新记录数，里面存放的都是我们要更新的，所以返回labels.length
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void deleteUserOrgRole(long org_id , long user_id) {
        String sql = "delete from user_role where user_id = ? and org_id = ?";
        Object[] o = new Object[] { user_id , org_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        this.getJdbcTemplate().update(sql, o);
    }

    public void peopleroleDoEdit(final long edited_user_id , final long org_id , final String role_ids) {
        this.deleteUserOrgRole(org_id , edited_user_id);
        if(role_ids.length() > 0) {
            final String[] role_id = role_ids.split(",");
            String sql = "insert into user_role (user_id , role_id , org_id) values (?,?,?)";
            try {
                this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, edited_user_id);
                        ps.setLong(2, Long.valueOf(role_id[i]));
                        ps.setLong(3, org_id);
                    }
                    public int getBatchSize() {
                        return role_id.length;//这个方法设定更新记录数，里面存放的都是我们要更新的，所以返回labels.length
                    }
                });
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public void peopleroleDoAdd(final long edited_user_id , final long org_id , final String[] role_id) {
        String sql = "insert into user_role (user_id , role_id , org_id) values (?,?,?)";
        try {
            this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setLong(1, edited_user_id);
                    ps.setLong(2, Long.valueOf(role_id[i]));
                    ps.setLong(3, org_id);
                }
                public int getBatchSize() {
                    return role_id.length;//这个方法设定更新记录数，里面存放的都是我们要更新的，所以返回labels.length
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 根据user_id获取user信息
     * @param user_id
     * @return
     */
    public User getUserByid(long user_id) {
        try{
            String sql = "select * from users where user_id = ?";
            Object[] o = new Object[] { user_id };
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return this.getJdbcTemplate().queryForObject(sql, o, new UserRowMapper());
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
            return null;
        }
    }

    public List<OrgUserRole> getUserOrgRoleList(long user_id , int start, int size) {
        String sql = "select a.user_id , a.role_id , a.org_id , b.user_name , c.role_name , d.org_name " +
                "from user_role a , users b , sys_roles c , sys_org d " +
                "where a.user_id = b.user_id and a.role_id = c.role_id and a.org_id = d.org_id and a.user_id = ? and a.status = 1 and d.status = 0 order by a.org_id , a.role_id limit ?,? ";
        Object[] o = new Object[] { user_id , start , size };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().query(sql , o  , new OrgUserRoleRowMapper());
    }

    public long getUserOrgRoleCount(long user_id) {
        String sql = "select count(1) from user_role a where a.user_id = ? and a.status = 1 ";
        Object[] o = new Object[] { user_id };
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().queryForLong(sql , o);
    }

    public int doResetPassword(long modify_user_id , String password) {
        String sql = "update users set  login_pwd = ? where user_id = ? ";
        Object[] o = new Object[] { password ,  modify_user_id};
        logger.info(sql.replaceAll("\\?", "{}"), o);
        return this.getJdbcTemplate().update(sql , o);
    }
}
