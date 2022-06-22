package com.example.progettotsw.controller.riservata.admin;


import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

@WebServlet("/inserisci-libro")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50) //50MB
public class InserisciLibroServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log(request.getServletPath());

        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String isbn = request.getParameter("isbn");
                String titolo = request.getParameter("titolo");
                String autoreString = request.getParameter("autore");
                String[] genere = request.getParameterValues("genere");
                log(genere.toString());
                String descrizione = request.getParameter("descrizione");
                BigDecimal prezzo = new BigDecimal(request.getParameter("prezzo"));
                String data = request.getParameter("dataPubblicazione");
                String editore = request.getParameter("editore");
                BigDecimal sconto = new BigDecimal(request.getParameter("sconto"));
                int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
                Part foto = request.getPart("foto");

                log(data);

                String uploadPath = getServletContext().getRealPath("") + "img";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();

                String imgpath = uploadPath + File.separator + foto.getSubmittedFileName();

                foto.write(imgpath);

                String subpath = "./img/" + foto.getSubmittedFileName();

                int year = Integer.parseInt(data.split("-")[0]);
                int month = Integer.parseInt(data.split("-")[1]);
                int day = Integer.parseInt(data.split("-")[2]);

                GregorianCalendar dataPubblicazione = new GregorianCalendar(year, month, day);

                Libro libro = new Libro(isbn, titolo, descrizione, prezzo, dataPubblicazione, editore, sconto, disponibilita, subpath);

                LibroDAO libroDAO = new LibroDAO();

                AutoreDAO autoreDAO = new AutoreDAO();

                Autore autore = autoreDAO.doRetrievebyName(autoreString);

                String msg = null;

                if (libroDAO.doSave(libro, autore.getCF(), genere) == 1)
                    msg = "Inserimento effettuate con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a> oppure effettua un altro inserimento";

                String address = "/WEB-INF/ADMIN/insLibro.jsp";

                request.setAttribute("msg", msg);

                GenereDAO genereDAO = new GenereDAO();

                request.setAttribute("generi", genereDAO.doRetrieveAll());

                RequestDispatcher rd = request.getRequestDispatcher(address);
                rd.forward(request, response);
            } else response.sendRedirect(request.getContextPath() + "/home");
        } else response.sendRedirect(request.getContextPath() + "/home");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
