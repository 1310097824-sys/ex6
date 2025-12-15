<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>人力资源档案</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f7fa; padding: 20px; }
        h1 { margin-top: 0; }
        .flex { display: flex; gap: 20px; flex-wrap: wrap; }
        .card { background: #fff; padding: 16px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); flex: 1; min-width: 320px; }
        label { display: block; font-weight: bold; margin-bottom: 6px; }
        input, select, textarea { width: 100%; padding: 8px; border: 1px solid #d0d7de; border-radius: 4px; box-sizing: border-box; }
        button { padding: 10px 14px; background: #1cc88a; color: #fff; border: none; border-radius: 6px; cursor: pointer; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { border: 1px solid #e1e4e8; padding: 8px; text-align: left; }
        th { background: #f0f3f6; }
        .actions form { display: inline; }
    </style>
</head>
<body>
<h1>人力资源档案</h1>
<div class="flex">
    <div class="card" style="max-width: 420px;">
        <h3>档案登记/变更</h3>
        <form action="${pageContext.request.contextPath}/archives" method="post">
            <label>姓名</label>
            <input type="text" name="fullName" required>
            <label>一级机构</label>
            <select name="level1Id" required>
                <c:forEach items="${level1List}" var="org"><option value="${org.id}">${org.name}</option></c:forEach>
            </select>
            <label>二级机构</label>
            <select name="level2Id" required>
                <c:forEach items="${level2List}" var="org"><option value="${org.id}">${org.name}</option></c:forEach>
            </select>
            <label>三级机构</label>
            <select name="level3Id" required>
                <c:forEach items="${level3List}" var="org"><option value="${org.id}">${org.name}</option></c:forEach>
            </select>
            <label>职位</label>
            <select name="positionId" required>
                <c:forEach items="${positions}" var="pos"><option value="${pos.id}">${pos.name}</option></c:forEach>
            </select>
            <label>照片 URL（可选）</label>
            <input type="text" name="photoUrl">
            <label>备注</label>
            <textarea name="notes"></textarea>
            <button type="submit">提交审核</button>
        </form>
    </div>
    <div class="card" style="flex: 2;">
        <h3>档案列表</h3>
        <form method="get" action="${pageContext.request.contextPath}/archives" style="margin-bottom: 10px;">
            <input type="text" name="keyword" placeholder="姓名/编号" value="${param.keyword}" style="width: 200px;">
            <select name="status">
                <option value="">全部状态</option>
                <option value="PENDING_REVIEW" ${param.status=='PENDING_REVIEW'?'selected':''}>待复核</option>
                <option value="NORMAL" ${param.status=='NORMAL'?'selected':''}>正常</option>
                <option value="DELETED" ${param.status=='DELETED'?'selected':''}>已删除</option>
            </select>
            <button type="submit">查询</button>
        </form>
        <table>
            <tr><th>ID</th><th>编号</th><th>姓名</th><th>职位</th><th>状态</th><th>操作</th></tr>
            <c:forEach items="${archives}" var="archive">
                <tr>
                    <td>${archive.id}</td>
                    <td>${archive.archiveNo}</td>
                    <td>${archive.fullName}</td>
                    <td>${archive.positionId}</td>
                    <td>${archive.status}</td>
                    <td class="actions">
                        <form action="${pageContext.request.contextPath}/archives/${archive.id}/review" method="post">
                            <input type="hidden" name="action" value="approve">
                            <button type="submit">复核通过</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/archives/${archive.id}/delete" method="post">
                            <button type="submit">删除</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/archives/${archive.id}/restore" method="post">
                            <button type="submit">恢复</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
