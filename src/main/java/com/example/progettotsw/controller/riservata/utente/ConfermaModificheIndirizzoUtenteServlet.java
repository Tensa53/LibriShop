package com.example.progettotsw.controller.riservata.utente;


import com.example.progettotsw.controller.Forms;
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

                if (compilazioneForm) {

                    int idprovincia = Integer.parseInt(provincia);

                    ProvinciaDAO provinciaDAO = new ProvinciaDAO();

                    String nomeProvincia = provinciaDAO.doRetrievebyId(idprovincia).getNome();

                    IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

                    Indirizzo oldindirizzodb = indirizzoDAO.doRetrieveByViaCivicoCittaUtente(viaF, civicoF, cittaF, mail);

                    Indirizzo indirizzodb = indirizzoDAO.doRetrieveByViaCivicoCittaUtente(via, civico, citta, mail);

                    boolean validazioneForm = Forms.validateFormIndirizzo(via, civico, citta, nomeProvincia, CAP, oldindirizzodb, indirizzodb, request);

                    if (validazioneForm) {
                        Indirizzo i = new Indirizzo(via, civico, citta, CAP, nomeProvincia);

                        String msg = null;

                        if (indirizzoDAO.doUpdateIndirizzoByViaCivicoCitta(i, viaF, civicoF, cittaF, mail) == 1) {
                            msg = "Modifiche effettuate con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

                            request.setAttribute("msg", msg);
                        }
                    } else
                        request.setAttribute("msgerrmod","Errore nella compilazione del form di modifica indirizzo !!!");

                    request.setAttribute("indirizzi", indirizzoDAO.doRetrievebyUserMail(mail));

                    request.setAttribute("province", provinciaDAO.doRetrieveAll());

                    String address = "/WEB-INF/UTENTE/indirizziUtente.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);
                    rd.forward(request, response);

                } else
                    response.sendRedirect(request.getContextPath() + "/user-forward-redirect?iMieiIndirizzi=i%20Miei%20Indirizzi");

            } else
                response.sendRedirect(request.getContextPath() + "/home");

        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
