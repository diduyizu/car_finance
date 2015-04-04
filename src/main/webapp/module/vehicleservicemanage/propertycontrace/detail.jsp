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
            </tr>
            </thead>
            <c:forEach var="vehicle" items="${vehicle_contrace_vehs_list}" varStatus="status">
                <tr>
                    <td>${vehicle.license_plate}</td>
                    <td>${vehicle.model}</td>
                    <td>${vehicle.km}</td>
                    <td>${vehicle.return_time}</td>
                    <td>${vehicle.return_km}</td>
                    <td>${vehicle.over_price}</td>

                    <td>${vehicle.etc}</td>
                    <td>${vehicle.etc_money}</td>
                    <td>${vehicle.oil_percent}</td>
                    <td>${vehicle.revert_oil_percent}</td>
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
                        <c:if test="${org.org_id == property_contrace_info.org_id}">
                            ${org.org_name}
                        </c:if>
                    </c:forEach>
                </td>
                <td class="tableleft">合同编号</td>
                <td>${property_contrace_info.contrace_no}</td>
                <td class="tableleft">合同类型</td>
                <td>产权租</td>
            </tr>
            <tr>
                <td class="tableleft">客户姓名</td>
                <td>${property_contrace_info.customer_name}</td>
                <td class="tableleft">客户类型</td>
                <td>${property_contrace_info.customer_type}</td>
                <td class="tableleft">客户手机</td>
                <td>${property_contrace_info.customer_dn}</td>
            </tr>
            <tr>
                <td class="tableleft">证件类型</td>
                <td>${property_contrace_info.customer_cer_type}</td>
                <td class="tableleft">证件号码</td>
                <td colspan="3">${property_contrace_info.customer_cer_no}</td>
            </tr>

            <tr>
                <td class="tableleft">签订日期</td>
                <td>${property_contrace_info.sign_at}</td>
                <td class="tableleft">合同期限</td>
                <td>${property_contrace_info.period_number}</td>
                <td class="tableleft">首付款</td>
                <td>${property_contrace_info.down_payment}</td>
            </tr>
            <tr>
                <td class="tableleft">合同租赁价格</td>
                <td>${property_contrace_info.lease_price}</td>
                <td class="tableleft">月付款</td>
                <td>${property_contrace_info.monthly_payment}</td>
                <td class="tableleft">协商月付</td>
                <td>${property_contrace_info.arrange_payment}</td>
            </tr>
            <tr>
                <td class="tableleft">月付款日</td>
                <td>${property_contrace_info.monthly_day}</td>
                <td class="tableleft">尾款</td>
                <td>${property_contrace_info.final_payment}</td>
                <td class="tableleft">付款方式</td>
                <td>${property_contrace_info.payment_type}</td>
            </tr>
            <tr>
                <td class="tableleft">已收回期数</td>
                <td>${property_contrace_info.received_periods}</td>
                <td class="tableleft">已收回金额</td>
                <td colspan="3">${property_contrace_info.already_back_amount}</td>
            </tr>

            <tr>
                <td class="tableleft">业务员id</td>
                <td>${property_contrace_info.employee_id}</td>
                <td class="tableleft">业务员姓名</td>
                <td colspan="3">${property_contrace_info.employee_name}</td>
            </tr>
            <tr>
                <td class="tableleft">描述</td>
                <%--<td colspan="5"><textarea readonly id="remark" rows="4" style="margin: 0px 0px 10px; width: 766px; height: 140px;" >${vehicle_contrace_info.remark}</textarea></td>--%>
                <td colspan="4">${property_contrace_info.remark}</td>
                <td colspan="5">
                    <input type="hidden" id="contrace_id" value="${property_contrace_info.id}"/>
                    <a href="${ctx}/vehicleservice/contrace/property/paymentdetail?contrace_id=${property_contrace_info.id}" target="_blank">还款明细</a>
                    <a href="${ctx}/vehicleservice/contrace/annex/detail?contrace_id=${property_contrace_info.id}" target="_blank">附件</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>