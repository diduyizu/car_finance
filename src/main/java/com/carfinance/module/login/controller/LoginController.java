package com.carfinance.module.login.controller;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carfinance.module.common.domain.Menu;
import com.carfinance.module.common.domain.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.log.service.LogService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.login.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private LogService logService;
	@Autowired
	private Properties appProps;
	

	@RequestMapping(value = "" , method = RequestMethod.GET)
	public String login(Model model , HttpServletRequest request , HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute("user");
		if(user != null) {
			return "redirect:/login/returnindex";
		}
		
		return "/module/login";
	}
	
	@RequestMapping(value = "/index" , method = {RequestMethod.POST,RequestMethod.GET})
	public String index(Model model , HttpServletRequest request , HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute("user");
		if(user != null) {
			return "redirect:/login/returnindex";
		}
		
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
//        String kaptcha = request.getParameter("kaptcha");
//        String kaptchaStr = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
        if(name == null || "".equals(name.trim()) || pwd == null || "".equals(pwd.trim())) {
        	return "redirect:/login";
        }
//        if(kaptcha == null || "".equals(kaptcha.trim())) {
//        	return "redirect:/module/login";
//        } else {
//        	if(logger.isDebugEnabled())logger.debug("VALID>>用户输入验证码=" + kaptcha + ",用户当前的SESSIONID=" + request.getSession().getId() + ",用户SESSION中保存的验证码=" + kaptchaStr);
//        	if (kaptchaStr != null) {
//                if (!kaptchaStr.equals(kaptcha.trim())) {
//                    return "redirect:/module/login";
//                }
//            }
//        }
        
		user = loginService.index(name , pwd);
		if(user != null) {
			model.addAttribute("user" , user);
			request.getSession().setAttribute("user", user);
			return "redirect:/login/returnindex";
		}
		return "redirect:/login";
	}
	
	/**
	 * 用户在线，但用户点击首页、操作不允许的菜单等
	 * 跳转至此接口
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/returnindex" , method = RequestMethod.GET)
	public String returnIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute("user");
		if(user != null) {
            //获取用户角色列表
            List<UserRole> user_role_list = this.commonService.getUserRoleList(user.getUser_id());
            //获取该用户能够操作的菜单，页面上部tab切换
//            List<Menu> user_top_menu_list = this.commonService.getUserTopMenuList(user_role_list);//这个是顶层菜单
            //获取用户角色对应的菜单列表，给js使用，返回的是json格式
            Map<String , Object> map = commonService.getUserMenuList(user_role_list);
            //获取该用户能够操作的菜单，页面上部tab切换
            List<Menu> user_top_menu_list = (List<Menu>)map.get("top_menu_ist");
            String user_menu_list = (String)map.get("menu_list");


                    model.addAttribute("user" , user);
            model.addAttribute("user_top_menu_list" , user_top_menu_list);
            model.addAttribute("user_menu_list" , user_menu_list);
            return "/module/index";
		}
		return "redirect:/login";
	}
}