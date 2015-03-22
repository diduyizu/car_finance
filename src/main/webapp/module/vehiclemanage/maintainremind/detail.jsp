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
<%--<form class="form-inline definewidth m20" action="${ctx}/vehicle/insurance/index" method="post">--%>
    <%--<button type="button" class="btn btn-success" id="addnew">新增车辆保险</button>--%>
<%--</form>--%>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>车牌号</th>
            <th>车架号</th>
            <th>发动机号</th>
            <th>保养时间</th>
            <th>保养内容</th>
            <th>保养金额</th>
            <th>当前里程</th>
            <th>下次保养里程</th>
        </tr>
    </thead>
    <c:forEach var="vehicle_maintain" items="${vehicle_maintain_list}" varStatus="status">
        <tr>
            <td>${vehicle_maintain.license_plate}</td>
            <td>${vehicle_maintain.carframe_no}</td>
            <td>${vehicle_maintain.engine_no}</td>
            <td>${vehicle_maintain.maintain_date}</td>
            <td>${vehicle_maintain.maintain_content}</td>
            <td>${vehicle_maintain.maintain_price}</td>
            <td>${vehicle_maintain.current_km}</td>
            <td>${vehicle_maintain.next_maintain_km}</td>
        </tr>
    </c:forEach>
</table>
<input type="hidden" id="carframe_no" value="${carframe_no}">
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
</script>