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
import java.util.List;

@WebServlet("/conferma-modifiche-indirizzo")
public class ConfermaModificheIndirizzoUtenteServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (!utente.isAmministratore()) {
            String via = request.getParameter("via");
            String civico = request.getParameter("civico");
            String provincia = request.getParameter("provincia");
            String citta = request.getParameter("citta");
            String CAP = request.getParameter("cap");
            String mail = utente.getMail();
            String viaF = request.getParameter("viaF");
            String civicoF = request.getParameter("civicoF");
            String cittaF = request.getParameter("cittaF");

            boolean compilazioneForm = via != null && civico != null && provincia != null && citta != null && CAP != null;

            if (compilazioneForm){
                ProvinciaDAO provinciaDAO = new ProvinciaDAO();

                String nomeProvincia = provinciaDAO.doRetrievebyId(Integer.parseInt(provincia)).getNome();

                Indirizzo i = new Indirizzo(via,civico,citta,CAP,nomeProvincia);
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

                String msg = null;

                if (indirizzoDAO.doUpdateIndirizzoByViaCivicoCitta(i,viaF,civicoF,cittaF,mail) == 1){
                    msg = "Modifiche effettuate con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

                    request.setAttribute("msg", msg);

                    request.getSession().removeAttribute("indirizzi");

                    request.getSession().setAttribute("indirizzi",indirizzoDAO.doRetrievebyUserMail(mail));
                }

            }

                String address = "/WEB-INF/ADMIN/modDelUtente.jsp";

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
