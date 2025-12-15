# Spring MVC 用户管理系统

## 项目简介
这是一个基于 Spring MVC 和 JSP 的用户管理系统，实现了用户登录、注册、修改密码和注销功能。

## 技术栈
- JDK 17
- Spring MVC 5.3.29
- MySQL 8.0
- JSP + JSTL
- Tomcat 9.x

## 功能列表
1. ✅ 用户登录（数据库验证）
2. ✅ 用户注册（插入数据库）
3. ✅ 修改密码（验证原密码）
4. ✅ 用户注销（禁用账户，不删除数据）

## 快速开始

### 1. 环境要求
- JDK 17+
- MySQL 8.0+
- Tomcat 9.x+
- IntelliJ IDEA（推荐）

### 2. 数据库设置
```sql
-- 创建数据库
CREATE DATABASE user_management_db;
USE user_management_db;

-- 执行 src/main/resources/schema.sql 中的 SQL 语句