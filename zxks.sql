-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: zxks
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'root','root',NULL,NULL,NULL),(2,'admin','admin',NULL,NULL,NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_library`
--

DROP TABLE IF EXISTS `exam_library`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_library` (
  `id_question` int(11) NOT NULL AUTO_INCREMENT,
  `code_subject` varchar(255) NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_question`),
  KEY `code_subject` (`code_subject`),
  CONSTRAINT `exam_library_ibfk_1` FOREIGN KEY (`code_subject`) REFERENCES `exam_parameter` (`code_subject`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_library`
--

LOCK TABLES `exam_library` WRITE;
/*!40000 ALTER TABLE `exam_library` DISABLE KEYS */;
INSERT INTO `exam_library` VALUES (10,'101','下面四个算式中（）的结果最大。（a是不等于0的自然数）#A. a－56#B. a×56#C. a＋56#D. 无法确定','D'),(11,'101','周长都相等的圆、正方形和三角形，它们的面积（）。#A. 圆最大#B.正方形最大#C. 长方形最大#D. 一样大','A'),(12,'101','白菜2元一斤，菜心3元一斤，小亮有10元钱，则他可以买（）。# A. 1斤白菜4菜心#B. 2斤白菜2菜心#C. 2斤白菜3菜心#D. 4斤白菜1菜心','B'),(13,'101','下面各数，在读数时一个“零”字也不用读的是（）。# A. 620080000#B. 35009000#C. 700200600#D. 80500000','B'),(14,'101','小花家造房子，买进水泥m吨，预计每天用0.8吨，用了n天，余下多少吨。算式是（）。#A、m＋0.8n#B、m－0.8n#C、0.8m＋n#D、0.8n－m','B'),(15,'101','一件原价100元的牛仔裤，先提价10%，再降价10%，现价是（）元。#A、100#B、99#C、95#D、90','B'),(16,'101','2008年的1月份、2月份、3月份一共有（）天。#A．89#B．90#C．91#D.92','C'),(17,'101','把一个平行四边形任意分割成两个梯形，这两个梯形，这两个梯形中（）总是相等。#A．高#B.上下两底的和#C.周长#D.面积','A'),(18,'101','一个长方形长5厘米，宽3厘米，表示（）几分之几。#A．长比宽多#B．长比宽少#C．宽比长少#D．宽比长多','A');
/*!40000 ALTER TABLE `exam_library` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_paper`
--

DROP TABLE IF EXISTS `exam_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_paper` (
  `id_paper` int(11) NOT NULL AUTO_INCREMENT,
  `id_card` varchar(255) NOT NULL,
  `code_subject` varchar(255) NOT NULL,
  `data_paper` text,
  `score_exam` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_paper`),
  KEY `id_card` (`id_card`),
  KEY `code_subject` (`code_subject`),
  CONSTRAINT `exam_paper_ibfk_1` FOREIGN KEY (`id_card`) REFERENCES `user_s` (`id_card`),
  CONSTRAINT `exam_paper_ibfk_2` FOREIGN KEY (`code_subject`) REFERENCES `exam_parameter` (`code_subject`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_paper`
--

LOCK TABLES `exam_paper` WRITE;
/*!40000 ALTER TABLE `exam_paper` DISABLE KEYS */;
INSERT INTO `exam_paper` VALUES (1,'201605021819','101','[{\"idQuestion\":11,\"codeSubject\":\"101\",\"question\":\"周长都相等的圆、正方形和三角形，它们的面积（）。#A. 圆最大#B.正方形最大#C. 长方形最大#D. 一样大\",\"trueAnswer\":\"A\",\"answer\":\"A\"},{\"idQuestion\":15,\"codeSubject\":\"101\",\"question\":\"一件原价100元的牛仔裤，先提价10%，再降价10%，现价是（）元。#A、100#B、99#C、95#D、90\",\"trueAnswer\":\"B\",\"answer\":\"B\"},{\"idQuestion\":13,\"codeSubject\":\"101\",\"question\":\"下面各数，在读数时一个“零”字也不用读的是（）。# A. 620080000#B. 35009000#C. 700200600#D. 80500000\",\"trueAnswer\":\"B\",\"answer\":\"D\"},{\"idQuestion\":17,\"codeSubject\":\"101\",\"question\":\"把一个平行四边形任意分割成两个梯形，这两个梯形，这两个梯形中（）总是相等。#A．高#B.上下两底的和#C.周长#D.面积\",\"trueAnswer\":\"A\",\"answer\":\"A\"},{\"idQuestion\":12,\"codeSubject\":\"101\",\"question\":\"白菜2元一斤，菜心3元一斤，小亮有10元钱，则他可以买（）。# A. 1斤白菜4菜心#B. 2斤白菜2菜心#C. 2斤白菜3菜心#D. 4斤白菜1菜心\",\"trueAnswer\":\"B\",\"answer\":\"B\"}]',80);
/*!40000 ALTER TABLE `exam_paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_parameter`
--

DROP TABLE IF EXISTS `exam_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_parameter` (
  `code_subject` varchar(255) NOT NULL,
  `name_subject` varchar(255) DEFAULT NULL,
  `time_exam` int(11) DEFAULT '40',
  `score_total` int(11) DEFAULT '100',
  `amount_question` int(11) DEFAULT '20',
  PRIMARY KEY (`code_subject`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_parameter`
--

LOCK TABLES `exam_parameter` WRITE;
/*!40000 ALTER TABLE `exam_parameter` DISABLE KEYS */;
INSERT INTO `exam_parameter` VALUES ('101','数学',20,100,5),('102','语文',40,100,20);
/*!40000 ALTER TABLE `exam_parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_s`
--

DROP TABLE IF EXISTS `user_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_s` (
  `id_card` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_card`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_s`
--

LOCK TABLES `user_s` WRITE;
/*!40000 ALTER TABLE `user_s` DISABLE KEYS */;
INSERT INTO `user_s` VALUES ('201605021819','ruan','123456',NULL,NULL);
/*!40000 ALTER TABLE `user_s` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-03 18:59:24
