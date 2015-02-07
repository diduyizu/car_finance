<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jstl_contexpath.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap-responsive.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/style.css" />" />
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>

    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>

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
<form class="form-inline definewidth m20" action="${ctx}/vehicle/register/index" method="post">
    门店：
    <select id="original_org">
        <c:forEach var="user_role" items="${user_role_list}" varStatus="status">
            <c:if test="${user_role.org_id == original_org}">
                <option value="${user_role.org_id}" selected="selected">${user_role.org_name}</option>
            </c:if>
            <c:if test="${user_role.org_id != original_org}">
                <option value="${user_role.org_id}">${user_role.org_name}</option>
            </c:if>
        </c:forEach>
    </select>
    品牌：
    <input type="text" name="brand" id="brand"class="abc input-default" placeholder="" value="${brand}">&nbsp;&nbsp;
    车架号：
    <input type="text" name="carframe_no" id="carframe_no"class="abc input-default" placeholder="" value="${carframe_no}">&nbsp;&nbsp;
    发动机号：
    <input type="text" name="engine_no" id="engine_no"class="abc input-default" placeholder="" value="${engine_no}">&nbsp;&nbsp;
    车牌号：
    <input type="text" name="license_plate" id="license_plate"class="abc input-default" placeholder="" value="${license_plate}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
    <button type="button" class="btn btn-success" id="addnew">新增车辆</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>档案编号</th>
            <th>存货编码</th>
            <th>品牌</th>
            <th>车型</th>
            <th>颜色</th>
            <th>车架号</th>
            <th>发动机号</th>
            <th>登记证书</th>
            <th>登记证书去向</th>
            <th>贷款银行</th>
            <th>关单/合格/一致性证书</th>
            <th>检验单</th>
            <th>完税证明/小本</th>
            <th>记录</th>
            <th>购买日期</th>
            <th>供应商名称</th>
            <th>车牌号</th>
            <th>上牌登记日期</th>
            <th>年审日期</th>
            <th>市场指导价</th>
            <th>车购价</th>
            <th>车购税</th>
            <th>保险公司</th>
            <th>交强险</th>
            <th>车船税</th>
            <th>交强险到期日期</th>
            <th>商业险</th>
            <th>商业险到期日期</th>
            <th>备注</th>
        </tr>
    </thead>
    <c:forEach var="vehicle" items="${vehicle_list}" varStatus="status">
        <tr>
            <td>${vehicle.archive_no}</td>
            <td>${vehicle.inventory_no}</td>
            <td>${vehicle.brand}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.color}</td>
            <td>${vehicle.carframe_no}</td>
            <td>${vehicle.engine_no}</td>
            <td>${vehicle.registry_certificate}</td>
            <td>${vehicle.certificate_direction}</td>
            <td>${vehicle.loan_bank}</td>
            <td>${vehicle.consistency_cer}</td>
            <td>${vehicle.check_list}</td>
            <td>${vehicle.duty_paid_proof}</td>
            <td>${vehicle.record}</td>
            <td>${vehicle.buy_at}</td>
            <td>${vehicle.supplier}</td>
            <td>${vehicle.license_plate}</td>
            <td>${vehicle.card_at}</td>
            <td>${vehicle.limited_at}</td>
            <td>${vehicle.guide_price}</td>
            <td>${vehicle.vehicle_price}</td>
            <td>${vehicle.vehicle_tax}</td>
            <td>${vehicle.insurance_company}</td>
            <td>${vehicle.strong_insurance}</td>
            <td>${vehicle.vehicle_vessel_tax}</td>
            <td>${vehicle.strong_insurance_expire_at}</td>
            <td>${vehicle.business_insurance}</td>
            <td>${vehicle.business_insurance_expire_at}</td>
            <td>${vehicle.remark}</td>
        </tr>
    </c:forEach>
    </tr>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $('#addnew').click(function(){
        window.location.href="${ctx}/vehicle/register/add";
    });
</script>