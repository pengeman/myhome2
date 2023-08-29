<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html id="html" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>#title</title>
    <meta name="description" content="使用freemark模板制作网页">
</head>
<body>
<form action="dosetup" method="post">
    名称：<input type="text" name="name"/><br/>
    地址：<input type="text" name="url"/><br/>
    <input type="submit" value="提交"/>
</form>

<hr/>
<table border="1">
    <tr>
        <td>名称</td>
        <td>链接</td>
        <td>删除</td>
    </tr>
    <#list rows as bookmark >
        <tr>
            <div id="id" style="display:none">${bookmark.id}</div>
            <td>${bookmark.name}</td>
            <td>${bookmark.url}</td>
            <td><a href="javascript:void(0);" onclick="itemdelete('${bookmark.id}')">删除</a></td>
        </tr>
    </#list>

</table>

</body>
</html>
<script type="text/javascript">
    function itemdelete(id){
        $.post("setupdeleteitem",
            {
                id:id
            },
            function(data,status){
                alert("Data: " + data + "\nStatus: " + status);
                window.location.href="gosetup";
            });
    }
</script>