-- In questo file inseriremo tutte le interrogazioni al database che ci saranno poi utili nei relativi model

-- Seleziona l'utente con relativa mail e password (ci serve per il login)
SELECT * FROM Utente WHERE Email=? AND Passwordhash=SHA1(?);

-- Seleziona tutti gli autori
SELECT * FROM Autore;

-- Seleziona il carrello per utente
SELECT * FROM Carrello WHERE Utente = ?;

-- Aggiorna il totale del carrello del relativo utente
UPDATE Carrello SET Totale = ? WHERE Utente = ?;

-- Seleziona tutti i dettagli
SELECT * FROM Dettaglio;

-- Rimuovi i dettagli dallo specifico carrello
DELETE FROM Dettaglio WHERE Carrello = ?;

-- Seleziona i dettagli del relativo carrello
SELECT * FROM Dettaglio WHERE Carrello = ?;

-- Seleziona il libro per genere
SELECT *
FROM Libro L, Genere G, Appartenenza A
WHERE (A.IsbnLibro = L.ISBN AND A.Genere = G.Nome) AND G.Nome = ?;

-- Componi il prodotto del Carrello
SELECT L.Disponibilita,L.Nome,D.Quantita,D.Prezzo,sum(D.Prezzo)
FROM Libro L,Dettaglio D,Carrello C
WHERE Dettaglio.ISBNLibro = L.ISBN AND D.Carrello = C.ID
GROUP BY (L.Disponibilita,L.Nome,D.Quantita,D.Prezzo,sum(D.Prezzo),C.ID);

-- Seleziona tutti i libri
SELECT *
FROM Libro; -- usare LIMIT per restringere le righe di output

-- Ricerca un libro che contiene la seguente stringa
SELECT * FROM Libro
WHERE Titolo LIKE '?%' OR Titolo LIKE '%?' OR Titolo LIKE '%?%';

-- Rimozione di un libro dal db (ci servirà nella parte admin)
DELETE FROM Appartenenza WHERE ISBNLibro = ?; -- cancellando il libro dal db le informazioni di genere non saranno più utili

DELETE FROM Scrittura WHERE ISBNLibro = ?; -- cancellando il libro dal db le informazioni sull'autore non saranno più utili

SET foreign_key_checks = 0; -- per preservare l'integrità degli eventuali dettagli di acquisto legati al libro, dobbiamo disattivare il controllo delle chiavi esterne

DELETE FROM Libro WHERE ISBN = ?;

SET foreign_key_checks = 1; -- dopo aver eliminato il libro possiamo riattivare il controllo delle chiavi esterne