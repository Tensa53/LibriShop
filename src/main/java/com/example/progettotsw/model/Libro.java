package com.example.progettotsw.model;

import java.util.GregorianCalendar;

public class Libro {

    public Libro(String ISBN, String titolo, String descrizione, float prezzo, GregorianCalendar dataPubblicazione, String editore, int sconto, int disponibilita, String foto) {
        this.ISBN = ISBN;
        Titolo = titolo;
        Descrizione = descrizione;
        Prezzo = prezzo;
        DataPubblicazione = dataPubblicazione;
        Editore = editore;
        Sconto = sconto;
        Disponibilita = disponibilita;
        Foto = foto;
    }


    public Libro() {
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitolo() {
        return Titolo;
    }

    public void setTitolo(String titolo) {
        Titolo = titolo;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public float getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(float prezzo) {
        Prezzo = prezzo;
    }

    public GregorianCalendar getDataPubblicazione() {
        return DataPubblicazione;
    }

    public void setDataPubblicazione(GregorianCalendar dataPubblicazione) {
        DataPubblicazione = dataPubblicazione;
    }

    public String getEditore() {
        return Editore;
    }

    public void setEditore(String editore) {
        Editore = editore;
    }

    public int getSconto() {
        return Sconto;
    }

    public void setSconto(int sconto) {
        Sconto = sconto;
    }

    public int getDisponibilita() {
        return Disponibilita;
    }

    public void setDisponibilita(int disponibilita) {
        Disponibilita = disponibilita;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    private String ISBN;
    private String Titolo;
    private String Descrizione;
    private float Prezzo;
    private GregorianCalendar DataPubblicazione;
    private String Editore;
    private int Sconto;
    private int Disponibilita;
    private String Foto;
}
