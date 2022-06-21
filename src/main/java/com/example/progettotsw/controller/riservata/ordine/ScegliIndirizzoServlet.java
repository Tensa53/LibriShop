package com.example.progettotsw.controller.riservata.ordine;

import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/scegli-indirizzo")
public class ScegliIndirizzoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (!utente.isAmministratore()) {
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

                List<Indirizzo> indirizzi = indirizzoDAO.doRetrievebyUserMail(utente.getMail());

                request.setAttribute("indirizzi",indirizzi);

                String address = "/WEB-INF/ORDINE/indirizzi.jsp";

                RequestDispatcher rd = request.getRequestDispatcher(address);

                rd.forward(request,response);
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }
}
