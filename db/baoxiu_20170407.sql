/*
Navicat MySQL Data Transfer

Source Server         : wenge
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : baoxiu

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-04-07 22:02:12
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
-- Table structure for baoxiu_equipmentset
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu_equipmentset`;
CREATE TABLE `baoxiu_equipmentset` (
  `setId` varchar(50) NOT NULL,
  `equipmentId` varchar(50) NOT NULL,
  PRIMARY KEY (`setId`,`equipmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备集合中间表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日志表';

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
  `listTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维修时间（根据维修状态显示具体时间，不同的维修状态对应显示不同的维修时间）默认值为当前时间',
  `listState` int(11) NOT NULL DEFAULT '9' COMMENT '维修状态：1已提交 2已派单 3延期 4等待派单 5正在备件 6已催单 7已评价 8待评价 9其他',
  `listDescription` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '故障描述',
  `listStatusTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维修单状态时间',
  `listPicture` varchar(255) CHARACTER SET utf8 DEFAULT 'default_list.png' COMMENT '故障描述图片',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`listNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='正在进行的维修单';

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
-- Table structure for shiro_rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_rolepermission`;
CREATE TABLE `shiro_rolepermission` (
  `roleId` varchar(50) NOT NULL,
  `permissionId` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限中见表';

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
-- Table structure for shiro_userrole
-- ----------------------------
DROP TABLE IF EXISTS `shiro_userrole`;
CREATE TABLE `shiro_userrole` (
  `userId` varchar(55) NOT NULL,
  `roleId` varchar(55) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色中见表';
