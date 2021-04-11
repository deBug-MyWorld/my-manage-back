-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: my_manage
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `menu_pid` int(11) DEFAULT NULL COMMENT '父id',
  `menu_pids` varchar(64) DEFAULT NULL COMMENT '所有的父id',
  `menu_name` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(64) DEFAULT NULL COMMENT '菜单路径',
  `permission` varchar(64) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(20) DEFAULT NULL COMMENT '菜单图标',
  `type` varchar(5) DEFAULT NULL COMMENT '菜单类型',
  `status` int(2) DEFAULT '1' COMMENT '状态,1可用0不可用',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(2) DEFAULT '0' COMMENT '是否删除,1已删0未删',
  `hidden` int(2) DEFAULT '0' COMMENT '隐藏',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,NULL,NULL,'系统管理','system',NULL,'el-icon-setting','0',1,NULL,'2021-02-18 20:19:01','2021-02-24 16:28:39',0,0,NULL),(2,1,'1','用户管理','user',NULL,'el-icon-user','0',1,NULL,'2021-02-18 20:20:27','2021-02-24 20:25:22',0,0,'user/index'),(3,2,'1,2','用户查询',NULL,'user:list',NULL,'1',1,NULL,'2021-02-18 20:23:52','2021-03-12 18:07:22',0,0,NULL),(4,1,'1','角色管理','role',NULL,'el-icon-s-custom','0',1,NULL,'2021-02-24 20:25:22','2021-03-12 19:18:40',0,0,'role/index'),(5,1,'1','菜单管理','menu',NULL,'el-icon-menu','0',1,NULL,'2021-02-24 20:27:23','2021-03-19 22:33:53',0,0,'menu/index'),(6,2,'1,2','用户删除',NULL,'user:delete',NULL,'1',1,NULL,'2021-03-08 15:11:37','2021-03-12 18:07:22',0,0,NULL),(7,2,'1,2','用户编辑',NULL,'user:edit',NULL,'1',1,NULL,'2021-03-08 15:12:24','2021-03-12 18:07:22',0,0,NULL),(8,2,'1,2','用户新增',NULL,'user:add',NULL,'1',1,NULL,'2021-03-08 15:12:43','2021-03-12 18:07:22',0,0,NULL),(9,5,'1,5','菜单新增',NULL,'menu:add',NULL,'1',1,NULL,'2021-03-08 15:13:17','2021-03-19 22:34:42',0,0,NULL),(10,5,'1,5','菜单查询',NULL,'menu:list',NULL,'1',1,NULL,'2021-03-08 15:13:42','2021-03-08 15:13:42',0,0,NULL),(11,5,'1,5','菜单编辑',NULL,'menu:edit',NULL,'1',1,NULL,'2021-03-08 15:14:21','2021-03-08 15:14:21',0,0,NULL),(16,1,'1','导航','guide',NULL,'el-icon-guide','0',1,NULL,'2021-03-08 15:18:52','2021-03-12 19:06:18',1,0,'guide/index'),(19,NULL,NULL,'监控管理','monitor',NULL,'el-icon-monitor','0',1,NULL,'2021-03-08 15:44:40','2021-03-08 15:44:40',0,0,NULL),(20,4,'1,4','角色新增',NULL,'role:add',NULL,'1',1,NULL,'2021-03-12 19:14:17','2021-03-12 19:18:40',0,0,NULL),(21,4,'1,4','角色修改',NULL,'role:edit',NULL,'1',1,NULL,'2021-03-12 19:15:08','2021-03-12 19:18:40',0,0,NULL),(22,4,'1,4','角色查询',NULL,'role:list',NULL,'1',1,NULL,'2021-03-12 19:15:45','2021-03-12 19:18:40',0,0,NULL),(23,4,'1,4','角色删除',NULL,'role:delete',NULL,'1',1,NULL,'2021-03-12 19:16:04','2021-03-12 19:18:40',0,0,NULL),(24,5,'1,5','菜单删除',NULL,'menu:delete',NULL,'1',1,NULL,'2021-03-12 19:16:53','2021-03-12 19:16:53',0,0,NULL),(25,4,'1,4','分配权限',NULL,'role:permission',NULL,'1',1,NULL,'2021-03-13 21:18:39','2021-03-13 21:18:39',0,0,NULL),(26,19,'19','Sql监控','sql',NULL,'el-icon-data-line','0',1,NULL,'2021-04-11 11:54:38','2021-04-11 12:02:57',0,0,'monitor/sql/index'),(27,NULL,NULL,'系统工具','tool',NULL,'el-icon-s-tools','0',1,NULL,'2021-04-11 11:57:49','2021-04-11 11:57:49',0,0,NULL),(28,27,'27','Swagger文档','swagger',NULL,'el-icon-document','0',1,NULL,'2021-04-11 11:59:25','2021-04-11 12:02:43',0,0,'tool/swagger/index'),(29,19,'19','服务监控','server',NULL,'el-icon-view','0',1,NULL,'2021-04-11 17:35:31','2021-04-11 17:35:31',0,0,'monitor/server/index');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色英文名称',
  `status` int(2) DEFAULT '1' COMMENT '状态,1可用0不可用',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` int(2) DEFAULT '0' COMMENT '是否删除,1已删0未删',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'管理员','admin',1,'2021-03-19 22:31:06','2021-02-18 15:36:40',0,1),(2,'测试人员','test',1,'2021-03-06 21:30:04','2021-03-04 14:21:24',0,2),(3,'普通用户','common',1,'2021-03-13 11:27:21','2021-03-12 23:25:35',0,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (85,2,3),(86,2,7),(87,2,8),(88,2,22),(89,2,9),(90,2,10),(91,2,11),(92,2,1),(93,2,2),(94,2,4),(95,2,5),(104,3,7),(105,3,8),(106,3,21),(107,3,10),(108,3,1),(109,3,2),(110,3,4),(111,3,5),(152,1,1),(153,1,2),(154,1,3),(155,1,6),(156,1,7),(157,1,8),(158,1,4),(159,1,20),(160,1,21),(161,1,22),(162,1,23),(163,1,25),(164,1,5),(165,1,9),(166,1,10),(167,1,11),(168,1,24),(169,1,19),(170,1,26),(171,1,29),(172,1,27),(173,1,28);
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `enabled` int(20) DEFAULT NULL COMMENT '状态：1启用、0禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(2) DEFAULT '0' COMMENT '是否删除,1已删0未删',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','男','18888888888','1234@qq.com','http://guixin-manage.oss-cn-hangzhou.aliyuncs.com/2021/04/04/37d71f02e9744f978821b64dc4d87bfb.png','$2a$10$oKXThTINDhwIYxzGpa1WLORLBNAGeP5qOnmXgpwqgykCsIfIM6CcW',1,'2021-02-18 15:33:01','2021-04-04 23:47:45',0),(3,'test','女','13888888888','123@163.com','http://guixin-manage.oss-cn-hangzhou.aliyuncs.com/2021/04/04/b458b7a39688438a8a9f417f35628cb4.png','$2a$10$DZhrkZJ2QirJ694XxvOAIe9xBJwcl45TYlaQ5Yi7M6BzU6Qdcd13C',1,'2021-03-04 22:25:32','2021-04-04 23:39:51',0),(4,'test1','女','13988888888','138@163.com',NULL,'$2a$10$XZ1ZBK9zjtvktM865UDF/eD1lxWXmq.b211/5GOKYGidD1imXyLGi',1,'2021-03-04 23:07:42','2021-04-09 17:05:16',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(20) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (23,1,1),(26,4,3),(27,3,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'my_manage'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-11 22:40:46
