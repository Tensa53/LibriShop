package com.example.progettotsw.controller.riservata.ordine;

import com.example.progettotsw.model.Indirizzo;
import com.example.progettotsw.model.IndirizzoDAO;
import com.example.progettotsw.model.Utente;
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
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if(utente != null) {
            if (!utente.isAmministratore()) {
                String [] indirizzovet = request.getParameterValues("indirizzo");

                if(indirizzovet.length == 3) {
                    IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

                    Indirizzo indirizzo = indirizzoDAO.doRetrieveByViaCivicoCAP(indirizzovet[0],indirizzovet[1],indirizzovet[2]);

                    request.getSession().setAttribute("indirizzo",indirizzo);

                    String address = "/WEB-INF/ORDINE/ordine.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request,response);
                } else
                    response.sendRedirect(request.getContextPath() + "/area-riservata");
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }
}
