package com.example.progettotsw.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Carrello {

    public Carrello(List<Dettaglio> dettagli, BigDecimal totale) {
        this.dettagli = dettagli;
        this.totale = totale;
    }

    public Carrello() {
        this.dettagli = new ArrayList<>();
        this.totale = new BigDecimal(0.00);
    }

    public List<Dettaglio> getDettagli() {
        return dettagli;
    }

    public void setDettagli(List<Dettaglio> dettagli) {
        this.dettagli = dettagli;
    }

    public BigDecimal getTotale() {
        return totale;
    }

    public void setTotale(BigDecimal totale) {
        this.totale = totale;
    }

    public int getNumeroProdotti() {
        int num = 0;

        for (Dettaglio d : dettagli)
            num += d.getQuantita();

        return num;
    }

    public List<Libro> getLibri () {
        ArrayList<Libro> libri = new ArrayList<>();

        for (Dettaglio d : dettagli) {
            libri.add(d.getLibro());
        }

        return libri;
    }

    public void addDettaglio (Dettaglio d) {
        dettagli.add(d);
    }

    public Dettaglio getDettagliobyISBN(String ISBN) {
        Dettaglio dettaglio = null;

        for(Dettaglio d : dettagli){
            if(d.getLibro().getISBN().equals(ISBN))
                dettaglio = d;
        }
        return dettaglio;
    }

    public String printDettagli () {
        String msg = "";

        for (Dettaglio d : dettagli){
            msg += d.toString();
        }

        return msg;
    }

    private List<Dettaglio> dettagli;
    private BigDecimal totale;
}
