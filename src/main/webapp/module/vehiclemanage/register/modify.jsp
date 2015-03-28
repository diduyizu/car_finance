<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <%--<script type="text/javascript" src="<c:url value="/resources/Js/jquery.js" />"></script>--%>
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery-1.7.1.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/ckform.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/common.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-datepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/bootstrap-typeahead.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/jquery.validate.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/Js/validation-init.js" />"></script>

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
    <%--<form class="definewidth m20">--%>
    <form class="cmxform form-horizontal">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft" colspan="6">车辆基本信息</td>
            </tr>
            <tr>
                <td class="tableleft">所属门店</td>
                <td colspan="5">
                    <select id="original_org" name="original_org">
                        <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                                <c:if test="${org.org_type > 12}">
                                    <option value="${org.org_id}" <c:if test="${vehicle_info.original_org == org.org_id}">selected="selected"</c:if>>${org.org_city_name} ${org.org_name}</option>
                                </c:if>
                                <c:if test="${org.org_type < 13}">
                                    <option value="${org.org_id}" <c:if test="${vehicle_info.original_org == org.org_id}">selected="selected"</c:if>>${org.org_name}</option>
                                </c:if>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tableleft">品牌</td>
                <td><input type="text" data-provide="typeahead" name="brand" id="brand" placeholder="必填" required value="${vehicle_info.brand}" /></td>
                <td class="tableleft">车型</td>
                <td><input type="text" data-provide="typeahead" name="model" id="model" placeholder="必填" required="true" value="${vehicle_info.model}" /></td>
                <td class="tableleft">颜色</td>
                <td><input type="text" name="color" id="color" placeholder="必填" required="true" value="${vehicle_info.color}"/></td>
            </tr>
            <tr>
                <td class="tableleft">车牌号</td>
                <td><input type="text" data-provide="typeahead" name="license_plate" id="license_plate" placeholder="必填" required="true" value="${vehicle_info.license_plate}" /></td>
                <td class="tableleft">车架号</td>
                <td><input type="text" name="carframe_no" id="carframe_no" placeholder="必填" required="true" value="${vehicle_info.carframe_no}" /></td>
                <td class="tableleft">发动机号</td>
                <td><input type="text" name="engine_no" id="engine_no" placeholder="必填" required="true" value="${vehicle_info.engine_no}" /></td>
            </tr>
            <tr>
                <td class="tableleft">公里数</td>
                <td><input type="text" name="km" id="km" placeholder="必填" required="true" value="${vehicle_info.km}" /></td>
                <td class="tableleft">存货编码</td>
                <td><input type="text" name="inventory_no" id="inventory_no" value="${vehicle_info.inventory_no}" /></td>
                <td class="tableleft">检验单</td>
                <td><input type="text" name="check_list" id="check_list" value="${vehicle_info.check_list}" /></td>
            </tr>
            <tr>
                <td class="tableleft">登记证书</td>
                <td><input type="text" name="registry_certificate" id="registry_certificate" value="${vehicle_info.registry_certificate}" /></td>
                <td class="tableleft">登记证书去向</td>
                <td><input type="text" name="certificate_direction" id="certificate_direction" value="${vehicle_info.certificate_direction}" /></td>
                <td class="tableleft">关单/合格/一致性证书</td>
                <td><input type="text" name="consistency_cer" id="consistency_cer" value="${vehicle_info.consistency_cer}" /></td>
            </tr>
            <tr>
                <td class="tableleft">完税证明/小本</td>
                <td><input type="text" name="duty_paid_proof" id="duty_paid_proof" value="${vehicle_info.duty_paid_proof}" /></td>
                <td class="tableleft">记录</td>
                <td><input type="text" name="record" id="record" value="${vehicle_info.record}" /></td>
                <td class="tableleft">备注</td>
                <td><input type="text" name="remark" id="remark" value="${vehicle_info.remark}" /></td>
            </tr>
        </table>
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft" colspan="6">车辆购买信息</td>
            </tr>
            <tr>
                <td class="tableleft">购买日期</td>
                <td>
                    <div class="input-append date" id="buy_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="buy_at_date" name="buy_at" required="true" placeholder="必填" readonly value="${vehicle_info.buy_at}">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
                <td class="tableleft">上牌登记日期</td>
                <td>
                    <div class="input-append date" id="card_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="card_at_date" name="card_at" required="true" placeholder="必填" readonly value="${vehicle_info.card_at}">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
                <td class="tableleft">市场指导价</td>
                <td><input type="text" name="guide_price" id="guide_price" placeholder="必填" required="true" value="${vehicle_info.guide_price}" /></td>
            </tr>
            <tr>
                <td class="tableleft">供应商名称</td>
                <td><input type="text" name="supplier" id="supplier" value="${vehicle_info.supplier}" /></td>
                <td class="tableleft">年审日期</td>
                <td>
                    <div class="input-append date" id="limited_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="limited_at_date" name="limited_at" required="true" placeholder="必填" readonly value="${vehicle_info.limited_at}">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
                <td class="tableleft">车购价</td>
                <td><input type="text" name="vehicle_price" id="vehicle_price" placeholder="必填" required="true" value="${vehicle_info.vehicle_price}"/></td>
            </tr>
            <tr>
                <td class="tableleft">融资租赁公司</td>
                <td><input type="text" name="financing_rent_company" id="financing_rent_company" value="${vehicle_info.financing_rent_company}"/></td>
                <td class="tableleft">融资租赁总价</td>
                <td colspan="3"><input type="text" name="financing_rent_price" id="financing_rent_price" value="${vehicle_info.financing_rent_price}" /></td>
            </tr>
            <tr>
                <td class="tableleft">保证金</td>
                <td><input type="text" name="bail" id="bail" value="${vehicle_info.bail}" /></td>
                <td class="tableleft">月付款</td>
                <td colspan="3"><input type="text" name="monthly_payment" id="monthly_payment" value="${vehicle_info.monthly_payment}" /></td>
            </tr>
        </table>
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft" colspan="6">车辆保险信息</td>
            </tr>
            <tr>
                <td class="tableleft">交强险</td>
                <td><input type="text" name="strong_insurance" id="strong_insurance" placeholder="必填" required="true" value="${vehicle_info.strong_insurance}"/></td>
                <td class="tableleft">交强险到期日期</td>
                <td>
                    <div class="input-append date" id="strong_insurance_expire_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="strong_insurance_expire_at_date" name="strong_insurance_expire_at" placeholder="必填" required="true" readonly value="${vehicle_info.strong_insurance_expire_at}"  />
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
                <td class="tableleft">车辆租赁状态</td>
                <td>
                    <select id="lease_status" name="lease_status">
                        <option value="在库" <c:if test="${vehicle_info.lease_status == '在库'}">selected="selected"</c:if>>在库</option>
                        <option value="零租" <c:if test="${vehicle_info.lease_status == '零租'}">selected="selected"</c:if>>零租</option>
                        <option value="产权租" <c:if test="${vehicle_info.lease_status == '产权租'}">selected="selected"</c:if>>产权租</option>
                        <option value="售出" <c:if test="${vehicle_info.lease_status == '售出'}">selected="selected"</c:if>>售出</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tableleft">商业险</td>
                <td><input type="text" name="business_insurance" id="business_insurance" placeholder="必填" required="true" value="${vehicle_info.business_insurance}"/></td>
                <td class="tableleft">商业险到期日期</td>
                <td>
                    <div class="input-append date" id="business_insurance_expire_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="business_insurance_expire_at_date" name="business_insurance_expire_at" placeholder="必填" required="true" readonly value="${vehicle_info.business_insurance_expire_at}" />
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
                <td class="tableleft">贷款银行</td>
                <td><input type="text" name="loan_bank" id="loan_bank" value="${vehicle_info.loan_bank}"/></td>
            </tr>
            <tr>
                <td class="tableleft">车购税</td>
                <td><input type="text" name="vehicle_tax" id="vehicle_tax" placeholder="必填" required="true" value="${vehicle_info.vehicle_tax}"/></td>
                <td class="tableleft">车船税</td>
                <td><input type="text" name="vehicle_vessel_tax" id="vehicle_vessel_tax" placeholder="必填" required="true" value="${vehicle_info.vehicle_vessel_tax}"/></td>
                <td class="tableleft">保险公司</td>
                <td><input type="text" name="insurance_company" id="insurance_company" placeholder="必填" required="true" value="${vehicle_info.insurance_company}"/></td>
            </tr>
        </table>
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td class="tableleft" colspan="6">车辆使用情况</td>
            </tr>
            <tr>
                <%--<td class="tableleft">保养剩余公里数</td>--%>
                <%--<td><input type="text" name="maintian_on_km" id="maintian_on_km" placeholder="必填" required="true" value="${vehicle_info.maintian_on_km}"/></td>--%>
                <td class="tableleft">下次保养公里数</td>
                <td><input type="text" name="next_main_km" id="next_main_km" placeholder="必填" required="true" value="${vehicle_info.next_main_km}"/></td>
                <td class="tableleft">是否有违章待处理</td>
                <td colspan="3">
                    <select id="peccancy_status" name="peccancy_status">
                        <option value="0" <c:if test="${vehicle_info.peccancy_status == 0}">selected="selected"</c:if>>无</option>
                        <option value="1" <c:if test="${vehicle_info.peccancy_status == 1}">selected="selected"</c:if>>有</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tableleft">GPS状态</td>
                <td>
                    <select id="gps" name="gps">
                        <option value="正常" <c:if test="${vehicle_info.gps == '正常'}">selected="selected"</c:if>>正常</option>
                        <option value="异常" <c:if test="${vehicle_info.gps == '异常'}">selected="selected"</c:if>>异常</option>
                        <option value="未安装" <c:if test="${vehicle_info.gps == '未安装'}">selected="selected"</c:if>>未安装</option>
                    </select>
                </td>
                <td class="tableleft">当前所在城市</td>
                <td>
                    <select id="current_city" name="current_city">
                        <c:forEach var="city" items="${city_list}" varStatus="status">
                            <option value="${city.city_id}" <c:if test="${vehicle_info.current_city == city.city_id}">selected="selected"</c:if>>${city.city_name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td class="tableleft">当前所在门店</td>
                <td>
                    <select id="current_shop" name="current_shop">
                        <c:forEach var="org" items="${user_all_org_list}" varStatus="status">
                            <c:if test="${org.org_type > 12}">
                                <option value="${org.org_id}" <c:if test="${vehicle_info.current_shop == org.org_id}">selected="selected"</c:if>>${org.org_city_name} ${org.org_name}</option>
                            </c:if>
                            <c:if test="${org.org_type < 13}">
                                <option value="${org.org_id}" <c:if test="${vehicle_info.current_shop == org.org_id}">selected="selected"</c:if>>${org.org_name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tableleft">是否有ETC</td>
                <td>
                    <select id="etc" name="etc">
                        <option value="有" <c:if test="${vehicle_info.etc == '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" <c:if test="${vehicle_info.etc == '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </td>
                <td class="tableleft">当前金额</td>
                <td><input type="text" name="etc_money" id="etc_money" value="${vehicle_info.etc_money}" /></td>
                <td class="tableleft">当前油量比（百分比％）</td>
                <td><input type="text" name="oil_percent" id="oil_percent" placeholder="必填" required="true" value="${vehicle_info.oil_percent}"/>%</td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td colspan="5">
                    <input type="hidden" id="vehicle_id" name="vehicle_id" value="${vehicle_id}" />
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
                        $("#current_city").append($('<option value="'+entity['city_id']+'">'+entity['city_name']+'</option>'));//后台数据加到下拉框
                    });
                }
            })
        });

        $('#save').click(function(){
            var vehicle_id=$.trim($('#vehicle_id').val());
            var brand=$.trim($('#brand').val());
            var model=$.trim($('#model').val());
            var color=$.trim($('#color').val());
            var carframe_no=$.trim($('#carframe_no').val());
            var engine_no=$.trim($('#engine_no').val());
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
            var strong_insurance_expire_at=$.trim($('#strong_insurance_expire_at_date').val());
            var vehicle_vessel_tax=$.trim($('#vehicle_vessel_tax').val());
            var business_insurance=$.trim($('#business_insurance').val());
            var business_insurance_expire_at=$.trim($('#business_insurance_expire_at_date').val());
            var km=$.trim($('#km').val());
//            var maintian_on_km=$.trim($('#maintian_on_km').val());
            var gps=$.trim($('#gps').val());
            var current_city=$.trim($('#current_city').val());
            var current_shop=$.trim($('#current_shop').val());
            var lease_status=$.trim($('#lease_status').val());
            var peccancy_status=$.trim($('#peccancy_status').val());
            var archive_no=$.trim($('#archive_no').val());
            var inventory_no=$.trim($('#inventory_no').val());
            var registry_certificate=$.trim($('#registry_certificate').val());
            var certificate_direction=$.trim($('#certificate_direction').val());
            var loan_bank=$.trim($('#loan_bank').val());
            var consistency_cer=$.trim($('#consistency_cer').val());
            var check_list=$.trim($('#check_list').val());
            var duty_paid_proof=$.trim($('#duty_paid_proof').val());
            var record=$.trim($('#record').val());
            var remark=$.trim($('#remark').val());
            var original_org=$('#original_org').val();
            var next_main_km=$.trim($('#next_main_km').val());

            var financing_rent_company=$.trim($('#financing_rent_company').val());
            var financing_rent_price=$.trim($('#financing_rent_price').val());
            var bail=$.trim($('#bail').val());
            var monthly_payment=$.trim($('#monthly_payment').val());

            var etc=$.trim($('#etc').val());
            var etc_money=$.trim($('#etc_money').val());
            var oil_percent=$.trim($('#oil_percent').val());


            $.ajax({
                url:"${ctx}/vehicle/vehicle/domodify",
                type: "post",
                data:{vehicle_id:vehicle_id,archive_no:archive_no,inventory_no:inventory_no,brand:brand,model:model,color:color,carframe_no:carframe_no,
                    engine_no:engine_no,registry_certificate:registry_certificate,certificate_direction:certificate_direction,
                    loan_bank:loan_bank,consistency_cer:consistency_cer,check_list:check_list,duty_paid_proof:duty_paid_proof,
                    record:record,buy_at:buy_at,supplier:supplier,license_plate:license_plate,card_at:card_at,limited_at:limited_at,
                    guide_price:guide_price,vehicle_price:vehicle_price,vehicle_tax:vehicle_tax,insurance_company:insurance_company,
                    strong_insurance:strong_insurance,vehicle_vessel_tax:vehicle_vessel_tax,strong_insurance_expire_at:strong_insurance_expire_at,
                    business_insurance:business_insurance,business_insurance_expire_at:business_insurance_expire_at,remark:remark,original_org:original_org,
                    km:km , gps:gps , current_city:current_city , current_shop:current_shop ,
                    lease_status:lease_status , peccancy_status:peccancy_status , next_main_km:next_main_km ,
                    financing_rent_company:financing_rent_company , financing_rent_price:financing_rent_price , bail:bail , monthly_payment:monthly_payment,
                    etc:etc,etc_money:etc_money,oil_percent:oil_percent},
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
