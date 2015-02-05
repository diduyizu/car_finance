<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jstl_contexpath.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>后台管理系统</title>
   	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap-responsive.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/style.css" />" />
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>
    <%--<script type="text/javascript" src="<c:url value="/resources/Js/jquery.sorted.js" />"></script>--%>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }

    </style>
    <script type="text/javascript">
		$(function() {
			//var kaptcha = $('#kaptcha');
			var loginName = $('#username');
		    var loginPwd = $('#password');
		    var loginForm = $('#loginForm');
			function checkInput() {
			    if (jQuery.trim(loginName.val()).length < 1) {
					alert("请输入登录名称!", "提示");
			        return false;
			    }
			    if (jQuery.trim(loginPwd.val()).length < 1) {
			        alert("请输入登录密码!", "提示");
			        return false;
			    }
			            //if (!/\d{5}/.test(kaptcha.val())) {
			            //    alert("请输入正确的验证码!", "提示");
			            //    return false;
			            //}
			    return true;         
			}
			
			$('#userlogin').click(function() {        
				if (checkInput()) {
					loginForm.attr("action", "${ctx }/login/index").submit();
				}
				return false;
			});
			        
			$('#kaptchaImg').click(function() {
				$('#kaptchaImg').attr('src', '${ctx}/kaptcha.jpg?update=' + Math.random());
				return false
			});
		})
	</script>
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" id="loginForm">
        <h2 class="form-signin-heading">登录系统</h2>
        <input type="text" name="username" id="username" class="input-block-level" placeholder="账号">
        <input type="password" name="password" id="password" class="input-block-level" placeholder="密码">
        <input type="text" name="verify" class="input-medium" placeholder="验证码">
       
        <p><button class="btn btn-large btn-primary" id="userlogin">登录</button></p>
    </form>
</div>
</body>
</html>