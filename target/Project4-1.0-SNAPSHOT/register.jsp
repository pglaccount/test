<%--
  Created by IntelliJ IDEA.
  User: 无意穿堂风
  Date: 2022/6/1/0001
  Time: 14:32
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
    <h1 class="text-center">注册页面</h1>
    <form action="${pageContext.request.contextPath}/user/register" method="post">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" name="username" class="form-control" placeholder="请输入用户名" id="username">
        </div>
        <div class="form-group">
            <label for="pwd">密码：</label>
            <input type="password" class="form-control" name="password" placeholder="请输入密码" id="pwd">
        </div>
        <button type="submit" class="btn btn-primary">提交</button>
    </form>
</div>

<script>
    if ("${msg}"){

        alert("${msg}")
    }
</script>
</body>
</html>
