DROP DATABASE IF EXISTS formact;

CREATE DATABASE `formact` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

use formact;

CREATE TABLE `categoria` (
  `idcategoria` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `descrizione` varchar(300) DEFAULT NULL,
  `ambitoDisciplinare` varchar(100) NOT NULL,
  PRIMARY KEY (`idcategoria`,`nome`,`ambitoDisciplinare`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `formatore` (
  `idformatore` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `sesso` varchar(1) DEFAULT NULL,
  `dataNascita` date NOT NULL,
  `paeseOrigine` varchar(20) DEFAULT NULL,
  `codiceFiscale` varchar(16) NOT NULL,
  `contoCorrente` varchar(28) DEFAULT NULL,
  PRIMARY KEY (`idformatore`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `percorso_formativo` (
  `idpercorso_formativo` int NOT NULL AUTO_INCREMENT,
  `formatore` int NOT NULL,
  `nome` varchar(100) NOT NULL,
  `ambito` int NOT NULL,
  `descrizione` varchar(500) NOT NULL,
  `indiceContenuti` text NOT NULL,
  `numeroLezioni` int DEFAULT NULL,
  `costo` double NOT NULL,
  `validate` tinyint NOT NULL,
  PRIMARY KEY (`idpercorso_formativo`),
  KEY `ambito_idx` (`ambito`),
  KEY `idFormatore_idx` (`formatore`),
  CONSTRAINT `ambito` FOREIGN KEY (`ambito`) REFERENCES `categoria` (`idcategoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idFormatore` FOREIGN KEY (`formatore`) REFERENCES `formatore` (`idformatore`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `disponibilita` (
  `id` int NOT NULL AUTO_INCREMENT,
  `giornoSettimana` varchar(10) NOT NULL,
  `orario` varchar(10) NOT NULL,
  `stato` tinyint(1) NOT NULL DEFAULT '1',
  `percorsoFormativo` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `percorsoFormativo_idx` (`percorsoFormativo`),
  CONSTRAINT `percorsoFormativo` FOREIGN KEY (`percorsoFormativo`) REFERENCES `percorso_formativo` (`idpercorso_formativo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `studente` (
  `idstudente` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `sesso` varchar(1) DEFAULT NULL,
  `dataNascita` date NOT NULL,
  `paeseOrigine` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idstudente`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='tabella che rappresenta uno studente';

CREATE TABLE `interesse` (
  `idinteresse` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`idinteresse`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `interesse_studente` (
  `studente` int NOT NULL,
  `interesse` int NOT NULL,
  PRIMARY KEY (`studente`,`interesse`),
  KEY `interesse_idx` (`interesse`),
  CONSTRAINT `interesse` FOREIGN KEY (`interesse`) REFERENCES `interesse` (`idinteresse`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studente` FOREIGN KEY (`studente`) REFERENCES `studente` (`idstudente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `iscrizione` (
  `studente` int NOT NULL,
  `percorso_formativo` int NOT NULL,
  `giorno` varchar(45)  NOT NULL,
  `orario` varchar(5)  NOT NULL,
  `metodo_pagamento` varchar(45) DEFAULT NULL,
  `data_pagamento` date DEFAULT NULL,
  PRIMARY KEY (`studente`, `percorso_formativo`,`giorno`,`orario`),
  KEY `percorsoFormativo_idx` (`percorso_formativo`),
  CONSTRAINT `corso` FOREIGN KEY (`percorso_formativo`) REFERENCES `percorso_formativo` (`idpercorso_formativo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studenteIscritto` FOREIGN KEY (`studente`) REFERENCES `studente` (`idstudente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `preferenza_studente` (
  `studente` int NOT NULL,
  `disponibile` varchar(10) NOT NULL,
  PRIMARY KEY (`studente`, `disponibile`),
  CONSTRAINT `studentePref` FOREIGN KEY (`studente`) REFERENCES `studente` (`idstudente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  CONSTRAINT `formatoreValutato` FOREIGN KEY (`idFormatore`) REFERENCES `formatore` (`idformatore`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idPercorso` FOREIGN KEY (`idPercorsoFormativo`) REFERENCES `percorso_formativo` (`idpercorso_formativo`) ON UPDATE CASCADE,
  CONSTRAINT `studenteValutante` FOREIGN KEY (`idStudente`) REFERENCES `studente` (`idstudente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use formact;
ALTER TABLE categoria AUTO_INCREMENT = 1;
ALTER TABLE disponibilita AUTO_INCREMENT = 1;
ALTER TABLE formatore AUTO_INCREMENT = 1;
ALTER TABLE studente AUTO_INCREMENT = 1;
ALTER TABLE percorso_formativo AUTO_INCREMENT = 1;
ALTER TABLE interesse AUTO_INCREMENT = 1;
