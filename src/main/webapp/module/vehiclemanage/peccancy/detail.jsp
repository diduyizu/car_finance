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
            <th>违章时间</th>
            <th>违章地点</th>
            <th>违章原因</th>
            <th>罚款金额</th>
            <th>扣分数</th>
            <th>处理状态</th>
            <th>处理仲裁</th>
            <th>操作</th>
        </tr>
    </thead>
    <c:forEach var="vehiclePeccancy" items="${vehiclePeccancy_list}" varStatus="status">
        <tr>
            <td>${vehiclePeccancy.license_plate}</td>
            <td>${vehiclePeccancy.carframe_no}</td>
            <td>${vehiclePeccancy.engine_no}</td>
            <td>${vehiclePeccancy.peccancy_at}</td>
            <td>${vehiclePeccancy.peccancy_place}</td>
            <td>${vehiclePeccancy.peccancy_reason}</td>
            <td>${vehiclePeccancy.peccancy_price}</td>
            <td>${vehiclePeccancy.score}</td>
            <td>
                <c:if test="${vehiclePeccancy.status == 0}">未处理</c:if>
                <c:if test="${vehiclePeccancy.status != 0}">已处理</c:if>
            </td>
            <td>${vehiclePeccancy.arbitration}</td>
            <td>
                <c:if test="${vehiclePeccancy.status == 0}">
                    <a href="javascript:;" id="execute" value="${vehiclePeccancy.id}">处理</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<input type="hidden" id="carframe_no" value="${carframe_no}">
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $('#execute').click(function(){
        var id = $("#execute").attr("value");
        alert(id);
    });
</script>