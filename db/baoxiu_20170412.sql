CREATE DATABASE  IF NOT EXISTS `baoxiu` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `baoxiu`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: baoxiu
-- ------------------------------------------------------
-- Server version	5.6.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `baoxiu_adminuser`
--

DROP TABLE IF EXISTS `baoxiu_adminuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_adminuser`
--

LOCK TABLES `baoxiu_adminuser` WRITE;
/*!40000 ALTER TABLE `baoxiu_adminuser` DISABLE KEYS */;
INSERT INTO `baoxiu_adminuser` VALUES ('9ee9ac13-87d9-42c0-af28-73610bbdf357','user1',1,'user1@example.com','1110122',1,'123456789012345678','12345678901',0),('fd98d921-b2ce-42cf-8531-6f1a3341c53c','张文岳',1,'15841139493@139.com','001',1,'150426199602051753','15841139493',0),('user2','2',1,'df','45',1,'3','5346587',0);
/*!40000 ALTER TABLE `baoxiu_adminuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_complaint`
--

DROP TABLE IF EXISTS `baoxiu_complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_complaint`
--

LOCK TABLES `baoxiu_complaint` WRITE;
/*!40000 ALTER TABLE `baoxiu_complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `baoxiu_complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_equipment`
--

DROP TABLE IF EXISTS `baoxiu_equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baoxiu_equipment` (
  `equipmentId` varchar(50) NOT NULL,
  `equipmentName` varchar(20) NOT NULL COMMENT '维修项目名字',
  `repairGroupId` varchar(50) NOT NULL COMMENT '维修小组ID',
  `deleteFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0非删除 1删除',
  `equipmentNumber` varchar(45) NOT NULL COMMENT '设备编号',
  `roomId` varchar(50) NOT NULL,
  PRIMARY KEY (`equipmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修设施名称';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_equipment`
--

LOCK TABLES `baoxiu_equipment` WRITE;
/*!40000 ALTER TABLE `baoxiu_equipment` DISABLE KEYS */;
INSERT INTO `baoxiu_equipment` VALUES ('110fa605-af28-4b4a-809a-619008810a56','测试设备','123',0,'002','777b8489-c9f7-4462-8b78-5d24208b6c8d'),('fa0cf7a5-71a8-45a4-9399-433093431137','灯灯','123',0,'001','d5933d9a-407d-4527-929c-b8993236acd6');
/*!40000 ALTER TABLE `baoxiu_equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_finishlist`
--

DROP TABLE IF EXISTS `baoxiu_finishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_finishlist`
--

LOCK TABLES `baoxiu_finishlist` WRITE;
/*!40000 ALTER TABLE `baoxiu_finishlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `baoxiu_finishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_judgement`
--

DROP TABLE IF EXISTS `baoxiu_judgement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_judgement`
--

LOCK TABLES `baoxiu_judgement` WRITE;
/*!40000 ALTER TABLE `baoxiu_judgement` DISABLE KEYS */;
/*!40000 ALTER TABLE `baoxiu_judgement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_liststatetime`
--

DROP TABLE IF EXISTS `baoxiu_liststatetime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baoxiu_liststatetime` (
  `liststatetimeid` varchar(50) NOT NULL,
  `listNumber` varchar(45) NOT NULL,
  `listState` int(4) NOT NULL,
  `liststatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`liststatetimeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_liststatetime`
--

LOCK TABLES `baoxiu_liststatetime` WRITE;
/*!40000 ALTER TABLE `baoxiu_liststatetime` DISABLE KEYS */;
INSERT INTO `baoxiu_liststatetime` VALUES ('0233a75e-a82f-4c0b-8cef-5d0bb34eba84','000002005746003333031231',1,'2017-04-09 04:54:29',0),('15097f1e-4c65-408e-9d5a-abe5c16efce9','000002005746003333034234',1,'2017-04-08 12:18:16',0),('330e74ef-829e-4594-ac2c-054e4ee63c69','000002005746003333123312',1,'2017-04-10 11:32:16',0),('4ff98535-b8aa-4e07-9c9c-7a980e2a9928','000002005746002222123312',1,'2017-04-09 09:05:35',0),('79179a09-3c25-4045-8d6a-7767d2f68c4c','000002005746003333012321',1,'2017-04-08 12:31:49',0),('a2cdd3da-5dd9-4c80-8526-bc1dd853bfe3','000003008867001113000123',1,'2017-04-08 12:11:43',0),('b2bb64e2-e5ee-4160-bfc2-e42ebd37a7a5','000003008867001113034234',1,'2017-04-10 11:36:42',0),('ec359742-edc4-446a-aeae-7969b6ecaab2','000003008867001113123312',1,'2017-04-08 12:21:24',0),('ecbd2736-e1ef-4045-8207-69dca9eea1cc','000003008867001113123213',1,'2017-04-08 12:19:47',0),('f51f792b-f61f-4123-ae5a-67fb752caece','000002005746001113012321',1,'2017-04-09 09:02:04',0),('fdff16e4-825f-444d-b66d-64b0cddc4647','000002005746003333000123',1,'2017-04-08 12:32:04',0);
/*!40000 ALTER TABLE `baoxiu_liststatetime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_log`
--

DROP TABLE IF EXISTS `baoxiu_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_log`
--

LOCK TABLES `baoxiu_log` WRITE;
/*!40000 ALTER TABLE `baoxiu_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `baoxiu_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_maintenancelist`
--

DROP TABLE IF EXISTS `baoxiu_maintenancelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_maintenancelist`
--

LOCK TABLES `baoxiu_maintenancelist` WRITE;
/*!40000 ALTER TABLE `baoxiu_maintenancelist` DISABLE KEYS */;
INSERT INTO `baoxiu_maintenancelist` VALUES ('000003008867001113034234','100','wenge','2121321','d5933d9a-407d-4527-929c-b8993236acd6','12321321','9423325b-ac9a-4a2f-b327-cdffb265000e','2222','2017-04-10 11:36:42',1,'文哥是煞笔','2017-04-10 11:36:42','default_list.png',0),('12','100','wenge','1234','d5933d9a-407d-4527-929c-b8993236acd6','12321322','a91ce1a9-9a01-4e17-b3c6-ef65b756f784','6666','2017-04-10 11:33:49',1,'234535','2017-04-10 11:33:49','default_list.png',0),('2','100','wenge','12321','87d5af70-f874-4b6c-9e29-279eee6dffcb','12321322','a91ce1a9-9a01-4e17-b3c6-ef65b756f784','4444','2017-04-10 11:33:49',1,'gjhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh','2017-04-10 11:33:49','default_list.png',0),('23','100','wenge','3213','fa98ca1b-b2f5-4042-bfaa-8154d8cce83a','12321322','a91ce1a9-9a01-4e17-b3c6-ef65b756f784','1111','2017-04-10 11:33:49',1,'nkml;l','2017-04-10 11:33:49','default_list.png',0),('3','100','wenge','12321','d5933d9a-407d-4527-929c-b8993236acd6','12321321','9423325b-ac9a-4a2f-b327-cdffb265000e','4444','2017-04-10 11:33:49',1,'vfhvjfvj','2017-04-10 11:33:49','default_list.png',0),('43','100','wenge','3213','d5933d9a-407d-4527-929c-b8993236acd6','12321321','9423325b-ac9a-4a2f-b327-cdffb265000e','1111','2017-04-10 11:33:49',9,'不好使','2017-04-10 11:33:49','default_list.png',0),('5','100','wenge','123','d5933d9a-407d-4527-929c-b8993236acd6','12321321','9423325b-ac9a-4a2f-b327-cdffb265000e','3333','2017-04-10 11:33:49',1,'asfdsg','2017-04-10 11:33:49','default_list.png',0),('58','100','wenge','12321','fa98ca1b-b2f5-4042-bfaa-8154d8cce83a','12321322','a91ce1a9-9a01-4e17-b3c6-ef65b756f784','4444','2017-04-10 11:33:49',1,'文哥是煞笔','2017-04-10 11:33:49','default_list.png',0),('78','100','wenge','1234','fa98ca1b-b2f5-4042-bfaa-8154d8cce83a','12321322','a91ce1a9-9a01-4e17-b3c6-ef65b756f784','6666','2017-04-10 11:33:49',1,'nkml;l','2017-04-10 11:33:49','default_list.png',0),('80','100','wenge','324','fa98ca1b-b2f5-4042-bfaa-8154d8cce83a','12321322','a91ce1a9-9a01-4e17-b3c6-ef65b756f784','7777','2017-04-10 11:33:49',1,'dwegterh','2017-04-10 11:33:49','default_list.png',0),('90','123123','ceshi','123','279eee6dffcb','12321322','ef65b756f784','2222','2017-04-10 11:33:49',9,'fasdf','2017-04-10 11:33:49','',0),('93','123123','ceshi','123','279eee6dffcb','12321322','ef65b756f784','2222','2017-04-10 11:33:49',9,'fasdf','2017-04-10 11:33:49','',0),('94','123123','ceshi','123','87d5af70-f874-4b6c-9e29-279eee6dffcb','12321322','a91ce1a9-9a01-4e17-b3c6-ef65b756f784','2222','2017-04-10 11:33:49',9,'fasdf','2017-04-10 11:33:49','',0),('96','100','wenge','2121321','fa98ca1b-b2f5-4042-bfaa-8154d8cce83a','12321322','a91ce1a9-9a01-4e17-b3c6-ef65b756f784','2222','2017-04-10 11:33:49',9,'不好使','2017-04-10 11:33:49','default_list.png',0);
/*!40000 ALTER TABLE `baoxiu_maintenancelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_mistake`
--

DROP TABLE IF EXISTS `baoxiu_mistake`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baoxiu_mistake` (
  `mistakeId` varchar(50) NOT NULL,
  `mistakeDescription` varchar(45) NOT NULL COMMENT '错误描述',
  `listNumber` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '维修单号',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`mistakeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='错误表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_mistake`
--

LOCK TABLES `baoxiu_mistake` WRITE;
/*!40000 ALTER TABLE `baoxiu_mistake` DISABLE KEYS */;
/*!40000 ALTER TABLE `baoxiu_mistake` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_placebuilding`
--

DROP TABLE IF EXISTS `baoxiu_placebuilding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baoxiu_placebuilding` (
  `buildingId` varchar(50) NOT NULL,
  `buildingName` varchar(20) NOT NULL COMMENT '维修区域名',
  `distinctId` varchar(50) NOT NULL COMMENT '所属校区Id',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  `buildingNumber` varchar(45) NOT NULL,
  PRIMARY KEY (`buildingId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修区域或楼';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_placebuilding`
--

LOCK TABLES `baoxiu_placebuilding` WRITE;
/*!40000 ALTER TABLE `baoxiu_placebuilding` DISABLE KEYS */;
INSERT INTO `baoxiu_placebuilding` VALUES ('12321321','1building','9423325b-ac9a-4a2f-b327-cdffb265000e',0,'8867'),('12321322','2building','a91ce1a9-9a01-4e17-b3c6-ef65b756f784',0,'5746'),('309810a3-7856-4003-9a1a-82404895cfe5','测试地点二','9423325b-ac9a-4a2f-b327-cdffb265000e',1,'0002');
/*!40000 ALTER TABLE `baoxiu_placebuilding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_placedistinct`
--

DROP TABLE IF EXISTS `baoxiu_placedistinct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baoxiu_placedistinct` (
  `distinctId` varchar(50) NOT NULL COMMENT '校区Id',
  `distinctName` varchar(20) NOT NULL COMMENT '校区名',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  `distinctNumber` varchar(45) NOT NULL COMMENT '校区编号',
  PRIMARY KEY (`distinctId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修校区';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_placedistinct`
--

LOCK TABLES `baoxiu_placedistinct` WRITE;
/*!40000 ALTER TABLE `baoxiu_placedistinct` DISABLE KEYS */;
INSERT INTO `baoxiu_placedistinct` VALUES ('1db564a1-7fcd-4e90-bae8-a329969dd75a','南区',0,'001'),('9423325b-ac9a-4a2f-b327-cdffb265000e','旅顺一期',0,'003'),('a91ce1a9-9a01-4e17-b3c6-ef65b756f784','南区',0,'002');
/*!40000 ALTER TABLE `baoxiu_placedistinct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_placeroom`
--

DROP TABLE IF EXISTS `baoxiu_placeroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baoxiu_placeroom` (
  `roomId` varchar(50) NOT NULL,
  `roomName` varchar(20) NOT NULL COMMENT '维修房间名',
  `buildingId` varchar(50) NOT NULL COMMENT '所属维修区域id',
  `roomNumber` varchar(50) NOT NULL COMMENT '具体地点房间编号',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`roomId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修具体房间号码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_placeroom`
--

LOCK TABLES `baoxiu_placeroom` WRITE;
/*!40000 ALTER TABLE `baoxiu_placeroom` DISABLE KEYS */;
INSERT INTO `baoxiu_placeroom` VALUES ('10cef49f-da83-4c88-a2a7-aaecd8a755d5','测试','12321321','1112',1),('777b8489-c9f7-4462-8b78-5d24208b6c8d','测试位置二','12321321','1116',0),('87d5af70-f874-4b6c-9e29-279eee6dffcb','晚上测试','12321322','2222',1),('d5933d9a-407d-4527-929c-b8993236acd6','测试二','12321321','1115',0),('dab43f49-1c36-4ae4-be8b-b2483f5a2cb3','测试位置二','12321321','0002',1),('e1161586-d2c8-4967-986c-0fedb5b8d746','测试','12321321','1111',1),('fa98ca1b-b2f5-4042-bfaa-8154d8cce83a','测试三','12321322','3333',0);
/*!40000 ALTER TABLE `baoxiu_placeroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_printer`
--

DROP TABLE IF EXISTS `baoxiu_printer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baoxiu_printer` (
  `printerId` varchar(50) NOT NULL,
  `printerZHCNName` varchar(45) NOT NULL COMMENT '打印机中文描述',
  `printerNumber` varchar(45) NOT NULL,
  `printIp` varchar(50) NOT NULL,
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`printerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='打印机';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_printer`
--

LOCK TABLES `baoxiu_printer` WRITE;
/*!40000 ALTER TABLE `baoxiu_printer` DISABLE KEYS */;
/*!40000 ALTER TABLE `baoxiu_printer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_repairgroup`
--

DROP TABLE IF EXISTS `baoxiu_repairgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baoxiu_repairgroup` (
  `repairGroupId` varchar(50) NOT NULL COMMENT '维修小组ID',
  `groupNumber` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '维修小组编号',
  `groupName` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '维修小组名称',
  `groupPrinterIp` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '维修小组对应打印机编号',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`repairGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修小组表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_repairgroup`
--

LOCK TABLES `baoxiu_repairgroup` WRITE;
/*!40000 ALTER TABLE `baoxiu_repairgroup` DISABLE KEYS */;
INSERT INTO `baoxiu_repairgroup` VALUES ('123','123','ceshigroup','ceshiPriter',0);
/*!40000 ALTER TABLE `baoxiu_repairgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_set`
--

DROP TABLE IF EXISTS `baoxiu_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baoxiu_set` (
  `setId` varchar(50) NOT NULL,
  `setName` varchar(45) NOT NULL COMMENT '维修项目名称',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0',
  `setNumber` varchar(45) NOT NULL COMMENT '设备组唯一编号',
  PRIMARY KEY (`setId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备集合表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_set`
--

LOCK TABLES `baoxiu_set` WRITE;
/*!40000 ALTER TABLE `baoxiu_set` DISABLE KEYS */;
INSERT INTO `baoxiu_set` VALUES ('1111','测试1',0,''),('1112','测试2',0,'');
/*!40000 ALTER TABLE `baoxiu_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_workerinfo`
--

DROP TABLE IF EXISTS `baoxiu_workerinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_workerinfo`
--

LOCK TABLES `baoxiu_workerinfo` WRITE;
/*!40000 ALTER TABLE `baoxiu_workerinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `baoxiu_workerinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baoxiu_workertype`
--

DROP TABLE IF EXISTS `baoxiu_workertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baoxiu_workertype` (
  `typeId` varchar(50) NOT NULL,
  `typeName` varchar(20) NOT NULL COMMENT '工种类别名称',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：非删除   1：删除',
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修工种表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baoxiu_workertype`
--

LOCK TABLES `baoxiu_workertype` WRITE;
/*!40000 ALTER TABLE `baoxiu_workertype` DISABLE KEYS */;
/*!40000 ALTER TABLE `baoxiu_workertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `common_dictionary`
--

DROP TABLE IF EXISTS `common_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `common_dictionary`
--

LOCK TABLES `common_dictionary` WRITE;
/*!40000 ALTER TABLE `common_dictionary` DISABLE KEYS */;
INSERT INTO `common_dictionary` VALUES (1,1,'deleteFlag',1,'已删除',1,1),(2,1,'deleteFlag',0,'未删除',1,2),(3,2,'adminGender',1,'男',1,3),(4,2,'adminGender',2,'女',1,4),(5,3,'adminState',0,'未激活',1,5),(6,3,'adminState',1,'激活态',1,6),(7,4,'workerDepartment',1,'财务部',1,7),(8,4,'workerDepartment',2,'园艺部',1,8),(9,4,'workerDepartment',3,'工程部',1,9),(10,4,'workerDepartment',4,'设备科 ',1,10),(11,4,'workerDepartment',5,'其他',1,11),(12,5,'logAction',1,'检索',1,12),(13,5,'logAction',2,'删除',1,13),(14,5,'logAction',3,'添加',1,14),(15,5,'logAction',4,'修改',1,15),(16,5,'logAction',5,'其他',1,16),(17,6,'listState',1,'已提交',1,17),(18,6,'listState',2,'已派单',1,18),(19,6,'listState',3,'延期',1,19),(20,6,'listState',4,'等待派单',1,20),(21,6,'listState',5,'正在备件',1,21),(22,6,'listState',6,'已催单',1,22),(23,6,'listState',7,'已评价',1,23),(24,6,'listState',8,'待评价',1,24),(25,6,'listState',9,'其他',1,25),(26,7,'complaintSchedule',1,'处理中',1,26),(27,7,'complaintSchedule',2,'已完成',1,27),(28,7,'complaintSchedule',3,'其他',1,28);
/*!40000 ALTER TABLE `common_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shiro_permission`
--

DROP TABLE IF EXISTS `shiro_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_permission` (
  `permissionId` varchar(50) NOT NULL,
  `permissionName` varchar(45) NOT NULL COMMENT '权限名称',
  `permissionZHCNName` varchar(45) NOT NULL COMMENT '该权限中文描述',
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_permission`
--

LOCK TABLES `shiro_permission` WRITE;
/*!40000 ALTER TABLE `shiro_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `shiro_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shiro_role`
--

DROP TABLE IF EXISTS `shiro_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_role` (
  `roleId` varchar(55) NOT NULL,
  `roleName` varchar(45) NOT NULL COMMENT '角色名称',
  `roleZHCNName` varchar(45) NOT NULL COMMENT '角色的中文描述',
  `deleteFlag` varchar(45) NOT NULL DEFAULT '0' COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_role`
--

LOCK TABLES `shiro_role` WRITE;
/*!40000 ALTER TABLE `shiro_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `shiro_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shiro_rolepermission`
--

DROP TABLE IF EXISTS `shiro_rolepermission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_rolepermission` (
  `roleId` varchar(50) NOT NULL,
  `permissionId` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限中见表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_rolepermission`
--

LOCK TABLES `shiro_rolepermission` WRITE;
/*!40000 ALTER TABLE `shiro_rolepermission` DISABLE KEYS */;
/*!40000 ALTER TABLE `shiro_rolepermission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shiro_user`
--

DROP TABLE IF EXISTS `shiro_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_user` (
  `userId` varchar(55) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `adminUserId` varchar(45) NOT NULL DEFAULT '' COMMENT '与管理员用户1:1',
  `deleteFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:未删除 1:删除',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_user`
--

LOCK TABLES `shiro_user` WRITE;
/*!40000 ALTER TABLE `shiro_user` DISABLE KEYS */;
INSERT INTO `shiro_user` VALUES ('24355a91-2fcb-4a42-b906-c0763d267f24','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','9ee9ac13-87d9-42c0-af28-73610bbdf357',0),('3214','435634','546','534',1),('89089','78908','879','user2',0),('d9d74599-9439-42b3-b104-49297d5fde4f','zwy','21432654756','fd98d921-b2ce-42cf-8531-6f1a3341c53c',0);
/*!40000 ALTER TABLE `shiro_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shiro_userrole`
--

DROP TABLE IF EXISTS `shiro_userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_userrole` (
  `userId` varchar(55) NOT NULL,
  `roleId` varchar(55) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色中见表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_userrole`
--

LOCK TABLES `shiro_userrole` WRITE;
/*!40000 ALTER TABLE `shiro_userrole` DISABLE KEYS */;
/*!40000 ALTER TABLE `shiro_userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-12 16:52:04
