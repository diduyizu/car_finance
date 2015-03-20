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
    <form class="cmxform form-horizontal" action="${ctx}/customer/annex/upload" id="uploadannex" enctype="multipart/form-data" method="post">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft">身份证明</td>
                <td>
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                            <c:if test="${customer_info.identity_url != null && '' != customer_info.identity_url}">
                                <img src="${ctx}${customer_info.identity_url}" alt="${customer_info.identity_name}" />
                            </c:if>
                            <c:if test="${customer_info.identity_url == null || '' == customer_info.identity_url}">
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
                <td class="tableleft">房产证明</td>
                <td>
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                            <c:if test="${customer_info.house_property_url != null && '' != customer_info.house_property_url}">
                                <img src="${ctx}${customer_info.house_property_url}" alt="${customer_info.certificate_name}" />
                            </c:if>
                            <c:if test="${customer_info.house_property_url == null || '' == customer_info.house_property_url}">
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
                <td class="tableleft">车辆证明</td>
                <td>
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                            <c:if test="${customer_info.driving_license_url != null && '' != customer_info.driving_license_url}">
                                <img src="${ctx}${customer_info.driving_license_url}" alt="${customer_info.driving_license_name}" />
                            </c:if>
                            <c:if test="${customer_info.driving_license_url == null || '' == customer_info.driving_license_url}">
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
                <td class="tableleft">其他证明</td>
                <td>
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                            <c:if test="${customer_info.other_url != null && '' != customer_info.other_url}">
                                <img src="${ctx}${customer_info.other_url}" alt="${customer_info.other_name}" />
                            </c:if>
                            <c:if test="${customer_info.other_url == null || '' == customer_info.other_url}">
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
                <td colspan="4">
                    <button type="submit" class="btn btn-primary" id="save">保存</button> &nbsp;&nbsp;
                    <button type="button" class="btn btn-success" id="backid">返回列表</button>
                    <input type="hidden" id="customer_id" name="customer_id" value="${customer_id}">
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
    });
</script>