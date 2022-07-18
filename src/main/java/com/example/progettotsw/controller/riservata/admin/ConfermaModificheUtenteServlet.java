package com.example.progettotsw.controller.riservata.admin;

import com.example.progettotsw.controller.Forms;
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

                boolean compilazioneForm = mail != null && nome != null && cognome != null;

                if (compilazioneForm) {
                    boolean validazioneForm;

                    UtenteDAO utenteDAO = new UtenteDAO();

                    Utente utentemaildb = utenteDAO.doRetrieveByMail(mail);

                    if (password == null || password.length() == 0)
                        validazioneForm = Forms.validateFormUtente(null,nome,cognome,null,request,utentemaildb);
                    else
                        validazioneForm = Forms.validateFormUtente(null,nome,cognome,password,request,utentemaildb);

                    if (validazioneForm) {

                        Utente u = new Utente(mail,nome, cognome, amministratore);

                        if (password != null) {
                            if (password.length() > 0){
                                utente.setPassword(password);
                                utenteDAO.doUpdateUserPasswordByMail(utente.getPasswordhash(), mail);
                            }
                        }

                        String msg = null;

                        if (utenteDAO.doUpdateUser(u) == 1)
                            msg = "Modifiche effettuate con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

                        request.setAttribute("msg", msg);
                    }else
                        request.setAttribute("msgerr","Errore nella validazione dei campi del form !!!");

                    request.setAttribute("utenti",utenteDAO.doRetrieveAll());

                    String address = "/WEB-INF/ADMIN/modDelUtente.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);
                    rd.forward(request, response);
                } else
                    response.sendRedirect(request.getContextPath() + "/admin-forward-redirect?modDelUtente=Modifica/Rimuovi%20Utente");

            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
