use base;

INSERT INTO Libro VALUES
                      ('9788883379529', 'Le notti bianche',4.90, '2021-04-30', 'Crescere', 5, 40, './img/LeNottiBianche.jpg','Le notti bianche è un racconto giovanile di Dostoevskij, pubblicato nel 1848 sulla rivista Annali patrii. Il titolo prende spunto dal periodo estivo in cui, nella Russia del nord, il sole tramonta dopo le dieci.'),
                      ('9788868361297', 'L’acchiappasogni',12.90, '2013-11-04', 'Sperling & Kufer', 5, 35, './img/LAcchiappasogni.jpg', 'Tempo fa, a Derry, quattro ragazzini coraggiosi compirono una buona azione. Che li trasformò per sempre. Da grandi, Henry, Jonesy, Beav e Pete hanno preso strade diverse, ma due cose hanno mantenuto un richiamo irresistibile: una è il legame con il bambino molto, molto particolare che aiutarono quel giorno lontano e l’altra è la fantastica battuta di caccia al cervo che ogni anno li riunisce nel Maine, là nella baita dove ondeggia quel curioso oggetto indiano chiamato acchiappasogni.'),
                      ('9788868362041', 'Cujo',10.90, '2014-05-10', 'Sperling & Kufer', 0, 20, './img/Cujo.jpg',  'A Castle Rock, una sonnolenta cittadina del Maine, la vita scorre sui soliti binari. Cujo, il docile San Bernardo del meccanico, scorrazza libero per la campagna, finché una notte il suo padroncino, aprendo la porta del ripostiglio, non vede emergere dalle tenebre due occhi infuocati. Chi è la creatura diabolica che da quel momento comincia a seminare ovunque terrore e desolazione? È forse Cujo che, diventato idrofobo, si è trasformato nell’incarnazione stessa del male?.'),
                      ('9788834009086', 'Scrivere zen',16.00, '1987-12-15', 'Astrolabio Ubaldini', 5, 15, './img/ScrivereZen.jpg', 'Con intelligenza, umorismo e affettuoso senso pratico, Natalie Goldberg esorta tutti coloro che non scrivono, e anche quelli che già lo fanno, a prendere in mano la matita o il pennarello, la biro o la stilografica, la macchina da scrivere o, perché no, il ‘personal’, e a scrivere.'),
                      ('9788804735144', 'La casa sul mare celeste',18.00, '2021-07-13', 'Mondadori', 10, 30, './img/LaCasa.jpg', 'Linus Baker è un assistente sociale impiegato al Dipartimento della Magia Minorile. Il compito che esegue con scrupolosa professionalità è assicurarsi che i bambini dotati di poteri magici, cresciuti in appositi istituti in modo da proteggere quelli "normali", siano ben accuditi. Tutto cambia quando, inaspettatamente, viene convocato nell’ufficio della Suprema Dirigenza.'),
                      ('9788804750185', 'Ancora una fermata',19.00, '2022-06-07', 'Mondadori', 20, 50, './img/AncoraUnaFermata.jpg', 'August Landry ha ventitré anni e ha trascorso gli ultimi cinque spostandosi da una città – e università – a un’altra. Cinica e disincantata, non si fida di nessuno e porta sempre con sé un coltellino svizzero perché, come le ha insegnato sua madre, “è meglio non farsi cogliere impreparate”. Quando decide di trasferirsi a New York, non ha grandi aspettative.'),
                      ('9788845930959', 'L’incubo di Hill House',12.00, '2016-07-03', 'Adelphi', 0, 20, './img/HillHouse.jpg', 'Chiunque abbia visto qualche film del terrore con al centro una costruzione abitata da sinistre presenze si sarà trovato a chiedersi almeno una volta perché le vittime di turno non optino, prima che sia troppo tardi, per la soluzione più semplice - e cioè non escano dalla stessa porta dalla quale sono entrati, allontanandosi senza voltarsi indietro. A tale domanda, meno oziosa di quanto potrebbe parere, questo romanzo fornisce una risposta.'),
                      ('9788845921841', 'La lotteria',10.00, '2007-05-16', 'Adelphi', 10, 15, './img/Lotteria.jpg', 'La storia si presenta in tutta innocenza quale pura e semplice descrizione della lotteria che si svolge nell’atmosfera pastorale, quasi idilliaca, di un villaggio del New England in un luminoso mattino di giugno, come ogni anno da tempo immemore.');

