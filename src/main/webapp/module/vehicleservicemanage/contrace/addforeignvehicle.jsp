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
                <td class="tableleft">车牌</td>
                <td><input type="text" name="license_plate" id="license_plate" placeholder="必填" required="true"/></td>
                <td class="tableleft">车型</td>
                <td><input type="text" name="model" id="model" placeholder="必填" required="true"/></td>
                <td class="tableleft">车价</td>
                <td><input type="text" name="vehicle_price" id="vehicle_price" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">是否有ETC</td>
                <td>
                    <select id="etc" name="etc">
                        <option value="有">有</option>
                        <option value="无">无</option>
                    </select>
                </td>
                <td class="tableleft">当前金额</td>
                <td colspan="3"><input type="text" name="etc_money" id="etc_money" /></td>
            </tr>
            <tr>
                <td class="tableleft">当前油量比（百分比％）</td>
                <td><input type="text" name="oil_percent" id="oil_percent" placeholder="必填" required="true"/>%</td>
                <td class="tableleft">公里数</td>
                <td colspan="3"><input type="text" name="other_vehicle_km" id="other_vehicle_km" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">外援公司</td>
                <td colspan="5"><input type="text" name="company" id="company" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">日租金</td>
                <td><input type="text" name="daily_price" id="daily_price" placeholder="必填" required="true" /></td>
                <td class="tableleft">结算方式</td>
                <td>
                    <select name="settlement_way" id="settlement_way">
                        <option value="客户自理">客户自理</option>
                        <option value="公司包干">公司包干</option>
                        <option value="一口价">一口价</option>
                    </select>
                </td>
                <td class="tableleft">一口价</td>
                <td><input type="text" name="fixed_price" id="fixed_price" value="0" /></td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td colspan="5">
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

            var etc=$.trim($('#etc').val());
            var etc_money=$.trim($('#etc_money').val());
            var oil_percent=$.trim($('#oil_percent').val());

            var daily_price=$.trim($('#daily_price').val());
            var settlement_way=$.trim($('#settlement_way').val());
            var fixed_price=$.trim($('#fixed_price').val());

            $.ajax({
                url:"${ctx}/vehicleservice/contrace/doaddforeignvehicle",
                type: "post",
                data:{contrace_id:contrace_id,license_plate:license_plate,model:model,vehicle_price:vehicle_price,company:company,
                    other_vehicle_km:other_vehicle_km,etc:etc,etc_money:etc_money,oil_percent:oil_percent,
                    daily_price:daily_price,settlement_way:settlement_way,fixed_price:fixed_price},
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