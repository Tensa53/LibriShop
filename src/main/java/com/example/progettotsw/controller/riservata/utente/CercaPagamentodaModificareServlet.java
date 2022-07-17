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

@WebServlet("/cerca-pagamento-da-modificare")
public class CercaPagamentodaModificareServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null){
            if (!utente.isAmministratore()){
                String numerocarta = request.getParameter("numeroCartaF");

                if (numerocarta != null) {
                    PagamentoDAO pagamentoDAO = new PagamentoDAO();

                    Pagamento pagamento = pagamentoDAO.doRetrieveByNumeroCartaUtente(numerocarta,utente.getMail());

                    request.setAttribute("pagamento",pagamento);

                    String address = "/WEB-INF/UTENTE/modificaPagamento.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request,response);
                } else
                    response.sendRedirect(request.getContextPath() + "/user-forward-redirect?iMieiMetodiDiPagamento=i%20Miei%20Metodi%20Di%20Pagamento");

            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
