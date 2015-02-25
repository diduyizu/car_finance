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
                <td class="tableleft">车架号</td>
                <td><input type="text" name="carframe_no" id="carframe_no"/></td>
            </tr>
            <tr>
                <td width="10%" class="tableleft">发动机号</td>
                <td><input type="text" name="engine_no" id="engine_no" required="true"/></td>
            </tr>
            <tr>
                <td class="tableleft">车牌号</td>
                <td><input type="text" name="license_plate" id="license_plate" required="true"/></td>
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
                        <input class="span2" size="16" type="text" id="strong_insurance_expire_at_date" name="strong_insurance_expire_at" required="true" readonly>
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

        $('#strong_insurance_expire_at').datepicker();
        $('#business_insurance_expire_at').datepicker();


		$('#backid').click(function(){
            window.location.href="${ctx}/vehicle/insurance/index";
		});

        $('#save').click(function(){
            var carframe_no=$.trim($('#carframe_no').val());
            var engine_no=$.trim($('#engine_no').val());
            var license_plate=$.trim($('#license_plate').val());
            var insurance_company=$.trim($('#insurance_company').val());
            var strong_insurance=$.trim($('#strong_insurance').val());
            var vehicle_vessel_tax=$.trim($('#vehicle_vessel_tax').val());
            var strong_insurance_expire_at=$.trim($('#strong_insurance_expire_at_date').val());
            var business_insurance=$.trim($('#business_insurance').val());
            var business_insurance_expire_at=$.trim($('#business_insurance_expire_at_date').val());
            var remark=$.trim($('#remark').val());

            $.ajax({
                url:"${ctx}/vehicle/insurance/doadd",
                type: "post",
                data:{carframe_no:carframe_no,engine_no:engine_no,license_plate:license_plate,insurance_company:insurance_company,
                    strong_insurance:strong_insurance,vehicle_vessel_tax:vehicle_vessel_tax,strong_insurance_expire_at:strong_insurance_expire_at,
                    business_insurance:business_insurance,business_insurance_expire_at:business_insurance_expire_at,remark:remark},
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