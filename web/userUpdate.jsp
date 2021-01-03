<%@ page import="Bean.User" %>
<%@ page import="Dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/28
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
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
            width: 450px;
            height: 600px;
            left:500px;
            top:100px;
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
        .select{
            position: relative;
            top: -30px;
            left:100px;;
            width:80px;
        }
        .form-button{
            position: relative;
            width: 200px;
        }


    </style>

</head>
<body>
    <div class="welcome">
    <span style="font-size: 40px">请假管理系统</span>
</div>
    <div class="form">
    <%
        //获取元素
        int uid=Integer.parseInt(request.getParameter("uid"));
        String account=request.getParameter("account");
        UserDao dao=new UserDao();
        User user=dao.getUser(uid);
        if(user==null){
            out.print("<script charset='utf-8' type='text/javascript'>");
            out.print("alert('无此用户!');");//弹窗提示
            out.print("window.opener=null;");//关闭页面
            out.print("window.open('','_self');");
            out.print("window.close();");
            out.print("</script>");
            return;
        }
    %>
    <form action="UpdateUserServlet?account=<%=account%>&uid=<%=uid%>" method="post" onsubmit="return check_form()">
        <!--表单内部-->
        <div class="form-inner">
            <div class="label uid">
                <span>用户id：	</span>
            </div>
            <div class="text uid">
                <%out.print(user.getUid());%>
            </div>
            <div class="label email">
                <span>邮箱：	</span>
            </div>
            <div class="text email">
                <input type="text" value="<%=user.getEmail()%>"  name="email" class="update-email" onchange="check_email()"/>
            </div>
            <div class="label name">
                <span>姓名：</span>
            </div>
            <div class="text name">
                <input type="text" value="<%=user.getUserName()%>" name="name" class="update-name"/>
            </div>
            <div class="label phone">
                <span>电话：</span>
            </div>
            <div class="text phone">
                <input type="text" value="<%=user.getPhone()%>" name="phone" class="update-phone"/>
            </div>
            <div class="label sex">
                <span>性别：</span>
            </div>
            <select class="select text" name="sex">
                <option class="option-male" value="1">男</option>
                <option class="option-female" value="0">女</option>
                <%
                    if(user.getSex()==0){
                %>
                <script>
                    var female=document.querySelector(".option-female");
                    female.setAttribute("selected","selected");
                </script>
                <%
                    }
                %>
            </select>
            <%
                if(account.equals("administrator")){
                    %>
            <div class="label password pwd1">
                <span>密码：</span>
            </div>
            <div class="text password pwd1">
                <input value="<%=user.getUserPwd()%>" type="password" class="update-password" name="password"/>
            </div>
            <div class="label-password pwd2" >
                <span >确认密码：</span>
            </div>
            <div class="text password pwd2">
                <input value="<%=user.getUserPwd()%>" type="password"  class="update-password2" name="password2"/>
            </div>
            <%
                }
            %>

            <div class="form-button">

                    <input type="submit" value="保存" onclick="change_save()"/>

                <%
                    if(account.equals("administrator")){
                %>

                        <input type="submit" value="删除" ONCLICK="change_delete()" >

                <%
                    }
                %>

                    <button type="button" onclick="shut()">
                        取消
                    </button>

            </div>
        </div>
    </form>
</div>
    <script charset='utf-8' type='text/javascript'>
    //验证密码是否一致
    function check_password() {
        console.log("验证两次密码是否一致");
        var pwd1=document.querySelector('.update-password');
        var pwd2=document.querySelector('.update-password2');
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
        var email=document.querySelector('.update-email');
        var name=document.querySelector('.update-name');
        var phone=document.querySelector('.update-phone');
        var pwd=document.querySelector('.update-password');
        if(!email.value||!name.value||!phone.value||!pwd.value){
            alert("每项都为必填！");
            return false;
        }else{
            return true;
        }
    }
    function shut() {
        window.opener=null;
        window.open('','_self');
        window.close();
    }
    function change_save() {
        var form=document.querySelector("form");
        form.setAttribute("action","UpdateUserServlet?account=<%=account%>&uid=<%=uid%>&update-type=save");
    }
    function change_delete() {
        var form=document.querySelector("form");
        form.setAttribute("action","UpdateUserServlet?account=<%=account%>&uid=<%=uid%>&update-type=delete");
    }
</script>
</body>
</html>
