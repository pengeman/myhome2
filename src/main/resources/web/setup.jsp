<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>setup</title>
</head>
<body>
<p>
    <a href="setup.do?p=new"> 新增 </a>.
</p>
<hr/>
<table border="1">
    <tr>
        <td>名称</td><td>链接</td><td></td><td></td>
    </tr>
<c:forEach items="${bookmarks}" var="bookmark">
    <tr>
    <td>${bookmark.name}</td>
    <td>${bookmark.url}</td>
    <td><a href="setup.do?p=update_go&id=${bookmark.id}&name=${bookmark.name}&url=${bookmark.url}">[修改]</a></td>
    <td><a href="setup.do?p=delete&id=${bookmark.id}">[删除]</a><br></td>
    </tr>
</c:forEach>
</table>
</body>
</html>