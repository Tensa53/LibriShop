package com.example.progettotsw.controller.riservata.utente;

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
import java.util.Objects;

@WebServlet("/conferma-modifiche-utente-cliente")
public class ConfermaModificheUtenteClienteServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (!utente.isAmministratore()) {
                String mail = request.getParameter("mail");
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String password = request.getParameter("password");

                boolean amministratore = Boolean.parseBoolean(request.getParameter("amministratore"));

                boolean compilazioneForm = mail != null && nome != null && cognome != null;

                boolean validazioneForm;

                UtenteDAO utenteDAO = new UtenteDAO();

                if (password == null || password.length() == 0)
                    validazioneForm = Forms.validateFormUtente(mail,nome,cognome,null,request,null);
                else
                    validazioneForm = Forms.validateFormUtente(mail,nome,cognome,password,request,null);

                if (compilazioneForm && validazioneForm) {
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

                    request.getSession().removeAttribute("utente");

                    request.getSession().setAttribute("utente",utenteDAO.doRetrieveByMail(mail));
                }

                String address = "/WEB-INF/UTENTE/iMieiDati.jsp";

                RequestDispatcher rd = request.getRequestDispatcher(address);
                rd.forward(request, response);
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
