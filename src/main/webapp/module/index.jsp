<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jstl_contexpath.jsp"%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>后台管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/dpl-min.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/bui-min.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/main-min.css" />" />
	</head>
	<body>
		<div class="header">
			<div class="dl-title">
				<!--<img src="/chinapost/Public/assets/img/top.png">-->
			</div>
			<div class="dl-log">
				欢迎您：
				<span class="dl-log-user">${user.user_name}</span>
                <a href="/resources/logout.jsp" title="退出系统" class="dl-log-quit">[退出]</a>
			</div>
		</div>
		<div class="content">
			<div class="dl-main-nav">
				<div class="dl-inform">
					<div class="dl-inform-title">
						<s class="dl-inform-icon dl-up"></s>
					</div>
				</div>
				<ul id="J_Nav" class="nav-list ks-clear">
                    <c:forEach var="menu" items="${user_top_menu_list}" varStatus="status">
                        <li class="nav-item dl-selected">
                            <div class="nav-item-inner ${menu.css}">
                                ${menu.menu_name}
                            </div>
                        </li>
                        <%--<li class="nav-item dl-selected">--%>
                            <%--<div class="nav-item-inner nav-order">--%>
                                <%--业务管理--%>
                            <%--</div>--%>
                        <%--</li>--%>
                        <%--<li class="nav-item dl-selected">--%>
                            <%--<div class="nav-item-inner nav-order">--%>
                                <%--啊啊啊管理--%>
                            <%--</div>--%>
                        <%--</li>--%>
                        <%--<li class="nav-item dl-selected">--%>
                            <%--<div class="nav-item-inner nav-order">--%>
                                <%--办不办管理--%>
                            <%--</div>--%>
                        <%--</li>--%>
                        <%--<li class="nav-item dl-selected">--%>
                            <%--<div class="nav-item-inner nav-order">--%>
                                <%--擦擦擦管理--%>
                            <%--</div>--%>
                        <%--</li>--%>
                    </c:forEach>
				</ul>
			</div>
			<ul id="J_NavContent" class="dl-tab-conten">
			</ul>
		</div>
        <script type="text/javascript" src="<c:url value="/resources/assets/js/jquery-1.8.1.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/assets/js/bui-min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/assets/js/common/main-min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/assets/js/config-min.js" />"></script>
		<script>
//            var config = [ {
//                id : '1',
//                menu : [ {
//                    text : '系统管理',
//                    items : [ {
//                        id : '12',
//                        text : '机构管理',
//                        href : 'Node/index.html'
//                    }, {
//                        id : '3',
//                        text : '角色管理',
//                        href : 'Role/index.html'
//                    }, {
//                        id : '4',
//                        text : '用户管理',
//                        href : 'User/index.html'
//                    }, {
//                        id : '6',
//                        text : '菜单管理',
//                        href : 'Menu/index.html'
//                    } ]
//                } ]
//            }, {
//                id : '7',
//                homePage : '9',
//                menu : [ {
//                    text : '业务管理',
//                    items : [ {
//                        id : '9',
//                        text : '查询业务',
//                        href : 'Node/index.html'
//                    } ]
//                } ]
//            } ];
            var config = ${user_menu_list};
            BUI.use('common/main', function() {
                new PageUtil.MainPage( {
                    modulesConfig : config
                });
            });
		</script>
	</body>
</html>