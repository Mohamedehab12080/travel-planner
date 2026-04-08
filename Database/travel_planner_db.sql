CREATE DATABASE  IF NOT EXISTS `travel_planner_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `travel_planner_db`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: travel_planner_db
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('1','mohamed.ehab','../../../modules/user-module/_liquibase/changes/changeset-2026-04-03.xml','2026-04-08 22:53:25',1,'EXECUTED','9:e4c27a8bf913621b5f32c412ba371a61','createTable tableName=tp_user; addForeignKeyConstraint baseTableName=tp_user, constraintName=fk_user_created_by, referencedTableName=tp_user; addForeignKeyConstraint baseTableName=tp_user, constraintName=fk_user_last_modified_by_id, referencedTabl...','',NULL,'4.27.0',NULL,NULL,'5681605288'),('1','mohamed.ehab','../../../modules/user-module/_liquibase/changes/changeset-2026-04-04.xml','2026-04-08 22:53:25',2,'EXECUTED','9:990b3cac2b96c45957bcbb0c0c0d2f18','createTable tableName=tp_token; addForeignKeyConstraint baseTableName=tp_token, constraintName=fk_user_token, referencedTableName=tp_user','',NULL,'4.27.0',NULL,NULL,'5681605288'),('1','mohamed.ehab','../../../modules/destination-module/_liquibase/changes/changeset-2026-04-03.xml','2026-04-08 22:53:25',3,'EXECUTED','9:69b84b096c8098acdb8a5ca6af61280e','createTable tableName=tp_destination; addForeignKeyConstraint baseTableName=tp_destination, constraintName=fk_destination_created_by, referencedTableName=tp_user; addForeignKeyConstraint baseTableName=tp_destination, constraintName=fk_destination_...','',NULL,'4.27.0',NULL,NULL,'5681605288'),('1','mohamed.ehab','../../../modules/wishlist/wishlist-module/_liquibase/changes/changeset-2026-04-03.xml','2026-04-08 22:53:26',4,'EXECUTED','9:dab964e3c4bdb9bac8de3ad23e0b153c','createTable tableName=tp_wishlist','',NULL,'4.27.0',NULL,NULL,'5681605288'),('2','mohamed.ehab','../../../modules/wishlist/wishlist-module/_liquibase/changes/changeset-2026-04-03.xml','2026-04-08 22:53:26',5,'EXECUTED','9:185f6a9ddb9704fed1f70968d27132c3','addForeignKeyConstraint baseTableName=tp_wishlist, constraintName=fk_wishlist_created_by, referencedTableName=tp_user; addForeignKeyConstraint baseTableName=tp_wishlist, constraintName=fk_wishlist_last_modified_by, referencedTableName=tp_user','',NULL,'4.27.0',NULL,NULL,'5681605288'),('3','mohamed.ehab','../../../modules/wishlist/wishlist-module/_liquibase/changes/changeset-2026-04-03.xml','2026-04-08 22:53:26',6,'EXECUTED','9:5b14200852a551620352e030f78bd935','addForeignKeyConstraint baseTableName=tp_wishlist, constraintName=fk_wishlist_user, referencedTableName=tp_user; addForeignKeyConstraint baseTableName=tp_wishlist, constraintName=fk_wishlist_destination, referencedTableName=tp_destination','',NULL,'4.27.0',NULL,NULL,'5681605288');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int NOT NULL,
  `LOCKED` tinyint NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,0,NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp_destination`
--

DROP TABLE IF EXISTS `tp_destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tp_destination` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `country_code` varchar(3) NOT NULL,
  `country_name` varchar(150) NOT NULL,
  `capital` varchar(150) DEFAULT NULL,
  `region` varchar(100) DEFAULT NULL,
  `sub_region` varchar(100) DEFAULT NULL,
  `population` bigint DEFAULT NULL,
  `currency_name` varchar(100) DEFAULT NULL,
  `currency_code` varchar(3) DEFAULT NULL,
  `flag_url` varchar(500) DEFAULT NULL,
  `languages` text,
  `time_zones` text,
  `status` varchar(20) NOT NULL DEFAULT 'PENDING',
  `note` text,
  `created_by_id` bigint NOT NULL,
  `last_modified_by_id` bigint DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_on` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `country_code` (`country_code`),
  KEY `fk_destination_created_by` (`created_by_id`),
  KEY `fk_destination_last_modified_by` (`last_modified_by_id`),
  CONSTRAINT `fk_destination_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `tp_user` (`id`),
  CONSTRAINT `fk_destination_last_modified_by` FOREIGN KEY (`last_modified_by_id`) REFERENCES `tp_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_destination`
