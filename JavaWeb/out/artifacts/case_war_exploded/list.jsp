<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        window.onload = function () {
            //点击删除选中则提交表单
            document.getElementById("delSelected").onclick = function () {
                //确认删除
                if (confirm("您确认删除吗？")) {
                    document.getElementById("form").submit();
                }
            };

            //点击第一个checkbox全选
            document.getElementById("firstCb").onclick = function () {
                //获取所有的checkbox
                var cbs = document.getElementsByName("uid");
                //遍历
                for (var i = 0; i < cbs.length; i++) {
                    //设置每一个cb的状态和第一个cb的状态一样
                    cbs[i].checked = this.checked;
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left">
        <form class="form-inline" action="${pageContext.request.contextPath}/userListServlet" method="post">
            <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" class="form-control" value="${requestScope.condition.name[0]}" name="name" id="name">
            </div>
            <div class="form-group">
                <label for="address">籍贯</label>
                <input type="text" class="form-control" value="${requestScope.condition.address[0]}" name="address" id="address">
            </div>
            <div class="form-group">
                <label for="email">邮箱</label>
                <input type="text" class="form-control" value="${requestScope.condition.email[0]}" name="email" id="email">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <div style="float: right; margin: 5px;">

        <a class="btn btn-primary" href="add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>

    </div>

    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach var="user" items="${requestScope.page.list}" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="uid" value="${user.id}"></td>
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td>
                            <%--回显数据，先查询用户信息，然后再转发到update.jsp--%>
                        <a class="btn btn-default btn-sm" href=${pageContext.request.contextPath}/userFindServlet?id=${user.id}>修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/userDeleteServlet?id=${user.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <!-- 导航 -->
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <!-- 前进 -->
                <c:if test="${requestScope.page.currentPage != 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/userListServlet?curPage=${requestScope.page.currentPage-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:if test="${requestScope.page.currentPage == 1}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/userListServlet?curPage=${requestScope.page.currentPage-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <!-- 中间 -->
                <c:forEach var="i" begin="1" end="${requestScope.page.pages}" step="1">
                    <c:if test="${requestScope.page.currentPage == i}">
                        <li  class="active">
                            <a href="${pageContext.request.contextPath}/userListServlet?curPage=${i}">
                                    ${i}
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${requestScope.page.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/userListServlet?curPage=${i}">
                                    ${i}
                            </a>
                        </li>
                    </c:if>

                </c:forEach>

                <!-- 后退 -->
                <c:if test="${requestScope.page.currentPage != requestScope.page.pages}">
                    <li>
                        <a href="${pageContext.request.contextPath}/userListServlet?curPage=${requestScope.page.currentPage+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:if test="${requestScope.page.currentPage == requestScope.page.pages}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/userListServlet?curPage=${requestScope.page.currentPage+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>

                <span style="font-size: 25px; margin-left: 10px">
                    共${requestScope.page.count}条记录，共${requestScope.page.pages}页
                </span>
            </ul>
        </nav>
    </div>

</div>
</body>
</html>

