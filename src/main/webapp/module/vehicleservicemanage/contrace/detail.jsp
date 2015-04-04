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
    <form class="cmxform form-horizontal">
        <table class="table table-bordered table-hover definewidth m10">
            <thead>
            <tr>
                <th>车牌号</th>
                <th>车型</th>
                <th>当前里程</th>
                <th>还车时间</th>
                <th>还车里程</th>
                <th>超期费用</th>
                <th>是否有ETC</th>
                <th>ETC金额</th>
                <th>发车油量比(%)</th>
                <th>还车油量比(%)</th>
                <th>日租金</th>
                <th>结算方式</th>
                <th>一口价</th>
            </tr>
            </thead>
            <c:forEach var="vehicle" items="${vehicle_contrace_vehs_list}" varStatus="status">
                <tr>
                    <td>${vehicle.license_plate}</td>
                    <td>${vehicle.model}</td>
                    <td>
                        <c:if test="${vehicle.isother == 0}">${vehicle.km}</c:if>
                        <c:if test="${vehicle.isother == 1}">${vehicle.other_vehicle_km}</c:if>
                    </td>
                    <td>${vehicle.return_time}</td>
                    <td>${vehicle.return_km}</td>
                    <td>${vehicle.over_price}</td>
                    <td>${vehicle.etc}</td>
                    <td>${vehicle.etc_money}</td>
                    <td>${vehicle.oil_percent}</td>
                    <td>${vehicle.revert_oil_percent}</td>
                    <td>${vehicle.daily_price}</td>
                    <td>${vehicle.settlement_way}</td>
                    <td>${vehicle.fixed_price}</td>
                </tr>
            </c:forEach>
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
                <td class="tableleft">系统应收租金</td>
                <td>${vehicle_contrace_info.system_total_price}</td>
                <td class="tableleft">约定所得租金</td>
                <td colspan="3">${vehicle_contrace_info.arrange_price}</td>
            </tr>
            <tr>
                <td class="tableleft">实际所得租金</td>
                <td>${vehicle_contrace_info.actual_price}</td>
                <td class="tableleft">滞纳金</td>
                <td colspan="3">${vehicle_contrace_info.late_fee}</td>
            </tr>
            <tr>
                <td class="tableleft">描述</td>
                <%--<td colspan="5"><textarea readonly id="remark" rows="4" style="margin: 0px 0px 10px; width: 766px; height: 140px;" >${vehicle_contrace_info.remark}</textarea></td>--%>
                <td colspan="3">${vehicle_contrace_info.remark}</td>
                <td class="tableleft" colspan="2">
                    <a href="${ctx}/vehicleservice/contrace/annex/detail?contrace_id=${vehicle_contrace_info.id}" target="_blank">附件</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>