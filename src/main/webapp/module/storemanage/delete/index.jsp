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
<form class="form-inline definewidth m20" action="${ctx}/store/delete/index" method="post">
    门店名称：
    <input type="text" name="store_name" id="store_name"class="abc input-default" placeholder="" value="${store_name}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>门店名称</th>
            <th>门店类型</th>
            <th>门店省份</th>
            <th>门店地市</th>
            <th>门店区县</th>
            <th>门店地址</th>
            <th>操作</th>
        </tr>
    </thead>
    <c:forEach var="store" items="${store_List}" varStatus="status">
        <tr>
            <td>${store.org_name}</td>
            <td>${store.org_type_name}</td>
            <td>${store.org_province_name}</td>
            <td>${store.org_city_name}</td>
            <td>${store.org_country_name}</td>
            <td>${store.org_address}</td>
            <td>
                <c:if test="${store.org_id >= 100000}">
                    <button type="button" class="btn btn-success delete" value="${store.org_id}">删除</button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tr>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $(function () {
        $('.delete').click(function(){
            if(confirm("确定要删除吗？")) {
                var store_id = $(this).val();
                $.ajax({
                    url:"${ctx}/store/dodelete",
                    type: "post",
                    data:{org_id:store_id},
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
            }
        })
    });
</script>