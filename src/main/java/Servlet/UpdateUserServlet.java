package Servlet;

import Bean.User;
import Dao.UserDao;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//更新用户Servlet
@WebServlet(urlPatterns = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取元素
        String account=request.getParameter("account");
        int uid=Integer.parseInt(request.getParameter("uid"));
        String updateType=request.getParameter("update-type");
        UserDao userDao=new UserDao();
        User user=null;
        try {
            //取得用户
            user=userDao.getUser(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user==null){
            //数据库没有这个用户
            out.print("<script charset='utf-8' type='text/javascript'>");
            out.print("alert('无此用户!');");//弹窗提示
            out.print("window.opener=null;");//关闭页面
            out.print("window.open('','_self');");
            out.print("window.close();");
            out.print("</script>");
            return;
        }
        //判断更新请求的类型是删除还是保存
        if(updateType.equals("save")){
            String email=request.getParameter("email");
            try {
                if(userDao.isExist(email)!=uid){
                    out.print("<script charset='utf-8' type='text/javascript'>");
                    out.print("alert('该邮箱已被他人注册!');");//弹窗提示
                    out.print("window.opener=null;");//关闭页面
                    out.print("window.open('','_self');");
                    out.print("window.close();");
                    out.print("</script>");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String userName=request.getParameter("name");
            String phone=request.getParameter("phone");
            int sex=Integer.parseInt(request.getParameter("sex"));
            user.setEmail(email);
            user.setUserName(userName);
            user.setPhone(phone);
            user.setSex(sex);
            if(account.equals("administrator")){
                //还有东西
                String password=request.getParameter("password");
                user.setUserPwd(password);
            }
            if(userDao.updateUser(user)>0){
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('保存成功!');");//弹窗提示
                out.print("window.opener=null;");//关闭页面
                out.print("window.open('','_self');");
                out.print("window.close();");
                out.print("</script>");
            }else{
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('保存失败!');");//弹窗提示
                out.print("window.opener=null;");//关闭页面
                out.print("window.open('','_self');");
                out.print("window.close();");
                out.print("</script>");
            }
        }else if(updateType.equals("delete")){
                if(userDao.deleteUser(user.getEmail())>0){
                    out.print("<script charset='utf-8' type='text/javascript'>");
                    out.print("alert('删除成功!');");//弹窗提示
                    out.print("window.opener=null;");//关闭页面
                    out.print("window.open('','_self');");
                    out.print("window.close();");
                    out.print("</script>");
                } else{
                    out.print("<script charset='utf-8' type='text/javascript'>");
                    out.print("alert('删除失败!');");//弹窗提示
                    out.print("window.opener=null;");//关闭页面
                    out.print("window.open('','_self');");
                    out.print("window.close();");
                    out.print("</script>");
                }
        }else{
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('非法更新类型!');");//弹窗提示
                out.print("window.opener=null;");//关闭页面
                out.print("window.open('','_self');");
                out.print("</script>");
                out.print("window.close();");
        }
    }
}
