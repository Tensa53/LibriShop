package com.example.progettotsw.controller.riservata.utente;

import com.example.progettotsw.model.Utente;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/user-forward-redirect")
public class UserForwardRedirect extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        String iMieiDati = request.getParameter("iMieiDati");
        String iMieiOrdini = request.getParameter("iMieiOrdini");
        String iMieiIndirizzi = request.getParameter("iMieiIndirizzi");
        String iMieiMetodiDiPagamento = request.getParameter("iMieiMetodiDiPagamento");

        if(utente != null) {
            if(!utente.isAmministratore()) {
                if (iMieiDati != null) {
                    response.sendRedirect(request.getContextPath() + "/home");
                }

                if (iMieiOrdini != null) {
                    response.sendRedirect(request.getContextPath() + "/home");
                }

                if (iMieiIndirizzi != null) {
                    response.sendRedirect(request.getContextPath() + "/home");
                }

                if (iMieiMetodiDiPagamento != null) {
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            } else
                response.sendRedirect(request.getContextPath() + "/home");

        } else
            response.sendRedirect(request.getContextPath() + "/home");

    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
