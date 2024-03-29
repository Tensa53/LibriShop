package com.example.progettotsw.controller.riservata.admin;

import com.example.progettotsw.controller.Forms;
import com.example.progettotsw.model.CarrelloDAO;
import com.example.progettotsw.model.Utente;
import com.example.progettotsw.model.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/inserisci-utente")
public class InserisciUtenteServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String mail = request.getParameter("mailr");
                String nome = request.getParameter("nomer");
                String cognome = request.getParameter("cognomer");
                String password = request.getParameter("passwordr");
                boolean amministratore = Boolean.parseBoolean(request.getParameter("amministratorer"));

                boolean compilazioneForm = mail != null && nome != null && cognome != null && password != null;

                if (compilazioneForm) {

                    UtenteDAO utenteDAO = new UtenteDAO();

                    Utente utentemaildb = utenteDAO.doRetrieveByMail(mail);

                    boolean validazioneForm = Forms.validateFormUtente(mail,nome,cognome,password,request,utentemaildb);

                    if (validazioneForm) {
                        Utente nuovoUtente = new Utente(mail,nome, cognome, amministratore);
                        nuovoUtente.setPassword(password);

                        int row = utenteDAO.doSave(nuovoUtente);

                        if (!amministratore) {
                            CarrelloDAO carrelloDAO = new CarrelloDAO();

                            carrelloDAO.doCreate(mail);
                        }

                        String msg = null;

                        if (row == 1)
                            msg = "Inserimento effettuato con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a> oppure effettua un altro inserimento";

                        request.setAttribute("msg", msg);
                    }

                    String address = "/WEB-INF/ADMIN/insUtente.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                } else
                    response.sendRedirect(request.getContextPath() + "/admin-forward-redirect?insUtente=Inserisci%20Utente");



            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
