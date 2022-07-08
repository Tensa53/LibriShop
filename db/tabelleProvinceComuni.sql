CREATE TABLE Provincia (
                                        id int(11) NOT NULL auto_increment,
                                        nome varchar(100) NOT NULL,
                                        PRIMARY KEY (id)
);

CREATE TABLE Comune (
                                      id int(11) NOT NULL auto_increment,
                                      nome varchar(100) NOT NULL,
                                      id_provincia int(11) NOT NULL,
                                      PRIMARY KEY (id),
                                      FOREIGN KEY (id_provincia) REFERENCES Provincia(id)
);