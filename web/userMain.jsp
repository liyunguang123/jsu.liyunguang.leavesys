<%@ page import="java.io.PrintWriter" %>
<%@ page import="Bean.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/29
  Time: 8:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请假管理系统</title>
    <style type="text/css">
        *{
            margin: 0px;
            list-style: none;
            text-decoration: none;
            color: black;
        }
        .head{
            background: dodgerblue;
        }
        .head .menu li{
            height:25px;
            width:140px;
            text-align:center;
            display:inline-block;
            margin:5px;
        }
        .head .menu .clickAble{
            *border: solid black 2px;
            background: deepskyblue;
            cursor: pointer;
        }
        .head .left{
            position: relative;
            left:60px;
            width: 400px;
        }
        .head .right{
            position:absolute;
            top:0px;
            right:30px;
            width: 500px;
        }
        .body{
            position: absolute;
            left:300px;
            width:800px;
        }
        .button-leave{
            position: relative;
            top:40px;
            left:400px;
        }
        .button-leave button{
            width:80px;
            height:40px;
        }
    </style>
</head>
<body>
<div class='head'>
    <div class="left">
        <ul class="left menu">
            <a href="userMain.jsp">
            <li class="clickAble home">
                系统主页</li></a>
            <a target="_blank" href="userUpdate.jsp?account=user&uid=<%=session.getAttribute("uid").toString()%>">
            <li class="clickAble myself">我的信息</li></a>
        </ul>
    </div>
    <div class='right'>
        <ul class="right menu">
            <li class="username">用户：
                ${userName}
            </li>
            <a class="link exit" href="login.jsp" onclick="return confirm('确认退出系统？')">
            <li class='clickAble exit'>
                退出系统
            </li></a>
        </ul>

    </div>
</div>
<div class="body">
    <div class="body-select">
        <span>查看类型：</span>
        <select name="select-type" class="select-type" onchange="changeView()">
            <option value="待审批">待审批</option>
            <option value="已完成">已完成</option>
            <option value="全部">全部</option>
        </select>
    </div>
    <div class="body-frame">
        <iframe class="view"  width="1000px" height="600px"></iframe>
        <script type="text/javascript">
            document.querySelector(".view").setAttribute('src',"userLeaveTable.jsp?type="+document.querySelector('.select-type').value);
        </script>
    </div>
    <div class="button-leave">
        <a href="userLeave.jsp">
            <button type="button">请假</button>
        </a>
    </div>
</div>
    <script  charset="UTF-8" type="text/javascript">
    function changeView(){
        var select=document.querySelector(".select-type");
        var view=document.querySelector(".view");
        view.setAttribute('src',"userLeaveTable.jsp?type="+select.value);
    }
</script>
</body>
</html>
