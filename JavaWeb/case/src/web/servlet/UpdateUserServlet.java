package web.servlet;

import bean.User;
import org.apache.commons.beanutils.BeanUtils;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        Map<String, String[]> userMap = request.getParameterMap();
        //3.封装数据
        User user = new User();
        try {
            BeanUtils.populate(user, userMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用UserService的updateUser方法
        new UserServiceImpl().updateUser(user);
        //5.跳转到UserListServlet
        response.sendRedirect(request.getContextPath() + "/userListServlet");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
