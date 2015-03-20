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
<form class="form-inline definewidth m20" action="${ctx}/vehicleservice/contrace/index" method="post">
    门店：
    <select id="original_org" name="original_org">
        <%--<c:forEach var="org" items="${user_all_org_list}" varStatus="status">--%>
            <%--<c:if test="${org.org_id == original_org}">--%>
                <%--<option value="${org.org_id}" selected="selected">${org.org_name}</option>--%>
            <%--</c:if>--%>
            <%--<c:if test="${org.org_id != original_org}">--%>
                <%--<option value="${org.org_id}">${org.org_name}</option>--%>
            <%--</c:if>--%>
        <%--</c:forEach>--%>

        <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
            <c:if test="${org.org_id == original_org}">
                <c:if test="${org.org_type > 12}">
                    <option value="${org.org_id}" selected="selected">${org.org_city_name} ${org.org_name}</option>
                </c:if>
                <c:if test="${org.org_type < 13}">
                    <option value="${org.org_id}" selected="selected">${org.org_name}</option>
                </c:if>
            </c:if>
            <c:if test="${org.org_id != original_org}">
                <c:if test="${org.org_type > 12}">
                    <option value="${org.org_id}">${org.org_city_name} ${org.org_name}</option>
                </c:if>
                <c:if test="${org.org_type < 13}">
                    <option value="${org.org_id}">${org.org_name}</option>
                </c:if>
            </c:if>
        </c:forEach>
    </select>&nbsp;&nbsp;
    <%--姓名：--%>
    <%--<input type="text" name="customer_name" id="customer_name"class="abc input-default" placeholder="" value="${customer_name}">&nbsp;&nbsp;--%>
    <%--手机号码：--%>
    <%--<input type="text" name="dn" id="dn"class="abc input-default" placeholder="" value="${dn}">&nbsp;&nbsp;--%>
    <%--证件号码：--%>
    <%--<input type="text" name="certificate_no" id="certificate_no"class="abc input-default" placeholder="" value="${certificate_no}">&nbsp;&nbsp;--%>
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>合同编号</th>
            <th>客户姓名</th>
            <th>客户类型</th>
            <th>手机号码</th>
            <th>系统应得租金</th>
            <th>约定应得租金</th>
            <th>实际所得租金</th>
            <th>欠款金额</th>
            <th>欠款天数</th>
            <th>滞纳金</th>
            <th>操作</th>
        </tr>
    </thead>
    <c:forEach var="contrace" items="${contrace_list}" varStatus="status">
        <tr>
            <%--<td><a href="${ctx}/vehicleservice/contrace/vech/list?contrace_id=${contrace.id}">${contrace.contrace_no}</a></td>--%>
            <td>${contrace.contrace_no}</td>
            <td>${contrace.customer_name}</td>
            <td>${contrace.customer_type}</td>
            <td>${contrace.customer_dn}</td>
            <td>${contrace.system_total_price}</td>
            <td>${contrace.arrange_price}</td>
            <td>${contrace.actual_price}</td>
            <td>${contrace.arrange_price-contrace.actual_price}</td>
            <td>${contrace.arrearage_days}</td>
            <td>${contrace.late_fee}</td>
            <td>
                <button type="button" class="btn btn-success returnmoney" value="${contrace.id}">还款</button>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $('.returnmoney').click(function(){
        var contrace_id = $(this).val();
        window.location.href="${ctx}/vehicleservice/contrace/returnmoney?contrace_id="+contrace_id;
    });
</script>