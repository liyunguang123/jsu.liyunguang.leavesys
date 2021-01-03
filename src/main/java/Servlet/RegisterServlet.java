package Servlet;

import Bean.User;
import Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//注册Servlet
@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取元素
        String email=request.getParameter("register-email");
        String name=request.getParameter("register-name");
        String phone=request.getParameter("register-phone");
        String sex=request.getParameter("register-sex");
        String password=request.getParameter("register-password");
        UserDao dao=new UserDao();
        try {
            //判断邮箱
            if(dao.isExist(email)>0){
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('邮箱已注册！');");
                out.print("window.location='login.jsp';");
                out.print("</script>");
            }else{
                //保存用户
                User user=new User(name,email,phone,password,Integer.parseInt(sex));
                int result=dao.saveUser(user);
                if(result>0){
                    out.print("<script charset='utf-8' type='text/javascript'>");
                    out.print("alert('注册成功！');");
                    out.print("window.location='login.jsp';");
                    out.print("</script>");
                }
                else{
                    out.print("<script charset='utf-8' type='text/javascript'>");
                    out.print("alert('注册失败！');");
                    out.print("window.location='register.jsp';");
                    out.print("</script>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
