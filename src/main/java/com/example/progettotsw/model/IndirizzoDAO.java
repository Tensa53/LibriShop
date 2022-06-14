package com.example.progettotsw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndirizzoDAO {

    public Indirizzo doRetrieveByViaCivicoCAP(String via, String civico, String CAP){
        String sql = "SELECT * FROM Indirizzo WHERE Via=? AND Civico=? AND CAP=?;";

        Indirizzo i = null;

        try(Connection conn=ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {

        ps.setString(1,via);
        ps.setString(2,civico);
        ps.setString(3,CAP);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                i = new Indirizzo();
                i.setVia(rs.getString("Via"));
                i.setCivico(rs.getString("Civico"));
                i.setCAP(rs.getString("CAP"));
                i.setCitta(rs.getString("Citta"));
                i.setProvincia(rs.getString("Provincia"));
                i.setStato(rs.getString("Stato"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return i;

    }

    public int doSave (Indirizzo i){
        String sql = "INSERT INTO Indirizzo VALUES (?,?,?,?,?,?);";

        try(Connection conn=ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1,i.getVia());
            ps.setString(2,i.getCivico());
            ps.setString(3,i.getCAP());
            ps.setString(4,i.getCitta());
            ps.setString(5,i.getProvincia());
            ps.setString(6,i.getStato());

            return ps.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

}
