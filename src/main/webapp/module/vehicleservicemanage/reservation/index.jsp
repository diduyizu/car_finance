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
<form class="form-inline definewidth m20" action="${ctx}/vehicleservice/reservation/index" method="post">
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
    姓名：
    <input type="text" name="customer_name" id="customer_name"class="abc input-default" placeholder="" value="${customer_name}">&nbsp;&nbsp;
    手机号码：
    <input type="text" name="dn" id="dn"class="abc input-default" placeholder="" value="${dn}">&nbsp;&nbsp;
    证件号码：
    <input type="text" name="certificate_no" id="certificate_no"class="abc input-default" placeholder="" value="${certificate_no}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
    <button type="button" class="btn btn-success" id="addnew">新增</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>客户姓名</th>
            <th>手机号码</th>
            <th>用车开始时间</th>
            <th>用车结束时间</th>
            <th>车型</th>
            <th>单价</th>
            <th>数量</th>
            <th>是否要配驾</th>
            <th>是否自理油及过路停车费</th>
            <th>状态</th>
        </tr>
    </thead>
    <c:forEach var="reservation" items="${reservation_list}" varStatus="status">
        <tr>
            <td>${reservation.customer_name}</td>
            <td>${reservation.customer_dn}</td>
            <td>${reservation.use_begin}</td>
            <td>${reservation.use_end}</td>
            <td>${reservation.model}</td>
            <td>${reservation.unit_price}</td>
            <td>${reservation.quantity}</td>
            <td>
                <c:if test="${reservation.with_driver == 1}">是</c:if>
                <c:if test="${reservation.with_driver == 0}">否</c:if>
            </td>
            <td>
                <c:if test="${reservation.expenses_self == 1}">是</c:if>
                <c:if test="${reservation.expenses_self == 0}">否</c:if>
            </td>
            <td>${reservation.status}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $('#addnew').click(function(){
        window.location.href="${ctx}/vehicleservice/reservation/add";
    });
</script>