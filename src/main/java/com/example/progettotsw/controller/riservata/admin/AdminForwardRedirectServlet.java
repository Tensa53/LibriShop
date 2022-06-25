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

@WebServlet("/admin-forward-redirect")
public class AdminForwardRedirectServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");

        log(request.getContextPath());

        String insLibro = request.getParameter("insLibro");
        String modDelLibro = request.getParameter("modDelLibro");
        String insUtente = request.getParameter("insUtente");
        String modDelUtente = request.getParameter("modDelUtente");
        String viewOrdini = request.getParameter("viewOrdini");
        String opsAutoreGenere = request.getParameter("opsAutoreGenere");

        if (utente != null) {
            if (utente.isAmministratore()) {
                if (insLibro != null) {
                    GenereDAO genereDAO = new GenereDAO();

                    List<Genere> generi = genereDAO.doRetrieveAll();

                    request.setAttribute("generi", generi);

                    String address = "/WEB-INF/ADMIN/insLibro.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                }

                if (modDelLibro != null) {
                    LibroDAO libroDAO = new LibroDAO();

                    List<Libro> libri = libroDAO.doRetrieveAll();

                    GenereDAO genereDAO = new GenereDAO();

                    List<Genere> generi = genereDAO.doRetrieveAll();

                    request.getSession().setAttribute("libri", libri);

                    request.getSession().setAttribute("generi", generi);

                    String address = "/WEB-INF/ADMIN/modDelLibro.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                }

                if (insUtente != null) {
                    String address = "/WEB-INF/ADMIN/insUtente.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                }

                if (modDelUtente != null) {
                    String address = "/WEB-INF/ADMIN/modDelUtente.jsp";

                    UtenteDAO utenteDAO = new UtenteDAO();

                    List<Utente> utenti = utenteDAO.doRetrieveAll();

                    request.getSession().setAttribute("utenti", utenti);

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                }

                if (viewOrdini != null) {
                    OrdineDAO ordineDAO = new OrdineDAO();

                    List<Ordine> ordini = ordineDAO.doRetrieveAll();

                    UtenteDAO utenteDAO = new UtenteDAO();

                    List<Utente> utenti = utenteDAO.doRetrieveAllUsers();

                    request.setAttribute("ordini", ordini);

                    request.setAttribute("utenti", utenti);

                    String address = "/WEB-INF/ADMIN/viewOrdini.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                }

                if (opsAutoreGenere != null) {
                    String address = "/WEB-INF/ADMIN/opsAutoreGenere.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    GenereDAO genereDAO = new GenereDAO();

                    List<Genere> generi = genereDAO.doRetrieveAll();

                    AutoreDAO autoreDAO = new AutoreDAO();

                    List<Autore> autori = autoreDAO.doRetrieveAll();

                    request.setAttribute("generi", generi);

                    request.setAttribute("autori", autori);

                    rd.forward(request, response);
                }

            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }
}
