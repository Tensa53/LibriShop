package com.example.progettotsw.controller;

import com.example.progettotsw.model.CarrelloDAO;
import com.example.progettotsw.model.Utente;
import com.example.progettotsw.model.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String mail = request.getParameter("mailr");
        String username = request.getParameter("usernamer");
        String nome = request.getParameter("nomer");
        String cognome = request.getParameter("cognomer");
        String password = request.getParameter("passwordr");
        boolean amministratore = Boolean.parseBoolean(request.getParameter("amministratorer"));

        boolean compilazioneForm = mail != null && username != null && nome != null && cognome != null && password != null;

        boolean validazioneForm = Forms.validateFormUtente(mail,username,nome,cognome,password,request);

        UtenteDAO utenteDAO = new UtenteDAO();

        Utente utentedb = utenteDAO.doRetrieveByMail(mail);

        if (utentedb.getMail().equals(mail)) {
            request.setAttribute("msgmailinuso","già in uso");
        }

        if (utentedb.getUsername().equals(username)) {
            request.setAttribute("msgusernameinuso", "già in uso");
        }

        if(compilazioneForm && validazioneForm) {
            Utente utente = new Utente(mail,username,nome,cognome,amministratore);
            utente.setPassword(password);

            log("righe :" + utenteDAO.doSave(utente));

            CarrelloDAO carrelloDAO = new CarrelloDAO();

            carrelloDAO.doCreate(mail);

            request.getSession().setAttribute("utente",utente);

            String address = request.getContextPath() + "/home";

            response.sendRedirect(address);
        } else {
            String address = "registrazione.jsp";
            System.out.println(address);
            RequestDispatcher rd = request.getRequestDispatcher(address);
            rd.forward(request,response);
        }
    }

    public void doGet (HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }


}
