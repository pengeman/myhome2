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
    <title>setup_update</title>
</head>
<body>
<div>
<form action="setup.do" method="get">
    <table>
        <tr>
            <td>id</td><td>${id}</td>
            <input type="hidden" value="update_do" name="p">
            <input type="hidden" value="${id}" name="id">
        </tr>
        <tr>
            <td>名称</td><td><input type="text" name="name" value="${name}"></td>
        </tr>
        <tr>
            <td>URL</td><td><input type="text" name="url" value="${url}"></td>
        </tr>
        <tr>
            <td>---</td><td><input type="submit" value="提交">        </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
