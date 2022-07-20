package com.example.progettotsw.controller;

import com.example.progettotsw.model.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Forms {

    public static boolean validateFormUtente(String mail,String nome, String cognome, String password, HttpServletRequest request, Utente utentemaildb) {
        int c = 0;

        if(nome.length() > 20) {
            request.setAttribute("msgnomeP","Il campo nome non può superare i 20 caratteri");
            c++;
        }

        if(cognome.length() > 20) {
            request.setAttribute("msgcognomeP","Il campo cognome non può superare i 20 caratteri");
            c++;
        }

        if (mail != null) {
            //regex per la mail

            Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
            Matcher matcher = pattern.matcher(mail);

            if(mail.length() > 50) {
                request.setAttribute("msgmailP","Il campo mail non può superare i 50 caratteri");
                c++;
            }

            if (!matcher.matches()) {
                request.setAttribute("msgcontrollomail","Il formato dell'email deve essere del tipo nomecasella@tuodominio.it");
                c++;
            }

            if(utentemaildb != null) {
                if (utentemaildb.getMail().equalsIgnoreCase(mail)) {
                    request.setAttribute("msgmailinuso","Mail già in uso");
                    c++;
                }
            }
        }

        if(password != null) {
            //regex per la password

            Pattern pattern1 = Pattern.compile("(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[A-Z]).{8,}");
            Matcher matcher1 = pattern1.matcher(password);

            if (password.length() > 40) {
                request.setAttribute("msgpasswordP","Il campo password non può superare i 40 caratteri");
                c++;
            }

            if (!matcher1.matches()) {
                request.setAttribute("msgcontrollopassword","Rispetta i criteri per la password");
                c++;
            }
        }

        return !(c > 0);
    }

    public static boolean validateFormIndirizzo(String via, String civico, String citta, String provincia, String cap, Indirizzo oldindirizzodb, Indirizzo indirizzodb, HttpServletRequest request) {
        int c = 0;

        Indirizzo newindirizzo = new Indirizzo(via,civico,citta,cap,provincia);

        if (oldindirizzodb != null){
            if (newindirizzo.equals(oldindirizzodb)) {
                return true;
            }
        }

        if (via.length() > 40) {
            request.setAttribute("msgviaP","La via non deve superare i 40 caratteri");
            c++;
        }

        if (civico.length() > 5) {
            request.setAttribute("msgcivicoP","Il numero civico ha massimo 5 cifre");
            c++;
        }

        if (cap.length() != 5) {
            request.setAttribute("msgcapP", "Il CAP deve essere di 5 cifre");
        }

        if (indirizzodb != null) {
            boolean res = true;

            if (oldindirizzodb != null)
                res = !(indirizzodb.equals(oldindirizzodb));

            if (res) {
                boolean duplicato = indirizzodb.getVia().equals(via) && indirizzodb.getCivico().equals(civico) && indirizzodb.getCitta().equals(citta);

                if (duplicato) {
                    request.setAttribute("controlloindirizzo", "Indirizzo già in uso");
                    c++;
                }
            }
        }

        return !(c > 0);
    }

    public static boolean validateFormPagamento(String numeroCarta, GregorianCalendar scadenza, String ccv, Pagamento pagamentodb,Pagamento oldpagamentodb, HttpServletRequest request) {
        int c = 0;

        Pagamento newpagamento = new Pagamento(numeroCarta,scadenza,ccv);

        GregorianCalendar oggi = new GregorianCalendar();

        if (oldpagamentodb != null){
            if (oldpagamentodb.equals(newpagamento)){
                return true;
            }
        }

        if (numeroCarta.length() > 16){
            request.setAttribute("msgnumerocartaP","Il numero carta ha massimo 16 cifre");
            c++;
        }

        if (ccv.length() != 3) {
            request.setAttribute("msgccvP", "Il ccv contiene 3 cifre");
            c++;
        }

        if (scadenza.before(oggi)) {
            request.setAttribute("msgscadenzaP", "non valida");
            c++;
        }

        if (pagamentodb != null){
            boolean res = true;

            if (oldpagamentodb != null)
                res = !(pagamentodb.equals(oldpagamentodb));

            if (res) {
                boolean duplicato = pagamentodb.getNumeroCarta().equals(numeroCarta);

                if (duplicato){
                    request.setAttribute("msgcontrollonumerocarta","Carta già in uso");
                    c++;
                }
            }


        }

        return !(c > 0);

    }

    public static boolean validateFormLibro(String isbn, String titolo, String[] genere, String altro, String descrizione, String editore, Libro librodb, Genere generedbaltro, HttpServletRequest request) {
        int c = 0;

        if(isbn != null) {
            if(isbn.length() != 13){
                request.setAttribute("msgisbnP","Il codice ISBN deve essere di 13 cifre");
                c++;
            }

            if (librodb != null) {
                if (librodb.getISBN().equals(isbn)){
                    request.setAttribute("msgcontrolloisbn","ISBN già in uso");
                    c++;
                }
            }
        }

        if (titolo.length() > 50){
            request.setAttribute("msgtitoloP","La lunghezza del titolo non deve superare i 50 caratteri");
            c++;
        }

        if (genere == null){
            request.setAttribute("msgcontrollogenere","seleziona un genere");
            c++;
        }

        if(altro.length() > 20){
            request.setAttribute("msgaltroP","Il genere può avere massimo 20 caratteri");
            c++;
        }

        if (generedbaltro != null) {
            request.setAttribute("msgcontrollogenerealtro","Il genere è già presente, selezionalo sopra");
            c++;
        }

        if(descrizione.length() > 65535){
            request.setAttribute("msgdescrizioneP","La descrizione deve rientrare nei 65535 caratteri");
            c++;
        }

        if (editore.length() > 20) {
            request.setAttribute("msgeditoreP","Il nome dell'editore non deve superare i 20 caratteri");
        }

        return !(c > 0);
    }

    public static boolean validateFormGenere(String nome, Genere generdb, HttpServletRequest request) {
        int c = 0;

        if (nome.length() > 20) {
            request.setAttribute("msggenereP","Il genere può avere massimo 20 caratteri");
            c++;
        }

        if (generdb != null) {
            if (generdb.getNome().equalsIgnoreCase(nome)){
                request.setAttribute("msgcontrollogenere","già presente nel db");
                c++;
            }
        }

        return !(c > 0);
    }

    public static boolean validateFormAutore(String cf, String nome, Autore autoredb, HttpServletRequest request) {
        int c = 0;

        if (cf != null) {

            if (cf.length() != 16) {
                request.setAttribute("msgCFP","Il CF dell'autore deve essere di 16 cifre");
                c++;
            }

            if (autoredb != null) {

                if (autoredb.getCF().equals(cf)) {
                    request.setAttribute("msgcontrolloCF","CF già in uso");
                    c++;
                }
            }

        }

        if (nome.length() > 40) {
            request.setAttribute("msgnomeP","Il nome dell'autore non deve superare i 40 caratteri");
            c++;
        }

        return !(c > 0);
    }
}
