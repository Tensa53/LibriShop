package com.example.progettotsw.model;

import com.example.progettotsw.controller.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class LibroDAO {

    public Libro doRetrieveById(String ISBN) {
        Libro libro = new Libro();

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Libro WHERE ISBN = ?");
            ps.setString(1,ISBN);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                libro.setISBN(ISBN);
                libro.setTitolo(rs.getString("Titolo"));
                libro.setDescrizione(rs.getString("Descrizione"));
                libro.setDisponibilita(rs.getInt("Disponibilita"));
                libro.setEditore(rs.getString("Editore"));
                String dataPubblicazioneString = rs.getString("DataPubblicazione");
                GregorianCalendar dataPubblicazione = new GregorianCalendar(Integer.parseInt(dataPubblicazioneString.split("-")[0]),Integer.parseInt(dataPubblicazioneString.split("-")[1]),Integer.parseInt(dataPubblicazioneString.split("-")[2]));
                libro.setDataPubblicazione(dataPubblicazione);
                libro.setSconto(rs.getInt("Sconto"));
                libro.setFoto(rs.getString("Foto"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return libro;
    }

    public List<Libro> doRetrieveAll(){
        String sql = "SELECT * FROM Libro;";

        List<Libro> libri = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                String ISBN = rs.getString("ISBN");
                String Titolo = rs.getString("Titolo");
                String Descrizione = rs.getString("Descrizione");
                float Prezzo = rs.getFloat("Prezzo");
                String Editore = rs.getString("Editore");
                String dataPubblicazioneString = rs.getString("DataPubblicazione");
                GregorianCalendar dataPubblicazione = new GregorianCalendar(Integer.parseInt(dataPubblicazioneString.split("-")[0]),Integer.parseInt(dataPubblicazioneString.split("-")[1]),Integer.parseInt(dataPubblicazioneString.split("-")[2]));
                int Sconto = rs.getInt("Sconto");
                int Disponibilita = rs.getInt("Disponibilita");
                String Foto = rs.getString("Foto");
                Libro l = new Libro(ISBN,Titolo,Descrizione,Prezzo,dataPubblicazione,Editore,Sconto,Disponibilita,Foto);
                libri.add(l);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return libri;
    }

}
