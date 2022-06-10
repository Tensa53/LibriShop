package com.example.progettotsw.controller;

import com.example.progettotsw.model.Carrello;
import com.example.progettotsw.model.CarrelloDAO;
import com.example.progettotsw.model.Utente;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        CarrelloDAO carrelloDAO = new CarrelloDAO();

        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if(!utente.isAmministratore()){
            Carrello carrelloSession = (Carrello) request.getSession().getAttribute("carrello");

            Carrello carrelloDB = carrelloDAO.doRetrievebyUtente(utente.getMail());

            if (carrelloDB.getTotale() > 0.0f && carrelloSession != null && carrelloSession.getTotale() > 0.0f) { //al momento del logout
                carrelloDAO.doRemoveAllbyUtente(utente.getMail());//rimuovo tutto quello che c'è nel carrello di db
                carrelloDAO.doSaveAllbyUtente(carrelloSession, utente.getMail());//inserisco l'ultima istanza utile da sessione del carrello
            } else
                carrelloDAO.doSaveAllbyUtente(carrelloSession, utente.getMail());//se il carrelloo del db è già vuoto, inserisco direttamente quello di sessione

            request.getSession().removeAttribute("carrello");

            request.getSession().setAttribute("carrello",new Carrello());
        }

        request.getSession().removeAttribute("utente");

        request.getSession().invalidate();

        String address = "http://localhost:8080/progettoTSW_war_exploded/home";

        response.sendRedirect(address);
    }
}
