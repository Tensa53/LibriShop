package com.example.progettotsw.controller.riservata.utente;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/conferma-modifiche-indirizzo")
public class ConfermaModificheIndirizzoUtenteServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,HttpServletResponse response) {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request,response);
    }
}
