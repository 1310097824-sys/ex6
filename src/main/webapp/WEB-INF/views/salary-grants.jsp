<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>薪酬发放</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f7f8fc; padding: 20px; }
        .section { background: #fff; padding: 18px; margin-bottom: 18px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); }
        label { display: block; font-weight: bold; margin-bottom: 6px; }
        input, select, textarea { width: 100%; padding: 8px; border: 1px solid #d0d7de; border-radius: 4px; box-sizing: border-box; }
        button { padding: 10px 14px; background: #1cc88a; color: #fff; border: none; border-radius: 6px; cursor: pointer; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { border: 1px solid #e1e4e8; padding: 8px; }
        th { background: #f0f3f6; }
        form.inline { display: inline; }
    </style>
</head>
<body>
<h1>薪酬发放管理</h1>
<div class="section">
    <h3>登记发放</h3>
    <form action="${pageContext.request.contextPath}/salary-grants" method="post">
        <label>关联薪酬标准</label>
        <select name="standardId" required>
            <c:forEach items="${standards}" var="std">
                <option value="${std.id}">${std.standardNo}（员工 ${std.employeeId}）</option>
            </c:forEach>
        </select>
        <label>奖励金额</label>
        <input type="number" name="reward" value="0" step="0.01" required>
        <label>应扣金额</label>
        <input type="number" name="deduction" value="0" step="0.01" required>
        <label>复核意见（可选）</label>
        <textarea name="comment"></textarea>
        <button type="submit">提交复核</button>
    </form>
</div>
<div class="section">
    <h3>发放列表</h3>
    <table>
        <tr>
            <th>ID</th><th>发放单号</th><th>标准ID</th><th>奖励</th><th>扣款</th><th>实发</th><th>状态</th><th>操作</th>
        </tr>
        <c:forEach items="${grants}" var="grant">
            <tr>
                <td>${grant.id}</td>
                <td>${grant.grantNo}</td>
                <td>${grant.standardId}</td>
                <td>${grant.reward}</td>
                <td>${grant.deduction}</td>
                <td>${grant.finalAmount}</td>
                <td>${grant.status}</td>
                <td>
                    <form class="inline" action="${pageContext.request.contextPath}/salary-grants/${grant.id}/review" method="post">
                        <input type="hidden" name="status" value="APPROVED">
                        <input type="hidden" name="reward" value="${grant.reward}">
                        <input type="hidden" name="deduction" value="${grant.deduction}">
                        <button type="submit">复核通过</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
