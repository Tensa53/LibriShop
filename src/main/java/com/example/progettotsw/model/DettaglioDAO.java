package com.example.progettotsw.model;

import com.example.progettotsw.controller.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DettaglioDAO {

    public List<Dettaglio> doRetrieveByCarrelloUtente(String mail){
        String sql = "SELECT * FROM Dettaglio WHERE Carrello = ?;";

        List<Dettaglio> dettagli = new ArrayList<>();

        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,mail);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int quantita = rs.getInt("Quantita");
                float prezzo = rs.getFloat("Prezzo");
                String ISBN = rs.getString("ISBNLibro");
                LibroDAO libroDAO = new LibroDAO();
                Libro libro = libroDAO.doRetrieveById(ISBN);
                Dettaglio dettaglio = new Dettaglio(quantita,prezzo,libro);
                dettagli.add(dettaglio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dettagli;
    }


    public void doSaveAllbyCarrelloUtente(List<Dettaglio> dettagli,String mail) {
        String sql = "INSERT INTO Dettaglio(Quantita,Prezzo,Carrello,ISBNLibro) VALUES (?,?,?,?);";

        for(Dettaglio d : dettagli){
            try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
                pstmt.setInt(1,d.getQuantita());
                pstmt.setFloat(2,d.getPrezzo());
                pstmt.setString(3,mail);
                pstmt.setString(4,d.getLibro().getISBN());

                pstmt.executeUpdate();
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return;
    }

    public void doDeleteALLByCarrelloUtente(String mail){
        String sql = "DELETE FROM Dettaglio WHERE Carrello = ?;";

        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mail);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Dettaglio> doRetrieveAll(){
        String sql = "SELECT * FROM Dettaglio;";

        List<Dettaglio> dettagli = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                int quantita = rs.getInt("Quantita");
                float prezzo = rs.getFloat("Prezzo");
                String ISBN = rs.getString("ISBNLibro");
                LibroDAO libroDAO = new LibroDAO();
                Libro libro = libroDAO.doRetrieveById(ISBN);
                Dettaglio dettaglio = new Dettaglio(quantita,prezzo,libro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dettagli;
    }

}
