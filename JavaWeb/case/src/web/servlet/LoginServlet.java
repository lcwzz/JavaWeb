package web.servlet;

import bean.Admin;
import service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");

        //2.获取请求参数
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");
        //封装请求参数
        Admin admin = new Admin(name, password);

        //3.判断验证码是否正确
        //获取产生的验证码
        HttpSession session = req.getSession();
        String sessionCheckCode = (String) session.getAttribute("sessionCheckCode");
        //删除sessionCheckCode,保证验证码的一次性(登陆成功后后退，之前验证码失效)
        session.removeAttribute("sessionCheckCode");
        //验证码正确
        if (sessionCheckCode != null && sessionCheckCode.equalsIgnoreCase(checkCode)) {
            //判断用户名密码是否正确
            Admin login_admin = new AdminServiceImpl().login(admin);
            if (login_admin == null) {
                //转发到登陆页面
                req.setAttribute("error", "用户名或密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } else {
                //登陆成功，重定向
                session.setAttribute("admin", login_admin);
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }
        } else { //验证码错误
            //转发到登陆页面
            req.setAttribute("error", "验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
