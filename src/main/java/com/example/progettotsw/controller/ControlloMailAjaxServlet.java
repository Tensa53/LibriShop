package com.example.progettotsw.controller;

import com.example.progettotsw.model.Utente;
import com.example.progettotsw.model.UtenteDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controlla-mail")
public class ControlloMailAjaxServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String mail = request.getParameter("mail");

        if (mail != null) {
            UtenteDAO utenteDAO = new UtenteDAO();

            Utente utente = utenteDAO.doRetrieveByMail(mail);

            if (utente == null) {
            } else {
                response.getWriter().print("Scegli un'email diversa.");
            }
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }
}