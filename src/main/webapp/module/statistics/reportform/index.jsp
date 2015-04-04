<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jstl_contexpath.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap-responsive.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/style.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/datepicker.css" />" />

    <%--<script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>--%>
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery-1.7.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datepicker.js" />"></script>

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
<form class="form-inline definewidth m20" action="${ctx}/statistics/reportform" method="post">
    <table>
        <tr>
            <td>
                <select name="report_type">
                    <option value="">－－全部－－</option>
                    <option value="day" <c:if test="${report_type == 'day'}">selected="selected"</c:if>>日报表</option>
                    <option value="week" <c:if test="${report_type == 'week'}">selected="selected"</c:if>>周报表</option>
                    <option value="month" <c:if test="${report_type == 'month'}">selected="selected"</c:if>>月报表</option>
                    <option value="quarter" <c:if test="${report_type == 'quarter'}">selected="selected"</c:if>>季报表</option>
                    <option value="year" <c:if test="${report_type == 'year'}">selected="selected"</c:if>>年报表</option>
                </select>
            </td>
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
            <%--<th>外借公司</th>--%>
            <th>超额费用</th>
            <th>实收金额</th>
            <th>总额</th>
        </tr>
    </thead>
    <c:forEach var="vehicle" items="${vehicle_list}" varStatus="status">
        <tr>
            <td>${vehicle.license_plate}</td>
            <td>${vehicle.model}</td>
            <%--<td>${vehicle.company}</td>--%>
            <td>${vehicle.over_price}</td>
            <td>${vehicle.actually_price}</td>
            <td>${vehicle.total_price}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $(function () {
        window.prettyPrint && prettyPrint();
        $('#begin').datepicker();
        $('#end').datepicker();
//        $('.form_datetime').datetimepicker({
//            format: 'yyyy-mm-dd',
//            language: 'zh-CN',
//            pickDate: true,
//            pickTime: true,
//            hourStep: 1,
//            minuteStep: 15,
//            secondStep: 30,
//            inputMask: true
//        });
    });
</script>