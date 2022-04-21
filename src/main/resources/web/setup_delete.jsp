<%--
  Created by IntelliJ IDEA.
  User: peng
  Date: 2021/10/1
  Time: 下午6:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>setup_delete</title>
</head>
<body>
<div>
<form action="setup.do" method="get">
    <table>
        <tr>
            <td>id</td><td>${id}</td>
        </tr>
        <tr>
            <td>名称</td><td><input type="text" name="name" value="${name}"></td>
        </tr>
        <tr>
            <td>URL</td><td><input type="text" name="url" value="${url}"></td>
        </tr>
        <tr>
            <td>确定要删除吗<button value="yes" onclick="delet('y',${id})"></button><button id="no" onclick="delet('n')">no</button> </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
<script type="text/javascript">
    function delet (yn,id)
    {
        if (yn=='y'){
            //步骤一:创建异步对象
            var ajax = new XMLHttpRequest();
//步骤二:设置请求的url参数,参数一是请求的类型,参数二是请求的url,可以带参数,动态的传递参数starName到服务端
            ajax.open('get','setup.do?p="del"&id='+id);
//步骤三:发送请求
            ajax.send();
//步骤四:注册事件 onreadystatechange 状态改变就会调用
            ajax.onreadystatechange = function () {
                if (ajax.readyState==4 &&ajax.status==200) {
                    //步骤五 如果能够进到这个判断 说明 数据 完美的回来了,并且请求的页面是存在的
                    alert("删除成功");
                }

            }
        }
    }
    function myFunction()
    {
        alert("Hello World!");
    }
</script>