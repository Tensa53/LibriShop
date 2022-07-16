package com.example.progettotsw.controller.riservata.utente;

import com.example.progettotsw.model.Indirizzo;
import com.example.progettotsw.model.IndirizzoDAO;
import com.example.progettotsw.model.ProvinciaDAO;
import com.example.progettotsw.model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/rimuovi-indirizzo")
public class RimuoviIndirizzoServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (!utente.isAmministratore()) {
                String via = request.getParameter("viaF");
                String civico = request.getParameter("civicoF");
                String citta = request.getParameter("cittaF");
                String mail = utente.getMail();

                boolean compilazioneForm = via != null && civico != null && citta != null;

                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

                ProvinciaDAO provinciaDAO = new ProvinciaDAO();

                if (compilazioneForm){

                    String msg = null;

                    if (indirizzoDAO.doDeleteIndirizzoByViaCivicoCitta(via,civico,citta,mail) == 1){
                        msg = "Rimozione effettuata con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

                        request.setAttribute("msg", msg);
                    }

                }

                request.setAttribute("province",provinciaDAO.doRetrieveAll());

                request.setAttribute("indirizzi",indirizzoDAO.doRetrievebyUserMail(mail));

                String address = "/WEB-INF/UTENTE/indirizziUtente.jsp";

                RequestDispatcher rd = request.getRequestDispatcher(address);
                rd.forward(request, response);

            }

            else response.sendRedirect(request.getContextPath() + "/home");

        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}

