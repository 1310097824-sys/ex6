<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>薪酬标准</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f7f8fc; padding: 20px; }
        .section { background: #fff; padding: 18px; margin-bottom: 18px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); }
        label { display: block; font-weight: bold; margin-bottom: 6px; }
        input, select, textarea { width: 100%; padding: 8px; border: 1px solid #d0d7de; border-radius: 4px; box-sizing: border-box; }
        button { padding: 10px 14px; background: #4e73df; color: #fff; border: none; border-radius: 6px; cursor: pointer; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { border: 1px solid #e1e4e8; padding: 8px; }
        th { background: #f0f3f6; }
        form.inline { display: inline; }
    </style>
</head>
<body>
<h1>薪酬标准管理</h1>
<div class="section">
    <h3>登记薪酬标准</h3>
    <form action="${pageContext.request.contextPath}/salary-standards" method="post">
        <label>员工档案</label>
        <select name="employeeId" required>
            <c:forEach items="${archives}" var="archive">
                <option value="${archive.id}">${archive.fullName}（${archive.archiveNo}）</option>
            </c:forEach>
        </select>
        <label>基本工资</label>
        <input type="number" name="baseSalary" step="0.01" required>
        <label>复核意见（可选）</label>
        <textarea name="comment"></textarea>
        <button type="submit">提交复核</button>
    </form>
</div>
<div class="section">
    <h3>标准列表</h3>
    <table>
        <tr>
            <th>ID</th><th>标准号</th><th>员工ID</th><th>基本工资</th><th>三险一金</th><th>合计</th><th>状态</th><th>操作</th>
        </tr>
        <c:forEach items="${standards}" var="std">
            <tr>
                <td>${std.id}</td>
                <td>${std.standardNo}</td>
                <td>${std.employeeId}</td>
                <td>${std.baseSalary}</td>
                <td>${std.pension + std.medical + std.unemployment + std.housing}</td>
                <td>${std.totalPayable}</td>
                <td>${std.status}</td>
                <td>
                    <form class="inline" action="${pageContext.request.contextPath}/salary-standards/${std.id}/review" method="post">
                        <input type="hidden" name="status" value="APPROVED">
                        <button type="submit">复核通过</button>
                    </form>
                    <form class="inline" action="${pageContext.request.contextPath}/salary-standards/${std.id}/review" method="post">
                        <input type="hidden" name="status" value="PENDING_REVIEW">
                        <input type="hidden" name="comment" value="退回修改">
                        <button type="submit">退回</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
