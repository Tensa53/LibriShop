package com.example.progettotsw.controller.riservata.ordine;

import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet("/completa-ordine")
public class CompletaOrdineServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (!utente.isAmministratore()) {
                Pagamento pagamento = (Pagamento) request.getSession().getAttribute("pagamento");

                Indirizzo indirizzo = (Indirizzo) request.getSession().getAttribute("indirizzo");

                String address;

                ArrayList<Dettaglio> incompatibili = new ArrayList<>();

                ArrayList<String> incompatibiliStr = new ArrayList<>();

                LibroDAO libroDAO = new LibroDAO();

                List<Libro> libri = new ArrayList<>();

                for (Dettaglio d : carrello.getDettagli()) {
                    Libro l = d.getLibro();

                    l.setDisponibilita((libroDAO.doRetrieveById(l.getISBN())).getDisponibilita()); //nel frattempo altri utenti potrebbero aver ordinato lo stesso libro ed occorre aggiornare la disponibilità

                    int indisponibile = l.getDisponibilita() - d.getQuantita();

                    if (indisponibile < 0) {
                        incompatibili.add(d);
                        String incompatibile = "Titolo : " + d.getLibro().getTitolo() + " - ISBN : " + d.getLibro().getISBN() + " - quantità in eccesso : " + indisponibile * -1;
                        incompatibiliStr.add(incompatibile);
                    } else {
                        l.setDisponibilita(l.getDisponibilita() - d.getQuantita());
                        libri.add(l);
                    }
                }

                if (pagamento == null || indirizzo == null) {
                    address = "/WEB-INF/ORDINE/ordine.jsp";

                    String msg = "Seleziona un indirizzo e un metodo di pagamento per il seguente ordine";

                    request.setAttribute("msgerrpagind", msg);

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                } else if (incompatibili.size() > 0) {
                    address = "/WEB-INF/ORDINE/ordine.jsp";

                    request.setAttribute("incompatibili",incompatibiliStr);

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                } else {
                    Ordine ordine = new Ordine(indirizzo, pagamento, carrello.getTotale(), utente);

                    OrdineDAO ordineDAO = new OrdineDAO();

                    ordineDAO.doSave(ordine);

                    libroDAO.doUpdateDisponibilitaAllFromList(libri);

                    request.getSession().removeAttribute("carrello");

                    request.getSession().removeAttribute("indirizzo");

                    request.getSession().removeAttribute("pagamento");

                    request.getSession().invalidate();

                    request.getSession().setAttribute("utente",utente);

                    request.getSession().setAttribute("carrello", new Carrello());

                    address = "/WEB-INF/ORDINE/completo.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                }
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
