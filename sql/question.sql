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
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(256) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `tags` varchar(1024) DEFAULT NULL COMMENT '标签列表（json 数组）',
  `answer` text COMMENT '推荐答案',
  `userId` bigint NOT NULL COMMENT '创建用户 id',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='题目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'Java中的垃圾回收机制','描述Java中的垃圾回收机制是如何工作的。详细说明垃圾回收器的种类以及它们的作用。','[\"Java\", \"GC\", \"内存管理\"]','Java的垃圾回收机制主要依赖于垃圾收集器，它们会自动回收不再使用的对象所占用的内存。常见的垃圾收集器有Serial、Parallel、CMS和G1等。',1),(2,'Python列表解析','解释Python中的列表解析是什么以及如何使用。','[\"Python\", \"列表解析\", \"语法\"]','列表解析是一种简洁的构建列表的方法，它允许我们通过一个现有的列表来创建一个新的列表。例如，[expression for item in iterable]。',2),(3,'C++中的多态性','探讨C++中的多态性以及它的实现方式。','[\"C++\", \"多态性\", \"面向对象\"]','C++中的多态性允许我们使用基类指针或引用调用派生类的成员函数。这通常通过虚函数来实现。',3),(4,'项目管理的最佳实践','讨论项目管理中的一些最佳实践。','[\"项目\", \"管理\", \"最佳实践\"]','项目管理的最佳实践包括制定清晰的项目目标、合理分配资源、有效的沟通、风险管理以及持续的过程改进。',4),(5,'自动化测试的框架','介绍自动化测试中常用的框架和工具。','[\"测试\", \"自动化\", \"框架\"]','自动化测试框架如Selenium和JUnit，它们可以帮助开发者自动执行测试用例，提高测试效率和覆盖率。',5),(6,'数据库索引优化','探讨如何优化数据库索引以提高查询性能。','[\"数据库\", \"索引\", \"性能优化\"]','数据库索引优化可以通过分析查询模式来实现，创建合适的索引可以显著提高查询速度。同时，需要定期维护索引以避免性能下降。',6),(7,'人工智能在医疗领域的应用','分析人工智能技术在医疗领域的应用案例。','[\"人工智能\", \"医疗\", \"应用\"]','人工智能在医疗领域的应用包括辅助诊断、个性化治疗计划、药物研发以及患者监护等。这些技术可以帮助提高医疗服务的质量和效率。',7),(8,'Java集合框架详解','请解释Java集合框架中的各种集合类，包括List、Set和Map，并说明它们的使用场景。','[\"Java\", \"集合框架\", \"List\", \"Set\", \"Map\"]','Java集合框架提供了一系列的接口和类，用于存储和处理对象集合。List接口代表有序集合，允许重复；Set接口代表不允许重复的集合，无序；Map接口代表键值对映射，键不允许重复。',1),(9,'Java线程同步机制','描述Java中的线程同步机制，包括synchronized关键字和Lock接口的使用。','[\"Java\", \"线程\", \"同步\", \"synchronized\", \"Lock\"]','Java提供了多种线程同步机制来控制对共享资源的访问。synchronized关键字可以用来修饰方法或代码块，而Lock接口提供了更灵活的锁定机制。',1),(10,'Java异常处理','解释Java中的异常处理机制，包括try-catch-finally语句和自定义异常的使用。','[\"Java\", \"异常处理\", \"try-catch-finally\", \"自定义异常\"]','Java异常处理机制允许程序捕获和处理异常情况。try块用来捕获异常，catch块处理异常，finally块则无论是否发生异常都会执行。',1),(11,'Java反射机制','探讨Java反射机制的工作原理及其在框架开发中的应用。','[\"Java\", \"反射\", \"机制\", \"框架\"]','Java反射机制允许程序在运行时访问、检查和修改它自己的结构，如类、接口、字段和方法。这在框架开发中非常有用，可以提高代码的灵活性和可扩展性。',1),(12,'Python装饰器的使用','解释Python中的装饰器是什么，以及如何在函数和类上使用装饰器。','[\"Python\", \"装饰器\", \"函数\", \"类\"]','Python装饰器是一种设计模式，用于在不修改函数内容的情况下扩展函数功能。装饰器本质上是一个返回函数的函数，可以应用于函数和类。',2),(13,'Python生成器和迭代器','描述Python中的生成器和迭代器的区别和联系，以及它们的使用场景。','[\"Python\", \"生成器\", \"迭代器\"]','Python中的迭代器是一个实现了迭代器协议的对象，而生成器是一种特殊的迭代器，使用yield关键字创建。生成器可以节省内存，适用于大数据量的迭代处理。',2),(14,'Python面向对象编程','解释Python中的面向对象编程概念，包括类和对象、继承、封装和多态。','[\"Python\", \"面向对象\", \"类\", \"继承\", \"封装\", \"多态\"]','Python支持面向对象编程，允许定义类和对象。类可以继承其他类，对象之间可以进行信息封装和多态行为。',2),(15,'Python错误和异常处理','讨论Python中的错误和异常处理机制，包括try-except-finally语句的使用。','[\"Python\", \"错误\", \"异常处理\", \"try-except-finally\"]','Python异常处理机制允许程序捕获和处理异常情况。try块用来捕获异常，except块处理异常，finally块则无论是否发生异常都会执行。',2);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-10 16:01:06
