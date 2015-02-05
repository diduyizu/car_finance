<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>

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
<form action="index.html" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">è§è²åç§°</td>
            <td><input type="text" name="title"/></td>
        </tr>
        <tr>
            <td class="tableleft">ç¶æ</td>
            <td>
                <input type="radio" name="status" value="1" checked/> å¯ç¨  <input type="radio" name="status" value="0"/> ç¦ç¨
            </td>
        </tr>
        <tr>
            <td class="tableleft">æé</td>
            <td>
                <ul><li><label class='checkbox inline'><input type='checkbox' name='group[]' value='' />å¬ç¨èç¹</label><ul><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='21' />çå¸åºçº§èæ°æ®</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='22' />çå¸åºæ°æ®è·å</label></ul></li><li><label class='checkbox inline'><input type='checkbox' name='group[]' value='' />æä¿¡çæ¹æ¬¡ç®¡ç</label><ul><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='25' />æä¿¡çæ¹æ¬¡ç®¡ç</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='26' />æä¿¡çæ¹æ¬¡æ·»å </label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='27' />æä¿¡çæ¹æ¬¡ç¼è¾</label></ul></li><li><label class='checkbox inline'><input type='checkbox' name='group[]' value='' />æ ç­¾ç®¡ç</label><ul><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='17' />æ ç­¾æ¥è¯¢</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='18' />æ ç­¾çæ</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='19' />æ¹éè´´æ ç­¾</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='20' />æ ç­¾ç¼è¾</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='23' />æ ç­¾çæä¸è½½ï¼è¯·åæ ç­¾çæåæ¶éä¸­ï¼</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='24' />æä»¶ä¸è½½ï¼è¯·åæ ç­¾çæãæ¥è¯¢åæ¶éä¸­ï¼</label></ul></li><li><label class='checkbox inline'><input type='checkbox' name='group[]' value='' />æé</label><ul><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='1' />æéåè¡¨</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='2' />æéæ·»å </label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='3' />æéç¼è¾</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='4' />æéå é¤</label></ul></li><li><label class='checkbox inline'><input type='checkbox' name='group[]' value='' />ç¨æ·</label><ul><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='9' />ç¨æ·åè¡¨</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='10' />ç¨æ·æ·»å </label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='11' />ç¨æ·ç¼è¾</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='12' />ç¨æ·å é¤</label></ul></li><li><label class='checkbox inline'><input type='checkbox' name='group[]' value='' />èåç®¡ç</label><ul><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='13' />èååè¡¨</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='14' />èåæ°å¢</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='15' />èåç¼è¾</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='16' />èåå é¤</label></ul></li><li><label class='checkbox inline'><input type='checkbox' name='group[]' value='' />è§è²</label><ul><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='5' />è§è²åè¡¨</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='6' />è§è²æ·»å </label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='7' />è§è²ç¼è¾</label><li><label class='checkbox inline'><input type='checkbox' name='node[]' value='8' />è§è²å é¤</label></ul></li></ul> 
            </td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">ä¿å­</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">è¿ååè¡¨</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script>
    $(function () {
        $(':checkbox[name="group[]"]').click(function () {
            $(':checkbox', $(this).closest('li')).prop('checked', this.checked);
        });

		$('#backid').click(function(){
				window.location.href="index.html";
		 });
    });
</script>