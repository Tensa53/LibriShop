package com.example.progettotsw.model;

import java.util.List;

public class Genere {

    public Genere() {
    }

    public Genere(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean contenutoIn(List<Genere> generi) {
        for (Genere g : generi){
            if (this.nome.equals(g.getNome()))
                return true;
        }

        return false;
    }

    String nome;
}
