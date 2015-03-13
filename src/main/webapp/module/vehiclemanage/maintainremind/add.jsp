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
                <td><input type="text" name="carframe_no" id="carframe_no" value="${vehicle_info.carframe_no}" readonly/></td>
                <td width="10%" class="tableleft">发动机号</td>
                <td><input type="text" name="engine_no" id="engine_no" value="${vehicle_info.engine_no}" readonly/></td>
                <td class="tableleft">车牌号</td>
                <td><input type="text" name="license_plate" id="license_plate" value="${vehicle_info.license_plate}" readonly/></td>
            </tr>
            <tr>
                <td class="tableleft">保养时间</td>
                <td>
                    <div class="input-append date" id="maintain_at" data-date-format="yyyy-mm-dd">
                        <input class="span2" size="16" type="text" id="maintain_at_date" name="maintain_at_date" placeholder="必填" required="true" readonly>
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </td>
                <td class="tableleft">保养内容</td>
                <td><input type="text" name="maintain_content" id="maintain_content" placeholder="必填" required/></td>
                <td class="tableleft">保养金额</td>
                <td><input type="text" name="maintain_price" id="maintain_price" placeholder="必填" required/></td>
            </tr>
            <tr>
                <td class="tableleft">公里数</td>
                <td><input type="text" name="current_km" id="current_km" placeholder="必填" required/></td>
                <td class="tableleft">下次保养公里数</td>
                <td colspan="3"><input type="text" name="next_maintain_km" id="next_maintain_km" placeholder="必填" required/></td>
            </tr>
            <tr>
                <td class="tableleft">处理员工ID</td>
                <td><input type="text" name="user_id" id="user_id" required/></td>
                <td class="tableleft">处理员工姓名</td>
                <td colspan="3"><input type="text" name="user_name" id="user_name" required/></td>
            </tr>
            <tr>
                <td class="tableleft"></td>
                <td colspan="5"><button type="button" class="btn btn-primary" id="save">保存</button></td>
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

        $('#maintain_at').datepicker();

        $('#save').click(function(){
            var carframe_no=$.trim($('#carframe_no').val());
            var engine_no=$.trim($('#engine_no').val());
            var license_plate=$.trim($('#license_plate').val());
            var maintain_at_date=$.trim($('#maintain_at_date').val());
            var maintain_content=$.trim($('#maintain_content').val());
            var maintain_price=$.trim($('#maintain_price').val());
            var current_km=$.trim($('#current_km').val());
            var next_maintain_km=$.trim($('#next_maintain_km').val());
            var user_id=$.trim($('#user_id').val());
            var user_name=$.trim($('#user_name').val());

            $.ajax({
                url:"${ctx}/vehicle/maintainremind/doadd",
                type: "post",
                data:{carframe_no:carframe_no,engine_no:engine_no,license_plate:license_plate,
                    maintain_at_date:maintain_at_date,maintain_content:maintain_content,maintain_price:maintain_price,
                    current_km:current_km,next_maintain_km:next_maintain_km,user_id:user_id,user_name:user_name},
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