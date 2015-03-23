<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jstl_contexpath.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap-responsive.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/style.css" />" />
    <%--<script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>--%>

    <script type="text/javascript" src="<c:url value="/resources/Js/jquery-1.7.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
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
            var vehicle_brand_json = ${vehicle_brand_json};
            $('#brand').typeahead({
                source: vehicle_brand_json
            });

            var vehicle_model_json = ${vehicle_model_json};
            $('#model').typeahead({
                source: vehicle_model_json
            });

            var vehicle_licene_plate_json = ${vehicle_licene_plate_json};
            $('#license_plate').typeahead({
                source: vehicle_licene_plate_json
            });
        })
    </script>
</head>
<body>
<form class="form-inline definewidth m20" action="${ctx}/vehicle/register/index" method="post">
    <table>
        <tr>
            <td>
                 当前所在门店：
                <select id="original_org" name="original_org">
                    <%--<c:forEach var="org" items="${user_all_org_list}" varStatus="status">--%>
                        <%--<c:if test="${org.org_id == original_org}">--%>
                            <%--<option value="${org.org_id}" selected="selected">${org.org_name}</option>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${org.org_id != original_org}">--%>
                            <%--<option value="${org.org_id}">${org.org_name}</option>--%>
                        <%--</c:if>--%>
                    <%--</c:forEach>--%>
                    <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                        <c:if test="${org.org_id == original_org}">
                            <c:if test="${org.org_type > 12}">
                                <option value="${org.org_id}" selected="selected">${org.org_city_name} ${org.org_name}</option>
                            </c:if>
                            <c:if test="${org.org_type < 13}">
                                <option value="${org.org_id}" selected="selected">${org.org_name}</option>
                            </c:if>
                        </c:if>
                        <c:if test="${org.org_id != original_org}">
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
            <td>
                当前所在城市：
                <select id="current_city" name="current_city">
                    <option value="">全部</option>
                    <c:forEach var="city" items="${sys_used_city_list}" varStatus="status">
                        <c:if test="${city.city_id == current_city}">
                            <option value="${city.city_id}" selected="selected">${city.city_name}</option>
                        </c:if>
                        <c:if test="${city.city_id != current_city}">
                            <option value="${city.city_id}">${city.city_name}</option>
                        </c:if>
                    </c:forEach>
                </select>&nbsp;&nbsp;
            </td>
            <td>
                车辆租赁状态：
                <select id="lease_status" name="lease_status">
                    <option value="">全部</option>
                    <option value="在库" <c:if test="${lease_status == '在库'}">selected="selected"</c:if>>在库</option>
                    <option value="零租" <c:if test="${lease_status == '零租'}">selected="selected"</c:if>>零租</option>
                    <option value="产权租" <c:if test="${lease_status == '产权租'}">selected="selected"</c:if>>产权租</option>
                    <option value="售出" <c:if test="${lease_status == '售出'}">selected="selected"</c:if>>售出</option>
                </select>&nbsp;&nbsp;
            </td>
            <td>
                GPS状态：
                <select id="gps" name="gps">
                    <option value="">全部</option>
                    <option value="正常" <c:if test="${gps == '正常'}">selected="selected"</c:if>>正常</option>
                    <option value="异常" <c:if test="${gps == '异常'}">selected="selected"</c:if>>异常</option>
                    <option value="未安装" <c:if test="${gps == '未安装'}">selected="selected"</c:if>>未安装</option>
                </select>&nbsp;&nbsp;
            </td>
            <td>
                <button type="button" class="btn btn-success" id="addnew">新增车辆</button>
            </td>
        </tr>
        <tr>
            <td>
                品牌：
                <input type="text" data-provide="typeahead" name="brand" id="brand" class="abc input-default" placeholder="" value="${brand}">&nbsp;&nbsp;
            </td>
            <td>
                车辆型号：
                <input type="text" data-provide="typeahead" name="model" id="model" class="abc input-default" placeholder="" value="${model}">&nbsp;&nbsp;
            </td>
            <td>
                车牌号：
                <input type="text" data-provide="typeahead" name="license_plate" id="license_plate" class="abc input-default" placeholder="" value="${license_plate}">&nbsp;&nbsp;
            </td>
            <td>
                颜色：
                <input type="text" data-provide="typeahead" name="color" id="color" class="abc input-default" placeholder="" value="${color}">&nbsp;&nbsp;
            </td>
            <td>
                <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
            </td>
        </tr>
    </table>
    <%--公里数：--%>
    <%--<input type="text" name="km_begin" id="km_begin"class="abc input-default" placeholder="" value="${km_begin}">到--%>
    <%--<input type="text" name="km_end" id="km_end"class="abc input-default" placeholder="" value="${km_end}">&nbsp;&nbsp;--%>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>车牌</th>
            <th>品牌</th>
            <th>车型</th>
            <%--<th>当前所在地市</th>--%>
            <th>当前所在门店</th>
            <th>车辆状态</th>
            <th>GPS状态</th>
            <th>操作</th>
            <%--<th>保养剩余公里数</th>--%>
            <%--<th>公里数</th>--%>
        </tr>
    </thead>
    <c:forEach var="vehicle" items="${vehicle_list}" varStatus="status">
        <tr>
            <td><a href="${ctx}/vehicle/info/detail?vehicle_id=${vehicle.id}">${vehicle.license_plate}</a></td>
            <td>${vehicle.brand}</td>
            <td>${vehicle.model}</td>
            <%--<td>${vehicle.current_city_name}</td>--%>
            <td>${vehicle.current_shop_name}</td>
            <td>${vehicle.lease_status}</td>
            <td>${vehicle.gps}</td>
            <td>
                <button type="button" class="btn btn-success modify" value="${vehicle.id}">修改</button>
                <button type="button" class="btn btn-success addinsurance" value="${vehicle.id}">新增保险</button>
                <button type="button" class="btn btn-success addpeccancy" value="${vehicle.id}">新增违章</button>
                <button type="button" class="btn btn-success addmaintainremind" value="${vehicle.id}">新增保养</button>
            </td>
            <%--<td>${vehicle.maintian_on_km}</td>--%>
            <%--<td>${vehicle.km}</td>--%>
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

    $('.addinsurance').click(function(){
        var vehicle_id = $(this).val();
        window.location.href="${ctx}/vehicle/insurance/add?vehicle_id="+vehicle_id;
    });

    $('.addpeccancy').click(function(){
        var vehicle_id = $(this).val();
        window.location.href="${ctx}/vehicle/peccancy/add?vehicle_id="+vehicle_id;
    });

    $('.addmaintainremind').click(function(){
        var vehicle_id = $(this).val();
        window.location.href="${ctx}/vehicle/maintainremind/add?vehicle_id="+vehicle_id;
    });

    $('.modify').click(function(){
        var vehicle_id = $(this).val();
        window.location.href="${ctx}/vehicle/vehicle/modify?vehicle_id="+vehicle_id;
    });


</script>