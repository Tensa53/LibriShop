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
import java.util.Arrays;
import java.util.GregorianCalendar;

@WebServlet("/conferma-modifiche-libro")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50) //50MB
public class ConfermaModificheLibroServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String isbn = request.getParameter("isbn");
                log("ISBN : " + isbn);
                String titolo = request.getParameter("titolo");
                log("Titolo : " + titolo);
                String autoreString = request.getParameter("autore");
                log("Autore : " + autoreString);
                String[] genere = request.getParameterValues("genere");
                log(Arrays.toString(genere));
                String descrizione = request.getParameter("descrizione");
                log(descrizione);
                String prezzoString = request.getParameter("prezzo");
                log(prezzoString);
                BigDecimal prezzo = new BigDecimal(prezzoString);
                String data = request.getParameter("dataPubblicazione");
                String editore = request.getParameter("editore");
                BigDecimal sconto = new BigDecimal(request.getParameter("sconto"));
                int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
                Part foto = request.getPart("foto");

                GenereDAO genereDAO = new GenereDAO();

                int year = Integer.parseInt(data.split("-")[0]);
                int month = Integer.parseInt(data.split("-")[1]);
                int day = Integer.parseInt(data.split("-")[2]);

                GregorianCalendar dataPubblicazione = new GregorianCalendar(year, month, day);

                Libro libro = new Libro(isbn, titolo, descrizione, prezzo, dataPubblicazione, editore, sconto, disponibilita);

                LibroDAO libroDAO = new LibroDAO();

                Libro oldLibro = libroDAO.doRetrieveById(isbn);

                AutoreDAO autoreDAO = new AutoreDAO();

                Autore autore = autoreDAO.doRetrievebyName(autoreString);

                log(foto.getSubmittedFileName());

                log(String.valueOf(foto.getSubmittedFileName().length()));

                if (foto.getSubmittedFileName().length() > 0) {
                    String uploadPath = getServletContext().getRealPath("") + "img";

                    log(uploadPath);

                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists())
                        uploadDir.mkdir();

                    String imgpath = uploadPath + File.separator + foto.getSubmittedFileName();

                    File oldimg = new File(getServletContext().getRealPath("") + oldLibro.getFoto().substring(1));

                    oldimg.delete();

                    foto.write(imgpath);

                    String subpath = "./img/" + foto.getSubmittedFileName();

                    libroDAO.doUpdateFoto(libro, subpath);
                }

                String msg = null;

                if (libroDAO.doUpdate(libro, autore.getCF(), genere) == 1)
                        msg = "Modifiche effettuate con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a>";

                String address = "/WEB-INF/ADMIN/modDelLibro.jsp";

                request.setAttribute("msg",msg);

                request.getSession().removeAttribute("libri");
                request.getSession().removeAttribute("generi");

                RequestDispatcher rd = request.getRequestDispatcher(address);
                rd.forward(request, response);
            } else
                response.sendRedirect(request.getContextPath() + "/home");
        } else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
