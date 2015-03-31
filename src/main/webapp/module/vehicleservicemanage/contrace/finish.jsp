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
    <%--<form class="cmxform form-horizontal">--%>
    <form class="form-inline definewidth m20">
        <table class="table table-bordered table-hover definewidth m10">
            <thead>
            <tr>
                <th>车牌号</th>
                <th>车型</th>
                <th>当前里程</th>
                <th>日租价格</th>
                <th>还车门店</th>
                <th>还车油量比(%)</th>
                <th>还车ETC金额</th>
                <th>还车时间</th>
                <th>还车里程</th>
                <th>超期费用</th>
                <th>操作</th>
            </tr>
            </thead>
            <c:forEach var="vehicle" items="${vehicle_contrace_vehs_list}" varStatus="status">
                <tr>
                    <td>${vehicle.license_plate}</td>
                    <td>${vehicle.model}</td>
                    <td>${vehicle.km}</td>
                    <td>${vehicle.daily_price}</td>
                    <td>
                        <c:if test="${vehicle.status == 1}">
                            <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                                <c:if test="${org.org_id == vehicle.return_org}">
                                    <c:if test="${org.org_type > 12}">${org.org_city_name} ${org.org_name}</c:if>
                                    <c:if test="${org.org_type < 13}">${org.org_name}</c:if>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${vehicle.status == 0}">
                            <select name="original_org">
                                <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                                    <c:if test="${org.org_type > 12}">
                                        <option value="${org.org_id}">${org.org_city_name} ${org.org_name}</option>
                                    </c:if>
                                    <c:if test="${org.org_type < 13}">
                                        <option value="${org.org_id}">${org.org_name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${vehicle.status == 1}">${vehicle.revert_oil_percent}</c:if>
                        <c:if test="${vehicle.status == 0}">
                            <input type="text" class="revert_oil_percent" name="revert_oil_percent" placeholder="必填" required="true" />
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${vehicle.status == 1}">${vehicle.revert_etc_money}</c:if>
                        <c:if test="${vehicle.status == 0}">
                            <input type="text" class="revert_etc_money" name="revert_etc_money" />
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${vehicle.status == 1}">${vehicle.return_time}</c:if>
                        <c:if test="${vehicle.status == 0}">
                            <input class="form_datetime return_time" size="16" type="text" placeholder="必填" required="true" name="return_time">
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${vehicle.status == 1}">${vehicle.return_km}</c:if>
                        <c:if test="${vehicle.status == 0}">
                            <input type="text" class="return_km" name="return_km" placeholder="必填" required="true" />
                            <input type="hidden" name="vehicle_id" value="${vehicle.vehicle_id}" />
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${vehicle.status == 1}">${vehicle.over_price}</c:if>
                        <c:if test="${vehicle.status == 0}">
                            <input type="text" name="over_price" />
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${vehicle.status == 0}">
                            <button type="button" class="btn btn-success returncar">还车</button>
                            <input type="hidden" name="vehicle_contrace_id" value="${vehicle.id}" />
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="hidden" id="contrace_id" value="${vehicle_contrace_info.id}" />
        <input type="hidden" id="use_begin" value="${vehicle_contrace_info.use_begin}" />
        <input type="hidden" id="use_end" value="${vehicle_contrace_info.use_end}" />
        <input type="hidden" id="daily_available_km" value="${vehicle_contrace_info.daily_available_km}" />
        <input type="hidden" id="over_km_price" value="${vehicle_contrace_info.over_km_price}" />
        <input type="hidden" id="over_hour_price" value="${vehicle_contrace_info.over_hour_price}" />
    </form>

    <form class="cmxform form-horizontal">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft">系统应收租金</td>
                <td><input type="text" name="system_total_price" id="system_total_price" value="${system_total_price}" readonly/></td>
                <td class="tableleft">约定所得租金</td>
                <td><input type="text" name="arrange_price" id="arrange_price" placeholder="必填" required /></td>
            </tr>
            <tr>
                <td class="tableleft">实际所得租金</td>
                <td><input type="text" name="actual_price" id="actual_price" placeholder="必填" required /></td>
                <td class="tableleft">滞纳金</td>
                <td><input type="text" name="late_fee" id="late_fee" value="0" placeholder="必填" required /></td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td colspan="3">
                    <button type="button" class="btn btn-primary" id="dofinish">结单</button>&nbsp;&nbsp;结单前，请还清所有车辆
                </td>
            </tr>
        </table>
    </form>

    <form class="cmxform form-horizontal">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft">所属门店</td>
                <td>
                    <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                        <c:if test="${org.org_id == vehicle_contrace_info.org_id}">
                            ${org.org_name}
                        </c:if>
                    </c:forEach>
                </td>
                <td class="tableleft">合同编号</td>
                <td>${vehicle_contrace_info.contrace_no}</td>
                <td class="tableleft">合同类型</td>
                <td>
                    <c:if test="${vehicle_contrace_info.contrace_type == 1}">零租</c:if>
                    <c:if test="${vehicle_contrace_info.contrace_type == 2}">产权组</c:if>
                </td>
            </tr>
            <tr>
                <td class="tableleft">客户姓名</td>
                <td>${vehicle_contrace_info.customer_name}</td>
                <td class="tableleft">客户类型</td>
                <td>
                    ${vehicle_contrace_info.customer_type}
                </td>
                <td class="tableleft">客户手机</td>
                <td>${vehicle_contrace_info.customer_dn}</td>
            </tr>
            <tr>
                <td class="tableleft">证件类型</td>
                <td>
                    ${vehicle_contrace_info.customer_cer_type}
                </td>
                <td class="tableleft">证件号码</td>
                <td colspan="3">${vehicle_contrace_info.customer_cer_no}</td>
            </tr>
            <tr>
                <td class="tableleft">用车开始时间</td>
                <td>${vehicle_contrace_info.use_begin}</td>
                <td class="tableleft">用车结束时间</td>
                <td colspan="3">${vehicle_contrace_info.use_end}</td>
            </tr>

            <tr>
                <td class="tableleft">日单价</td>
                <td>${vehicle_contrace_info.daily_price}</td>
                <td class="tableleft">日公里数</td>
                <td colspan="3">${vehicle_contrace_info.daily_available_km}</td>
            </tr>
            <tr>
                <td class="tableleft">超公里金额</td>
                <td>${vehicle_contrace_info.over_km_price}</td>
                <td class="tableleft">超小时金额</td>
                <td colspan="3">${vehicle_contrace_info.over_hour_price}</td>
            </tr>
            <tr>
                <td class="tableleft">月结日</td>
                <td>${vehicle_contrace_info.monthly_day}</td>
                <td class="tableleft">包月单价</td>
                <td>${vehicle_contrace_info.month_price}</td>
                <td class="tableleft">包月公里数</td>
                <td>${vehicle_contrace_info.month_available_km}</td>
            </tr>
            <tr>
                <td class="tableleft">预付款</td>
                <td>${vehicle_contrace_info.pre_payment}</td>
                <td class="tableleft">总押金</td>
                <td>${vehicle_contrace_info.deposit}</td>
                <td class="tableleft">违章押金</td>
                <td>${vehicle_contrace_info.peccancy_deposit}</td>
            </tr>
            <tr>
                <td class="tableleft">业务员id</td>
                <td>${vehicle_contrace_info.employee_id}</td>
                <td class="tableleft">业务员姓名</td>
                <td colspan="3">${vehicle_contrace_info.employee_name}</td>
            </tr>
            <tr>
                <td class="tableleft">描述</td>
                <%--<td colspan="5"><textarea readonly id="remark" rows="4" style="margin: 0px 0px 10px; width: 766px; height: 140px;" >${vehicle_contrace_info.remark}</textarea></td>--%>
                <td colspan="5">${vehicle_contrace_info.remark}</td>
            </tr>
        </table>
    </form>
</body>
</html>
<script>
    $(function () {
        window.prettyPrint && prettyPrint();
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

        $(".returncar").click(function(){
            var contrace_id=$.trim($('#contrace_id').val());
            var return_time = $(this).parent('td').parent('tr').find("input[name='return_time']").val();
            var return_km = $(this).parent('td').parent('tr').find("input[name='return_km']").val();
            var vehicle_id = $(this).parent('td').parent('tr').find("input[name='vehicle_id']").val();
            var over_price = $(this).parent('td').parent('tr').find("input[name='over_price']").val();
            var vehicle_contrace_id = $(this).next("input").val();
            var return_org = $(this).parent('td').parent('tr').find("select[name='original_org']").val();

            var revert_oil_percent = $(this).parent('td').parent('tr').find("input[name='revert_oil_percent']").val();
            var revert_etc_money = $(this).parent('td').parent('tr').find("input[name='revert_etc_money']").val();


            if(return_time == "" || return_time == null) {
                alert("请选择还车时间");
                return false;
            }
            if(return_km == "" || return_km == null) {
                alert("请输入还车里程");
                return false;
            }
            if(revert_oil_percent == "" || revert_oil_percent == null) {
                alert("请输入还车油量比");
                return false;
            }


            $.ajax({
                url:"${ctx}/vehicleservice/contrace/returnvahicle",
                type: "post",
                data:{contrace_id:contrace_id,return_time:return_time,return_km:return_km,vehicle_id:vehicle_id,
                    over_price:over_price,vehicle_contrace_id:vehicle_contrace_id,return_org:return_org,
                    revert_oil_percent:revert_oil_percent,revert_etc_money:revert_etc_money},
                success:function(data){
                    if(data > 0) {
                        alert("成功");
                        window.location.href="${ctx}/vehicleservice/contrace/finish?contrace_id="+contrace_id;
                    } else {
                        alert("失败");
                        return false;
                    }
                }
            })
        });

        $(".return_km").blur(function(){
            var return_km_this = $(this);
            var return_time = $(this).parent('td').prev('td').children("input").val();
            var return_km = $(this).val();
            if(return_time != null && "" != return_time && return_km != null && "" != return_km) {
                var use_begin=$.trim($('#use_begin').val());
                var use_end=$.trim($('#use_end').val());
                var daily_available_km=$.trim($('#daily_available_km').val());
                var over_km_price=$.trim($('#over_km_price').val());
                var over_hour_price=$.trim($('#over_hour_price').val());
                var vehicle_id = $(this).next("input").val();

                $.ajax({
                    url:"${ctx}/vehicleservice/contrace/calculateovertime",
                    type: "post",
                    data:{use_begin:use_begin,use_end:use_end,return_time:return_time,return_km:return_km,daily_available_km:daily_available_km,over_km_price:over_km_price,over_hour_price:over_hour_price,vehicle_id:vehicle_id},
                    success:function(data){
                        console.info(data);
                        var d=eval(data);//解析
                        console.info(data);
                        $(d).each(function(index,entity){
                            return_km_this.parent('td').next('td').children("input").val(entity['all_alg_money']);
//                            $("#current_city").append($('<option value="'+entity['city_id']+'">'+entity['city_name']+'</option>'));//后台数据加到下拉框
                        });

                    }
                })
            }
        });

        $("#dofinish").click(function(){
            var contrace_id=$.trim($('#contrace_id').val());
            var system_total_price=$.trim($('#system_total_price').val());
            var arrange_price=$.trim($('#arrange_price').val());
            var actual_price=$.trim($('#actual_price').val());
            var late_fee=$.trim($('#late_fee').val());

            $.ajax({
                url:"${ctx}/vehicleservice/contrace/dofinish",
                type: "post",
                data:{contrace_id:contrace_id,system_total_price:system_total_price,arrange_price:arrange_price,actual_price:actual_price,late_fee:late_fee},
                success:function(data){
                    if(data > 0) {
                        alert("成功");
                        window.location.href="${ctx}/vehicleservice/contrace/index";
                    } else {
                        alert("失败");
                        return false;
                    }
                }
            })
        });


    });
</script>