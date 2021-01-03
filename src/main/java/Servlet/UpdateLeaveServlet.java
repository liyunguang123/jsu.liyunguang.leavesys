package Servlet;

import Bean.LeaveTable;
import Dao.LeaveTableDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
//更新请假表
@WebServlet(urlPatterns = "/UpdateLeaveServlet")
public class UpdateLeaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取元素
        String account=request.getParameter("account");
        int lid=Integer.parseInt(request.getParameter("lid"));
        LeaveTableDao dao=new LeaveTableDao();
        LeaveTable lt=null;
        try {
            //取得表
            lt=dao.getLeaveTableByLid(lid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(lt==null){
            out.print("<script charset='utf-8' type='text/javascript'>");
            out.print("alert('没有该请假表！');");
            out.print("</script>");
            return;
        }
        //判断，获得要修改的表
        if(account.equals("user")){
            //取得元素
            String begin=request.getParameter("begin");
            String end=request.getParameter("end");
            String reason=request.getParameter("reason");
            //设置元素
            lt.setBegin(begin);
            lt.setEnd(end);
            lt.setReason(reason);
        }
        else{
            String state=request.getParameter("state");
            String message=request.getParameter("message");
            System.out.println(message);
            //修改表的状态
            lt.setState(state);
            lt.setMessage(message);
        }
        try {
            //更新表
            int result=dao.updateLeaveTable(lid,lt);
            if(result>0){
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('请假表更新成功！');");
                out.print("window.opener=null;");//下三行关闭网页
                out.print("window.open('','_self');");
                out.print("window.close();");
                out.print("</script>");
            }
            else{
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('请假表更新失败！');");
                out.print("window.opener=null;");
                out.print("window.open('','_self');");
                out.print("window.close();");
                out.print("</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
