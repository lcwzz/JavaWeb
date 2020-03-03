<%@ page import="bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆成功</title>
</head>
<body>
    <h1>
        <%User user = (User) request.getSession().getAttribute("user");%>
        <%=user.getName()%>欢迎你
    </h1>
</body>
</html>
