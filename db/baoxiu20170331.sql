-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: baoxiu
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `shiro_permission`
--

DROP TABLE IF EXISTS `shiro_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiro_permission` (
  `permissionId` varchar(50) NOT NULL,
  `permissionName` varchar(45) NOT NULL,
  `permissionZHCNName` varchar(45) NOT NULL,
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `roleName` varchar(45) NOT NULL,
  `roleZHCNName` varchar(45) NOT NULL,
  `deleteFlag` varchar(45) NOT NULL DEFAULT '0' COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
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
  `adminId` varchar(45) NOT NULL DEFAULT '' COMMENT '与管理员用户1:1',
  `deleteFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:未删除 1:删除',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_user`
--

LOCK TABLES `shiro_user` WRITE;
/*!40000 ALTER TABLE `shiro_user` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiro_userrole`
--

LOCK TABLES `shiro_userrole` WRITE;
/*!40000 ALTER TABLE `shiro_userrole` DISABLE KEYS */;
/*!40000 ALTER TABLE `shiro_userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Table structure for table `lgb_log`
--

DROP TABLE IF EXISTS `baoxiu_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baoxiu_log` (
  `logId` int(11) NOT NULL AUTO_INCREMENT,
  `logAction` tinyint(2) NOT NULL COMMENT '操作：1：检索 2：删除 3：添加 4：修改 5：其他',
  `logLevel` tinyint(2) NOT NULL COMMENT '级别：1：提示',
  `logContent` varchar(255) NOT NULL COMMENT '内容',
  `logUser` varchar(45) NOT NULL COMMENT '操作者',
  `logTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：否1：删除',
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=494 DEFAULT CHARSET=utf8mb4 COMMENT='日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-31 22:45:18
