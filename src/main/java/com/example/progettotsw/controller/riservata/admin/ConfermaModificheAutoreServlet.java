package com.example.progettotsw.controller.riservata.admin;

import com.example.progettotsw.model.Autore;
import com.example.progettotsw.model.AutoreDAO;
import com.example.progettotsw.model.GenereDAO;
import com.example.progettotsw.model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/conferma-modifiche-autore")
public class ConfermaModificheAutoreServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null){
            if (utente.isAmministratore()){
                String cf = request.getParameter("CF");
                String nome = request.getParameter("nome");

                if(cf != null && nome != null){
                    AutoreDAO autoreDAO = new AutoreDAO();

                    GenereDAO genereDAO = new GenereDAO();

                    Autore autore = new Autore(cf,nome);

                    autoreDAO.doUpdate(autore);

                    String msg = "Modifiche effettuate con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

                    request.setAttribute("autori",autoreDAO.doRetrieveAll());
                    request.setAttribute("generi",genereDAO.doRetrieveAll());
                    request.setAttribute("msg",msg);
                }

                String address = "/WEB-INF/ADMIN/opsAutoreGenere.jsp";

                RequestDispatcher rd = request.getRequestDispatcher(address);

                rd.forward(request,response);
            }else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }


    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
