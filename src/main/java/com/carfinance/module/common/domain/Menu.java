package com.carfinance.module.common.domain;

import java.io.Serializable;

public class Menu implements Serializable{
	private static final long serialVersionUID = -1450900287435164266L;
	
	private long menu_id;
	private String menu_name;
	private String menu_url;
    private long pid;//父菜单id
    private long level;//菜单级别
	private String menu_desc;
    private String type;//类型
    private String css;//
	private String status;

    private int role_menu_status = 0;//角色对应于该菜单，是否有权限，0-无权限；1-有权限

    public int getRole_menu_status() {
        return role_menu_status;
    }

    public void setRole_menu_status(int role_menu_status) {
        this.role_menu_status = role_menu_status;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(long menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public String getMenu_desc() {
        return menu_desc;
    }

    public void setMenu_desc(String menu_desc) {
        this.menu_desc = menu_desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
