package com.example.progettotsw.controller;

import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet("/completa-ordine")
public class CompletaOrdineServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

        Utente utente = (Utente) request.getSession().getAttribute("utente");

        ArrayList<Dettaglio> incompatibili = new ArrayList<>();

        String incompatibiliStr = "";

        List<Libro> libri = new ArrayList<>();

        for (Dettaglio d : carrello.getDettagli()) {
            Libro l = d.getLibro();

            if (l.getDisponibilita() - d.getQuantita() < 0) {
                incompatibili.add(d);
                incompatibiliStr += "Titolo : " + d.getLibro().getTitolo() + "ISBN : " + d.getLibro().getISBN() + "\n";
            }
            else {
                l.setDisponibilita(l.getDisponibilita() - d.getQuantita());
                libri.add(l);
            }
        }

        if(incompatibili.size() > 0){
            throw new ServletException("i seguenti libri \n" + incompatibiliStr + "eccedono la quantità attualmente acquistabile,sei pregato di modificare il carrello");
        }


        Pagamento pagamento = (Pagamento) request.getSession().getAttribute("pagamento");

        Indirizzo indirizzo = (Indirizzo) request.getSession().getAttribute("indirizzo");

        Ordine ordine = new Ordine(new GregorianCalendar(),indirizzo,pagamento,carrello.getTotale(),utente);

        OrdineDAO ordineDAO = new OrdineDAO();

        ordineDAO.doSave(ordine);

        LibroDAO libroDAO = new LibroDAO();

        libroDAO.doUpdateDisponibilitaAllFromList(libri);

        request.getSession().removeAttribute("carrello");

        request.getSession().setAttribute("carrello",new Carrello());

        String address = "/WEB-INF/completo.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(address);

        rd.forward(request,response);
    }
}
