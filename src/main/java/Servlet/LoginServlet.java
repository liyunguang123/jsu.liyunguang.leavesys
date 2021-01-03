package Servlet;

import Bean.Administrator;
import Bean.User;
import Dao.AdministratorDao;
import Dao.UserDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
//登录Servlet
@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();//得到session
        ServletContext application =super.getServletContext();//GenericServlet提供的方法，得到application
        //取得元素
        String type=request.getParameter("accountType");
        String account=request.getParameter("login-account");
        String password=request.getParameter("login-password");
        //判断登录
        if(type.equals("user")){
            //用户登录
            UserDao dao=new UserDao();
            User user=null;
            try {
                if(dao.isRight(account,password)) {
                    user = dao.getUser(account);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(user!=null){
                //用户登录成功
                session.setAttribute("accountType","user");
                session.setAttribute("userName",user.getUserName());
                session.setAttribute("email",user.getEmail());
                session.setAttribute("uid",user.getUid());
                session.setAttribute("user",user);
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('用户登录成功!');");//弹窗提示
                out.print("window.location='userMain.jsp';");//跳转页面
                out.print("</script>");
            }
            else{
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('登录失败！请检查账号密码!');");
                out.print("window.location='login.jsp';");
                out.print("</script>");
            }
        }
        else {
            //管理员登录
            AdministratorDao dao=new AdministratorDao();
            Administrator admin=null;
            try {
                if(dao.isRight(account,password)){
                    admin=dao.getAdmin(account);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(admin!=null){
                //管理员登录成功，写入session
                session.setAttribute("accountType","administrator");
                session.setAttribute("adminName",admin.getAdminName());
                session.setAttribute("aid",admin.getAid());
                session.setAttribute("admin", admin);
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('管理员登录成功!');");//弹出窗口
                out.print("window.location='adminMain.jsp';");//定位页面
                out.print("</script>");
            }
            else{
                //管理员登录失败
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('登录失败！请检查账号密码!');");
                out.print("window.location='login.jsp';");
                out.print("</script>");
            }
        }
    }
}
