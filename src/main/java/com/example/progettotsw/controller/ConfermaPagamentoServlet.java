package com.example.progettotsw.controller;

import com.example.progettotsw.model.Pagamento;
import com.example.progettotsw.model.PagamentoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/conferma-pagamento")
public class ConfermaPagamentoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numeroCarta = request.getParameter("numeroCarta");

        PagamentoDAO pagamentoDAO = new PagamentoDAO();

        Pagamento pagamento = pagamentoDAO.doRetrieveByNumeroCarta(numeroCarta);

        request.getSession().setAttribute("pagamento",pagamento);

        String address = "/WEB-INF/ordine.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(address);

        rd.forward(request,response);
    }
}
