package com.example.progettotsw.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Pagamento {

    public Pagamento(String numeroCarta, GregorianCalendar scadenza, String CCV) {
        this.numeroCarta = numeroCarta;
        this.scadenza = scadenza;
        this.CCV = CCV;
    }

    public Pagamento(){}

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public GregorianCalendar getScadenza() {
        return scadenza;
    }

    public String getScadenzaString() {
        return scadenza.get(GregorianCalendar.DAY_OF_MONTH) + "-" + (scadenza.get(GregorianCalendar.MONTH) + 1) + "-" + scadenza.get(GregorianCalendar.YEAR);
    }
    public String getScadenzaReversedString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setCalendar(this.scadenza);
        String result = format.format(this.scadenza.getTime());

        return result;
    }

    public boolean equals (Pagamento p) {
        return this.numeroCarta.equals(p.getNumeroCarta()) && this.scadenza.equals(p.getScadenza()) && this.CCV.equals(p.getCCV());
    }

    public void setScadenza(GregorianCalendar scadenza) {
        this.scadenza = scadenza;
    }

    public String getCCV() {
        return CCV;
    }

    public void setCCV(String CCV) {
        this.CCV = CCV;
    }

    private String numeroCarta;
    private GregorianCalendar scadenza;
    private String CCV;

}
