package com.example.progettotsw.controller.riservata.admin;

import com.example.progettotsw.controller.Forms;
import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/inserisci-genere")
public class InserisciGenereServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String nome = request.getParameter("genere");

                LibroDAO libroDAO = new LibroDAO();

                GenereDAO genereDAO = new GenereDAO();

                AutoreDAO autoreDAO = new AutoreDAO();

                List<Libro> libri = libroDAO.doRetrievebyGenere(nome);

                Genere generdb = genereDAO.doRetrievebyNome(nome);

                boolean compilazioneForm = nome != null;

                boolean validazioneForm = Forms.validateFormGenere(nome,generdb,request);

                if (compilazioneForm && validazioneForm){
                    genereDAO.doSave(nome);
                    request.setAttribute("msg","Inserimento effettuate con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a> oppure effettua un altro inserimento");
                }

                String address = "/WEB-INF/ADMIN/opsAutoreGenere.jsp";

                request.setAttribute("autori", autoreDAO.doRetrieveAll());

                request.setAttribute("generi", genereDAO.doRetrieveAll());

                RequestDispatcher rd = request.getRequestDispatcher(address);

                rd.forward(request, response);
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
