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

@WebServlet("/cerca-utente-da-modificare")
public class CercaUtentedaModificareServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String mail = request.getParameter("mail-utente");

                UtenteDAO utenteDAO = new UtenteDAO();

                Utente u = utenteDAO.doRetrieveByMail(mail);

                request.setAttribute("utente",u);

                String address = "/WEB-INF/ADMIN/modUtente.jsp";

                RequestDispatcher rd = request.getRequestDispatcher(address);

                rd.forward(request,response);
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
}
