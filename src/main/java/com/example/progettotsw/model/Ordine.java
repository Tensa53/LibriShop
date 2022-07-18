package com.example.progettotsw.model;

import jakarta.servlet.http.HttpServlet;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

public class Ordine extends HttpServlet {

    public Ordine() {

    }

    public Ordine(Timestamp dataOrdine, Indirizzo indirizzo, Pagamento pagamento, BigDecimal totale, Utente utente) {
        this.indirizzo = indirizzo;
        this.pagamento = pagamento;
        this.totale = totale;
        this.utente = utente;
    }

    public Ordine(Indirizzo indirizzo, Pagamento pagamento, BigDecimal totale, Utente utente) {
        this.indirizzo = indirizzo;
        this.pagamento = pagamento;
        this.totale = totale;
        this.utente = utente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GregorianCalendar getDataOrdine() {
        return dataOrdine;
    }

    public String getDataOrdineReversedString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format.setCalendar(this.dataOrdine);
        String result = format.format(this.dataOrdine.getTime());

        return result;
    }

    public void setDataOrdine(GregorianCalendar dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public BigDecimal getTotale() {
        return totale;
    }

    public void setTotale(BigDecimal totale) {
        this.totale = totale;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public List<Dettaglio> getDettagli() {
        return dettagli;
    }

    public void setDettagli(List<Dettaglio> dettagli) {
        this.dettagli = dettagli;
    }

    private int id;
    private GregorianCalendar dataOrdine;
    private Indirizzo indirizzo;
    private Pagamento pagamento;
    private BigDecimal totale;
    private Utente utente;
    private List<Dettaglio> dettagli;
}
