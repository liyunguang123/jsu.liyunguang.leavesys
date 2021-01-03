package Servlet;

import Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//验证邮箱Servlet,用于ajax
@WebServlet(urlPatterns = "/CheckEmailServlet")
public class CheckEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取邮箱
        String email=request.getParameter("email");
        if(email!=null){
            UserDao dao=new UserDao();
            try {
                //查询邮箱是否存在，并返回结果
                if(dao.isExist(email)>0){
                    out.write("此邮箱已注册");
                }
                else{
                    out.write("此邮箱未注册");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            out.write("请填写邮箱");
        }
    }
}
