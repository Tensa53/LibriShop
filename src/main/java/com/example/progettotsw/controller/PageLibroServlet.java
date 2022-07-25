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

@WebServlet("/page-libro")
public class PageLibroServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String isbn = request.getParameter("isbn");

        if (isbn != null) {
            LibroDAO libroDAO = new LibroDAO();

            Libro libro = libroDAO.doRetrieveById(isbn);

            String address = "/libro.jsp";

            RequestDispatcher rd = request.getRequestDispatcher(address);

            request.setAttribute("libro",libro);

            rd.forward(request,response);
        } else
            response.sendRedirect(request.getContextPath() + "/home");

    }
}
