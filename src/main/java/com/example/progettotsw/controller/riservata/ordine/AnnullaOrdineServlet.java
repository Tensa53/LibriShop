package com.example.progettotsw.controller.riservata.ordine;

import com.example.progettotsw.model.Ordine;
import com.example.progettotsw.model.OrdineDAO;
import com.example.progettotsw.model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@WebServlet("/annulla-ordine")
public class AnnullaOrdineServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        int id = Integer.parseInt(request.getParameter("id"));

        OrdineDAO ordineDAO = new OrdineDAO();

        Ordine ordine = ordineDAO.doRetrievebyId(id);

        GregorianCalendar momentoOrdine24 = ordine.getDataOrdine();

        System.out.println(momentoOrdine24.getTime());

        momentoOrdine24.add(GregorianCalendar.DAY_OF_MONTH,1);

        System.out.println(momentoOrdine24.getTime());

        GregorianCalendar momentoOra = new GregorianCalendar();

        System.out.println(momentoOra.getTime());

        String address = "/WEB-INF/UTENTE/ordini.jsp";

        boolean ordineAnnullabile = momentoOra.before(momentoOrdine24);

        if (ordineAnnullabile) {
            ordineDAO.doRemovebyId(id);
            String success = "Ordine N." + id + " rimosso con successo";
            request.setAttribute("msgsuccess",success);
            request.setAttribute("ordini",ordineDAO.doRetrieveAllbyUserMail(utente.getMail()));
            RequestDispatcher rd = request.getRequestDispatcher(address);
            rd.forward(request,response);
        } else {
            String error = "Ordine N." + id + " non è più annullabile dopo 24 ore";
            request.setAttribute("msgerror",error);
            request.setAttribute("ordini",ordineDAO.doRetrieveAllbyUserMail(utente.getMail()));
            RequestDispatcher rd = request.getRequestDispatcher(address);
            rd.forward(request,response);
        }
    }

    public void doGet(HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
        doPost(request,response);
    }
}
