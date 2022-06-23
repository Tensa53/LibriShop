package com.example.progettotsw.controller;

import com.example.progettotsw.model.Autore;
import com.example.progettotsw.model.AutoreDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/autore")

public class ControlloAutoreServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String nome = request.getParameter("autore");
        AutoreDAO autoreDAO = new AutoreDAO();

        Autore autore = autoreDAO.doRetrievebyName(nome);

        if(autore == null) {
            response.getWriter().print("L'autore non è presente nel database, inserisci l'autore prima di continuare.");
        }
    }

}
