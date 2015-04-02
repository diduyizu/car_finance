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
            var user_employee_id_name_json = ${user_employee_id_name_json};
            console.info(user_employee_id_name_json);
            $('#employee_id_name').typeahead({
                source: user_employee_id_name_json
            });
        })
    </script>
</head>
<body>
<form class="form-inline definewidth m20" action="${ctx}/statistics/achievement" method="post" id="form">
    <table>
        <tr>
            <td>
                门店：
                <select id="original_org" name="original_org">
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
                开始时间：
                <%--<input class="form_datetime" size="16" type="text" id="begin_date" name="begin_date" value="${begin_date}"  readonly>--%>
                <div class="input-append date" id="begin" data-date-format="yyyy-mm-dd">
                    <input class="span2" size="16" type="text" id="begin_date" name="begin_date" value="${begin_date}" readonly>
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
            </td>
            <td>
                结束时间：
                <%--<input class="form_datetime" size="16" type="text" id="end_date" name="end_date" value="${end_date}"  readonly>--%>
                <div class="input-append date" id="end" data-date-format="yyyy-mm-dd">
                    <input class="span2" size="16" type="text" id="end_date" name="end_date" value="${end_date}" readonly>
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
            </td>
            <td>
                业务员：
                <input type="hidden" name="employee_id" id="employee_id">
                <input type="text" name="employee_id_name" id="employee_id_name" value="${employee_id_name}" />
            </td>
            <td>
                <button type="button" class="btn btn-primary" id="query">查询</button>
            </td>
        </tr>
    </table>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>业务员工号</th>
            <th>业务员姓名</th>
            <th>总金额</th>
        </tr>
    </thead>
    <c:forEach var="achievement" items="${achievement_list}" varStatus="status">
        <tr>
            <td>${achievement.employee_id}</td>
            <td>${achievement.employee_name}</td>
            <td>${achievement.total_actually}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $(function () {
        window.prettyPrint && prettyPrint();
        $('#begin').datepicker();
        $('#end').datepicker();

        $('#query').click(function(){
            var employee_id_name = $.trim($('#employee_id_name').val());
            var employee_id = employee_id_name.split("|")[0];
            $('#employee_id').val(employee_id);

            $('#form').submit();
        })
    });
</script>