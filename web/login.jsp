<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/28
  Time: 8:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <style>
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
    .welcome{
        position: relative;
        left: 150px;
        top:50px;
        width: 400px;
    }
    .form{
    position: absolute;
    width: 450px;
    height: 180px;
    left:600px;
    top:200px;
    padding: 10px;
    background: skyblue;
    border: solid black 2px;
    }
    .form-inner{
    position: relative;
    left: 60px;
    }
    .form-inner>div{
    margin: 10px;
    }
    .form-button{
    position: relative;
    top:20px;
    left: 30px;
    }
    .form-button div{
    height: 40px;
    }
    .register-button{
    position:relative;
    top:-40px;
    left:80px;
    }
    </style>
</head>
<body>
<div class="welcome">
    <span style="font-size: 40px">请假管理系统</span>
</div>
<div class="form">
    <form action="LoginServlet" method="post">

        <div class="form-inner">
            <div class="form-email">
                <span>账户：</span>
                <input type="text" name="login-account" placeholder="用户邮箱或管理员编号"/>

            </div>
            <div class="form-password">
                <span >密码：</span>
                <input type="password" name="login-password"/>
            </div>
            <div class="form-select">
                <span>账户类型：</span>
                <select name="accountType" >
                    <option value="user">普通用户</option>
                    <option value="administrator">管理员</option>
                </select>
            </div>
            <div class="form-button">
                <div class="login-button">
                    <input type="submit" value="登陆">
                </div>
                <div class="register-button">
                    <button type="button">
                        <a href="register.jsp" target="_blank">
                            注册
                        </a>
                    </button>
                </div>
            </div>
        </div>
        <script>

        </script>
    </form>

</div>

</body>
</html>
