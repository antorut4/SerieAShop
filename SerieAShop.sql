DROP DATABASE SerieAShop;
CREATE DATABASE IF NOT EXISTS SerieAShop;
USE SerieAShop;
	CREATE TABLE Squadra (
        idSquadra VARCHAR(25) NOT NULL,
        pathLogo VARCHAR(60) NULL,
        PRIMARY KEY (idSquadra));

    CREATE TABLE Adminc (
        Username VARCHAR(25) NOT  	NULL,
        password VARCHAR(45) NULL,
        nome VARCHAR(45) NULL,
        cognome VARCHAR(45) NULL,
        dataDiNascita DATE NULL,
        PRIMARY KEY (Username));

    CREATE TABLE Utente (
        Username VARCHAR(25) NOT NULL,
        Nome VARCHAR(45) NULL,
        Cognome VARCHAR(45) NULL,
        Data_Nascita DATE NULL,
        Email VARCHAR(45) NULL,
        Password VARCHAR(45) NULL,
        PRIMARY KEY (Username));

    CREATE TABLE Carrello (
        idCarrello INT NOT NULL,
        username VARCHAR(25),
        PRIMARY KEY (idCarrello),
        FOREIGN KEY(username) REFERENCES Utente(Username)
        );

    CREATE TABLE Prodotto (
        idProdotto INT NOT NULL,
        Descrizione VARCHAR(45) NULL,
        NomeProdotto VARCHAR(45) NULL,
        Quantita INT NULL,
        Prezzo VARCHAR(45) NULL,
        idSquadra VARCHAR(25),
        PRIMARY KEY (idProdotto),
        FOREIGN KEY(idSquadra) REFERENCES Squadra(idSquadra));
    
    CREATE TABLE Composto (
        id INT NOT NULL,
        idProdotto INT,
        idCarrello INT,
        PRIMARY KEY (id),
        FOREIGN KEY(idProdotto) REFERENCES Prodotto(idProdotto),
        FOREIGN KEY(idCarrello) REFERENCES Carrello(idCarrello)
        );

    CREATE TABLE Ordine (
        idOrdine INT NOT NULL,
        PrezzoTotale DOUBLE NULL,
        metodoDiPagamento VARCHAR(45) NULL,
        via VARCHAR(45) NULL,
        civico VARCHAR(45) NULL,
        CAP VARCHAR(45) NULL,
        nomeDestinatario VARCHAR(45) NULL,
        PRIMARY KEY (idOrdine));

    CREATE TABLE Genera (
        id INT NOT NULL,
        PRIMARY KEY (id),
        username varchar(25),
        idOrdine INT,
        FOREIGN KEY(username) REFERENCES Utente(Username),
        FOREIGN KEY(idOrdine) REFERENCES Ordine(idOrdine));
        
-- Popolamento della tabella Squadra con almeno 10 occorrenze
INSERT INTO Squadra (idSquadra, pathLogo)
VALUES
    ("Juventus", "juventus.png"),
    ("Milan", "milan.png"),
    ("Inter", "inter.png"),
    ("Roma", "roma.png"),
    ("Napoli", "napoli.png"),
    ("Lazio", "lazio.png"),
    ("Fiorentina", "fiorentina.png"),
    ("Atalanta", "atalanta.png"),
    ("Sampdoria", "sampdoria.png"),
    ("Torino", "torino.png"),
    ("Bologna", "bologna.png");

-- Popolamento della tabella Adminc con almeno 10 occorrenze
INSERT INTO Adminc (Username, password, nome, cognome, dataDiNascita)
VALUES
    ("admin1", "password1", "Nome1", "Cognome1", "1990-01-01"),
    ("admin2", "password2", "Nome2", "Cognome2", "1985-05-15"),
    ("admin3", "password3", "Nome3", "Cognome3", "1988-08-20"),
    ("admin4", "password4", "Nome4", "Cognome4", "1992-02-12"),
    ("admin5", "password5", "Nome5", "Cognome5", "1987-07-25"),
    ("admin6", "password6", "Nome6", "Cognome6", "1994-04-04"),
    ("admin7", "password7", "Nome7", "Cognome7", "1989-09-15"),
    ("admin8", "password8", "Nome8", "Cognome8", "1995-05-10"),
    ("admin9", "password9", "Nome9", "Cognome9", "1986-12-30"),
    ("admin10", "password10", "Nome10", "Cognome10", "1998-11-08");

-- Popolamento della tabella Utente con almeno 10 occorrenze
INSERT INTO Utente (Username, Nome, Cognome, Data_Nascita, Email, Password)
VALUES
    ("utente1", "NomeUtente1", "CognomeUtente1", "1995-03-10", "utente1@email.com", "passwordUtente1"),
    ("utente2", "NomeUtente2", "CognomeUtente2", "1988-07-20", "utente2@email.com", "passwordUtente2"),
    ("utente3", "NomeUtente3", "CognomeUtente3", "1990-05-15", "utente3@email.com", "passwordUtente3"),
    ("utente4", "NomeUtente4", "CognomeUtente4", "1987-12-05", "utente4@email.com", "passwordUtente4"),
    ("utente5", "NomeUtente5", "CognomeUtente5", "1992-02-28", "utente5@email.com", "passwordUtente5"),
    ("utente6", "NomeUtente6", "CognomeUtente6", "1986-10-18", "utente6@email.com", "passwordUtente6"),
    ("utente7", "NomeUtente7", "CognomeUtente7", "1993-09-22", "utente7@email.com", "passwordUtente7"),
    ("utente8", "NomeUtente8", "CognomeUtente8", "1989-04-14", "utente8@email.com", "passwordUtente8"),
    ("utente9", "NomeUtente9", "CognomeUtente9", "1991-08-07", "utente9@email.com", "passwordUtente9"),
    ("utente10", "NomeUtente10", "CognomeUtente10", "1985-06-30", "utente10@email.com", "passwordUtente10");

