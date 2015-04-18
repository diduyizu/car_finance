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
<form class="form-inline definewidth m20" action="${ctx}/vehicle/leasestatus/index" method="post">
    门店：
    <select id="original_org" name="original_org">
        <c:forEach var="user_role" items="${user_role_list}" varStatus="status">
            <c:if test="${user_role.org_id == original_org}">
                <option value="${user_role.org_id}" selected="selected">${user_role.org_name}</option>
            </c:if>
            <c:if test="${user_role.org_id != original_org}">
                <option value="${user_role.org_id}">${user_role.org_name}</option>
            </c:if>
        </c:forEach>
    </select>
    车辆租赁状态：
    <select id="leasestatus" name="leasestatus">
        <option value="">全部</option>
        <option value="在库" <c:if test="${lease_status == '在库'}">selected="selected"</c:if>>在库</option>
        <option value="零租" <c:if test="${lease_status == '零租'}">selected="selected"</c:if>>零租</option>
        <option value="产权租" <c:if test="${lease_status == '产权租'}">selected="selected"</c:if>>产权租</option>
        <option value="售出" <c:if test="${lease_status == '售出'}">selected="selected"</c:if>>售出</option>
        <option value="出库中" <c:if test="${vehicle_info.lease_status == '出库中'}">selected="selected"</c:if>>出库中</option>
    </select>
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>车架号</th>
            <th>发动机号</th>
            <th>车牌号</th>
            <th>车辆状态</th>
        </tr>
    </thead>
    <c:forEach var="vehicle" items="${vehicle_list}" varStatus="status">
        <tr>
            <td>${vehicle.carframe_no}</td>
            <td>${vehicle.engine_no}</td>
            <td>${vehicle.license_plate}</td>
            <td>${vehicle.lease_status}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
</script>