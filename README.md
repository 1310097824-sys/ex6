# Spring MVC 人力资源管理系统

## 项目简介
基于 Spring MVC + JSP + MySQL 8 的简易人力资源管理系统，包含机构、职位、薪酬项目、档案、薪酬标准及薪酬发放的登记与复核流程，附带登录模块。

## 功能模块
- 机构/职位/薪酬项目管理（系统管理）
- 员工人事档案：登记 → 复核 → 查询 → 变更 → 逻辑删除/恢复
- 薪酬标准：登记自动计算三险一金 → 复核 → 查询
- 薪酬发放：登记奖励/扣款 → 复核 → 查询
- 用户登录/注册/修改密码

## 快速开始
1. 安装 JDK 17、MySQL 8、Tomcat 9。
2. 在 MySQL 中执行 `src/main/resources/schema.sql` 创建数据库和表。
3. 根据本地数据库账户修改 `src/main/resources/application.properties`。
4. 使用 Maven 打包：`mvn clean package`，将生成的 `user-management.war` 部署到 Tomcat。
5. 访问 `http://localhost:8080/user-management/login` 进行登录，默认账号：admin/admin123。

## 三险一金计算
- 养老保险：基本工资 × 8%
- 医疗保险：基本工资 × 2% + 3 元
- 失业保险：基本工资 × 0.5%
- 住房公积金：基本工资 × 8%
- 总计：基本工资 + 上述四项
