package com.example.progettotsw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class PagamentoDAO {

    public Pagamento doRetrieveByNumeroCartaUtente(String numeroCarta,String mail) {
        Pagamento p = null;

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Pagamento WHERE NumeroCarta = ? AND Utente = ?");
            ps.setString(1, numeroCarta);
            ps.setString(2,mail);

            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                p = new Pagamento();
                p.setNumeroCarta(numeroCarta);
                String scadenzaString = rs.getString("Scadenza");
                GregorianCalendar scadenza = new GregorianCalendar(Integer.parseInt(scadenzaString.split("-")[0]), (Integer.parseInt(scadenzaString.split("-")[1]))-1, Integer.parseInt(scadenzaString.split("-")[2]));
                p.setScadenza(scadenza);
                p.setCCV(rs.getString("CCV"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public int doSave (Pagamento p){
        String sql = "INSERT INTO Pagamento VALUES (?,?,?);";

        try(Connection conn=ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1,p.getNumeroCarta());
            ps.setString(2,p.getScadenzaString());
            ps.setString(3,p.getCCV());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSaveByMail (Pagamento p, String mail){
        String sql = "INSERT INTO Pagamento VALUES (?,?,?,?);";

        try(Connection conn=ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1,p.getNumeroCarta());
            ps.setString(2,p.getScadenzaReversedString());
            ps.setString(3,p.getCCV());
            ps.setString(4,mail);

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pagamento> doRetrievebyUserMail(String mail) {
        String sql = "SELECT * FROM Pagamento WHERE Utente = ?";

        List<Pagamento> pagamenti = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,mail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String numeroCarta = rs.getString("NumeroCarta");
                String scadenzaString = rs.getString("Scadenza");
                String ccv = rs.getString("CCV");

                GregorianCalendar dataScadenza = new GregorianCalendar(Integer.parseInt(scadenzaString.split("-")[0]), (Integer.parseInt(scadenzaString.split("-")[1]))-1, Integer.parseInt(scadenzaString.split("-")[2]));
                Pagamento pagamento = new Pagamento(numeroCarta,dataScadenza,ccv);

                pagamenti.add(pagamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pagamenti;
    }


    public void doDeletebyNumeroCartaUtente(String numeroCarta,String mail) {
        String sql = "DELETE FROM Pagamento WHERE NumeroCarta = ? AND Utente = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,numeroCarta);
            ps.setString(2,mail);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteAllbyUtente(String mail){
        String sql = "DELETE FROM Pagamento WHERE Utente = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,mail);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doUpdate(Pagamento p, String numeroCarta, String scadenza, String CCV, String mail) {
        String sql = "UPDATE Pagamento SET NumeroCarta = ?, Scadenza = ?, CCV = ? WHERE NumeroCarta = ? AND Scadenza = ? AND CCV = ? AND Utente = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1,p.getNumeroCarta());
            pstmt.setString(2,p.getScadenzaReversedString());
            pstmt.setString(3,p.getCCV());
            pstmt.setString(4,numeroCarta);
            pstmt.setString(5,scadenza);
            pstmt.setString(6,CCV);
            pstmt.setString(7,mail);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doDeletePagamento(Pagamento p, String mail) {
        String sql = "DELETE FROM Pagamento WHERE NumeroCarta = ? AND Scadenza = ? AND CCV = ? AND Utente = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1,p.getNumeroCarta());
            pstmt.setString(2,p.getScadenzaReversedString());
            pstmt.setString(3,p.getCCV());
            pstmt.setString(4,mail);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
