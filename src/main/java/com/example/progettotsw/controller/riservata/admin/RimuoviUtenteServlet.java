package com.example.progettotsw.controller.riservata.admin;

import com.example.progettotsw.model.Utente;
import com.example.progettotsw.model.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/rimuovi-utente")
public class RimuoviUtenteServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String mail = request.getParameter("mail-utente");

                if (mail != null) {
                    UtenteDAO utenteDAO = new UtenteDAO();

                    Utente u = utenteDAO.doRetrieveByMail(mail);

                    int row = utenteDAO.doDeleteUtente(u);

                    String msg = null;

                    if (row == 1)
                        msg = "Rimozione effettuata con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

                    request.setAttribute("msg", msg);

                    String address = "/WEB-INF/ADMIN/modDelUtente.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                } else
                    response.sendRedirect(request.getContextPath() + "/admin-forward-redirect?modDelUtente=Modifica/Rimuovi%20Utente");

            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}