package com.example.progettotsw.controller;

import com.example.progettotsw.model.Genere;
import com.example.progettotsw.model.GenereDAO;
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

        String insLibro = request.getParameter("insLibro");
        String modLibro = request.getParameter("modLibro");
        String delLibro = request.getParameter("delLibro");
        String delUtente = request.getParameter("delUtente");
        String modUtente = request.getParameter("modUtente");
        String insUtente = request.getParameter("insUtente");

        if (insLibro != null) {
            GenereDAO genereDAO = new GenereDAO();

            List<Genere> generi = genereDAO.doRetrieveAll();

            request.setAttribute("generi",generi);

            String address = "/WEB-INF/insLibro.jsp";

            RequestDispatcher rd = request.getRequestDispatcher(address);

            rd.forward(request,response);
        }

        if(modLibro != null) {
            String address = "https://www.google.com/";

            response.sendRedirect(address);
        }

        if(delLibro != null) {
            String address = "https://www.google.com/";

            response.sendRedirect(address);
        }

        if(delUtente != null) {
            String address = "https://www.google.com/";

            response.sendRedirect(address);
        }

        if(modUtente != null) {
            String address = "https://www.google.com/";

            response.sendRedirect(address);
        }

        if(insUtente != null) {
            String address = "https://www.google.com/";

            response.sendRedirect(address);
        }
    }
}
