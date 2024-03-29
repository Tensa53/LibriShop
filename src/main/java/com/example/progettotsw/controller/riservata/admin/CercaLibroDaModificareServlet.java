package com.example.progettotsw.controller.riservata.admin;

import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/cerca-libro-da-modificare")
public class CercaLibroDaModificareServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null){
            if (utente.isAmministratore()) {
                String isbn = request.getParameter("isbn-libro");

                if (isbn != null) {
                    LibroDAO libroDAO = new LibroDAO();

                    GenereDAO genereDAO = new GenereDAO();

                    Libro libro = libroDAO.doRetrieveById(isbn);

                    log(libro.getDataPubblicazioneReversedString());

                    AutoreDAO autoreDAO = new AutoreDAO();

                    List<Genere> generiLibro = genereDAO.doRetrieveByISBNLibro(isbn);

                    Autore autoreLibro = autoreDAO.doRetrievebyISBNLibro(isbn);

                    List<Libro> libri = libroDAO.doRetrieveAll();

                    List<Genere> generi = genereDAO.doRetrieveAll();

                    List<Autore> autori = autoreDAO.doRetrieveAll();

                    request.setAttribute("libri", libri);

                    request.setAttribute("generi", generi);

                    request.setAttribute("autori",autori);

                    request.setAttribute("libro",libro);

                    request.setAttribute("autore",autoreLibro);

                    request.setAttribute("generi-libro",generiLibro);

                    String address = "/WEB-INF/ADMIN/modDelLibro.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request,response);
                } else
                    response.sendRedirect(request.getContextPath() + "/admin-forward-redirect?modDelLibro=Modifica/Rimuovi%20Libro");
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
