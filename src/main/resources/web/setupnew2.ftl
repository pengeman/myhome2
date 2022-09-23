<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html id="html" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>#title</title>
    <meta name="description" content="使用vue.js+freemark制作网页">
    <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
    <script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<#--<form action="dosetup" method="post">-->
<#--    名称：<input type="text" name="name"/><br/>-->
<#--    地址：<input type="text" name="url"/><br/>-->
<#--    <input type="submit" value="提交"/>-->
<#--</form>-->
<hr/>
<div id="dosetup">
    名称：<input id="name" type="text" v-model="name" placeholder="网站名称">
    地址：<input type="text" name="url" placeholder="网站url"/>
    <button v-on:click="dosetup">提交</button>
    <textarea rows="3" cols="11" v-model="bookmark"></textarea>
</div>
<hr/>
<table border="1">
    <tr>
        <td>名称</td>
        <td>链接</td>
    </tr>
    <div id="bookmarks">
        <table>
            <template v-for="book in books">
                <tr>
                    <td>book.name</td>
                    <td>book.url</td>
                </tr>
            </template>
        </table>
    </div>


</table>
<hr/>
<div id="bookmarklist"> <#--    bookmark列表-->
    <li v-for="row in bookmarks">
        {{row.name}}--{{row.url}}<br/>
    </li>
</div>
</body>
</html>
<script type="text/javascript">
    var vm = new Vue({
        el: "#dosetup",
        data: {
            bookmark: 'ee3'
        },
        methods: {
            // dosetup1: function () {
            //     axios.get('allbookmarks')
            //         .then(response => bookmark = "response.data")
            //         .catch(function (error) {
            //             alert(error);
            //         });
            // },
            dosetup(event) {
                $.ajax({
                    url: "allbookmarks", success: function (result) {
                        vm.bookmark = result;
                    }
                });
            }
        }
    });

    var bks = new Vue({  // 所有数据库中的bookmark列表
        el:"#bookmarklist",
        data:{
            // bookmarks:[{"id":1,"name":"163","url":"www.163.com"},{"id":2,"name":"163","url":"www.163.com"}]
            bookmarks:['']
        },
        created : function (){
            $.ajax({
                url: "allbookmarks",
                success: function (result) {
                    vm.bookmarks = result;
                }
            });
        }
    });
</script>