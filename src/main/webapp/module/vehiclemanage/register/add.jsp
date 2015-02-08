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

    <script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datepicker.js" />"></script>

 

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
    <form class="definewidth m20" method="post" action="${ctx}/vehicle/register/doadd">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td width="10%" class="tableleft">门店</td>
                <td>
                    <select id="original_org" name="original_org">
                        <c:forEach var="user_role" items="${user_role_list}" varStatus="status">
                            <option value="${user_role.org_id}">${user_role.org_name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tableleft">档案编号</td>
                <td><input type="text" name="archive_no" id="archive_no" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">存货编码</td>
                <td><input type="text" name="inventory_no" id="inventory_no" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">品牌</td>
                <td><input type="text" name="brand" id="brand" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">车型</td>
                <td><input type="text" name="model" id="model"/></td>
            </tr>
            <tr>
                <td class="tableleft">颜色</td>
                <td><input type="text" name="color" id="color" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">车架号</td>
                <td><input type="text" name="carframe_no" id="carframe_no"/></td>
            </tr>
            <tr>
                <td width="10%" class="tableleft">发动机号</td>
                <td><input type="text" name="engine_no" id="engine_no" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">登记证书</td>
                <td><input type="text" name="registry_certificate" id="registry_certificate" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">登记证书去向</td>
                <td><input type="text" name="certificate_direction" id="certificate_direction" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">贷款银行</td>
                <td><input type="text" name="loan_bank" id="loan_bank"/></td>
            </tr>
            <tr>
                <td class="tableleft">关单/合格/一致性证书</td>
                <td><input type="text" name="consistency_cer" id="consistency_cer" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">检验单</td>
                <td><input type="text" name="check_list" id="check_list"/></td>
            </tr>

            <tr>
                <td width="10%" class="tableleft">完税证明/小本</td>
                <td><input type="text" name="duty_paid_proof" id="duty_paid_proof" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">记录</td>
                <td><input type="text" name="record" id="record" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">购买日期</td>
                <td>
                    <div class="input-append date" id="buy_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" name="buy_at" value="">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="tableleft">供应商名称</td>
                <td><input type="text" name="supplier" id="supplier"/></td>
            </tr>
            <tr>
                <td class="tableleft">车牌号</td>
                <td><input type="text" name="license_plate" id="license_plate" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">上牌登记日期</td>
                <td>
                    <div class="input-append date" id="card_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" name="card_at" value="">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
            </tr>

            <tr>
                <td width="10%" class="tableleft">年审日期</td>
                <td>
                    <div class="input-append date" id="limited_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" name="limited_at" value="">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="tableleft">市场指导价</td>
                <td><input type="text" name="guide_price" id="guide_price" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">车购价</td>
                <td><input type="text" name="vehicle_price" id="vehicle_price" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">车购税</td>
                <td><input type="text" name="vehicle_tax" id="vehicle_tax"/></td>
            </tr>
            <tr>
                <td class="tableleft">保险公司</td>
                <td><input type="text" name="insurance_company" id="insurance_company" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">交强险</td>
                <td><input type="text" name="strong_insurance" id="strong_insurance"/></td>
            </tr>

            <tr>
                <td class="tableleft">车船税</td>
                <td><input type="text" name="vehicle_vessel_tax" id="vehicle_vessel_tax"/></td>
            </tr>
            <tr>
                <td class="tableleft">交强险到期日期</td>
                <td>
                    <div class="input-append date" id="strong_insurance_expire_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" name="strong_insurance_expire_at" value="">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="tableleft">商业险</td>
                <td><input type="text" name="business_insurance" id="business_insurance"/></td>
            </tr>

            <tr>
                <td class="tableleft">商业险到期日期</td>
                <td>
                    <div class="input-append date" id="business_insurance_expire_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" name="business_insurance_expire_at" value="">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="tableleft">备注</td>
                <td><input type="text" name="remark" id="remark"/></td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td>
                    <button type="submit" class="btn btn-primary" id="save">保存</button> &nbsp;&nbsp;
                    <button type="button" class="btn btn-success" id="backid">返回列表</button>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
<script>
    $(function () {
        window.prettyPrint && prettyPrint();
//        $('#dp1').datepicker({
//            format: 'yyyy-mm-dd'
//        });
//        $('#dp2').datepicker();
//        $('#dp3').datepicker();
//        var startDate = new Date(2012,1,20);
//        var endDate = new Date(2012,1,25);
//        $('#dp4').datepicker()
//                .on('changeDate', function(ev){
//                    if (ev.date.valueOf() > endDate.valueOf()){
//                        $('#alert').show().find('strong').text('The start date can not be greater then the end date');
//                    } else {
//                        $('#alert').hide();
//                        startDate = new Date(ev.date);
//                        $('#startDate').text($('#dp4').data('date'));
//                    }
//                    $('#dp4').datepicker('hide');
//                });
//        $('#dp5').datepicker()
//                .on('changeDate', function(ev){
//                    if (ev.date.valueOf() < startDate.valueOf()){
//                        $('#alert').show().find('strong').text('The end date can not be less then the start date');
//                    } else {
//                        $('#alert').hide();
//                        endDate = new Date(ev.date);
//                        $('#endDate').text($('#dp5').data('date'));
//                    }
//                    $('#dp5').datepicker('hide');
//                });

        $('#buy_at').datepicker();
        $('#card_at').datepicker();
        $('#limited_at').datepicker();
        $('#strong_insurance_expire_at').datepicker();
        $('#business_insurance_expire_at').datepicker();


		$('#backid').click(function(){
            window.location.href="${ctx}/vehicle/register/index";
		});

        <%--$('#save').click(function(){--%>
            <%--var login_name = $.trim($('#login_name').val());--%>
            <%--var login_pwd = $.trim($('#login_pwd').val());--%>
            <%--var user_name = $.trim($('#user_name').val());--%>
            <%--var nice_name = $.trim($('#nice_name').val());--%>
            <%--var org_id = $('#org_id').val();--%>
            <%--var role_id = $('#role_id').val();--%>

            <%--if(login_name == '') {--%>
                <%--alert("请输入登录名");--%>
                <%--return false;--%>
            <%--}--%>
            <%--if(login_pwd == '') {--%>
                <%--alert("请输入密码");--%>
                <%--return false;--%>
            <%--}--%>
            <%--if(user_name == '') {--%>
                <%--alert("请输入真实姓名");--%>
                <%--return false;--%>
            <%--}--%>

            <%--$.ajax({--%>
                <%--url:"${ctx}/people/people/doadd",--%>
                <%--type: "post",--%>
                <%--data:{login_name:login_name,login_pwd:login_pwd,user_name:user_name,nick_name:nice_name,org_id:org_id,role_id:role_id},--%>
<%--//                dataType:"json",--%>
                <%--success:function(data){--%>
                    <%--if(data == 1){--%>
                        <%--alert("成功");--%>
                        <%--location.reload();--%>
                    <%--} else {--%>
                        <%--alert("失败");--%>
                    <%--}--%>
                <%--}--%>
            <%--})--%>
        <%--})--%>
    });
</script>