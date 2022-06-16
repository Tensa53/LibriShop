package com.example.progettotsw.controller;

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

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        log("sono nella servlet di login");


        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        UtenteDAO utenteDAO = new UtenteDAO();

        Utente utente =  utenteDAO.doRetrieveByMailPassword(mail,password);

        if(Objects.isNull(utente)) {
            String msg = "Mail o password errati !!";

            log("utente nullo");

            request.removeAttribute("mail");
            request.removeAttribute("password");

            request.setAttribute("msg",msg);

            String address = "./login.jsp";

            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

        else if(!utente.isAmministratore()) {
            log(utente.getUsername());

            CarrelloDAO carrelloDAO = new CarrelloDAO();

            Carrello carrelloDB = carrelloDAO.doRetrievebyUtente(utente.getMail());

            log(String.valueOf(Objects.isNull(carrelloDB)));

            Carrello carrelloSession = (Carrello) request.getSession().getAttribute("carrello");

            log(String.valueOf(Objects.isNull(carrelloSession)));

            BigDecimal zero = new BigDecimal(0.00);

            if(carrelloDB.getTotale().compareTo(zero) == 1 && carrelloSession.getTotale().compareTo(zero) == 1) { //nel caso sia il carrello di db che quello di sessione siano pieni
                request.getSession().removeAttribute("carrello");//rimuoviamo il vecchio carrello dalla session
                request.getSession().setAttribute("carrello",mergeCarrelli(carrelloDB,carrelloSession));//aggiungiamo il carrello risultato della merge
            }else if (carrelloDB.getTotale().compareTo(zero) == 1 && carrelloSession.getTotale().compareTo(zero) == 0){//nel caso in cui il carrello di sessione sia vuoto
                request.getSession().removeAttribute("carrello");//rimuoviamo il carrello vuoto dalla session
                request.getSession().setAttribute("carrello",carrelloDB);//aggiungiamo il carrello dal db alla session
            }
        }

        request.getSession().setAttribute("utente",utente);

        String address = "http://localhost:8080/progettoTSW_war_exploded/home";

        response.sendRedirect(address);
    }

    private Carrello merge(Carrello carrelloA, Carrello carrelloB){
        for (Dettaglio dettaglioB : carrelloB.getDettagli()){
            Dettaglio dettaglioA = null;

            if((dettaglioA = carrelloA.getDettagliobyISBN(dettaglioB.getLibro().getISBN())) != null) {
                dettaglioA.setQuantita(dettaglioA.getQuantita() + dettaglioB.getQuantita());
                BigDecimal prezzoDettaglioA = dettaglioA.getPrezzo();
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
