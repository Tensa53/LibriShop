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

@WebServlet("/conferma-modifiche-utente")
public class ConfermaModificheUtenteServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String mail = request.getParameter("mail");
                String username = request.getParameter("username");
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String password = request.getParameter("password");
                boolean amministratore = Boolean.parseBoolean(request.getParameter("amministratore"));

                Utente u = new Utente(mail,username,nome,cognome,amministratore);

                UtenteDAO utenteDAO = new UtenteDAO();

                if (password.length() > 0) {
                    utente.setPassword(password);
                    utenteDAO.doUpdateUserPasswordByMail(utente.getPasswordhash(),mail);
                }

                String msg = null;

                if(utenteDAO.doUpdateUser(u) == 1)
                    msg = "Modifiche effettuate con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

                String address = "/WEB-INF/ADMIN/modDelUtente.jsp";

                request.setAttribute("msg",msg);

                request.getSession().removeAttribute("utenti");

                RequestDispatcher rd = request.getRequestDispatcher(address);
                rd.forward(request, response);
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
