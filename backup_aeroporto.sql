CREATE DATABASE  IF NOT EXISTS `aeroporto` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `aeroporto`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: aeroporto
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `aeroportos`
--

DROP TABLE IF EXISTS `aeroportos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aeroportos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `abreviacao` varchar(10) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `data_criacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_modificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aeroportos`
--

LOCK TABLES `aeroportos` WRITE;
/*!40000 ALTER TABLE `aeroportos` DISABLE KEYS */;
INSERT INTO `aeroportos` VALUES (1,'Aeroporto de Congonhas','CGH','São Paulo','2025-12-03 18:02:01','2025-12-03 18:02:01'),(2,'Aeroporto Santos Dumont','SDU','Rio de Janeiro','2025-12-03 18:02:01','2025-12-03 18:02:01'),(3,'Aeroporto de Guarulhos','GRU','São Paulo','2025-12-03 18:02:01','2025-12-03 18:02:01'),(4,'Aeroporto de Uberaba','UBA','Uberaba','2025-12-03 22:46:29','2025-12-03 22:46:29');
/*!40000 ALTER TABLE `aeroportos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boarding_pass`
--

DROP TABLE IF EXISTS `boarding_pass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boarding_pass` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint DEFAULT NULL,
  `passageiro_id` bigint DEFAULT NULL,
  `voo_id` bigint DEFAULT NULL,
  `assento_id` bigint DEFAULT NULL,
  `data_criacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_modificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `ticket_id` (`ticket_id`),
  KEY `passageiro_id` (`passageiro_id`),
  KEY `voo_id` (`voo_id`),
  KEY `assento_id` (`assento_id`),
  CONSTRAINT `boarding_pass_ibfk_1` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`) ON DELETE CASCADE,
  CONSTRAINT `boarding_pass_ibfk_2` FOREIGN KEY (`passageiro_id`) REFERENCES `passageiros` (`id`) ON DELETE CASCADE,
  CONSTRAINT `boarding_pass_ibfk_3` FOREIGN KEY (`voo_id`) REFERENCES `voos` (`id`) ON DELETE CASCADE,
  CONSTRAINT `boarding_pass_ibfk_4` FOREIGN KEY (`assento_id`) REFERENCES `voo_assentos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boarding_pass`
--

LOCK TABLES `boarding_pass` WRITE;
/*!40000 ALTER TABLE `boarding_pass` DISABLE KEYS */;
INSERT INTO `boarding_pass` VALUES (1,3,5,7,3,'2025-12-03 23:06:47','2025-12-03 23:06:47');
/*!40000 ALTER TABLE `boarding_pass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkins`
--

DROP TABLE IF EXISTS `checkins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkins` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint DEFAULT NULL,
  `documento` varchar(50) DEFAULT NULL,
  `data_criacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_modificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `ticket_id` (`ticket_id`),
  CONSTRAINT `checkins_ibfk_1` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkins`
--

LOCK TABLES `checkins` WRITE;
/*!40000 ALTER TABLE `checkins` DISABLE KEYS */;
INSERT INTO `checkins` VALUES (1,3,'123456','2025-12-03 23:06:46','2025-12-03 23:06:46');
/*!40000 ALTER TABLE `checkins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companhias_aereas`
--

DROP TABLE IF EXISTS `companhias_aereas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companhias_aereas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `abreviacao` varchar(10) DEFAULT NULL,
  `data_criacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_modificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companhias_aereas`
--

