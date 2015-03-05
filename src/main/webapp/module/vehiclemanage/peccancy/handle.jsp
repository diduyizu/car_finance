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
                <td><input type="text" name="carframe_no" id="carframe_no" value="${vehicle_peccancy.carframe_no}" readonly/></td>
            </tr>
            <tr>
                <td width="10%" class="tableleft">发动机号</td>
                <td><input type="text" name="engine_no" id="engine_no" value="${vehicle_peccancy.engine_no}" readonly/></td>
            </tr>
            <tr>
                <td class="tableleft">车牌号</td>
                <td><input type="text" name="license_plate" id="license_plate" value="${vehicle_peccancy.license_plate}" readonly/></td>
            </tr>
            <tr>
                <td class="tableleft">违章时间</td>
                <td>
                    <div class="input-append date" id="peccancy_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="peccancy_at_date" name="peccancy_at_date" value="${vehicle_peccancy.peccancy_at}">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="tableleft">违章地点</td>
                <td><input type="text" name="peccancy_place" id="peccancy_place" value="${vehicle_peccancy.peccancy_place}" /></td>
            </tr>

            <tr>
                <td class="tableleft">违章原因</td>
                <td><input type="text" name="peccancy_reason" id="peccancy_reason" value="${vehicle_peccancy.peccancy_reason}" /></td>
            </tr>
            <tr>
                <td class="tableleft">罚款金额</td>
                <td><input type="text" name="peccancy_price" id="peccancy_price" value="${vehicle_peccancy.peccancy_price}" /></td>
            </tr>
            <tr>
                <td class="tableleft">扣分数</td>
                <td><input type="text" name="score" id="score" value="${vehicle_peccancy.score}" /></td>
            </tr>
            <tr>
                <td class="tableleft">是否已处理</td>
                <td>
                    <input type="radio" name="status" value="0" checked>未处理
                    <input type="radio" name="status" value="1">已处理
                </td>
            </tr>
            <tr>
                <td class="tableleft">仲裁结果</td>
                <td><input type="text" name="arbitration" id="arbitration" required=""/></td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td>
                    <input type="hidden" id="peccancy_id" name="peccancy_id" value="${vehicle_peccancy.id}">
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

        $('#peccancy_at').datepicker();

		$('#backid').click(function(){
            var carframe_no=$.trim($('#carframe_no').val());
            window.location.href="${ctx}/vehicle/peccancy/detail?carframe_no="+carframe_no;
		});

        $('#save').click(function(){
            var carframe_no=$.trim($('#carframe_no').val());
            var engine_no=$.trim($('#engine_no').val());
            var license_plate=$.trim($('#license_plate').val());
            var peccancy_at_date=$.trim($('#peccancy_at_date').val());
            var peccancy_place=$.trim($('#peccancy_place').val());
            var peccancy_reason=$.trim($('#peccancy_reason').val());
            var score=$.trim($('#score').val());
            var status=$('input:radio:checked').val();
            var peccancy_price=$.trim($('#peccancy_price').val());
            var arbitration=$.trim($('#arbitration').val());
            var peccancy_id = $.trim($('#peccancy_id').val());

            $.ajax({
                url:"${ctx}/vehicle/peccancy/dohandle",
                type: "post",
                data:{id:peccancy_id,carframe_no:carframe_no,engine_no:engine_no,license_plate:license_plate,
                    peccancy_at_date:peccancy_at_date,peccancy_place:peccancy_place,peccancy_reason:peccancy_reason,
                    score:score,status:status,peccancy_price:peccancy_price,arbitration:arbitration},
                success:function(data){
                    if(data == 1){
                        alert("成功");
                        window.location.href="${ctx}/vehicle/peccancy/detail?carframe_no="+carframe_no;
                    } else {
                        alert("失败");
                        return false;
                    }
                }
            })
        })
    });
</script>