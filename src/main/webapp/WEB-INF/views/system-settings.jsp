<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>系统管理</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f6f8; margin: 0; padding: 20px; }
        h2 { margin-top: 0; }
        .section { background: #fff; padding: 20px; margin-bottom: 20px; border-radius: 8px; box-shadow: 0 2px 6px rgba(0,0,0,0.05); }
        form { display: grid; grid-template-columns: repeat(auto-fit, minmax(180px, 1fr)); gap: 10px; align-items: end; }
        label { display: block; font-weight: bold; margin-bottom: 6px; }
        input, select, textarea { width: 100%; padding: 8px; border: 1px solid #d0d7de; border-radius: 4px; box-sizing: border-box; }
        button { padding: 10px 14px; background: #4e73df; color: #fff; border: none; border-radius: 6px; cursor: pointer; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { border: 1px solid #e1e4e8; padding: 8px; text-align: left; }
        th { background: #f0f3f6; }
    </style>
</head>
<body>
<h1>系统管理</h1>
<div class="section">
    <h2>机构设置</h2>
    <form action="${pageContext.request.contextPath}/system/org" method="post">
        <div>
            <label>机构名称</label>
            <input type="text" name="name" required>
        </div>
        <div>
            <label>机构代码</label>
            <input type="text" name="code" required>
        </div>
        <div>
            <label>层级</label>
            <select name="level" required>
                <option value="1">一级</option>
                <option value="2">二级</option>
                <option value="3">三级</option>
            </select>
        </div>
        <div>
            <label>上级机构（可选）</label>
            <select name="parentId">
                <option value="">无</option>
                <c:forEach items="${level1List}" var="org">
                    <option value="${org.id}">${org.name}（一级）</option>
                </c:forEach>
                <c:forEach items="${level2List}" var="org">
                    <option value="${org.id}">${org.name}（二级）</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit">新增机构</button>
    </form>
    <table>
        <tr><th>编号</th><th>名称</th><th>代码</th><th>层级</th><th>上级</th></tr>
        <c:forEach items="${level1List}" var="org">
            <tr><td>${org.id}</td><td>${org.name}</td><td>${org.code}</td><td>一级</td><td>-</td></tr>
        </c:forEach>
        <c:forEach items="${level2List}" var="org">
            <tr><td>${org.id}</td><td>${org.name}</td><td>${org.code}</td><td>二级</td><td>${org.parentId}</td></tr>
        </c:forEach>
        <c:forEach items="${level3List}" var="org">
            <tr><td>${org.id}</td><td>${org.name}</td><td>${org.code}</td><td>三级</td><td>${org.parentId}</td></tr>
        </c:forEach>
    </table>
</div>

<div class="section">
    <h2>职位设置</h2>
    <form action="${pageContext.request.contextPath}/system/position" method="post">
        <div>
            <label>职位名称</label>
            <input type="text" name="name" required>
        </div>
        <div>
            <label>所属三级机构</label>
            <select name="orgId" required>
                <c:forEach items="${level3List}" var="org">
                    <option value="${org.id}">${org.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit">新增职位</button>
    </form>
    <table>
        <tr><th>ID</th><th>职位</th><th>机构ID</th></tr>
        <c:forEach items="${positions}" var="pos">
            <tr><td>${pos.id}</td><td>${pos.name}</td><td>${pos.orgId}</td></tr>
        </c:forEach>
    </table>
</div>

<div class="section">
    <h2>薪酬项目</h2>
    <form action="${pageContext.request.contextPath}/system/salary-item" method="post">
        <div>
            <label>项目名称</label>
            <input type="text" name="name" required>
        </div>
        <div>
            <label>说明</label>
            <textarea name="description"></textarea>
        </div>
        <button type="submit">新增项目</button>
    </form>
    <table>
        <tr><th>ID</th><th>项目</th><th>说明</th></tr>
        <c:forEach items="${salaryItems}" var="item">
            <tr><td>${item.id}</td><td>${item.name}</td><td>${item.description}</td></tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