LOCK TABLES `companhias_aereas` WRITE;
/*!40000 ALTER TABLE `companhias_aereas` DISABLE KEYS */;
INSERT INTO `companhias_aereas` VALUES (1,'Azul','A1','2025-12-03 18:02:01','2025-12-03 18:02:01'),(2,'Gol','G2','2025-12-03 18:02:01','2025-12-03 18:02:01');
/*!40000 ALTER TABLE `companhias_aereas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `despacho_bagagem`
--

DROP TABLE IF EXISTS `despacho_bagagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `despacho_bagagem` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint DEFAULT NULL,
  `documento` varchar(50) DEFAULT NULL,
  `data_criacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_modificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `ticket_id` (`ticket_id`),
  CONSTRAINT `despacho_bagagem_ibfk_1` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `despacho_bagagem`
--

LOCK TABLES `despacho_bagagem` WRITE;
/*!40000 ALTER TABLE `despacho_bagagem` DISABLE KEYS */;
INSERT INTO `despacho_bagagem` VALUES (1,1,'111111','2025-12-03 22:52:23','2025-12-03 22:52:23');
/*!40000 ALTER TABLE `despacho_bagagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passageiros`
--

DROP TABLE IF EXISTS `passageiros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passageiros` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `nascimento` date DEFAULT NULL,
  `documento` varchar(50) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `data_criacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_modificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `documento` (`documento`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passageiros`
--

LOCK TABLES `passageiros` WRITE;
/*!40000 ALTER TABLE `passageiros` DISABLE KEYS */;
INSERT INTO `passageiros` VALUES (1,'Rai','2000-01-15','111111','rai','123','2025-12-03 18:02:01','2025-12-03 22:44:58'),(2,'Maria','1995-05-20','222222','maria','123','2025-12-03 18:02:01','2025-12-03 18:02:01'),(4,'Maria Luiza','1991-01-01','12345','maria','senha123','2025-12-03 22:39:00','2025-12-03 22:39:00'),(5,'Jose Costa','1992-01-01','123456','jose','senha123','2025-12-03 22:39:00','2025-12-03 22:39:00');
/*!40000 ALTER TABLE `passageiros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `valor` decimal(10,2) DEFAULT '0.00',
  `voo_id` bigint DEFAULT NULL,
  `passageiro_id` bigint DEFAULT NULL,
  `codigo` bigint DEFAULT NULL,
  `data_criacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_modificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo` (`codigo`),
  KEY `voo_id` (`voo_id`),
  KEY `passageiro_id` (`passageiro_id`),
  CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`voo_id`) REFERENCES `voos` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`passageiro_id`) REFERENCES `passageiros` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (1,500.00,3,1,989609,'2025-12-03 22:49:49','2025-12-03 22:49:49'),(2,500.00,5,2,408053,'2025-12-03 22:56:48','2025-12-03 22:56:48'),(3,500.00,7,5,973689,'2025-12-03 23:06:13','2025-12-03 23:06:13');
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voo_assentos`
--

DROP TABLE IF EXISTS `voo_assentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voo_assentos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `voo_id` bigint DEFAULT NULL,
  `cod_assento` bigint DEFAULT NULL,
  `passageiro_id` bigint DEFAULT NULL,
  `data_criacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_modificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `voo_id` (`voo_id`),
  KEY `passageiro_id` (`passageiro_id`),
  CONSTRAINT `voo_assentos_ibfk_1` FOREIGN KEY (`voo_id`) REFERENCES `voos` (`id`) ON DELETE CASCADE,
  CONSTRAINT `voo_assentos_ibfk_2` FOREIGN KEY (`passageiro_id`) REFERENCES `passageiros` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voo_assentos`
--

LOCK TABLES `voo_assentos` WRITE;
/*!40000 ALTER TABLE `voo_assentos` DISABLE KEYS */;
INSERT INTO `voo_assentos` VALUES (1,3,16,1,'2025-12-03 22:49:49','2025-12-03 22:49:49'),(2,5,59,2,'2025-12-03 22:56:48','2025-12-03 22:56:48'),(3,7,97,5,'2025-12-03 23:06:13','2025-12-03 23:06:13');
/*!40000 ALTER TABLE `voo_assentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voos`
--

DROP TABLE IF EXISTS `voos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `origem` varchar(100) NOT NULL,
  `destino` varchar(100) NOT NULL,
  `data_voo` datetime DEFAULT NULL,
  `duracao` time DEFAULT NULL,
  `cia` varchar(50) DEFAULT NULL,
  `capacidade` bigint DEFAULT '0',
  `status` varchar(50) DEFAULT 'Programado',
  `data_criacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_modificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voos`
--

LOCK TABLES `voos` WRITE;
/*!40000 ALTER TABLE `voos` DISABLE KEYS */;
INSERT INTO `voos` VALUES (1,'CGH','SDU','2025-10-26 14:00:00','00:55:00','A1',5,'Decolado','2025-12-03 18:02:01','2025-12-03 18:02:01'),(2,'CGH','SDU','2025-10-30 23:30:00','01:00:00','G2',5,'Programado','2025-12-03 18:02:01','2025-12-03 18:02:01'),(3,'SDU','CGH','2025-10-31 10:00:00','00:50:00','A1',4,'Programado','2025-12-03 18:02:01','2025-12-03 22:49:49'),(4,'GRU','SDU','2025-11-15 11:00:00','01:10:00','G2',5,'Programado','2025-12-03 18:02:01','2025-12-03 18:02:01'),(5,'SDU','GRU','2025-12-04 23:30:00','10:00:00','A1',9,'Programado','2025-12-03 22:56:07','2025-12-03 22:56:48'),(7,'SDU','GRU','2025-12-04 23:00:00','05:00:00','A1',10,'Programado','2025-12-03 23:05:24','2025-12-03 23:06:13');
/*!40000 ALTER TABLE `voos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-04  0:19:49
