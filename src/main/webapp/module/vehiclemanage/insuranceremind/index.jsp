<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jstl_contexpath.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap-responsive.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/style.css" />" />
    <%--<script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>--%>

    <script type="text/javascript" src="<c:url value="/resources/Js/jquery-1.7.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-typeahead.js" />"></script>

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

    <script type="text/javascript">
        $().ready(function() {
            var vehicle_licene_plate_json = ${vehicle_licene_plate_json};
            $('#license_plate').typeahead({
                source: vehicle_licene_plate_json
            });
        })
    </script>
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
    <%--品牌：--%>
    <%--<input type="text" name="brand" id="brand"class="abc input-default" placeholder="" value="${brand}">&nbsp;&nbsp;--%>
    <%--车架号：--%>
    <%--<input type="text" name="carframe_no" id="carframe_no"class="abc input-default" placeholder="" value="${carframe_no}">&nbsp;&nbsp;--%>
    <%--发动机号：--%>
    <%--<input type="text" name="engine_no" id="engine_no"class="abc input-default" placeholder="" value="${engine_no}">&nbsp;&nbsp;--%>
    车牌号：
    <input type="text" data-provide="typeahead" name="license_plate" id="license_plate"class="abc input-default" placeholder="" value="${license_plate}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <%--<th>车架号</th>--%>
            <%--<th>发动机号</th>--%>
            <th>车牌号</th>
            <th>保险公司</th>
            <th>交强险</th>
            <th>交强险到期日期</th>
            <th>车船税</th>
            <th>商业险</th>
            <th>商业险到期日期</th>
        </tr>
    </thead>
    <c:forEach var="vehicle" items="${vehicle_insurance_remind_list}" varStatus="status">
        <tr>
            <%--<td>${vehicle.carframe_no}</td>--%>
            <%--<td>${vehicle.engine_no}</td>--%>
            <td><a href="${ctx}/vehicle/insurance/detail?carframe_no=${vehicle.carframe_no}">${vehicle.license_plate}</a></td>
            <td>${vehicle.insurance_company}</td>
            <td>${vehicle.strong_insurance}</td>
            <td>${vehicle.strong_insurance_expire_at}</td>
            <td>${vehicle.vehicle_vessel_tax}</td>
            <td>${vehicle.business_insurance}</td>
            <td>${vehicle.business_insurance_expire_at}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>