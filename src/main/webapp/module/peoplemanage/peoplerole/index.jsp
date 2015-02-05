<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jstl_contexpath.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap-responsive.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/style.css" />" />
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>

    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
<form class="form-inline definewidth m20">
    <select onchange="">
        <c:forEach var="org" items="${user_org_list}" varStatus="status">
            <c:if test="${org.org_id == choose_org_id}">
                <option value="${org.org_id}" selected="selected">${org.org_name}</option>
            </c:if>
            <c:if test="${org.org_id != choose_org_id}">
                <option value="${org.org_id}">${org.org_name}</option>
            </c:if>
        </c:forEach>
    </select>
    用户名称：
    <input type="text" name="username" id="username"class="abc input-default" placeholder="" value="${user_name}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>真实姓名</th>
            <th>用户所在组织</th>
            <th>用户角色</th>
            <th>操作</th>
        </tr>
    </thead>
    <c:forEach var="user" items="${org_user_role_list}" varStatus="status">
        <tr>
            <td>${user.user_name}</td>
            <td>${user.org_name}</td>
            <td>${user.role_name}</td>
            <td><a href="${ctx}/people/people/edit?edited_user_id=${user.user_id}&org_id=${user.org_id}&role_id=${role_id}">删除</a></td>
        </tr>
    </c:forEach>
    </tr>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $(function () {
		$('#addnew').click(function(){
            window.location.href="${ctx}/people/peoplerole/add";
		});
    });

	function del(id) {
		if(confirm("确定要删除吗？")) {
			var url = "index.html";
			window.location.href=url;
		}
	}
</script>