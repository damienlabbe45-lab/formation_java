-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS Formation  DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


USE Formation;

DROP TABLE IF EXISTS Be;

DROP TABLE IF EXISTS Order_;

DROP TABLE IF EXISTS Parcours;

DROP TABLE IF EXISTS Client;

DROP TABLE IF EXISTS User;

DROP TABLE IF EXISTS Formation;

-- -----------------------------------------------------------------------------
-- - Construction de la tables des formations                        ---
-- -----------------------------------------------------------------------------
CREATE TABLE Formation (
	formation_id			int		PRIMARY KEY AUTO_INCREMENT,
	description			varchar(350)	NOT NULL,
	name_formation      varchar(90) NOT NULL UNIQUE
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO Formation ( name_formation, description) VALUES 
    ( 'Java', 'Cours de débutant pour java'),
    ( 'Python', 'Cours de débutant pour python'),
    ( 'SQL et base de données relationnelle', 'cours génériques de base de donnée et de sql'),
    ( 'Nosql et base de donnée non relationelle', 'cours génériques de nosql'),
    ( 'New sql, c''est quoi?', 'Cours de new sql et explication en profondeur de ce que c''est' ),
    ( 'Mysql et mariadb', 'cours sur mysql et mariadb'),
    ( 'SQLite', 'cours sur sqlite'),
    ( 'PosgSQL', 'cours sur PosgrSQL'),
    ( 'IBM DB2', 'cours sur IBM DB2'),
	('parcours sql et nosql', "ensemble des cours sur les bases de données"),
    ( 'Programmation avancée python', 'Progammation orientée objet en python, utilisation des mixins'),
    ( 'Proggrammation orientée Java', 'cours en java avancée'),
    ( 'Les structures de données', 'cours sur les structures de données les plus courantes en proggrammation'),
	("Utilisation des threads en python", "utilisation des threads en python"),
	("Exception et BaseException", "explication des Execeptions et BaseException"),
	("Random et secrets, leurs utilités et différences", "explications sur secrets et random en python"),
	("asynchrone et asyncio", "explication de c'est quoi l'asynchrone ainsi que comment utiliser asyncio en python"),
	("l'aléatoire en java", "explication de l'aléatoire en java"),
	("les fichiers en python et java", "explications sur comment ouvrir les fichiers sur ces différents types de langage"),
	("les gestionnaires d'erreurs vs les vérification en java et pyhon", 
	"explique comment faire des gestionnaires d'erreurs personnalisés en python et en java"),
	("parcours python-java", "ensemble des parcours sur python et java"),
	("git", "cours de débutant sur git"),
	("git avancée", "cours avancés sur git"),
	("parcours git", "l'ensemble des cours git");


	-- -----------------------------------------------------------------------------
-- - Construction de la table User                       ---
-- -----------------------------------------------------------------------------


CREATE TABLE User(
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	addressemail VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(60) NOT NULL,
	is_director boolean  NOT NULL
)ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO User(addressemail, password, is_director) VALUES
("animal.tsuky@anais.fr", "ouafouaf", false),
("ebbal.neimad67890@dujargon.fr", "1234567890", false),
("boulive.meinardeis09@heho.fr", "hbfgvtedghsdvdehgriehfgnfjdgdjzà988,jfjghà&&dhdbz!db", true);


	-- -----------------------------------------------------------------------------
-- - Construction de la table Client                  ---
-- -----------------------------------------------------------------------------

CREATE TABLE Client(
	client_id INT PRIMARY KEY,
	name_client VARCHAR(50) NOT NULL,
	firstname_client VARCHAR(50) NOT NULL,
	phonenumber VARCHAR(18) DEFAULT 0,
	CONSTRAINT fk_client_id_user FOREIGN KEY(client_id) REFERENCES User(user_id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO Client(client_id, name_client, firstname_client, phonenumber) VALUES
(1, "Tsuky", "Animal", default),
(2, "Neimad", "Ebbal", "345676543445654565"),
(3, "Meinardeis", "Boulive", default);

	-- -----------------------------------------------------------------------------
-- - Construction de la table Parcours                  ---
-- -----------------------------------------------------------------------------
CREATE TABLE Parcours(
	formation_incluant int NOT NULL, 
	formation_incluse int NOT NULL,
	PRIMARY KEY(formation_incluant, formation_incluse),
	CONSTRAINT fk_formation_incluant_formation_id FOREIGN KEY(formation_incluant) REFERENCES Formation(formation_id),
	CONSTRAINT fk_formation_incuse_formation_id FOREIGN KEY(formation_incluse) REFERENCES Formation(formation_id)
	) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO Parcours(formation_incluant, formation_incluse) VALUES
	(10, 3),
	(10, 4),
	(10, 5),
	(10, 6),
	(10, 7),
	(10, 8),
	(10, 9);


	-- -----------------------------------------------------------------------------
-- - Construction de la table Order_                ---
-- -----------------------------------------------------------------------------
CREATE TABLE Order_(
	formation_id int NOT NULL, 
	client_id int NOT NULL,
	date_order DATE NOT NULL,
	PRIMARY KEY(formation_id, client_id),
	CONSTRAINT fk_Parcours_formation_id_formation_id FOREIGN KEY(formation_id) REFERENCES Formation(formation_id),
	CONSTRAINT fk_Parcours_client_id_client_id FOREIGN KEY(client_id) REFERENCES Client(client_id)
	) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO Order_(formation_id, client_id, date_order)  VALUES
	(1, 3, CURRENT_DATE),
	(10, 1, CURRENT_DATE),
	(10, 2, CURRENT_DATE),
	(11, 1, CURRENT_DATE),
	(1, 1, '2025-12-01');

	-- -----------------------------------------------------------------------------
-- - Construction de la table Be                ---
-- -----------------------------------------------------------------------------
CREATE TABLE Be(
	formation_id int NOT NULL, 
	is_dist boolean NOT NULL,
	session_id int NOT NULL,
	price DECIMAL(6, 2),
	end_date DATE NOT NULL,
	beginning_date DATE NOT NULL,
	PRIMARY KEY(formation_id, is_dist, session_id),
	CONSTRAINT fk_Be_formation_id_formation_id FOREIGN KEY(formation_id) REFERENCES Formation(formation_id)
	) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO Be(formation_id, is_dist, session_id, price, end_date, beginning_date) VALUES
	(1, false, 1, 45.67, '2015-08-22', '2015-07-15'),
	(1, true, 2, 40.67, '2025-08-22', '2025-07-25'),
	(1, false, 2, 45.67, '2025-08-22', '2025-07-25'),
	(1, true, 3, 50.67, '2026-08-22', '2026-07-25'),
	(1, false, 3, 57.67, '2026-08-22', '2026-07-25'),
	(10, false, 3, 123.09, '2027-12-20', '2026-10-08'),
	(10, false, 2, 167.09, '2026-12-20', '2025-10-08'),
	(10, false, 1, 189.09, '2025-12-20', '2024-10-08'),
	(2, true, 1, 34.86,'2026-11-07', CURRENT_DATE),
	(3, true, 1, 39.86,'2026-11-07', CURRENT_DATE),
	(4, false, 1, 64.86,'2026-11-07', CURRENT_DATE),
	(5, true, 1, 14.86,'2026-11-07', CURRENT_DATE),
	(6, true, 1 ,389.66,'2026-11-07', CURRENT_DATE),
	(7, true, 1 ,289.66,'2026-11-07', CURRENT_DATE),
	(8, false, 1 ,12.66,'2026-11-07', CURRENT_DATE),
	(9, false, 1 ,1002.66,'2026-11-07', CURRENT_DATE),
	(11, false, 1 ,542.66,'2026-11-07', CURRENT_DATE),
	(12, false, 1 ,102.66,'2026-11-07', CURRENT_DATE),
	(13, false, 1 ,102.50,'2026-11-07', CURRENT_DATE),
	(14, true, 1 ,102.66,'2026-11-07', CURRENT_DATE),
	(15, true, 1 ,40.66,'2026-11-07', CURRENT_DATE),
	(16, true, 1 ,34.66,'2026-11-07', CURRENT_DATE),
	(17, false, 1 ,56.66,'2026-11-07', CURRENT_DATE),
	(18, false, 1 ,96.66,'2026-11-07', CURRENT_DATE),
	(19, false, 1 ,6.66,'2026-11-07', CURRENT_DATE),
	(20, true, 1 ,76.12,'2026-11-07', CURRENT_DATE),
	(21, true, 1 ,34.12,'2026-11-07', CURRENT_DATE),
	(22, true, 1 ,4576.12,'2026-11-07', CURRENT_DATE);
