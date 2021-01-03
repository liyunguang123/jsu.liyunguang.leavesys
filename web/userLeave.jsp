<%@ page import="Bean.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/28
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请假界面</title>
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
            top:-30px;
            left:70px;
        }
        .form{
            position: absolute;
            width: 550px;
            height: 600px;
            left:500px;
            top:120px;
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
    <form action="LeaveServlet" method="post" onsubmit="return check_form()">
        <!--表单内部-->
        <div class="form-inner">
            <div class="label email">
                <span>邮箱：	</span>
            </div>
            <div class="text email">
               <%
                   User user=(User)session.getAttribute("user");
                   out.print(user.getEmail());
               %>
            </div>
            <div class="label name">
                <span>姓名：</span>
            </div>
            <div class="text name">
                <%
                    out.print(user.getUserName());
                %>
            </div>
            <div class="label phone">
                <span>电话：</span>
            </div>
            <div class="text phone">
                <%
                    out.print(user.getPhone());
                %>
            </div>
            <div class="Label begin">
                起始时间：
            </div>
            <div class="Text begin">
                <input type="text" name="begin" class="leave-begin">
            </div>
            <div class="Label end">
                结束时间:
            </div>
            <div class="Text end">
                <input type="text" name="end" class="leave-end">
            </div>
            <div class="label reason">
                请假原因：
            </div>
            <div>
                <textarea class="leave-reason" name="reason" style="height: 200px;width: 400px"></textarea>
            </div>
            <div class="form-button">
                <div class="register-button">
                    <input type="submit" value="请假"/></a>
                </div>
                <div class="cancel-button">
                    <button type="button">
                        <a href="userMain.jsp">取消</a>
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
    <script charset='utf-8' type='text/javascript'>
    //验证整个表单是否填写完整
    function check_form(){
        console.log("验证请假表单");
        var begin=document.querySelector(".leave-begin");
        var end=document.querySelector(".leave-end");
        var reason=document.querySelector(".leave-reason");
        if(!begin.value||!end.value||!reason.value){
            alert("每项都为必填！");
            return false;
        }else{
            return true;
        }
    }
    </script>
</body>
</html>
