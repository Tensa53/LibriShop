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

@WebServlet("/conferma-modifiche-pagamento")
public class ConfermaModifichePagamentoUtenteServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (!utente.isAmministratore()) {
                String numeroCarta = request.getParameter("numeroCarta");
                String scadenza = request.getParameter("scadenza");
                String CCV = request.getParameter("ccv");
                String numeroCartaF = request.getParameter("numeroCartaF");
                String scadenzaF = request.getParameter("scadenzaF");
                String CCVf = request.getParameter("CCVF");
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

                    if (pagamentoDAO.doUpdate(p,numeroCartaF,scadenzaF,CCVf,mail) == 1){
                        msg = "Modifiche effettuate con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

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
