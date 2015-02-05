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
            <input type="hidden" name="edited_user_id" id="edited_user_id" value="${edited_user.user_id}" />
            <table class="table table-bordered table-hover definewidth m10">
                <tr>
                    <td width="10%" class="tableleft">登录名</td>
                    <td><input type="text" name="username" value="${edited_user.login_name}" readonly/></td>
                </tr>
                <tr>
                    <td class="tableleft">密码</td>
                    <td><input type="password" name="password" value="${edited_user.login_pwd}" readonly/></td>
                </tr>
                <tr>
                    <td class="tableleft">真实姓名</td>
                    <td><input type="text" name="user_name" id="user_name" value="${edited_user.user_name}"/></td>
                </tr>
                <tr>
                    <td class="tableleft"></td>
                    <td>
                        <button type="button" class="btn btn-primary" name="save" id="save">保存</button>&nbsp;&nbsp;
                        <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
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
            var edited_user_id = $('#edited_user_id').val();
            var user_name = $('#user_name').val();
            alert(edited_user_id);
            alert(user_name);


            $.ajax({
                type:'POST',
                url:'${ctx}/people/people/doedit',
                data:{edited_user_id:edited_user_id,user_name:user_name},
                dataType:'text',
                success: function(data){
                    if(data == 1) {
                        alert("成功！");
                    } else {
                        alert("失败！");
                    }
                }
            });
        });

    });
</script>