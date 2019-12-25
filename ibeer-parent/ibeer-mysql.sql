-- 显示数据库
SHOW DATABASES;
-- 创建数据库
CREATE DATABASES ibeer;

-- 切换数据库
USE ibeer;

-- 删除表 drop table 表名;
-- 创建表
-- 1.用户表（T_A_ACCOUNT）
CREATE TABLE T_A_ACCOUNT (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	-- 编号标识 主键,自动增长
	UID INT UNIQUE,
	-- 用户唯一号	唯一（从10000开始）
	SEX CHAR (1),
	-- 性别（0：女；1：男）	
	CREATEDATE INT,
	-- 注册时间	
	CREATEIP CHAR (15),
	-- 注册IP	
	LASTTIME INT,
	-- 上次登录时间	
	STATUS CHAR (2) -- 状态	0：禁用；1：正常
);

-- 2.	用户认证表(T_A_OAUTH)
CREATE TABLE T_A_OAUTH (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	-- 编号标识	主键,自动增长
	U_ID INT,
	-- 用户唯一号	外键来源于用户表中的UID
	OAUTH_ID VARCHAR (50),
	-- 第三方登录唯一ID	
	OAUTH_TYPE VARCHAR (18),
	-- 第三方登录平台标识(手机号，邮箱，用户名，第三方应用名称（微信，QQ，微博…）)	phone,email,uname,wechat,qq,weibo
	CREDENTIAL VARCHAR (50),
	-- 密码凭证	站内的保存密码，站外的不保存或保存token
	NICKNAME VARCHAR (18),
	-- 昵称	昵称
	AVATAR VARCHAR (120) -- 图像	图像
);

-- 3.	登录记录表(T-A-LOGIN-LIST)
CREATE TABLE T_A_LOGIN_LIST (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	-- 编号标识	主键,自动增长
	U_ID INT,
	-- 用户UID	来源于用户表中ID
	LOGIN_TIME INT,
	-- 登录时间	
	LOGIN_IP CHAR (15),
	-- 登录IP	
	LOGIN_IP_LOOKUP VARCHAR (18) -- IP反查结果	
);

-- 4.	公司信息表(T_A_COMPANY_INFO)
CREATE TABLE T_A_COMPANY_INFO (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	-- 编号标识	主键,自动增长
	U_ID INT,
	-- 用户UID	唯一
	COMPANY_TYPE VARCHAR (2),
	-- 公司类型1:公司	
	COMPANY_NAME VARCHAR (50),
	-- 公司名称	
	CREDIT_ID VARCHAR (18) -- 统一社会信用代码	
);

-- 5.	用户组表 (T_A_GROUP)
CREATE TABLE T_A_GROUP (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	-- 组编号	主键,自动增长
	G_NAME VARCHAR (30),
	-- 组名称	
	G_PARENT INT,
	-- 所属组编号	
	G_DESC VARCHAR (50) -- 组描述	
);

-- 6.	用户组表(T_A_ACCOUNT_GROUP)
CREATE TABLE T_A_ACCOUNT_GROUP (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	-- 编号	主键,自动增长
	U_ID INT,
	-- 用户唯一号	来源于用户标准的UID
	GROUP_ID INT -- 组编号	来源于用户组表中的ID
);

-- 7.	角色主表(T_A_ROLE)
CREATE TABLE T_A_ROLE (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	-- 编号	主键,自动增长
	ROLE_NAME VARCHAR (30),
	-- 角色名称	
	ROLE_DESC VARCHAR (50) -- 角色描述	
);

-- 8.	角色组表(T_A_ROLE_GROUP)
CREATE TABLE T_A_ROLE_GROUP (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	-- 编号	主键,自动增长
	ROLE_ID INT,
	-- 角色编号	来源于角色主表的ID
	GROUP_ID INT -- 组编号	来源于用户组表
);

-- 9.	权限主表(T_A_RIGHT)
CREATE TABLE T_A_RIGHT (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	-- 编号	主键,自动增长
	MENU_NO VARCHAR (10),
	-- 菜单唯一号	
	MENU VARCHAR (18),
	-- 菜单名称	
	PARENT_ID INT,
	-- 上级菜单编号	0:顶级菜单
	MENU_TYPE VARCHAR (2) -- 菜单类型	1:菜单;2:按钮
);

-- 10.	权限明细（T_A_RIGHT_TETAIL）
CREATE TABLE T_A_RIGHT_TETAIL (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	-- 编号	主键,自动增长
	URL VARCHAR (100),
	-- 菜单URL	
	RIGHT_NO VARCHAR (10),
	-- 权限编号	来源于权限主表的菜单唯一号
	ISLOGIN VARCHAR (5),
	-- 是否登录	anon 不需要登录 authc 需要登录
	`DESC` VARCHAR (80),
	-- 描述	
	ISLOG VARCHAR (2) -- 是否记日志	0:不需要;1:需要
);

