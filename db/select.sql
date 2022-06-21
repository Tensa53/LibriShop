/*in questo file sono presenti alcune query sql che permettono di avere facilmente una vista completa del database*/
use base;

SELECT * FROM Libro;

SELECT L.Titolo,L.ISBN,G.Nome FROM Appartenenza A,Libro L,Genere G WHERE (A.Genere = G.Nome AND L.ISBN = A.ISBNLibro);

SELECT * FROM Genere;

SELECT L.Titolo,L.ISBN,A.Nome FROM Autore A,Libro L,Scrittura S WHERE (A.CF = S.Autore AND L.ISBN = S.ISBNLibro);

SELECT * FROM Autore;

SELECT * FROM Utente;

SELECT U.Email,I.Via,I.Civico,I.CAP FROM Utente U,Dichiarazione D,Indirizzo I WHERE (U.Email = D.Utente AND I.Via = D.IndirizzoVia AND I.Civico = D.IndirizzoNumero AND I.CAP = D.IndirizzoCAP);

SELECT * FROM Indirizzo;

SELECT U.Email,P.NumeroCarta FROM Utente U,Definizione D,Pagamento P WHERE (D.Utente = U.Email AND P.NumeroCarta = D.Pagamento);

SELECT * FROM Pagamento;

SELECT * FROM Dettaglio;

SELECT * FROM Carrello;

SELECT * FROM Ordine;