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
                    <td class="tableleft">原密码</td>
                    <td><input type="old_password" name="old_password" placeholder="必填" required/></td>
                </tr>
                <tr>
                    <td class="tableleft">新密码</td>
                    <td><input type="text" name="new_password" id="new_password" placeholder="必填" required/></td>
                </tr>
                <tr>
                    <td class="tableleft">确认新密码</td>
                    <td><input type="text" name="confirm_password" id="confirm_password" placeholder="必填" required/></td>
                </tr>
                <tr>
                    <td class="tableleft"></td>
                    <td>
                        <button type="button" class="btn btn-primary" name="save" id="save">保存</button>&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
<script>
    $(function () {       
        $('#save').click(function(){
            var old_password = $('#old_password').val();
            var new_password = $('#new_password').val();
            var confirm_password = $('#confirm_password').val();

            if(old_password == null || '' == old_password) {
                alert("请输入原密码");
            }
            if(new_password == null || '' == new_password) {
                alert("请输入新密码");
            }
            if(confirm_password == null || '' == confirm_password) {
                alert("请输入确认密码");
            }

            if(new_password != new_password) {
                alert("请输入相同新密码！");
                return false;
            }
            $.ajax({
                type:'POST',
                url:'${ctx}/people/people/domodify',
                data:{old_password:old_password,new_password:new_password,confirm_password:confirm_password},
                success: function(data){
                    if(data == -1) {
                        alert("原密码与当前密码不同");
                        return false;
                    }
                    if(data == -2) {
                        alert("新密码与确认密码不同");
                        return false;
                    }
                    if(data > 0) {
                        alert("成功！");
                        location.reload();
                    } else {
                        alert("失败！");
                        return false;
                    }
                }
            });
        });

    });
</script>