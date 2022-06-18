package com.example.progettotsw.controller.riservata;

import com.example.progettotsw.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        UtenteDAO utenteDAO = new UtenteDAO();

        Utente utente =  utenteDAO.doRetrieveByMailPassword(mail,password);

        if (utente != null) {
            if(!utente.isAmministratore()) {
                log(utente.getUsername());

                CarrelloDAO carrelloDAO = new CarrelloDAO();

                Carrello carrelloDB = carrelloDAO.doRetrievebyUtente(utente.getMail());

                log(String.valueOf(Objects.isNull(carrelloDB)));

                Carrello carrelloSession = (Carrello) request.getSession().getAttribute("carrello");

                log(String.valueOf(Objects.isNull(carrelloSession)));

                BigDecimal zero = new BigDecimal(0.00);

                //un carrello può considerarsi vuoto quando il totale è azzerato,se si verifica un errore di prezzo sul totale si controlla anche che non ci sia nessun dettaglio al suo interno

                boolean statoCarrelloDB = carrelloDB.getTotale().compareTo(zero) == 0 || carrelloDB.getDettagli().size() == 0;//true = vuoto ; false = pieno

                boolean statoCarrelloSession = carrelloSession.getTotale().compareTo(zero) == 0 || carrelloSession.getDettagli().size() == 0;

                if(!statoCarrelloDB && !statoCarrelloSession) {
                    request.getSession().removeAttribute("carrello");//rimuoviamo il vecchio carrello dalla session
                    request.getSession().setAttribute("carrello",mergeCarrelli(carrelloDB,carrelloSession));//aggiungiamo il carrello risultato della merge
                } else if (!statoCarrelloDB && statoCarrelloSession) {
                    request.getSession().removeAttribute("carrello");//rimuoviamo il carrello vuoto dalla session
                    request.getSession().setAttribute("carrello",carrelloDB);//aggiungiamo il carrello dal db alla session
                }
            }

            request.getSession().setAttribute("utente",utente);

            String address = "http://localhost:8080/progettoTSW_war_exploded/home";

            response.sendRedirect(address);
        } else {
            String msg = "Mail o password errati !!";

            log("utente nullo");

            request.removeAttribute("mail");
            request.removeAttribute("password");

            request.setAttribute("msg",msg);

            String address = "./login.jsp";

            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private Carrello merge(Carrello carrelloA, Carrello carrelloB){
        for (Dettaglio dettaglioB : carrelloB.getDettagli()){
            Dettaglio dettaglioA = null;

            if((dettaglioA = carrelloA.getDettagliobyISBN(dettaglioB.getLibro().getISBN())) != null) {
                dettaglioA.setQuantita(dettaglioA.getQuantita() + dettaglioB.getQuantita());
                BigDecimal prezzoDettaglioA = dettaglioA.getPrezzo();
                if(dettaglioA.getQuantita() > 5)
                    prezzoDettaglioA = prezzoDettaglioA.multiply(new BigDecimal(5.00));
                else
                    prezzoDettaglioA = prezzoDettaglioA.multiply(new BigDecimal(dettaglioA.getQuantita()));

                dettaglioA.setPrezzo(prezzoDettaglioA);
                BigDecimal totaleCarrelloA = carrelloA.getTotale();
                totaleCarrelloA = totaleCarrelloA.add(dettaglioA.getPrezzo());
                carrelloA.setTotale(totaleCarrelloA);
            }else {
                carrelloA.addDettaglio(dettaglioB);
                BigDecimal totaleCarrelloA = carrelloA.getTotale();
                totaleCarrelloA = totaleCarrelloA.add(dettaglioB.getPrezzo());
                carrelloA.setTotale(totaleCarrelloA);
            }
        }

        return carrelloA;
    }

    private Carrello mergeCarrelli(Carrello carrelloDB,Carrello carrelloSession) {
        if(carrelloDB.getDettagli().size() > carrelloSession.getDettagli().size()){ //appendo al carrello più grande
            return merge(carrelloDB,carrelloSession); //merge del secondo nel primo, carrelloDB = carrelloA; carrelloSession = carrelloB;
        } else {
            return merge(carrelloSession,carrelloDB); //merge del secondo nel primo, carrelloSession = carrelloA; carrelloDB = carrelloB;
        }
    }
}
