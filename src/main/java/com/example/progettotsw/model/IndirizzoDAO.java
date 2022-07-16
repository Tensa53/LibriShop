package com.example.progettotsw.model;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndirizzoDAO {

    public Indirizzo doRetrieveByViaCivicoCittaUtente(String via, String civico, String Citta, String mail){
        String sql = "SELECT * FROM Indirizzo WHERE Via=? AND Civico=? AND Citta=? AND Utente = ?;";

        Indirizzo i = null;

        try(Connection conn=ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {

        ps.setString(1,via);
        ps.setString(2,civico);
        ps.setString(3,Citta);
        ps.setString(4,mail);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                i = new Indirizzo();
                i.setVia(rs.getString("Via"));
                i.setCivico(rs.getString("Civico"));
                i.setCitta(rs.getString("Citta"));
                i.setCAP(rs.getString("CAP"));
                i.setProvincia(rs.getString("Provincia"));
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
            ps.setString(3,i.getCitta());
            ps.setString(4,i.getCAP());
            ps.setString(5,i.getProvincia());

            return ps.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    public int doSaveWithMail (Indirizzo i, String mail){
        String sql = "INSERT INTO Indirizzo VALUES (?,?,?,?,?,?)";

        try(Connection conn=ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1,i.getVia());
            ps.setString(2,i.getCivico());
            ps.setString(3,i.getCitta());
            ps.setString(4,i.getCAP());
            ps.setString(5,i.getProvincia());
            ps.setString(6,mail);

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Indirizzo> doRetrievebyUserMail(String mail){
        String sql = "SELECT * FROM Indirizzo WHERE Utente = ?";

        List<Indirizzo> indirizzi = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,mail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String via = rs.getString("Via");
                String civico = rs.getString("Civico");
                String citta = rs.getString("Citta");
                String CAP = rs.getString("CAP");
                String provincia = rs.getString("Provincia");

                Indirizzo indirizzo = new Indirizzo(via,civico,citta,CAP,provincia);

                indirizzi.add(indirizzo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return indirizzi;
    }

    public void doDeletebyIndirizzoUtente(String via, String civico, String Citta,String mail) {
        String sql = "DELETE FROM Indirizzo WHERE Via = ? AND Civico = ? AND Citta = ? AND Utente = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,via);
            ps.setString(2,civico);
            ps.setString(3,Citta);
            ps.setString(4,mail);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteAllbyUtente(String mail) {
        String sql = "DELETE FROM Indirizzo WHERE Utente = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,mail);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doUpdateIndirizzoByViaCivicoCitta(Indirizzo i, String via, String civico, String citta, String email){
        String sql = "UPDATE Indirizzo SET Via = ?, Civico = ?, Citta = ?, CAP = ?, Provincia = ? WHERE Via = ? AND Civico = ? AND Citta = ? AND Utente = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1,i.getVia());
            pstmt.setString(2,i.getCivico());
            pstmt.setString(3,i.getCitta());
            pstmt.setString(4,i.getCAP());
            pstmt.setString(5,i.getProvincia());
            pstmt.setString(6,via);
            pstmt.setString(7,civico);
            pstmt.setString(8,citta);
            pstmt.setString(9,email);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doDeleteIndirizzoByViaCivicoCitta(String via,String civico,String citta,String email){

        String sql = "DELETE FROM Indirizzo WHERE Via = ? AND Civico = ? AND Citta = ? AND Utente = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1,via);
            pstmt.setString(2,civico);
            pstmt.setString(3,citta);
            pstmt.setString(4,email);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
