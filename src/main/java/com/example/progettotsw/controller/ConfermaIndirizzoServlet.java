package com.example.progettotsw.controller;

import com.example.progettotsw.model.Indirizzo;
import com.example.progettotsw.model.IndirizzoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/conferma-indirizzo")
public class ConfermaIndirizzoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String [] indirizzovet = request.getParameterValues("indirizzo");

        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

        Indirizzo indirizzo = indirizzoDAO.doRetrieveByViaCivicoCAP(indirizzovet[0],indirizzovet[1],indirizzovet[2]);

        request.getSession().setAttribute("indirizzo",indirizzo);

        String address = "/WEB-INF/ordine.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(address);

        rd.forward(request,response);
    }
}
