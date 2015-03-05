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
<form class="form-inline definewidth m20" action="${ctx}/vehicle/insuranceremind/index" method="post">
    所在门店：
    <select id="original_org" name="original_org">
        <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
            <c:if test="${org.org_id == original_org}">
                <option value="${org.org_id}" selected="selected">${org.org_name}</option>
            </c:if>
            <c:if test="${org.org_id != original_org}">
                <option value="${org.org_id}">${org.org_name}</option>
            </c:if>
        </c:forEach>
    </select>
    当前所在城市：
    <select id="current_city" name="current_city">
        <option value="">全部</option>
        <c:forEach var="city" items="${sys_used_city_list}" varStatus="status">
            <c:if test="${city.city_id == current_city}">
                <option value="${city.city_id}" selected="selected">${city.city_name}</option>
            </c:if>
            <c:if test="${city.city_id != current_city}">
                <option value="${city.city_id}">${city.city_name}</option>
            </c:if>
        </c:forEach>
    </select>
    车牌号：
    <input type="text" name="license_plate" id="license_plate"class="abc input-default" placeholder="" value="${license_plate}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>车牌号</th>
            <th>年审时间</th>
            <th>所在城市</th>
            <th>所在门店</th>
        </tr>
    </thead>
    <c:forEach var="vehicle" items="${vehicle_peccancy_remind_list}" varStatus="status">
        <tr>
            <td><a href="${ctx}/vehicle/peccancy/detail?carframe_no=${vehicle.carframe_no}">${vehicle.license_plate}</a></td>
            <td>${vehicle.limited_at}</td>
            <td>${vehicle.current_city}</td>
            <td>${vehicle.original_org}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>