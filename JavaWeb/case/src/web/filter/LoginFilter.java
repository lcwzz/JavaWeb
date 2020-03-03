package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.类型转换
        HttpServletRequest request = (HttpServletRequest) req;
        //2.排除有关登陆的访问
        String uri = request.getRequestURI();
        if(uri.contains("/login.jsp") || uri.contains("/loginServlet") ||
                uri.contains("/checkCodeServlet") || uri.contains("/css/") ||
                uri.contains("/js/") || uri.contains("/fonts/")) {
            chain.doFilter(req, resp);
        } else {
            //3.判断是否登陆
            //登陆完后会在Session域中存入admin属性，如果有admin则已经登陆
            if (null != request.getSession().getAttribute("admin")) {
                chain.doFilter(req, resp);
            } else {
                //提示未登录信息，转发到登陆页面
                request.setAttribute("error", "用户未登录，请登陆！");
                request.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }

    }

    @Override
    public void destroy() {

    }

}
