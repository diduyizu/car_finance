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
                <td class="tableleft">省份</td>
                <td>
                    <select id="province">
                        <option value="0">---请选择---</option>
                        <c:forEach var="province" items="${province_list}" varStatus="status">
                            <option value="${province.enum_value}">${province.enum_desc}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tableleft">地市</td>
                <td>
                    <select id="city">
                        <option value="0">---请选择---</option>
                    </select>
                </td>
            </tr>
            <%--<tr>--%>
                <%--<td class="tableleft">区县</td>--%>
                <%--<td>--%>
                    <%--<select id="country">--%>
                        <%--<option value="0">---请选择---</option>--%>
                    <%--</select>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <td class="tableleft">门店类型</td>
                <td>
                    <%--<select id="store_type">--%>
                        <%--<c:forEach var="org_type" items="${org_type_list}" varStatus="status">--%>
                            <%--<c:if test="${org_type.enum_value > 12}">--%>
                                <%--<option value="${org_type.enum_value}">${org_type.enum_desc}</option>--%>
                            <%--</c:if>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                    <select id="store_type">
                        <option value="0">---请选择---</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tableleft">门店名称</td>
                <td><input type="text" name="store_name" id="store_name" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">门店地址</td>
                <td><input type="text" name="store_address" id="store_address" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td>
                    <button type="button" class="btn btn-primary" id="save">保存</button>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
<script>
    $(function () {
        $('#province').change(function(){
            var province_id = $(this).children('option:selected').val();
            $.ajax({
                url:"${ctx}/store/add/provincecitylist",
                type: "get",
                data:{province_id:province_id},
                dataType:"json",
                success:function(data){
                    var d=eval(data);//解析
//                    $("#city").append($('<option value="0">---请选择---</option>'));
                    $(d).each(function(index,entity){
                        $("#city").append($('<option value="'+entity['city_id']+'">'+entity['city_name']+'</option>'));//后台数据加到下拉框
                    });
                }
            })
        });

        $('#city').change(function(){
            var city_id = $(this).children('option:selected').val();
            $.ajax({
                url:"${ctx}/store/add/cityorg",
                type: "get",
                data:{city_id:city_id},
                dataType:"json",
                success:function(data){
                    $("#store_type").empty();
                    var d=eval(data);//解析
//                    $("#country").append($('<option value="0">---请选择---</option>'));
                    $(d).each(function(index,entity){
                        if(entity['city_have_org'] == 1) {
                            $("#store_type").append($('<option value="14">二类门店</option>')).append($('<option value="15">三类门店</option>'));
                        } else {
                            $("#store_type").append($('<option value="13">市公司（一类门店）</option>'));
                        }
                    });
                }
            })
        });

        $('#save').click(function(){
            var province_id = $('#province').val();
            var city_id = $('#city').val();
            var country_id = $('#country').val();
            var store_type = $('#store_type').val();
            var store_name = $.trim($('#store_name').val());
            var store_address = $.trim($('#store_address').val());

            if(province_id == 0) {
                alert("请选择省份");
                return false;
            }
            if(city_id == 0) {
                alert("请选择地市");
                return false;
            }
            if(country_id == 0) {
                alert("请选择区县");
                return false;
            }
            if(store_name == '') {
                alert("请输入门店名称");
                return false;
            }

            $.ajax({
                url:"${ctx}/store/add/doadd",
                type: "post",
                data:{province_id:province_id,city_id:city_id,country_id:country_id,store_type:store_type,store_name:store_name,store_address:store_address},
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