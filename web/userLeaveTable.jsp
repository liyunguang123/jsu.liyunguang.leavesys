<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.LeaveTable" %>
<%@ page import="Dao.LeaveTableDao" %>
<%@ page import="Bean.User" %>
<%@ page import="Bean.Administrator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/29
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户请假表</title>
    <style>
        table{
            border: solid  black;
            width: 950px;
        }
    </style>
</head>
<body>
    <table class="userView" border="solid black 2px" cellspacing="0px" cellpadding="10px">
        <tr>
            <th>请假表编号</th>
            <th>用户编号</th>
            <th>用户姓名</th>
            <th>用户邮箱</th>
            <th width="100px">起始时间</th>
            <th width="100px">结束时间</th>
            <th>请假状态</th>
            <th>操作</th>
        </tr>
    <%
        //取得元素,准备处理
        String type=request.getParameter("type");
        String accountType=session.getAttribute("accountType").toString();
        LeaveTableDao dao=new LeaveTableDao();
        ArrayList<LeaveTable> list=null;
        //开始处理，按要求查询表,
        if(accountType.equals("user")){
            User user=(User)session.getAttribute("user");
            list=dao.getLeaveTablesByUid(user.getUid(),type);
        }else{
            list=dao.getLeaveTables(type);
        }
        //更新表格
        for(LeaveTable x:list){
    %>
        <tr>
            <td><%out.print(x.getLid());%></td>
            <td><%out.print(x.getUid());%></td>
            <td><%out.print(x.getUserName());%></td>
            <td><%out.print(x.getEmail());%></td>
            <td><%out.print(x.getBegin());%></td>
            <td><%out.print(x.getEnd());%></td>
            <td><%out.print(x.getState());%></td>
            <%
                if(accountType.equals("user")){
            %>
            <td><a target="_blank" href="seeLeave.jsp?account=user&lid=<%=x.getLid()%>" class="link" style="color: dodgerblue">查看</a></td>
            <%
                }else{
            %>
            <td><a target="_blank" href="seeLeave.jsp?account=administrator&lid=<%=x.getLid()%>" class="link" style="color: dodgerblue">查看</a></td>
            <%
                }
            %>
        </tr>
        <%
        }
    %>
    </table>

</body>
</html>
