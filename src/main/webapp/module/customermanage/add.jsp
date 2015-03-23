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
    <script type="text/javascript" src="<c:url value="/resources/Js/idcardValidate.js" />"></script>



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
    <form class="cmxform form-horizontal" action="${ctx}/customer/info/doadd" id="uploadannex" enctype="multipart/form-data" method="post">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td width="15%" class="tableleft">姓名</td>
                <td co><input type="text" name="customer_name" id="customer_name" placeholder="必填" required="true"/></td>
                <td class="tableleft">手机号</td>
                <td ><input type="text" name="customer_dn" id="customer_dn" placeholder="必填" required="true"/></td>
                <td class="tableleft">会员号</td>
                <td><input type="text" name="vip_no" id="vip_no" /></td>
            </tr>
            <tr>
                <td class="tableleft">客户类型</td>
                <td>
                    <select id="customer_type" name="customer_type">
                        <option value="个人用户">个人用户</option>
                        <option value="企业用户">企业用户</option>
                    </select>
                </td>
                <td class="tableleft">证件类型</td>
                <td>
                    <select id="certificate_type" name="certificate_type">
                        <option value="身份证">身份证</option>
                        <option value="国际护照">国际护照</option>
                        <option value="回乡证">回乡证</option>
                        <option value="台胞证">台胞证</option>
                        <option value="其他">其他</option>
                    </select>
                </td>
                <td class="tableleft">证件号码</td>
                <td ><input type="text" name="certificate_no" id="certificate_no" placeholder="必填" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">客户房产</td>
                <td><input type="text" name="customer_house" id="customer_house" /></td>
                <td class="tableleft">客户车辆</td>
                <td><input type="text" name="customer_vehicle" id="customer_vehicle"/></td>
                <td class="tableleft">客户担保人/单位</td>
                <td><input type="text" name="customer_guarantee" id="customer_guarantee"/></td>
            </tr>
            <tr>
                <td class="tableleft">证件图片</td>
                <td colspan="5">
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                            <img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt="" />
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
            window.location.href="${ctx}/customer/info/index";
		});

        $("#certificate_no").blur(function(){
            var certificate_type = $.trim($('#certificate_type').val());
            if(certificate_type == '身份证') {
                var certificate_no = $(this).val();
                var idcard_validate_result = IdCardValidate(certificate_no);
                if(!idcard_validate_result) {
                    alert("请输入正确的身份证号码");
                    return false;
                }
            }
        });

        $('#save').click(function(){
            var certificate_type = $.trim($('#certificate_type').val());
            var certificate_no = $.trim($('#certificate_no').val());
            if(certificate_type == '身份证') {
                var idcard_validate_result = IdCardValidate(certificate_no);
                if(!idcard_validate_result) {
                    alert("请输入正确的身份证号码");
                    return false;
                }
            }
            $("#uploadannex").submit();
        });

        <%--$('#save').click(function(){--%>
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
                <%--url:"${ctx}/customer/info/doadd",--%>
                <%--type: "post",--%>
                <%--data:{certificate_type:certificate_type,certificate_no:certificate_no,customer_name:customer_name,customer_dn:customer_dn,--%>
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