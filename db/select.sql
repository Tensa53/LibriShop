/*in questo file sono presenti alcune query sql che permettono di avere facilmente una vista completa del database*/
use base;

SELECT * FROM Libro;

SELECT L.Titolo,L.ISBN,G.Nome FROM Appartenenza A,Libro L,Genere G WHERE (A.Genere = G.Nome AND L.ISBN = A.ISBNLibro);

SELECT * FROM Genere;

SELECT L.Titolo,L.ISBN,A.Nome FROM Autore A,Libro L,Scrittura S WHERE (A.CF = S.Autore AND L.ISBN = S.ISBNLibro);

SELECT * FROM Autore;

SELECT * FROM Utente;

SELECT * FROM Indirizzo;

SELECT * FROM Pagamento;

SELECT * FROM Dettaglio;

SELECT * FROM Carrello;

SELECT * FROM Ordine;