package web.servlet;

import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取要删除数据的id
        String[] uids = request.getParameterValues("uid");
        //2.调用UserService中的delSelected方法
        if(uids != null && uids.length!= 0) {
            new UserServiceImpl().delSelected(uids);
        }
        //3.跳转到UserListServlet
        response.sendRedirect(request.getContextPath() + "/userListServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
