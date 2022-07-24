package com.example.progettotsw.controller.carrello;

import com.example.progettotsw.model.Carrello;
import com.example.progettotsw.model.Dettaglio;
import com.example.progettotsw.model.Libro;
import com.example.progettotsw.model.LibroDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet("/carrello")
public class VerificaCarrelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

        if (carrello != null) {
            ArrayList<Dettaglio> dettagli = new ArrayList<>(carrello.getDettagli());//clono la lista dei dettagli

            ArrayList<Libro> indisponibili = new ArrayList<>();

            LibroDAO libroDAO = new LibroDAO();

            if (dettagli.size() > 0) {
                  for (Dettaglio d : carrello.getDettagli()){ //itero dalla lista di dettagli presente nel carrello (non quella clonata)
                    Libro libro = libroDAO.doRetrieveById(d.getLibro().getISBN()); //estraggo il libro dal dettaglio, interrogando il db (valori aggiornati)

                    if (libro != null) {
                        if (libro.getDisponibilita() == -1 || libro.getDisponibilita() == 0) { //un libro non è aggiungibile al carrello se ha disponibilità 0 (esaurito) o -1 (non più in vendita e rimosso dal db)
                            BigDecimal totaleCarrello = carrello.getTotale();

                            totaleCarrello = totaleCarrello.subtract(d.getPrezzo());

                            dettagli.remove(d);

                            carrello.setTotale(totaleCarrello);

                            indisponibili.add(d.getLibro());
                        }
                    } else {
                        BigDecimal totaleCarrello = carrello.getTotale();

                        totaleCarrello = totaleCarrello.subtract(d.getPrezzo());

                        dettagli.remove(d);

                        carrello.setTotale(totaleCarrello);

                        indisponibili.add(d.getLibro());
                    }
                }
            }

            carrello.setDettagli(dettagli); //aggiorno la lista di dettalgi del carrello

            request.getSession().removeAttribute("carrello");

            request.getSession().setAttribute("carrello",carrello);

            request.setAttribute("indisponibili", indisponibili);

            String address = "/WEB-INF/carrello.jsp";

            RequestDispatcher rd = request.getRequestDispatcher(address);

            rd.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }
}
