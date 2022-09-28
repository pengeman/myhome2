<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html id="html" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>#title</title>
    <meta name="description" content="使用vue.js+freemark制作网页">
    <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
    <#--    <script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>-->
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>

<hr/>
<div id="dosetup">
    名称：<input id="name" type="text" v-model="name" placeholder="网站名称">
    地址：<input type="text" v-model="url" placeholder="网站url"/>
    <button v-on:click="dosetup">提交</button>
</div>

<hr/>
<div id="bookmarklist"> <#--    bookmark列表-->
    <table border="1">
        <tr>
            <td>名称</td>
            <td>链接</td>
        </tr>
        <tr v-for="row in bookmarks">
            <td>{{row.name}}</td>
            <td>{{row.url}}</td>
        </tr>
    </table>
</div>
</body>
</html>


<script type="text/javascript">
    var vm = new Vue({
        el: "#dosetup",
        data: {
            name: '',
            url: ''
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
                let that = this

                // todo : 提交新记录
                $.ajax({
                    type: "POST",
                    url: "dosetup",
                    data: "name=" + this.name + "&url=" + this.url,
                    dataType: "text",
                    success: function (data, status) {
                        // console.log("result=" + data + status);
                        alert("result:");
                        // todo   刷新页面

                        $.ajax({
                            type: "GET",
                            url: "allbookmarks",
                            success: function (result) {
                                bks.bookmarks = JSON.parse(result);

                                console.log(result)
                            }
                        });
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert(textStatus);
                        console.log(errorThrown)
                        console.log("error: " + XMLHttpRequest + textStatus)
                    }
                });


            }
        }
    });

    var bks = new Vue({  // 所有数据库中的bookmark列表
        el: "#bookmarklist",
        data: {
            //bks:[{"id":1,"name":"163","url":"www.163.com"},{"id":2,"name":"163","url":"www.163.com"}],
            bookmarks: [
                {}
            ]
        },
        created: function () {
            let that = this

            $.ajax({
                url: "allbookmarks",
                success: function (result) {
                    that.bookmarks = JSON.parse(result);
                }
            });
        }
    });


</script>