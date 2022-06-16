package com.example.progettotsw.model;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Indirizzo> doRetrievebyUserMail(String mail){
        String sql = "SELECT * FROM Dichiarazione D,Indirizzo I WHERE (D.IndirizzoVia = I.Via AND D.IndirizzoCAP = I.CAP AND D.IndirizzoNumero = I.Civico) AND D.Utente = ?";

        List<Indirizzo> indirizzi = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,mail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String via = rs.getString("Via");
                String civico = rs.getString("Civico");
                String CAP = rs.getString("CAP");
                String citta = rs.getString("Citta");
                String provincia = rs.getString("Provincia");
                String stato = rs.getString("Stato");

                Indirizzo indirizzo = new Indirizzo(via,civico,CAP,citta,provincia,stato);

                indirizzi.add(indirizzo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return indirizzi;
    }

}
