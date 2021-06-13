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
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `type` varchar(5) DEFAULT NULL COMMENT '菜单类型',
  `status` int(2) DEFAULT '1' COMMENT '状态,1可用0不可用',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(2) DEFAULT '0' COMMENT '是否删除,1已删0未删',
  `hidden` int(2) DEFAULT '0' COMMENT '隐藏',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,NULL,NULL,'系统管理','system',NULL,'el-icon-setting','0',1,NULL,'2021-02-18 20:19:01','2021-02-24 16:28:39',0,0,NULL),(2,1,'1','用户管理','user',NULL,'el-icon-user','0',1,NULL,'2021-02-18 20:20:27','2021-02-24 20:25:22',0,0,'user/index'),(3,2,'1,2','用户查询',NULL,'user:list',NULL,'1',1,NULL,'2021-02-18 20:23:52','2021-03-12 18:07:22',0,0,NULL),(4,1,'1','角色管理','role',NULL,'el-icon-s-custom','0',1,NULL,'2021-02-24 20:25:22','2021-03-12 19:18:40',0,0,'role/index'),(5,1,'1','菜单管理','menu',NULL,'el-icon-menu','0',1,NULL,'2021-02-24 20:27:23','2021-03-19 22:33:53',0,0,'menu/index'),(6,2,'1,2','用户删除',NULL,'user:delete',NULL,'1',1,NULL,'2021-03-08 15:11:37','2021-03-12 18:07:22',0,0,NULL),(7,2,'1,2','用户编辑',NULL,'user:edit',NULL,'1',1,NULL,'2021-03-08 15:12:24','2021-03-12 18:07:22',0,0,NULL),(8,2,'1,2','用户新增',NULL,'user:add',NULL,'1',1,NULL,'2021-03-08 15:12:43','2021-03-12 18:07:22',0,0,NULL),(9,5,'1,5','菜单新增',NULL,'menu:add',NULL,'1',1,NULL,'2021-03-08 15:13:17','2021-03-19 22:34:42',0,0,NULL),(10,5,'1,5','菜单查询',NULL,'menu:list',NULL,'1',1,NULL,'2021-03-08 15:13:42','2021-03-08 15:13:42',0,0,NULL),(11,5,'1,5','菜单编辑',NULL,'menu:edit',NULL,'1',1,NULL,'2021-03-08 15:14:21','2021-03-08 15:14:21',0,0,NULL),(16,1,'1','导航','guide',NULL,'el-icon-guide','0',1,NULL,'2021-03-08 15:18:52','2021-03-12 19:06:18',1,0,'guide/index'),(19,NULL,NULL,'监控管理','monitor',NULL,'el-icon-monitor','0',1,NULL,'2021-03-08 15:44:40','2021-03-08 15:44:40',0,0,NULL),(20,4,'1,4','角色新增',NULL,'role:add',NULL,'1',1,NULL,'2021-03-12 19:14:17','2021-03-12 19:18:40',0,0,NULL),(21,4,'1,4','角色修改',NULL,'role:edit',NULL,'1',1,NULL,'2021-03-12 19:15:08','2021-03-12 19:18:40',0,0,NULL),(22,4,'1,4','角色查询',NULL,'role:list',NULL,'1',1,NULL,'2021-03-12 19:15:45','2021-03-12 19:18:40',0,0,NULL),(23,4,'1,4','角色删除',NULL,'role:delete',NULL,'1',1,NULL,'2021-03-12 19:16:04','2021-03-12 19:18:40',0,0,NULL),(24,5,'1,5','菜单删除',NULL,'menu:delete',NULL,'1',1,NULL,'2021-03-12 19:16:53','2021-03-12 19:16:53',0,0,NULL),(25,4,'1,4','分配权限',NULL,'role:permission',NULL,'1',1,NULL,'2021-03-13 21:18:39','2021-03-13 21:18:39',0,0,NULL),(26,19,'19','Sql监控','sql',NULL,'el-icon-data-line','0',1,NULL,'2021-04-11 11:54:38','2021-04-11 12:02:57',0,0,'monitor/sql/index'),(27,NULL,NULL,'系统工具','tool',NULL,'el-icon-s-tools','0',1,NULL,'2021-04-11 11:57:49','2021-04-11 11:57:49',0,0,NULL),(28,27,'27','Swagger文档','swagger',NULL,'el-icon-document','0',1,NULL,'2021-04-11 11:59:25','2021-04-11 12:02:43',0,0,'tool/swagger/index'),(29,19,'19','服务监控','server',NULL,'el-icon-view','0',1,NULL,'2021-04-11 17:35:31','2021-04-11 17:35:31',0,0,'monitor/server/index'),(30,19,'19','日志监控','log',NULL,'el-icon-date','0',1,NULL,'2021-04-17 17:50:18','2021-04-17 17:50:18',0,0,'monitor/log/index'),(31,30,'19,30','日志查询',NULL,'role:list',NULL,'1',1,NULL,'2021-04-17 17:51:23','2021-04-17 17:51:23',0,0,NULL),(32,27,'27','聊天室','chat',NULL,'el-icon-chat-line-round','0',1,NULL,'2021-05-03 21:49:20','2021-05-03 21:49:20',0,0,'tool/chat/index'),(33,27,'27','定时任务','timing',NULL,'el-icon-timer','0',1,NULL,'2021-06-12 17:39:59','2021-06-12 17:39:59',0,0,'tool/timing/index'),(34,33,'27,33','任务查询',NULL,'quartz:list',NULL,'1',1,NULL,'2021-06-12 20:35:17','2021-06-12 20:35:17',0,0,NULL),(35,33,'27,33','增改任务',NULL,'quartz:exe',NULL,'1',1,NULL,'2021-06-12 20:36:06','2021-06-12 20:36:06',0,0,NULL),(36,33,'27,33','删除任务',NULL,'quartz:delete',NULL,'1',1,NULL,'2021-06-12 20:36:30','2021-06-12 20:36:30',0,0,NULL),(37,33,'27,33','手动执行一次',NULL,'quartz:trigger',NULL,'1',1,NULL,'2021-06-12 20:36:58','2021-06-12 20:36:58',0,0,NULL),(38,33,'27,33','暂停任务',NULL,'quartz:pause',NULL,'1',1,NULL,'2021-06-12 20:37:34','2021-06-12 20:37:34',0,0,NULL),(39,33,'27,33','恢复任务',NULL,'quartz:resume',NULL,'1',1,NULL,'2021-06-12 20:38:04','2021-06-12 20:38:04',0,0,NULL);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_blob_triggers`
--

DROP TABLE IF EXISTS `qrtz_blob_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_blob_triggers`
--

LOCK TABLES `qrtz_blob_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_calendars`
--

DROP TABLE IF EXISTS `qrtz_calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_calendars`
--

LOCK TABLES `qrtz_calendars` WRITE;
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_cron_triggers`
--

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_cron_triggers`
--

LOCK TABLES `qrtz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_cron_triggers` VALUES ('manageQuartzScheduler','我的任务','DEFAULT','0/10 * * * * ?','Asia/Shanghai'),('manageQuartzScheduler','测试任务失败','DEFAULT','0/5 * * * * ?','Asia/Shanghai');
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_fired_triggers`
--

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_fired_triggers`
--

LOCK TABLES `qrtz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_job_details`
--

DROP TABLE IF EXISTS `qrtz_job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_job_details`
--

LOCK TABLES `qrtz_job_details` WRITE;
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
INSERT INTO `qrtz_job_details` VALUES ('manageQuartzScheduler','我的任务','DEFAULT','第一个任务','com.guixin.quartz.SpringBeanJob','1','0','0','0','�\�\0sr\0org.quartz.JobDataMap���迩�\�\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�\�\��\�](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\�.�(v\n\�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0job_data_keysr\0com.guixin.pojo.QuartzJob\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0cronq\0~\0	L\0descriptionq\0~\0	L\0jobIdt\0Ljava/lang/Integer;L\0jobNameq\0~\0	L\0\nmethodNameq\0~\0	L\0pauseAfterFailureq\0~\0L\0statusq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0\nxpt\0myJobpsr\0java.util.Datehj�KYt\0\0xpw\0\0z\01`xt\00/10 * * * * ?t\0第一个任务sr\0java.lang.Integer⠤\���8\0I\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0t\0我的任务t\0testq\0~\0t\01ppx\0'),('manageQuartzScheduler','测试任务失败','DEFAULT','测试执行方法不正确的情况','com.guixin.quartz.SpringBeanJob','1','0','0','0','�\�\0sr\0org.quartz.JobDataMap���迩�\�\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�\�\��\�](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\�.�(v\n\�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0job_data_keysr\0com.guixin.pojo.QuartzJob\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0cronq\0~\0	L\0descriptionq\0~\0	L\0jobIdt\0Ljava/lang/Integer;L\0jobNameq\0~\0	L\0\nmethodNameq\0~\0	L\0pauseAfterFailureq\0~\0L\0statusq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0\nxpt\0myJobt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0z�\��xt\0\r0/5 * * * * ?t\0$测试执行方法不正确的情况sr\0java.lang.Integer⠤\���8\0I\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0t\0测试任务失败t\0abcsq\0~\0\0\0\0t\01t\0testsq\0~\0w\0\0z\�]�xx\0');
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_locks`
--

DROP TABLE IF EXISTS `qrtz_locks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_locks`
--

LOCK TABLES `qrtz_locks` WRITE;
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
INSERT INTO `qrtz_locks` VALUES ('manageQuartzScheduler','STATE_ACCESS'),('manageQuartzScheduler','TRIGGER_ACCESS');
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_paused_trigger_grps`
--

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_paused_trigger_grps`
--

LOCK TABLES `qrtz_paused_trigger_grps` WRITE;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_scheduler_state`
--

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_scheduler_state`
--

LOCK TABLES `qrtz_scheduler_state` WRITE;
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
INSERT INTO `qrtz_scheduler_state` VALUES ('manageQuartzScheduler','LAPTOP-RQ27LOGS1623596253826',1623596725721,10000);
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simple_triggers`
--

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simple_triggers`
--

LOCK TABLES `qrtz_simple_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simprop_triggers`
--

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simprop_triggers`
--

LOCK TABLES `qrtz_simprop_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_triggers`
--

DROP TABLE IF EXISTS `qrtz_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_triggers`
--

LOCK TABLES `qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_triggers` VALUES ('manageQuartzScheduler','我的任务','DEFAULT','我的任务','DEFAULT',NULL,1623556470000,1623556460000,5,'PAUSED','CRON',1623556422000,0,NULL,2,''),('manageQuartzScheduler','测试任务失败','DEFAULT','测试任务失败','DEFAULT',NULL,1623594400000,1623594395000,5,'PAUSED','CRON',1623594393000,0,NULL,2,'');
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_job`
--

DROP TABLE IF EXISTS `quartz_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `quartz_job` (
  `job_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `job_name` varchar(100) DEFAULT NULL COMMENT '任务名',
  `description` varchar(100) DEFAULT NULL COMMENT '任务描述',
  `cron` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `bean_name` varchar(100) DEFAULT NULL COMMENT '任务执行时调用的spring的bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '任务方法名',
  `status` varchar(1) DEFAULT NULL COMMENT '任务状态（1运行中 2暂停中 3未启动)',
  `pause_after_failure` int(3) DEFAULT NULL COMMENT '任务失败后是否暂停',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='quartz定时任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_job`
--

LOCK TABLES `quartz_job` WRITE;
/*!40000 ALTER TABLE `quartz_job` DISABLE KEYS */;
INSERT INTO `quartz_job` VALUES (1,'我的任务','第一个任务','0/10 * * * * ?','myJob','test','2',1,'admin','admin','2021-06-12 20:03:40','2021-06-13 11:54:25'),(2,'测试任务失败','测试执行方法不正确的情况','0/5 * * * * ?','myJob','abc','2',1,'admin','test','2021-06-13 12:00:14','2021-06-13 22:26:33');
/*!40000 ALTER TABLE `quartz_job` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=313 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (104,3,7),(105,3,8),(106,3,21),(107,3,10),(108,3,1),(109,3,2),(110,3,4),(111,3,5),(263,1,1),(264,1,2),(265,1,3),(266,1,6),(267,1,7),(268,1,8),(269,1,4),(270,1,20),(271,1,21),(272,1,22),(273,1,23),(274,1,25),(275,1,5),(276,1,9),(277,1,10),(278,1,11),(279,1,24),(280,1,19),(281,1,26),(282,1,29),(283,1,30),(284,1,31),(285,1,27),(286,1,28),(287,1,32),(288,1,33),(289,1,34),(290,1,35),(291,1,36),(292,1,37),(293,1,38),(294,1,39),(295,2,3),(296,2,7),(297,2,8),(298,2,22),(299,2,9),(300,2,10),(301,2,11),(302,2,28),(303,2,32),(304,2,34),(305,2,35),(306,2,37),(307,2,1),(308,2,2),(309,2,4),(310,2,5),(311,2,27),(312,2,33);
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_log` (
  `log_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` varchar(255) DEFAULT NULL COMMENT '日志类型',
  `description` varchar(255) DEFAULT NULL COMMENT '日志描述',
  `ip` varchar(30) DEFAULT NULL COMMENT '操作ip地址',
  `addr` varchar(25) DEFAULT NULL COMMENT '操作地址',
  `request_uri` varchar(30) DEFAULT NULL COMMENT '请求uri',
  `method_name` varchar(255) NOT NULL,
  `method` varchar(10) DEFAULT NULL COMMENT '请求方式(POST,GET,PUT,DELETE)',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求提交参数',
  `time` bigint(20) DEFAULT NULL COMMENT '请求时间',
  `exception_detail` text COMMENT '错误详情',
  `create_by` varchar(20) DEFAULT NULL COMMENT '操作人',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8 COMMENT='系统日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (5,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',18,NULL,'admin','2021-04-17 09:29:12'),(6,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',37,NULL,'admin','2021-04-17 09:29:14'),(11,'INFO','查询服务监控','127.0.0.1','内网IP','/monitor','com.guixin.controller.MonitorController.MonitorQuery()','GET','',1079,NULL,'admin','2021-04-17 09:29:49'),(12,'INFO','查询服务监控','127.0.0.1','内网IP','/monitor','com.guixin.controller.MonitorController.MonitorQuery()','GET','',1069,NULL,'admin','2021-04-17 09:29:52'),(17,'INFO','个人中心编辑信息','127.0.0.1','内网IP','/user/center','com.guixin.controller.UserController.center()','POST','',211,NULL,'test','2021-04-17 09:33:33'),(20,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',18,NULL,'test','2021-04-17 09:36:33'),(24,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',64,NULL,'admin','2021-04-17 09:41:03'),(25,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',69,NULL,'admin','2021-04-17 09:41:31'),(27,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',14,NULL,'admin','2021-04-17 09:41:34'),(28,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',7,NULL,'admin','2021-04-17 09:42:04'),(29,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%E7%AE%A1%E7%90%86%5D',98,NULL,'admin','2021-04-17 09:42:10'),(30,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',7,NULL,'admin','2021-04-17 09:42:37'),(31,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',5,NULL,'admin','2021-04-17 09:42:54'),(33,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',6,NULL,'admin','2021-04-17 09:45:18'),(34,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',14,NULL,'admin','2021-04-17 09:46:52'),(35,'INFO','新增菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.add()','POST','',424,NULL,'admin','2021-04-17 09:50:19'),(36,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',10,NULL,'admin','2021-04-17 09:50:19'),(37,'INFO','新增菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.add()','POST','',257,NULL,'admin','2021-04-17 09:51:23'),(38,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',7,NULL,'admin','2021-04-17 09:51:24'),(39,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',6,NULL,'admin','2021-04-17 09:51:58'),(40,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',8,NULL,'admin','2021-04-17 09:52:00'),(41,'INFO','分配权限','127.0.0.1','内网IP','/role/permission','com.guixin.controller.RoleController.Permission()','POST','',333,NULL,'admin','2021-04-17 09:52:09'),(43,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',6,NULL,'admin','2021-04-17 09:52:14'),(47,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',66,NULL,'admin','2021-04-17 14:05:51'),(48,'INFO','删除用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.del()','DELETE','',168,NULL,'admin','2021-04-17 14:05:55'),(49,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',16,NULL,'admin','2021-04-17 14:05:56'),(50,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',175,NULL,'admin','2021-04-18 07:02:04'),(53,'ERROR','新增角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.add()','POST','',0,'com.guixin.exception.CustomException: 角色名已存在！\r\n	at com.guixin.service.impl.RoleServiceImpl.addRole(RoleServiceImpl.java:43)\r\n	at com.guixin.service.impl.RoleServiceImpl$$FastClassBySpringCGLIB$$a1bcd9c9.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at com.guixin.service.impl.RoleServiceImpl$$EnhancerBySpringCGLIB$$3cf1f570.addRole(<generated>)\r\n	at com.guixin.controller.RoleController.add(RoleController.java:69)\r\n	at com.guixin.controller.RoleController$$FastClassBySpringCGLIB$$a89f95c5.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:779)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)\r\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:64)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\r\n	at com.guixin.log.LogAspect.around(LogAspect.java:41)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:634)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:624)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:72)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)\r\n	at org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor.invoke(MethodSecurityInterceptor.java:61)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:692)\r\n	at com.guixin.controller.RoleController$$EnhancerBySpringCGLIB$$18f43063.add(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:197)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:141)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:894)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1060)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:962)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:652)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:113)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:327)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:115)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:113)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:126)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:149)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:218)\r\n	at org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:212)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at com.guixin.auth.JwtAuthenticationTokenFilter.doFilterInternal(JwtAuthenticationTokenFilter.java:48)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:748)\r\n','admin','2021-04-18 07:28:00'),(54,'ERROR','新增角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.add()','POST','',136,'com.guixin.exception.CustomException: 角色名已存在！\r\n	at com.guixin.service.impl.RoleServiceImpl.addRole(RoleServiceImpl.java:43)\r\n	at com.guixin.service.impl.RoleServiceImpl$$FastClassBySpringCGLIB$$a1bcd9c9.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at com.guixin.service.impl.RoleServiceImpl$$EnhancerBySpringCGLIB$$40672c9.addRole(<generated>)\r\n	at com.guixin.controller.RoleController.add(RoleController.java:69)\r\n	at com.guixin.controller.RoleController$$FastClassBySpringCGLIB$$a89f95c5.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:779)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)\r\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:64)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\r\n	at com.guixin.log.LogAspect.around(LogAspect.java:41)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:634)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:624)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:72)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)\r\n	at org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor.invoke(MethodSecurityInterceptor.java:61)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:750)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:692)\r\n	at com.guixin.controller.RoleController$$EnhancerBySpringCGLIB$$f80d43a6.add(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:197)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:141)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:894)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1060)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:962)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:652)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:113)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:327)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:115)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:113)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:126)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:149)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:218)\r\n	at org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:212)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at com.guixin.auth.JwtAuthenticationTokenFilter.doFilterInternal(JwtAuthenticationTokenFilter.java:48)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:748)\r\n','admin','2021-04-18 07:29:55'),(55,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',15,NULL,'admin','2021-05-03 13:43:16'),(56,'INFO','新增菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.add()','POST','',183,NULL,'admin','2021-05-03 13:49:20'),(57,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',16,NULL,'admin','2021-05-03 13:49:21'),(58,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',153,NULL,'admin','2021-05-03 13:49:28'),(59,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',11,NULL,'admin','2021-05-03 13:49:31'),(60,'INFO','分配权限','127.0.0.1','内网IP','/role/permission','com.guixin.controller.RoleController.Permission()','POST','',356,NULL,'admin','2021-05-03 13:49:36'),(61,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',11,NULL,'admin','2021-05-03 13:49:42'),(62,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',130,NULL,'admin','2021-05-04 02:33:38'),(63,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',19,NULL,'admin','2021-05-04 02:33:41'),(64,'INFO','分配权限','127.0.0.1','内网IP','/role/permission','com.guixin.controller.RoleController.Permission()','POST','',275,NULL,'admin','2021-05-04 02:34:04'),(65,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',114,NULL,'admin','2021-05-05 04:01:19'),(66,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',48,NULL,'admin','2021-05-05 04:03:37'),(67,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',13,NULL,'admin','2021-05-05 04:14:10'),(68,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',9,NULL,'admin','2021-05-05 04:14:41'),(69,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',7,NULL,'admin','2021-05-05 04:15:33'),(70,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',13,NULL,'admin','2021-05-05 04:17:49'),(71,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',11,NULL,'admin','2021-05-05 04:20:52'),(72,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',6,NULL,'admin','2021-05-05 04:21:43'),(73,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',9,NULL,'admin','2021-05-05 04:22:37'),(74,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',7,NULL,'admin','2021-05-05 04:23:10'),(75,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',5,NULL,'admin','2021-05-05 04:23:16'),(76,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',5,NULL,'admin','2021-05-05 04:23:56'),(77,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',11,NULL,'admin','2021-05-05 04:25:06'),(78,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',14,NULL,'admin','2021-05-05 04:27:34'),(79,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',11,NULL,'admin','2021-05-05 04:28:52'),(80,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',13,NULL,'admin','2021-05-05 04:29:00'),(81,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',7,NULL,'admin','2021-05-05 04:29:26'),(82,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',15,NULL,'admin','2021-05-05 04:32:35'),(83,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',14,NULL,'admin','2021-05-05 04:41:55'),(84,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',25,NULL,'admin','2021-05-05 04:46:05'),(85,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',7,NULL,'admin','2021-05-05 04:46:57'),(86,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',5,NULL,'admin','2021-05-05 04:47:10'),(87,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',12,NULL,'admin','2021-05-05 04:48:40'),(88,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',8,NULL,'admin','2021-05-05 04:48:46'),(89,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',14,NULL,'admin','2021-06-12 09:19:09'),(90,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',99,NULL,'admin','2021-06-12 09:33:27'),(91,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',43,NULL,'admin','2021-06-12 09:36:29'),(92,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',14,NULL,'admin','2021-06-12 09:36:35'),(93,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',5,NULL,'admin','2021-06-12 09:39:01'),(94,'INFO','新增菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.add()','POST','',97,NULL,'admin','2021-06-12 09:39:59'),(95,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',7,NULL,'admin','2021-06-12 09:39:59'),(96,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',7,NULL,'admin','2021-06-12 09:40:14'),(97,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',55,NULL,'admin','2021-06-12 09:40:43'),(98,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',6,NULL,'admin','2021-06-12 09:40:46'),(99,'INFO','分配权限','127.0.0.1','内网IP','/role/permission','com.guixin.controller.RoleController.Permission()','POST','',409,NULL,'admin','2021-06-12 09:40:56'),(100,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',5,NULL,'admin','2021-06-12 09:41:00'),(101,'INFO','查询用户','127.0.0.1','内网IP','/user','com.guixin.controller.UserController.getUserList()','GET','pageNum=%5B1%5D&pageSize=%5B8%5D&username=%5B%5D',9,NULL,'admin','2021-06-12 10:24:00'),(102,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',32,NULL,'admin','2021-06-12 10:24:03'),(103,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',56,NULL,'admin','2021-06-12 10:24:07'),(104,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',5,NULL,'admin','2021-06-12 10:24:10'),(105,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',8,NULL,'admin','2021-06-12 10:26:51'),(106,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',6,NULL,'admin','2021-06-12 10:27:27'),(107,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',54,NULL,'admin','2021-06-12 10:27:30'),(108,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',8,NULL,'admin','2021-06-12 10:28:00'),(109,'INFO','新增/修改定时任务','0:0:0:0:0:0:0:1','湖南省 株洲市','/quartz','com.guixin.controller.QuartzJobController.AddOrSave()','POST','',1459,NULL,'admin','2021-06-12 12:03:40'),(110,'INFO','新增/修改定时任务','0:0:0:0:0:0:0:1','湖南省 株洲市','/quartz','com.guixin.controller.QuartzJobController.AddOrSave()','POST','',931,NULL,'admin','2021-06-12 12:07:36'),(111,'INFO','新增/修改定时任务','0:0:0:0:0:0:0:1','湖南省 株洲市','/quartz','com.guixin.controller.QuartzJobController.AddOrSave()','POST','',1636,NULL,'admin','2021-06-12 12:10:09'),(112,'INFO','新增/修改定时任务','0:0:0:0:0:0:0:1','湖南省 株洲市','/quartz','com.guixin.controller.QuartzJobController.AddOrSave()','POST','',1567,NULL,'admin','2021-06-12 12:10:59'),(113,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',165,NULL,'admin','2021-06-12 12:34:09'),(114,'INFO','新增菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.add()','POST','',168,NULL,'admin','2021-06-12 12:35:17'),(115,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',8,NULL,'admin','2021-06-12 12:35:17'),(116,'INFO','新增菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.add()','POST','',111,NULL,'admin','2021-06-12 12:36:06'),(117,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',8,NULL,'admin','2021-06-12 12:36:06'),(118,'INFO','新增菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.add()','POST','',144,NULL,'admin','2021-06-12 12:36:31'),(119,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',7,NULL,'admin','2021-06-12 12:36:31'),(120,'INFO','新增菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.add()','POST','',138,NULL,'admin','2021-06-12 12:36:58'),(121,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',6,NULL,'admin','2021-06-12 12:36:58'),(122,'INFO','新增菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.add()','POST','',92,NULL,'admin','2021-06-12 12:37:35'),(123,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',9,NULL,'admin','2021-06-12 12:37:35'),(124,'INFO','新增菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.add()','POST','',130,NULL,'admin','2021-06-12 12:38:04'),(125,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',5,NULL,'admin','2021-06-12 12:38:04'),(126,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',116,NULL,'admin','2021-06-12 12:38:19'),(127,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',26,NULL,'admin','2021-06-12 12:38:21'),(128,'INFO','分配权限','127.0.0.1','内网IP','/role/permission','com.guixin.controller.RoleController.Permission()','POST','',321,NULL,'admin','2021-06-12 12:38:35'),(186,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',164,NULL,'admin','2021-06-13 08:27:59'),(187,'INFO','手动执行一次任务','127.0.0.1','内网IP','/quartz/trigger','com.guixin.controller.QuartzJobController.triggerJob()','PUT','',419,NULL,'admin','2021-06-13 08:28:07'),(188,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',19,NULL,'admin','2021-06-13 08:28:07'),(189,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',60,NULL,'admin','2021-06-13 08:44:50'),(190,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',103,NULL,'admin','2021-06-13 10:21:12'),(191,'INFO','新增/修改定时任务','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.AddOrSave()','POST','',710,NULL,'admin','2021-06-13 10:22:07'),(192,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',10,NULL,'admin','2021-06-13 10:22:07'),(193,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',52,NULL,'admin','2021-06-13 10:22:24'),(194,'INFO','新增/修改定时任务','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.AddOrSave()','POST','',959,NULL,'admin','2021-06-13 10:22:51'),(195,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',11,NULL,'admin','2021-06-13 10:22:51'),(196,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',6,NULL,'admin','2021-06-13 10:23:02'),(197,'INFO','恢复定时任务','127.0.0.1','内网IP','/quartz/resume','com.guixin.controller.QuartzJobController.resume()','PUT','',227,NULL,'admin','2021-06-13 10:23:06'),(198,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',5,NULL,'admin','2021-06-13 10:23:06'),(199,'INFO','暂停定时任务','127.0.0.1','内网IP','/quartz/pause','com.guixin.controller.QuartzJobController.pause()','PUT','',7,NULL,'admin','2021-06-13 10:23:19'),(200,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',7,NULL,'admin','2021-06-13 10:23:19'),(201,'INFO','删除定时任务','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.delete()','DELETE','',674,NULL,'admin','2021-06-13 10:23:38'),(202,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',6,NULL,'admin','2021-06-13 10:23:39'),(203,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',16,NULL,'admin','2021-06-13 14:24:53'),(204,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',97,NULL,'admin','2021-06-13 14:24:55'),(205,'INFO','查询菜单','127.0.0.1','内网IP','/menu','com.guixin.controller.MenuController.getAllMenus()','GET','',10,NULL,'admin','2021-06-13 14:24:58'),(206,'INFO','分配权限','127.0.0.1','内网IP','/role/permission','com.guixin.controller.RoleController.Permission()','POST','',261,NULL,'admin','2021-06-13 14:25:16'),(207,'INFO','查询角色','127.0.0.1','内网IP','/role','com.guixin.controller.RoleController.PageRoleList()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&query=%5B%5D',7,NULL,'test','2021-06-13 14:25:40'),(208,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',57,NULL,'test','2021-06-13 14:25:54'),(209,'INFO','手动执行一次任务','127.0.0.1','内网IP','/quartz/trigger','com.guixin.controller.QuartzJobController.triggerJob()','PUT','',454,NULL,'test','2021-06-13 14:26:09'),(210,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',7,NULL,'test','2021-06-13 14:26:09'),(211,'INFO','新增/修改定时任务','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.AddOrSave()','POST','',897,NULL,'test','2021-06-13 14:26:34'),(212,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',12,NULL,'test','2021-06-13 14:26:34'),(213,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',4,NULL,'test','2021-06-13 14:26:45'),(214,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%E6%88%91%E7%9A%84%E4%BB%BB%E5%8A%A1%5D',9,NULL,'test','2021-06-13 14:27:03'),(215,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B20%5D&jobName=%5B%E6%88%91%E7%9A%84%E4%BB%BB%E5%8A%A1%5D',4,NULL,'test','2021-06-13 14:27:06'),(216,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B20%5D&jobName=%5B%5D',8,NULL,'test','2021-06-13 14:27:13'),(217,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B20%5D&jobName=%5B%5D',30,NULL,'test','2021-06-13 14:27:17'),(218,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',6,NULL,'admin','2021-06-13 14:28:29'),(219,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',102,NULL,'test','2021-06-13 14:47:22'),(220,'INFO','手动执行一次任务','127.0.0.1','内网IP','/quartz/trigger','com.guixin.controller.QuartzJobController.triggerJob()','PUT','',407,NULL,'test','2021-06-13 14:47:45'),(221,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','pageNum=%5B1%5D&pageSize=%5B5%5D&jobName=%5B%5D',9,NULL,'test','2021-06-13 14:47:45'),(222,'INFO','手动执行一次任务','127.0.0.1','内网IP','/quartz/trigger','com.guixin.controller.QuartzJobController.triggerJob()','PUT','{QuartzJob(jobId=1, jobName=我的任务, description=第一个任务, cron=0/10 * * * * ?, beanName=myJob, methodName=test, status=2, pauseAfterFailure=1, createBy=null, updateBy=null, createTime=Sat Jun 12 20:03:40 CST 2021, updateTime=null) }',369,NULL,'test','2021-06-13 15:01:51'),(223,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','{1 5  }',182,NULL,'test','2021-06-13 15:01:51'),(224,'INFO','查询定时任务列表','127.0.0.1','内网IP','/quartz','com.guixin.controller.QuartzJobController.jobPage()','GET','{1 5  }',9,NULL,'admin','2021-06-13 15:02:43');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
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
INSERT INTO `user` VALUES (1,'admin','男','18888888888','1234@qq.com','http://guixin-manage.oss-cn-hangzhou.aliyuncs.com/2021/04/04/37d71f02e9744f978821b64dc4d87bfb.png','$2a$10$oKXThTINDhwIYxzGpa1WLORLBNAGeP5qOnmXgpwqgykCsIfIM6CcW',1,'2021-02-18 15:33:01','2021-04-04 23:47:45',0),(3,'test','女','13888888888','1234@163.com','http://guixin-manage.oss-cn-hangzhou.aliyuncs.com/2021/04/04/b458b7a39688438a8a9f417f35628cb4.png','$2a$10$DZhrkZJ2QirJ694XxvOAIe9xBJwcl45TYlaQ5Yi7M6BzU6Qdcd13C',1,'2021-03-04 22:25:32','2021-04-17 17:33:32',0),(4,'test1','女','13988888888','138@163.com',NULL,'$2a$10$XZ1ZBK9zjtvktM865UDF/eD1lxWXmq.b211/5GOKYGidD1imXyLGi',1,'2021-03-04 23:07:42','2021-04-17 22:06:26',0);
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

-- Dump completed on 2021-06-13 23:07:45
