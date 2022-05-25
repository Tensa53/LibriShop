package com.example.progettotsw.model;

public class Utente {

    public Utente(String mail, String username, String nome, String cognome, String passwordhash) {
        this.mail = mail;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.passwordhash = passwordhash;
    }

    public Utente() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public boolean isAmministratore() {
        return amministratore;
    }

    public void setAmministratore(boolean amministratore) {
        this.amministratore = amministratore;
    }

    private String mail;
    private String username;
    private String nome;
    private String cognome;
    private String passwordhash;

    private boolean amministratore;
}
