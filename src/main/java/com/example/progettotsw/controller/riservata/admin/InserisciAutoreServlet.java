package com.example.progettotsw.controller.riservata.admin;

import com.example.progettotsw.model.Autore;
import com.example.progettotsw.model.AutoreDAO;
import com.example.progettotsw.model.GenereDAO;
import com.example.progettotsw.model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/inserisci-autore")
public class InserisciAutoreServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String cf = request.getParameter("CF");
                String nome = request.getParameter("nome");

                AutoreDAO autoreDAO = new AutoreDAO();

                GenereDAO genereDAO = new GenereDAO();

                if (cf != null && nome != null) {

                    Autore autore = new Autore(cf, nome);

                    String msg = null;

                    if (autoreDAO.doSave(autore) == 1)
                        msg = "msg = \"Inserimento effettuato con successo !!! Torna alla <a href = \\\"\" + request.getContextPath() + \"/area-riservata\\\"> dashboard </a>\";";
                }

                request.setAttribute("autori", autoreDAO.doRetrieveAll());
                request.setAttribute("generi", genereDAO.doRetrieveAll());

                String address = "/WEB-INF/ADMIN/opsAutoreGenere.jsp";

                RequestDispatcher rd = request.getRequestDispatcher(address);

                rd.forward(request, response);
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
