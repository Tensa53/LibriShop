package com.example.progettotsw.model;

public class Autore {

    public Autore(String CF, String nome, String cognome) {
        this.CF = CF;
        Nome = nome;
        Cognome = cognome;
    }

    public Autore() {
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    private String CF;
    private String Nome;
    private String Cognome;

}
