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
    车型：
    <input type="text" name="model" id="model"class="abc input-default" placeholder="" value="${vehicle_model}">&nbsp;&nbsp;
    车牌：
    <input type="text" name="license_plate" id="license_plate"class="abc input-default" placeholder="" value="${license_plate}">&nbsp;&nbsp;
    <input type="hidden" id="contrace_id" name="contrace_id" value="${contrace_id}">
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>合同编号</th>
            <th>车型</th>
            <th>车牌</th>
            <th>车价</th>
            <th>是否外借</th>
            <th>公司名称</th>
            <th>配驾人员id</th>
            <th>配驾人员姓名</th>
            <th>操作</th>
        </tr>
    </thead>
    <c:forEach var="vehicle" items="${vehicle_list}" varStatus="status">
        <tr>
            <td>${vehicle.contrace_id}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.license_plate}</td>
            <td>${vehicle.vehicle_price}</td>
            <td>
                <c:if test="${vehicle.isother == 0}">否</c:if>
                <c:if test="${vehicle.isother == 1}">是</c:if>
            </td>
            <td>${vehicle.company}</td>
            <td>${vehicle.driving_user_id}</td>
            <td>${vehicle.driving_user_name}</td>
            <td>
                <c:if test="${vehicleContraceInfo.status == 0 || vehicleContraceInfo.status == -1}">
                    <c:if test="${vehicle.driving_user_id == 0}">
                        <button type="button" class="btn btn-success addpeijia" value="${vehicle.id}">新增配驾</button>
                    </c:if>
                    <button type="button" class="btn btn-danger cancelvehicle" value="${vehicle.id}">取消车辆</button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $('.addpeijia').click(function(){
        var veh_contrace_vehs_id = $(this).val();
        window.location.href="${ctx}/vehicleservice/contrace/vechdriver/list?veh_contrace_vehs_id="+veh_contrace_vehs_id;
    });

    //取消合同车辆
    $('.cancelvehicle').click(function(){
        var veh_contrace_vehs_id = $(this).val();
        $.ajax({
            url:"${ctx}/vehicleservice/contrace/cancelchoosevehicle",
            type: "post",
            data:{veh_contrace_vehs_id:veh_contrace_vehs_id},
            dataType:"json",
            success:function(data){
                if(data > 0){
                    alert("成功");
                    location.reload();
                } else if(data == -1) {
                    alert("您还没有绑定车辆，请先增加车辆，再提交审核");
                    return false;
                } else {
                    alert("失败");
                    return false;
                }
            }
        })
    });

    $('.over').click(function(){
        var id = $(this).val();
        $.ajax({
            url:"${ctx}/vehicleservice/contrace/dofinish",
            type: "post",
            data:{id:id,status:6},
            dataType:"json",
            success:function(data){
                if(data > 0){
                    alert("成功");
                    location.reload();
                } else {
                    alert("失败");
                    return false;
                }
            }
        })
    });
</script>