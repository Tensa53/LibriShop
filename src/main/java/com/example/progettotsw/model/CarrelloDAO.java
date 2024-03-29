package com.example.progettotsw.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                carrello.setTotale(rs.getBigDecimal("Totale"));
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
            ps.setBigDecimal(2,new BigDecimal(0.00));
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
            System.out.println(carrello.getTotale());
            ps.setBigDecimal(1,carrello.getTotale());
            ps.setString(2,mail);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dettaglioDAO.doSaveAllbyCarrelloUtente(carrello.getDettagli(),mail);
    }

    public void doRemoveAllbyUtente(String mail){
        DettaglioDAO dettaglioDAO = new DettaglioDAO();

        String sql = "UPDATE Carrello SET Totale = ? WHERE Utente = ?;";

        try (Connection con = ConPool.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBigDecimal(1,new BigDecimal(0.00));
            ps.setString(2,mail);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dettaglioDAO.doDeleteALLByCarrelloUtente(mail);
    }


    public void doDeletebyUtente(String mail){
        String sql = "DELETE FROM Carrello WHERE Utente = ?;";

        DettaglioDAO dettaglioDAO = new DettaglioDAO();

        try (Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,mail);
            dettaglioDAO.doDeleteALLByCarrelloUtente(mail);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
