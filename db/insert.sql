/* In questo file inseriremo tutte le istruzioni relative all'inserimento dei record nelle varie tabelle,così da poter
   facilmente ripopolare il database sulle diverse macchine utilizzate
 */

use base;

INSERT INTO Libro VALUES
                      ('9788883379529', 'Le notti bianche', 'Le notti bianche è un racconto giovanile di Dostoevskij, pubblicato nel 1848 sulla rivista Annali patrii. Il titolo prende spunto dal periodo estivo in cui, nella Russia del nord, il sole tramonta dopo le dieci.', 4.90, '2021-04-30', 'Crescere', 5, 40, './img/LeNottiBianche.jpg'),
                      ('9788868361297', 'L’acchiappasogni', 'Tempo fa, a Derry, quattro ragazzini coraggiosi compirono una buona azione. Che li trasformò per sempre. Da grandi, Henry, Jonesy, Beav e Pete hanno preso strade diverse, ma due cose hanno mantenuto un richiamo irresistibile: una è il legame con il bambino molto, molto particolare che aiutarono quel giorno lontano e l’altra è la fantastica battuta di caccia al cervo che ogni anno li riunisce nel Maine, là nella baita dove ondeggia quel curioso oggetto indiano chiamato acchiappasogni.', 12.90, '2013-11-04', 'Sperling & Kufer', 5, 35, './img/LAcchiappasogni.jpg'),
                      ('9788868362041', 'Cujo', 'A Castle Rock, una sonnolenta cittadina del Maine, la vita scorre sui soliti binari. Cujo, il docile San Bernardo del meccanico, scorrazza libero per la campagna, finché una notte il suo padroncino, aprendo la porta del ripostiglio, non vede emergere dalle tenebre due occhi infuocati. Chi è la creatura diabolica che da quel momento comincia a seminare ovunque terrore e desolazione? È forse Cujo che, diventato idrofobo, si è trasformato nell’incarnazione stessa del male?.', 10.90, '2014-05-10', 'Sperling & Kufer', 0, 20, './img/Cujo.jpg'),
                      ('9788834009086', 'Scrivere zen', 'Con intelligenza, umorismo e affettuoso senso pratico, Natalie Goldberg esorta tutti coloro che non scrivono, e anche quelli che già lo fanno, a prendere in mano la matita o il pennarello, la biro o la stilografica, la macchina da scrivere o, perché no, il ‘personal’, e a scrivere.', 16, '1987-12-15', 'Astrolabio Ubaldini', 5, 15, './img/ScrivereZen.jpg'),
                      ('9788804735144', 'La casa sul mare celeste', 'Linus Baker è un assistente sociale impiegato al Dipartimento della Magia Minorile. Il compito che esegue con scrupolosa professionalità è assicurarsi che i bambini dotati di poteri magici, cresciuti in appositi istituti in modo da proteggere quelli "normali", siano ben accuditi. Tutto cambia quando, inaspettatamente, viene convocato nell’ufficio della Suprema Dirigenza.', 18, '2021-07-13', 'Mondadori', 10, 30, './img/LaCasa.jpg'),
                      ('9788804750185', 'Ancora una fermata', 'August Landry ha ventitré anni e ha trascorso gli ultimi cinque spostandosi da una città – e università – a un’altra. Cinica e disincantata, non si fida di nessuno e porta sempre con sé un coltellino svizzero perché, come le ha insegnato sua madre, “è meglio non farsi cogliere impreparate”. Quando decide di trasferirsi a New York, non ha grandi aspettative.', 19, '2022-06-07', 'Mondadori', 20, 50, './img/AncoraUnaFermata.jpg'),
                      ('9788845930959', 'L’incubo di Hill House', 'Chiunque abbia visto qualche film del terrore con al centro una costruzione abitata da sinistre presenze si sarà trovato a chiedersi almeno una volta perché le vittime di turno non optino, prima che sia troppo tardi, per la soluzione più semplice - e cioè non escano dalla stessa porta dalla quale sono entrati, allontanandosi senza voltarsi indietro. A tale domanda, meno oziosa di quanto potrebbe parere, questo romanzo fornisce una risposta.', 12, '2016-07-03', 'Adelphi', 0, 20, './img/HillHouse.jpg'),
                      ('9788845921841', 'La lotteria', 'La storia si presenta in tutta innocenza quale pura e semplice descrizione della lotteria che si svolge nell’atmosfera pastorale, quasi idilliaca, di un villaggio del New England in un luminoso mattino di giugno, come ogni anno da tempo immemore.', 10, '2007-05-16', 'Adelphi', 10, 15, './img/Lotteria.jpg');

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
                       ('DSTFDR80A01F839G','Fëdor','Dostoevskij'),
                       ('STPKNG80A01F839I','Stephen','King'),
                       ('GLDNTL80A41F839N','Natalie', 'Goldberg'),
                       ('KLNTLR80A01F839L','TJ', 'Klune'),
                       ('MCQCSY80A41F839G','Casey', 'McQuiston'),
                       ('JCKSRL80A41F839E','Shirley', 'Jackson');


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
                       ('mariorossi@gmail.com','mariorossi','Mario','Rossi','7C4A8D09CA3762AF61E59520943DC26494F8941B'),
                       ('lindabianchi@libero.it','lindabianchi','Linda','Bianchi','1F6CCD2BE75F1CC94A22A773EEA8F8AEB5C68217'),
                       ('lucaruocco@hotmail.it','lucaruocco','Luca','Ruocco','431ACC6DBC62501D4D0A67A9515A2D4C4B909E64'),
                       ('rosagiudice@gmail.com','rosagiudice','Rosa','Giudice','6F47C44AD9FF9D642008B84406E95D2E70D324F1'),
                       ('danielefabiano@hotmail.it','danielefabiano','Daniele','Fabiano','9C943C55A3D140756BB1AB6088629908C2FB21E5');

