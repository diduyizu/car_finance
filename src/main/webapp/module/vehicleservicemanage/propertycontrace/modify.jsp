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
    <script type="text/javascript" src="<c:url value="/resources/Js/idcardValidate.js" />"></script>

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

            var user_employee_id_name_json = ${user_employee_id_name_json};
            console.info(user_employee_id_name_json);
            $('#employee_id_name').typeahead({
                source: user_employee_id_name_json
            });
        })
    </script>
</head>
<body>
    <form class="cmxform form-horizontal">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft">所属门店</td>
                <td>
                    <select id="original_org" name="original_org">
                        <%--<c:forEach var="org" items="${user_all_org_list}" varStatus="status">--%>
                            <%--<c:if test="${org.org_id == property_contrace_info.org_id}">--%>
                                <%--<option value="${org.org_id}" selected="selected">${org.org_name}</option>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${org.org_id != property_contrace_info.org_id}">--%>
                                <%--<option value="${org.org_id}">${org.org_name}</option>--%>
                            <%--</c:if>--%>
                        <%--</c:forEach>--%>

                        <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                            <c:if test="${org.org_id == property_contrace_info.org_id}">
                                <c:if test="${org.org_type > 12}">
                                    <option value="${org.org_id}" selected="selected">${org.org_city_name} ${org.org_name}</option>
                                </c:if>
                                <c:if test="${org.org_type < 13}">
                                    <option value="${org.org_id}" selected="selected">${org.org_name}</option>
                                </c:if>
                            </c:if>
                            <c:if test="${org.org_id != property_contrace_info.org_id}">
                                <c:if test="${org.org_type > 12}">
                                    <option value="${org.org_id}">${org.org_city_name} ${org.org_name}</option>
                                </c:if>
                                <c:if test="${org.org_type < 13}">
                                    <option value="${org.org_id}">${org.org_name}</option>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
                <td class="tableleft">合同编号</td>
                <td><input type="text" name="contrace_no" id="contrace_no" placeholder="必填" value="${property_contrace_info.contrace_no}" required="true"/></td>
                <td class="tableleft">合同类型</td>
                <td>产权租</td>
            </tr>
            <tr>
                <td class="tableleft">客户姓名</td>
                <td><input type="text" data-provide="typeahead" name="customer_name" id="customer_name" required="true" placeholder="必填" value="${property_contrace_info.customer_name}"/></td>
                <td class="tableleft">客户类型</td>
                <td>
                    <select id="customer_type" name="customer_type">
                        <option value="个人用户" <c:if test="${property_contrace_info.customer_type == '个人用户'}"> selected="selected" </c:if>>个人用户</option>
                        <option value="企业用户" <c:if test="${property_contrace_info.customer_type == '企业用户'}"> selected="selected" </c:if>>企业用户</option>
                    </select>
                </td>
                <td class="tableleft">客户手机</td>
                <td><input type="text" name="customer_dn" id="customer_dn" required="true" placeholder="必填" value="${property_contrace_info.customer_dn}"/></td>
            </tr>
            <tr>
                <td class="tableleft">证件类型</td>
                <td>
                    <select id="certificate_type" name="certificate_type">
                        <option value="身份证" <c:if test="${property_contrace_info.customer_cer_type == '身份证'}"> selected="selected" </c:if>>身份证</option>
                        <option value="国际护照" <c:if test="${property_contrace_info.customer_cer_type == '国际护照'}"> selected="selected" </c:if>>国际护照</option>
                        <option value="回乡证" <c:if test="${property_contrace_info.customer_cer_type == '回乡证'}"> selected="selected" </c:if>>回乡证</option>
                        <option value="台胞证" <c:if test="${property_contrace_info.customer_cer_type == '台胞证'}"> selected="selected" </c:if>>台胞证</option>
                        <option value="其他" <c:if test="${vehicle_contrace_info.customer_cer_type == '其他'}"> selected="selected" </c:if>>其他</option>
                    </select>
                </td>
                <td class="tableleft">证件号码</td>
                <td colspan="3"><input type="text" name="certificate_no" id="certificate_no" required="true" placeholder="必填" value="${property_contrace_info.customer_cer_no}"/></td>
            </tr>

            <tr>
                <td class="tableleft">签订日期</td>
                <td>
                    <div class="input-append date" id="sign_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="sign_at_date"  name="sign_at_date" placeholder="必填"  value="${property_contrace_info.sign_at}" required />
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
                <td class="tableleft">合同期限</td>
                <td><input type="text" name="period_number" id="period_number" placeholder="必填" required="true" value="${property_contrace_info.period_number}"/></td>
                <td class="tableleft">首付款</td>
                <td><input type="text" name="down_payment" id="down_payment" placeholder="必填" required="true" value="${property_contrace_info.down_payment}"/></td>
            </tr>
            <tr>
                <td class="tableleft">合同租赁价格</td>
                <td><input type="text" name="lease_price" id="lease_price" placeholder="必填" required="true" value="${property_contrace_info.lease_price}"/></td>
                <td class="tableleft">月付款</td>
                <td><input type="text" name="montyly_payment" id="montyly_payment" placeholder="必填" required="true" value="${property_contrace_info.monthly_payment}"/></td>
                <td class="tableleft">协商月付</td>
                <td><input type="text" name="arrange_payment" id="arrange_payment" placeholder="必填" required="true" value="${property_contrace_info.arrange_payment}"/></td>
            </tr>
            <tr>
                <td class="tableleft">月付款日</td>
                <td><input type="text" name="monthly_day" id="monthly_day" placeholder="必填" required="true" value="${property_contrace_info.monthly_day}"/></td>
                <td class="tableleft">尾款</td>
                <td><input type="text" name="final_payment" id="final_payment" placeholder="必填" required="true" value="${property_contrace_info.final_payment}"/></td>
                <td class="tableleft">付款方式</td>
                <td colspan="3"><input type="text" name="payment_type" id="payment_type" placeholder="必填" required="true" value="${property_contrace_info.payment_type}"/></td>
            </tr>
            <tr>
                <td class="tableleft">已收回期数</td>
                <td><input type="text" name="received_periods" id="received_periods" placeholder="必填" required="true" value="${property_contrace_info.received_periods}"/></td>
                <td class="tableleft">已收回金额</td>
                <td colspan="3"><input type="text" name="already_back_amount" id="already_back_amount" placeholder="必填" required="true" value="${property_contrace_info.already_back_amount}"/></td>
            </tr>

            <tr>
                <td class="tableleft">处理员工姓名/工号</td>
                <td colspan="5"><input type="text" name="employee_id_name" id="employee_id_name" placeholder="必填" required value="${property_contrace_info.employee_name}|${property_contrace_info.employee_id}" /></td>
                <%--<td class="tableleft">业务员id</td>--%>
                <%--<td><input type="text" name="employee_id" id="employee_id" value="${property_contrace_info.employee_id}"/></td>--%>
                <%--<td class="tableleft">业务员姓名</td>--%>
                <%--<td colspan="3"><input type="text" name="employee_name" id="employee_name" value="${property_contrace_info.employee_name}"/></td>--%>
            </tr>
            <tr>
                <td class="tableleft">描述</td>
                <td colspan="5"><textarea id="remark" rows="4" style="margin: 0px 0px 10px; width: 766px; height: 140px;" >${property_contrace_info.remark}</textarea></td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td colspan="5">
                    <input type="hidden" id="contrace_id" value="${property_contrace_info.id}">
                    <input type="hidden" id="reservation_id" value="${property_contrace_info.reservation_id}">
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
        $('#sign_at').datepicker();

		$('#backid').click(function(){
            window.location.href="${ctx}/vehicleservice/contrace/index?page_index=${current_page}&original_org=${original_org}";
		});

        $("#certificate_no").blur(function(){
            var certificate_type = $.trim($('#certificate_type').val());
            if(certificate_type == '身份证') {
                var certificate_no = $(this).val();
                var idcard_validate_result = IdCardValidate(certificate_no);
                if(!idcard_validate_result) {
                    alert("请输入正确的身份证号码");
                    return false;
                }
            }
        });

        $('#save').click(function(){
            var contrace_id=$.trim($('#contrace_id').val());
            var reservation_id=$.trim($('#reservation_id').val());
            var original_org=$.trim($('#original_org').val());


            var contrace_no=$.trim($('#contrace_no').val());
//            var contrace_type=$.trim($('#contrace_type').val());
            var customer_name=$.trim($('#customer_name').val());
            var customer_type=$.trim($('#customer_type').val());
            var customer_dn=$.trim($('#customer_dn').val());
            var certificate_type=$.trim($('#certificate_type').val());
            var certificate_no=$.trim($('#certificate_no').val());
            var sign_at_date=$.trim($('#sign_at_date').val());

            var period_number=$.trim($('#period_number').val());
            var down_payment=$.trim($('#down_payment').val());
            var lease_price=$.trim($('#lease_price').val());
            var montyly_payment=$.trim($('#montyly_payment').val());
            var arrange_payment=$.trim($('#arrange_payment').val());
            var monthly_day=$.trim($('#monthly_day').val());
            var final_payment=$.trim($('#final_payment').val());
            var payment_type=$.trim($('#payment_type').val());
            var received_periods=$.trim($('#received_periods').val());
            var already_back_amount=$.trim($('#already_back_amount').val());

            var employee_id_name = $.trim($('#employee_id_name').val());
            var employee_id = employee_id_name.split("|")[0];
            var employee_name = employee_id_name.split("|")[1];
//            var employee_id=$.trim($('#employee_id').val());
//            var employee_name=$.trim($('#employee_name').val());
            var remark=$.trim($('#remark').val());

            $.ajax({
                url:"${ctx}/vehicleservice/contrace/property/domodify",
                type: "post",
                data:{contrace_id:contrace_id,reservation_id:reservation_id,original_org:original_org,contrace_no:contrace_no,
                    customer_name:customer_name,customer_type:customer_type,customer_dn:customer_dn,certificate_type:certificate_type,certificate_no:certificate_no,
                    sign_at_date:sign_at_date,period_number:period_number,down_payment:down_payment,lease_price:lease_price,montyly_payment:montyly_payment,
                    arrange_payment:arrange_payment,monthly_day:monthly_day,final_payment:final_payment,payment_type:payment_type,received_periods:received_periods,
                    already_back_amount:already_back_amount,employee_id:employee_id,employee_name:employee_name,remark:remark},
                success:function(data){
                    if(data > 0){
                        alert("成功");
                        window.location.href="${ctx}/vehicleservice/contrace/property/index";
                    } else {
                        alert("失败");
                        return false;
                    }
                }
            })
        })
    });
</script>