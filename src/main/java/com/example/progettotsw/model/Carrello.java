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

    public List<Libro> getLibri() {
        ArrayList<Libro> libri = new ArrayList<>();

        for (Dettaglio d : dettagli) {
            libri.add(d.getLibro());
        }

        return libri;
    }

    public void addDettaglio(Dettaglio d) {
        dettagli.add(d);
    }

    public Dettaglio getDettagliobyISBN(String ISBN) {
        Dettaglio dettaglio = null;

        for (Dettaglio d : dettagli) {
            if (d.getLibro().getISBN().equals(ISBN))
                dettaglio = d;
        }
        return dettaglio;
    }

    public String printDettagli() {
        String msg = "";

        for (Dettaglio d : dettagli) {
            msg += d.toString();
        }

        return msg;
    }

    public static Carrello merge(Carrello carrelloA, Carrello carrelloB) {
        for (Dettaglio dettaglioB : carrelloB.getDettagli()) {
            Dettaglio dettaglioA = null;

            if ((dettaglioA = carrelloA.getDettagliobyISBN(dettaglioB.getLibro().getISBN())) != null) {
                dettaglioA.setQuantita(dettaglioA.getQuantita() + dettaglioB.getQuantita());
                BigDecimal prezzoDettaglioA = dettaglioA.getPrezzo();
                if (dettaglioA.getQuantita() > 5)
                    prezzoDettaglioA = prezzoDettaglioA.multiply(new BigDecimal(5.00));
                else
                    prezzoDettaglioA = prezzoDettaglioA.multiply(new BigDecimal(dettaglioA.getQuantita()));

                dettaglioA.setPrezzo(prezzoDettaglioA);
                BigDecimal totaleCarrelloA = carrelloA.getTotale();
                totaleCarrelloA = totaleCarrelloA.add(dettaglioA.getPrezzo());
                carrelloA.setTotale(totaleCarrelloA);
            } else {
                carrelloA.addDettaglio(dettaglioB);
                BigDecimal totaleCarrelloA = carrelloA.getTotale();
                totaleCarrelloA = totaleCarrelloA.add(dettaglioB.getPrezzo());
                carrelloA.setTotale(totaleCarrelloA);
            }
        }

        return carrelloA;
    }
    public static Carrello mergeCarrelli(Carrello carrelloDB, Carrello carrelloSession) {
        if (carrelloDB.getDettagli().size() > carrelloSession.getDettagli().size()) { //appendo al carrello più grande
            return merge(carrelloDB, carrelloSession); //merge del secondo nel primo, carrelloDB = carrelloA; carrelloSession = carrelloB;
        } else {
            return merge(carrelloSession, carrelloDB); //merge del secondo nel primo, carrelloSession = carrelloA; carrelloDB = carrelloB;
        }
    }
    private List<Dettaglio> dettagli;
    private BigDecimal totale;
}
