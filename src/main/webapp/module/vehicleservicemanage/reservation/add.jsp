<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jstl_contexpath.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap.min.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap-responsive.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/datetimepicker.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/style.css" />" />

    <%--<script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>--%>
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery-1.7.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datetimepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datetimepicker.zh-CN.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-typeahead.js" />"></script>

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
    <form class="definewidth m20">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft">所属门店</td>
                <td>
                    <select id="original_org" name="original_org">
                        <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                            <option value="${org.org_id}">${org.org_name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td class="tableleft">合同类型</td>
                <td>
                    <select id="contrace_type" name="contrace_type">
                        <option value="1">零租</option>
                        <option value="2">产权租</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tableleft">客户姓名</td>
                <td><input type="text" data-provide="typeahead" name="customer_name" id="customer_name" placeholder="必填" required="true"/></td>
                <td class="tableleft">客户手机</td>
                <td><input type="text" name="customer_dn" id="customer_dn" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">用车开始时间</td>
                <td>
                    <input class="form_datetime" size="16" type="text" id="use_begin_date" name="use_begin_date" value="" placeholder="必填" required="true" readonly>
                </td>
                <td class="tableleft">用车结束时间</td>
                <td>
                    <input class="form_datetime" size="16" type="text" id="use_end_date" name="use_end_date" value="" placeholder="必填" required="true" readonly>
                </td>
            </tr>
            <tr>
                <td class="tableleft">业务员id</td>
                <td><input type="text" name="employee_id" id="employee_id" value="0" /></td>
                <td class="tableleft">业务员姓名</td>
                <td><input type="text" name="employee_name" id="employee_name" /></td>
            </tr>
            <tr>
                <td class="tableleft">描述</td>
                <td colspan="3"><textarea id="remark" rows="6" placeholder="备注、描述，填写客户所需车辆型号、价格、是否配驾、是否自理油费等信息" style="margin: 0px 0px 10px; width: 766px; height: 140px;" ></textarea></td>
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
            var contrace_type=$.trim($('#contrace_type').val());
            var customer_name=$.trim($('#customer_name').val());
            var customer_dn=$.trim($('#customer_dn').val());
            var use_begin_date=$.trim($('#use_begin_date').val());
            var use_end_date=$.trim($('#use_end_date').val());
            var employee_id=$.trim($('#employee_id').val());
            var employee_name=$.trim($('#employee_name').val());
            var remark=$.trim($('#remark').val());

            $.ajax({
                url:"${ctx}/vehicleservice/reservation/doadd",
                type: "post",
                data:{original_org:original_org,contrace_type:contrace_type,customer_name:customer_name,customer_dn:customer_dn,
                    use_begin:use_begin_date,use_end:use_end_date,employee_id:employee_id,employee_name:employee_name,remark:remark},
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