-- Popolamento della tabella Carrello con almeno 10 occorrenze
INSERT INTO Carrello (idCarrello, username)
VALUES
    (1, "utente1"),
    (2, "utente2"),
    (3, "utente3"),
    (4, "utente4"),
    (5, "utente5"),
    (6, "utente6"),
    (7, "utente7"),
    (8, "utente8"),
    (9, "utente9"),
    (10, "utente10");
    
INSERT INTO Prodotto (idProdotto, Descrizione, NomeProdotto, Quantita, Prezzo, idSquadra)
VALUES
    (1, "Maglia Juventus", "Maglia Juventus 2023", 10, "50.00", "Juventus"),
    (2, "Maglia Milan", "Maglia Milan 2023", 8, "45.00", "Milan"),
    (3, "Sciarpa Inter", "Sciarpa Inter 2023", 15, "20.00", "Inter"),
    (4, "Maglia Roma", "Maglia Roma 2023", 12, "48.00", "Roma"),
    (5, "Maglia Napoli", "Maglia Napoli 2023", 9, "42.00", "Napoli"),
    (6, "Sciarpa Lazio", "Sciarpa Lazio 2023", 18, "22.00", "Lazio"),
    (7, "Maglia Fiorentina", "Maglia Fiorentina 2023", 11, "46.00", "Fiorentina"),
    (8, "Maglia Atalanta", "Maglia Atalanta 2023", 7, "44.00", "Atalanta"),
    (9, "Sciarpa Sampdoria", "Sciarpa Sampdoria 2023", 14, "18.00", "Sampdoria"),
    (10, "Maglia Torino", "Maglia Torino 2023", 13, "47.00", "Torino");

-- Popolamento della tabella Composto con almeno 10 occorrenze
INSERT INTO Composto (id, idProdotto, idCarrello)
VALUES
    (1, 1, 1),
    (2, 2, 1),
    (3, 1, 2),
    (4, 3, 2),
    (5, 4, 3),
    (6, 5, 3),
    (7, 6, 4),
    (8, 7, 4),
    (9, 8, 5),
    (10, 9, 5);

-- Popolamento della tabella Ordine con almeno 10 occorrenze
INSERT INTO Ordine (idOrdine, PrezzoTotale, metodoDiPagamento, via, civico, CAP, nomeDestinatario)
VALUES
    (1, 95.00, "Carta di credito", "Via Roma", "123", "00100", "Nome Destinatario 1"),
    (2, 45.00, "PayPal", "Via Milano", "456", "00200", "Nome Destinatario 2"),
    (3, 75.00, "Carta di credito", "Via Napoli", "789", "00300", "Nome Destinatario 3"),
    (4, 60.00, "PayPal", "Via Firenze", "101", "00400", "Nome Destinatario 4"),
    (5, 120.00, "Carta di credito", "Via Torino", "202", "00500", "Nome Destinatario 5"),
    (6, 30.00, "PayPal", "Via Bologna", "303", "00600", "Nome Destinatario 6"),
    (7, 50.00, "Carta di credito", "Via Venezia", "404", "00700", "Nome Destinatario 7"),
    (8, 90.00, "PayPal", "Via Verona", "505", "00800", "Nome Destinatario 8"),
    (9, 110.00, "Carta di credito", "Via Pisa", "606", "00900", "Nome Destinatario 9"),
    (10, 70.00, "PayPal", "Via Genova", "707", "01000", "Nome Destinatario 10");

-- Popolamento della tabella Genera con almeno 10 occorrenze
INSERT INTO Genera (id, username, idOrdine)
VALUES
    (1, "utente1", 1),
    (2, "utente2", 2),
    (3, "utente3", 3),
    (4, "utente4", 4),
    (5, "utente5", 5),
    (6, "utente6", 6),
    (7, "utente7", 7),
    (8, "utente8", 8),
    (9, "utente9", 9),
    (10, "utente10", 10);
    

-- Seleziona tutte le squadre
SELECT * FROM Squadra;

-- Seleziona tutti gli utenti
SELECT * FROM Utente;

-- Seleziona tutti i prodotti con una quantità maggiore di 10
SELECT * FROM Prodotto WHERE Quantita > 10;

-- Seleziona tutti gli ordini con un prezzo totale superiore a 50.00
SELECT * FROM Ordine WHERE PrezzoTotale > 50.00;

-- Seleziona tutti i carrelli degli utenti con username "utente1"
SELECT * FROM Carrello WHERE username = "utente1";

-- Seleziona tutti i prodotti nel carrello con idCarrello 1
SELECT P.* FROM Prodotto P
JOIN Composto C ON P.idProdotto = C.idProdotto
WHERE C.idCarrello = 1;

-- Seleziona tutti gli ordini generati dall'utente con username "utente2"
SELECT O.* FROM Ordine O
JOIN Genera G ON O.idOrdine = G.idOrdine
WHERE G.username = "utente2";

-- Seleziona il totale speso da un utente con username "utente3"
SELECT U.Username, SUM(O.PrezzoTotale) AS TotaleSpeso
FROM Utente U
JOIN Genera G ON U.Username = G.username
JOIN Ordine O ON G.idOrdine = O.idOrdine
WHERE U.Username = "utente3"
GROUP BY U.Username;

        
        

    