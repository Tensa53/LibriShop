package com.example.progettotsw.model;

public class Autore {

    public Autore(String CF, String nome) {
        this.CF = CF;
        Nome = nome;
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

    private String CF;
    private String Nome;

}
