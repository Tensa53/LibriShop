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
        String sql = "SELECT * FROM Dettaglio WHERE Carrello is not NULL AND Utente = ?;";

        List<Dettaglio> dettagli = new ArrayList<>();

        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,mail);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int ID = rs.getInt("ID");
                int quantita = rs.getInt("Quantita");
                float prezzo = rs.getFloat("Prezzo");
                String ISBN = rs.getString("ISBNLibro");
                LibroDAO libroDAO = new LibroDAO();
                Libro libro = libroDAO.doRetrieveById(ISBN);
                Dettaglio dettaglio = new Dettaglio(ID,quantita,prezzo,libro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dettagli;
    }


    public List<Dettaglio> doRetrieveAll(){
        String sql = "SELECT * FROM Dettaglio;";

        List<Dettaglio> dettagli = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                int ID = rs.getInt("ID");
                int quantita = rs.getInt("Quantita");
                float prezzo = rs.getFloat("Prezzo");
                String ISBN = rs.getString("ISBNLibro");
                LibroDAO libroDAO = new LibroDAO();
                Libro libro = libroDAO.doRetrieveById(ISBN);
                Dettaglio dettaglio = new Dettaglio(ID,quantita,prezzo,libro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dettagli;
    }

}
