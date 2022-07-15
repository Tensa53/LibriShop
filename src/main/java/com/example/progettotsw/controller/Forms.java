package com.example.progettotsw.controller;

import com.example.progettotsw.model.Utente;
import jakarta.servlet.http.HttpServletRequest;

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

            Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
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
                if (utentemaildb.getMail().equals(mail)) {
                    request.setAttribute("msgmailinuso","già in uso");
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

    public static boolean validateFormLibro(String isbn,String titolo,String altro,String descrizione, String editore,HttpServletRequest request) {
        int c = 0;

        if(isbn != null) {
            if(isbn.length() != 13){
                request.setAttribute("msgisbnP","Il codice ISBN deve essere di 13 cifre");
                c++;
            }
        }

        if (titolo.length() > 30){
            request.setAttribute("msgtitoloP","La lunghezza del titolo non deve superare i 30 caratteri");
            c++;
        }

        if(altro.length() > 20){
            request.setAttribute("msgaltroP","Il genere può avere massimo 20 caratteri");
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
}
