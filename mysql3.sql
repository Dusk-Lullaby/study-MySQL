-- 现有员工表 `emp` ，
-- 包含字段员工编号（no），类型为整数，长度为20，是主键，自增长，非空；
-- 姓名（name），类型为字符串，长度为20，非空；
-- 性别（sex），类型为字符串，长度为2, 默认值为"男"; 
-- 年龄（age），类型为整数，长度为3, 非空；
-- 所属部门（dept），类型为字符串，长度为20, 非空；
-- 薪资（salary），类型为浮点数，长度为10, 小数点后面保留2位有效数字，非空
DROP TABLE IF EXISTS emp;
CREATE TABLE IF NOT EXISTS emp (
	`no` INT(20) AUTO_INCREMENT  NOT NULL PRIMARY KEY COMMENT '编号',
	name VARCHAR(20) NOT NULL COMMENT '姓名',
	sex VARCHAR(2) DEFAULT '男' COMMENT '性别',
	age INT(3) NOT NULL COMMENT '年龄',
	dept VARCHAR(20) NOT NULL COMMENT '所属部门',
	salary DOUBLE(10, 2) NOT NULL
) ENGINE=InnoDB CHARSET=UTF8 COMMENT='员工表';

INSERT INTO emp(`no`, name, sex, age, dept, salary) VALUES (DEFAULT, '张三', '男', 22, '研发部', 13000);
INSERT INTO emp(`no`, name, sex, age, dept, salary) VALUES (DEFAULT, '李刚', '男', 24, '研发部', 14000);
INSERT INTO emp(`no`, name, sex, age, dept, salary) VALUES (DEFAULT, '金凤', '女', 23, '财务部', 8000);
INSERT INTO emp(`no`, name, sex, age, dept, salary) VALUES (DEFAULT, '肖青', '女', 26, '财务部', 9000);
INSERT INTO emp(`no`, name, sex, age, dept, salary) VALUES (DEFAULT, '张华', '男', 28, '研发部', 15000);
INSERT INTO emp(`no`, name, sex, age, dept, salary) VALUES (DEFAULT, '董钰', '女', 24, '研发部', 12000);
INSERT INTO emp(`no`, name, sex, age, dept, salary) VALUES (DEFAULT, '吴梅', '女', 24, '测试部', 9000);
INSERT INTO emp(`no`, name, sex, age, dept, salary) VALUES (DEFAULT, '王玲', '女', 26, '测试部', 9500);
-- 吴梅工作出色被提升为测试主管，薪资调整为11000
UPDATE emp SET salary=11000 WHERE name='吴梅'; 
-- 研发部金凤离职
DELETE FROM emp WHERE name='金凤';
-- 从员工表中查询出平均年龄小于25的部门
SELECT dept FROM emp GROUP BY dept HAVING AVG(age)<25;
-- 从员工表中统计研发部的最高薪资、最低薪资、平均薪资和总薪资
SELECT MAX(salary), MIN(salary), AVG(salary), SUM(salary) FROM emp WHERE dept='研发部';
-- 从员工表统计各个部门的员工数量
SELECT COUNT(*), dept FROM emp GROUP BY dept;
-- 从员工表中查询薪资在10000以上的员工信息并按薪资从高到低排序
SELECT * FROM emp WHERE salary>10000 ORDER BY salary DESC;
-- 从员工表中查询员工信息，每页显示5条员工信息，按薪资从高到低排序，查询第二页员工信息
SELECT * FROM emp ORDER BY salary DESC LIMIT 5, 5;

-- MySQL修改root密码
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '新密码';