INSERT INTO Indirizzo VALUES
                          ("Roma","23","84028","Serre","SA","Italia"),
                          ("Giuseppe Ungaretti","112","20066","Melzo","MI","Italia"),
                          ("Altabella","2","40126","Bologna","BO","Italia"),
                          ("Borgo dell’Oro","56","10094","Torino","TO","Italia"),
                          ("Mandria","76","84121","Salerno","SA","Italia"),
                          ("Miramonte","32","50014","Fiesole","FI","Italia"),
                          ("Savonella","24","70011","Alberobello","BA","Italia");

INSERT INTO Dichiarazione VALUES
                              ('mariorossi@gmail.com','Roma','23','84028'),
                              ('lindabianchi@libero.it','Giuseppe Ungaretti','112','20066'),
                              ('lucaruocco@hotmail.it','Altabella','2','40126'),
                              ('lucaruocco@hotmail.it','Savonella','24','70011'),
                              ('rosagiudice@gmail.com','Borgo dell’Oro','56','10094'),
                              ('rosagiudice@gmail.com','Miramonte','32','50014'),
                              ('danielefabiano@hotmail.it','Mandria','76','84121');

INSERT INTO Pagamento VALUES
                          ('4716019594082932', '2025-12-01', '176'),
                          ('4532010684511437', '2028-12-01', '335'),
                          ('4716933961025409', '2026-12-01', '874'),
                          ('4929409682314169', '2030-12-01', '216'),
                          ('4410923939558010', '2030-12-01', '479'),
                          ('4556005537679432', '2029-12-01', '893');

INSERT INTO Definizione VALUES
                            ('mariorossi@gmail.com','4716019594082932'),
                            ('lindabianchi@libero.it','4532010684511437'),
                            ('lucaruocco@hotmail.it','4716933961025409'),
                            ('lucaruocco@hotmail.it','4929409682314169'),
                            ('rosagiudice@gmail.com','4410923939558010'),
                            ('danielefabiano@hotmail.it','4556005537679432');
