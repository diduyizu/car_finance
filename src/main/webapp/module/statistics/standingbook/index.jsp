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
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>合同编号</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>应得租金</th>
            <th>约定租金</th>
            <th>实得租金</th>
        </tr>
    </thead>
    <c:forEach var="contrace" items="${contrace_list}" varStatus="status">
        <tr>
            <td><a href="${ctx}/vehicleservice/contrace/detail?contrace_id=${contrace.id}">${contrace.contrace_no}</a></td>
            <td>${contrace.use_begin}</td>
            <td>${contrace.use_end}</td>
            <td>${contrace.system_total_price}</td>
            <td>${contrace.arrange_price}</td>
            <td>${contrace.actual_price}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>