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
<form class="form-inline definewidth m20" action="${ctx}/statistics/vehicleincom" method="post">
    <table>
        <tr>
            <td>
                车辆型号：
                <input type="text" data-provide="typeahead" name="model" id="model" class="abc input-default" placeholder="" value="${vehicle_model}">&nbsp;&nbsp;
            </td>
            <td>
                车牌号：
                <input type="text" data-provide="typeahead" name="license_plate" id="license_plate" class="abc input-default" placeholder="" value="${license_plate}">&nbsp;&nbsp;
            </td>
            <td>
                <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
            </td>
        </tr>
    </table>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>车牌</th>
            <th>车型</th>
            <th>外借公司</th>
            <th>还车时间</th>
            <th>还车里程</th>
            <th>超额费用</th>
            <th>实收总金额</th>
        </tr>
    </thead>
    <c:forEach var="vehicle" items="${vehicle_list}" varStatus="status">
        <tr>
            <td>${vehicle.license_plate}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.company}</td>
            <td>${vehicle.return_time}</td>
            <td>${vehicle.return_km}</td>
            <td>${vehicle.over_price}</td>
            <td>${vehicle.actually_price}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>