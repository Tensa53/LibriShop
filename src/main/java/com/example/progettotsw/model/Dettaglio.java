package com.example.progettotsw.model;

public class Dettaglio {
    /*nel dettaglio al livello di bean, non è necessario memorizzare i riferimenti alle entità carrello e/o ordine
     in quanto i dettagli saranno intrinsecamente legati ad uno dei due rispetto alla sessione o al db.
     Il carrello sarà invece modellato come un ArrayList di Dettagli
     */

    public Dettaglio( int quantita, float prezzo, Libro libro) {
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.libro = libro;
    }

    public Dettaglio() {
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Dettaglio{" +
                "quantita=" + quantita +
                ", prezzo=" + prezzo +
                ", libro=" + libro +
                '}';
    }

    private int quantita;
    private float prezzo;
    private Libro libro;
}
