CREATE DATABASE  IF NOT EXISTS `formact` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `formact`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: formact
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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idcategoria` int NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descrizione` varchar(300) DEFAULT NULL,
  `ambitoDisciplinare` varchar(100) NOT NULL,
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'matematica 1','studio di funzioni','affine/integrativo'),(2,'programmazione 1','programmazione procedurale','informatiche'),(3,'reti di calcolatori','protocolli e topologie','informatiche'),(4,'databases','algebra relazionale','informatiche');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificazione`
--

DROP TABLE IF EXISTS `certificazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificazione` (
  `idcertificazione` int NOT NULL,
  `formatore` int NOT NULL,
  `nome` varchar(50) NOT NULL,
  `tipologia` varchar(300) NOT NULL,
  `istituto` varchar(100) NOT NULL,
  `descrizione` varchar(300) DEFAULT NULL,
  `annoInizio` date NOT NULL,
  `annoFine` date NOT NULL,
  PRIMARY KEY (`idcertificazione`),
  KEY `formatore_idx` (`formatore`),
  CONSTRAINT `formatore` FOREIGN KEY (`formatore`) REFERENCES `formatore` (`idformatore`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificazione`
--

LOCK TABLES `certificazione` WRITE;
/*!40000 ALTER TABLE `certificazione` DISABLE KEYS */;
INSERT INTO `certificazione` VALUES (1234,1,'CISCO','RETI','UNISA','CERTIFICATO CISCO CCNA','2015-02-10','2016-01-05');
/*!40000 ALTER TABLE `certificazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disponibilità`
--

DROP TABLE IF EXISTS `disponibilità`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disponibilità` (
  `id` int NOT NULL,
  `giornoSettimana` varchar(10) NOT NULL,
  `orario` time NOT NULL,
  `stato` tinyint(1) NOT NULL DEFAULT '1',
  `percorsoFormativo` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `percorso_formativo_idx` (`percorsoFormativo`),
  CONSTRAINT `percorso_formativo` FOREIGN KEY (`percorsoFormativo`) REFERENCES `percorso_formativo` (`idpercorso_formativo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disponibilità`
--

LOCK TABLES `disponibilità` WRITE;
/*!40000 ALTER TABLE `disponibilità` DISABLE KEYS */;
INSERT INTO `disponibilità` VALUES (1,'martedì','09:00:00',1,1),(2,'lunedì','09:00:00',1,2),(3,'lunedì','10:00:00',1,3),(4,'venerdì','11:00:00',1,4),(5,'lunedì','09:00:00',1,5);
/*!40000 ALTER TABLE `disponibilità` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formatore`
--

DROP TABLE IF EXISTS `formatore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formatore` (
  `idformatore` int NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `sesso` varchar(1) DEFAULT NULL,
  `dataNascita` date NOT NULL,
  `paeseOrigine` varchar(20) DEFAULT NULL,
  `codiceFiscale` varchar(16) NOT NULL,
  `contoCorrente` varchar(27) DEFAULT NULL,
  PRIMARY KEY (`idformatore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formatore`
--

LOCK TABLES `formatore` WRITE;
/*!40000 ALTER TABLE `formatore` DISABLE KEYS */;
INSERT INTO `formatore` VALUES (1,'ciaociao','ueue','','uni','M','2000-02-10','Italia','DASDADWETQG','1dasdqw5d4784');
/*!40000 ALTER TABLE `formatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giorno_settimana`
--

DROP TABLE IF EXISTS `giorno_settimana`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giorno_settimana` (
  `id` int NOT NULL,
  `giorno` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giorno_settimana`
--

LOCK TABLES `giorno_settimana` WRITE;
/*!40000 ALTER TABLE `giorno_settimana` DISABLE KEYS */;
INSERT INTO `giorno_settimana` VALUES (1,'lunedì'),(2,'martedì'),(3,'mercoledì'),(4,'giovedì'),(5,'venerdì');
/*!40000 ALTER TABLE `giorno_settimana` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interesse`
--

DROP TABLE IF EXISTS `interesse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interesse` (
  `idinteresse` int NOT NULL,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`idinteresse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interesse`
--

LOCK TABLES `interesse` WRITE;
/*!40000 ALTER TABLE `interesse` DISABLE KEYS */;
INSERT INTO `interesse` VALUES (1,'Informatica teorica'),(2,'Matematica'),(3,'Linguaggio di programmazione'),(4,'Informatica di base'),(5,'Reti 1'),(6,'Reti 2'),(7,'Pacchetto Office'),(8,'Analisi 1'),(9,'Analisi 2'),(10,'Algebra'),(11,'Geometria'),(12,'Insiemistica'),(13,'Java'),(14,'Python'),(15,'HTML'),(16,'CSS'),(17,'XML'),(18,'PHP'),(19,'Go'),(20,'UML'),(21,'Android'),(22,'Ingegneria del sfotware'),(23,'discreto'),(24,'Assembly'),(25,'Algoirtmi'),(26,'Intelligenza artificiale'),(27,'Grafica e interattività'),(28,'3d'),(29,'Interfacce'),(30,'Programmazione orientata agli oggetti');
/*!40000 ALTER TABLE `interesse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interesse_studente`
--

DROP TABLE IF EXISTS `interesse_studente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interesse_studente` (
  `studente` int NOT NULL,
  `interesse` int NOT NULL,
  PRIMARY KEY (`studente`,`interesse`),
  KEY `interesse_idx` (`interesse`),
  CONSTRAINT `interesse` FOREIGN KEY (`interesse`) REFERENCES `interesse` (`idinteresse`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studente` FOREIGN KEY (`studente`) REFERENCES `studente` (`idstudente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interesse_studente`
--

LOCK TABLES `interesse_studente` WRITE;
/*!40000 ALTER TABLE `interesse_studente` DISABLE KEYS */;
INSERT INTO `interesse_studente` VALUES (1,1);
/*!40000 ALTER TABLE `interesse_studente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iscrizione`
--

DROP TABLE IF EXISTS `iscrizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iscrizione` (
  `studente` int NOT NULL,
  `percorso_formativo` int NOT NULL,
  `giorno` varchar(45) DEFAULT NULL,
  `orario` time DEFAULT NULL,
  `metodo_pagamento` varchar(45) DEFAULT NULL,
  `data_pagamento` date DEFAULT NULL,
  PRIMARY KEY (`studente`,`percorso_formativo`),
  KEY `percorsoFormativo_idx` (`percorso_formativo`),
  CONSTRAINT `percorsoFormativo` FOREIGN KEY (`percorso_formativo`) REFERENCES `percorso_formativo` (`idpercorso_formativo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studenteIscritto` FOREIGN KEY (`studente`) REFERENCES `studente` (`idstudente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iscrizione`
--

LOCK TABLES `iscrizione` WRITE;
/*!40000 ALTER TABLE `iscrizione` DISABLE KEYS */;
INSERT INTO `iscrizione` VALUES (1,1,'martedì','09:00:00','visa','2022-01-18'),(1,2,'lunedì','09:00:00','visa','2022-02-15'),(1,3,'lunedì','10:00:00','visa','2022-01-10'),(1,4,'venerdì','11:00:00','visa','2022-02-08');
/*!40000 ALTER TABLE `iscrizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `percorso_formativo`
--

DROP TABLE IF EXISTS `percorso_formativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `percorso_formativo` (
  `idpercorso_formativo` int NOT NULL,
  `formatore` int NOT NULL,
  `nome` varchar(100) NOT NULL,
  `ambito` int NOT NULL,
  `descrizione` varchar(500) NOT NULL,
  `indiceContenuti` text NOT NULL,
  `numeroLezioni` int DEFAULT NULL,
  `costo` double NOT NULL,
  `validate` tinyint NOT NULL,
  PRIMARY KEY (`idpercorso_formativo`),
  KEY `formatore_idx` (`formatore`),
  KEY `categoria_idx` (`ambito`),
  CONSTRAINT `ambito` FOREIGN KEY (`ambito`) REFERENCES `categoria` (`idcategoria`) ON UPDATE CASCADE,
  CONSTRAINT `idFormatore` FOREIGN KEY (`formatore`) REFERENCES `formatore` (`idformatore`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `percorso_formativo`
--

LOCK TABLES `percorso_formativo` WRITE;
/*!40000 ALTER TABLE `percorso_formativo` DISABLE KEYS */;
INSERT INTO `percorso_formativo` VALUES (1,1,'prova molteplici disp',1,' Dettagli corso ',' Indice dei contenuti... ',222,32,0),(2,1,'creator service',4,' Dettagli corso ',' Indice dei contenuti... ',23,3,0),(3,1,'terzo percorso creato',2,'dett','durata lezioni : 1 ora',24,3,0),(4,1,'quarto percorso creato',1,'dertt','ddasdas',23,32,0),(5,1,'prova costo',3,' Dettagli corso ',' Indice dei contenuti... ',23,10,0),(6,1,'costo 2',1,' Dettagli corso ',' Indice dei contenuti... ',2,10,0),(7,1,'costo 3',1,' Dettagli corso ',' Indice dei contenuti... ',2,10,0),(8,1,'costo .',1,' Dettagli corso ',' Indice dei contenuti... ',23,10,0),(9,1,'costfdsa ',1,' Dettagli corso ',' Indice dei contenuti... ',23,10.5,0),(10,1,'csad',1,' Dettagli corso ',' Indice dei contenuti... ',2,10.5,0),(11,1,'desdfs',1,' Dettagli corso ',' Indice dei contenuti... ',34,10.1,0),(12,1,'qweas',1,' Dettagli corso ',' Indice dei contenuti... ',23,0.3,0),(13,1,'deasde',1,' Dettagli corso ',' Indice dei contenuti... ',34,0,0);
/*!40000 ALTER TABLE `percorso_formativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferenza_studente`
--

DROP TABLE IF EXISTS `preferenza_studente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preferenza_studente` (
  `studente` int NOT NULL,
  `disponibile` int NOT NULL,
  PRIMARY KEY (`studente`,`disponibile`),
  KEY `disp_idx` (`disponibile`),
  CONSTRAINT `disp` FOREIGN KEY (`disponibile`) REFERENCES `giorno_settimana` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idstudente` FOREIGN KEY (`studente`) REFERENCES `studente` (`idstudente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferenza_studente`
--

LOCK TABLES `preferenza_studente` WRITE;
/*!40000 ALTER TABLE `preferenza_studente` DISABLE KEYS */;
INSERT INTO `preferenza_studente` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `preferenza_studente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studente`
--

