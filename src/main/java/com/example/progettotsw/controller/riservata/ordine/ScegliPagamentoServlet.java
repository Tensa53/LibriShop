package com.example.progettotsw.controller.riservata.ordine;

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
import java.util.List;

@WebServlet("/scegli-pagamento")
public class ScegliPagamentoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (!utente.isAmministratore()) {
                PagamentoDAO pagamentoDAO = new PagamentoDAO();

                List<Pagamento> pagamenti = pagamentoDAO.doRetrievebyUserMail(utente.getMail());

                request.setAttribute("pagamenti",pagamenti);

                String address = "/WEB-INF/ORDINE/pagamenti.jsp";

                RequestDispatcher rd = request.getRequestDispatcher(address);

                rd.forward(request,response);
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }
}
