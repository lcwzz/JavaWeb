<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
        <!-- 隐藏域提交user.id -->
        <input type="hidden" name="id" value="${requestScope.user.id}">

        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name"  readonly="readonly" value="${requestScope.user.name}" />
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${requestScope.user.gender == '男'}">
                <input type="radio" name="gender" value="男" checked />男
                <input type="radio" name="gender" value="女"  />女
            </c:if>

            <c:if test="${requestScope.user.gender == '女'}">
                <input type="radio" name="gender" value="男" />男
                <input type="radio" name="gender" value="女" checked />女
            </c:if>
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${requestScope.user.age}" />
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <c:if test="${requestScope.user.address == '广东'}">
                <select name="address" id="address" class="form-control" >
                    <option value="广东" selected>广东</option>
                    <option value="广西">广西</option>
                    <option value="湖南">湖南</option>
                </select>
            </c:if>

            <c:if test="${requestScope.user.address == '广西'}">
                <select name="address" id="address" class="form-control" >
                    <option value="广东">广东</option>
                    <option value="广西" selected>广西</option>
                    <option value="湖南">湖南</option>
                </select>
            </c:if>

            <c:if test="${requestScope.user.address == '湖南'}">
                <select name="address" id="address" class="form-control" >
                    <option value="广东">广东</option>
                    <option value="广西">广西</option>
                    <option value="湖南" selected>湖南</option>
                </select>
            </c:if>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" name="qq" id="qq" value="${requestScope.user.qq}"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" id="email" value="${requestScope.user.email}"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" id="back" value="返回"/>
        </div>
    </form>

    <script>
        //返回
        document.getElementById("back").onclick = function () {
            history.back();
        }
    </script>
</div>
</body>
</html>
