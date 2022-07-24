package com.example.progettotsw.controller.riservata.utente;

import com.example.progettotsw.model.Comune;
import com.example.progettotsw.model.ComuneDAO;
import com.example.progettotsw.model.Utente;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/comuni")
public class ComuniAjaxServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if(utente != null) {
            if (!utente.isAmministratore()) {
                String idprovincia = request.getParameter("provincia");

                if (idprovincia != null) {
                    int id = Integer.parseInt(idprovincia);

                    System.out.println(id);

                    ComuneDAO comuneDAO = new ComuneDAO();

                    List<Comune> comuni = comuneDAO.doRetrieveAllbyProvincia(id);

                    for (Comune c : comuni) {
                        response.getWriter().print("<option value=\"" + c.getNome() + "\">" + c.getNome() + "</option>");
                    }
                } else
                    response.sendRedirect(request.getContextPath() + "/area-riservata");
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }
}
