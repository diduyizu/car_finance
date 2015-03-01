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
                    <td class="tableleft">真实姓名</td>
                    <td>
                        <input type="text" name="username" value="${edited_user_name}"/>
                        <input type="hidden" name="edited_user_id" id="edited_user_id" value="${edited_user_id}" />
                    </td>
                </tr>
                <tr>
                    <td class="tableleft">所在组织</td>
                    <td>
                        <input type="text" name="org_name" value="${org_name}"/>
                        <input type="hidden" name="org_id" id="org_id" value="${org_id}" />
                    </td>
                </tr>
                <tr>
                    <td class="tableleft">用户角色</td>
                    <td>
                        <ul>
                            <c:forEach var="user_org_role" items="${user_org_role_list}" varStatus="status">
                                <li>
                                    <label class='checkbox inline'>
                                        <c:if test="${user_org_role.user_role_status != 0}">
                                            <input type='checkbox' name='user_org_role' value='${user_org_role.role_id}' checked="true"/>${user_org_role.role_name}
                                        </c:if>
                                        <c:if test="${user_org_role.user_role_status == 0}">
                                            <input type='checkbox' name='user_org_role' value='${user_org_role.role_id}' />${user_org_role.role_name}
                                        </c:if>
                                    </label>
                                </li>
                            </c:forEach>
                        </ul>
                    </td>
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
            window.location.href="${ctx}/people/peoplerole/index";
		});

        $('#save').click(function(){
            var edited_user_id = $('#edited_user_id').val();
            var org_id = $('#org_id').val();
            var role_id='';
            $('input[name="user_org_role"]:checked').each(function(){
                role_id+=$(this).val()+',';
            });

            $.ajax({
                type:'POST',
                url:'${ctx}/people/peoplerole/doedit',
                data:{edited_user_id:edited_user_id,org_id:org_id,role_id:role_id},
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