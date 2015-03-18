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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/bootstrap-fileupload.css" />" />

    <%--<script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>--%>
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery-1.7.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-fileupload.js" />"></script>

 

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
    <%--<form class="cmxform form-horizontal">--%>
    <form class="cmxform form-horizontal" action="${ctx}/customer/info/domodify" id="uploadannex" enctype="multipart/form-data" method="post">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft">姓名</td>
                <td><input type="text" name="customer_name" id="customer_name" required="true" placeholder="必填" value="${customer_info.customer_name}"/></td>
                <td class="tableleft">手机号</td>
                <td><input type="text" name="customer_dn" id="customer_dn" required="true" placeholder="必填" value="${customer_info.customer_dn}"/></td>
                <td class="tableleft">会员号</td>
                <td><input type="text" name="vip_no" id="vip_no" value="${customer_info.vip_no}" /></td>
            </tr>
            <tr>
                <td class="tableleft">客户类型</td>
                <td>
                    <select id="customer_type" name="customer_type">
                        <option value="个人用户" <c:if test="${customer_info.customer_type == '个人用户'}">selected="selected"</c:if>>个人用户</option>
                        <option value="企业用户" <c:if test="${customer_info.customer_type == '企业用户'}">selected="selected"</c:if>>企业用户</option>
                    </select>
                </td>
                <td class="tableleft">证件类型</td>
                <td>
                    <select id="certificate_type" name="certificate_type">
                        <option value="身份证" <c:if test="${customer_info.certificate_type == '身份证'}">selected="selected"</c:if>>身份证</option>
                        <option value="国际护照" <c:if test="${customer_info.certificate_type == '国际护照'}">selected="selected"</c:if>>国际护照</option>
                        <option value="回乡证" <c:if test="${customer_info.certificate_type == '回乡证'}">selected="selected"</c:if>>回乡证</option>
                        <option value="台胞证" <c:if test="${customer_info.certificate_type == '台胞证'}">selected="selected"</c:if>>台胞证</option>
                        <option value="其他" <c:if test="${customer_info.certificate_type == '其他'}">selected="selected"</c:if>>其他</option>
                    </select>
                </td>
                <td class="tableleft">证件号码</td>
                <td><input type="text" name="certificate_no" id="certificate_no" required="true" placeholder="必填" value="${customer_info.certificate_no}"/></td>
            </tr>
            <tr>
                <td class="tableleft">客户房产</td>
                <td><input type="text" name="customer_house" id="customer_house" value="${customer_info.customer_house}" /></td>
                <td class="tableleft">客户车辆</td>
                <td><input type="text" name="customer_vehicle" id="customer_vehicle" value="${customer_info.customer_vehicle}" /></td>
                <td class="tableleft">客户担保人/单位</td>
                <td><input type="text" name="customer_guarantee" id="customer_guarantee" value="${customer_info.customer_guarantee}" /></td>
            </tr>
            <tr>
                <td class="tableleft">证件图片</td>
                <td colspan="5">
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                            <c:if test="${customer_info.certificate_url != null || '' != customer_info.certificate_url}">
                                <img src="${ctx}${customer_info.certificate_url}" alt="${customer_info.certificate_name}" />
                            </c:if>
                            <c:if test="${customer_info.certificate_url == null && '' == customer_info.certificate_url}">
                                <img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt="" />
                            </c:if>
                        </div>
                        <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                        <div>
                                                   <span class="btn btn-white btn-file">
                                                   <span class="fileupload-new"><i class="fa fa-paper-clip"></i>选择图片</span>
                                                   <span class="fileupload-exists"><i class="fa fa-undo"></i>更换</span>
                                                   <input type="file" class="default" name="files" />
                                                   </span>
                            <a href="#" class="btn btn-danger fileupload-exists" data-dismiss="fileupload"><i class="fa fa-trash"></i>删除</a>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td colspan="5">
                    <button type="submit" class="btn btn-primary" id="save">保存</button> &nbsp;&nbsp;
                    <button type="button" class="btn btn-success" id="backid">返回列表</button>
                    <input type="hidden" id="customer_id" name="customer_id" value="${customer_info.id}">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
<script>
    $(function () {
		$('#backid').click(function(){
            window.location.href="${ctx}/customer/info/index";
		});

        <%--$('#save').click(function(){--%>
            <%--var customer_id=$.trim($('#customer_id').val());--%>
            <%--var certificate_type=$.trim($('#certificate_type').val());--%>
            <%--var certificate_no=$.trim($('#certificate_no').val());--%>
            <%--var customer_name=$.trim($('#customer_name').val());--%>
            <%--var customer_dn=$.trim($('#customer_dn').val());--%>
            <%--var customer_email=$.trim($('#customer_email').val());--%>
            <%--var customer_type=$.trim($('#customer_type').val());--%>
            <%--var customer_house=$.trim($('#customer_house').val());--%>
            <%--var customer_vehicle=$.trim($('#customer_vehicle').val());--%>
            <%--var customer_guarantee=$.trim($('#customer_guarantee').val());--%>
            <%--var vip_no = $.trim($('#vip_no').val());--%>

            <%--$.ajax({--%>
                <%--url:"${ctx}/customer/info/domodify",--%>
                <%--type: "post",--%>
                <%--data:{id:customer_id,certificate_type:certificate_type,certificate_no:certificate_no,customer_name:customer_name,customer_dn:customer_dn,--%>
                    <%--customer_email:customer_email,customer_type:customer_type,customer_house:customer_house,--%>
                    <%--customer_vehicle:customer_vehicle,customer_guarantee:customer_guarantee,vip_no:vip_no},--%>
                <%--success:function(data){--%>
                    <%--if(data == 1){--%>
                        <%--alert("成功");--%>
                        <%--location.reload();--%>
                    <%--} else if(data = -1) {--%>
                        <%--alert("证件号码重复");--%>
                        <%--return false;--%>
                    <%--} else {--%>
                        <%--alert("失败");--%>
                        <%--return false;--%>
                    <%--}--%>
                <%--}--%>
            <%--})--%>
        <%--})--%>
    });
</script>