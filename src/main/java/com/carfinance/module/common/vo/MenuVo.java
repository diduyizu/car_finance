package com.carfinance.module.common.vo;


public class MenuVo {
	private long id;//菜单id
	private String text;//菜单名
	private String href;//菜单url

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
