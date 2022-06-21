package com.example.progettotsw.controller.riservata.admin;

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

@WebServlet("/inserisci-utente")
public class InserisciUtenteServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String mail = request.getParameter("mailr");
                String username = request.getParameter("usernamer");
                String nome = request.getParameter("nomer");
                String cognome = request.getParameter("cognomer");
                String password = request.getParameter("passwordr");
                boolean amministratore = Boolean.parseBoolean(request.getParameter("amministratorer"));

                if(mail != null && username != null && nome != null && cognome != null && password != null) {
                    Utente nuovoUtente = new Utente(mail,username,nome,cognome,amministratore);
                    nuovoUtente.setPassword(password);

                    UtenteDAO utenteDAO = new UtenteDAO();

                    int row = utenteDAO.doSave(nuovoUtente);

                    String msg = null;

                    if (row == 1)
                        msg = "Inserimento effettuato con successo !!! Torna alla <a href = \"http://localhost:8080/progettoTSW_war_exploded/area-riservata\"> dashboard </a> oppure effettua un altro inserimento";

                    request.setAttribute("msg",msg);
                }

                if (!amministratore) {
                    CarrelloDAO carrelloDAO = new CarrelloDAO();

                    carrelloDAO.doCreate(mail);
                }

                String address = "/WEB-INF/ADMIN/insUtente.jsp";

                RequestDispatcher rd = request.getRequestDispatcher(address);

                rd.forward(request,response);

            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");




    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
}
