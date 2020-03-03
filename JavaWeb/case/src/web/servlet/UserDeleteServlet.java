package web.servlet;

import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        //2.调用UserService中的deleteUserById
        new UserServiceImpl().deleteUserById(id);
        //3.重定向到UserListServlet
        response.sendRedirect(request.getContextPath() + "/userListServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
