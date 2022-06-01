package com.example.progettotsw.model;

import com.example.progettotsw.controller.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {

    public Utente doRetrieveByMailPassword(String mail,String password){
        String sql = "SELECT * FROM Utente WHERE Email=? AND Passwordhash=SHA1(?);";

        Utente u = null;

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,mail);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                u = new Utente();
                u.setMail(rs.getString("Email"));
                u.setUsername(rs.getString("Username"));
                u.setNome(rs.getString("Nome"));
                u.setCognome(rs.getString("Cognome"));
                u.setPasswordhash(rs.getString("Passwordhash"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return u;
    }

    public void doSave(Utente utente) {
        String sql = "INSERT INTO Utente VALUES (?,?,?,?,?,?)";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,utente.getMail());
            pstmt.setString(2,utente.getUsername());
            pstmt.setString(3,utente.getNome());
            pstmt.setString(4,utente.getCognome());
            pstmt.setString(5,utente.getPasswordhash());
            pstmt.setBoolean(6,utente.isAmministratore());

            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
