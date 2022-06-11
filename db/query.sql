-- In questo file inseriremo tutte le interrogazioni al database che ci saranno poi utili nei relativi model

-- Seleziona tutti i libri
SELECT *
FROM Libro; -- usare LIMIT per restringere le righe di output

-- Seleziona il libro per genere
SELECT *
FROM Libro L, Genere G, Appartenenza A
WHERE (A.IsbnLibro = L.ISBN AND A.Genere = G.Nome) AND G.Nome = 'Inserisci nome genere';

-- Componi il prodotto del Carrello
SELECT L.Disponibilita,L.Nome,D.Quantita,D.Prezzo,sum(D.Prezzo)
FROM Libro L,Dettaglio D,Carrello C
WHERE Dettaglio.ISBNLibro = L.ISBN AND D.Carrello = C.ID
GROUP BY (L.Disponibilita,L.Nome,D.Quantita,D.Prezzo,sum(D.Prezzo),C.ID);

-- Ricerca un libro che contiene la seguente stringa
SELECT * FROM Libro
WHERE Titolo LIKE 'UnaQualunqueStringa%' OR Titolo LIKE '%UnaQualunqueStringa' OR Titolo LIKE '%UnaQualunqueStringa%'