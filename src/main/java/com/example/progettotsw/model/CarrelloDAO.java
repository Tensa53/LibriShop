package com.example.progettotsw.model;

import com.example.progettotsw.controller.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class CarrelloDAO {

    public Carrello doRetrievebyUtente(String mail){
        Carrello carrello = new Carrello();

        String sql = "SELECT * FROM Carrello WHERE Utente = ?";

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,mail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                carrello.setTotale(rs.getFloat("Totale"));
                DettaglioDAO dettaglioDAO = new DettaglioDAO();
                carrello.setDettagli(dettaglioDAO.doRetrieveByCarrelloUtente(mail));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return carrello;

    }
}
