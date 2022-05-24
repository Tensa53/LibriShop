package com.example.progettotsw.model;

import com.example.progettotsw.controller.ConPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoreDAO {

    public List<Autore> doRetrieveAll(){
        String sql = "SELECT * FROM Autore;";

        List<Autore> autori = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                String CF = rs.getString("CF");
                String Nome = rs.getString("Nome");
                String Cognome = rs.getString("Cognome");
                Autore autore = new Autore(CF,Nome,Cognome);
                autori.add(autore);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autori;
    }
}
