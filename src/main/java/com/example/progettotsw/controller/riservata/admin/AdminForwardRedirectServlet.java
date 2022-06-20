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
        String modLibro = request.getParameter("modLibro");
        String delLibro = request.getParameter("delLibro");
        String insUtente = request.getParameter("insUtente");
        String modUtente = request.getParameter("modUtente");
        String delUtente = request.getParameter("delUtente");
        String viewOrdini = request.getParameter("viewOrdini");
        String insAutore = request.getParameter("insAutore");
        String modAutore = request.getParameter("modAutore");
        String delAutore = request.getParameter("delAutore");
        String insGenere = request.getParameter("insGenere");
        String modGenere = request.getParameter("modGenere");
        String delGenere = request.getParameter("delGenere");

        if(utente != null){
            if (utente.isAmministratore()){
                if(insLibro != null) {
                    GenereDAO genereDAO = new GenereDAO();

                    List<Genere> generi = genereDAO.doRetrieveAll();

                    request.setAttribute("generi",generi);

                    String address = "/WEB-INF/ADMIN/insLibro.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request,response);
                }

                if(modLibro != null) {
                    LibroDAO libroDAO = new LibroDAO();

                    List<Libro> libri = libroDAO.doRetrieveAll();

                    GenereDAO genereDAO = new GenereDAO();

                    List<Genere> generi = genereDAO.doRetrieveAll();

                    request.getSession().setAttribute("libri",libri);

                    request.getSession().setAttribute("generi",generi);

                    String address = "/WEB-INF/ADMIN/modLibro.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request,response);
                }

                if(delLibro != null) {
                    String address = "https://www.google.com/";

                    response.sendRedirect(address);
                }

                if(insUtente != null) {
                    String address = "https://www.google.com/";

                    response.sendRedirect(address);
                }

                if(modUtente != null) {
                    String address = "https://www.google.com/";

                    response.sendRedirect(address);
                }

                if(delUtente != null) {
                    String address = "https://www.google.com/";

                    response.sendRedirect(address);
                }

                if(viewOrdini != null) {
                    String address = "https://www.google.com/";

                    response.sendRedirect(address);
                }

                if(insAutore != null) {
                    String address = "https://www.google.com/";

                    response.sendRedirect(address);
                }

                if(modAutore != null) {
                    String address = "https://www.google.com/";

                    response.sendRedirect(address);
                }

                if(delAutore != null) {
                    String address = "https://www.google.com/";

                    response.sendRedirect(address);
                }

                if(insGenere != null) {
                    String address = "https://www.google.com/";

                    response.sendRedirect(address);
                }

                if(modGenere != null) {
                    String address = "https://www.google.com/";

                    response.sendRedirect(address);
                }

                if(delGenere != null) {
                    String address = "https://www.google.com/";

                    response.sendRedirect(address);
                }
            } else
                response.sendRedirect("http://localhost:8080/progettoTSW_war_exlpoded/home");
        } else
            response.sendRedirect("http://localhost:8080/progettoTSW_war_exlpoded/home");
    }
}
