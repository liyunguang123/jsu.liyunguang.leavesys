<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/28
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
    <style type="text/css">
        .welcome{
            position: relative;
            left: 150px;
            top:50px;
            width: 400px;
        }
        *{
            margin:0px;
            list-style: none;
            text-decoration: none;
        }
        a{
            color: black;
        }
        body{
            margin: 5px;
            background-image: url("images/sea.jpg");
        }
        .text{
            position: relative;
            top:-32px;
            left:70px;
        }
        .email-msg{
            position: absolute;
            top: -10px;
            left: 260px;
        }
        .form{
            position: absolute;
            width: 450px;
            height: 400px;
            left:500px;
            top:200px;
            padding: 10px;
            background: skyblue;
            border: solid black 2px;
        }
        .form-inner{
            position: relative;
            left: 50px;
        }
        .form-inner>div{
            margin: 10px;
        }
        .form-button{
            position: relative;
            left: 50px;
        }
        .form-button div{
            height: 40px;
        }
        .select{
        position: relative;
            top: -30px;
            left:100px;;
            width:80px;
        }
        .form-button{
            position: relative;
            top:-20px;
        }
        .cancel-button{
            position:relative;
            top:-40px;
            left:70px;
        }
    </style>

</head>
<body>
    <div class="welcome">
        <span style="font-size: 40px">请假管理系统</span>
    </div>
    <div class="form">
        <form action="RegisterServlet" method="post" onsubmit="return check_form()">
            <!--表单内部-->
            <div class="form-inner">
                <div class="label email">
                    <span>邮箱：	</span>
                </div>
                <div class="text email">
                    <input type="text" class="register-email" name="register-email" onchange="check_email()" class="register-email"/>
                </div>
                <div class="email-msg">
                    <%--ajax提示邮箱是否已注册信息--%>

                </div>
                <div class="label name">
                    <span>姓名：</span>
                </div>
                <div class="text name">
                    <input type="text" name="register-name" class="register-name"/>
                </div>
                <div class="label phone">
                    <span>电话：</span>
                </div>
                <div class="text phone">
                    <input type="text" name="register-phone" class="register-phone"/>
                </div>
                <div class="label sex">
                    <span>性别：</span>
                </div>
                <select class="select" name="register-sex">
                    <option value="1">男</option>
                    <option value="0">女</option>
                </select>
                <div class="label password pwd1">
                    <span>密码：</span>
                </div>
                <div class="text password pwd1">
                    <input type="password" class="register-password" name="register-password"/>
                </div>
                <div class="label-password pwd2" >
                    <span >确认密码：</span>
                </div>
                <div class="text password pwd2">
                    <input type="password"  class="register-password2" name="register-password2"/>
                </div>
                <div class="form-button">
                    <div class="register-button">
                        <input type="submit" value="注册"/></a>
                    </div>
                    <div class="cancel-button">
                        <button type="button" onclick="shut()">
                            取消
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <script charset='utf-8' type='text/javascript'>
        function getXhr() // 获取 XMLHttpRequest
        {
            var xhr;
            if(window.XMLHttpRequest){
                xhr = new XMLHttpRequest(); // 非ie浏览器
            }else{
                xhr = new ActiveXObject('Microsoft.XMLHttp'); // ie浏览器
            }
            return xhr;
        }
        //ajax验证邮箱是否已存在
        function check_email() {
            // 第一步: 获得 ajax对象
            var xhr = getXhr();
            // 第二步: 发送请求
            console.log("准备发送请求,xhr="+xhr+"邮箱："+document.querySelector('.register-email').value);
            xhr.open('get','CheckEmailServlet?email='+document.querySelector('.register-email').value,true);
            console.log("发送请求");
            // 第三步: ajax函数: 注册一个事件监听器
            xhr.onreadystatechange = function() //此函数为 匿名函数，内部函数
            {
                // 只有ajax对象的readyState值为4时，才能获得服务器返回的数据
                if(xhr.readyState == 4)
                {
                    console.log("获得返回数据");
                    // 获得服务器返回的文本数据
                    var txt = xhr.responseText;
                    // 更新页面
                    document.querySelector(".email-msg").innerHTML = txt;
                }
            }
            //发送
            xhr.send();
        }
        //验证密码是否一致
        function check_password() {
            console.log("验证两次密码是否一致");
            var pwd1=document.querySelector('.register-password');
            var pwd2=document.querySelector('.register-password2');
            if(pwd1.value===pwd2.value) {
                return true;
            }else{
                alert("两次输入密码不一致!");
                return false;
            }
            console.log("验证密码完毕");
        }
        //验证整个表单是否填写完整
        function check_form(){
            console.log("验证表单");
            if(check_password()==false){
                return false;
            }
            var email=document.querySelector('.register-email');
            var name=document.querySelector('.register-name');
            var phone=document.querySelector('.register-phone');
            var pwd=document.querySelector('.register-password');
            if(!email.value||!name.value||!phone.value||!pwd.value){
                alert("每项都为必填！");
                return false;
            }else{
                return true;
            }
        }
        //关闭窗口
        function shut() {
            window.opener=null;
            window.open('','_self');
            window.close();
        }
    </script>
</body>
</html>
