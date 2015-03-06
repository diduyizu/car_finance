<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jstl_contexpath.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap-responsive.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/style.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap.min.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap-datetimepicker.min.css" />" />

    <script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datetimepicker.min.js" />"></script>
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
</head>
<body>
    <form class="definewidth m20">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td width="10%" class="tableleft">所属门店</td>
                <td>
                    <select id="original_org" name="original_org">
                        <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                            <option value="${org.org_id}">${org.org_name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td class="tableleft">车型</td>
                <td><input type="text" name="model" id="model" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">客户姓名</td>
                <td><input type="text" name="customer_name" id="customer_name" required="true"/></td>
                <td class="tableleft">客户手机</td>
                <td><input type="text" name="customer_dn" id="customer_dn" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">用车开始时间</td>
                <td>
                    <%--<div class="input-append date" id="use_begin">--%>
                        <input class="form_datetime" size="16" type="text" id="use_begin_date" name="use_begin_date" value="" required="true" readonly>
                        <%--<span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>--%>
                    <%--</div>--%>
                </td>
                <td class="tableleft">用车结束时间</td>
                <td>
                    <%--<div class="input-append date" id="use_end">--%>
                        <input class="form_datetime" size="16" type="text" id="use_end_date" name="use_end_date" value="" required="true" readonly>
                        <%--<span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>--%>
                    <%--</div>--%>
                </td>
            </tr>
            <tr>
                <td class="tableleft">单价</td>
                <td><input type="text" name="unit_price" id="unit_price" required="true"/></td>
                <td class="tableleft">数量</td>
                <td><input type="text" name="quantity" id="quantity" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">是否配驾</td>
                <td>
                    <input type="radio" name="with_driver" value="1">是
                    <input type="radio" name="with_driver" value="0" checked>否
                </td>
                <td class="tableleft">是否自理油费过路费</td>
                <td>
                    <input type="radio" name="expenses_self" value="1">是
                    <input type="radio" name="expenses_self" value="0" checked>否
                </td>
            </tr>
            <tr>
                <td class="tableleft">业务员id</td>
                <td><input type="text" name="employee_id" id="employee_id" value="0" required="true"/></td>
                <td class="tableleft">业务员姓名</td>
                <td><input type="text" name="employee_name" id="employee_name" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td colspan="3">
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
//        $('#use_begin').datetimepicker({
//            format: 'yyyy-mm-dd hh:ii',
//            language: 'zh-CN',
//            pickDate: true,
//            pickTime: true,
//            hourStep: 1,
//            minuteStep: 15,
//            secondStep: 30,
//            inputMask: true
//        });
//        $('#use_end').datetimepicker({
//            format: 'yyyy-mm-dd hh:ii',
//            language: 'en',
//            pickDate: true,
//            pickTime: true,
//            hourStep: 1,
//            minuteStep: 15,
//            secondStep: 30,
//            inputMask: true
//        });

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
            window.location.href="${ctx}/vehicleservice/reservation/index";
		});

        $('#save').click(function(){
            var original_org=$.trim($('#original_org').val());
            var model=$.trim($('#model').val());
            var customer_name=$.trim($('#customer_name').val());
            var customer_dn=$.trim($('#customer_dn').val());
            var use_begin_date=$.trim($('#use_begin_date').val());
            var use_end_date=$.trim($('#use_end_date').val());
            var unit_price=$.trim($('#unit_price').val());
            var quantity=$.trim($('#quantity').val());
            var with_driver=$('input[name="with_driver"]:checked').val();
            var expenses_self=$('input[name="expenses_self"]:checked').val();
            var employee_id=$.trim($('#employee_id').val());
            var employee_name=$.trim($('#employee_name').val());

            $.ajax({
                url:"${ctx}/vehicleservice/reservation/doadd",
                type: "post",
                data:{original_org:original_org,model:model,customer_name:customer_name,customer_dn:customer_dn,
                    use_begin:use_begin_date,use_end:use_end_date,unit_price:unit_price,quantity:quantity,with_driver:with_driver,
                    expenses_self:expenses_self,employee_id:employee_id,employee_name:employee_name},
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