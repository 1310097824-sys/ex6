<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>个人主页</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; margin: 0; padding: 0; }
        .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 20px 30px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .header h1 { margin: 0; font-size: 24px; }
        .user-info { background: white; margin: 30px auto; padding: 25px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); max-width: 800px; }
        .user-info h2 { color: #333; margin-top: 0; border-bottom: 2px solid #f0f0f0; padding-bottom: 15px; }
        .info-row { display: flex; margin-bottom: 15px; }
        .info-label { width: 150px; font-weight: bold; color: #666; }
        .info-value { flex: 1; color: #333; }
        .actions { background: white; margin: 30px auto; padding: 25px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); max-width: 800px; }
        .actions h3 { color: #333; margin-top: 0; margin-bottom: 20px; }
        .action-buttons { display: flex; gap: 15px; flex-wrap: wrap; }
        .btn { padding: 12px 25px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; font-weight: bold; text-decoration: none; display: inline-block; text-align: center; }
        .btn-primary { background-color: #4CAF50; color: white; }
        .btn-primary:hover { background-color: #45a049; }
        .btn-secondary { background-color: #2196F3; color: white; }
        .btn-secondary:hover { background-color: #1976D2; }
        .btn-danger { background-color: #f44336; color: white; }
        .btn-danger:hover { background-color: #d32f2f; }
        .btn-warning { background-color: #ff9800; color: white; }
        .btn-warning:hover { background-color: #f57c00; }
        .logout-form { display: inline; }
    </style>
</head>
<body>
<div class="header">
    <h1>用户管理系统 - 个人主页</h1>
</div>

<div class="user-info">
    <h2>欢迎, ${user.username}!</h2>

    <div class="info-row">
        <div class="info-label">用户ID:</div>
        <div class="info-value">${user.id}</div>
    </div>

    <div class="info-row">
        <div class="info-label">用户名:</div>
        <div class="info-value">${user.username}</div>
    </div>

    <div class="info-row">
        <div class="info-label">邮箱:</div>
        <div class="info-value">${user.email}</div>
    </div>

    <div class="info-row">
        <div class="info-label">账户状态:</div>
        <div class="info-value">
            <c:choose>
                <c:when test="${user.status == 'ACTIVE'}">
                    <span style="color: #4CAF50; font-weight: bold;">● 活跃</span>
                </c:when>
                <c:otherwise>
                    <span style="color: #f44336; font-weight: bold;">● 已禁用</span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="info-row">
        <div class="info-label">注册时间:</div>
        <div class="info-value">
            <fmt:formatDate value="${user.createdAt}" pattern="yyyy年MM月dd日 HH:mm:ss" />
        </div>
    </div>

    <div class="info-row">
        <div class="info-label">最后更新:</div>
        <div class="info-value">
            <fmt:formatDate value="${user.updatedAt}" pattern="yyyy年MM月dd日 HH:mm:ss" />
        </div>
    </div>
</div>

<div class="actions">
    <h3>操作菜单</h3>
    <div class="action-buttons">
        <a href="${pageContext.request.contextPath}/change-password" class="btn btn-primary">修改密码</a>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">退出登录</a>

        <form action="${pageContext.request.contextPath}/logout-account" method="post" class="logout-form">
            <button type="submit" class="btn btn-danger"
                    onclick="return confirm('⚠️ 警告：确定要注销账户吗？您的账户将被禁用，但数据不会被删除。此操作不可逆！')">
                注销账户
            </button>
        </form>
    </div>
</div>
</body>
</html>