DROP TABLE IF EXISTS `studente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studente` (
  `idstudente` int NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `sesso` varchar(1) DEFAULT NULL,
  `dataNascita` date NOT NULL,
  `paeseOrigine` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idstudente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='tabella che rappresenta uno studente';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studente`
--

LOCK TABLES `studente` WRITE;
/*!40000 ALTER TABLE `studente` DISABLE KEYS */;
INSERT INTO `studente` VALUES (1,'fabio.pica10@gmail.com','Balocco.95','fabio','pica','M','1995-09-10','Italia'),(2,'fabio@peppo.it','123sdas','fabio2','peppo','M','3895-03-09','Italia'),(3,'dasd@pica.it','dsadasda','dasda','dsada','m','2022-02-02','dsad');
/*!40000 ALTER TABLE `studente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valutazione`
--

DROP TABLE IF EXISTS `valutazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valutazione` (
  `idStudente` int NOT NULL,
  `idFormatore` int NOT NULL,
  `idPercorsoFormativo` int NOT NULL,
  `descrizione` varchar(300) NOT NULL,
  `data` date NOT NULL,
  `stelle` int NOT NULL,
  PRIMARY KEY (`idStudente`,`idFormatore`,`idPercorsoFormativo`),
  KEY `formatoreValutato_idx` (`idFormatore`),
  KEY `idPercorso_idx` (`idPercorsoFormativo`),
  CONSTRAINT `formatoreValutato` FOREIGN KEY (`idFormatore`) REFERENCES `percorso_formativo` (`formatore`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idPercorso` FOREIGN KEY (`idPercorsoFormativo`) REFERENCES `percorso_formativo` (`idpercorso_formativo`) ON UPDATE CASCADE,
  CONSTRAINT `studenteValutante` FOREIGN KEY (`idStudente`) REFERENCES `studente` (`idstudente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valutazione`
--

LOCK TABLES `valutazione` WRITE;
/*!40000 ALTER TABLE `valutazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `valutazione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-24 16:13:31
