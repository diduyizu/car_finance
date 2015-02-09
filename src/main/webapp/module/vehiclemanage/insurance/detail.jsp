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
<form class="form-inline definewidth m20" action="${ctx}/vehicle/insurance/index" method="post">
    <button type="button" class="btn btn-success" id="addnew">新增车辆保险</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>车架号</th>
            <th>发动机号</th>
            <th>车牌号</th>
            <th>保险公司</th>
            <th>交强险</th>
            <th>车船税</th>
            <th>交强险到期日期</th>
            <th>商业险</th>
            <th>商业险到期日期</th>
            <th>备注</th>
            <th>录入人</th>
            <th>录入时间</th>
        </tr>
    </thead>
    <c:forEach var="vehicleInsurance" items="${vehicleInsurance_list}" varStatus="status">
        <tr>
            <td>${vehicleInsurance.carframe_no}</td>
            <td>${vehicleInsurance.engine_no}</td>
            <td>${vehicleInsurance.license_plate}</td>
            <td>${vehicleInsurance.insurance_company}</td>
            <td>${vehicleInsurance.strong_insurance}</td>
            <td>${vehicleInsurance.vehicle_vessel_tax}</td>
            <td>${vehicleInsurance.strong_insurance_expire_at}</td>
            <td>${vehicleInsurance.business_insurance}</td>
            <td>${vehicleInsurance.business_insurance_expire_at}</td>
            <td>${vehicleInsurance.remark}</td>
            <td>${vehicleInsurance.create_by}</td>
            <td>${vehicleInsurance.create_at}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $('#addnew').click(function(){
        window.location.href="${ctx}/vehicle/insurance/add";
    });
</script>