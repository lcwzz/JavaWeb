package web.servlet;

import bean.Page;
import bean.User;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*        //1.调用UserService中的方法完成查询
        UserService userService = new UserServiceImpl();
        List<User> users = userService.findAll();

        //2.将查询结果存入Request域中
        request.setAttribute("users", users);

        //3.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);*/

        request.setCharacterEncoding("utf-8");
        //分页显示
        //1.获取currentPage和rows
        String curPage = request.getParameter("curPage");
        String pageSize = request.getParameter("pageSize");

        //2.如果没有设置参数，currentPage默认为1，rows默认为5
        if (curPage == null || "".equals(curPage)) {
            curPage = "1";
        }
        if (pageSize == null || "".equals(pageSize)) {
            pageSize = "5";
        }
        
        //获取查询条件condition
        Map<String, String[]> condition = request.getParameterMap();

        //3.调用service中的getPage()
        Page<User> page = new UserServiceImpl().getPage(curPage, pageSize, condition);

        //4.将数据存入request域
        request.setAttribute("condition", condition); //为了回显查询条件
        request.setAttribute("page", page);

        //5.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
