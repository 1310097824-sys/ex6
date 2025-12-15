<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改密码</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; }
        .container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 0 15px rgba(0,0,0,0.1); width: 380px; }
        h2 { text-align: center; color: #333; margin-bottom: 25px; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; color: #555; font-weight: bold; }
        input[type="password"] { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; font-size: 16px; }
        button { width: 100%; padding: 12px; background-color: #ff9800; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; font-weight: bold; }
        button:hover { background-color: #f57c00; }
        .error { color: #f44336; text-align: center; margin-bottom: 15px; padding: 10px; background: #ffebee; border-radius: 4px; }
        .message { color: #4CAF50; text-align: center; margin-bottom: 15px; padding: 10px; background: #e8f5e9; border-radius: 4px; }
        .back-link { text-align: center; margin-top: 20px; font-size: 14px; }
        .back-link a { color: #2196F3; text-decoration: none; }
        .back-link a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<div class="container">
    <h2>修改密码</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/change-password" method="post">
        <div class="form-group">
            <label for="oldPassword">原密码:</label>
            <input type="password" id="oldPassword" name="oldPassword" required>
        </div>

        <div class="form-group">
            <label for="newPassword">新密码:</label>
            <input type="password" id="newPassword" name="newPassword" required>
        </div>

        <div class="form-group">
            <label for="confirmPassword">确认新密码:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
        </div>

        <button type="submit">修改密码</button>
    </form>

    <div class="back-link">
        <a href="${pageContext.request.contextPath}/home">返回个人主页</a>
    </div>
</div>
</body>
</html>