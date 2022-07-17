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

@WebServlet("/inserisci-indirizzo")
public class InserisciIndirizzoUtenteServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (!utente.isAmministratore()) {
                String via = request.getParameter("viar");
                String civico = request.getParameter("civicor");
                String provincia = request.getParameter("provinciar");
                String citta = request.getParameter("cittar");
                String CAP = request.getParameter("capr");
                String mail = utente.getMail();

                boolean compilazioneForm = via != null && civico != null && provincia != null && citta != null && CAP != null;

                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

                ProvinciaDAO provinciaDAO = new ProvinciaDAO();

                if (compilazioneForm){

                    int idprovincia = Integer.parseInt(provincia);

                    String nomeProvincia = provinciaDAO.doRetrievebyId(idprovincia).getNome();

                    Indirizzo indirizzodb = indirizzoDAO.doRetrieveByViaCivicoCittaUtente(via, civico, citta, mail);

                    boolean validazioneForm = Forms.validateFormIndirizzo(via, civico, citta, nomeProvincia, CAP, null, indirizzodb, request);

                    if (validazioneForm) {
                        Indirizzo i = new Indirizzo(via,civico,citta,CAP,nomeProvincia);

                        String msg = null;

                        if (indirizzoDAO.doSaveWithMail(i,mail) == 1){
                            msg = "Inserimento effettuato con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

                            request.setAttribute("msg", msg);
                        }
                    }
                } else
                    response.sendRedirect(request.getContextPath() + "/user-forward-redirect?iMieiIndirizzi=i%20Miei%20Indirizzi");

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

