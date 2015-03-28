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
<form class="form-inline definewidth m20" action="${ctx}/vehicleservice/contrace/addvech" method="post">
    <table>
        <tr>
            <td>
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
            </td>
            <td>
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
            </td>
        </tr>
        <tr>
            <td>
                品牌：
                <input type="text" name="brand" id="brand"class="abc input-default" placeholder="" value="${brand}">&nbsp;&nbsp;
            </td>
            <td>
                车辆型号：
                <input type="text" name="model" id="model"class="abc input-default" placeholder="" value="${vehicle_model}">&nbsp;&nbsp;
            </td>
            <td>
                车牌号：
                <input type="text" name="license_plate" id="license_plate"class="abc input-default" placeholder="" value="${license_plate}">&nbsp;&nbsp;
            </td>
            <td>
                <input type="hidden" id="contrace_id" name="contrace_id" value="${contrace_id}">
                <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
                <button type="button" class="btn btn-primary" id="addforeignvehicle">外援车辆</button>&nbsp;&nbsp;
            </td>
        </tr>
    </table>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th colspan="7">已选车辆</th>
    </tr>
    <tr>
        <th>车牌</th>
        <th>车型</th>
        <th>当前里程</th>
        <th>是否有ETC</th>
        <th>ETC余额</th>
        <th>当前油量比（百分比％）</th>
    </tr>
    </thead>
    <c:forEach var="vehicle" items="${contrace_vehicle_list}" varStatus="status">
        <tr>
            <td>${vehicle.license_plate}</td>
            <td>${vehicle.model}</td>
            <td>
                <c:if test="${vehicle.isother == 0}">${vehicle.km}</c:if>
                <c:if test="${vehicle.isother == 1}">${vehicle.other_vehicle_km}</c:if>
            </td>
            <td>${vehicle.etc}</td>
            <td>${vehicle.etc_money}</td>
            <td>${vehicle.oil_percent}</td>
        </tr>
    </c:forEach>
    </tr>
</table>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>车牌</th>
            <th>品牌</th>
            <th>车型</th>
            <th>当前所在地市</th>
            <th>车辆状态</th>
            <th>保养剩余公里数</th>
            <th>公里数</th>
            <th>是否有ETC</th>
            <th>ETC余额</th>
            <th>当前油量比（百分比％）</th>
            <th>操作</th>
        </tr>
    </thead>
    <c:forEach var="vehicle" items="${vehicle_list}" varStatus="status">
        <tr>
            <td>${vehicle.license_plate}</td>
            <td>${vehicle.brand}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.current_city_name}</td>
            <td>${vehicle.lease_status}</td>
            <td>${vehicle.maintian_on_km}</td>
            <td>${vehicle.km}</td>
            <td>${vehicle.etc}</td>
            <td>${vehicle.etc_money}</td>
            <td>${vehicle.oil_percent}</td>
            <td><button type="button" class="btn btn-success choosevehi" value="${vehicle.id}">选择</button></td>
        </tr>
    </c:forEach>
    </tr>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>

    $('#addforeignvehicle').click(function(){
        var contrace_id = $.trim($('#contrace_id').val());
        window.location.href="${ctx}/vehicleservice/contrace/addforeignvehicle?contrace_id="+contrace_id;
    });

    $('.choosevehi').click(function(){
        var contrace_id = $.trim($('#contrace_id').val());
        var vehicle_id = $(this).val();
        $.ajax({
            url:"${ctx}/vehicleservice/contrace/dochoosevech",
            type: "post",
            data:{contrace_id:contrace_id,vehicle_id:vehicle_id},
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