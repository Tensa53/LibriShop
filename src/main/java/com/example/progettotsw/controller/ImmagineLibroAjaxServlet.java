package com.example.progettotsw.controller;

import com.example.progettotsw.model.Libro;
import com.example.progettotsw.model.LibroDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/immaginelibro")

public class ImmagineLibroAjaxServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String isbn = request.getParameter("isbn");
        LibroDAO libroDAO = new LibroDAO();

        Libro libro = libroDAO.doRetrieveById(isbn);

        response.getWriter().print(libro.getFoto());
    }

}
