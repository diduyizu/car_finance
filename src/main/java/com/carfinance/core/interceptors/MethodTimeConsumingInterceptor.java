package com.carfinance.core.interceptors;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.carfinance.module.common.service.ManageMemcacdedClient;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MethodTimeConsumingInterceptor extends HandlerInterceptorAdapter {
	final Logger logger = LoggerFactory.getLogger(MethodTimeConsumingInterceptor.class);
	private long preTime = 0l;

	@Autowired
    private Properties appProps;
	@Autowired
	private ManageMemcacdedClient memcachedClient;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		logger.debug("执行资源调用耗时拦截器的preHandle方法,该方法在控制层的方法被调用前调用!");
//		User user = (User) request.getSession().getAttribute("user");//登陆用户的User对象
//		logger.debug("clsssSpace-request.getRequestURI()=" + request.getRequestURI());
//		logger.debug("clsssSpace-request.getRequestURL()=" + request.getRequestURL().toString());
//		logger.debug("clsssSpace-request.getContextPath()=" + request.getContextPath());
//		
//		
//		//************有可能是第三方应用跳转至教育云，判断传入token是否正确*****************
//		if(user == null) {
//			logger.info("用户的Session对象中不包含User对象,判断是否是第三方应用跳转");
//			String token = request.getParameter("token");
//			if(token != null && !"".equals(token.trim())) {
//				user = (User)memcachedClient.get(CacheKey.getEduCloudUserThridParty(token));
//				if(user != null) {//缓存中存在token对应的user对象，给用户做一次登录
//					logger.info("缓存中通过token能够找到user对象，将user放入session中");
//					request.getSession().setAttribute("user" , user);
//					return true;
//				}
//			}
//		}
//		//************有可能是第三方应用跳转至教育云，判断传入token是否正确*****************
		
		
		String str = request.getRequestURI();
		if (str != null && str.equals(request.getContextPath() + "/app/module/login")) {
			logger.info("用户请求路径匹配登录请求路径,系统判定为登录请求!");
			return true;
		} else if (str != null && str.equals(request.getContextPath() + "/app/module/login/index")) {
			logger.info("用户请求路径匹配用户登录,系统判定为正常!");
			return true;
		} else if (str != null && str.equals(request.getContextPath() + "/app/module/login/getcookie")) {
			logger.info("用户请求路径匹配cms侧获取cookies,系统判定为正常!");
			return true;
		} else if (str != null && str.equals(request.getContextPath() + "/app/module/login/getuserbycookie")) {
			logger.info("用户请求路径匹配cms侧根据cookies获取用户信息,系统判定为正常!");
			return true;
		} else if (str != null && str.equals(request.getContextPath() + "/app/module/login/logout")) {
			logger.info("用户请求路径匹配用户退出,系统判定为正常!");
			return true;
		} else if (str != null && str.equals(request.getContextPath() + "/app/module/login/log")) {
			logger.info("用户请求路径匹配用户请求登录页面,系统判定为正常!");
			return true;
		} else if (str != null && str.contains(request.getContextPath() + "/app/module/register")) {
			logger.info("用户请求路径匹配注册流程,系统判定为正常!");
			logger.info(request.getContextPath());
			return true;
		} else if (str != null && str.contains(request.getContextPath() + "/app/module/news")) {
			logger.info("用户请求路径匹配新闻流程,系统判定为正常!");
			logger.info(request.getContextPath());
			return true;
		} else if (str != null && str.contains(request.getContextPath() + "/app/module/activity")) {
			logger.info("用户请求路径匹配活动流程,系统判定为正常!");
			logger.info(request.getContextPath());
			return true;
		} else if (str != null && str.contains(request.getContextPath() + "/app/module/resource")) {
			logger.info("用户请求路径匹配资源流程,系统判定为正常!");
			logger.info(request.getContextPath());
			return true;
		} else if (str != null && str.contains(request.getContextPath() + "/app/module/readafterme")) {
			logger.info("用户请求路径匹配跟我读流程,系统判定为正常!");
			logger.info(request.getContextPath());
			return true;
		} else if (str != null && str.contains(request.getContextPath() + "/app/module/terminal")) {
			logger.info("用户请求路径匹配终端接口流程,系统判定为正常!");
			logger.info(request.getContextPath());
			return true;
		} else if (str != null && str.contains(request.getContextPath() + "/app/module/study")) {
			logger.info("用户请求路径匹配教育资源登录流程,系统判定为正常!");
			logger.info(request.getContextPath());
			return true;
		} else if (str != null && str.contains(request.getContextPath() + "/app/module/client")) {
			logger.info("用户请求路径匹配客户端下载流程,系统判定为正常!");
			logger.info(request.getContextPath());
			return true;
		} else if (str != null && str.contains(request.getContextPath() + "/app/module/blackboard")) {
			logger.info("用户请求路径匹配超级黑板流程,系统判定为正常!");
			logger.info(request.getContextPath());
			return true;
		} else if (str != null && str.contains(request.getContextPath() + "/app/module/oauth")) {
			logger.info("用户请求路径匹配第三方应用鉴权流程,系统判定为正常!");
			logger.info(request.getContextPath());
			return true;
		}
		
//		if (user == null) {
//			logger.info("用户的Session对象中不包含User对象,系统判定Session失效,跳转到网站首页");
//			
//			Cookie[] allCookie=request.getCookies();
//	    	for(int i=0;i<allCookie.length;i++){
//		        if(allCookie[i].getName().equals("uccookie")){
//		    	    //删除cookie
//		    	    allCookie[i].setMaxAge(0);
//		    	    allCookie[i].setPath("/");
//		    	    response.addCookie(allCookie[i]);
//			    }
//		    }
//			
////			response.sendRedirect(request.getContextPath() + "/");
//			response.sendRedirect((String)applicationProps.get("face.url"));
//			return false;
//		} else {
//		}
		preTime = System.currentTimeMillis();
		request.setAttribute("preTime", preTime);
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.debug("执行资源调用耗时拦截器的postHandle方法,该方法在控制层的方法被调用后调用!");
		long postTime = System.currentTimeMillis();
		long temp = postTime - preTime;
		logger.debug("耗时{}毫秒", temp);
		request.setAttribute("postTime", postTime);
		super.postHandle(request, response, handler, modelAndView);
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		/*
		 * 由于这个方法是在渲染视图后运行，�?��在该方法中设置request属�?没有效果
		 */
		logger.debug("执行资源调用耗时拦截器的afterCompletion方法,该方法在视图被渲染后被调用");
		long completionTime = System.currentTimeMillis();
		long temp = completionTime - preTime;
		logger.debug("耗时{}毫秒", temp);
		super.afterCompletion(request, response, handler, ex);
	}
}
