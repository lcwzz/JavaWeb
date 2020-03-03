<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>Home</h1>
    <%
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
                    out.write("<h1>欢迎回来，您上次访问时间为" + value + "</h1>");

                    //2.重写value
                    String time = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
                    //对time进行URL编码
                    time = URLEncoder.encode(time, "utf-8");
                    cookie.setValue(time);
                    cookie.setMaxAge(60*60);//保存一小时
                    response.addCookie(cookie);

                    flag = true;
                    break;
                }
            }
        }

        //首次访问
        if (cookies == null || cookies.length == 0 || !flag) {
            //1.响应信息
            out.write("<h1>您好，欢迎您首次访问</h1>");

            //2.写cookie
            String time = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
            //对time进行URL编码
            time = URLEncoder.encode(time, "utf-8");
            Cookie cookie = new Cookie("lastTime", time);
            cookie.setMaxAge(60*60);//保存一小时
            response.addCookie(cookie);
        }
    %>
</body>
</html>
