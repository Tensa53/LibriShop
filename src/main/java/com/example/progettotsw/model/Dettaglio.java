package com.example.progettotsw.model;

import java.math.BigDecimal;

public class Dettaglio {
    /*nel dettaglio al livello di bean, non è necessario memorizzare i riferimenti alle entità carrello e/o ordine
     in quanto i dettagli saranno intrinsecamente legati ad uno dei due rispetto alla sessione o al db.
     Il carrello sarà invece modellato come un ArrayList di Dettagli
     */

    public Dettaglio(int quantita, BigDecimal prezzo, Libro libro, int id) {
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.libro = libro;
        this.id = id;
    }

    public Dettaglio() {
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Dettaglio{" +
                "quantita=" + quantita +
                ", prezzo=" + prezzo +
                ", libro=" + libro +
                '}';
    }


    private int id;
    private int quantita;
    private BigDecimal prezzo;
    private Libro libro;
}
