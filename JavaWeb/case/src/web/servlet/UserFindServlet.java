package web.servlet;

import bean.User;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userFindServlet")
public class UserFindServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        //2.调用UserService中的findUserById
        User user = new UserServiceImpl().findUserById(id);
        //3.将数据存入request域
        request.setAttribute("user", user);
        //4.转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
