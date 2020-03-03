package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置相应消息编码
        response.setContentType("text/html;charset=utf-8");

        //获取全部cookie
        Cookie[] cookies = request.getCookies();

        boolean flag = false;

        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                //不是首次访问
                if ("lastTime".equals(name)) {
                    //1.响应信息
                    String value = cookie.getValue();
                    //对value进行解码
                    value = URLDecoder.decode(value, "utf-8");
                    response.getWriter().write("<h1>欢迎回来，您上次访问时间为" + value + "</h1>");

                    //2.重写value
                    String time = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
                    //对time进行URL编码
                    time = URLEncoder.encode(time, "utf-8");
                    cookie.setValue(time);
                    cookie.setMaxAge(60*60*24);//保存一天
                    response.addCookie(cookie);

                    flag = true;
                    break;
                }
            }
        }

        //首次访问
        if (cookies == null || cookies.length == 0 || !flag) {
            //1.响应信息
            response.getWriter().write("<h1>您好，欢迎您首次访问</h1>");

            //2.写cookie
            String time = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
            //对time进行URL编码
            time = URLEncoder.encode(time, "utf-8");
            Cookie cookie = new Cookie("lastTime", time);
            cookie.setMaxAge(60*60*24);//保存一天
            response.addCookie(cookie);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
