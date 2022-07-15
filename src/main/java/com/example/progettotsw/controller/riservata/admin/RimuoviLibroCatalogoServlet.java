package com.example.progettotsw.controller.riservata.admin;

import com.example.progettotsw.model.Libro;
import com.example.progettotsw.model.LibroDAO;
import com.example.progettotsw.model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/rimuovi-libro-catalogo")
public class RimuoviLibroCatalogoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String isbn = request.getParameter("isbn-libro");

                if (isbn != null) {
                    LibroDAO libroDAO = new LibroDAO();

                    String msg = null;

                    int row = libroDAO.doDeleteLibrobyID(isbn);
                    if (row == 1)
                        msg = "Rimozione effettuata con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata" + "\"> dashboard </a> oppure effettua altre rimozioni";

                    request.setAttribute("msg", msg);

                    String address = "/WEB-INF/ADMIN/modDelLibro.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);

                    rd.forward(request, response);
                } else
                    response.sendRedirect(request.getContextPath() + "/admin-forward-redirect?modDelLibro=Modifica/Rimuovi%20Libro");


            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");


    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
