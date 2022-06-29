package com.example.progettotsw.controller.riservata.utente;

import com.example.progettotsw.model.Indirizzo;
import com.example.progettotsw.model.IndirizzoDAO;
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
                String via = request.getParameter("via");
                String civico = request.getParameter("civico");
                String provincia = request.getParameter("provincia");
                String citta = request.getParameter("citta");
                String CAP = request.getParameter("cap");
                String mail = utente.getMail();

                boolean compilazioneForm = via != null && civico != null && provincia != null && citta != null && CAP != null;

                if (compilazioneForm){
                    Indirizzo i = new Indirizzo(via,civico,citta,CAP,provincia);
                    IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

                    String msg = null;

                    if (indirizzoDAO.doDeleteIndirizzoByViaCivicoCitta(i,mail) == 1){
                        msg = "Rimozione effettuata con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

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

