package com.example.progettotsw.controller;

import com.example.progettotsw.model.Utente;
import com.example.progettotsw.model.UtenteDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controlla-username")

public class ControlloUsernameServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            String username = request.getParameter("username");
            UtenteDAO utenteDAO = new UtenteDAO();

            Utente utente = utenteDAO.doRetrieveByUsername(username);

            if(utente == null) {}
            else {
                response.getWriter().print("Scegli un username diverso.");
            }
        }
    }
