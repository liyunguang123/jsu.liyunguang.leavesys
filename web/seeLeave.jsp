<%@ page import="Bean.User" %>
<%@ page import="Bean.LeaveTable" %>
<%@ page import="Dao.LeaveTableDao" %>
<%@ page import="Dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/30
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看请假表</title>
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
            left:100px;
        }
        .form{
            position: absolute;
            width: 550px;
            height: 700px;
            left:500px;
            top:30px;
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
            left: 100px;
        }
        .form-button button{
            height: 30px;
            margin: 20px;
        }

    </style>

</head>
<body>
    <div class="welcome">
    <span style="font-size: 40px">请假管理系统</span>
</div>
    <%
        //获取元素
        String account=request.getParameter("account");
        int lid=Integer.parseInt(request.getParameter("lid"));
        LeaveTableDao dao=new LeaveTableDao();
        LeaveTable lt=dao.getLeaveTableByLid(lid);
        UserDao userDao=new UserDao();
        User user=userDao.getUser(lt.getEmail());
    %>
    <div class="form">
    <form action="UpdateLeaveServlet?lid=<%=lid%>&account=<%=account%>" method="post" onsubmit="return check_form()">
        <!--表单内部-->
        <div class="form-inner">
            <div class="label lid">
                请假表编号：
            </div>
            <div class="text lid">
                <%
                    out.print(lid);
                %>
            </div>
            <div class="label uid">
                用户编号：
            </div>
            <div class="text uid">
                <%
                    out.print(user.getUid());
                %>
            </div>
            <div class="label userName">
                用户名：
            </div>
            <div class="text userName">
                <%
                    out.print(user.getUserName());
                %>
            </div>
            <div class="label email">
                <span>邮箱：	</span>
            </div>
            <div class="text email">
                <%
                    out.print(user.getEmail());
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
            <div class="text begin">
                <%
                    //只有用户可以修改待审批的表
                    if(account.equals("user")&&lt.getState().equals("待审批")){
                        %>
                        <input type="text" name="begin" class="leave-begin" value="<%=lt.getBegin()%>">
                        <%
                    }else{
                        out.print(lt.getBegin());
                    }
                %>
            </div>
            <div class="Label end">
                结束时间:
            </div>
            <div class="text end">
                <%
                    //只有用户可以修改待审批的表
                    if(account.equals("user")&&lt.getState().equals("待审批")){
                %>
                <input type="text" name="end" class="leave-end" value="<%=lt.getEnd()%>">
                <%
                    }else{
                        out.print(lt.getEnd());
                    }
                %>
            </div>
            <div class="label reason">
                请假原因：
            </div>
            <div class="text-area reason" style="height: 50px;width: 400px">
                <%
                    //只有用户可以修改待审批的表
                    if(account.equals("user")&&lt.getState().equals("待审批")){
                %>
                    <textarea class="leave-reason"  name="reason" style="height: 50px;width: 400px"><%out.print(lt.getReason());%></textarea>
                <%
                    }else{
                        out.print(lt.getReason());
                    }
                %>
            </div>
            <%
                if(account.equals("administrator")&&lt.getState().equals("待审批")){
                    //管理员审批请假表
            %>
                <div class="label message">
                    审批信息：
                </div>
                <div class="text-area message">
                    <textarea name="message" class="message" style="height: 50px;width: 400px"></textarea>
                </div>
            <%
                }else if(!lt.getState().equals("待审批")){
             %>
                <div class="label message">
                   审批信息：
                </div>
                <div class="text-area message">
                    <textarea name="message" disabled class="message" style="height: 50px;width: 400px"><%out.print(lt.getMessage());%></textarea>
                </div>
            <%
                }
            %>

            </div>
        <div class="form-button">

                    <%
                        if(account.equals("user")&&lt.getState().equals("待审批")){
                    %>
                    <button type="submit">
                        保存
                    </button>
                    <%
                        }
                        else if(account.equals("administrator")&&lt.getState().equals("待审批")){
                                //管理员审批呆审批的表
                    %>
                    <input class="state" type="hidden" name="state" value="已批准">
                    <button type="submit">
                        批准
                    </button>
                    <button type="submit" onclick="changeState()">
                        拒绝
                    </button>
                    <%
                        }
                    %>


                    <button type="button" onclick="shut()">
                        退出
                    </button>

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
    function shut() {
        window.opener=null;
        window.open('','_self');
        window.close();
    }
    function changeState() {
        document.querySelector(".state").value="已拒绝";
    }
    </script>
</body>
</html>
