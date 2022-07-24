package com.example.progettotsw.controller.carrello;

import com.example.progettotsw.model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/aggiungi-al-carrello")
public class AddLibroCarrelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if(utente == null || (utente != null && !utente.isAmministratore())){ //possono accedere al carrello solamente gli utenti guest e utenti registrati di livello cliente
            String isbn = request.getParameter("isbn");

            int quantita = Integer.parseInt(request.getParameter("quantita"));

            LibroDAO libroDAO = new LibroDAO();

            Libro libro = libroDAO.doRetrieveById(isbn);

            Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

            Dettaglio d;

            BigDecimal zero = new BigDecimal(0.00);

            if (carrello == null)//nel caso un utente guest acceda direttamente alla pagina del libro da un link, non avremo nessun carrello istanziato
                carrello = new Carrello();//inizializzazione del carrello

            if (carrello.getTotale().compareTo(zero) == 1 && (d = carrello.getDettagliobyISBN(isbn)) != null) { //carrello non vuoto e libro già esistente
                BigDecimal totaleCarrello = carrello.getTotale();
                totaleCarrello = totaleCarrello.subtract(d.getPrezzo());//rimuovo il prezzo del dettaglio attuale
                d.setQuantita(d.getQuantita() + quantita);//sommiamo la quantità già esistente e la quantità aggiuntiva
                BigDecimal prezzoDettaglio = d.getLibro().getPrezzoScontato();//se sconto è uguale a 0,prezzo e prezzoScontato saranno uguali
                prezzoDettaglio = prezzoDettaglio.multiply(new BigDecimal(d.getQuantita()));
                d.setPrezzo(prezzoDettaglio);
                totaleCarrello = totaleCarrello.add(prezzoDettaglio);
                carrello.setTotale(totaleCarrello);
            } else if (carrello.getTotale().compareTo(zero) == 1 || carrello.getTotale().compareTo(zero) == 0) { //nel caso invece il carrello sia vuoto oppure non vuoto ma quel libro non è stato già aggiunto
                BigDecimal prezzoDettaglio = new BigDecimal(0.00);
                prezzoDettaglio = prezzoDettaglio.add(libro.getPrezzoScontato());
                prezzoDettaglio = prezzoDettaglio.multiply(new BigDecimal(quantita));
                d = new Dettaglio(quantita, prezzoDettaglio, libro,0);//creo un nuovo dettaglio
                carrello.addDettaglio(d);//aggiungo il nuovo dettaglio al carrello
                BigDecimal totaleCarrello = carrello.getTotale();
                totaleCarrello = totaleCarrello.add(d.getPrezzo());
                carrello.setTotale(totaleCarrello);//aggiorno il totale del carrello
            }


            request.getSession().removeAttribute("carrello");

            request.getSession().setAttribute("carrello",carrello);

        }

        response.sendRedirect(request.getContextPath() + "/home");//col sendRedirect la request non sarà condivisa ma ricreata dalla servlet/jsp richiamata
    }

}
