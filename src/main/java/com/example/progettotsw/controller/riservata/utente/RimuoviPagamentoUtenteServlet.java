package com.example.progettotsw.controller.riservata.utente;

import com.example.progettotsw.model.Pagamento;
import com.example.progettotsw.model.PagamentoDAO;
import com.example.progettotsw.model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.GregorianCalendar;

import static java.lang.Integer.parseInt;

@WebServlet("/rimuovi-pagamento")
public class RimuoviPagamentoUtenteServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (!utente.isAmministratore()) {
                String numeroCarta = request.getParameter("numeroCartaF");
                String scadenza = request.getParameter("scadenzaF");
                String CCV = request.getParameter("CCVF");
                String mail = utente.getMail();

                String[] data = scadenza.split("-");
                String anno = data[0];
                String mese = data[1];
                String giorno = data[2];

                boolean compilazioneForm = numeroCarta != null && scadenza != null && CCV != null;

                if (compilazioneForm){
                    Pagamento p = new Pagamento(numeroCarta,new GregorianCalendar(parseInt(anno),parseInt(mese)-1,parseInt(giorno)),CCV);
                    PagamentoDAO pagamentoDAO = new PagamentoDAO();

                    String msg = null;

                    if (pagamentoDAO.doDeletePagamento(p,mail) == 1){
                        msg = "Rimozione effettuata con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

                        request.setAttribute("msg", msg);

                        request.getSession().removeAttribute("pagamenti");

                        request.getSession().setAttribute("pagamenti",pagamentoDAO.doRetrievebyUserMail(mail));
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


