#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

DROP DATABASE IF EXISTS RentACar;
CREATE DATABASE RentACar;
USE RentACar;


#------------------------------------------------------------
# Table: ProgrammeFidélité
#------------------------------------------------------------

CREATE TABLE ProgrammeFidelite(
        idProgrammeFidelite Int NOT NULL ,
        description         Varchar (50) NOT NULL ,
        prix                Decimal (10,2) NOT NULL ,
        tauxReduc           Float NOT NULL ,
        dateSouscription    Date NOT NULL
    ,CONSTRAINT ProgrammeFidelite_PK PRIMARY KEY (idProgrammeFidelite)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
        idClient            Int NOT NULL ,
        nom_client          Varchar (50) NOT NULL ,
        prenom_client       Varchar (50) NOT NULL ,
        email_client        Varchar (50) NOT NULL ,
        rue_client          Varchar (50) NOT NULL ,
        ville_client        Varchar (50) NOT NULL ,
        codePostal_client   Int NOT NULL ,
        numTel_client       Varchar (50) NOT NULL ,
        dureeDuProgramme    Int NOT NULL ,
        idProgrammeFidelite Int NOT NULL
    ,CONSTRAINT Client_PK PRIMARY KEY (idClient)

    ,CONSTRAINT Client_ProgrammeFidelite_FK FOREIGN KEY (idProgrammeFidelite) REFERENCES ProgrammeFidelite(idProgrammeFidelite)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: CatégorieVéhicule
#------------------------------------------------------------

CREATE TABLE CategorieVehicule(
        typeCategorie Varchar (50) NOT NULL ,
        caution       Decimal (10,2) NOT NULL ,
        tarifParJour  Decimal (10,2) NOT NULL
	,CONSTRAINT CategorieVehicule_PK PRIMARY KEY (typeCategorie)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Modèle
#------------------------------------------------------------

CREATE TABLE Modele(
        modele Varchar (50) NOT NULL ,
        marque Varchar (50) NOT NULL
	,CONSTRAINT Modele_PK PRIMARY KEY (modele)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Assurance
#------------------------------------------------------------

CREATE TABLE Assurance(
        type_assurance Varchar (5) NOT NULL ,
        prix_assurance Decimal (10,2) NOT NULL
	,CONSTRAINT Assurance_PK PRIMARY KEY (type_assurance)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: localisation
#------------------------------------------------------------

CREATE TABLE localisation(
        longitude  Float NOT NULL ,
        lattitude  Float NOT NULL ,
        rue        Varchar (50) NOT NULL ,
        ville      Varchar (50) NOT NULL ,
        codepostal Varchar (50) NOT NULL
	,CONSTRAINT localisation_PK PRIMARY KEY (longitude,lattitude)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Agence
#------------------------------------------------------------

CREATE TABLE Agence(
        idAgence        Int NOT NULL ,
        telephoneAgence Varchar (50) NOT NULL ,
        CapaciteMax     Int NOT NULL ,
        longitude       Float NOT NULL ,
        lattitude       Float NOT NULL
	,CONSTRAINT Agence_PK PRIMARY KEY (idAgence)

	,CONSTRAINT Agence_localisation_FK FOREIGN KEY (longitude,lattitude) REFERENCES localisation(longitude,lattitude)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Véhicule
#------------------------------------------------------------

CREATE TABLE Vehicule(
        matricule     Varchar (50) NOT NULL ,
        kilometrage   Int NOT NULL ,
        location      Bool NOT NULL ,
        boiteManuelle Bool NOT NULL ,
        climatise     Bool NOT NULL ,
        typeCarburant Varchar (50) NOT NULL ,
        typeCategorie Varchar (50) NOT NULL ,
        idAgence      Int NOT NULL ,
        modele        Varchar (50) NOT NULL
	,CONSTRAINT Vehicule_PK PRIMARY KEY (matricule)

	,CONSTRAINT Vehicule_CategorieVehicule_FK FOREIGN KEY (typeCategorie) REFERENCES CategorieVehicule(typeCategorie)
	,CONSTRAINT Vehicule_Agence0_FK FOREIGN KEY (idAgence) REFERENCES Agence(idAgence)
	,CONSTRAINT Vehicule_Modele1_FK FOREIGN KEY (modele) REFERENCES Modele(modele)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Employé
#------------------------------------------------------------

CREATE TABLE Employe(
        loginEmploye       Varchar (50) NOT NULL ,
        mdpEmploye         Varchar (50) NOT NULL ,
        nom_employe        Varchar (50) NOT NULL ,
        prenom_employe     Varchar (50) NOT NULL ,
        email_employe      Varchar (50) NOT NULL ,
        rue_employe        Varchar (50) NOT NULL ,
        ville_employe      Varchar (50) NOT NULL ,
        codePostal_employe Int NOT NULL ,
        numTel_employe     Varchar (50) NOT NULL ,
        idAgence           Int NOT NULL
	,CONSTRAINT Employe_PK PRIMARY KEY (loginEmploye)

	,CONSTRAINT Employe_Agence_FK FOREIGN KEY (idAgence) REFERENCES Agence(idAgence)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Loue
#------------------------------------------------------------

CREATE TABLE Loue(
        idClient           Int NOT NULL ,
        matricule          Varchar (50) NOT NULL ,
        type_assurance     Varchar (5) DEFAULT NULL,
        date_location      Date NOT NULL ,
        date_reservation   Date DEFAULT NULL,
        duree_location     Int NOT NULL ,
        frais_dommage      Decimal (10,2) DEFAULT NULL ,
        etat_vehicule      Varchar (50) DEFAULT NULL,
        niveau_essence_fin Float DEFAULT NULL,
        location_en_cours  boolean NOT NULL DEFAULT FALSE
	,CONSTRAINT Loue_PK PRIMARY KEY (idClient,matricule,date_location)

	,CONSTRAINT Loue_Client_FK FOREIGN KEY (idClient) REFERENCES Client(idClient)
	,CONSTRAINT Loue_Vehicule0_FK FOREIGN KEY (matricule) REFERENCES Vehicule(matricule)
	,CONSTRAINT Loue_Assurance1_FK FOREIGN KEY (type_assurance) REFERENCES Assurance(type_assurance)
)ENGINE=InnoDB;

-- Création des 3 catégories
INSERT INTO CategorieVehicule (typeCategorie, caution, tarifParJour)
VALUES ('Luxe', 1500, 500);
INSERT INTO CategorieVehicule (typeCategorie, caution, tarifParJour)
VALUES ('Confort', 750, 250);
INSERT INTO CategorieVehicule (typeCategorie, caution, tarifParJour)
VALUES ('Economique', 300, 100);

-- Agence n° 1
INSERT INTO localisation (longitude, lattitude, rue, ville, codepostal)
VALUES (2.3637654717477607, 48.788803409876515, '34 Avenue de la République', 'Villejuif', '94800');

INSERT INTO agence (idAgence, telephoneAgence, CapaciteMax, longitude, lattitude)
VALUES (1, 0304050607, 50, 2.3637654717477607, 48.788803409876515);

-- Agence n° 2
INSERT INTO localisation (longitude, lattitude, rue, ville, codepostal)
VALUES (-0.37359023469940134, 49.180971754459094, '8 Impasse Duc Rollon', 'Caen', '14000');

INSERT INTO agence (idAgence, telephoneAgence, CapaciteMax, longitude, lattitude)
VALUES (2, 0203040506, 20, -0.37359023469940134, 49.180971754459094);

-- Agence n° 3
INSERT INTO localisation (longitude, lattitude, rue, ville, codepostal)
VALUES (7.750873419410569, 48.58172484710157, 'Place de la Cathédrale', 'Strasbourg', '67000');

INSERT INTO agence (idAgence, telephoneAgence, CapaciteMax, longitude, lattitude)
VALUES (3, 0102030405, 10, 7.750873419410569, 48.58172484710157);

-- Employe n° 1 Agence n° 1 Vrai mdp : bob
INSERT INTO employe (loginEmploye, mdpEmploye, nom_employe,
                     prenom_employe, email_employe, rue_employe, ville_employe, codePostal_employe, numTel_employe, idAgence)
VALUES ('Bob','eFeaUvBJpKvyX/1yLb2SnbkmzMX8P6LPLG3Oh3/CAwY=','Bob','Bobby','bob.bobby@bob.bobby','17 rue des Bob','BobLand',
        '94370','0102030405',1);

-- Employe n° 2 Agence n° 1 Vrai mdp : aucune idée
INSERT INTO employe (loginEmploye, mdpEmploye, nom_employe,
                     prenom_employe, email_employe, rue_employe, ville_employe, codePostal_employe, numTel_employe, idAgence)
VALUES ('Abdelkader','RJOMGcE0O33trLWXkSwIy85IxQ7X5L6ZyQHynCysruI=','Machoui','Abdelkader','abdelkader.machoui@gmail.com','25 rue des Anneaux','Villejuif',
        '94440','0203040506',1);

-- Employe n° 1 Agence n° 2 Vrai mdp : bib
INSERT INTO employe (loginEmploye, mdpEmploye, nom_employe,
                     prenom_employe, email_employe, rue_employe, ville_employe, codePostal_employe, numTel_employe, idAgence)
VALUES ('Bib','mw2s8pFaguJzZscGALnGvKm2HCe3E3T0GcJ1hafVfA4=','Bib','Bibby','bib.bibby@bib.bibby','17 rue des Bib','BibLand',
        '94370','0102030405',2);

-- Employe n° 2 Agence n° 2 Vrai mdp : Zoulette
INSERT INTO employe (loginEmploye, mdpEmploye, nom_employe,
                     prenom_employe, email_employe, rue_employe, ville_employe, codePostal_employe, numTel_employe, idAgence)
VALUES ('Reycun','qfN8CVlFxuaD2biDDOaI1JXj0kDZRlzJVBgcYt/VKN8=','Hanania','Matthieu','matthieu.hanania@gmail.com','17 rue des Jean','Vanves',
        '92170','0203040506',2);

-- Employe n° 1 Agence n° 3 Vrai mdp : Bub
INSERT INTO employe (loginEmploye, mdpEmploye, nom_employe,
                     prenom_employe, email_employe, rue_employe, ville_employe, codePostal_employe, numTel_employe, idAgence)
VALUES ('Bub','339qbCZe0nXrqZy7PfgKOxaDOLyeTCP0ha4bQURHCfo=','Bub','Bubby','bub.bubby@bub.bubby','17 rue des Bub','BubLand',
        '94370','0102030405',3);

-- Voiture 1
INSERT INTO modele (modele, marque)
VALUES ('Multipla','Fiat');
INSERT INTO vehicule (matricule, kilometrage, location, boiteManuelle, climatise, typeCarburant, typeCategorie, idAgence, modele)
VALUES ('123-ABC-456', 12345, false, true, true, 'Essence', 'Economique', 1, 'Multipla');

-- voiture 2
INSERT INTO modele (modele, marque)
VALUES ('Megan','Renault');
INSERT INTO vehicule (matricule, kilometrage, location, boiteManuelle, climatise, typeCarburant, typeCategorie, idAgence, modele)
VALUES ('123-XYZ-900', 120000, false, false, true, 'Diesel', 'Confort', 3, 'Megan');

-- voiture 3
INSERT INTO modele (modele, marque)
VALUES ('Aventador','Lamborghini');
INSERT INTO vehicule (matricule, kilometrage, location, boiteManuelle, climatise, typeCarburant, typeCategorie, idAgence, modele)
VALUES ('353-FDL-902', 120, false, true, true, 'Electrique', 'Luxe', 2, 'Aventador');

-- voiture 1
INSERT INTO modele (modele, marque)
VALUES ('Rino','Chevrolet');
INSERT INTO vehicule (matricule, kilometrage, location, boiteManuelle, climatise, typeCarburant, typeCategorie, idAgence, modele)
VALUES ('1A3-ZBC-879', 12987, false, true, false, 'Diesel', 'Luxe', 2, 'Rino');

-- voiture 2
INSERT INTO modele (modele, marque)
VALUES ('Corsa','Opel');
INSERT INTO vehicule (matricule, kilometrage, location, boiteManuelle, climatise, typeCarburant, typeCategorie, idAgence, modele)
VALUES ('113-DFD-902', 120000, false, false, false, 'Essence', 'Confort', 3, 'Corsa');

-- voiture 3
INSERT INTO modele (modele, marque)
VALUES ('206','Renault');
INSERT INTO vehicule (matricule, kilometrage, location, boiteManuelle, climatise, typeCarburant, typeCategorie, idAgence, modele)
VALUES ('313-ADL-932', 120, false, true, true, 'Diesel', 'Economique', 1, '206');

-- Assurance 0
INSERT INTO Assurance(type_assurance, prix_assurance)
VALUES ('0', 0);

-- Assurance 1
INSERT INTO Assurance(type_assurance, prix_assurance)
VALUES ('1', 100);

-- Assurance 2
INSERT INTO Assurance(type_assurance, prix_assurance)
VALUES ('2', 250);

-- Assurance 3
INSERT INTO Assurance(type_assurance, prix_assurance)
VALUES ('3', 500);

-- ProgFidel 0
INSERT INTO ProgrammeFidelite(idProgrammeFidelite, description, prix, tauxReduc, dateSouscription)
VALUES ('0', 'Aucune Souscription du client', '0', '0', '2021-01-01');

-- ProgFidel 1
INSERT INTO ProgrammeFidelite(idProgrammeFidelite, description, prix, tauxReduc, dateSouscription)
VALUES ('1', 'Super Programme', '100', '0.1', '2021-05-31');

-- ProgFidel 2
INSERT INTO ProgrammeFidelite(idProgrammeFidelite, description, prix, tauxReduc, dateSouscription)
VALUES ('2', 'Super Mega Programme', '200', '0.2', '2021-05-31');

-- ProgFidel 2
INSERT INTO ProgrammeFidelite(idProgrammeFidelite, description, prix, tauxReduc, dateSouscription)
VALUES ('3', 'Super Mega Ultra Programme', '200', '0.2', '2021-05-31');

-- Client 1
INSERT INTO client (idClient, nom_client, prenom_client, email_client, rue_client, ville_client, codePostal_client, numTel_client, dureeDuProgramme, idProgrammeFidelite)
VALUES ('1','Fievet','Tristan','tristan.fievet@gmail.com','16 rue des faneuses','Marolles-en-brie','94440','0102030405', 0, 0);

-- Client 2
INSERT INTO client (idClient, nom_client, prenom_client, email_client, rue_client, ville_client, codePostal_client, numTel_client, dureeDuProgramme, idProgrammeFidelite)
VALUES ('2','Hanania','Matthieu','matthieu.hanania@gmail.com','17 rue des Jean','Vanves','92170','0102030405', 0, 0);

-- Client 3
INSERT INTO client (idClient, nom_client, prenom_client, email_client, rue_client, ville_client, codePostal_client, numTel_client, dureeDuProgramme, idProgrammeFidelite)
VALUES ('3','Tazi','Kenza','kenza.tazi@gmail.com','16 rue du bled','Bled','100000','0102030405', 0, 0);

-- Client 4
INSERT INTO client (idClient, nom_client, prenom_client, email_client, rue_client, ville_client, codePostal_client, numTel_client, dureeDuProgramme, idProgrammeFidelite)
VALUES ('4','Poncin','Severin','severin.poncin@crackito.com','25 rue des cracks','CrackLand','65830','0102030405', 0, 0);



