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
    <form class="definewidth m20">
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
                <td><input type="text" name="model" id="model" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">颜色</td>
                <td><input type="text" name="color" id="color" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">车架号</td>
                <td><input type="text" name="carframe_no" id="carframe_no" required="true"/></td>
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
                <td><input type="text" name="loan_bank" id="loan_bank" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">关单/合格/一致性证书</td>
                <td><input type="text" name="consistency_cer" id="consistency_cer" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">检验单</td>
                <td><input type="text" name="check_list" id="check_list" required="true"/></td>
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
                        <input class="span2" size="16" type="text" id="buy_at_date" name="buy_at" required="true" readonly>
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="tableleft">供应商名称</td>
                <td><input type="text" name="supplier" id="supplier" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">车牌号</td>
                <td><input type="text" name="license_plate" id="license_plate" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">上牌登记日期</td>
                <td>
                    <div class="input-append date" id="card_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="card_at_date" name="card_at" required="true" readonly>
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tableleft">年审日期</td>
                <td>
                    <div class="input-append date" id="limited_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="limited_at_date" name="limited_at" required="true" readonly>
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
                <td><input type="text" name="vehicle_tax" id="vehicle_tax" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">保险公司</td>
                <td><input type="text" name="insurance_company" id="insurance_company" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">交强险</td>
                <td><input type="text" name="strong_insurance" id="strong_insurance" required="true"/></td>
            </tr>

            <tr>
                <td class="tableleft">车船税</td>
                <td><input type="text" name="vehicle_vessel_tax" id="vehicle_vessel_tax" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">交强险到期日期</td>
                <td>
                    <div class="input-append date" id="strong_insurance_expire_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="strong_insurance_expire_at_date" name="strong_insurance_expire_at" required="true" readonly>
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="tableleft">商业险</td>
                <td><input type="text" name="business_insurance" id="business_insurance" required="true"/></td>
            </tr>

            <tr>
                <td class="tableleft">商业险到期日期</td>
                <td>
                    <div class="input-append date" id="business_insurance_expire_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="business_insurance_expire_at_date" name="business_insurance_expire_at" required="true" readonly>
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
        window.prettyPrint && prettyPrint();
        $('#buy_at').datepicker();
        $('#card_at').datepicker();
        $('#limited_at').datepicker();
        $('#strong_insurance_expire_at').datepicker();
        $('#business_insurance_expire_at').datepicker();

		$('#backid').click(function(){
            window.location.href="${ctx}/vehicle/register/index";
		});

        $('#save').click(function(){
            var archive_no=$.trim($('#archive_no').val());
            var inventory_no=$.trim($('#inventory_no').val());
            var brand=$.trim($('#brand').val());
            var model=$.trim($('#model').val());
            var color=$.trim($('#color').val());
            var carframe_no=$.trim($('#carframe_no').val());
            var engine_no=$.trim($('#engine_no').val());
            var registry_certificate=$.trim($('#registry_certificate').val());
            var certificate_direction=$.trim($('#certificate_direction').val());
            var loan_bank=$.trim($('#loan_bank').val());
            var consistency_cer=$.trim($('#consistency_cer').val());
            var check_list=$.trim($('#check_list').val());
            var duty_paid_proof=$.trim($('#duty_paid_proof').val());
            var record=$.trim($('#record').val());
            var buy_at=$.trim($('#buy_at_date').val());
            var supplier=$.trim($('#supplier').val());
            var license_plate=$.trim($('#license_plate').val());
            var card_at=$.trim($('#card_at_date').val());
            var limited_at=$.trim($('#limited_at_date').val());
            var guide_price=$.trim($('#guide_price').val());
            var vehicle_price=$.trim($('#vehicle_price').val());
            var vehicle_tax=$.trim($('#vehicle_tax').val());
            var insurance_company=$.trim($('#insurance_company').val());
            var strong_insurance=$.trim($('#strong_insurance').val());
            var vehicle_vessel_tax=$.trim($('#vehicle_vessel_tax').val());
            var strong_insurance_expire_at=$.trim($('#strong_insurance_expire_at_date').val());
            var business_insurance=$.trim($('#business_insurance').val());
            var business_insurance_expire_at=$.trim($('#business_insurance_expire_at_date').val());
            var remark=$.trim($('#remark').val());
            var original_org=$('#original_org').val();

            $.ajax({
                url:"${ctx}/vehicle/register/doadd",
                type: "post",
                data:{archive_no:archive_no,inventory_no:inventory_no,brand:brand,model:model,color:color,carframe_no:carframe_no,
                    engine_no:engine_no,registry_certificate:registry_certificate,certificate_direction:certificate_direction,
                    loan_bank:loan_bank,consistency_cer:consistency_cer,check_list:check_list,duty_paid_proof:duty_paid_proof,
                    record:record,buy_at:buy_at,supplier:supplier,license_plate:license_plate,card_at:card_at,limited_at:limited_at,
                    guide_price:guide_price,vehicle_price:vehicle_price,vehicle_tax:vehicle_tax,insurance_company:insurance_company,
                    strong_insurance:strong_insurance,vehicle_vessel_tax:vehicle_vessel_tax,strong_insurance_expire_at:strong_insurance_expire_at,
                    business_insurance:business_insurance,business_insurance_expire_at:business_insurance_expire_at,remark:remark,original_org:original_org},
                success:function(data){
                    if(data == 1){
                        alert("成功");
                        location.reload();
                    } else {
                        alert("失败");
                        return false;
                    }
                }
            })
        })
    });
</script>