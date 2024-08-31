<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html id="html" xmlns="http://www.w3.org/1999/xhtml">
<#--  要替换的参数
title,
-->
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf8"/>
    <base target="_blank"/>
    <title>${title}</title>

    <style>
        * {
            margin: 1px;
            padding: 1px;
            list-style: none;
            border: 1px;
        }

        body {
            background: #fffbe6;
        }

        .content {
            border: 3px solid #ddd;
            width: 455px;
            margin-left: 20px;
            margin-top: 50px;
            float: left;
        }

        .datetable {
            border-top: 1px solid #ccc;
            border-left: 1px solid #ccc;
            background: #fff;
        }

        .datetable td {
            border-bottom: 1px solid #ccc;
            border-right: 1px solid #ccc;
            text-align: center;
        }

        .datetable thead {
            background: #006600;
        }

        .datetable thead td {
            padding: 10px 5px;
            font: normal 12px/normal 'microsoft yahei';
            color: #fff;
            text-align: center;
        }

        .datetable thead td span {
            padding: 0 5px;
        }

        .datetable tbody td {
            padding: 5px 3px;
            font-size: 12px;
        }

        .cpic {
            width: 100px;
            height: 40px;
        }

    </style>
    <style type="text/css">
        body {
            overflow: auto
        }

        #tcss {
            width: 400px;
            overflow: hidden
        }

        .left {
            float: left;
            width: 180px;
            border: 1px solid #F00
        }

        .right {
            float: right;
            width: 200px;
            border: 1px solid #00F
        }

        #footer {
            height: 20px;
            line-height: 20px;
            position: fixed;
            bottom: 0;
            width: 100%;
            text-align: right;
            background: #b333;
            color: #7766ff;
            font-family: Arial;
            font-size: 12px;
            letter-spacing: 1px;
        }

        .hide {
            display: none;
        }
    </style>
</head>
<body onload="initial();">

<!-- 百度搜索 -->
<form action="https://www.baidu.com/s" method="get" target="_blank" id="form1">
    <div>
        <a href="https://www.baidu.com/" id="s1" style="display: block"><img src="image/baidu2.png" class="cpic"></a>
        <a href="https://www4.bing.com/" id="s2" style="display: none"><img src="image/binglog.jpeg" class="cpic"></a>
        <a href="https://www.google.com/" id="s3" style="display: none"><img src="image/google.png" class="cpic"></a>

        <input type="text" name="word" id="word" style="width:500px;height:30px;background-color: #FDD6DC">
        <input type="submit" class="ss_btn" id="search_btn" style="width:40px;height:30px;background-color:#FDD6DC"
               value="搜 索">
        <input type="button" value="clear" onclick="javascript:cls();"><br/>
        <input type="radio" id="r1" name="x" value=" " onclick="radioClicked(1)" checked="checked"><label
                for="r1">百度</label>
        <input type="radio" id="r2" name="x" value=" " onclick="radioClicked(2)"><label for="r2">Bing</label>
        <input type="radio" id="r3" name="x" value=" " onclick="radioClicked(3)"><label for="r3">Google</label>
    </div>
</form>
<!-- 百度搜索 end -->

<div class="box" style="top:100px; float:left;">

    <!-- search tools -->

    <h2>实用查询</h2>

</div>

<form name="CLD" class="content">

</form>

</body>
</html>


<script type="text/javascript">

</script>
<script type="text/javascript">

</script>

