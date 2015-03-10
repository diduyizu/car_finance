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
<form class="form-inline definewidth m20" action="${ctx}/vehicleservice/reservation/remind" method="post">
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
    <%--<button type="button" class="btn btn-success" id="addnew">新增</button>--%>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>客户姓名</th>
            <th>手机号码</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>描述</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
    </thead>
    <c:forEach var="reservation" items="${reservation_list}" varStatus="status">
        <tr>
            <td>${reservation.customer_name}</td>
            <td>${reservation.customer_dn}</td>
            <td>${reservation.use_begin}</td>
            <td>${reservation.use_end}</td>
            <td>${reservation.remark}</td>
            <td>初始状态</td>
            <td>
                <c:if test="${reservation.create_by == user.user_id}">
                    <button type="button" class="btn btn-success cancel" value="${reservation.id}">取消</button>
                    <button type="button" class="btn btn-success tocontract" value="${reservation.id}">转合同</button>
                </c:if>
            </td>
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

    $('.cancel').click(function(){
        var reservation_id = $(this).val();
        $.ajax({
            url:"${ctx}/vehicleservice/reservation/docancel",
            type: "post",
            data:{reservation_id:reservation_id , status:2},
            success:function(data){
                if(data == 1){
                    alert("成功");
                    location.reload();
                } else {
                    alert("失败");
                    return false;
                }
            }
        })
    })

    $('.tocontract').click(function(){
        var reservation_id = $(this).val();
        window.location.href="${ctx}/vehicleservice/contrace/add?reservation_id="+reservation_id;
    });
</script>