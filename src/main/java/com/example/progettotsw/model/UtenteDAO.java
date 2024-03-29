package com.example.progettotsw.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class UtenteDAO {

    public List<Utente> doRetrieveAll(){
        String sql = "SELECT * FROM Utente";

        List<Utente> utenti = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                String mail = rs.getString("Email");
                String nome = rs.getString("Nome");
                String cognome = rs.getString("Cognome");
                boolean amministratore = rs.getBoolean("Amministratore");
                Utente u = new Utente(mail,nome,cognome,amministratore);
                utenti.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return utenti;
    }

    public List<Utente> doRetrieveAllUsers(){
        String sql = "SELECT * FROM Utente WHERE Amministratore = false";

        List<Utente> utenti = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                String mail = rs.getString("Email");
                String username = rs.getString("Username");
                String nome = rs.getString("Nome");
                String cognome = rs.getString("Cognome");
                boolean amministratore = rs.getBoolean("Amministratore");
                Utente u = new Utente(mail,nome,cognome,amministratore);
                utenti.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return utenti;
    }

    public Utente doRetrieveByMailPassword(String mail,String password){
        String sql = "SELECT * FROM Utente WHERE Email=? AND Passwordhash=SHA1(?);";

        Utente u = null;

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,mail);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                u = new Utente();
                u.setMail(rs.getString("Email"));
                u.setNome(rs.getString("Nome"));
                u.setCognome(rs.getString("Cognome"));
                u.setPasswordhash(rs.getString("Passwordhash"));
                u.setAmministratore(rs.getBoolean("Amministratore"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return u;
    }

    public Utente doRetrieveByMail(String mail) {
        String sql = "SELECT * FROM Utente WHERE Email=?;";

        Utente u = null;

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,mail);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                u = new Utente();
                u.setMail(rs.getString("Email"));
                u.setNome(rs.getString("Nome"));
                u.setCognome(rs.getString("Cognome"));
                u.setPasswordhash(rs.getString("Passwordhash"));
                u.setAmministratore(rs.getBoolean("Amministratore"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return u;
    }

    public int doSave(Utente utente) {
        String sql = "INSERT INTO Utente VALUES (?,?,?,?,?);";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,utente.getMail());
            pstmt.setString(2,utente.getNome());
            pstmt.setString(3,utente.getCognome());
            pstmt.setString(4,utente.getPasswordhash());
            pstmt.setBoolean(5,utente.isAmministratore());

            return pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int doUpdateUser(Utente utente) {
        String sql = "UPDATE Utente SET Nome = ?,Cognome = ?,Amministratore = ? WHERE Email = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1,utente.getNome());
            pstmt.setString(2,utente.getCognome());
            pstmt.setBoolean(3,utente.isAmministratore());
            pstmt.setString(4, utente.getMail());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doUpdateUserPasswordByMail (String passwordhash,String mail) {
        String sql = "UPDATE Utente SET PasswordHash = ? WHERE Email = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1,passwordhash);
            pstmt.setString(2,mail);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int doDeleteUtente(Utente utente) {
        String sql = "DELETE FROM Utente WHERE Email = ?;";
        String sql2 = "SET foreign_key_checks = ?;";

        CarrelloDAO carrelloDAO = new CarrelloDAO();

        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

        PagamentoDAO pagamentoDAO = new PagamentoDAO();

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); PreparedStatement pst = conn.prepareStatement(sql2); ) {
            pstmt.setString(1,utente.getMail());
            indirizzoDAO.doDeleteAllbyUtente(utente.getMail());
            pagamentoDAO.doDeleteAllbyUtente(utente.getMail());
            carrelloDAO.doDeletebyUtente(utente.getMail());
            pst.setInt(1,0);
            pst.executeUpdate();
            int row = pstmt.executeUpdate();
            pst.setInt(1,1);
            pst.executeUpdate();
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
