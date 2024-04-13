-- ----------------------------
-- 需要新建数据库
-- ----------------------------

-- ----------------------------
-- 创建用户表
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `user_id`     BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `user_name`   VARCHAR(30)  NOT NULL COMMENT '用户账号',
    `nick_name`   VARCHAR(30)  NOT NULL COMMENT '用户昵称',
    `email`       VARCHAR(50)  NULL DEFAULT '' COMMENT '用户邮箱',
    `phonenumber` VARCHAR(11)  NULL DEFAULT '' COMMENT '手机号码',
    `sex`         CHAR(1)      NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `avatar`      VARCHAR(100) NULL DEFAULT '' COMMENT '头像地址',
    `password`    VARCHAR(100) NULL DEFAULT '' COMMENT '密码',
    `status`      CHAR(1)      NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `del_flag`    CHAR(1)      NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `login_ip`    VARCHAR(128) NULL DEFAULT '' COMMENT '最后登录IP',
    `login_date`  DATETIME     NULL DEFAULT NULL COMMENT '最后登录时间',
    `create_by`   VARCHAR(64)  NULL DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME     NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   VARCHAR(64)  NULL DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME     NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表';

-- ----------------------------
-- 增加几条用户信息
-- ----------------------------
INSERT INTO `user`
VALUES (1, 'admin', '管理员', '253546544@qq.com', '18888888888', '0', '', 'xxxxxx', '0', '0', '10.25.245.45',
        '2022-01-23 15:50:27', 'admin', '2022-01-23 15:50:48', 'ydl', '2022-01-26 15:50:53');


-- ----------------------------
-- 角色的表结构
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `role_id`     BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name`   VARCHAR(30) NOT NULL COMMENT '角色名称',
    `role_tag`    VARCHAR(30) NULL DEFAULT NULL COMMENT '角色标签',
    `role_sort`   INT(4)      NOT NULL COMMENT '显示顺序',
    `status`      CHAR(1)     NOT NULL COMMENT '角色状态（0正常 1停用）',
    `del_flag`    CHAR(1)     NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    `create_by`   VARCHAR(64) NULL DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME    NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   VARCHAR(64) NULL DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME    NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息表';

-- ----------------------------
-- 角色的记录
-- ----------------------------
INSERT INTO `role`
VALUES (1, '管理员', NULL, 1, '0', '0', 'admin', '2022-01-28 15:57:57', 'admin', '2022-01-29 15:58:04');


-- ----------------------------
-- 用户角色中间表结构
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色关联表';

-- ----------------------------
-- 角色用户中间表的记录
-- ----------------------------
INSERT INTO `user_role`
VALUES (1, 1);

-- ----------------------------
-- 菜单表结构
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`
(
    `menu_id`     BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name`   VARCHAR(50)  NOT NULL COMMENT '菜单名称',
    `perms`       VARCHAR(50)  NULL DEFAULT NULL COMMENT '权限标识',
    `parent_id`   BIGINT(20)   NULL DEFAULT 0 COMMENT '父菜单ID',
    `order_num`   INT(4)       NULL DEFAULT 0 COMMENT '显示顺序',
    `path`        VARCHAR(200) NULL DEFAULT '' COMMENT '路由地址',
    `component`   VARCHAR(255) NULL DEFAULT NULL COMMENT '组件路径',
    `menu_type`   CHAR(1)      NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `visible`     CHAR(1)      NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
    `icon`        VARCHAR(100) NULL DEFAULT '#' COMMENT '菜单图标',
    `create_by`   VARCHAR(64)  NULL DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME     NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   VARCHAR(64)  NULL DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME     NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表';

-- ----------------------------
-- 菜单记录
-- ----------------------------
INSERT INTO `menu`
VALUES (1, '系统管理', 'system', 0, 0, 'system', 'null', '1', '0', '#', '', NULL, '', NULL);
INSERT INTO `menu`
VALUES (2, '用户管理', 'system:user', 1, 1, 'system/user/index', NULL, '', '0', '#', '', NULL, '', NULL);
INSERT INTO `menu`
VALUES (3, '角色管理', 'system:role', 1, 2, 'system/role/index', NULL, '', '0', '#', '', NULL, '', NULL);
INSERT INTO `menu`
VALUES (4, '菜单管理', 'system:menu', 1, 3, 'system/menu/index', NULL, '', '0', '#', '', NULL, '', NULL);


-- ----------------------------
-- 角色菜单中间表结构
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`
(
    `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
    `menu_id` BIGINT(20) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和菜单关联表';

-- ----------------------------
-- 角色菜单中间表的记录
-- ----------------------------
INSERT INTO `role_menu`
VALUES (1, 1);
INSERT INTO `role_menu`
VALUES (1, 2);
INSERT INTO `role_menu`
VALUES (1, 3);
INSERT INTO `role_menu`
VALUES (1, 4);

DROP TABLE IF EXISTS `oper_log`;
CREATE TABLE `oper_log`
(
    `oper_id`        INT(11)     NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title`          VARCHAR(255) COMMENT '操作模块',
    `business_type`  VARCHAR(255) COMMENT '业务类型',
    `method`         VARCHAR(255) COMMENT 'api方法',
    `request_method` VARCHAR(255) COMMENT '请求方式',
    `oper_name`      VARCHAR(255) COMMENT '操作人员',
    `oper_url`       VARCHAR(255) COMMENT '请求url',
    `oper_ip`        VARCHAR(255) COMMENT '操作地址',
    `status`         INT(11)     NULL DEFAULT NULL COMMENT '操作状态',
    `errorMsg`       VARCHAR(255) COMMENT '错误消息',
    `operTime`       DATETIME(0) NULL DEFAULT NULL COMMENT '操作时间',
    PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志';
