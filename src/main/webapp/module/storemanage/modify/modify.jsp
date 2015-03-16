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
    <form class="definewidth m20">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft">省份</td>
                <td>
                    <c:forEach var="province" items="${province_list}" varStatus="status">
                        <c:if test="${province.enum_value == store.org_province}">${province.enum_desc}</c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td class="tableleft">地市</td>
                <td>
                    <c:forEach var="city" items="${city_list}" varStatus="status">
                        <c:if test="${city.city_id == store.org_city}">${city.city_name}</c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td class="tableleft">门店类型</td>
                <td>
                    <c:forEach var="org_type" items="${org_type_list}" varStatus="status">
                        <c:if test="${org_type.enum_value == store.org_type}">${org_type.enum_desc}</c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td width="15%" class="tableleft">门店名称</td>
                <td><input type="text" name="store_name" id="store_name" placeholder="必填" required="true" value="${store.org_name}"/></td>
            </tr>
            <tr>
                <td class="tableleft">门店地址</td>
                <td><input type="text" name="store_address" id="store_address" placeholder="必填" required="true" value="${store.org_address}"/></td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td>
                    <input type="hidden" id="org_id" value="${store.org_id}"/>
                    <button type="button" class="btn btn-primary" id="save">保存</button>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
<script>
    $(function () {
        $('#save').click(function(){
            var org_id = $('#org_id').val();
            var store_name = $.trim($('#store_name').val());
            var store_address = $.trim($('#store_address').val());

            if(store_name == '') {
                alert("请输入门店名称");
                return false;
            }

            $.ajax({
                url:"${ctx}/store/domodify",
                type: "post",
                data:{org_id:org_id,store_name:store_name,store_address:store_address},
//                dataType:"json",
                success:function(data){
                    if(data > 0){
                        alert("成功");
                        location.reload();
                    } else {
                        alert("失败");
                    }
                }
            })
        })
    });
</script>