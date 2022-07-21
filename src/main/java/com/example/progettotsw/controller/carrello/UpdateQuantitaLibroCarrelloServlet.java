package com.example.progettotsw.controller.carrello;

import com.example.progettotsw.model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;


@WebServlet("/modifica-quantita")
public class UpdateQuantitaLibroCarrelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

        if (carrello != null) {
            if (carrello.getDettagli().size() > 0) {
                synchronized (carrello) {
                    int quantita = Integer.parseInt(request.getParameter("quantita"));
                    String isbn = request.getParameter("isbn");

                    Dettaglio d = carrello.getDettagliobyISBN(isbn);

                    BigDecimal carrelloTotale = carrello.getTotale();

                    carrelloTotale = carrelloTotale.subtract(d.getPrezzo());

                    log(carrelloTotale.toString());

                    d.setQuantita(quantita);

                    BigDecimal prezzoDettaglio = d.getPrezzo();

                    log(prezzoDettaglio.toString());

                    BigDecimal prezzoLibro = d.getLibro().getPrezzoScontato();

                    prezzoDettaglio = prezzoLibro.multiply(new BigDecimal(quantita));

                    d.setPrezzo(prezzoDettaglio);

                    carrelloTotale = carrelloTotale.add(prezzoDettaglio);

                    carrello.setTotale(carrelloTotale);

                    log(carrelloTotale.toString());

                    response.setContentType("text/xml;charset=UTF-8");
                    response.getWriter().append("<carrello>");
                    response.getWriter().append("<libro><prezzoDettaglio>" + d.getPrezzo() + "</prezzoDettaglio><prezzoTotale>" + carrello.getTotale() + "</prezzoTotale></libro>");
                    response.getWriter().append("</carrello>");
                }
            } else
                response.sendRedirect(request.getContextPath() + "/carrello");
        } else
            response.sendRedirect(request.getContextPath() + "/home");

    }
}
