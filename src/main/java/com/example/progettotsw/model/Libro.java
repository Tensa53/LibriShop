package com.example.progettotsw.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.BiFunction;

public class Libro {

    public Libro(String ISBN, String titolo, BigDecimal prezzo, GregorianCalendar dataPubblicazione, String editore, BigDecimal sconto, int disponibilita, String foto,  String descrizione) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.prezzo = prezzo;
        this.dataPubblicazione = dataPubblicazione;
        this.editore = editore;
        this.sconto = sconto;
        this.disponibilita = disponibilita;
        this.foto = foto;
        this.descrizione = descrizione;
    }

    public Libro(String ISBN, String titolo,BigDecimal prezzo, GregorianCalendar dataPubblicazione, String editore, BigDecimal sconto, int disponibilita,  String descrizione) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.dataPubblicazione = dataPubblicazione;
        this.editore = editore;
        this.sconto = sconto;
        this.disponibilita = disponibilita;
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

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public BigDecimal getPrezzoScontato(){
        BigDecimal percento = new BigDecimal(100);

        MathContext precisione = new MathContext(2);

        BigDecimal prezzoScontato = new BigDecimal(prezzo.toString());

        BigDecimal scontoMonetario = new BigDecimal(prezzo.toString());

        scontoMonetario = scontoMonetario.multiply(sconto.divide(percento)).round(precisione);

        prezzoScontato = prezzoScontato.subtract(scontoMonetario);

        return prezzoScontato;
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

    public BigDecimal getSconto() {
        return sconto;
    }

    public void setSconto(BigDecimal sconto) {
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
        return dataPubblicazione.get(GregorianCalendar.DAY_OF_MONTH) + "-" + (dataPubblicazione.get(GregorianCalendar.MONTH) + 1) + "-" + dataPubblicazione.get(GregorianCalendar.YEAR);
    }

    public String getDataPubblicazioneReversedString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setCalendar(this.dataPubblicazione);
        String result = format.format(this.dataPubblicazione.getTime());

        return result;
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
    private BigDecimal prezzo;
    private GregorianCalendar dataPubblicazione;
    private String editore;
    private BigDecimal sconto;
    private int disponibilita;
    private String foto;
}
