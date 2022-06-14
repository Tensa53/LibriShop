package com.example.progettotsw.model;

public class Indirizzo {

    public Indirizzo(String via, String civico, String CAP, String citta, String provincia, String stato) {
        this.via = via;
        this.civico = civico;
        this.CAP = CAP;
        this.citta = citta;
        this.provincia = provincia;
        this.stato = stato;
    }

    public Indirizzo(){}

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    private String via, civico, CAP, citta, provincia, stato;

}
