package com.example.progettotsw.controller;

import jakarta.servlet.http.HttpServletRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Forms {

    public static boolean validateFormUtente(String mail, String username, String nome, String cognome, String password, HttpServletRequest request) {
        int c = 0;

        if(nome.length() > 20) {
            request.setAttribute("msgnomeP","Il campo nome non può superare i 20 caratteri.");
            c++;
        }

        if(cognome.length() > 20) {
            request.setAttribute("msgcognomeP","Il campo cognome non può superare i 20 caratteri");
            c++;
        }

        if (mail != null) {
            //regex per la mail

            System.out.println(mail);

            Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
            Matcher matcher = pattern.matcher(mail);

            boolean res = matcher.matches();

            System.out.println("regex : " + res);

            if(mail.length() > 50) {
                request.setAttribute("msgmailP","Il campo mail non può superare i 50 caratteri.");
                c++;
            }

            if (!matcher.matches()) {
                request.setAttribute("msgcontrollomail","Il formato dell'email deve essere del tipo nomecasella@tuodominio.it");
                c++;
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

        if(username.length() > 20) {
            request.setAttribute("msgusernameP","Il campo username non può superare i 20 caratteri");
            c++;
        }

        return !(c > 0);
    }
}
