-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: serieashop
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `carrello`
--

DROP TABLE IF EXISTS `carrello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrello` (
  `idCarrello` int NOT NULL,
  `username` varchar(25) DEFAULT NULL,
  `totale` double DEFAULT NULL,
  PRIMARY KEY (`idCarrello`),
  KEY `username` (`username`),
  CONSTRAINT `carrello_ibfk_1` FOREIGN KEY (`username`) REFERENCES `utente` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrello`
--

LOCK TABLES `carrello` WRITE;
/*!40000 ALTER TABLE `carrello` DISABLE KEYS */;
INSERT INTO `carrello` VALUES (2,'utente2',NULL),(3,'utente3',NULL),(4,'utente4',NULL),(5,'utente5',NULL),(6,'utente6',NULL),(7,'utente7',NULL),(8,'utente8',NULL),(9,'utente9',NULL),(10,'utente10',NULL);
/*!40000 ALTER TABLE `carrello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genera`
--

DROP TABLE IF EXISTS `genera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genera` (
  `id` int NOT NULL,
  `username` varchar(25) DEFAULT NULL,
  `idOrdine` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `idOrdine` (`idOrdine`),
  CONSTRAINT `genera_ibfk_1` FOREIGN KEY (`username`) REFERENCES `utente` (`Username`),
  CONSTRAINT `genera_ibfk_2` FOREIGN KEY (`idOrdine`) REFERENCES `ordine` (`idOrdine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genera`
--

LOCK TABLES `genera` WRITE;
/*!40000 ALTER TABLE `genera` DISABLE KEYS */;
INSERT INTO `genera` VALUES (2,'utente2',2),(3,'utente3',3),(4,'utente4',4),(5,'utente5',5),(6,'utente6',6),(7,'utente7',7),(8,'utente8',8),(9,'utente9',9),(10,'utente10',10);
/*!40000 ALTER TABLE `genera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `idOrdine` int NOT NULL,
  `PrezzoTotale` double DEFAULT NULL,
  `dataOrdine` date DEFAULT NULL,
  `metodoDiPagamento` varchar(45) DEFAULT NULL,
  `indirizzoSpedizione` varchar(60) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `idCarrello` int DEFAULT NULL,
  PRIMARY KEY (`idOrdine`),
  KEY `username` (`username`),
  KEY `idCarrello` (`idCarrello`),
  CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`username`) REFERENCES `utente` (`Username`),
  CONSTRAINT `ordine_ibfk_2` FOREIGN KEY (`idCarrello`) REFERENCES `carrello` (`idCarrello`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (1,95,NULL,'Carta di credito','Via Roma 123 00100','utente2',2),(2,45,NULL,'PayPal','Via Milano 456 00200','utente2',2),(3,75,NULL,'Carta di credito Via Napoli 789','00300','utente2',2),(4,60,NULL,'PayPal','Via Firenze 01 00400','utente2',2),(5,120,NULL,'Carta di credito','Via Torino 202 00500','utente2',2),(6,30,NULL,'PayPal','Via Bologna 303 00600','utente2',2),(7,50,NULL,'Carta di credito','Via Venezia 404 00700','utente2',2),(8,90,NULL,'PayPal','Via Verona 505 00800','utente2',2),(9,110,NULL,'Carta di credito','Via Pisa 606 00900','utente2',2),(10,70,NULL,'PayPal','Via Genova 707 01000','utente2',2);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotticarrello`
--

DROP TABLE IF EXISTS `prodotticarrello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotticarrello` (
  `idProdCarr` int NOT NULL,
  `quantita` int DEFAULT NULL,
  `idCarrello` int DEFAULT NULL,
  `idProdotto` int DEFAULT NULL,
  PRIMARY KEY (`idProdCarr`),
  KEY `idProdotto` (`idProdotto`),
  KEY `idCarrello` (`idCarrello`),
  CONSTRAINT `prodotticarrello_ibfk_1` FOREIGN KEY (`idProdotto`) REFERENCES `prodotto` (`idProdotto`),
  CONSTRAINT `prodotticarrello_ibfk_2` FOREIGN KEY (`idCarrello`) REFERENCES `carrello` (`idCarrello`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotticarrello`
--

LOCK TABLES `prodotticarrello` WRITE;
/*!40000 ALTER TABLE `prodotticarrello` DISABLE KEYS */;
/*!40000 ALTER TABLE `prodotticarrello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `idProdotto` int NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(3000) DEFAULT NULL,
  `nomeProdotto` varchar(45) DEFAULT NULL,
  `quantita` int DEFAULT NULL,
  `prezzo` varchar(45) DEFAULT NULL,
  `idSquadra` varchar(25) DEFAULT NULL,
  `categoria` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`idProdotto`),
  KEY `idSquadra` (`idSquadra`),
  CONSTRAINT `prodotto_ibfk_1` FOREIGN KEY (`idSquadra`) REFERENCES `squadra` (`idSquadra`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (1,'Joma e Atalanta sono orgogliosi di presentare le nuove maglie gioco con le quali i giocatori affronteranno la prossima stagione 2023/2024.\r\n\r\nLa prima maglia si caratterizza con l’iconico disegno a righe neroazzurre, in cui trova spazio un moderno sublimato geometrico evidente nelle righe azzurre.\r\n\r\nI disegni danno così origine a un effetto ottico nuovo e innovativo completamente diverso da quanto realizzato nelle passate stagioni. Il modello scelto per la maglia home 23/24 si contraddistingue per un elegante collo a polo, con costine neroazzurre anche nei polsini, per una vestibilità ancor più confortevole.','Maglia Home 2023/2024',55,'89.0','Atalanta','maglia'),(2,'Pantaloncini da uomo della prima divisa dell\'Atalanta 2023/2024. I pantaloncini completano la nuova divisa della squadra. C\'è il nero come colore principale, accompagnato da dettagli nel tradizionale blu insieme alla parte superiore.','Pantaloncini Home 2023/2024',25,'23.0','Atalanta','pantaloncini'),(3,'Calze da calcio da uomo. Indicate per uso professionale in competizione. Completa la tua divisa dell\'Atalanta con queste calze con i colori della squadra. Prenditi cura di ogni dettaglio al millimetro e sentiti come un vero calciatore d\'élite.','Calze Home 2023/2024',50,'19.0','Atalanta','calzettoni'),(4,'Le maniche sono blu con bordo rosso, il retro del collo è personalizzato con la scritta “LO SQUADRONE CHE TREMARE IL MONDO FA”, il logo Macron e la scritta ‘Designed in Bologna’ che certifica come ogni capo sia ideato, progettato e sviluppato nel quartier generale dello sponsor tecnico. Nel retrocollo esterno, è stampata in rosso “WE ARE ONE”, claim identificativo del Club. Sul petto, in stampa siliconata, a sinistra, il logo del Bologna Fc 1909, a destra e in bianco il Macron Hero.','Maglia Home 2023/2024',50,'50.0','Bologna','maglia'),(5,'100% poliestere. Pantaloncini gara ufficiali cin girovita elastico. Loghi Bologna FC e Macron.','Pantaloncini Home 2023/2024',30,'25.0','Bologna','pantaloncini'),(6,'80% poliammide, 15% cotone, 5% elastane. Calzettoni gara.','Calze Home 2023/2024',30,'20.0','Bologna','calzettoni'),(7,'Impreziosita da un colletto rossoblù stile polo\r\n\r\nTessuto 85% Poliestere 15% Elasten\r\n\r\nManiche, carrè e pannelli laterali in tessuto mesh','Maglia Home 2023/2024',10,'95.0','Cagliari','maglia'),(8,'Tessuto 100% Poliestere.\r\nTutti i tessuti hanno subito un trattamento per massimizzare la traspirabilità e ottimizzare le prestazioni sul campo.','Pantaloncini Home 2023/2024',10,'25.0','Cagliari','pantaloncini'),(9,'Tessuto poliammide ed elastan, piede in cotone. Zona di compressione a coste all’altezza del tendine d’Achille.\r\nScritta Cagliari Calcio sullo stinco e logo EYE Sport sul risvolto superiore. Prodotto Ufficiale EYE Sport – Cagliari Calcio.','Calze Home 2023/2024',13,'15.0','Cagliari','calzettoni'),(10,'Questo capo è stato realizzato con tessuto Quick Dry altamente performante e traspirante. Permette una rapida asciugatura e facilita l’allontanamento del sudore dal corpo verso l’esterno, lasciando la pelle fresca e asciutta.','Maglia Home 2023/2024',10,'80.0','Empoli','maglia'),(11,'Tessuto 100% Poliestere. Tutti i tessuti hanno subito un trattamento per massimizzare la traspirabilità e ottimizzare le prestazioni sul campo.','Pantaloncini Home 2023/2024',10,'15.0','Empoli','pantaloncini'),(12,'Il design della maglia Home 23/24 si ispira al Giglio, simbolo per eccellenza di Firenze e della Fiorentina.\r\n\r\nFiori intrecciati si uniscono con la sinuosità di un DNA, il DNA che accomuna tutti i Viola, tifosi e squadra, mettendo la tecnologia PRO KOMBAT™ al servizio dell’identità e della storia del Club.','Maglia Home 2023/2024',30,'99.0','Fiorentina','maglia'),(13,'Pantaloncini gara del nuovo home kit 23-24 in tessuto piquè riciclato e con tecnologia HYDRO-WAY PROTECTION.\r\n\r\nRegular fit.\r\n\r\nLogo Fiorentina con patch 3D.','Pantaloncini Home 2023/2024',30,'49.0','Fiorentina','pantaloncini'),(14,'Calza gioco KOMBAT™ in fibra poliammidica elasticizzata. Piede con spugna anatomica per massima ammortizzazione, zona di aerazione sul collo del piede, polpaccio con lavorazione in jacquard e linea anatomica specifica per piede sinistro e destro. Packaging 1 paio. Logo Omini sul retro in jacquard e logo team in jacquard sul fronte. ACF FIORENTINA.\r\nComposizione:15% Cotone, 4% Gomma, 75% Poliammide, 6% Poliestere','Calze Home 2023/2024',30,'15.0','Fiorentina','calzettoni'),(15,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',20,'30.0','Genoa','maglia'),(16,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',20,'45.0','Genoa','pantaloncini'),(17,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',35,'50.0','Genoa','calzettoni'),(18,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',25,'65.0','Inter','maglia'),(19,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',25,'45.0','Inter','pantaloncini'),(20,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024ù',45,'35.0','Inter','calzettoni'),(21,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',20,'25.0','Juventus','maglia'),(22,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',65,'35.0','Juventus','pantaloncini'),(23,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',30,'55.0','Juventus','calzettoni'),(24,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',45,'25.0','Lazio','maglia'),(25,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',35,'35.0','Lazio','pantaloncini'),(26,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',35,'35.0','Lazio','calzettoni'),(27,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',35,'45.0','Milan','maglia'),(28,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',40,'60.0','Milan','pantaloncini'),(29,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',45,'30.0','Milan','calzettoni'),(30,'SSC Napoli Maglia gara home 2023/2024\r\n\r\nDal brand EA7 by Giorgio Armani Group\r\n\r\nLa Maglia EA7/SSC Napoli Home 2023/2024 rappresenta l’unicità e la New Era del mondo azzurro. \r\n\r\nNuovo styline con manica raglan su tessuto tecnico stretch, piping in contrasto bianco, collo a V ad incrocio con stampa tricolore come sulla linea fondo della manica. Nuovo motivo allover “N” con tecnica di stampa a caldo su materiale tecnico traspirante con tecnologia Dry Touch. Patch classico Napoli in silicone e scudetto tricolore personalizzato. \r\n\r\nVestibilità: Slim Fit. Composizione: 82% poliestere, 18% elastane','Maglia Home 2023/2024',90,'130.0','Napoli','maglia'),(31,'SSC Napoli Shorts Gara Azzurro 2023/2024. Banda laterale con loghi SSC Napoli allover. 100% poliestere.','Pantaloncini Home 2023/2024',50,'85.0','Napoli','pantaloncini'),(32,'Calze gara SSC Napoli azzurre 2023/2024. 92% nylon, 8% elastane','Calze Home 2023/2024',35,'50.0','Napoli','calzettoni'),(33,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',35,'45.0','Roma','maglia'),(34,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',45,'50.0','Roma','pantaloncini'),(35,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',50,'50.0','Roma','calzettoni'),(36,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',25,'50.0','Salernitana','maglia'),(37,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',50,'50.0','Salernitana','pantaloncini'),(38,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',30,'45.0','Salernitana','calzettoni'),(39,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',60,'55.0','Sassuolo','maglia'),(40,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',45,'35.0','Sassuolo','pantaloncini'),(41,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',50,'45.0','Sassuolo','calzettoni'),(42,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',45,'25.0','Torino','maglia'),(43,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',35,'35.0','Torino','maglia'),(44,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',50,'45.0','Torino','calzettoni'),(45,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',35,'45.0','Udinese','maglia'),(46,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',60,'45.0','Udinese','pantaloncini'),(47,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',15,'35.0','Verona','maglia'),(48,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',50,'45.0','Verona','pantaloncini'),(49,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',35,'45.0','Verona','calzettoni'),(50,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',25,'35.0','Monza','maglia'),(51,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',35,'45.0','Monza','pantaloncini'),(52,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',55,'55.0','Monza','calzettoni'),(53,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',30,'50.0','Lecce','maglia'),(54,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',45,'35.0','Lecce','maglia'),(55,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',55,'15.0','Lecce','calzettoni'),(56,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',45,'35.0','Frosinone','maglia');
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `squadra`
--

DROP TABLE IF EXISTS `squadra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `squadra` (
  `idSquadra` varchar(25) NOT NULL,
  `pathLogo` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idSquadra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `squadra`
--

LOCK TABLES `squadra` WRITE;
/*!40000 ALTER TABLE `squadra` DISABLE KEYS */;
INSERT INTO `squadra` VALUES ('Atalanta','/image/stemmi/atalanta.png'),('Bologna','/image/stemmi/bologna.png'),('Cagliari','/image/stemmi/cagliari.png'),('Empoli','/image/stemmi/empoli.png'),('Fiorentina','/image/stemmi/fiorentina.png'),('Frosinone','/image/stemmi/frosinone.png'),('Genoa','/image/stemmi/genoa.png'),('Inter','/image/stemmi/inter.png'),('Juventus','/image/stemmi/juventus.png'),('Lazio','/image/stemmi/lazio.png'),('Lecce','/image/stemmi/lecce.png'),('Milan','/image/stemmi/milan.png'),('Monza','/image/stemmi/monza.png'),('Napoli','/image/stemmi/napoli.png'),('Roma','/image/stemmi/roma.png'),('Salernitana','/image/stemmi/salernitana.png'),('Sassuolo','/image/stemmi/sassuolo.png'),('Torino','/image/stemmi/torino.png'),('Udinese','/image/stemmi/udinese.png'),('Verona','/image/stemmi/verona.png');
/*!40000 ALTER TABLE `squadra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `Username` varchar(25) NOT NULL,
  `Nome` varchar(45) DEFAULT NULL,
  `Cognome` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  `Indirizzo` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES ('admin','Antonio','Ruta','admin@gmail.com','3456789042','via 1','admin',1),('utente10','NomeUtente10','CognomeUtente10','utente10@email.com','234546546','via 10','passwordUtente10',0),('utente2','NomeUtente2','CognomeUtente2','utente2@email.com','2354525425','via 2','passwordUtente2',0),('utente3','NomeUtente3','CognomeUtente3','utente3@email.com','2344556777','via 3','passwordUtente3',0),('utente4','NomeUtente4','CognomeUtente4','utente4@email.com','4566677235','via 4','passwordUtente4',0),('utente5','NomeUtente5','CognomeUtente5','utente5@email.com','3546365656','via 5','passwordUtente5',0),('utente6','NomeUtente6','CognomeUtente6','utente6@email.com','35776874555','via 6','passwordUtente6',0),('utente7','NomeUtente7','CognomeUtente7','utente7@email.com','456789999','via 7','passwordUtente7',0),('utente8','NomeUtente8','CognomeUtente8','utente8@email.com','3544657537','via 8','passwordUtente8',0),('utente9','NomeUtente9','CognomeUtente9','utente9@email.com','23454545454','via 9','passwordUtente9',0);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-23 17:36:58
