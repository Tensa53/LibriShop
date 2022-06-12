package com.example.progettotsw.controller;

import com.example.progettotsw.model.Carrello;
import com.example.progettotsw.model.Dettaglio;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/rimuovi-dal-carrello")
public class RimuoviLibroCarrelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String i = request.getParameter("i");
        String isbn = request.getParameter("isbn"+i);

        log(i);
        log(isbn);

        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

        Dettaglio d = carrello.getDettagliobyISBN(isbn);

        carrello.getDettagli().remove(d);

        carrello.setTotale(carrello.getTotale()-d.getPrezzo());
        log(String.valueOf(carrello.getNumeroProdotti()));

        String address = "http://localhost:8080/progettoTSW_war_exploded/carrello.jsp";

        response.sendRedirect(address);
    }
}
