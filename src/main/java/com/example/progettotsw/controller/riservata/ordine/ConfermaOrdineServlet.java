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

@WebServlet("/conferma-ordine")
public class ConfermaOrdineServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");
        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

        String address = "";
        String msg = "";

        if (utente != null) {
            if (!utente.isAmministratore()) {
                address = "/WEB-INF/ORDINE/ordine.jsp";
                CarrelloDAO carrelloDAO = new CarrelloDAO();
                carrelloDAO.doRemoveAllbyUtente(utente.getMail());//quando l'utente conferma l'ordine,preserviamo il carrello
                carrelloDAO.doSaveAllbyUtente(carrello,utente.getMail());//lo salviamo già nel db così da poterlo preservare da chiusure involontarie della pagina
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        }
        else {
            address = "login.jsp";
            msg = "Per confermare un ordine devi effettuare il login. Controlla poi il carrello e conferma l'ordine";
            request.setAttribute("msg",msg);
        }

        RequestDispatcher rd = request.getRequestDispatcher(address);

        rd.forward(request, response);
    }
}
