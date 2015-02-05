<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String ctx=request.getContextPath()+"/login";
    //Cookie[] allCookie=request.getCookies();
    //if(allCookie==null||allCookie.length==0){
    //}else{
	//    for(int i=0;i<allCookie.length;i++){
	//        if(allCookie[i].getName().equals("cpal")){
	//    	    //删除cookie
	//    	    allCookie[i].setMaxAge(0);
	//    	    allCookie[i].setPath(ctx);
	//    	    response.addCookie(allCookie[i]);
	//	    }
	//    }
    //}
    //String flag = request.getParameter("remotelogin");
    request.getSession().invalidate();
    System.out.print(ctx);
    response.sendRedirect(ctx);
%>
