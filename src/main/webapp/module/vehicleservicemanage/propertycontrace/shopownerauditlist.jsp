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
<form class="form-inline definewidth m20" action="${ctx}/vehicleservice/contrace/property/shopowner/audit" method="post">
    门店：
    <select id="original_org" name="original_org">
        <%--<c:forEach var="org" items="${user_role_org_list}" varStatus="status">--%>
            <%--<c:if test="${org.org_id == original_org}">--%>
                <%--<option value="${org.org_id}" selected="selected">${org.org_name}</option>--%>
            <%--</c:if>--%>
            <%--<c:if test="${org.org_id != original_org}">--%>
                <%--<option value="${org.org_id}">${org.org_name}</option>--%>
            <%--</c:if>--%>
        <%--</c:forEach>--%>

        <c:forEach var="org" items="${user_role_org_list}" varStatus="status">
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
    </select>&nbsp;&nbsp;
    状态：
    <select id="status" name="status">
        <option value="-99">全部</option>
        <option value="1" <c:if test="${status == '1'}">selected="selected"</c:if>>待审核</option>
        <option value="2" <c:if test="${status == '2'}">selected="selected"</c:if>>店长审核通过</option>
        <option value="-1" <c:if test="${status == '-1'}">selected="selected"</c:if>>店长驳回</option>
        <option value="-2" <c:if test="${status == '-2'}">selected="selected"</c:if>>店长审核不通过</option>
    </select>&nbsp;&nbsp;
    合同类型：
    <select id="contrace_type" name="contrace_type">
        <option value="1" <c:if test="${contrace_type == '1'}">selected="selected"</c:if>>零租</option>
        <option value="2" <c:if test="${contrace_type == '2'}">selected="selected"</c:if>>产权租</option>
    </select>
    <%--姓名：--%>
    <%--<input type="text" name="customer_name" id="customer_name"class="abc input-default" placeholder="" value="${customer_name}">&nbsp;&nbsp;--%>
    <%--手机号码：--%>
    <%--<input type="text" name="dn" id="dn"class="abc input-default" placeholder="" value="${dn}">&nbsp;&nbsp;--%>
    <button type="submit" class="btn btn-primary">查询</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>合同编号</th>
            <th>客户姓名</th>
            <th>客户类型</th>
            <th>手机号码</th>
            <th>证件类型</th>
            <th>证件号码</th>
            <th>合同期限</th>
            <th>首付款</th>
            <th>合同租赁价格</th>
            <th>月付款</th>
            <th>协商月付</th>
            <th>月付款日</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
    </thead>
    <c:forEach var="contrace" items="${contrace_list}" varStatus="status">
        <tr>
            <td>
                <a href="${ctx}/vehicleservice/contrace/detail?contrace_id=${contrace.id}">${contrace.contrace_no}</a>
            </td>
            <td>
            <%--${contrace.customer_name}--%>
                <a href="${ctx}/vehicleservice/contrace/audit/tocustomerdetail?customer_cer_no=${contrace.customer_cer_no}">${contrace.customer_name}</a>
            </td>
            <td>${contrace.customer_type}</td>
            <td>${contrace.customer_dn}</td>
            <td>${contrace.customer_cer_type}</td>
            <td>${contrace.customer_cer_no}</td>
            <td>${contrace.period_number}</td>
            <td>${contrace.down_payment}</td>
            <td>${contrace.lease_price}</td>
            <td>${contrace.monthly_payment}</td>
            <td>${contrace.arrange_payment}</td>
            <td>${contrace.monthly_day}</td>
            <td>
                <c:if test="${contrace.status == 0}">初始状态</c:if>
                <c:if test="${contrace.status == 1}">待审核</c:if>
                <c:if test="${contrace.status == 2}">店长审核通过</c:if>
                <c:if test="${contrace.status == 3}">市公司店长审核通过</c:if>
                <c:if test="${contrace.status == 4}">区域经理审核通过</c:if>
                <c:if test="${contrace.status == 5}">财务通过</c:if>
                <c:if test="${contrace.status == 6}">完结</c:if>
                <c:if test="${contrace.status == -1}">店长驳回</c:if>
                <c:if test="${contrace.status == -2}">店长审核不通过</c:if>
                <c:if test="${contrace.status == -3}">市公司店长审核不通过</c:if>
                <c:if test="${contrace.status == -4}">区域经理审核不通过</c:if>
                <c:if test="${contrace.status == -5}">财务不通过</c:if>
            </td>
            <td>
                <c:if test="${contrace.status == 1}">
                    <button type="button" class="btn btn-success pass" value="${contrace.id}">通过</button>
                    <button type="button" class="btn btn-danger rewrite" value="${contrace.id}">驳回</button>
                    <button type="button" class="btn btn-danger nopass" value="${contrace.id}">不通过</button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $(function () {
        $('.pass').click(function(){
            var id = $(this).val();
            $.ajax({
                url:"${ctx}/vehicleservice/contrace/property/shopowner/doaudit",
                type: "post",
                data:{id:id,status:2},
                success:function(data){
                    if(data > 0){
                        alert("成功");
                        location.reload();
                    } else {
                        alert("失败");
                        return false;
                    }
                }
            })
        })

        $('.nopass').click(function(){
            if(confirm("确定不通过吗？")) {
                var id = $(this).val();
                $.ajax({
                    url:"${ctx}/vehicleservice/contrace/property/shopowner/doaudit",
                    type: "post",
                    data:{id:id,status:-2},
                    success:function(data){
                        if(data > 0){
                            alert("成功");
                            location.reload();
                        } else {
                            alert("失败");
                            return false;
                        }
                    }
                })
            }
        })

        $('.rewrite').click(function(){
            if(confirm("确定驳回吗？")) {
                var id = $(this).val();
                $.ajax({
                    url:"${ctx}/vehicleservice/contrace/property/shopowner/doaudit",
                    type: "post",
                    data:{id:id,status:-1},
                    success:function(data){
                        if(data > 0){
                            alert("成功");
                            location.reload();
                        } else {
                            alert("失败");
                            return false;
                        }
                    }
                })
            }
        })
    });

</script>