package com.example.progettotsw.controller.riservata.utente;

import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/cerca-indirizzo-da-modificare")
public class CercaIndirizzodaModificareServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null){
            if (!utente.isAmministratore()){
                String viaF = request.getParameter("viaF");
                String civicoF = request.getParameter("civicoF");
                String cittaF = request.getParameter("cittaF");

                boolean compilazioneForm = viaF != null && civicoF != null && cittaF != null;

                if (compilazioneForm) {
                    IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

                    ProvinciaDAO provinciaDAO = new ProvinciaDAO();

                    List<Provincia> province = provinciaDAO.doRetrieveAll();

                    Indirizzo indirizzo = indirizzoDAO.doRetrieveByViaCivicoCittaUtente(viaF,civicoF,cittaF,utente.getMail());

                    Provincia provincia = provinciaDAO.doRetrievebyNome(indirizzo.getProvincia());

                    request.setAttribute("indirizzo",indirizzo);

                    request.setAttribute("province",province);

                    request.setAttribute("provincia",provincia);

                    String address = "/WEB-INF/UTENTE/modificaIndirizzo.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request,response);
                } else
                    response.sendRedirect(request.getContextPath() + "/user-forward-redirect?iMieiIndirizzi=i%20Miei%20Indirizzi");

            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
}
