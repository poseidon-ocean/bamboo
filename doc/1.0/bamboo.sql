/*
Navicat MySQL Data Transfer

Source Server         : hdb
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : bamboo

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-10-26 09:06:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for r_dept
-- ----------------------------
DROP TABLE IF EXISTS `r_dept`;
CREATE TABLE `r_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `num` int(11) DEFAULT NULL COMMENT '排序号',
  `pid` int(11) DEFAULT NULL COMMENT '父级ID',
  `simple_name` varchar(45) DEFAULT NULL COMMENT '简称',
  `full_name` varchar(255) DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of r_dept
-- ----------------------------
INSERT INTO `r_dept` VALUES ('24', '1', '0', '总公司', '总公司', '', null);
INSERT INTO `r_dept` VALUES ('25', '2', '24', '开发部', '开发部', '', null);
INSERT INTO `r_dept` VALUES ('26', '3', '24', '运营部', '运营部', '', null);
INSERT INTO `r_dept` VALUES ('27', '4', '24', '战略部', '战略部', '', null);
INSERT INTO `r_dept` VALUES ('34', '4', '24', '品质部', '品质部', '', null);

-- ----------------------------
-- Table structure for r_dict
-- ----------------------------
DROP TABLE IF EXISTS `r_dict`;
CREATE TABLE `r_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `num` int(11) DEFAULT NULL COMMENT '排序号',
  `pid` int(11) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(255) DEFAULT NULL COMMENT '字典名称',
  `tips` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of r_dict
-- ----------------------------
INSERT INTO `r_dict` VALUES ('16', '0', '0', '状态', '');
INSERT INTO `r_dict` VALUES ('17', '1', '16', '启用', '');
INSERT INTO `r_dict` VALUES ('18', '2', '16', '禁用', '');
INSERT INTO `r_dict` VALUES ('29', '0', '0', '性别', '');
INSERT INTO `r_dict` VALUES ('30', '1', '29', '男', '');
INSERT INTO `r_dict` VALUES ('31', '2', '29', '女', '');
INSERT INTO `r_dict` VALUES ('35', '0', '0', '账号状态', '');
INSERT INTO `r_dict` VALUES ('36', '1', '35', '启用', '');
INSERT INTO `r_dict` VALUES ('37', '2', '35', '冻结', '');
INSERT INTO `r_dict` VALUES ('38', '3', '35', '已删除', '');
INSERT INTO `r_dict` VALUES ('62', '0', '0', '银行', null);
INSERT INTO `r_dict` VALUES ('63', '1', '62', '中国银行', null);
INSERT INTO `r_dict` VALUES ('64', '2', '62', '建设银行', null);
INSERT INTO `r_dict` VALUES ('65', '3', '62', '工商银行', null);
INSERT INTO `r_dict` VALUES ('66', '4', '62', '农业银行', null);
INSERT INTO `r_dict` VALUES ('67', '5', '62', '邮政银行', null);
INSERT INTO `r_dict` VALUES ('68', '6', '62', '浦东银行', null);
INSERT INTO `r_dict` VALUES ('69', '7', '62', '南京银行', null);
INSERT INTO `r_dict` VALUES ('70', '8', '62', '江苏银行', null);
INSERT INTO `r_dict` VALUES ('71', '9', '62', '微商银行', null);

-- ----------------------------
-- Table structure for r_file
-- ----------------------------
DROP TABLE IF EXISTS `r_file`;
CREATE TABLE `r_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(3) DEFAULT NULL COMMENT '文件类型 1-图片 2-视频 3-语音',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_by` varchar(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of r_file
-- ----------------------------

-- ----------------------------
-- Table structure for r_login_log
-- ----------------------------
DROP TABLE IF EXISTS `r_login_log`;
CREATE TABLE `r_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logname` varchar(255) DEFAULT NULL COMMENT '日志名',
  `userid` int(65) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL,
  `message` text COMMENT '日志信息',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8 COMMENT='登录日志';

-- ----------------------------
-- Records of r_login_log
-- ----------------------------
INSERT INTO `r_login_log` VALUES ('139', '登录日志', '1', '2017-06-27 10:34:39', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('140', '退出日志', '1', '2017-06-27 10:43:13', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('141', '登录日志', '1', '2017-06-27 10:43:19', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('142', '退出日志', '1', '2017-06-28 09:58:56', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('143', '登录日志', '1', '2017-06-28 09:59:07', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('144', '登录日志', '1', '2017-06-28 11:10:33', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('145', '登录日志', '1', '2017-06-29 13:35:22', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('146', '登录日志', '1', '2017-06-29 14:26:22', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('147', '登录日志', '1', '2017-07-04 15:53:12', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('148', '退出日志', '1', '2017-07-04 17:02:00', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('149', '登录日志', '1', '2017-07-04 17:02:28', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('150', '退出日志', '1', '2017-07-04 18:40:08', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('151', '登录日志', '1', '2017-07-04 18:40:29', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('152', '退出日志', '1', '2017-07-05 14:43:13', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('153', '登录日志', '46', '2017-07-05 14:43:21', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('154', '登录日志', '46', '2017-07-10 11:03:37', '成功', null, '0:0:0:0:0:0:0:1');
INSERT INTO `r_login_log` VALUES ('155', '登录日志', '1', '2017-08-10 13:45:41', '成功', null, '172.19.10.230');

-- ----------------------------
-- Table structure for r_menu
-- ----------------------------
DROP TABLE IF EXISTS `r_menu`;
CREATE TABLE `r_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) DEFAULT NULL COMMENT '菜单所有父编号',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `num` int(65) DEFAULT NULL COMMENT '排序号',
  `levels` int(65) DEFAULT NULL COMMENT '菜单层级',
  `ismenu` int(11) DEFAULT NULL,
  `tips` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(65) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `isopen` int(11) DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of r_menu
-- ----------------------------
INSERT INTO `r_menu` VALUES ('105', 'system', '0', '[0],', '系统管理', 'fa-user', '', '3', '1', '1', '', '1', '1');
INSERT INTO `r_menu` VALUES ('106', 'mgr', 'system', '[0],[system],', '用户管理', '', '/sys/mgr', '1', '2', '1', '', '1', '0');
INSERT INTO `r_menu` VALUES ('107', 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', '', '/sys/mgr/add', '1', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('108', 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', '', '/sys/mgr/edit', '2', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('109', 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', '', '/sys/mgr/delete', '3', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('110', 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', '', '/sys/mgr/reset', '4', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('111', 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', '', '/sys/mgr/freeze', '5', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('112', 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', '', '/sys/mgr/unfreeze', '6', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('113', 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', '', '/sys/mgr/setRole', '7', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('114', 'role', 'system', '[0],[system],', '角色管理', '', '/sys/role', '2', '2', '1', '', '1', '0');
INSERT INTO `r_menu` VALUES ('115', 'role_add', 'role', '[0],[system],[role],', '添加角色', '', '/sys/role/add', '1', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('116', 'role_edit', 'role', '[0],[system],[role],', '修改角色', '', '/sys/role/edit', '2', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('117', 'role_remove', 'role', '[0],[system],[role],', '删除角色', '', '/sys/role/remove', '3', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('118', 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', '', '/sys/role/setAuthority', '4', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('119', 'menu', 'system', '[0],[system],', '菜单管理', '', '/sys/menu', '4', '2', '1', '', '1', '0');
INSERT INTO `r_menu` VALUES ('120', 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', '', '/sys/menu/add', '1', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('121', 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', '', '/sys/menu/edit', '2', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('122', 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', '', '/sys/menu/remove', '3', '3', '0', '', '1', '0');
INSERT INTO `r_menu` VALUES ('128', 'log', 'system', '[0],[system],', '业务日志', '', '/sys/log', '6', '2', '1', '', '1', '0');
INSERT INTO `r_menu` VALUES ('130', 'druid', 'system', '[0],[system],', '监控管理', '', '/druid', '7', '2', '1', '', '1', null);
INSERT INTO `r_menu` VALUES ('131', 'dept', 'system', '[0],[system],', '部门管理', '', '/sys/dept', '3', '2', '1', '', '1', null);
INSERT INTO `r_menu` VALUES ('132', 'dict', 'system', '[0],[system],', '字典管理', '', '/sys/dict', '4', '2', '1', '', '1', null);
INSERT INTO `r_menu` VALUES ('133', 'loginLog', 'system', '[0],[system],', '登录日志', '', '/sys/loginLog', '6', '2', '1', '', '1', null);
INSERT INTO `r_menu` VALUES ('134', 'log_clean', 'log', '[0],[system],[log],', '清空日志', '', '/sys/log/delLog', '3', '3', '0', '', '1', null);
INSERT INTO `r_menu` VALUES ('135', 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', '', '/sys/dept/add', '1', '3', '0', '', '1', null);
INSERT INTO `r_menu` VALUES ('136', 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', '', '/sys/dept/update', '1', '3', '0', '', '1', null);
INSERT INTO `r_menu` VALUES ('137', 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', '', '/sys/dept/delete', '1', '3', '0', '', '1', null);
INSERT INTO `r_menu` VALUES ('138', 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', '', '/sys/dict/add', '1', '3', '0', '', '1', null);
INSERT INTO `r_menu` VALUES ('139', 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', '', '/sys/dict/update', '1', '3', '0', '', '1', null);
INSERT INTO `r_menu` VALUES ('140', 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', '', '/sys/dict/delete', '1', '3', '0', '', '1', null);
INSERT INTO `r_menu` VALUES ('141', 'notice', 'system', '[0],[system],', '通知管理', '', '/sys/notice', '9', '2', '1', '', '1', null);
INSERT INTO `r_menu` VALUES ('142', 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', '', '/sys/notice/add', '1', '3', '0', '', '1', null);
INSERT INTO `r_menu` VALUES ('143', 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', '', '/sys/notice/update', '2', '3', '0', '', '1', null);
INSERT INTO `r_menu` VALUES ('144', 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', '', '/sys/notice/delete', '3', '3', '0', '', '1', null);
INSERT INTO `r_menu` VALUES ('145', 'hello', '0', '[0],', '通知', 'fa-rocket', '/sys/notice/hello', '1', '1', '1', '', '1', null);
INSERT INTO `r_menu` VALUES ('148', 'code', 'system', '[0],[system],', '代码生成', 'fa-user', '/sys/code', '10', '2', '1', '', '1', null);
INSERT INTO `r_menu` VALUES ('149', 'api_mgr', '0', '[0],', '接口文档', 'fa-leaf', '/swagger-ui.html', '2', '1', '1', '', '1', null);

-- ----------------------------
-- Table structure for r_notice
-- ----------------------------
DROP TABLE IF EXISTS `r_notice`;
CREATE TABLE `r_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='提示信息表';

-- ----------------------------
-- Records of r_notice
-- ----------------------------
INSERT INTO `r_notice` VALUES ('6', '世界', '10', '欢迎使用Raytoo CMS管理系统', '2017-01-11 08:53:20', '1', null);
INSERT INTO `r_notice` VALUES ('11', '时间', null, '人生天地之间；若白驹之过隙；忽然而已。 &nbsp; - 《知北游》', '2017-07-04 17:56:44', '张三', '1');

-- ----------------------------
-- Table structure for r_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `r_operation_log`;
CREATE TABLE `r_operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logtype` varchar(255) DEFAULT NULL COMMENT '日志类型',
  `logname` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL COMMENT '类名',
  `method` text COMMENT '方法',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL,
  `message` text COMMENT '信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of r_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for r_role
-- ----------------------------
DROP TABLE IF EXISTS `r_role`;
CREATE TABLE `r_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `num` int(11) DEFAULT NULL COMMENT '排序号',
  `pid` int(11) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `deptid` int(11) DEFAULT NULL COMMENT '部门id',
  `tips` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of r_role
-- ----------------------------
INSERT INTO `r_role` VALUES ('1', '1', '0', '超级管理员', '24', 'administrator', '1');
INSERT INTO `r_role` VALUES ('5', '2', '1', '临时', '26', 'temp', null);
INSERT INTO `r_role` VALUES ('6', '1', '1', '维护人员', '25', '维护人员', null);
INSERT INTO `r_role` VALUES ('7', '4', '1', '操作员', '24', '操作员', null);

-- ----------------------------
-- Table structure for r_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `r_role_menu`;
CREATE TABLE `r_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3724 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of r_role_menu
-- ----------------------------
INSERT INTO `r_role_menu` VALUES ('3390', '118', '5');
INSERT INTO `r_role_menu` VALUES ('3391', '119', '5');
INSERT INTO `r_role_menu` VALUES ('3392', '120', '5');
INSERT INTO `r_role_menu` VALUES ('3393', '121', '5');
INSERT INTO `r_role_menu` VALUES ('3394', '122', '5');
INSERT INTO `r_role_menu` VALUES ('3395', '150', '5');
INSERT INTO `r_role_menu` VALUES ('3396', '151', '5');
INSERT INTO `r_role_menu` VALUES ('3624', '105', '1');
INSERT INTO `r_role_menu` VALUES ('3625', '106', '1');
INSERT INTO `r_role_menu` VALUES ('3626', '107', '1');
INSERT INTO `r_role_menu` VALUES ('3627', '108', '1');
INSERT INTO `r_role_menu` VALUES ('3628', '109', '1');
INSERT INTO `r_role_menu` VALUES ('3629', '110', '1');
INSERT INTO `r_role_menu` VALUES ('3630', '111', '1');
INSERT INTO `r_role_menu` VALUES ('3631', '112', '1');
INSERT INTO `r_role_menu` VALUES ('3632', '113', '1');
INSERT INTO `r_role_menu` VALUES ('3633', '165', '1');
INSERT INTO `r_role_menu` VALUES ('3634', '166', '1');
INSERT INTO `r_role_menu` VALUES ('3635', '167', '1');
INSERT INTO `r_role_menu` VALUES ('3636', '114', '1');
INSERT INTO `r_role_menu` VALUES ('3637', '115', '1');
INSERT INTO `r_role_menu` VALUES ('3638', '116', '1');
INSERT INTO `r_role_menu` VALUES ('3639', '117', '1');
INSERT INTO `r_role_menu` VALUES ('3640', '118', '1');
INSERT INTO `r_role_menu` VALUES ('3641', '162', '1');
INSERT INTO `r_role_menu` VALUES ('3642', '163', '1');
INSERT INTO `r_role_menu` VALUES ('3643', '164', '1');
INSERT INTO `r_role_menu` VALUES ('3644', '119', '1');
INSERT INTO `r_role_menu` VALUES ('3645', '120', '1');
INSERT INTO `r_role_menu` VALUES ('3646', '121', '1');
INSERT INTO `r_role_menu` VALUES ('3647', '122', '1');
INSERT INTO `r_role_menu` VALUES ('3648', '150', '1');
INSERT INTO `r_role_menu` VALUES ('3649', '151', '1');
INSERT INTO `r_role_menu` VALUES ('3650', '128', '1');
INSERT INTO `r_role_menu` VALUES ('3651', '134', '1');
INSERT INTO `r_role_menu` VALUES ('3652', '158', '1');
INSERT INTO `r_role_menu` VALUES ('3653', '159', '1');
INSERT INTO `r_role_menu` VALUES ('3654', '130', '1');
INSERT INTO `r_role_menu` VALUES ('3655', '131', '1');
INSERT INTO `r_role_menu` VALUES ('3656', '135', '1');
INSERT INTO `r_role_menu` VALUES ('3657', '136', '1');
INSERT INTO `r_role_menu` VALUES ('3658', '137', '1');
INSERT INTO `r_role_menu` VALUES ('3659', '152', '1');
INSERT INTO `r_role_menu` VALUES ('3660', '153', '1');
INSERT INTO `r_role_menu` VALUES ('3661', '154', '1');
INSERT INTO `r_role_menu` VALUES ('3662', '132', '1');
INSERT INTO `r_role_menu` VALUES ('3663', '138', '1');
INSERT INTO `r_role_menu` VALUES ('3664', '139', '1');
INSERT INTO `r_role_menu` VALUES ('3665', '140', '1');
INSERT INTO `r_role_menu` VALUES ('3666', '155', '1');
INSERT INTO `r_role_menu` VALUES ('3667', '156', '1');
INSERT INTO `r_role_menu` VALUES ('3668', '157', '1');
INSERT INTO `r_role_menu` VALUES ('3669', '133', '1');
INSERT INTO `r_role_menu` VALUES ('3670', '160', '1');
INSERT INTO `r_role_menu` VALUES ('3671', '161', '1');
INSERT INTO `r_role_menu` VALUES ('3672', '141', '1');
INSERT INTO `r_role_menu` VALUES ('3673', '142', '1');
INSERT INTO `r_role_menu` VALUES ('3674', '143', '1');
INSERT INTO `r_role_menu` VALUES ('3675', '144', '1');
INSERT INTO `r_role_menu` VALUES ('3676', '148', '1');
INSERT INTO `r_role_menu` VALUES ('3677', '145', '1');
INSERT INTO `r_role_menu` VALUES ('3678', '149', '1');
INSERT INTO `r_role_menu` VALUES ('3689', '105', '6');
INSERT INTO `r_role_menu` VALUES ('3690', '106', '6');
INSERT INTO `r_role_menu` VALUES ('3691', '107', '6');
INSERT INTO `r_role_menu` VALUES ('3692', '108', '6');
INSERT INTO `r_role_menu` VALUES ('3693', '109', '6');
INSERT INTO `r_role_menu` VALUES ('3694', '110', '6');
INSERT INTO `r_role_menu` VALUES ('3695', '111', '6');
INSERT INTO `r_role_menu` VALUES ('3696', '112', '6');
INSERT INTO `r_role_menu` VALUES ('3697', '113', '6');
INSERT INTO `r_role_menu` VALUES ('3698', '165', '6');
INSERT INTO `r_role_menu` VALUES ('3699', '166', '6');
INSERT INTO `r_role_menu` VALUES ('3700', '167', '6');
INSERT INTO `r_role_menu` VALUES ('3701', '114', '6');
INSERT INTO `r_role_menu` VALUES ('3702', '115', '6');
INSERT INTO `r_role_menu` VALUES ('3703', '116', '6');
INSERT INTO `r_role_menu` VALUES ('3704', '117', '6');
INSERT INTO `r_role_menu` VALUES ('3705', '118', '6');
INSERT INTO `r_role_menu` VALUES ('3706', '162', '6');
INSERT INTO `r_role_menu` VALUES ('3707', '163', '6');
INSERT INTO `r_role_menu` VALUES ('3708', '164', '6');
INSERT INTO `r_role_menu` VALUES ('3709', '119', '6');
INSERT INTO `r_role_menu` VALUES ('3710', '120', '6');
INSERT INTO `r_role_menu` VALUES ('3711', '121', '6');
INSERT INTO `r_role_menu` VALUES ('3712', '122', '6');
INSERT INTO `r_role_menu` VALUES ('3713', '150', '6');
INSERT INTO `r_role_menu` VALUES ('3714', '151', '6');
INSERT INTO `r_role_menu` VALUES ('3715', '128', '6');
INSERT INTO `r_role_menu` VALUES ('3716', '134', '6');
INSERT INTO `r_role_menu` VALUES ('3717', '158', '6');
INSERT INTO `r_role_menu` VALUES ('3718', '159', '6');
INSERT INTO `r_role_menu` VALUES ('3719', '130', '6');
INSERT INTO `r_role_menu` VALUES ('3720', '133', '6');
INSERT INTO `r_role_menu` VALUES ('3721', '160', '6');
INSERT INTO `r_role_menu` VALUES ('3722', '161', '6');
INSERT INTO `r_role_menu` VALUES ('3723', '148', '6');

-- ----------------------------
-- Table structure for r_user
-- ----------------------------
DROP TABLE IF EXISTS `r_user`;
CREATE TABLE `r_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `avatar` varchar(255) DEFAULT NULL,
  `account` varchar(45) DEFAULT NULL COMMENT '帐号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT '加密盐',
  `name` varchar(45) DEFAULT NULL COMMENT '用户名',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(45) DEFAULT NULL COMMENT '手机号',
  `roleid` varchar(255) DEFAULT NULL COMMENT '角色id 多个以逗号隔开',
  `deptid` int(11) DEFAULT NULL COMMENT '部门id',
  `status` int(11) DEFAULT NULL COMMENT '是否可用  1 - 可用  0 -不可用',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` datetime DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of r_user
-- ----------------------------
INSERT INTO `r_user` VALUES ('1', 'girl.gif', 'admin', 'ecfadcde9305f8891bcfe5a1e28c253e', '8pgby', '张三', '2017-05-05 00:00:00', '2', 'sn93@qq.com', '18200000000', '1', '27', '1', '', '2017-06-27 10:31:49', null, null, null);
INSERT INTO `r_user` VALUES ('46', null, 'hexiangdong', '93fbbe193ff0004f6d9049cb94b939ab', 'vkdw6', '何向东', '1976-06-29 00:00:00', '1', 'hexiangdong@163.com', '', '1', '25', '1', null, null, null, null, null);
INSERT INTO `r_user` VALUES ('47', null, 'shihao', '392de31b954d8fe543f9ef9174e970bb', '85ef8', '石浩', '2012-07-04 00:00:00', '1', 'shihao@qq.com', '15112345678', '6', '27', '1', null, null, null, null, null);
INSERT INTO `r_user` VALUES ('48', null, 'xueguo', 'ade866c2f588779299f6f2d8f80909a8', 'c1m9q', '薛果', '1984-07-04 00:00:00', '1', 'xueguo@qq.com', '17745451245', null, '25', '1', '张三', '2017-07-04 16:06:58', null, null, null);
