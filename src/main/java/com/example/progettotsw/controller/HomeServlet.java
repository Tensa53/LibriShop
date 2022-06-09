package com.example.progettotsw.controller;

import com.example.progettotsw.model.Carrello;
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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        LibroDAO libroDAO = new LibroDAO();

        List<Libro> libri = libroDAO.doRetrieveAll();

        request.setAttribute("libri",libri);

        String address = "/WEB-INF/home.jsp";

        //la home può essere oggetto di redirect, risulta fondamentale controllare il carrello affinchè non vada perso
        if (request.getSession().getAttribute("carrello") == null) {
            Carrello carrelloSession = new Carrello();
            request.getSession().setAttribute("carrello", carrelloSession);
        }

        RequestDispatcher rd = request.getRequestDispatcher(address);

        rd.forward(request,response);

    }
}
