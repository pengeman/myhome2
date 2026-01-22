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
            width: 555px;
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

        input {
            border: 1px solid #00F
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
    <style type="text/css">
        .centered {
            margin: 50px auto;
            text-align: center;
        }

        .button::-moz-focus-inner {
            border: 0;
            padding: 0;
        }

        .button {
            display: inline-block;
            *display: inline;
            zoom: 1;
            padding: 6px 20px;
            margin: 0;
            cursor: pointer;
            border: 1px solid #bbb;
            overflow: visible;
            font: bold 13px arial, helvetica, sans-serif;
            text-decoration: none;
            white-space: nowrap;
            color: #555;

            background-color: #ddd;


            -webkit-transition: background-color 0.2s ease-out;
            -moz-transition: background-color 0.2s ease-out;
            -ms-transition: background-color 0.2s ease-out;
            -o-transition: background-color 0.2s ease-out;
            transition: background-color 0.2s ease-out;
            background-clip: padding-box; /* Fix bleeding */
            -moz-border-radius: 3px;
            -webkit-border-radius: 3px;
            border-radius: 3px;
            -moz-box-shadow: 0 1px 0 rgba(0, 0, 0, 0.3), 0 2px 2px -1px rgba(0, 0, 0, 0.5),
            0 1px 0 rgba(255, 255, 255, 0.3) inset;
            -webkit-box-shadow: 0 1px 0 rgba(0, 0, 0, 0.3),
            0 2px 2px -1px rgba(0, 0, 0, 0.5), 0 1px 0 rgba(255, 255, 255, 0.3) inset;
            box-shadow: 0 1px 0 rgba(0, 0, 0, 0.3), 0 2px 2px -1px rgba(0, 0, 0, 0.5),
            0 1px 0 rgba(255, 255, 255, 0.3) inset;
            text-shadow: 0 1px 0 rgba(255, 255, 255, 0.9);

            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -khtml-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        .button:hover {
            background-color: #eee;
            color: #555;
        }

        .button:active {
            background: #e9e9e9;
            position: relative;
            top: 1px;
            text-shadow: none;
            -moz-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.3) inset;
            -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.3) inset;
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.3) inset;
        }

        .button[disabled],
        .button[disabled]:hover,
        .button[disabled]:active {
            border-color: #eaeaea;
            background: #fafafa;
            cursor: default;
            position: static;
            color: #999;
            /* Usually, !important should be avoided but here it's really needed :) */
            -moz-box-shadow: none !important;
            -webkit-box-shadow: none !important;
            box-shadow: none !important;
            text-shadow: none !important;
        }

        /* Smaller buttons styles */

        .button.small {
            padding: 4px 12px;
        }

        /* Larger buttons styles */

        .button.large {
            padding: 12px 30px;
            text-transform: uppercase;
        }

        .button.large:active {
            top: 2px;
        }

        /* Colored buttons styles */

        .button.green,
        .button.red,
        .button.blue {
            color: #fff;
            text-shadow: 0 1px 0 rgba(0, 0, 0, 0.2);
        }

        /* */

        .button.green {
            background-color: #57a957;
            border-color: #57a957;
        }

        .button.green:hover {
            background-color: #62c462;
        }

        .button.green:active {
            background: #57a957;
        }

        /* */

        .button.red {
            background-color: #ca3535;
            border-color: #c43c35;
        }

        .button.red:hover {
            background-color: #ee5f5b;
        }

        .button.red:active {
            background: #c43c35;
        }

        /* */

        .button.blue {
            background-color: #269ce9;
            border-color: #269ce9;
        }

        .button.blue:hover {
            background-color: #70b9e8;
        }

        .button.blue:active {
            background: #269ce9;
        }

        /* */

        .green[disabled],
        .green[disabled]:hover,
        .green[disabled]:active {
            border-color: #57a957;
            background: #57a957;
            color: #d2ffd2;
        }

        .red[disabled],
        .red[disabled]:hover,
        .red[disabled]:active {
            border-color: #c43c35;
            background: #c43c35;
            color: #ffd3d3;
        }

        .blue[disabled],
        .blue[disabled]:hover,
        .blue[disabled]:active {
            border-color: #269ce9;
            background: #269ce9;
            color: #93d5ff;
        }

        /* Group buttons */

        .button-group,
        .button-group li {
            display: inline-block;
            *display: inline;
            zoom: 1;
        }

        .button-group {
            font-size: 0; /* Inline block elements gap - fix */
            margin: 0;
            padding: 0;
            background: rgba(0, 0, 0, 0.1);
            border-bottom: 1px solid rgba(0, 0, 0, 0.1);
            padding: 7px;
            -moz-border-radius: 7px;
            -webkit-border-radius: 7px;
            border-radius: 7px;
        }

        .button-group li {
            margin-right: -1px; /* Overlap each right button border */
        }

        .button-group .button {
            font-size: 13px; /* Set the font size, different from inherited 0 */
            -moz-border-radius: 0;
            -webkit-border-radius: 0;
            border-radius: 0;
        }

        .button-group .button:active {
            -moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.2) inset,
            5px 0 5px -3px rgba(0, 0, 0, 0.2) inset,
            -5px 0 5px -3px rgba(0, 0, 0, 0.2) inset;
            -webkit-box-shadow: 0 0 1px rgba(0, 0, 0, 0.2) inset,
            5px 0 5px -3px rgba(0, 0, 0, 0.2) inset,
            -5px 0 5px -3px rgba(0, 0, 0, 0.2) inset;
            box-shadow: 0 0 1px rgba(0, 0, 0, 0.2) inset,
            5px 0 5px -3px rgba(0, 0, 0, 0.2) inset,
            -5px 0 5px -3px rgba(0, 0, 0, 0.2) inset;
        }

        .button-group li:first-child .button {
            -moz-border-radius: 3px 0 0 3px;
            -webkit-border-radius: 3px 0 0 3px;
            border-radius: 3px 0 0 3px;
        }

        .button-group li:first-child .button:active {
            -moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.2) inset,
            -5px 0 5px -3px rgba(0, 0, 0, 0.2) inset;
            -webkit-box-shadow: 0 0 1px rgba(0, 0, 0, 0.2) inset,
            -5px 0 5px -3px rgba(0, 0, 0, 0.2) inset;
            box-shadow: 0 0 1px rgba(0, 0, 0, 0.2) inset,
            -5px 0 5px -3px rgba(0, 0, 0, 0.2) inset;
        }

        .button-group li:last-child .button {
            -moz-border-radius: 0 3px 3px 0;
            -webkit-border-radius: 0 3px 3px 0;
            border-radius: 0 3px 3px 0;
        }

        .button-group li:last-child .button:active {
            -moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.2) inset,
            5px 0 5px -3px rgba(0, 0, 0, 0.2) inset;
            -webkit-box-shadow: 0 0 1px rgba(0, 0, 0, 0.2) inset,
            5px 0 5px -3px rgba(0, 0, 0, 0.2) inset;
            box-shadow: 0 0 1px rgba(0, 0, 0, 0.2) inset,
            5px 0 5px -3px rgba(0, 0, 0, 0.2) inset;
        }

    </style>
