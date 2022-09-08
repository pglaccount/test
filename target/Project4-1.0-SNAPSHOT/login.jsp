<%--
  Created by IntelliJ IDEA.
  User: 无意穿堂风
  Date: 2022/5/27/0027
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<div style="width: 50%;margin: auto">
    <h1 class="text-center">登录页面</h1>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" name="username" class="form-control" placeholder="请输入用户名" id="username">
        </div>
        <div class="form-group">
            <label for="pwd">密码：</label>
            <input type="password" class="form-control" name="password" placeholder="请输入密码" id="pwd">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> 记住密码
            </label>
        </div>
        <button type="submit" class="btn btn-primary">提交</button>
    </form>
    <h3 class="btn btn-primary text-center" onclick="register()">注册页面</h3>
</div>
<script>
    if ("${msg}"){
        alert("${msg}")
    }
    function register() {
        window.location.href = "${pageContext.request.contextPath}/register.jsp"
    }
</script>
</body>
</html>
