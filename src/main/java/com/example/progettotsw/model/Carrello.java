package com.example.progettotsw.model;

import java.util.List;

public class Carrello {

    public Carrello(List<Dettaglio> dettagli, float totale) {
        this.dettagli = dettagli;
        this.totale = totale;
    }

    public Carrello() {
    }

    public List<Dettaglio> getDettagli() {
        return dettagli;
    }

    public void setDettagli(List<Dettaglio> dettagli) {
        this.dettagli = dettagli;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    private List<Dettaglio> dettagli;
    private float totale;
}
