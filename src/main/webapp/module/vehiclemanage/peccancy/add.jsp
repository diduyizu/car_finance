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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/datetimepicker.css" />" />

    <%--<script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>--%>
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery-1.7.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-typeahead.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datetimepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datetimepicker.zh-CN.js" />"></script>

 

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
            var user_employee_id_name_json = ${user_employee_id_name_json};
            console.info(user_employee_id_name_json);
            $('#employee_id_name').typeahead({
                source: user_employee_id_name_json
            });

            var customer_name_certification_no_json = ${customer_name_certification_no_json};
            console.info(customer_name_certification_no_json);
            $('#customer_id_name').typeahead({
                source: customer_name_certification_no_json
            });
        })
    </script>
</head>
<body>
    <form class="definewidth m20">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft">车架号</td>
                <td><input type="text" name="carframe_no" id="carframe_no" value="${vehicle_info.carframe_no}" readonly/></td>
                <td class="tableleft">发动机号</td>
                <td><input type="text" name="engine_no" id="engine_no" value="${vehicle_info.engine_no}" readonly/></td>
                <td class="tableleft">车牌号</td>
                <td><input type="text" name="license_plate" id="license_plate" value="${vehicle_info.license_plate}" readonly/></td>
            </tr>
            <tr>
                <td class="tableleft">违章时间</td>
                <td>
                    <%--<div class="input-append date" id="peccancy_at" data-date-format="yyyy-mm-dd">--%>
                        <%--<input class="span2" size="16" type="text" id="peccancy_at_date" name="peccancy_at_date" placeholder="必填" required="true" readonly>--%>
                        <%--<span class="add-on"><i class="icon-th"></i></span>--%>
                    <%--</div>--%>
                    <input class="form_datetime" size="16" type="text" id="peccancy_at_date" name="peccancy_at_date" placeholder="必填" value="${vehicleReservationInfo.use_begin}" required="true" readonly>
                </td>
                <td class="tableleft">违章地点</td>
                <td><input type="text" name="peccancy_place" id="peccancy_place" placeholder="必填" required/></td>
                <td class="tableleft">违章原因</td>
                <td><input type="text" name="peccancy_reason" id="peccancy_reason" placeholder="必填" required/></td>
            </tr>
            <tr>
                <td class="tableleft">罚款金额</td>
                <td><input type="text" name="peccancy_price" id="peccancy_price" value="0" placeholder="必填" required=""/></td>
                <td class="tableleft">扣分数</td>
                <td><input type="text" name="score" id="score" value="0"/></td>
                <td class="tableleft">是否已处理</td>
                <td>
                    <input type="radio" name="status" value="0" checked>未处理
                    <input type="radio" name="status" value="1">已处理
                </td>
            </tr>
            <%--<tr>--%>
                <%--<td class="tableleft">处理员工工号/姓名</td>--%>
                <%--<td><input type="text" name="employee_id" id="employee_id" /></td>--%>
                <%--<td class="tableleft">处理员工姓名</td>--%>
                <%--<td colspan="3"><input type="text" name="employee_name" id="employee_name"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td class="tableleft">客户id</td>--%>
                <%--<td><input type="text" name="customer_id" id="customer_id" value="0"/></td>--%>
                <%--<td class="tableleft">客户姓名</td>--%>
                <%--<td colspan="3"><input type="text" name="customer_name" id="customer_name"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td class="tableleft">仲裁结果</td>--%>
                <%--<td colspan="5"><input type="text" name="arbitration" id="arbitration"/></td>--%>
            <%--</tr>--%>
            <tr>
                <td class="tableleft">处理员工姓名/工号</td>
                <td><input type="text" name="employee_id_name" id="employee_id_name" placeholder="必填" required /></td>
                <td class="tableleft">客户姓名/证件号</td>
                <td><input type="text" name="customer_id_name" id="customer_id_name"  /></td>
                <td class="tableleft">仲裁结果</td>
                <td colspan="5"><input type="text" name="arbitration" id="arbitration"/></td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td colspan="5">
                    <button type="button" class="btn btn-primary" id="save">保存</button> &nbsp;&nbsp;
                    <button type="button" class="btn btn-success" id="backid">返回列表</button>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
<script>
    $(function () {
        window.prettyPrint && prettyPrint();
        $('#peccancy_at').datepicker();

        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            language: 'zh-CN',
            pickDate: true,
            pickTime: true,
            hourStep: 1,
            minuteStep: 15,
            secondStep: 30,
            inputMask: true
        });

		$('#backid').click(function(){
            window.location.href="${ctx}/vehicle/peccancy/index";
		});

        $('#peccancy_place').focus(function(){
            var license_plate=$.trim($('#license_plate').val());
            var peccancy_at_date=$.trim($('#peccancy_at_date').val());

            $.ajax({
                url:"${ctx}/vehicle/peccancy/timegetcustomer",
                type: "post",
                data:{license_plate:license_plate,peccancy_at_date:peccancy_at_date},
                success:function(data){
                    $('#customer_id_name').val(data);
                }
            })

        });



        $('#save').click(function(){
            var carframe_no=$.trim($('#carframe_no').val());
            var engine_no=$.trim($('#engine_no').val());
            var license_plate=$.trim($('#license_plate').val());
            var peccancy_at_date=$.trim($('#peccancy_at_date').val());
            var peccancy_place=$.trim($('#peccancy_place').val());
            var peccancy_reason=$.trim($('#peccancy_reason').val());
            var score=$.trim($('#score').val());
            var status=$('input:radio:checked').val();

            var peccancy_price=$.trim($('#peccancy_price').val());
            var arbitration=$.trim($('#arbitration').val());

            var employee_id_name = $.trim($('#employee_id_name').val());
            var customer_id_name = $.trim($('#customer_id_name').val());

            var employee_id = employee_id_name.split("|")[1];
            var employee_name = employee_id_name.split("|")[0];
            var customer_id = customer_id_name.split("|")[1];
            var customer_name = customer_id_name.split("|")[0];

//            var employee_id=$.trim($('#employee_id').val());
//            var employee_name=$.trim($('#employee_name').val());
//            var customer_id=$.trim($('#customer_id').val());
//            var customer_name=$.trim($('#customer_name').val());

            $.ajax({
                url:"${ctx}/vehicle/peccancy/doadd",
                type: "post",
                data:{carframe_no:carframe_no,engine_no:engine_no,license_plate:license_plate,
                    peccancy_at_date:peccancy_at_date,peccancy_place:peccancy_place,peccancy_reason:peccancy_reason,
                    score:score,status:status,peccancy_price:peccancy_price,arbitration:arbitration,
                    employee_id:employee_id,employee_name:employee_name,customer_id:customer_id,customer_name:customer_name},
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
    });
</script>