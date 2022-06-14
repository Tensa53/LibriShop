package com.example.progettotsw.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Libro {

    public Libro(String ISBN, String titolo, String descrizione, float prezzo, GregorianCalendar dataPubblicazione, String editore, int sconto, int disponibilita, String foto) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.dataPubblicazione = dataPubblicazione;
        this.editore = editore;
        this.sconto = sconto;
        this.disponibilita = disponibilita;
        this.foto = foto;
    }

    public Libro(String ISBN,String titolo) {
        this.ISBN = ISBN;
        this.titolo = titolo;
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
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public void setDataPubblicazione(GregorianCalendar dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public String getEditore() {
        return editore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        this.sconto = sconto;
    }

    public int getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(int disponibilita) {
        this.disponibilita = disponibilita;
    }

    public GregorianCalendar getDataPubblicazione() {
        return dataPubblicazione;
    }

    public String getDataPubblicazioneString() {
        return dataPubblicazione.get(Calendar.DAY_OF_MONTH) + "-" + (dataPubblicazione.get(Calendar.MONTH) + 1) + "-" + dataPubblicazione.get(Calendar.YEAR);
    }

    public String getDataPubblicazioneReversedString() {
        return dataPubblicazione.get(Calendar.YEAR) + "-" + (dataPubblicazione.get(Calendar.MONTH) + 1) + "-" + dataPubblicazione.get(Calendar.DAY_OF_MONTH);
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    private String ISBN;
    private String titolo;
    private String descrizione;
    private float prezzo;
    private GregorianCalendar dataPubblicazione;
    private String editore;
    private int sconto;
    private int disponibilita;
    private String foto;
}
