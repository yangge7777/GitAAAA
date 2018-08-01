-- MySQL dump 10.13  Distrib 5.7.19, for macos10.12 (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `bid` char(32) NOT NULL,
  `bname` varchar(100) DEFAULT NULL,
  `price` decimal(5,1) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `cid` char(32) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  KEY `cid` (`cid`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('1','Java编程思想(第4版)',75.6,'qdmmy6','book_img/9317290-1_l.jpg','1'),('2','Java核心技术卷1',68.5,'qdmmy6','book_img/20285763-1_l.jpg','1'),('3','Java就业培训教程',39.9,'张孝祥','book_img/8758723-1_l.jpg','1'),('4','Head First java',47.5,'(美) 塞若','book_img/9265169-1_l.jpg','1'),('5','JavaWeb开发详解',83.3,'孙鑫','book_img/22788412-1_l.jpg','2'),('6','Struts2深入详解',63.2,'孙鑫','book_img/20385925-1_l.jpg','2'),('7','精通Hibernate',30.0,'孙卫琴','book_img/8991366-1_l.jpg','2'),('8','精通Spring2.x',63.2,'陈华雄','book_img/20029394-1_l.jpg','2'),('89401C6F761946748DF07D4EAD60B4F2','帅逼的自我修养',666.0,'李硕','/photo/2102211475974D3CB04691BD9831FA88a.jpg','C135EDB351094999957AFCE1A90CC388'),('9','Javascript权威指南',93.6,'(美)弗兰纳根','book_img/22722790-1_l.jpg','3');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartlist`
--

DROP TABLE IF EXISTS `cartlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartlist` (
  `uid` varchar(50) DEFAULT NULL,
  `bid` varchar(50) DEFAULT NULL,
  `count` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartlist`
--

LOCK TABLES `cartlist` WRITE;
/*!40000 ALTER TABLE `cartlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `cid` char(32) NOT NULL,
  `cname` varchar(100) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('1','JavaSE'),('2','JavaEE'),('3','Javascript'),('C135EDB351094999957AFCE1A90CC388','mycategory');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitem` (
  `iid` char(32) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `oid` char(32) DEFAULT NULL,
  `bid` char(32) DEFAULT NULL,
  PRIMARY KEY (`iid`),
  KEY `oid` (`oid`),
  KEY `bid` (`bid`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `oid` char(32) NOT NULL,
  `ordertime` datetime DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `state` smallint(1) DEFAULT NULL,
  `uid` char(32) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `uid` (`uid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `uid` char(32) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `code` char(64) DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `Admin` varchar(20),
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('098A6AB92CFA469AA6A8FEE576AF70D4','董小飒','123456','123123',NULL,1,'Admin'),('1B0C01559A0D481DBEBE24A7B630C1E0','cwbcwb521','521521cwb','cwbcwb521@hotmail.com',NULL,1,'user'),('AA5100C086964603B8F9F3F54A970CF1','王五','123','5555555',NULL,1,'user'),('F3319DF8039D4F0F99E1BFDAE5742C08','yangge777','123321','164472491@qq.com',NULL,1,'Admin');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-26 20:11:24
