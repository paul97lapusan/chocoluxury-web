-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pasteleria
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulos` (
  `cod_art` int NOT NULL AUTO_INCREMENT,
  `nombre_art` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `precio_venta` decimal(9,2) NOT NULL,
  `stock` int NOT NULL,
  `imagen` varchar(100) NOT NULL,
  PRIMARY KEY (`cod_art`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulos`
--

LOCK TABLES `articulos` WRITE;
/*!40000 ALTER TABLE `articulos` DISABLE KEYS */;
INSERT INTO `articulos` VALUES (1,'Barrita','Barrita de chocolate rellena de caramelo',2.40,100,'images/chocolate2.png'),(2,'Chocolatina','Chocolatina',3.60,50,'images/chocolate3.png'),(3,'Bombón','Bombón de chocolate relleno de whiskey',1.90,65,'images/chocolate1.png');
/*!40000 ALTER TABLE `articulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `cod_cli` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `clave` varchar(20) NOT NULL,
  PRIMARY KEY (`cod_cli`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Javier Dominguez','623415789','Calle Asturias 24','javi@gmail.com','holas555'),(2,'Rafael Nadal',NULL,'Calle Lazaro 41','nadal@gmail.com','holas555');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `cod_emp` int NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) NOT NULL,
  `email` varchar(50) NOT NULL,
  `clave` varchar(20) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `perfil` int NOT NULL,
  PRIMARY KEY (`cod_emp`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_emp_per_emp` (`perfil`),
  CONSTRAINT `fk_emp_per_emp` FOREIGN KEY (`perfil`) REFERENCES `perfiles_empleado` (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,'X7250916M','paul@gmail.com','holas666','Paul Lapusan','687776554',1),(2,'51042214Q','maria@gmail.com','holas666','Maria Ioana','667412114',2),(3,'39302850J','lucas@gmail.com','holas666','Lucas Lapusan',NULL,3);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados_pedido`
--

DROP TABLE IF EXISTS `estados_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados_pedido` (
  `id_estado` int NOT NULL,
  `estado` varchar(20) NOT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados_pedido`
--

LOCK TABLES `estados_pedido` WRITE;
/*!40000 ALTER TABLE `estados_pedido` DISABLE KEYS */;
INSERT INTO `estados_pedido` VALUES (1,'Preparando'),(2,'En reparto'),(3,'Listo para recoger'),(4,'Entregado');
/*!40000 ALTER TABLE `estados_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial_ventas`
--

DROP TABLE IF EXISTS `historial_ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial_ventas` (
  `cod_art` int NOT NULL,
  `mes` int NOT NULL,
  `cantidad_acumulada` decimal(9,2) NOT NULL,
  PRIMARY KEY (`cod_art`),
  CONSTRAINT `fk_his_ven` FOREIGN KEY (`cod_art`) REFERENCES `articulos` (`cod_art`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial_ventas`
--

LOCK TABLES `historial_ventas` WRITE;
/*!40000 ALTER TABLE `historial_ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `historial_ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lin_ped`
--

DROP TABLE IF EXISTS `lin_ped`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lin_ped` (
  `cod_ped` int NOT NULL,
  `cod_art` int NOT NULL,
  `cantidad_pedida` int NOT NULL,
  PRIMARY KEY (`cod_ped`,`cod_art`),
  KEY `fk_lin_ped_art` (`cod_art`),
  CONSTRAINT `fk_lin_ped_art` FOREIGN KEY (`cod_art`) REFERENCES `articulos` (`cod_art`),
  CONSTRAINT `fk_lin_ped_ped` FOREIGN KEY (`cod_ped`) REFERENCES `pedidos` (`cod_ped`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lin_ped`
--

LOCK TABLES `lin_ped` WRITE;
/*!40000 ALTER TABLE `lin_ped` DISABLE KEYS */;
INSERT INTO `lin_ped` VALUES (1,1,2),(1,3,2),(5,2,2),(5,3,1);
/*!40000 ALTER TABLE `lin_ped` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `cod_ped` int NOT NULL AUTO_INCREMENT,
  `fecha_ped` date NOT NULL,
  `cod_cli` int NOT NULL,
  `estado` int NOT NULL,
  `domicilio` int NOT NULL,
  `importe` decimal(9,2) NOT NULL,
  PRIMARY KEY (`cod_ped`),
  KEY `fk_ped_cli` (`cod_cli`),
  KEY `fk_ped_est_ped` (`estado`),
  CONSTRAINT `fk_ped_cli` FOREIGN KEY (`cod_cli`) REFERENCES `clientes` (`cod_cli`),
  CONSTRAINT `fk_ped_est_ped` FOREIGN KEY (`estado`) REFERENCES `estados_pedido` (`id_estado`),
  CONSTRAINT `check_pedido_domicilio` CHECK (((`domicilio` = 0) or (`domicilio` = 1)))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,'2021-06-13',1,3,0,27.40),(2,'2021-06-13',1,4,1,30.00),(3,'2021-06-13',2,4,0,59.12),(4,'2021-06-13',2,4,0,5.60),(5,'2021-06-14',1,2,1,11.10);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles_empleado`
--

DROP TABLE IF EXISTS `perfiles_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfiles_empleado` (
  `id_perfil` int NOT NULL,
  `perfil` varchar(15) NOT NULL,
  PRIMARY KEY (`id_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles_empleado`
--

LOCK TABLES `perfiles_empleado` WRITE;
/*!40000 ALTER TABLE `perfiles_empleado` DISABLE KEYS */;
INSERT INTO `perfiles_empleado` VALUES (1,'Administrador'),(2,'Repostero'),(3,'Repartidor');
/*!40000 ALTER TABLE `perfiles_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preparacion_pedidos`
--

DROP TABLE IF EXISTS `preparacion_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preparacion_pedidos` (
  `cod_ped` int NOT NULL,
  `cod_emp` int NOT NULL,
  `fecha_listo` date DEFAULT NULL,
  PRIMARY KEY (`cod_ped`,`cod_emp`),
  KEY `fk_prep_ped_emp` (`cod_emp`),
  CONSTRAINT `fk_prep_ped_emp` FOREIGN KEY (`cod_emp`) REFERENCES `empleados` (`cod_emp`),
  CONSTRAINT `fk_prep_ped_ped` FOREIGN KEY (`cod_ped`) REFERENCES `pedidos` (`cod_ped`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preparacion_pedidos`
--

LOCK TABLES `preparacion_pedidos` WRITE;
/*!40000 ALTER TABLE `preparacion_pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `preparacion_pedidos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-14 16:07:12
