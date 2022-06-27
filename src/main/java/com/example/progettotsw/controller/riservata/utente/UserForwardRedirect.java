package com.example.progettotsw.controller.riservata.utente;

import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/user-forward-redirect")
public class UserForwardRedirect extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        String iMieiDati = request.getParameter("iMieiDati");
        String iMieiOrdini = request.getParameter("iMieiOrdini");
        String iMieiIndirizzi = request.getParameter("iMieiIndirizzi");
        String iMieiMetodiDiPagamento = request.getParameter("iMieiMetodiDiPagamento");

        if(utente != null) {
            if(!utente.isAmministratore()) {
                if (iMieiDati != null) {
                    String address = "/WEB-INF/UTENTE/iMieiDati.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request,response);
                }

                if (iMieiOrdini != null) {
                    OrdineDAO ordineDAO = new OrdineDAO();

                    List<Ordine> ordini = ordineDAO.doRetrieveAllbyUserMail(utente.getMail());

                    request.setAttribute("ordini", ordini);

                    String address = "/WEB-INF/UTENTE/ordini.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                }

                if (iMieiIndirizzi != null) {
                    IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

                    List<Indirizzo> indirizzi = indirizzoDAO.doRetrievebyUserMail(utente.getMail());

                    request.setAttribute("indirizzi", indirizzi);

                    String address = "/WEB-INF/UTENTE/indirizziUtente.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                }

                if (iMieiMetodiDiPagamento != null) {
                    PagamentoDAO pagamentoDAO = new PagamentoDAO();

                    List<Pagamento> pagamenti = pagamentoDAO.doRetrievebyUserMail(utente.getMail());

                    request.setAttribute("pagamenti",pagamenti);

                    String address = "/WEB-INF/UTENTE/pagamentiUtente.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                }
            } else
                response.sendRedirect(request.getContextPath() + "/home");

        } else
            response.sendRedirect(request.getContextPath() + "/home");

    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
}
