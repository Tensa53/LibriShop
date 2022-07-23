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
            ArrayList<Dettaglio> dettagli = (ArrayList<Dettaglio>) carrello.getDettagli();

            ArrayList<Libro> indisponibili = new ArrayList<>();

            LibroDAO libroDAO = new LibroDAO();

            if (dettagli.size() > 0) {
                for (int i = 0; i < dettagli.size(); i++) {
                    Dettaglio d = dettagli.get(i);

                    Libro libro = libroDAO.doRetrieveById(d.getLibro().getISBN());

                    if (libro != null) {
                        if (libro.getDisponibilita() == -1 || libro.getDisponibilita() == 0) {
                            BigDecimal totaleCarrello = carrello.getTotale();

                            totaleCarrello = totaleCarrello.subtract(d.getPrezzo());

                            carrello.removeDettaglio(d);

                            carrello.setTotale(totaleCarrello);

                            indisponibili.add(d.getLibro());
                        }
                    } else {
                        BigDecimal totaleCarrello = carrello.getTotale();

                        totaleCarrello = totaleCarrello.subtract(d.getPrezzo());

                        carrello.removeDettaglio(d);

                        carrello.setTotale(totaleCarrello);

                        indisponibili.add(d.getLibro());
                    }
                }
            }

            request.setAttribute("indisponibili", indisponibili);

            String address = "/WEB-INF/carrello.jsp";

            RequestDispatcher rd = request.getRequestDispatcher(address);

            rd.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }
}