</head>
<body onload="initial();">

<form name="CLD" class="content" action="doParrot" onsubmit="dosubmit()" method="post">

    <table>
        <tr>
            <td>金额：</td>
            <td><input type="text" name="money" id="money"></td>
            <td></td>
        </tr>
        <tr>
            <td>一级类别：</td>
            <td><input type="text" name="class1" id="class1"></td>
            <td></td>
        </tr>
        <tr>
            <td>二级类别：</td>
            <td><input type="text" name="class2" id="class2"></td>
            <td></td>
        </tr>
        <tr>
            <td>注释：</td>
            <td><input type="text" name="comment" id="comment" placeholder="费用内容注释"></td>
            <td></td>
        </tr>
        <tr>
            <td>日期：</td>
            <td><input type="date" name="thedate" id="thedate"></td>
            <td></td>
        </tr>
        <tr>
            <td>项目：</td>
            <td><input type="text" name="item" id="item"></td>
            <td></td>
        </tr>
        <tr>
            <td>人员：</td>
            <td><input type="text" name="person" id="person"></td>
            <td></td>
        </tr>
    </table>
    <hr>
    <input type="submit" value="提交" class="large button"/><input type="button" value="取消" style="padding: 4px 24px;"/>
</form>

</body>
</html>


<script type="text/javascript">
    function initial() {

    }

    function dosubmit() {
        var event = window.event || event;

        d = document.getElementById("money").value;
        if (valid(d) == false) {
                     event.preventDefault();
        }
        var t1 = document.getElementById("money");
        var t2 = document.getElementById("class1");
        var t3 = document.getElementById("class2");
        var t4 = document.getElementById("comment");
        var t5 = document.getElementById("item");
        var t6 = document.getElementById("person");
        var t7 = document.getElementById("thedate");
        if (t1.value.length < 1) {
            //var event = window.event || event;
            if (event.preventDefault) {
                event.preventDefault();
            } else {
                event.returnValue = false;
            }
            console.log("dosubmit : " );
            return false;
        }
    }

    function valid(d) {
        console.log("d ; " + d);
        console.log("d: " + d.length);
        if (isNaN(Number(d)) || d.length == 0) {
            //当输入不是数字的时候，Number后返回的值是NaN;然后用isNaN判断。
            return false;
        } else {
            return true;
        }

    }
</script>
<script type="text/javascript">

</script>

