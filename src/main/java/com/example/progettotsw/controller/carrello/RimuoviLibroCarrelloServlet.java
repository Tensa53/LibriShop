package com.example.progettotsw.controller.carrello;

import com.example.progettotsw.model.Carrello;
import com.example.progettotsw.model.Dettaglio;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/rimuovi-dal-carrello")
public class RimuoviLibroCarrelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String i = request.getParameter("i");
        String isbn = request.getParameter("isbn"+i);

        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

        if (carrello != null) {

            Dettaglio d = carrello.getDettagliobyISBN(isbn);

            if (carrello.getDettagli().size() > 0) {
                carrello.removeDettaglio(d);

                BigDecimal totaleCarrello = carrello.getTotale();

                totaleCarrello = totaleCarrello.subtract(d.getPrezzo());

                carrello.setTotale(totaleCarrello);

                request.getSession().removeAttribute("carrello");

                request.getSession().setAttribute("carrello",carrello);
            }

            response.sendRedirect(request.getContextPath() + "/carrello");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }
}