INSERT INTO Genere VALUES
                       ('Narrativa'),
                       ('Horror'),
                       ('Dramma'),
                       ('Fantascienza'),
                       ('Thriller'),
                       ('Autoaiuto'),
                       ('Fantasy'),
                       ('Rosa');

INSERT INTO Appartenenza VALUES
                             ('9788883379529','Narrativa'),
                             ('9788868361297','Horror'),
                             ('9788868361297','Dramma'),
                             ('9788868361297','Fantascienza'),
                             ('9788868361297','Thriller'),
                             ('9788868362041','Horror'),
                             ('9788868362041','Dramma'),
                             ('9788834009086','Autoaiuto'),
                             ('9788804735144','Narrativa'),
                             ('9788804735144','Fantasy'),
                             ('9788804750185','Rosa'),
                             ('9788845930959','Horror'),
                             ('9788845930959','Narrativa');

INSERT INTO Autore VALUES
                       ('DSTFDR80A01F839G','Fëdor Dostoevskij'),
                       ('STPKNG80A01F839I','Stephen King'),
                       ('GLDNTL80A41F839N','Natalie Goldberg'),
                       ('KLNTLR80A01F839L','TJ Klune'),
                       ('MCQCSY80A41F839G','Casey McQuiston'),
                       ('JCKSRL80A41F839E','Shirley Jackson');


INSERT INTO Scrittura VALUES
                          ('9788883379529','DSTFDR80A01F839G'),
                          ('9788868361297','STPKNG80A01F839I'),
                          ('9788868362041','STPKNG80A01F839I'),
                          ('9788834009086','GLDNTL80A41F839N'),
                          ('9788804735144','KLNTLR80A01F839L'),
                          ('9788804750185','MCQCSY80A41F839G'),
                          ('9788845930959','JCKSRL80A41F839E'),
                          ('9788845921841','JCKSRL80A41F839E');

INSERT INTO Utente VALUES
                       ('mariorossi@gmail.com','Mario','Rossi','7C4A8D09CA3762AF61E59520943DC26494F8941B',false),
                       ('lindabianchi@libero.it','Linda','Bianchi','1F6CCD2BE75F1CC94A22A773EEA8F8AEB5C68217',false),
                       ('lucaruocco@hotmail.it','Luca','Ruocco','431ACC6DBC62501D4D0A67A9515A2D4C4B909E64',false),
                       ('rosagiudice@gmail.com','Rosa','Giudice','6F47C44AD9FF9D642008B84406E95D2E70D324F1',false),
                       ('danielefabiano@hotmail.it','Daniele','Fabiano','9C943C55A3D140756BB1AB6088629908C2FB21E5',true),
                       ('marymaselli@libero.it','Mariantonietta','Maselli','7BEF225F10720B39E2645C72B96CEA9ED83CB8C4',true);

INSERT INTO Indirizzo VALUES
                          ("Roma","23","Serre","84028","Salerno",'mariorossi@gmail.com'),
                          ("Giuseppe Ungaretti","112","Melzo","20066","Milano",'lindabianchi@libero.it'),
                          ("Altabella","2","Bologna","40126","Bologna",'lucaruocco@hotmail.it'),
                          ("Borgo dell’Oro","56","Torino","10094","Torino",'lucaruocco@hotmail.it'),
                          ("Miramonte","32","Fiesole","50014","Firenze",'rosagiudice@gmail.com'),
                          ("Savonella","24","Alberobello","70011","Bari",'rosagiudice@gmail.com');

INSERT INTO Pagamento VALUES
                          ('4716019594082932', '2025-12-01', '176','mariorossi@gmail.com'),
                          ('4532010684511437', '2028-12-01', '335','lindabianchi@libero.it'),
                          ('4716933961025409', '2026-12-01', '874','lucaruocco@hotmail.it'),
                          ('4929409682314169', '2030-12-01', '216','lucaruocco@hotmail.it'),
                          ('4410923939558010', '2030-12-01', '479','rosagiudice@gmail.com');


INSERT INTO Carrello (Utente,Totale) VALUES
                                         ('mariorossi@gmail.com',0.0),
                                         ('lindabianchi@libero.it',0.0),
                                         ('lucaruocco@hotmail.it',0.0),
                                         ('rosagiudice@gmail.com',0.0);