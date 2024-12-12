-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mianshiya
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `question_bank_question`
--

DROP TABLE IF EXISTS `question_bank_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_bank_question` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `questionBankId` bigint NOT NULL COMMENT '题库 id',
  `questionId` bigint NOT NULL COMMENT '题目 id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `questionBankId` (`questionBankId`,`questionId`),
  KEY `questionId` (`questionId`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='题库题目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_bank_question`
--

LOCK TABLES `question_bank_question` WRITE;
/*!40000 ALTER TABLE `question_bank_question` DISABLE KEYS */;
INSERT INTO `question_bank_question` VALUES (16,1,1),(18,1,8),(19,1,9),(20,1,10),(21,1,11),(17,1,16),(22,2,2),(24,2,12),(25,2,13),(26,2,14),(27,2,15),(23,2,17),(28,3,35),(29,3,36),(30,3,37),(31,3,38),(32,3,39),(33,3,40),(34,4,4),(35,4,19),(36,4,41),(37,4,42),(38,4,43),(39,4,44),(40,4,45),(41,4,46),(42,5,5),(43,5,20),(44,5,47),(45,5,48),(46,5,49),(47,5,50),(48,5,51),(49,5,52),(50,6,6),(51,6,23),(52,6,24),(53,6,25),(54,6,26),(55,6,27),(56,6,28),(57,7,7),(58,7,22),(59,7,29),(60,7,30),(61,7,31),(62,7,32),(63,7,33),(64,7,34),(65,8,53),(66,8,54),(67,8,55),(68,8,56),(69,8,57),(70,8,58),(71,9,59),(72,9,60),(73,9,61),(74,9,62),(75,9,63),(76,9,64);
/*!40000 ALTER TABLE `question_bank_question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-12 14:35:16
