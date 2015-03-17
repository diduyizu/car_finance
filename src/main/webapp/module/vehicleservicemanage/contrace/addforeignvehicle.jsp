<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jstl_contexpath.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap.min.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap-responsive.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/datetimepicker.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/datepicker.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/style.css" />" />

    <%--<script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>--%>
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery-1.7.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datetimepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datetimepicker.zh-CN.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-typeahead.js" />"></script>
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
    <script type="text/javascript">
        $().ready(function() {
            var customer_name_json = ${customer_name_json};
            console.info(customer_name_json);
            $('#customer_name').typeahead({
                source: customer_name_json
            });
        })
    </script>
</head>
<body>
    <form class="cmxform form-horizontal">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td width="15%" class="tableleft">车牌</td>
                <td><input type="text" name="license_plate" id="license_plate" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">车型</td>
                <td><input type="text" name="model" id="model" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">车价</td>
                <td><input type="text" name="vehicle_price" id="vehicle_price" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">公里数</td>
                <td><input type="text" name="other_vehicle_km" id="other_vehicle_km" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">外援公司</td>
                <td><input type="text" name="company" id="company" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td>
                    <input type="hidden" id="contrace_id" value="${contrace_id}">
                    <button type="button" class="btn btn-primary" id="save">保存</button> &nbsp;&nbsp;
                    <%--<button type="button" class="btn btn-success" id="backid">返回列表</button>--%>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
<script>
    $(function () {
        window.prettyPrint && prettyPrint();

		$('#backid').click(function(){
            window.location.href="${ctx}/vehicleservice/reservation/index";
		});

        $('#save').click(function(){
            var contrace_id=$.trim($('#contrace_id').val());
            var license_plate=$.trim($('#license_plate').val());
            var model=$.trim($('#model').val());
            var vehicle_price=$.trim($('#vehicle_price').val());
            var company=$.trim($('#company').val());
            var other_vehicle_km = $.trim($('#other_vehicle_km').val());

            $.ajax({
                url:"${ctx}/vehicleservice/contrace/doaddforeignvehicle",
                type: "post",
                data:{contrace_id:contrace_id,license_plate:license_plate,model:model,vehicle_price:vehicle_price,company:company,other_vehicle_km:other_vehicle_km},
                success:function(data){
                    if(data > 0){
                        alert("成功");
                        window.location.href="${ctx}/vehicleservice/contrace/index";
                    } else {
                        alert("失败");
                        return false;
                    }
                }
            })
        })
    });
</script>