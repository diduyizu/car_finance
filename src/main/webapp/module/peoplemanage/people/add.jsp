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
    <form class="definewidth m20">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td width="10%" class="tableleft">登录名</td>
                <td><input type="text" name="login_name" id="login_name" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">密码</td>
                <td><input type="password" name="login_pwd" id="login_pwd" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">真实姓名</td>
                <td><input type="text" name="user_name" id="user_name" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">员工工号/id</td>
                <td><input type="text" name="employee_id" id="employee_id" value=""/></td>
            </tr>
            <tr>
                <td class="tableleft">昵称</td>
                <td><input type="text" name="nice_name" id="nice_name"/></td>
            </tr>
            <tr>
                <td class="tableleft">门店名称</td>
                <td>
                    <%--<input type="text" name="org_name" id="org_name" value="${org.org_name}" readonly required="true"/>--%>
                    <%--<input type="hidden" name="org_id" id="org_id" value="${org.org_id}" required="true"/>--%>
                    <select id="org_id" name="org_id">
                        <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                            <option value="${org.org_id}">${org.org_name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tableleft">角色</td>
                <td>
                    <select id="role_id">
                        <c:forEach var="role" items="${role_list}" varStatus="status">
                            <option value="${role.role_id}">${role.role_name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td>
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
		$('#backid').click(function(){
            window.location.href="${ctx}/people/people/index";
		});

        $('#save').click(function(){
            var login_name = $.trim($('#login_name').val());
            var login_pwd = $.trim($('#login_pwd').val());
            var user_name = $.trim($('#user_name').val());
            var employee_id = $.trim($('#employee_id').val());
            var nice_name = $.trim($('#nice_name').val());
            var org_id = $('#org_id').val();
            var role_id = $('#role_id').val();

            if(login_name == '') {
                alert("请输入登录名");
                return false;
            }
            if(login_pwd == '') {
                alert("请输入密码");
                return false;
            }
            if(user_name == '') {
                alert("请输入真实姓名");
                return false;
            }

            $.ajax({
                url:"${ctx}/people/people/doadd",
                type: "post",
                data:{login_name:login_name,login_pwd:login_pwd,user_name:user_name,nick_name:nice_name,org_id:org_id,role_id:role_id,employee_id:employee_id},
//                dataType:"json",
                success:function(data){
                    if(data == 1){
                        alert("成功");
                        location.reload();
                    } else {
                        alert("失败");
                    }
                }
            })
        })
    });
</script>