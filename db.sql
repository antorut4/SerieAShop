
DROP DATABASE SerieAShop;
CREATE DATABASE IF NOT EXISTS SerieAShop;
USE SerieAShop;



CREATE TABLE `squadra` (
                           `idSquadra` varchar(25) NOT NULL,
                           `pathLogo` varchar(60) DEFAULT NULL,
                           PRIMARY KEY (`idSquadra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- Dumping data for table `squadra`
--

LOCK TABLES `squadra` WRITE;
/*!40000 ALTER TABLE `squadra` DISABLE KEYS */;
INSERT INTO `squadra` VALUES ('Atalanta','/image/stemmi/atalanta.png'),('Bologna','/image/stemmi/bologna.png'),('Cagliari','/image/stemmi/cagliari.png'),('Empoli','/image/stemmi/empoli.png'),('Fiorentina','/image/stemmi/fiorentina.png'),('Frosinone','/image/stemmi/frosinone.png'),('Genoa','/image/stemmi/genoa.png'),('Inter','/image/stemmi/inter.png'),('Juventus','/image/stemmi/juventus.png'),('Lazio','/image/stemmi/lazio.png'),('Lecce','/image/stemmi/lecce.png'),('Milan','/image/stemmi/milan.png'),('Monza','/image/stemmi/monza.png'),('Napoli','/image/stemmi/napoli.png'),('Roma','/image/stemmi/roma.png'),('Salernitana','/image/stemmi/salernitana.png'),('Sassuolo','/image/stemmi/sassuolo.png'),('Torino','/image/stemmi/torino.png'),('Udinese','/image/stemmi/udinese.png'),('Verona','/image/stemmi/verona.png');
/*!40000 ALTER TABLE `squadra` ENABLE KEYS */;
UNLOCK TABLES;



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

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES ('admin','admin','admin','admin@gmail.com','3456789042','via 1','admin',1);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;


CREATE TABLE `carrello` (
                            `idCarrello` int NOT NULL auto_increment,
                            `username` varchar(25) DEFAULT NULL,
                            `totale` double DEFAULT NULL,
                            PRIMARY KEY (`idCarrello`),
                            KEY `username` (`username`),
                            CONSTRAINT `carrello_ibfk_1` FOREIGN KEY (`username`) REFERENCES `utente` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `ordine` (
                          `idOrdine` int NOT NULL auto_increment,
                          `PrezzoTotale` double DEFAULT NULL,
                          `dataOrdine` date DEFAULT NULL,
                          `metodoDiPagamento` varchar(60) DEFAULT NULL,
                          `indirizzoSpedizione` varchar(60) DEFAULT NULL,
                          `username` varchar(45) DEFAULT NULL,
                          `idCarrello` int DEFAULT NULL,
                          prodotti varchar(2000) NOT NULL,
                          PRIMARY KEY (`idOrdine`),
                          KEY `username` (`username`),
                          KEY `idCarrello` (`idCarrello`),
                          CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`username`) REFERENCES `utente` (`Username`),
                          CONSTRAINT `ordine_ibfk_2` FOREIGN KEY (`idCarrello`) REFERENCES `carrello` (`idCarrello`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




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

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (1,'Joma e Atalanta sono orgogliosi di presentare le nuove maglie gioco con le quali i giocatori affronteranno la prossima stagione 2023/2024.\r\n\r\nLa prima maglia si caratterizza con l’iconico disegno a righe neroazzurre, in cui trova spazio un moderno sublimato geometrico evidente nelle righe azzurre.\r\n\r\nI disegni danno così origine a un effetto ottico nuovo e innovativo completamente diverso da quanto realizzato nelle passate stagioni. Il modello scelto per la maglia home 23/24 si contraddistingue per un elegante collo a polo, con costine neroazzurre anche nei polsini, per una vestibilità ancor più confortevole.','Maglia Home 2023/2024',55,'89.0','Atalanta','maglia'),(2,'Pantaloncini da uomo della prima divisa dell\'Atalanta 2023/2024. I pantaloncini completano la nuova divisa della squadra. C\'è il nero come colore principale, accompagnato da dettagli nel tradizionale blu insieme alla parte superiore.','Pantaloncini Home 2023/2024',25,'23.0','Atalanta','pantaloncini'),(3,'Calze da calcio da uomo. Indicate per uso professionale in competizione. Completa la tua divisa dell\'Atalanta con queste calze con i colori della squadra. Prenditi cura di ogni dettaglio al millimetro e sentiti come un vero calciatore d\'élite.','Calze Home 2023/2024',50,'19.0','Atalanta','calzettoni'),(4,'Le maniche sono blu con bordo rosso, il retro del collo è personalizzato con la scritta “LO SQUADRONE CHE TREMARE IL MONDO FA”, il logo Macron e la scritta ‘Designed in Bologna’ che certifica come ogni capo sia ideato, progettato e sviluppato nel quartier generale dello sponsor tecnico. Nel retrocollo esterno, è stampata in rosso “WE ARE ONE”, claim identificativo del Club. Sul petto, in stampa siliconata, a sinistra, il logo del Bologna Fc 1909, a destra e in bianco il Macron Hero.','Maglia Home 2023/2024',50,'50.0','Bologna','maglia'),(5,'100% poliestere. Pantaloncini gara ufficiali cin girovita elastico. Loghi Bologna FC e Macron.','Pantaloncini Home 2023/2024',30,'25.0','Bologna','pantaloncini'),(6,'80% poliammide, 15% cotone, 5% elastane. Calzettoni gara.','Calze Home 2023/2024',30,'20.0','Bologna','calzettoni'),(7,'Impreziosita da un colletto rossoblù stile polo\r\n\r\nTessuto 85% Poliestere 15% Elasten\r\n\r\nManiche, carrè e pannelli laterali in tessuto mesh','Maglia Home 2023/2024',10,'95.0','Cagliari','maglia'),(8,'Tessuto 100% Poliestere.\r\nTutti i tessuti hanno subito un trattamento per massimizzare la traspirabilità e ottimizzare le prestazioni sul campo.','Pantaloncini Home 2023/2024',10,'25.0','Cagliari','pantaloncini'),(9,'Tessuto poliammide ed elastan, piede in cotone. Zona di compressione a coste all’altezza del tendine d’Achille.\r\nScritta Cagliari Calcio sullo stinco e logo EYE Sport sul risvolto superiore. Prodotto Ufficiale EYE Sport – Cagliari Calcio.','Calze Home 2023/2024',13,'15.0','Cagliari','calzettoni'),(10,'Questo capo è stato realizzato con tessuto Quick Dry altamente performante e traspirante. Permette una rapida asciugatura e facilita l’allontanamento del sudore dal corpo verso l’esterno, lasciando la pelle fresca e asciutta.','Maglia Home 2023/2024',10,'80.0','Empoli','maglia'),(11,'Tessuto 100% Poliestere. Tutti i tessuti hanno subito un trattamento per massimizzare la traspirabilità e ottimizzare le prestazioni sul campo.','Pantaloncini Home 2023/2024',10,'15.0','Empoli','pantaloncini'),(12,'Il design della maglia Home 23/24 si ispira al Giglio, simbolo per eccellenza di Firenze e della Fiorentina.\r\n\r\nFiori intrecciati si uniscono con la sinuosità di un DNA, il DNA che accomuna tutti i Viola, tifosi e squadra, mettendo la tecnologia PRO KOMBAT™ al servizio dell’identità e della storia del Club.','Maglia Home 2023/2024',30,'99.0','Fiorentina','maglia'),(13,'Pantaloncini gara del nuovo home kit 23-24 in tessuto piquè riciclato e con tecnologia HYDRO-WAY PROTECTION.\r\n\r\nRegular fit.\r\n\r\nLogo Fiorentina con patch 3D.','Pantaloncini Home 2023/2024',30,'49.0','Fiorentina','pantaloncini'),(14,'Calza gioco KOMBAT™ in fibra poliammidica elasticizzata. Piede con spugna anatomica per massima ammortizzazione, zona di aerazione sul collo del piede, polpaccio con lavorazione in jacquard e linea anatomica specifica per piede sinistro e destro. Packaging 1 paio. Logo Omini sul retro in jacquard e logo team in jacquard sul fronte. ACF FIORENTINA.\r\nComposizione:15% Cotone, 4% Gomma, 75% Poliammide, 6% Poliestere','Calze Home 2023/2024',30,'15.0','Fiorentina','calzettoni'),(15,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',20,'30.0','Genoa','maglia'),(16,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',20,'45.0','Genoa','pantaloncini'),(17,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',35,'50.0','Genoa','calzettoni'),(18,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',25,'65.0','Inter','maglia'),(19,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',25,'45.0','Inter','pantaloncini'),(20,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024ù',45,'35.0','Inter','calzettoni'),(21,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',20,'25.0','Juventus','maglia'),(22,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',65,'35.0','Juventus','pantaloncini'),(23,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',30,'55.0','Juventus','calzettoni'),(24,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',45,'25.0','Lazio','maglia'),(25,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',35,'35.0','Lazio','pantaloncini'),(26,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',35,'35.0','Lazio','calzettoni'),(27,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',35,'45.0','Milan','maglia'),(28,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',40,'60.0','Milan','pantaloncini'),(29,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',45,'30.0','Milan','calzettoni'),(30,'SSC Napoli Maglia gara home 2023/2024\r\n\r\nDal brand EA7 by Giorgio Armani Group\r\n\r\nLa Maglia EA7/SSC Napoli Home 2023/2024 rappresenta l’unicità e la New Era del mondo azzurro. \r\n\r\nNuovo styline con manica raglan su tessuto tecnico stretch, piping in contrasto bianco, collo a V ad incrocio con stampa tricolore come sulla linea fondo della manica. Nuovo motivo allover “N” con tecnica di stampa a caldo su materiale tecnico traspirante con tecnologia Dry Touch. Patch classico Napoli in silicone e scudetto tricolore personalizzato. \r\n\r\nVestibilità: Slim Fit. Composizione: 82% poliestere, 18% elastane','Maglia Home 2023/2024',90,'130.0','Napoli','maglia'),(31,'SSC Napoli Shorts Gara Azzurro 2023/2024. Banda laterale con loghi SSC Napoli allover. 100% poliestere.','Pantaloncini Home 2023/2024',50,'85.0','Napoli','pantaloncini'),(32,'Calze gara SSC Napoli azzurre 2023/2024. 92% nylon, 8% elastane','Calze Home 2023/2024',35,'50.0','Napoli','calzettoni'),(33,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',35,'45.0','Roma','maglia'),(34,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',45,'50.0','Roma','pantaloncini'),(35,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',50,'50.0','Roma','calzettoni'),(36,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',25,'50.0','Salernitana','maglia'),(37,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',50,'50.0','Salernitana','pantaloncini'),(38,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',30,'45.0','Salernitana','calzettoni'),(39,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',60,'55.0','Sassuolo','maglia'),(40,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',45,'35.0','Sassuolo','pantaloncini'),(41,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',50,'45.0','Sassuolo','calzettoni'),(42,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',45,'25.0','Torino','maglia'),(43,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',35,'35.0','Torino','pantaloncino'),(44,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',50,'45.0','Torino','calzettoni'),(45,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',35,'45.0','Udinese','maglia'),(46,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',60,'45.0','Udinese','pantaloncini'),(47,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',15,'35.0','Verona','maglia'),(48,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',50,'45.0','Verona','pantaloncini'),(49,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',35,'45.0','Verona','calzettoni'),(50,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',25,'35.0','Monza','maglia'),(51,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',35,'45.0','Monza','pantaloncini'),(52,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',55,'55.0','Monza','calzettoni'),(53,'Maglia gara Home stagione sportiva 2023/2024.','Maglia Home 2023/2024',30,'50.0','Lecce','maglia'),(54,'Pantaloncini gara Home stagione sportiva 2023/2024','Pantaloncini Home 2023/2024',45,'35.0','Lecce','pantaloncini'),(55,'Calzettoni gara Home stagione sportiva 2023/2024','Calze Home 2023/2024',55,'15.0','Lecce','calzettoni'),(56,'Maglia gara Home stagione sportiva 2023/2024','Maglia Home 2023/2024',45,'35.0','Frosinone','maglia');
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;



CREATE TABLE `prodotticarrello` (
                                    `idProdCarr` int NOT NULL auto_increment,
                                    `quantita` int DEFAULT NULL,
                                    `idCarrello` int DEFAULT NULL,
                                    `idProdotto` int DEFAULT NULL,
                                    taglia varchar(3) default NULL,
                                    PRIMARY KEY (`idProdCarr`),
                                    KEY `idProdotto` (`idProdotto`),
                                    KEY `idCarrello` (`idCarrello`),
                                    CONSTRAINT `prodotticarrello_ibfk_1` FOREIGN KEY (`idProdotto`) REFERENCES `prodotto` (`idProdotto`),
                                    CONSTRAINT `prodotticarrello_ibfk_2` FOREIGN KEY (`idCarrello`) REFERENCES `carrello` (`idCarrello`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;