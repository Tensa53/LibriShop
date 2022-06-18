package com.example.progettotsw.controller;

import com.example.progettotsw.model.Libro;
import com.example.progettotsw.model.LibroDAO;
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

            request.setAttribute("libri",libri);

            String address = "/WEB-INF/ricerca.jsp";

            log("length lista : " + libri.size());

            RequestDispatcher rd = request.getRequestDispatcher(address);

            rd.forward(request,response);
        }

    }
}
