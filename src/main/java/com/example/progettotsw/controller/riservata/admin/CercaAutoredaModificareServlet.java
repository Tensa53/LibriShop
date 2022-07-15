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

@WebServlet("/cerca-autore-da-modificare")
public class CercaAutoredaModificareServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null){
            if (utente.isAmministratore()){
                String cf = request.getParameter("cf-autore");

                AutoreDAO autoreDAO = new AutoreDAO();

                GenereDAO genereDAO = new GenereDAO();

                if(cf != null) {
                    Autore autore = autoreDAO.doRetrievebyCF(cf);

                    request.setAttribute("autore",autore);

                    request.setAttribute("autori",autoreDAO.doRetrieveAll());

                    request.setAttribute("generi",genereDAO.doRetrieveAll());

                    String address = "/WEB-INF/ADMIN/opsAutoreGenere.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request,response);
                } else
                    response.sendRedirect(request.getContextPath() + "/admin-forward-redirect?opsAutoreGenere=Gestione%20Autore%20e%20Genere");

            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
}
