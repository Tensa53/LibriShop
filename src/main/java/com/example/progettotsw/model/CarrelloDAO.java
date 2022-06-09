package com.example.progettotsw.model;

import com.example.progettotsw.controller.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class CarrelloDAO {

    public Carrello doRetrievebyUtente(String mail){
        Carrello carrello = null;

        String sql = "SELECT * FROM Carrello WHERE Utente = ?";

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,mail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                carrello = new Carrello();
                carrello.setTotale(rs.getFloat("Totale"));
                DettaglioDAO dettaglioDAO = new DettaglioDAO();
                carrello.setDettagli(dettaglioDAO.doRetrieveByCarrelloUtente(mail));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return carrello;

    }

    public void doCreate(String mail) {
        String sql = "INSERT INTO Carrello VALUES(?,?);";

        try (Connection con = ConPool.getConnection();PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1,mail);
            ps.setFloat(2,0.0f);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSaveAllbyUtente(Carrello carrello,String mail) {
        DettaglioDAO dettaglioDAO = new DettaglioDAO();

        String sql = "UPDATE Carrello SET Totale = ? WHERE Utente = ?";

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1,carrello.getTotale());
            ps.setString(2,mail);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dettaglioDAO.doSaveAllbyCarrelloUtente(carrello.getDettagli(),mail);
    }

    public void doRemoveAllbyUtente(String mail){
        DettaglioDAO dettaglioDAO = new DettaglioDAO();

        String sql = "UPDATE Carrello SET Totale = ? WHERE Utente = ?";

        try (Connection con = ConPool.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setFloat(1,0.0f);
            ps.setString(2,mail);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dettaglioDAO.doDeleteALLByCarrelloUtente(mail);
    }
}
