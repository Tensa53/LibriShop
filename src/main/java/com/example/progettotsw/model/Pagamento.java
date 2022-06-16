package com.example.progettotsw.model;

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
        return scadenza.get(Calendar.DAY_OF_MONTH) + "-" + (scadenza.get(Calendar.MONTH) + 1) + "-" + scadenza.get(Calendar.YEAR);
    }
    public String getScadenzaReversedString() {
        return scadenza.get(Calendar.YEAR) + "-" + scadenza.get(Calendar.DAY_OF_MONTH) + "-" + (scadenza.get(Calendar.MONTH) + 1);
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
