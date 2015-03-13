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
                <td width="15%" class="tableleft">角色名称</td>
                <td>
                    <select id="role">
                        <c:forEach var="role" items="${roleList}" varStatus="status">
                            <c:if test="${role.role_id == choose_role_id}">
                                <option value="${role.role_id}" selected="selected">${role.role_name}</option>
                            </c:if>
                            <c:if test="${role.role_id != choose_role_id}">
                                <option value="${role.role_id}">${role.role_name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tableleft">权限</td>
                <td>
                    <ul>
                        <c:forEach var="top_menu" items="${top_menu_list}" varStatus="status">
                            <li>
                                <label class='checkbox inline'>
                                    <%--<input type='checkbox' name='group[]' value='${top_menu.menu_id}' />${top_menu.menu_name}--%>
                                    ${top_menu.menu_name}
                                </label>
                                <ul>
                                    <c:forEach var="sub_menu" items="${sub_menu_list}" varStatus="status">
                                        <c:if test="${top_menu.menu_id == sub_menu.pid}">
                                            <li>
                                                <label class='checkbox inline'>
                                                    <c:if test="${sub_menu.role_menu_status == 1}">
                                                        <input type='checkbox' name='sub_menu' value='${sub_menu.menu_id}' checked="true"/>${sub_menu.menu_name}
                                                    </c:if>
                                                    <c:if test="${sub_menu.role_menu_status != 1}">
                                                        <input type='checkbox' name='sub_menu' value='${sub_menu.menu_id}' />${sub_menu.menu_name}
                                                    </c:if>
                                                </label>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td>
                    <button type="button" class="btn btn-primary" name="save" id="save">保存</button> &nbsp;&nbsp;
                </td>
            </tr>
        </table>
    </form>
</body>
</html>

<script>
    $(function () {
        $('#role').change(function(){
            var choose_role_id = $(this).children('option:selected').val();
            window.location.href="${ctx}/people/rolemenu/index?choose_role_id="+choose_role_id;
        });

        $('#save').click(function(){
            var sub_menu_id='';
            $('input[name="sub_menu"]:checked').each(function(){
                sub_menu_id+=$(this).val()+',';
            });
            var role_id = $('#role').val();

            $.ajax({
                url:"${ctx}/people/rolemenu/doconfig",
                type: "post",
                data:{sub_menu_id:sub_menu_id,role_id:role_id},
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