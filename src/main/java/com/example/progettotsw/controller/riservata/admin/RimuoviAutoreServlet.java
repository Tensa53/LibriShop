package com.example.progettotsw.controller.riservata.admin;

import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/rimuovi-autore")
public class RimuoviAutoreServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String cf = request.getParameter("cf-autore");

                if(cf != null) {

                    AutoreDAO autoreDAO = new AutoreDAO();

                    GenereDAO genereDAO = new GenereDAO();

                    Autore autore = autoreDAO.doRetrievebyCF(cf);

                    LibroDAO libroDAO = new LibroDAO();

                    List<Libro> libri = libroDAO.doRetrievebyAutore(autore);

                    ArrayList<String> autoriIncancellabili = new ArrayList<>();

                    String msg = null;

                    String address = "/WEB-INF/ADMIN/opsAutoreGenere.jsp";

                    if (libri.size() > 0) {

                        for (Libro l : libri) {
                            autoriIncancellabili.add(l.getISBN() + " - " + l.getTitolo());
                        }

                    } else {
                        autoreDAO.doRemoveAutorebyCF(cf);
                        msg = "Rimozione effettuata con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";
                    }

                    request.setAttribute("msgsuccess", msg);

                    request.setAttribute("autoriIncancellabili",autoriIncancellabili);

                    request.setAttribute("autori", autoreDAO.doRetrieveAll());

                    request.setAttribute("generi", genereDAO.doRetrieveAll());

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                } else
                    response.sendRedirect(request.getContextPath() + "/admin-forward-redirect?modDelUtente=Modifica/Rimuovi%20Utente");

            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
