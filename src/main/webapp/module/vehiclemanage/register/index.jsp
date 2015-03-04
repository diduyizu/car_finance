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
<form class="form-inline definewidth m20" action="${ctx}/vehicle/register/index" method="post">
    门店：
    <select id="original_org" name="original_org">
        <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
            <c:if test="${org.org_id == original_org}">
                <option value="${org.org_id}" selected="selected">${org.org_name}</option>
            </c:if>
            <c:if test="${org.org_id != original_org}">
                <option value="${org.org_id}">${org.org_name}</option>
            </c:if>
        </c:forEach>
    </select>&nbsp;&nbsp;
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
    </select>&nbsp;&nbsp;
    车辆租赁状态：
    <select id="lease_status" name="lease_status">
        <option value="">全部</option>
        <option value="在库" <c:if test="${lease_status == '在库'}">selected="selected"</c:if>>在库</option>
        <option value="零租" <c:if test="${lease_status == '零租'}">selected="selected"</c:if>>零租</option>
        <option value="产权租" <c:if test="${lease_status == '产权租'}">selected="selected"</c:if>>产权租</option>
        <option value="售出" <c:if test="${lease_status == '售出'}">selected="selected"</c:if>>售出</option>
    </select>&nbsp;&nbsp;
    品牌：
    <input type="text" name="brand" id="brand"class="abc input-default" placeholder="" value="${brand}">&nbsp;&nbsp;
    车辆型号：
    <input type="text" name="model" id="model"class="abc input-default" placeholder="" value="${model}">&nbsp;&nbsp;
    车牌号：
    <input type="text" name="license_plate" id="license_plate"class="abc input-default" placeholder="" value="${license_plate}">&nbsp;&nbsp;
    GPS状态：
    <select id="gps" name="gps">
        <option value="">全部</option>
        <option value="正常" <c:if test="${gps == '正常'}">selected="selected"</c:if>>正常</option>
        <option value="异常" <c:if test="${gps == '异常'}">selected="selected"</c:if>>异常</option>
        <option value="未安装" <c:if test="${gps == '未安装'}">selected="selected"</c:if>>未安装</option>
    </select>&nbsp;&nbsp;
    公里数：
    <input type="text" name="km_begin" id="km_begin"class="abc input-default" placeholder="" value="${km_begin}">到
    <input type="text" name="km_end" id="km_end"class="abc input-default" placeholder="" value="${km_end}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
    <button type="button" class="btn btn-success" id="addnew">新增车辆</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>车牌</th>
            <th>当前所在地市</th>
            <th>品牌</th>
            <th>车型</th>
            <th>保养剩余公里数</th>
            <th>公里数</th>
            <th>GPS状态</th>
            <th>车辆状态</th>
        </tr>
    </thead>
    <c:forEach var="vehicle" items="${vehicle_list}" varStatus="status">
        <tr>
            <td><a href="javascript:;">${vehicle.license_plate}</a></td>
            <td>${vehicle.current_city_name}</td>
            <td>${vehicle.brand}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.maintian_on_km}</td>
            <td>${vehicle.km}</td>
            <td>${vehicle.gps}</td>
            <td>${vehicle.lease_status}</td>
        </tr>
    </c:forEach>
    </tr>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $('#addnew').click(function(){
        window.location.href="${ctx}/vehicle/register/add";
    });
</script>