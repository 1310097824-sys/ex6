<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理系统</title>
</head>
<body>
<h2>欢迎使用用户管理系统</h2>
<p>正在跳转到登录页面...</p>
<script>
    window.location.href = "${pageContext.request.contextPath}/login";
</script>
</body>
</html>