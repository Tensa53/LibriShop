package com.example.progettotsw.controller;

import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/ricerca")
public class RicercaServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ricerca = request.getParameter("ricerca");

        if(ricerca != null || !ricerca.isEmpty() || ricerca.length() > 0) {
            LibroDAO libroDAO = new LibroDAO();

            List<Libro> libri = libroDAO.doRetrievebyString(ricerca);

            AutoreDAO autoreDAO = new AutoreDAO();

            List<Autore> autori = autoreDAO.doRetrieveAll();

            GenereDAO genereDAO = new GenereDAO();

            List<Genere> generi = genereDAO.doRetrieveAll();

            request.getSession().setAttribute("autori",autori);

            request.getSession().setAttribute("generi",generi);

            request.setAttribute("libri",libri);

            String address = "/WEB-INF/ricerca.jsp";

            log("length lista : " + libri.size());

            RequestDispatcher rd = request.getRequestDispatcher(address);

            rd.forward(request,response);
        }

    }
}
