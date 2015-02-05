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
    <form class="form-inline definewidth m20" action="${ctx}/people/role/index" method="post">
        角色名称：
        <input type="text" name="role_name" id="role_name"class="abc input-default" placeholder="" value="${role_name}">&nbsp;&nbsp;
        <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
    </form>
    <table class="table table-bordered table-hover definewidth m10" >
        <thead>
            <tr>
                <th>角色id</th>
                <th>角色名称</th>
                <th>状态</th>
            </tr>
        </thead>
        <c:forEach var="role" items="${roleList}" varStatus="status">
            <tr>
                <td>${role.role_id}</td>
                <td>${role.role_name}</td>
                <td>
                    ${role.status}
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>