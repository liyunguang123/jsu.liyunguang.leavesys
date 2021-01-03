<%@ page import="java.io.PrintWriter" %>
<%@ page import="Bean.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/29
  Time: 8:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            background:dodgerblue;
        }
        .head .menu li{
            height:30px;
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
            left:30px;
            width: 400px;
        }
        .serch-frame{
            position: absolute;
            border: solid black 1px;
            width:200px;
            height: 140px;
            left:30px;
            top:100px;
            background: skyblue;
            margin: 10px;
        }
        .serch-frame>form{
            position: relative;
            left:20px;
            top:30px;
        }
        .button-serch{
            position: relative;
            top:20px;
            left:40px
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
            <a href="adminMain.jsp">
                <li class="clickAble home">
                    系统主页</li></a>
            <a href="register.jsp" target="_blank">
                <li class="clickAble add">添加用户</li>
            </a>
        </ul>
    </div>
        <div class='right'>
        <ul class="right menu">
            <li class="username" width="200px">管理员：
                <c:out value="${sessionScope.adminName}"></c:out>
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
    </div>
    <div class="serch-frame">
        <form target="_blank" action="userUpdate.jsp" onsubmit="return check_uid()">
            <div class="form-inner">
                <input type="hidden" value="administrator" name="account">
                <div class="label uid">
                    <span>查询用户：</span>
                </div>
                <div class="text uid">
                    <input class="serch-uid" type="text" name="uid" placeholder="请输入用户uid">
                </div>
                <div class="button-serch">
                    <input type="submit" value="查询" onsubmit="return ">
                </div>
            </div>
        </form>
    </div>
    <script  charset="UTF-8" type="text/javascript">
    function changeView(){
        var select=document.querySelector(".select-type");
        var view=document.querySelector(".view");
        view.setAttribute('src',"userLeaveTable.jsp?type="+select.value);
    }
    function check_uid() {
        var uid=document.querySelector(".serch-uid");
        if(!uid.value){
            alert("请输入uid!");
            return false;
        }else{
            return true;
        }
    }
</script>
</body>
</html>
