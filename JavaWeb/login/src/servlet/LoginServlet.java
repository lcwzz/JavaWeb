package servlet;

import bean.User;
import dao.UserDAO;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");

        /*//获取请求参数
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        //封装请求参数
        User user = new User(0, name, password);*/

        //获取请求参数
        Map<String, String[]> parameterMap = req.getParameterMap();
        //封装请求参数
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用login
        User login_user = new UserDAO().login(user);
        //请求转发
        if (login_user == null) {
            //登陆失败
            req.getRequestDispatcher("/failServlet").forward(req, resp);
        } else {
            //登陆成功
            req.setAttribute("user", login_user);
            req.getRequestDispatcher("/successServlet").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
