<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <%--<script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>--%>
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery-1.7.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-typeahead.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery.validate.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/validation-init.js" />"></script>

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
    <%--<form class="definewidth m20">--%>
    <form class="cmxform form-horizontal">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft" colspan="6">车辆基本信息</td>
            </tr>
            <tr>
                <td class="tableleft">所属门店</td>
                <td colspan="5">
                    <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                        <c:if test="${vehicle_info.original_org == org.org_id}">${org.org_name}</c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td class="tableleft">品牌</td>
                <td>${vehicle_info.brand}</td>
                <td class="tableleft">车型</td>
                <td>${vehicle_info.model}</td>
                <td class="tableleft">颜色</td>
                <td>${vehicle_info.color}</td>
            </tr>
            <tr>
                <td class="tableleft">车牌号</td>
                <td>${vehicle_info.license_plate}</td>
                <td class="tableleft">车架号</td>
                <td>${vehicle_info.carframe_no}</td>
                <td class="tableleft">发动机号</td>
                <td>${vehicle_info.engine_no}</td>
            </tr>
            <tr>
                <td class="tableleft">公里数</td>
                <td>${vehicle_info.km}</td>
                <td class="tableleft">存货编码</td>
                <td>${vehicle_info.inventory_no}</td>
                <td class="tableleft">检验单</td>
                <td>${vehicle_info.check_list}</td>
            </tr>
            <tr>
                <td class="tableleft">登记证书</td>
                <td>${vehicle_info.registry_certificate}</td>
                <td class="tableleft">登记证书去向</td>
                <td>${vehicle_info.certificate_direction}</td>
                <td class="tableleft">关单/合格/一致性证书</td>
                <td>${vehicle_info.consistency_cer}</td>
            </tr>
            <tr>
                <td class="tableleft">完税证明/小本</td>
                <td>${vehicle_info.duty_paid_proof}</td>
                <td class="tableleft">记录</td>
                <td>${vehicle_info.record}</td>
                <td class="tableleft">备注</td>
                <td>${vehicle_info.remark}</td>
            </tr>
        </table>
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft" colspan="6">车辆购买信息</td>
            </tr>
            <tr>
                <td class="tableleft">购买日期</td>
                <td>${vehicle_info.buy_at}</td>
                <td class="tableleft">上牌登记日期</td>
                <td>${vehicle_info.card_at}</td>
                <td class="tableleft">市场指导价</td>
                <td>${vehicle_info.guide_price}</td>
            </tr>
            <tr>
                <td class="tableleft">供应商名称</td>
                <td>${vehicle_info.supplier}</td>
                <td class="tableleft">年审日期</td>
                <td>${vehicle_info.limited_at}</td>
                <td class="tableleft">车购价</td>
                <td>${vehicle_info.vehicle_price}</td>
            </tr>
            <tr>
                <td class="tableleft">融资租赁公司</td>
                <td>${vehicle_info.financing_rent_company}</td>
                <td class="tableleft">融资租赁总价</td>
                <td colspan="3">${vehicle_info.financing_rent_price}</td>
            </tr>
            <tr>
                <td class="tableleft">保证金</td>
                <td>${vehicle_info.bail}</td>
                <td class="tableleft">月付款</td>
                <td colspan="3">${vehicle_info.monthly_payment}</td>
            </tr>
        </table>
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft" colspan="6">车辆保险信息</td>
            </tr>
            <tr>
                <td class="tableleft">交强险</td>
                <td>${vehicle_info.strong_insurance}</td>
                <td class="tableleft">交强险到期日期</td>
                <td>${vehicle_info.strong_insurance_expire_at}</td>
                <td class="tableleft">车辆租赁状态</td>
                <td>${vehicle_info.lease_status}</td>
            </tr>
            <tr>
                <td class="tableleft">商业险</td>
                <td>${vehicle_info.business_insurance}</td>
                <td class="tableleft">商业险到期日期</td>
                <td>${vehicle_info.business_insurance_expire_at}</td>
                <td class="tableleft">贷款银行</td>
                <td>${vehicle_info.loan_bank}</td>
            </tr>
            <tr>
                <td class="tableleft">车购税</td>
                <td>${vehicle_info.vehicle_tax}</td>
                <td class="tableleft">车船税</td>
                <td>${vehicle_info.vehicle_vessel_tax}</td>
                <td class="tableleft">保险公司</td>
                <td>${vehicle_info.insurance_company}</td>
            </tr>
        </table>
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft" colspan="6">车辆使用情况</td>
            </tr>
            <tr>
                <td class="tableleft">保养剩余公里数</td>
                <td>${vehicle_info.maintian_on_km}</td>
                <td class="tableleft">下次保养公里数</td>
                <td>${vehicle_info.next_main_km}</td>
                <td class="tableleft">是否有违章待处理</td>
                <td>
                    <c:if test="${vehicle_info.peccancy_status == 0}">无</c:if>
                    <c:if test="${vehicle_info.peccancy_status == 1}">有</c:if>
                </td>
            </tr>
            <tr>
                <td class="tableleft">GPS状态</td>
                <td>${vehicle_info.gps}</td>
                <td class="tableleft">当前所在城市</td>
                <td>
                    <c:forEach var="city" items="${city_list}" varStatus="status">
                        <c:if test="${vehicle_info.current_city == city.city_id}">${city.city_name}</c:if>
                    </c:forEach>
                </td>
                <td class="tableleft">当前所在门店</td>
                <td>
                    <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                        <c:if test="${vehicle_info.current_shop == org.org_id}">
                            <c:if test="${org.org_type > 12}">
                                ${org.org_city_name} ${org.org_name}
                            </c:if>
                            <c:if test="${org.org_type < 13}">
                                ${org.org_name}
                            </c:if>
                        </c:if>
                    </c:forEach>

                </td>
            </tr>
        </table>
    </form>
</body>
</html>
