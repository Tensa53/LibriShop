package com.example.progettotsw.controller;

import com.example.progettotsw.model.CarrelloDAO;
import com.example.progettotsw.model.Utente;
import com.example.progettotsw.model.UtenteDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    public void doGet (HttpServletRequest request,HttpServletResponse response) throws IOException {

        String mail = request.getParameter("mailr");
        String username = request.getParameter("usernamer");
        String nome = request.getParameter("nomer");
        String cognome = request.getParameter("cognomer");
        String password = request.getParameter("passwordr");
        boolean amministratore = Boolean.parseBoolean(request.getParameter("amministratorer"));

        if(mail != null && username != null && nome != null && cognome != null && password != null && !amministratore) {
            Utente utente = new Utente(mail,username,nome,cognome,amministratore);
            utente.setPassword(password);

            UtenteDAO utenteDAO = new UtenteDAO();

            log("righe :" + utenteDAO.doSave(utente));

            CarrelloDAO carrelloDAO = new CarrelloDAO();

            carrelloDAO.doCreate(mail);

            request.getSession().setAttribute("utente",utente);
        }

        String address = request.getContextPath() + "/home";

        response.sendRedirect(address);
    }
}
