package Servlet;

import Bean.LeaveTable;
import Bean.User;
import Dao.LeaveTableDao;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
//请假（添加请假表）Servlet
@WebServlet(urlPatterns = "/LeaveServlet")
public class LeaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置格式和内容类型
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();//得到输出流
        HttpSession session=request.getSession();//得到session
        ServletContext application =super.getServletContext();//得到application
        //获得元素
        String begin=request.getParameter("begin");
        String end=request.getParameter("end");
        String reason=request.getParameter("reason");
        User user=(User)session.getAttribute("user");
        //处理请求
        LeaveTable lt=new LeaveTable(user.getUid(),user.getUserName(),user.getEmail(),begin,end,reason,"待审批");
        LeaveTableDao ltDao=new LeaveTableDao();
        if(ltDao.saveLeaveTable(lt)>0){
            //保存成功
            out.print("<script charset='utf-8' type='text/javascript'>");
            out.print("alert('请假已申请!');");//弹窗提示
            out.print("window.location='userMain.jsp';");//跳转页面
            out.print("</script>");
        }
        else{
            //保存失败
            out.print("<script charset='utf-8' type='text/javascript'>");
            out.print("alert('申请失败!');");//弹窗提示
            out.print("window.location='userMain.jsp';");//跳转页面
            out.print("</script>");
        }
    }
}
