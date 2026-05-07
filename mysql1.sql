-- 创建数据库
CREATE DATABASE IF NOT EXISTS lesson DEFAULT CHARACTER SET GBK COLLATE GBK_CHINESE_CI;
-- 修改数据库
ALTER DATABASE lesson CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI;
-- 删除数据库
DROP DATABASE IF EXISTS lesson;


-- 创建学生表
CREATE TABLE IF NOT EXISTS student(
	`number` VARCHAR(30) NOT NULL PRIMARY KEY COMMENT '学号，主键',
	name varchar(30) NOT NULL COMMENT '姓名',
	sex TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '性别：0-男，1-女，2-其他',
	age TINYINT(3) UNSIGNED DEFAULT 0 COMMENT '年龄',
	score DOUBLE(5, 2) UNSIGNED COMMENT '成绩'
)ENGINE=InnoDB CHARSET=UTF8 COMMENT='学生表';

-- 将student表名修改为stu
ALTER TABLE student RENAME AS stu;

-- 在`stu`表中添加字段联系电话（phone），列类型为字符串，长度11，非空
ALTER TABLE stu ADD phone VARCHAR(11) NOT NULL COMMENT '电话';

-- 将`stu`表中的`sex`字段的类型设置为`VARCHAR`，长度为2，默认值为男，注释为：性别，男，女，其他
ALTER TABLE stu MODIFY sex VARCHAR(2) DEFAULT '男' COMMENT '性别，男，女，其他';

-- 将`stu`表中`phone`字段修改为`mobile`，属性不变
ALTER TABLE stu CHANGE phone mobile VARCHAR(11) NOT NULL COMMENT '电话';

-- 删除`stu`中的`mobile`
ALTER TABLE stu DROP mobile;

-- 删除数据表stu
DROP TABLE IF EXISTS stu;
