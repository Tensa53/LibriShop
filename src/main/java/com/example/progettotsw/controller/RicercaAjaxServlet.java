package com.example.progettotsw.controller;

import com.example.progettotsw.model.Libro;
import com.example.progettotsw.model.LibroDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ricerca-ajax")
public class RicercaAjaxServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String stringa = request.getParameter("titolo");

        if (stringa != null) {
            synchronized (stringa) {
                LibroDAO libroDAO = new LibroDAO();

                List<Libro> libri = libroDAO.doRetrievebyString(stringa);

                for (Libro l : libri) {
                    response.getWriter().append("<option>" + l.getTitolo() + "</option>");
                }

            }
        }
    }
}
