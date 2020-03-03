<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <script>
        //绑定单击事件
        window.onload = function () {
            var img = document.getElementById("img");
            img.onclick = change;

            function change() {
                var time = new Date().getTime();
                img.src = "/logincheck/checkCodeServlet?" + time; //加参数不让浏览器访问缓存
            }
        }
    </script>

    <style>
        div {
            color: red;
        }
    </style>
</head>
<body>
    <form action="/logincheck/loginServlet" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="name" placeholder="请输入用户名"></td>
            </tr>

            <tr>
                <td>密码</td>
                <td><input type="password" name="password" placeholder="请输入密码"></td>
            </tr>

            <tr>
                <td>验证码</td>
                <td><input type="text" name="checkCode" placeholder="请输入验证码"></td>
            </tr>

            <tr>
                <td colspan="2"><img id="img" src="/logincheck/checkCodeServlet"></td>
            </tr>

            <tr>
                <td colspan="2"><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>
    <div><%=
    (request.getAttribute("error") == null) ? "" : request.getAttribute("error")
    %></div>
</body>
</html>
