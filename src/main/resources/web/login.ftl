<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>
<h1>登陆</h1>
<div class="container">
        <div class="form-box">
            <!-- 注册 -->
		<form action="/register" method="post" class="register-box hidden">
			<h1>register</h1>
			<input type="text" placeholder="用户名" name="username">
			<input type="email" placeholder="邮箱" name="email">
			<input type="password" placeholder="密码" name="password" id="pw1">
			<input type="password" placeholder="确认密码" name="password" id="pw2">
			<input type="submit" name="" class="but" value="注册" />
		</form>
		<!-- 登录 -->

            <form action="/dologin" method="post" class="login-box">
            	<h1>login</h1>
                <h3><label>${error}</label></h3>
            	<input type="text" placeholder="用户名" name="username">
            	<input type="password" placeholder="密码" name="password">
            	<input type="submit" name="" class="but" value="登录" />
            </form>
        </div>
        <div class="con-box left">
            <h2>欢迎来到<span>MyHome</span></h2>
            <img src="./static/fg1.png" alt="">
            <p>已有账号</p>
            <button id="login">去登录</button>
        </div>
        <div class="con-box right">
            <h2>欢迎来到<span>MyHome</span></h2>
             <img src="./static/yz1.png" alt="">
            <p>没有账号</p>
            <button id="register">去注册</button>
        </div>
    </div>
    <div class="hide"></div>
</body>
</html>


<style>
*{		    /* 初始化 */
		    margin: 0;
		    padding: 0;
		}
		body{
		    /* 100%窗口高度 */
		    height: 100vh;
		    /* 弹性布局 水平+垂直居中 */
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    /* 渐变背景 */
		    background: linear-gradient(200deg,#ffffce,#fff3f5);
		}
		.container{
		    background-color: #fff;
		    width: 650px;
		    height: 415px;
		    border-radius: 5px;
		    /* 阴影 */
		    box-shadow: 5px 5px 5px rgba(0,0,0,0.1);
		    /* 相对定位 */
		    position: relative;
		}
		.form-box{
		    /* 绝对定位 */
		    position: absolute;
		    background-color: #ced0b4;
		    width: 350px;
		    height: 415px;
		    border-radius: 5px;
		    box-shadow: 2px 0 10px rgba(0,0,0,0.1);
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    z-index: 2;
		    /* 动画过渡 加速后减速 */
		    transition: 0.5s ease-in-out;
		}
		.register-box,.login-box{
		    /* 弹性布局 垂直排列 */
		    display: flex;
		    flex-direction: column;
		    align-items: center;
		    width: 100%;
		}
		.hidden{
		    display: none;
		    transition: 0.5s;
		}
		h1{
		    text-align: center;
		    margin-bottom: 25px;
		    /* 大写 */
		    text-transform: uppercase;
		    color: #fff;
		    /* 字间距 */
		    letter-spacing: 5px;
		}
		input{
		    background-color: transparent;
		    width: 70%;
		    color: #fff;
		    border: none;
		    /* 下边框样式 */
		    border-bottom: 1px solid rgba(255,255,255,0.4);
		    padding: 10px 0;
		    text-indent: 10px;
		    margin: 8px 0;
		    font-size: 14px;
		    letter-spacing: 2px;
		}
		input::placeholder{
		    color: #fff;
		}
		input:focus{
		    color: #a262ad;
		    outline: none;
		    border-bottom: 1px solid #a262ad80;
		    transition: 0.5s;
		}
		input:focus::placeholder{
		    opacity: 0;
		}
		.but{
		    width: 70%;
		    margin-top: 35px;
		    background-color: #f6f6f6;
		    outline: none;
		    border-radius: 8px;
		    padding: 13px;
		    color: #a2ad9f;
		    letter-spacing: 2px;
		    border: none;
		    cursor: pointer;
		}
		.but:hover{
		    background-color: #a2ad88;
		    color: #f6f6f6;
		    transition: background-color 0.5s ease;
		}
		.con-box{
		    width: 50%;
		    /* 弹性布局 垂直排列 居中 */
		    display: flex;
		    flex-direction: column;
		    justify-content: center;
		    align-items: center;
		    /* 绝对定位 居中 */
		    position: absolute;
		    top: 50%;
		    transform: translateY(-50%);
		}
		.con-box.left{
		    left: -2%;
		}
		.con-box.right{
		    right: -2%;
		}
		.con-box h2{
		    color: #8e9aaf;
		    font-size: 25px;
		    font-weight: bold;
		    letter-spacing: 3px;
		    text-align: center;
		    margin-bottom: 4px;
		}
		.con-box p{
		    font-size: 12px;
		    letter-spacing: 2px;
		    color: #8e9aaf;
		    text-align: center;
		}
		.con-box span{
		    color: #d3b7d8;
		}
		.con-box img{
		    width: 150px;
		    height: 150px;
		    opacity: 0.9;
		    margin: 40px 0;
		}
		.con-box button{
		    margin-top: 3%;
		    background-color: #fff;
		    color: #a262ad;
		    border: 1px solid #d3b7d8;
		    padding: 6px 10px;
		    border-radius: 5px;
		    letter-spacing: 1px;
		    outline: none;
		    cursor: pointer;
		}
		.con-box button:hover{
		    background-color: #d3b7d8;
		    color: #fff;
		}
</style>

<script type="text/javascript">
    // 要操作到的元素
        let login=document.getElementById('login');
        let register=document.getElementById('register');
        let form_box=document.getElementsByClassName('form-box')[0];
        let register_box=document.getElementsByClassName('register-box')[0];
        let login_box=document.getElementsByClassName('login-box')[0];
        // 去注册按钮点击事件
        register.addEventListener('click',()=>{
            form_box.style.transform='translateX(86%)';
            login_box.classList.add('hidden');
            register_box.classList.remove('hidden');
        });
        // 去登录按钮点击事件
        login.addEventListener('click',()=>{
            form_box.style.transform='translateX(0%)';
            register_box.classList.add('hidden');
            login_box.classList.remove('hidden');
        })
</script>