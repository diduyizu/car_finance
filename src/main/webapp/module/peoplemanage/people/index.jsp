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
<form class="form-inline definewidth m20" action="${ctx}/people/people/index" method="post">
    <select id="choose_org_id" name="choose_org_id">
        <c:forEach var="org" items="${user_org_list}" varStatus="status">
            <c:if test="${org.org_id == choose_org_id}">
                <c:if test="${org.org_type > 12}">
                    <option value="${org.org_id}" selected="selected">${org.org_city_name} ${org.org_name}</option>
                </c:if>
                <c:if test="${org.org_type < 13}">
                    <option value="${org.org_id}" selected="selected">${org.org_name}</option>
                </c:if>
            </c:if>
            <c:if test="${org.org_id != choose_org_id}">
                <c:if test="${org.org_type > 12}">
                    <option value="${org.org_id}">${org.org_city_name} ${org.org_name}</option>
                </c:if>
                <c:if test="${org.org_type < 13}">
                    <option value="${org.org_id}">${org.org_name}</option>
                </c:if>
            </c:if>
        </c:forEach>
    </select>
    真实姓名：
    <input type="text" name="username" id="username"class="abc input-default" placeholder="" value="${user_name}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
    <button type="button" class="btn btn-success" id="addnew">新增用户</button>
    <%--<input type="hidden" id="choose_org_id" name="choose_org_id" value="${choose_org_id}">--%>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
        <tr>
            <th>用户id</th>
            <th>用户名称</th>
            <th>真实姓名</th>
            <th>登录名</th>
            <th>操作</th>
        </tr>
    </thead>
    <c:forEach var="org_user" items="${user_list}" varStatus="status">
        <tr>
            <td>${org_user.user_id}</td>
            <td>${org_user.nick_name}</td>
            <td>${org_user.user_name}</td>
            <td>${org_user.login_name}</td>
            <td>
                <button type="button" class="btn btn-primary modify" value="${org_user.user_id}" >编辑</button> &nbsp;&nbsp;
                <button type="button" class="btn btn-danger delete" value="${org_user.user_id}" >删除</button>
            </td>
        </tr>
    </c:forEach>
    </tr>
</table>
<%@ include file="/resources/page.jsp"%>
</body>
</html>
<script>
    $('#addnew').click(function(){
        var choose_org_id = $('#choose_org_id').val();
        window.location.href="${ctx}/people/people/add?org_id="+choose_org_id;
    });

    $('.modify').click(function(){
        var edited_user_id = $(this).val();
        window.location.href="${ctx}/people/people/edit?edited_user_id="+edited_user_id;
    })

    $('.delete').click(function(){
        if(confirm("确定要删除吗？")) {
            var edited_user_id = $(this).val();
            $.ajax({
                url:"${ctx}/people/people/dodelete",
                type: "post",
                data:{edited_user_id:edited_user_id},
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
</script>