--

LOCK TABLES `tp_destination` WRITE;
/*!40000 ALTER TABLE `tp_destination` DISABLE KEYS */;
/*!40000 ALTER TABLE `tp_destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp_token`
--

DROP TABLE IF EXISTS `tp_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tp_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `token` varchar(500) DEFAULT NULL,
  `token_type` varchar(100) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `expiry_date` timestamp NULL DEFAULT NULL,
  `is_used` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_user_token` (`user_id`),
  CONSTRAINT `fk_user_token` FOREIGN KEY (`user_id`) REFERENCES `tp_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_token`
--

LOCK TABLES `tp_token` WRITE;
/*!40000 ALTER TABLE `tp_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `tp_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp_user`
--

DROP TABLE IF EXISTS `tp_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tp_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `mobile_number` varchar(20) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `is_active` tinyint DEFAULT NULL,
  `created_by_id` bigint DEFAULT NULL,
  `last_modified_by_id` bigint DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_on` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_created_by` (`created_by_id`),
  KEY `fk_user_last_modified_by_id` (`last_modified_by_id`),
  CONSTRAINT `fk_user_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `tp_user` (`id`),
  CONSTRAINT `fk_user_last_modified_by_id` FOREIGN KEY (`last_modified_by_id`) REFERENCES `tp_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_user`
--

LOCK TABLES `tp_user` WRITE;
/*!40000 ALTER TABLE `tp_user` DISABLE KEYS */;
INSERT INTO `tp_user` VALUES (1,'mohamedehab12080@gmail.com','$2a$10$5uoAqECTM2IYXBNuIyT.mePQ/ysdIjQUPaIXch4Iu7yA8IM1.5R2S','Mohamed Ehab','+201234567890','ROLE_SUPER_ADMIN',1,NULL,NULL,'2026-04-08 21:12:50',NULL),(2,'m.ehab.rabea@gmail.com','$2a$10$ihzJgrOxWQ7r2GFGeXVTIu5rO2DhC9Nh0Gea7eLKnuQAIlMhfeQj2','Mohamed Ehab Rabea','+201234567891','ROLE_ADMIN',1,NULL,NULL,'2026-04-08 21:12:50',NULL),(3,'demo@travelplanner.com','$2a$10$fNAwq04Ts2wkjymG6Oa8MOtZx5PifaLSwUqnRfztMzsybvDbQOew.','Demo User','+201234567892','ROLE_USER',1,NULL,NULL,'2026-04-08 21:12:50',NULL);
/*!40000 ALTER TABLE `tp_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp_wishlist`
--

DROP TABLE IF EXISTS `tp_wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tp_wishlist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `destination_id` bigint NOT NULL,
  `status` varchar(20) NOT NULL,
  `priority` int DEFAULT NULL,
  `notes` text,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_on` timestamp NULL DEFAULT NULL,
  `created_by_id` bigint NOT NULL,
  `last_modified_by_id` bigint DEFAULT NULL,
  `visited_date` date DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `review` text,
  PRIMARY KEY (`id`),
  KEY `fk_wishlist_created_by` (`created_by_id`),
  KEY `fk_wishlist_last_modified_by` (`last_modified_by_id`),
  KEY `fk_wishlist_user` (`user_id`),
  KEY `fk_wishlist_destination` (`destination_id`),
  CONSTRAINT `fk_wishlist_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `tp_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_wishlist_destination` FOREIGN KEY (`destination_id`) REFERENCES `tp_destination` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_wishlist_last_modified_by` FOREIGN KEY (`last_modified_by_id`) REFERENCES `tp_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_wishlist_user` FOREIGN KEY (`user_id`) REFERENCES `tp_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_wishlist`
--

LOCK TABLES `tp_wishlist` WRITE;
/*!40000 ALTER TABLE `tp_wishlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `tp_wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-08 23:18:58
