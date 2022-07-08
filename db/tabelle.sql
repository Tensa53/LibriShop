-- In questo file inseriremo tutte le istruzioni relative alla creazione del database e delle relative tabelle

DROP SCHEMA IF EXISTS base;
CREATE SCHEMA base;
USE base;

-- tabelle indipendenti

CREATE TABLE Libro (
                       ISBN char(13) primary key,
                       Titolo varchar(30) not null, /*nel titolo viene eventualmente specificata l'edizione*/
                       Prezzo decimal(5,2) not null,
                       DataPubblicazione date not null,
                       Editore varchar(20) not null,
                       Sconto decimal(2,0) not null,
                       Disponibilita int not null,
                       Foto varchar(50) not null,
                       Descrizione text not null
);

CREATE TABLE Genere (
    Nome varchar(20) primary key
);

CREATE TABLE Autore (
                        CF char(16) primary key,
                        Nome varchar(40) not null
);

CREATE TABLE Utente (
                        Email varchar(30) primary key,
                        Username varchar(20) unique,
                        Nome varchar(20) not null,
                        Cognome varchar(20) not null,
                        PasswordHash varchar(40) not null,
                        Amministratore boolean not null
);

CREATE TABLE Indirizzo (
                           Via varchar(40) not null,
                           Civico varchar(5) not null,
                           Citta varchar(50) not null,
                           CAP char(5) not null,
                           Provincia varchar(30) not null,
                           Utente varchar(30) not null,
                           primary key(Via,Civico,Citta,Utente)
);

CREATE TABLE Pagamento (
                           NumeroCarta varchar(16) not null,
                           Scadenza date not null,
                           CCV char(3) not null,
                           Utente varchar(30) not null,
                           primary key(NumeroCarta,Utente)
);

-- tabelle dipendenti

CREATE TABLE Appartenenza (
                              ISBNLibro char(13),
                              Genere varchar(20),
                              primary key(ISBNLibro,Genere),
                              foreign key (ISBNLibro) references Libro(ISBN),
                              foreign key (Genere) references Genere(Nome)
);

CREATE TABLE Scrittura (
                           ISBNLibro char(13),
                           Autore char(16),
                           primary key(ISBNLibro,Autore),
                           foreign key(ISBNLibro) references Libro(ISBN),
                           foreign key(Autore) references Autore(CF)
);

CREATE TABLE Ordine(
                       ID int primary key auto_increment,
                       DataOrdine timestamp not null,
                       Via varchar(40) not null,
                       Numero varchar(5) not null,
                       CAP char(5) not null,
                       Citta varchar(50) not null,
                       Provincia varchar(30) not null,
                       NumeroCarta varchar(16) not null,
                       Scadenza date not null,
                       CCV char(3) not null,
                       Totale decimal(6,2) not null,
                       Utente varchar(30) not null,
                       foreign key(Utente) references Utente(Email)
);

CREATE TABLE Carrello (
                          Utente varchar(30) not null,
                          Totale decimal(6,2) not null,
                          foreign key(Utente) references Utente(Email)
);

CREATE TABLE Dettaglio (
                           ID int primary key auto_increment,
                           Quantita int not null,
                           Prezzo decimal(6,2) not null,
                           Carrello varchar(20),
                           Ordine int,
                           ISBNLibro varchar(13) not null,
                           TitoloLibro varchar(30) not null,
                           foreign key (ISBNLibro) references Libro(ISBN),
                           foreign key (Carrello) references Carrello(Utente),
                           foreign key (Ordine) references Ordine(ID)
);