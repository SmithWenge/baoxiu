/*
Navicat MySQL Data Transfer

Source Server         : wenge
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : baoxiu

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-04-09 12:19:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for baoxiu_adminuser
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_adminuser`;
CREATE TABLE `baoxiu_adminuser` (
  `adminUserId` varchar(50) NOT NULL,
  `adminName` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '工作人员姓名',
  `adminGender` tinyint(2) NOT NULL COMMENT '工作人员性别:1:男 2:女',
  `adminEmail` varchar(20) NOT NULL COMMENT '工作人员邮箱',
  `adminNumber` varchar(20) NOT NULL COMMENT '工作人员工作证号',
  `adminState` tinyint(2) NOT NULL DEFAULT '1' COMMENT '工作人员状态:0:未激活 1：激活态',
  `adminCard` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '工作人员身份证号',
  `adminTelephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '维修人员联系电话',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`adminUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作人员信息表';

-- ----------------------------
-- Records of baoxiu_adminuser
-- ----------------------------
INSERT INTO `baoxiu_adminuser` VALUES ('9ee9ac13-87d9-42c0-af28-73610bbdf357', 'user1', '1', 'user1@example.com', '1110122', '1', '123456789012345678', '12345678901', '0');

-- ----------------------------
-- Table structure for baoxiu_complaint
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_complaint`;
CREATE TABLE `baoxiu_complaint` (
  `complaintId` varchar(50) NOT NULL,
  `complaintContent` varchar(50) NOT NULL COMMENT '投诉内容',
  `complaintExplain` varchar(50) NOT NULL COMMENT '说明信息',
  `userId` varchar(55) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `userTel` varchar(45) NOT NULL COMMENT '用户联系电话',
  `complaintTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '投诉时间 默认值为当前时间',
  `complaintSchedule` tinyint(5) NOT NULL COMMENT '处理进度：1.处理中 2.已完成 3.其他',
  `complaintReply` varchar(50) NOT NULL COMMENT '业务管理员回复内容',
  `deleteFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:未删除 1:删除',
  PRIMARY KEY (`complaintId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='投诉表';

-- ----------------------------
-- Records of baoxiu_complaint
-- ----------------------------

-- ----------------------------
-- Table structure for baoxiu_equipment
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_equipment`;
CREATE TABLE `baoxiu_equipment` (
  `equipmentId` varchar(50) NOT NULL,
  `equipmentName` varchar(20) NOT NULL COMMENT '维修项目名字',
  `repairGroupId` varchar(50) NOT NULL COMMENT '维修小组ID',
  `deleteFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0非删除 1删除',
  `equipmentNumber` varchar(45) NOT NULL COMMENT '设备编号',
  PRIMARY KEY (`equipmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修设施名称';

-- ----------------------------
-- Records of baoxiu_equipment
-- ----------------------------
INSERT INTO `baoxiu_equipment` VALUES ('1111', 'ceshi1', '3213', '0', '123');
INSERT INTO `baoxiu_equipment` VALUES ('2222', 'ceshi2', '2121321', '0', '34234');
INSERT INTO `baoxiu_equipment` VALUES ('3333', 'ceshi3', '123', '0', '123213');
INSERT INTO `baoxiu_equipment` VALUES ('4444', 'ceshi4', '12321', '0', '123312');
INSERT INTO `baoxiu_equipment` VALUES ('5555', 'ceshi5', '1232', '0', '123');
INSERT INTO `baoxiu_equipment` VALUES ('6666', 'ceshi6', '1234', '0', '12321');
INSERT INTO `baoxiu_equipment` VALUES ('7777', 'ceshi7', '324', '0', '31231');
INSERT INTO `baoxiu_equipment` VALUES ('8888', 'ceshi8', '3453', '0', '12321');

-- ----------------------------
-- Table structure for baoxiu_equipmentset
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_equipmentset`;
CREATE TABLE `baoxiu_equipmentset` (
  `setId` varchar(50) NOT NULL,
  `equipmentId` varchar(50) NOT NULL,
  PRIMARY KEY (`setId`,`equipmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备集合中间表';

-- ----------------------------
-- Records of baoxiu_equipmentset
-- ----------------------------
INSERT INTO `baoxiu_equipmentset` VALUES ('', '');
INSERT INTO `baoxiu_equipmentset` VALUES ('1111', '1111');
INSERT INTO `baoxiu_equipmentset` VALUES ('1112', '2222');
INSERT INTO `baoxiu_equipmentset` VALUES ('1113', '');
INSERT INTO `baoxiu_equipmentset` VALUES ('1115', '');
INSERT INTO `baoxiu_equipmentset` VALUES ('1116', '');
INSERT INTO `baoxiu_equipmentset` VALUES ('1117', '');
INSERT INTO `baoxiu_equipmentset` VALUES ('1117', '8888');

-- ----------------------------
-- Table structure for baoxiu_finishlist
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_finishlist`;
CREATE TABLE `baoxiu_finishlist` (
  `listNumber` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '维修单号',
  `userId` varchar(55) NOT NULL COMMENT '用户Id',
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `repairGroupId` varchar(50) NOT NULL COMMENT '维修组Id',
  `repairGroupName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '维修小组名称',
  `equipmentId` varchar(50) NOT NULL,
  `listDescription` varchar(14) CHARACTER SET utf8 NOT NULL COMMENT '故障描述',
  `listFinishTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '保修单完成时间',
  `listPicture` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '故障描述图片',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`listNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修完成的维修单表';

-- ----------------------------
-- Records of baoxiu_finishlist
-- ----------------------------

-- ----------------------------
-- Table structure for baoxiu_judgement
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_judgement`;
CREATE TABLE `baoxiu_judgement` (
  `judgementId` varchar(50) NOT NULL,
  `listScore` tinyint(5) NOT NULL DEFAULT '1' COMMENT '评价分数: 1满意 2不满意 3一般',
  `listWord` varchar(24) CHARACTER SET utf8 NOT NULL COMMENT '用户评价',
  `listNumber` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '维修单号',
  `userId` varchar(50) NOT NULL COMMENT '用户Id',
  `judgementTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评价时间',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`judgementId`),
  KEY `listNumber_idx` (`listNumber`),
  CONSTRAINT `listNumber` FOREIGN KEY (`listNumber`) REFERENCES `baoxiu_maintenancelist` (`listNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评价表';

-- ----------------------------
-- Records of baoxiu_judgement
-- ----------------------------

-- ----------------------------
-- Table structure for baoxiu_liststatetime
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_liststatetime`;
CREATE TABLE `baoxiu_liststatetime` (
  `liststatetimeid` varchar(45) NOT NULL,
  `listNumber` varchar(45) NOT NULL,
  `listState` int(4) NOT NULL,
  `liststatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`liststatetimeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of baoxiu_liststatetime
-- ----------------------------
INSERT INTO `baoxiu_liststatetime` VALUES ('1111', '123123', '1', '2017-04-08 12:13:51', '0');
INSERT INTO `baoxiu_liststatetime` VALUES ('1112', '123123', '2', '2017-04-10 12:14:13', '0');

-- ----------------------------
-- Table structure for baoxiu_log
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_log`;
CREATE TABLE `baoxiu_log` (
  `logId` int(11) NOT NULL AUTO_INCREMENT,
  `logAction` tinyint(2) NOT NULL COMMENT '操作：1：检索 2：删除 3：添加 4：修改 5：其他',
  `logLevel` tinyint(2) NOT NULL COMMENT '级别：1：提示 2：其他',
  `logContent` varchar(255) NOT NULL COMMENT '内容',
  `logUser` varchar(45) NOT NULL COMMENT '操作者',
  `logTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：否1：删除',
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='日志表';

-- ----------------------------
-- Records of baoxiu_log
-- ----------------------------
INSERT INTO `baoxiu_log` VALUES ('1', '3', '1', '添加角色中第三方', 'admin', '2017-04-09 01:10:31', '0');
INSERT INTO `baoxiu_log` VALUES ('2', '4', '1', '更新角色权限cbefd102-7e68-4492-841c-299fd0734580', 'admin', '2017-04-09 02:50:00', '0');

-- ----------------------------
-- Table structure for baoxiu_maintenancelist
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_maintenancelist`;
CREATE TABLE `baoxiu_maintenancelist` (
  `listNumber` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '维修单号',
  `userId` varchar(55) NOT NULL COMMENT '用户Id',
  `userName` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `repairGroupId` varchar(50) NOT NULL COMMENT '维修组Id',
  `roomId` varchar(50) DEFAULT NULL COMMENT '维修房间ID',
  `buildingId` varchar(50) NOT NULL COMMENT '维修区域id',
  `distinctId` varchar(50) NOT NULL COMMENT '校区Id',
  `equipmentId` varchar(50) NOT NULL,
  `listState` int(11) NOT NULL DEFAULT '9' COMMENT '维修状态：1已提交 2已派单 3延期 4等待派单 5正在备件 6已催单 7已评价 8待评价 9其他',
  `listDescription` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '故障描述',
  `listPicture` varchar(255) CHARACTER SET utf8 DEFAULT 'default_list.png' COMMENT '故障描述图片',
  `liststatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '状态时间的冗余字段',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`listNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='正在进行的维修单';

-- ----------------------------
-- Records of baoxiu_maintenancelist
-- ----------------------------
INSERT INTO `baoxiu_maintenancelist` VALUES ('123123', '123123', 'ceshi', '123', '87d5af70-f874-4b6c-9e29-279eee6dffcb', '12321322', 'a91ce1a9-9a01-4e17-b3c6-ef65b756f784', '2222', '9', 'fasdf', '', '2017-04-05 11:12:08', '0');
INSERT INTO `baoxiu_maintenancelist` VALUES ('123124', '123123', 'ceshi', '123', '279eee6dffcb', '12321322', 'ef65b756f784', '2222', '9', 'fasdf', '', '2017-04-13 11:12:14', '0');
INSERT INTO `baoxiu_maintenancelist` VALUES ('123125', '123123', 'ceshi', '123', '279eee6dffcb', '12321322', 'ef65b756f784', '2222', '9', 'fasdf', '', '2017-04-15 11:12:21', '0');

-- ----------------------------
-- Table structure for baoxiu_mistake
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_mistake`;
CREATE TABLE `baoxiu_mistake` (
  `mistakeId` varchar(50) NOT NULL,
  `mistakeDescription` varchar(45) NOT NULL COMMENT '错误描述',
  `listNumber` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '维修单号',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`mistakeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='错误表';

-- ----------------------------
-- Records of baoxiu_mistake
-- ----------------------------

-- ----------------------------
-- Table structure for baoxiu_placebuilding
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_placebuilding`;
CREATE TABLE `baoxiu_placebuilding` (
  `buildingId` varchar(50) NOT NULL,
  `buildingName` varchar(20) NOT NULL COMMENT '维修区域名',
  `distinctId` varchar(50) NOT NULL COMMENT '所属校区Id',
  `setId` varchar(50) DEFAULT NULL,
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`buildingId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修区域或楼';

-- ----------------------------
-- Records of baoxiu_placebuilding
-- ----------------------------
INSERT INTO `baoxiu_placebuilding` VALUES ('12321321', '1building', '9423325b-ac9a-4a2f-b327-cdffb265000e', '1111', '0');
INSERT INTO `baoxiu_placebuilding` VALUES ('12321322', '2building', 'a91ce1a9-9a01-4e17-b3c6-ef65b756f784', '1112', '0');

-- ----------------------------
-- Table structure for baoxiu_placedistinct
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_placedistinct`;
CREATE TABLE `baoxiu_placedistinct` (
  `distinctId` varchar(50) NOT NULL COMMENT '校区Id',
  `distinctName` varchar(20) NOT NULL COMMENT '校区名',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  `distinctNumber` varchar(45) NOT NULL COMMENT '校区编号',
  PRIMARY KEY (`distinctId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修校区';

-- ----------------------------
-- Records of baoxiu_placedistinct
-- ----------------------------
INSERT INTO `baoxiu_placedistinct` VALUES ('9423325b-ac9a-4a2f-b327-cdffb265000e', '旅顺一期', '0', '003');
INSERT INTO `baoxiu_placedistinct` VALUES ('a91ce1a9-9a01-4e17-b3c6-ef65b756f784', '南区', '0', '002');
INSERT INTO `baoxiu_placedistinct` VALUES ('ae4ad531-03b6-4e8c-ab4c-73f10712d68c', '是打发', '0', '889');

-- ----------------------------
-- Table structure for baoxiu_placeroom
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_placeroom`;
CREATE TABLE `baoxiu_placeroom` (
  `roomId` varchar(50) NOT NULL,
  `roomName` varchar(20) NOT NULL COMMENT '维修房间名',
  `buildingId` varchar(50) NOT NULL COMMENT '所属维修区域id',
  `setId` varchar(50) NOT NULL,
  `roomNumber` varchar(50) NOT NULL COMMENT '具体地点房间编号',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`roomId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修具体房间号码';

-- ----------------------------
-- Records of baoxiu_placeroom
-- ----------------------------
INSERT INTO `baoxiu_placeroom` VALUES ('10cef49f-da83-4c88-a2a7-aaecd8a755d5', '测试', '12321321', '1111', '1112', '1');
INSERT INTO `baoxiu_placeroom` VALUES ('87d5af70-f874-4b6c-9e29-279eee6dffcb', '晚上测试', '12321322', '1112', '2222', '0');
INSERT INTO `baoxiu_placeroom` VALUES ('d5933d9a-407d-4527-929c-b8993236acd6', '测试三', '12321321', '1115', '1113', '0');
INSERT INTO `baoxiu_placeroom` VALUES ('e1161586-d2c8-4967-986c-0fedb5b8d746', '测试', '12321321', '1116', '1111', '1');
INSERT INTO `baoxiu_placeroom` VALUES ('fa98ca1b-b2f5-4042-bfaa-8154d8cce83a', '测试三', '12321322', '1117', '3333', '0');

-- ----------------------------
-- Table structure for baoxiu_printer
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_printer`;
CREATE TABLE `baoxiu_printer` (
  `printerId` varchar(50) NOT NULL,
  `printerZHCNName` varchar(45) NOT NULL COMMENT '打印机中文描述',
  `printerNumber` varchar(45) NOT NULL,
  `printIp` varchar(50) NOT NULL,
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`printerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='打印机';

-- ----------------------------
-- Records of baoxiu_printer
-- ----------------------------

-- ----------------------------
-- Table structure for baoxiu_repairgroup
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_repairgroup`;
CREATE TABLE `baoxiu_repairgroup` (
  `repairGroupId` varchar(50) NOT NULL COMMENT '维修小组ID',
  `groupNumber` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '维修小组编号',
  `groupName` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '维修小组名称',
  `groupPrinterIp` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '维修小组对应打印机编号',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`repairGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修小组表';

-- ----------------------------
-- Records of baoxiu_repairgroup
-- ----------------------------
INSERT INTO `baoxiu_repairgroup` VALUES ('123', '123', 'ceshigroup', 'ceshiPriter', '0');
INSERT INTO `baoxiu_repairgroup` VALUES ('fc4febbb-8f87-4512-bd4b-54d7db60a02c', '999', '测试', '1233', '0');

-- ----------------------------
-- Table structure for baoxiu_set
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_set`;
CREATE TABLE `baoxiu_set` (
  `setId` varchar(50) NOT NULL,
  `setName` varchar(45) NOT NULL COMMENT '维修项目名称',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0',
  `setNumber` varchar(45) NOT NULL COMMENT '设备组唯一编号',
  PRIMARY KEY (`setId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备集合表';

-- ----------------------------
-- Records of baoxiu_set
-- ----------------------------
INSERT INTO `baoxiu_set` VALUES ('1111', '测试1', '0', '');
INSERT INTO `baoxiu_set` VALUES ('1112', '测试2', '0', '');

-- ----------------------------
-- Table structure for baoxiu_workerinfo
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_workerinfo`;
CREATE TABLE `baoxiu_workerinfo` (
  `userId` varchar(55) NOT NULL,
  `workerName` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '维修人员姓名',
  `workerUnit` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '维修人员工作单位',
  `workerDepartment` tinyint(25) NOT NULL COMMENT '维修人员工作部门:1:财务部 2:园艺部 3:工程部 4:设备科 5:其他',
  `workerJob` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '维修人员职位',
  `workerState` tinyint(10) NOT NULL DEFAULT '1' COMMENT '维修人员状态（1：在岗 2：离职 3：休假 4：其他）',
  `repairGroupId` varchar(50) NOT NULL COMMENT '维修小组ID',
  `typeId` varchar(50) NOT NULL COMMENT '维修工种ID',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修人员信息表';

-- ----------------------------
-- Records of baoxiu_workerinfo
-- ----------------------------

-- ----------------------------
-- Table structure for baoxiu_workertype
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_workertype`;
CREATE TABLE `baoxiu_workertype` (
  `typeId` varchar(50) NOT NULL,
  `typeName` varchar(20) NOT NULL COMMENT '工种类别名称',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修工种表';

-- ----------------------------
-- Records of baoxiu_workertype
-- ----------------------------

-- ----------------------------
-- Table structure for common_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `common_dictionary`;
CREATE TABLE `common_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupKey` int(11) NOT NULL,
  `groupValue` varchar(20) CHARACTER SET utf8 NOT NULL,
  `itemKey` int(11) NOT NULL,
  `itemValue` varchar(20) CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COMMENT='数据字典';

-- ----------------------------
-- Records of common_dictionary
-- ----------------------------
INSERT INTO `common_dictionary` VALUES ('1', '1', 'deleteFlag', '1', '已删除', '1', '1');
INSERT INTO `common_dictionary` VALUES ('2', '1', 'deleteFlag', '0', '未删除', '1', '2');
INSERT INTO `common_dictionary` VALUES ('3', '2', 'adminGender', '1', '男', '1', '3');
INSERT INTO `common_dictionary` VALUES ('4', '2', 'adminGender', '2', '女', '1', '4');
INSERT INTO `common_dictionary` VALUES ('5', '3', 'adminState', '0', '未激活', '1', '5');
INSERT INTO `common_dictionary` VALUES ('6', '3', 'adminState', '1', '激活态', '1', '6');
INSERT INTO `common_dictionary` VALUES ('7', '4', 'workerDepartment', '1', '财务部', '1', '7');
INSERT INTO `common_dictionary` VALUES ('8', '4', 'workerDepartment', '2', '园艺部', '1', '8');
INSERT INTO `common_dictionary` VALUES ('9', '4', 'workerDepartment', '3', '工程部', '1', '9');
INSERT INTO `common_dictionary` VALUES ('10', '4', 'workerDepartment', '4', '设备科 ', '1', '10');
INSERT INTO `common_dictionary` VALUES ('11', '4', 'workerDepartment', '5', '其他', '1', '11');
INSERT INTO `common_dictionary` VALUES ('12', '5', 'logAction', '1', '检索', '1', '12');
INSERT INTO `common_dictionary` VALUES ('13', '5', 'logAction', '2', '删除', '1', '13');
INSERT INTO `common_dictionary` VALUES ('14', '5', 'logAction', '3', '添加', '1', '14');
INSERT INTO `common_dictionary` VALUES ('15', '5', 'logAction', '4', '修改', '1', '15');
INSERT INTO `common_dictionary` VALUES ('16', '5', 'logAction', '5', '其他', '1', '16');
INSERT INTO `common_dictionary` VALUES ('17', '6', 'listState', '1', '已提交', '1', '17');
INSERT INTO `common_dictionary` VALUES ('18', '6', 'listState', '2', '已派单', '1', '18');
INSERT INTO `common_dictionary` VALUES ('19', '6', 'listState', '3', '延期', '1', '19');
INSERT INTO `common_dictionary` VALUES ('20', '6', 'listState', '4', '等待派单', '1', '20');
INSERT INTO `common_dictionary` VALUES ('21', '6', 'listState', '5', '正在备件', '1', '21');
INSERT INTO `common_dictionary` VALUES ('22', '6', 'listState', '6', '已催单', '1', '22');
INSERT INTO `common_dictionary` VALUES ('23', '6', 'listState', '7', '已评价', '1', '23');
INSERT INTO `common_dictionary` VALUES ('24', '6', 'listState', '8', '待评价', '1', '24');
INSERT INTO `common_dictionary` VALUES ('25', '6', 'listState', '9', '其他', '1', '25');
INSERT INTO `common_dictionary` VALUES ('26', '7', 'complaintSchedule', '1', '处理中', '1', '26');
INSERT INTO `common_dictionary` VALUES ('27', '7', 'complaintSchedule', '2', '已完成', '1', '27');
INSERT INTO `common_dictionary` VALUES ('28', '7', 'complaintSchedule', '3', '其他', '1', '28');

-- ----------------------------
-- Table structure for shiro_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_permission`;
CREATE TABLE `shiro_permission` (
  `permissionId` varchar(50) NOT NULL,
  `permissionName` varchar(45) NOT NULL COMMENT '权限名称',
  `permissionZHCNName` varchar(45) NOT NULL COMMENT '该权限中文描述',
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of shiro_permission
-- ----------------------------
INSERT INTO `shiro_permission` VALUES ('1', 'distinct:list', '校区查看');
INSERT INTO `shiro_permission` VALUES ('10', 'room:add', '位置添加');
INSERT INTO `shiro_permission` VALUES ('11', 'room:edit', '位置编辑');
INSERT INTO `shiro_permission` VALUES ('12', 'room:delete', '位置删除');
INSERT INTO `shiro_permission` VALUES ('13', 'set:list', '设备组查看');
INSERT INTO `shiro_permission` VALUES ('14', 'set:add', '设备组添加');
INSERT INTO `shiro_permission` VALUES ('15', 'set:edit', '设备组编辑');
INSERT INTO `shiro_permission` VALUES ('16', 'set:delete', '设备组删除');
INSERT INTO `shiro_permission` VALUES ('17', 'equipment:list', '设备查看');
INSERT INTO `shiro_permission` VALUES ('18', 'equipment:add', '设备添加');
INSERT INTO `shiro_permission` VALUES ('19', 'equipment:edit', '设备编辑');
INSERT INTO `shiro_permission` VALUES ('2', 'distinct:add', '校区添加');
INSERT INTO `shiro_permission` VALUES ('20', 'equipment:delete', '设备删除');
INSERT INTO `shiro_permission` VALUES ('21', 'printer:list', '打印机查看');
INSERT INTO `shiro_permission` VALUES ('22', 'printer:add', '打印机添加');
INSERT INTO `shiro_permission` VALUES ('23', 'printer:edit', '打印机编辑');
INSERT INTO `shiro_permission` VALUES ('24', 'printer:delete', '打印机删除');
INSERT INTO `shiro_permission` VALUES ('25', 'workerInfo:list', '工人信息查看');
INSERT INTO `shiro_permission` VALUES ('26', 'workerInfo:add', '工人信息添加');
INSERT INTO `shiro_permission` VALUES ('27', 'workerInfo:edit', '工人信息编辑');
INSERT INTO `shiro_permission` VALUES ('28', 'workerInfo:delete', '工人信息删除');
INSERT INTO `shiro_permission` VALUES ('29', 'workerType:list', '工种查看');
INSERT INTO `shiro_permission` VALUES ('3', 'distinct:edit', '校区编辑');
INSERT INTO `shiro_permission` VALUES ('30', 'workerType:add', '工种添加');
INSERT INTO `shiro_permission` VALUES ('31', 'workerType:edit', '工种编辑');
INSERT INTO `shiro_permission` VALUES ('32', 'workerType:delete', '工种删除');
INSERT INTO `shiro_permission` VALUES ('33', 'repairGroup:list', '维修小组查看');
INSERT INTO `shiro_permission` VALUES ('34', 'repairGroup:add', '维修小组添加');
INSERT INTO `shiro_permission` VALUES ('35', 'repairGroup:edit', '维修小组编辑');
INSERT INTO `shiro_permission` VALUES ('36', 'repairGroup:delete', '维修小组删除');
INSERT INTO `shiro_permission` VALUES ('37', 'maintenanceList:list', '报修单查看');
INSERT INTO `shiro_permission` VALUES ('38', 'maintenanceList:add', '报修单添加');
INSERT INTO `shiro_permission` VALUES ('39', 'maintenanceList:edit', '报修单编辑');
INSERT INTO `shiro_permission` VALUES ('4', 'distinct:delete', '校区删除');
INSERT INTO `shiro_permission` VALUES ('40', 'maintenanceList:delete', '报修单删除');
INSERT INTO `shiro_permission` VALUES ('5', 'building:list', '地点查看');
INSERT INTO `shiro_permission` VALUES ('6', 'building:add', '地点添加');
INSERT INTO `shiro_permission` VALUES ('7', 'building:edit', '地点编辑');
INSERT INTO `shiro_permission` VALUES ('8', 'building:delete', '地点删除');
INSERT INTO `shiro_permission` VALUES ('9', 'room:list', '位置查看');

-- ----------------------------
-- Table structure for shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role` (
  `roleId` varchar(55) NOT NULL,
  `roleName` varchar(45) NOT NULL COMMENT '角色名称',
  `roleZHCNName` varchar(45) NOT NULL COMMENT '角色的中文描述',
  `deleteFlag` varchar(45) NOT NULL DEFAULT '0' COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES ('1111', '超级管理员', '管理所有', '0');
INSERT INTO `shiro_role` VALUES ('cbefd102-7e68-4492-841c-299fd0734580', '中第三方', 'sfda', '0');

-- ----------------------------
-- Table structure for shiro_rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_rolepermission`;
CREATE TABLE `shiro_rolepermission` (
  `roleId` varchar(50) NOT NULL,
  `permissionId` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限中见表';

-- ----------------------------
-- Records of shiro_rolepermission
-- ----------------------------
INSERT INTO `shiro_rolepermission` VALUES ('1111', '1');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '2');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '37');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '4');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '5');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '6');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '7');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '8');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '9');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '10');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '11');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '12');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '13');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '14');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '15');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '16');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '17');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '18');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '19');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '20');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '21');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '22');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '23');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '24');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '25');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '26');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '27');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '28');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '29');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '30');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '31');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '32');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '33');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '34');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '35');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '36');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '3');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '38');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '39');
INSERT INTO `shiro_rolepermission` VALUES ('1111', '40');
INSERT INTO `shiro_rolepermission` VALUES ('cbefd102-7e68-4492-841c-299fd0734580', '10');
INSERT INTO `shiro_rolepermission` VALUES ('cbefd102-7e68-4492-841c-299fd0734580', '11');
INSERT INTO `shiro_rolepermission` VALUES ('cbefd102-7e68-4492-841c-299fd0734580', '12');
INSERT INTO `shiro_rolepermission` VALUES ('cbefd102-7e68-4492-841c-299fd0734580', '9');
INSERT INTO `shiro_rolepermission` VALUES ('cbefd102-7e68-4492-841c-299fd0734580', '1');
INSERT INTO `shiro_rolepermission` VALUES ('cbefd102-7e68-4492-841c-299fd0734580', '2');
INSERT INTO `shiro_rolepermission` VALUES ('cbefd102-7e68-4492-841c-299fd0734580', '3');
INSERT INTO `shiro_rolepermission` VALUES ('cbefd102-7e68-4492-841c-299fd0734580', '4');

-- ----------------------------
-- Table structure for shiro_user
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user` (
  `userId` varchar(55) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `adminUserId` varchar(45) NOT NULL DEFAULT '' COMMENT '与管理员用户1:1',
  `deleteFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:未删除 1:删除',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of shiro_user
-- ----------------------------
INSERT INTO `shiro_user` VALUES ('24355a91-2fcb-4a42-b906-c0763d267f24', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '9ee9ac13-87d9-42c0-af28-73610bbdf357', '0');

-- ----------------------------
-- Table structure for shiro_userrole
-- ----------------------------
DROP TABLE IF EXISTS `shiro_userrole`;
CREATE TABLE `shiro_userrole` (
  `userId` varchar(55) NOT NULL,
  `roleId` varchar(55) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色中见表';

-- ----------------------------
-- Records of shiro_userrole
-- ----------------------------
INSERT INTO `shiro_userrole` VALUES ('24355a91-2fcb-4a42-b906-c0763d267f24', '1111');
