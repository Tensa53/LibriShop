package com.example.progettotsw.controller;

import com.example.progettotsw.model.Utente;
import com.example.progettotsw.model.UtenteDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        UtenteDAO utenteDAO = new UtenteDAO();

        Utente u =  utenteDAO.doRetrieveByMailPassword(mail,password);

        if(u == null) {

        }

        request.getSession().setAttribute("utente",u);

        String address = "http://localhost:8080/initCat_war_exploded/Home";

        response.sendRedirect(address);

    }
}
