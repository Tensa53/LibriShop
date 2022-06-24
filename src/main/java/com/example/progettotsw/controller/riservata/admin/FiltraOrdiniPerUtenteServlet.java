package com.example.progettotsw.controller.riservata.admin;

import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/filtra-ordini-utente")
public class FiltraOrdiniPerUtenteServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String mail = request.getParameter("mail-utente");

                if (mail == null || mail.equals("Nessun Filtro")){
                    response.sendRedirect(request.getContextPath() + "/admin-forward-redirect?viewOrdini=Visualizza%20Ordini");
                } else {
                    OrdineDAO ordineDAO = new OrdineDAO();

                    List<Ordine> ordini = ordineDAO.doRetrieveAllbyUserMail(mail);

                    UtenteDAO utenteDAO = new UtenteDAO();

                    List<Utente> utenti = utenteDAO.doRetrieveAllUsers();

                    request.setAttribute("ordini",ordini);

                    request.setAttribute("utenti",utenti);

                    String address = "/WEB-INF/ADMIN/viewOrdini.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request,response);
                }

            }else
                response.sendRedirect(request.getContextPath() + "/home");
        }else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
}
