package com.example.progettotsw.controller;

import com.example.progettotsw.model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/aggiungi-al-carrello")
public class AddLibroCarrelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if((utente != null && !utente.isAmministratore()) || utente == null){ //possono accedere al carrello solamente gli utenti guest e utenti registrati di livello cliente
            String isbn = request.getParameter("isbn");

            int quantita = Integer.parseInt(request.getParameter("quantita"));

            LibroDAO libroDAO = new LibroDAO();

            Libro libro = libroDAO.doRetrieveById(isbn);

            Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

            Dettaglio d;

            if (carrello == null)//nel caso un utente guest acceda direttamente alla pagina del libro da un link, non avremo nessun carrello istanziato
                carrello = new Carrello();//inizializzazione del carrello

            if (carrello.getTotale() > 0.0f && (d = carrello.getDettagliobyISBN(isbn)) != null) { //carrello non vuoto e libro già esistente
                d.setQuantita(d.getQuantita() + quantita);//sommiamo la quantità già esistente e la quantità aggiuntiva
                d.setPrezzo(d.getQuantita() * d.getLibro().getPrezzo());//aggiorniamo il prezzo del dettaglio
                carrello.setTotale(carrello.getTotale() + d.getPrezzo());//aggiorniamo il prezzo del carrello
            } else if (carrello.getTotale() > 0.0f || carrello.getTotale() == 0.0f) { //nel caso invece il carrello sia vuoto oppure non vuoto ma quel libro non è stato già aggiunto
                d = new Dettaglio(quantita, libro.getPrezzo() * quantita, libro);//creo un nuovo dettaglio
                carrello.addDettaglio(d);//aggiungo il nuovo dettaglio al carrello
                carrello.setTotale(carrello.getTotale() + d.getPrezzo());//aggiorno il totale del carrello
            }

            request.getSession().removeAttribute("carrello");
            request.getSession().setAttribute("carrello",carrello);
        }

        String address = "http://localhost:8080/progettoTSW_war_exploded/home";

        response.sendRedirect(address);//col sendRedirect la request non sarà condivisa ma ricreata dalla servlet/jsp richiamata
    }

}
