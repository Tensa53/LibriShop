package com.example.progettotsw.controller;

import com.example.progettotsw.model.Utente;
import com.example.progettotsw.model.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        log("sono nella servlet di login");


        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        UtenteDAO utenteDAO = new UtenteDAO();

        Utente utente =  utenteDAO.doRetrieveByMailPassword(mail,password);

        log(utente.getUsername());

        if(utente == null) {
            String msg = "Mail o password errati !!";

            request.setAttribute("messaggio",msg);

            String address = "/login.jsp";

            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

        request.getSession().setAttribute("utente",utente);

        String address = "http://localhost:8080/progettoTSW_war_exploded/home";

        response.sendRedirect(address);
    }
}
