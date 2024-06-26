USE casarina;

CREATE TABLE Parent(
	idParent int,
    Nom varchar(30),
    Prénom varchar(30),
    NuméroDeTéléphone1 bigint,
    NuméroDeTéléphone2 bigint,
    NomDuTuteur2 varchar(30),
    NuméroDeTéléphone bigint,
    PRIMARY KEY (idParent)
);

CREATE TABLE Client(
	idClient int,
    Nom varchar(30),
    Prénom varchar(30),
    DateDeNaissance Date,
    Adresse varchar(50),
    idParent int,
    PRIMARY KEY(idClient),
    FOREIGN KEY(idParent)
        REFERENCES Parent(idParent)
);

CREATE TABLE Santé(
    GroupeSanguin varchar(3),
    Allergie varchar(30),
    MaladieParticulière varchar(30),
    idClient int,
    FOREIGN KEY (idClient)
    	REFERENCES Client(idCLient)
);

INSERT INTO Client(idClient, Nom, Prénom, nbPresence)
	VALUES (1, "Gojo", "Satoru", 0),
    	   (2, "Ryomen", "Sukuna", 0);
           
ALTER TABLE Client
	ADD COLUMN nbPresence int;
    
UPDATE Client
	SET nbPresence = 0
    WHERE idClient < 3;

SELECT * from Client;