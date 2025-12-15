-- 创建数据库（需要在MySQL中手动执行）
CREATE DATABASE IF NOT EXISTS user_management_db;
USE user_management_db;

-- 创建用户表
CREATE TABLE IF NOT EXISTS users (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

-- 插入测试用户数据
INSERT INTO users (username, password, email, status) VALUES
                                                          ('admin', 'admin123', 'admin@example.com', 'ACTIVE'),
                                                          ('testuser', 'test123', 'test@example.com', 'ACTIVE');