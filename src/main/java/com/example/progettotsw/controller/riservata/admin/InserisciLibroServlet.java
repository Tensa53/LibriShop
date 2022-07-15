package com.example.progettotsw.controller.riservata.admin;


import com.example.progettotsw.controller.Forms;
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
import java.sql.Array;
import java.util.*;

@WebServlet("/inserisci-libro")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50) //50MB
public class InserisciLibroServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (utente != null) {
            if (utente.isAmministratore()) {
                String isbn = request.getParameter("isbn");
                String titolo = request.getParameter("titolo");
                String autoreCF = request.getParameter("autore");
                String[] genere = request.getParameterValues("genere");
                String altro = request.getParameter("altro");
                String descrizione = request.getParameter("descrizione");
                String prezzoString = request.getParameter("prezzo");
                String disponibilitaString = request.getParameter("disponibilita");
                String data = request.getParameter("dataPubblicazione");
                String editore = request.getParameter("editore");
                Part foto = request.getPart("foto");

                boolean compilazioneForm = isbn != null && titolo != null && autoreCF != null && genere != null && descrizione != null && prezzoString != null && disponibilitaString != null && data != null && editore != null && foto != null;

                if (compilazioneForm) {
                    GenereDAO genereDAO = new GenereDAO();

                    LibroDAO libroDAO = new LibroDAO();

                    AutoreDAO autoreDAO = new AutoreDAO();

                    Libro librodb = libroDAO.doRetrieveById(isbn);

                    String autoreString = autoreDAO.doRetrievebyCF(autoreCF).getNome();

                    Genere generedbaltro = genereDAO.doRetrievebyNome(altro);

                    boolean validazioneForm = Forms.validateFormLibro(isbn,titolo,altro,descrizione,editore,librodb,generedbaltro,request);

                    if (validazioneForm) {
                        BigDecimal prezzo = new BigDecimal(prezzoString);
                        BigDecimal sconto = new BigDecimal(request.getParameter("sconto"));
                        int disponibilita = Integer.parseInt(disponibilitaString);

                        ArrayList<String> generi = new ArrayList<>();
                        Collections.addAll(generi, genere);

                        String uploadPath = getServletContext().getRealPath("") + "img";
                        log(uploadPath);

                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists())
                            uploadDir.mkdir();

                        String imgpath = uploadPath + File.separator + foto.getSubmittedFileName();

                        foto.write(imgpath);

                        String subpath = "./img/" + foto.getSubmittedFileName();

                        int year = Integer.parseInt(data.split("-")[0]);
                        int month = Integer.parseInt(data.split("-")[1]);
                        int day = Integer.parseInt(data.split("-")[2]);

                        GregorianCalendar dataPubblicazione = new GregorianCalendar(year, month, day);

                        Libro libro = new Libro(isbn, titolo, prezzo, dataPubblicazione, editore, sconto, disponibilita, subpath, descrizione);

                        Autore autore = autoreDAO.doRetrievebyName(autoreString);

                        if (altro.length() > 0) {
                            generi.add(altro);
                            genereDAO.doSave(altro);
                        }

                        String msg = null;

                        if (libroDAO.doSave(libro, autore.getCF(), generi) == 1)
                            msg = "Inserimento effettuate con successo !!! Torna alla <a href = \"" + request.getContextPath() + "/area-riservata\"> dashboard </a> oppure effettua un altro inserimento";

                        request.setAttribute("msg", msg);

                        request.setAttribute("generi", genereDAO.doRetrieveAll());

                        request.setAttribute("autori", autoreDAO.doRetrieveAll());

                    }

                    String address = "/WEB-INF/ADMIN/insLibro.jsp";

                    RequestDispatcher rd = request.getRequestDispatcher(address);
                    rd.forward(request, response);
                } else
                    response.sendRedirect(request.getContextPath() + "/admin-forward-redirect?insLibro=Inserisci%20Libro");



            } else response.sendRedirect(request.getContextPath() + "/home");

        } else response.sendRedirect(request.getContextPath() + "/home");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
