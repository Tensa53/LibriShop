package com.example.progettotsw.controller;

import com.example.progettotsw.model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/area-riservata")
public class AreaRiservataServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        String address = "";

        if(utente.isAmministratore())
            address="/WEB-INF/admin.jsp";
        else
            address="/WEB-INF/personale.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(address);

        rd.forward(request,response);
    }
}
