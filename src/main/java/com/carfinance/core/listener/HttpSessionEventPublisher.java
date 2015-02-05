package com.carfinance.core.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HttpSessionEventPublisher implements HttpSessionListener {
	private static final String LOGGER_NAME = HttpSessionEventPublisher.class.getName();

	ApplicationContext getContext(ServletContext servletContext) {
		return WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}

	ApplicationContext _getContext(ServletContext servletContext, String servletName) {
		return WebApplicationContextUtils.getWebApplicationContext(servletContext, servletName);
	}

	public void sessionCreated(HttpSessionEvent event) {
//		Log log = LogFactory.getLog(LOGGER_NAME);
//		log.info("会话创建操作�?��......");
//		log.info("会话创建操作结束");
	}

	public void sessionDestroyed(HttpSessionEvent event) {
//		Log log = LogFactory.getLog(LOGGER_NAME);
//		log.info("会话�?��操作�?��......");
//		log.info("会话�?��操作结束!");
	}
}
