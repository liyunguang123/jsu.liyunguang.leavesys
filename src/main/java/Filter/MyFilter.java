package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
//过滤器，进入用户和管理员界面前需要验证登录
@WebFilter(urlPatterns = {"/userMain.jsp","/adminMain.jsp"})
public class MyFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }
    public void destroy() {
    }
    //过滤方法
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //设置
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        String accountType=(String)session.getAttribute("accountType");
        if(accountType!=null){
            //取得url
            String url=request.getRequestURL().toString();
            if(url.contains("userMain.jsp")&&session.getAttribute("user")==null) {
                //访问用户主界面但未登录用户账号
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('请先登录用户账号');");
                out.print("window.location='login.jsp';");
                out.print("</script>");
            }else if(url.contains("adminMain.jsp")&&session.getAttribute("admin")==null){
                //访问用户主界面但未登录用户账号
                out.print("<script charset='utf-8' type='text/javascript'>");
                out.print("alert('请先登录管理员账号');");
                out.print("window.location='login.jsp';");
                out.print("</script>");
            }
        }else{
            //未登陆任何账号
            out.print("<script charset='utf-8' type='text/javascript'>");
            out.print("alert('Please Login!');");
            out.print("window.location='login.jsp';");
            out.print("</script>");
        }
        chain.doFilter(request, response);
    }
}
