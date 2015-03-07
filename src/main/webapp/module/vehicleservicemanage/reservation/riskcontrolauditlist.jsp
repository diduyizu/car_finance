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
<form class="form-inline definewidth m20" action="${ctx}/vehicleservice/riskcontrol/audit" method="post">
    门店：
    <select id="original_org" name="original_org">
        <c:forEach var="org" items="${user_role_org_list}" varStatus="status">
            <c:if test="${org.org_id == original_org}">
                <option value="${org.org_id}" selected="selected">${org.org_name}</option>
            </c:if>
            <c:if test="${org.org_id != original_org}">
                <option value="${org.org_id}">${org.org_name}</option>
            </c:if>
        </c:forEach>
    </select>&nbsp;&nbsp;
    状态：
    <select id="status" name="status">
        <option value="">全部</option>
        <option value="待审核" <c:if test="${status == '待审核'}">selected="selected"</c:if>>待审核</option>
        <option value="审核通过" <c:if test="${status == '审核通过'}">selected="selected"</c:if>>审核通过</option>
        <option value="审核不通过" <c:if test="${status == '审核不通过'}">selected="selected"</c:if>>审核不通过</option>
    </select>
    姓名：
    <input type="text" name="customer_name" id="customer_name"class="abc input-default" placeholder="" value="${customer_name}">&nbsp;&nbsp;
    手机号码：
    <input type="text" name="dn" id="dn"class="abc input-default" placeholder="" value="${dn}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>
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
            <th>配驾</th>
            <th>自理油/过路费</th>
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
            <td>
                <c:if test="${reservation.status == '待审核'}">
                    <button type="button" class="btn btn-success" id="pass" value="${reservation.id}">通过</button>
                    <button type="button" class="btn btn-danger" id="nopass" value="${reservation.id}">不通过</button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $(function () {
        $('#pass').click(function(){
            var id = $(this).val();
            $.ajax({
                url:"${ctx}/vehicleservice/riskcontrol/doaudit",
                type: "post",
                data:{id:id,status:'风控通过'},
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

        $('#nopass').click(function(){
            if(confirm("确定不通过吗？")) {
                var id = $(this).val();
                $.ajax({
                    url:"${ctx}/vehicleservice/riskcontrol/doaudit",
                    type: "post",
                    data:{id:id,status:'风控不通过'},
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
            }

        })
    });

</script>