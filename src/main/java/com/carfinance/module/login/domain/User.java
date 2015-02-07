package com.carfinance.module.login.domain;

import java.io.Serializable;
import java.util.List;

import com.carfinance.module.common.domain.Role;
import com.carfinance.module.common.domain.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User implements Serializable {
	final Logger logger = LoggerFactory.getLogger(User.class);

	private static final long serialVersionUID = -1696834536010591478L;

	private long user_id;
	private String login_name;
	private String login_pwd;
	private String user_name;
	private String nick_name;
	private String head_url;
	private String birthday;
	private String address;
	private String email;
    private List<UserRole> role_list;

    public List<UserRole> getRole_list() {
        return role_list;
    }

    public void setRole_list(List<UserRole> role_list) {
        this.role_list = role_list;
    }

    public Logger getLogger() {
        return logger;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_pwd() {
        return login_pwd;
    }

    public void setLogin_pwd(String login_pwd) {
        this.login_pwd = login_pwd;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
