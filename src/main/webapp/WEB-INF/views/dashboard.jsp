<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>人力资源管理系统 - 控制台</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background: #f7f9fb; }
        header { background: linear-gradient(120deg, #4e73df, #1cc88a); color: white; padding: 20px 30px; }
        .content { padding: 30px; }
        .grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(260px, 1fr)); gap: 20px; }
        .card { background: white; padding: 20px; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        .card h3 { margin-top: 0; color: #2c3e50; }
        .stat { font-size: 26px; font-weight: bold; color: #1cc88a; }
        .links a { display: inline-block; margin: 8px 8px 0 0; padding: 10px 14px; background: #4e73df; color: #fff; border-radius: 6px; text-decoration: none; }
        .links a:hover { opacity: 0.9; }
    </style>
</head>
<body>
<header>
    <h1>人力资源管理系统</h1>
    <p>覆盖档案、薪酬、机构的统一控制台</p>
</header>
<div class="content">
    <div class="grid">
        <div class="card">
            <h3>档案管理</h3>
            <div class="stat">${archiveCount} 条</div>
            <p>待复核、正常、已删除档案集中管理，支持编号自动生成。</p>
            <div class="links">
                <a href="${pageContext.request.contextPath}/archives">进入档案</a>
            </div>
        </div>
        <div class="card">
            <h3>薪酬标准</h3>
            <div class="stat">${standardCount} 条</div>
            <p>基础工资一键计算三险一金，经理复核后生效。</p>
            <div class="links">
                <a href="${pageContext.request.contextPath}/salary-standards">管理标准</a>
            </div>
        </div>
        <div class="card">
            <h3>薪酬发放</h3>
            <div class="stat">${grantCount} 条</div>
            <p>录入奖励/应扣金额，复核后即可发放。</p>
            <div class="links">
                <a href="${pageContext.request.contextPath}/salary-grants">发放记录</a>
            </div>
        </div>
        <div class="card">
            <h3>系统管理</h3>
            <div class="stat">机构/职位/项目</div>
            <p>维护三级机构、职位以及薪酬项目，供各模块联动使用。</p>
            <div class="links">
                <a href="${pageContext.request.contextPath}/system">基础数据</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
