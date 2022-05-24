package com.example.progettotsw.model;

public class Dettaglio {
    /*nel dettaglio al livello di bean, non è necessario memorizzare i riferimenti alle entità carrello e/o ordine
     in quanto i dettagli saranno intrinsecamente legati ad uno dei due rispetto alla sessione o al db.
     Il carrello sarà invece modellato come un ArrayList di Dettagli
     */

    private int ID;
    private int quantita;
    private float prezzo;
    private Libro libro;
}
