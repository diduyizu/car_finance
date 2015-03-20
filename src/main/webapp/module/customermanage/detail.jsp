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

    <script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>
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
    <form class="cmxform form-horizontal">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft">姓名</td>
                <td>${customer_info.customer_name}</td>
                <td class="tableleft">手机号</td>
                <td>${customer_info.customer_dn}</td>
                <td class="tableleft">会员号</td>
                <td>${customer_info.vip_no}</td>
            </tr>
            <tr>
                <td class="tableleft">客户类型</td>
                <td>${customer_info.customer_type}</td>
                <td class="tableleft">证件类型</td>
                <td>${customer_info.certificate_type}</td>
                <td class="tableleft">证件号码</td>
                <td>${customer_info.certificate_no}</td>
            </tr>
            <tr>
                <td class="tableleft">客户房产</td>
                <td>${customer_info.customer_house}</td>
                <td class="tableleft">客户车辆</td>
                <td>${customer_info.customer_vehicle}</td>
                <td class="tableleft">客户担保人/单位</td>
                <td>${customer_info.customer_guarantee}</td>
            </tr>
        </table>
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td width="15%" class="tableleft">证件照</td>
                <td colspan="3">
                    <c:if test="${customer_info.certificate_url != null && '' != customer_info.certificate_url}">
                        <img src="${ctx}${customer_info.certificate_url}" alt="${customer_info.certificate_name}">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td class="tableleft">身份证明</td>
                <td>
                    <c:if test="${customer_info.identity_url != null && '' != customer_info.identity_url}">
                        <img src="${ctx}${customer_info.identity_url}" alt="${customer_info.identity_name}">
                    </c:if>
                </td>
                <td class="tableleft">房产证明</td>
                <td>
                    <c:if test="${customer_info.house_property_url != null && '' != customer_info.house_property_url}">
                        <img src="${ctx}${customer_info.house_property_url}" alt="${customer_info.house_property_name}">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td class="tableleft">车辆证明</td>
                <td>
                    <c:if test="${customer_info.driving_license_url != null && '' != customer_info.driving_license_url}">
                        <img src="${ctx}${customer_info.driving_license_url}" alt="${customer_info.driving_license_name}">
                    </c:if>
                </td>
                <td class="tableleft">其他证明</td>
                <td>
                    <c:if test="${customer_info.other_url != null && '' != customer_info.other_url}">
                        <img src="${ctx}${customer_info.other_url}" alt="${customer_info.other_name}">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td colspan="3">
                    <button type="button" class="btn btn-success" id="backid">返回列表</button>
                </td>
            </tr>
        </table>
        <table class="table table-bordered table-hover definewidth m10">
            <thead>
                <tr>
                    <th>合同编号</th>
                    <th>租赁类型</th>
                </tr>
            </thead>
            <c:forEach var="contrace" items="${vehicle_contrace_list}" varStatus="status">
            <tr>
                <td>${contrace.contrace_no}</td>
                <td>零租</td>
            </tr>
            </c:forEach>
        </table>
        <table class="table table-bordered table-hover definewidth m10">
            <thead>
                <tr>
                    <th>合同编号</th>
                    <th>租赁类型</th>
                </tr>
            </thead>
            <c:forEach var="contrace" items="${property_contrace_list}" varStatus="status">
            <tr>
                <td>${contrace.contrace_no}</td>
                <td>产权租</td>
            </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>
<script>
    $(function () {
		$('#backid').click(function(){
            window.location.href="${ctx}/customer/info/index";
		});

        $('#save').click(function(){
            var customer_id=$.trim($('#customer_id').val());
            var certificate_type=$.trim($('#certificate_type').val());
            var certificate_no=$.trim($('#certificate_no').val());
            var customer_name=$.trim($('#customer_name').val());
            var customer_dn=$.trim($('#customer_dn').val());
            var customer_email=$.trim($('#customer_email').val());
            var customer_type=$.trim($('#customer_type').val());
            var customer_house=$.trim($('#customer_house').val());
            var customer_vehicle=$.trim($('#customer_vehicle').val());
            var customer_guarantee=$.trim($('#customer_guarantee').val());

            $.ajax({
                url:"${ctx}/customer/info/domodify",
                type: "post",
                data:{id:customer_id,certificate_type:certificate_type,certificate_no:certificate_no,customer_name:customer_name,customer_dn:customer_dn,
                    customer_email:customer_email,customer_type:customer_type,customer_house:customer_house,
                    customer_vehicle:customer_vehicle,customer_guarantee:customer_guarantee},
                success:function(data){
                    if(data == 1){
                        alert("成功");
                        location.reload();
                    } else if(data = -1) {
                        alert("证件号码重复");
                        return false;
                    } else {
                        alert("失败");
                        return false;
                    }
                }
            })
        })
    });
</script>