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

    public void  removeDettaglio(Dettaglio d) {dettagli.remove(d);}

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

            BigDecimal totaleCarrelloA = carrelloA.getTotale();

            if ((dettaglioA = carrelloA.getDettagliobyISBN(dettaglioB.getLibro().getISBN())) != null) { //se c'è già un dettaglio di questo libro nel carrello
                totaleCarrelloA = totaleCarrelloA.subtract(dettaglioA.getPrezzo());//rimuovi il vecchio prezzo del dettaglio dal totale del carrello
                dettaglioA.setQuantita(dettaglioA.getQuantita() + dettaglioB.getQuantita());//incrementa la quantità
                BigDecimal prezzoDettaglioA = dettaglioA.getLibro().getPrezzoScontato();//setta il prezzo base nel dettaglio
                if (dettaglioA.getQuantita() > 5)
                    dettaglioA.setQuantita(5); //se si eccede nelle quantità,resetta a 5

                prezzoDettaglioA = prezzoDettaglioA.multiply(new BigDecimal(dettaglioA.getQuantita()));//moltiplica il prezzo base per la quantità

                dettaglioA.setPrezzo(prezzoDettaglioA);
                totaleCarrelloA = totaleCarrelloA.add(dettaglioA.getPrezzo()); //aggiorniamo il totale del carrello col nuovo prezzo del dettaglio
                carrelloA.setTotale(totaleCarrelloA);
            } else {
                carrelloA.addDettaglio(dettaglioB);
                totaleCarrelloA = totaleCarrelloA.add(dettaglioB.getPrezzo());
                carrelloA.setTotale(totaleCarrelloA);
            }
        }

        return carrelloA;
    }
    private List<Dettaglio> dettagli;
    private BigDecimal totale;
}
