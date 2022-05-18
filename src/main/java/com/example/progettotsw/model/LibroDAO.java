package com.example.progettotsw.model;

import com.example.progettotsw.controller.ConPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class LibroDAO {

    public List<Libro> doRetrieveAll(){
        String sql = "SELECT * FROM Libro LIMIT 3;";

        List<Libro> libri = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                String ISBN = rs.getString("ISBN");
                String Titolo = rs.getString("Titolo");
                String Descrizione = rs.getString("Descrizione");
                float Prezzo = rs.getFloat("Prezzo");
                String Editore = rs.getString("Editore");
                int Sconto = rs.getInt("Sconto");
                int Disponibilita = rs.getInt("Disponibilita");
                String Foto = rs.getString("Foto");
                Libro l = new Libro(ISBN,Titolo,Descrizione,Prezzo,new GregorianCalendar(),Editore,Sconto,Disponibilita,Foto);
                libri.add(l);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return libri;
    }

}
