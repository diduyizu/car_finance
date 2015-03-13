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
    <input type="hidden" id="veh_contrace_vehs_id" name="veh_contrace_vehs_id" value="${veh_contrace_vehs_id}">
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>配驾员id</th>
            <th>配驾员工号</th>
            <th>配驾员姓名</th>
            <th>配驾员驾照</th>
            <th>操作</th>
        </tr>
    </thead>
    <c:forEach var="driver" items="${driver_list}" varStatus="status">
        <tr>
            <td>${driver.user_id}</td>
            <td>${driver.employee_id}</td>
            <td>${driver.user_name}</td>
            <td>${driver.driver_license_no}</td>
            <td>
                <button type="button" class="btn btn-success addpeijia" value="${driver.user_id}">选择</button>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $('.addpeijia').click(function(){
        var veh_contrace_vehs_id=$.trim($('#veh_contrace_vehs_id').val());
        var driver_user_id = $(this).val();
        $.ajax({
            url:"${ctx}/vehicleservice/contrace/dochoosedriver",
            type: "post",
            data:{veh_contrace_vehs_id:veh_contrace_vehs_id,driver_user_id:driver_user_id},
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