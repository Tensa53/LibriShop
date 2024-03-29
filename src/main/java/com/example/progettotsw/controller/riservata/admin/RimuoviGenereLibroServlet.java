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

@WebServlet("/rimuovi-genere")
public class RimuoviGenereLibroServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String nome = request.getParameter("nome-genere");

                if (nome != null) {
                    LibroDAO libroDAO = new LibroDAO();

                    GenereDAO genereDAO = new GenereDAO();

                    AutoreDAO autoreDAO = new AutoreDAO();

                    List<Libro> libri = libroDAO.doRetrievebyGenere(nome);

                    ArrayList<String> generiIncancellabili = new ArrayList<>();

                    String msg = null;

                    String address = "/WEB-INF/ADMIN/opsAutoreGenere.jsp";

                    if (libri.size() > 0) {

                        for (Libro l : libri) {
                            generiIncancellabili.add(l.getISBN() + " - " + l.getTitolo());
                        }

                    } else {
                        genereDAO.doRemove(nome);
                        msg = "Rimozione effettuata con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";
                    }

                    request.setAttribute("msgsuccess", msg);

                    request.setAttribute("generiIncancellabili",generiIncancellabili);

                    request.setAttribute("autori", autoreDAO.doRetrieveAll());

                    request.setAttribute("generi", genereDAO.doRetrieveAll());

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                } else
                    response.sendRedirect(request.getContextPath() + "/admin-forward-redirect?Gestione%20Autore%20e%20Genere");

            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
