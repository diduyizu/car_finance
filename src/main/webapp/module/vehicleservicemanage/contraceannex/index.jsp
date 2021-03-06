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
    <form class="cmxform form-horizontal">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td>附件名</td>
                <td>附件图片</td>
            </tr>
            <c:forEach var="contrace_annex" items="${contrace_annex_list}" varStatus="status">
                <tr>
                    <td>${contrace_annex.annex_name}</td>
                    <td>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                                <img src="${ctx}${contrace_annex.annex_url}" alt="${contrace_annex.annex_name}" />
                            </div>
                            <%--<div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>--%>
                            <%--<div>--%>
                                                   <%--<span class="btn btn-white btn-file">--%>
                                                   <%--<span class="fileupload-new"><i class="fa fa-paper-clip"></i>选择图片</span>--%>
                                                   <%--<span class="fileupload-exists"><i class="fa fa-undo"></i>更换</span>--%>
                                                   <%--<input type="file" class="default" name="files" />--%>
                                                   <%--</span>--%>
                                <%--<a href="#" class="btn btn-danger fileupload-exists" data-dismiss="fileupload"><i class="fa fa-trash"></i>删除</a>--%>
                            <%--</div>--%>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <form class="cmxform form-horizontal" action="${ctx}/vehicleservice/contrace/annex/upload" id="uploadannex" enctype="multipart/form-data" method="post">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft">新增</td>
                <td>
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
                    <button type="submit" class="btn btn-primary" id="save">保存</button> &nbsp;&nbsp;
                    <%--<button type="button" class="btn btn-success" id="backid">返回列表</button>--%>
                    <input type="hidden" id="contrace_id" name="contrace_id" value="${contrace_id}">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
<script>
    $(function () {
		<%--$('#backid').click(function(){--%>
            <%--window.location.href="${ctx}/customer/info/index";--%>
		<%--});--%>
    });
</script>