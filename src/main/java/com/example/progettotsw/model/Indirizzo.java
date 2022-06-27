package com.example.progettotsw.model;

public class Indirizzo {

    public Indirizzo(String via, String civico, String citta, String CAP, String provincia) {
        this.via = via;
        this.civico = civico;
        this.citta = citta;
        this.CAP = CAP;
        this.provincia = provincia;
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

    private String via, civico, CAP, citta, provincia, stato;

}
