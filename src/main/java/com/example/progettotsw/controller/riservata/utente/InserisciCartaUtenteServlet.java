package com.example.progettotsw.controller.riservata.utente;

import com.example.progettotsw.controller.Forms;
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

@WebServlet("/inserisci-carta")
public class InserisciCartaUtenteServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (!utente.isAmministratore()) {
                String numeroCarta = request.getParameter("numeroCarta");
                String scadenza = request.getParameter("scadenza");
                String CCVr = request.getParameter("ccv");
                String mail = utente.getMail();

                String[] data = scadenza.split("-");
                String anno = data[0];
                String mese = data[1];
                String giorno = data[2];

                PagamentoDAO pagamentoDAO = new PagamentoDAO();

                boolean compilazioneForm = numeroCarta != null && scadenza != null && CCVr != null;

                if (compilazioneForm){

                    Pagamento pagamentodb = pagamentoDAO.doRetrieveByNumeroCartaUtente(numeroCarta,utente.getMail());

                    GregorianCalendar gregorianCalendar = new GregorianCalendar(parseInt(anno),parseInt(mese)-1,parseInt(giorno));

                    boolean validazioneForm = Forms.validateFormPagamento(numeroCarta,gregorianCalendar,CCVr,pagamentodb,null,request);

                    if (validazioneForm) {
                        Pagamento p = new Pagamento(numeroCarta,gregorianCalendar,CCVr);

                        String msg = null;

                        if (pagamentoDAO.doSaveByMail(p,mail) == 1){
                            msg = "Inserimento effettuato con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

                            request.setAttribute("msg", msg);
                        }
                    }

                    request.setAttribute("pagamenti",pagamentoDAO.doRetrievebyUserMail(mail));

                    String address = "/WEB-INF/UTENTE/pagamentiUtente.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);
                    rd.forward(request, response);

                } else
                    response.sendRedirect(request.getContextPath() + "/user-forward-redirect?iMieiMetodiDiPagamento=i%20Miei%20Metodi%20Di%20Pagamento");


            }

            else response.sendRedirect(request.getContextPath() + "/home");

        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
