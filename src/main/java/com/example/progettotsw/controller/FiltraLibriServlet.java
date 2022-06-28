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

@WebServlet("/filtra-libri")
public class FiltraLibriServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filtraAutore = request.getParameter("filtraAutore");
        String filtraGenere = request.getParameter("filtraGenere");
        String ordinaPrezzo = request.getParameter("ordinaPrezzo");

        if (filtraAutore != null) {
            String autore = request.getParameter("autore");
            String [] isbn = request.getParameterValues("isbn-libro");

            LibroDAO libroDAO = new LibroDAO();

            List<Libro> libri = libroDAO.doRetrievebyIsbnListandAutore(isbn,autore);

            request.setAttribute("libri",libri);

            String address = "/WEB-INF/ricerca.jsp";

            RequestDispatcher rd = request.getRequestDispatcher(address);

            rd.forward(request,response);

        } else if (filtraGenere != null) {
            String genere = request.getParameter("genere");
            String [] isbn = request.getParameterValues("isbn-libro");

            LibroDAO libroDAO = new LibroDAO();

            List<Libro> libri = libroDAO.doRetrievebyIsbnListandGenere(isbn,genere);

            request.setAttribute("libri",libri);

            String address = "/WEB-INF/ricerca.jsp";

            RequestDispatcher rd = request.getRequestDispatcher(address);

            rd.forward(request,response);
        } else if (ordinaPrezzo != null) {
            String prezzo = request.getParameter("prezzo");
            String [] isbn = request.getParameterValues("isbn-libro");

            LibroDAO libroDAO = new LibroDAO();

            List<Libro> libri = libroDAO.doRetrievebyIsbnListandPrezzo(isbn,prezzo);

            request.setAttribute("libri",libri);

            String address = "/WEB-INF/ricerca.jsp";

            RequestDispatcher rd = request.getRequestDispatcher(address);

            rd.forward(request,response);
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
}
