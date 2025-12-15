-- 数据库初始化脚本
CREATE DATABASE IF NOT EXISTS hr_management_db;
USE hr_management_db;

-- 用户表（用于登录）
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO users (username, password, email, status) VALUES
    ('admin', 'admin123', 'admin@example.com', 'ACTIVE'),
    ('hr_user', 'hr123456', 'hr@example.com', 'ACTIVE')
ON DUPLICATE KEY UPDATE email = VALUES(email);

-- 机构表（支持三级）
CREATE TABLE IF NOT EXISTS org_unit (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(10) NOT NULL,
    level TINYINT NOT NULL,
    parent_id INT DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_org_parent FOREIGN KEY (parent_id) REFERENCES org_unit(id)
);

INSERT INTO org_unit (id, name, code, level, parent_id) VALUES
    (1, '总部', '01', 1, NULL),
    (2, '华北大区', '02', 1, NULL),
    (3, '北京分公司', '11', 2, 2),
    (4, '天津分公司', '12', 2, 2),
    (5, '研发中心', '21', 2, 1),
    (6, '北京一部', '01', 3, 3),
    (7, '北京二部', '02', 3, 3),
    (8, '天津一部', '01', 3, 4),
    (9, '研发一组', '01', 3, 5)
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- 职位表
CREATE TABLE IF NOT EXISTS position (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    org_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_position_org FOREIGN KEY (org_id) REFERENCES org_unit(id)
);

INSERT INTO position (name, org_id) VALUES
    ('人事专员', 6),
    ('人事经理', 6),
    ('薪酬专员', 7),
    ('薪酬经理', 7),
    ('Java 工程师', 9),
    ('架构师', 9)
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- 薪酬项目
CREATE TABLE IF NOT EXISTS salary_item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO salary_item (name, description) VALUES
    ('基本工资', '岗位基础薪资'),
    ('绩效工资', '按绩效发放'),
    ('餐补', '工作餐补贴'),
    ('交通补', '通勤交通补贴')
ON DUPLICATE KEY UPDATE description = VALUES(description);

-- 员工档案
CREATE TABLE IF NOT EXISTS employee_archive (
    id INT PRIMARY KEY AUTO_INCREMENT,
    archive_no VARCHAR(30) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    org_level1_id INT NOT NULL,
    org_level2_id INT NOT NULL,
    org_level3_id INT NOT NULL,
    position_id INT NOT NULL,
    status ENUM('PENDING_REVIEW','NORMAL','DELETED') DEFAULT 'PENDING_REVIEW',
    photo_url VARCHAR(255),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_archive_org1 FOREIGN KEY (org_level1_id) REFERENCES org_unit(id),
    CONSTRAINT fk_archive_org2 FOREIGN KEY (org_level2_id) REFERENCES org_unit(id),
    CONSTRAINT fk_archive_org3 FOREIGN KEY (org_level3_id) REFERENCES org_unit(id),
    CONSTRAINT fk_archive_position FOREIGN KEY (position_id) REFERENCES position(id)
);

-- 薪酬标准
CREATE TABLE IF NOT EXISTS salary_standard (
    id INT PRIMARY KEY AUTO_INCREMENT,
    standard_no VARCHAR(30) UNIQUE NOT NULL,
    employee_id INT NOT NULL,
    base_salary DECIMAL(10,2) NOT NULL,
    pension DECIMAL(10,2) NOT NULL,
    medical DECIMAL(10,2) NOT NULL,
    unemployment DECIMAL(10,2) NOT NULL,
    housing DECIMAL(10,2) NOT NULL,
    total_payable DECIMAL(10,2) NOT NULL,
    status ENUM('PENDING_REVIEW','APPROVED') DEFAULT 'PENDING_REVIEW',
    review_comment VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_standard_employee FOREIGN KEY (employee_id) REFERENCES employee_archive(id)
);

-- 薪酬发放
CREATE TABLE IF NOT EXISTS salary_grant (
    id INT PRIMARY KEY AUTO_INCREMENT,
    grant_no VARCHAR(30) UNIQUE NOT NULL,
    employee_id INT NOT NULL,
    standard_id INT NOT NULL,
    reward DECIMAL(10,2) DEFAULT 0,
    deduction DECIMAL(10,2) DEFAULT 0,
    final_amount DECIMAL(10,2) NOT NULL,
    status ENUM('PENDING_REVIEW','APPROVED') DEFAULT 'PENDING_REVIEW',
    review_comment VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_grant_employee FOREIGN KEY (employee_id) REFERENCES employee_archive(id),
    CONSTRAINT fk_grant_standard FOREIGN KEY (standard_id) REFERENCES salary_standard(id)
);
