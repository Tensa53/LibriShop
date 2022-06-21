package com.example.progettotsw.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class LibroDAO {

    public Libro doRetrieveById(String ISBN) {
        Libro libro = null;

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Libro WHERE ISBN = ?");
            ps.setString(1,ISBN);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                libro = new Libro();
                libro.setISBN(ISBN);
                libro.setTitolo(rs.getString("Titolo"));
                libro.setDescrizione(rs.getString("Descrizione"));
                libro.setDisponibilita(rs.getInt("Disponibilita"));
                libro.setEditore(rs.getString("Editore"));
                String dataPubblicazioneString = rs.getString("DataPubblicazione");
                GregorianCalendar dataPubblicazione = new GregorianCalendar(Integer.parseInt(dataPubblicazioneString.split("-")[0]),(Integer.parseInt(dataPubblicazioneString.split("-")[1]))-1,Integer.parseInt(dataPubblicazioneString.split("-")[2]));
                libro.setDataPubblicazione(dataPubblicazione);
                libro.setSconto(rs.getBigDecimal("Sconto"));
                libro.setFoto(rs.getString("Foto"));
                libro.setPrezzo(rs.getBigDecimal("Prezzo"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return libro;
    }

    public List<Libro> doRetrievebyString(String ricerca){
        String sql = "SELECT * FROM Libro WHERE Titolo LIKE ? OR Titolo LIKE ? OR Titolo LIKE ?";


        List<Libro> libri = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); java.sql.Statement stmt = conn.createStatement();PreparedStatement ps = conn.prepareStatement(sql);){


            ps.setString(1,ricerca + "%");
            ps.setString(2,"%" + ricerca);
            ps.setString(3,"%" + ricerca + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String ISBN = rs.getString("ISBN");
                String Titolo = rs.getString("Titolo");
                String Descrizione = rs.getString("Descrizione");
                BigDecimal Prezzo = rs.getBigDecimal("Prezzo");
                String Editore = rs.getString("Editore");
                String dataPubblicazioneString = rs.getString("DataPubblicazione");
                GregorianCalendar dataPubblicazione = new GregorianCalendar(Integer.parseInt(dataPubblicazioneString.split("-")[0]),Integer.parseInt(dataPubblicazioneString.split("-")[1]),Integer.parseInt(dataPubblicazioneString.split("-")[2]));
                BigDecimal Sconto = rs.getBigDecimal("Sconto");
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

    public List<Libro> doRetrieveAll(){
        String sql = "SELECT * FROM Libro;";

        List<Libro> libri = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                String ISBN = rs.getString("ISBN");
                String Titolo = rs.getString("Titolo");
                String Descrizione = rs.getString("Descrizione");
                BigDecimal Prezzo = rs.getBigDecimal("Prezzo");
                String Editore = rs.getString("Editore");
                String dataPubblicazioneString = rs.getString("DataPubblicazione");
                GregorianCalendar dataPubblicazione = new GregorianCalendar(Integer.parseInt(dataPubblicazioneString.split("-")[0]),(Integer.parseInt(dataPubblicazioneString.split("-")[1]))-1,Integer.parseInt(dataPubblicazioneString.split("-")[2]));
                BigDecimal Sconto = rs.getBigDecimal("Sconto");
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

    public int doUpdate(Libro libro,String autore,String [] genere) {
        String sql = "UPDATE Libro SET Titolo = ?,Descrizione = ?,Prezzo = ?,DataPubblicazione = ?,Editore = ?,Sconto = ?,Disponibilita = ? WHERE ISBN = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,libro.getTitolo());
            pstmt.setString(2,libro.getDescrizione());
            pstmt.setBigDecimal(3,libro.getPrezzo());
            pstmt.setString(4,libro.getDataPubblicazioneReversedString());
            pstmt.setString(5,libro.getEditore());
            pstmt.setBigDecimal(6,libro.getSconto());
            pstmt.setInt(7,libro.getDisponibilita());
            pstmt.setString(8,libro.getISBN());
            int row = pstmt.executeUpdate();
            doUpdateAppartenenzaGenereLibro(libro,genere);
            doUpdateScritturaAutoreLibro(libro,autore);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int doUpdateFoto(Libro libro,String subpath) {
        String sql = "UPDATE Libro SET Foto = ? WHERE ISBN = ?;";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,subpath);
            pstmt.setString(2,libro.getISBN());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateAppartenenzaGenereLibro(Libro libro,String [] genere){
        String sql = "DELETE FROM Appartenenza WHERE ISBNLibro = ?;";
        String sql2 = "INSERT INTO Appartenenza VALUES(?,?)";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); PreparedStatement pst = conn.prepareStatement(sql2);) {
            pstmt.setString(1,libro.getISBN());
            pstmt.executeUpdate();

            for (String g : genere){
                pst.setString(1,libro.getISBN());
                pst.setString(2,g);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doUpdateScritturaAutoreLibro(Libro libro,String autore) {
        String sql = "DELETE FROM Scrittura WHERE ISBNLibro = ?;";
        String sql2 = "INSERT INTO Scrittura VALUES(?,?)";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); PreparedStatement pst = conn.prepareStatement(sql2);) {
            pstmt.setString(1,libro.getISBN());
            pstmt.executeUpdate();

            pst.setString(1,libro.getISBN());
            pst.setString(2,autore);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateDisponibilitaAllFromList(List<Libro> libri){
        String sql = "UPDATE Libro SET Disponibilita = ? WHERE ISBN = ?;";

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            for (Libro l : libri){
                ps.setInt(1,l.getDisponibilita());
                ps.setString(2,l.getISBN());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(Libro libro,String autore,String [] genere){
        String sql = "INSERT INTO Libro VALUES (?,?,?,?,?,?,?,?,?);";

        try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,libro.getISBN());
            pstmt.setString(2,libro.getTitolo());
            pstmt.setString(3,libro.getDescrizione());
            pstmt.setBigDecimal(4,libro.getPrezzo());
            pstmt.setString(5,libro.getDataPubblicazioneReversedString());
            pstmt.setString(6,libro.getEditore());
            pstmt.setBigDecimal(7,libro.getSconto());
            pstmt.setInt(8,libro.getDisponibilita());
            pstmt.setString(9,libro.getFoto());
            int row = pstmt.executeUpdate();
            doSaveAutoreLibro(libro,autore);
            doSaveGenereLibro(libro,genere);
            return row;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSaveGenereLibro(Libro libro,String[] generi){
        String sql = "INSERT INTO Appartenenza VALUES (?,?);";
        String isbn = libro.getISBN();

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            for (String g : generi){
                ps.setString(1,isbn);
                ps.setString(2,g);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSaveAutoreLibro(Libro libro,String CF){
        String sql = "INSERT INTO Scrittura VALUES (?,?);";
        String isbn = libro.getISBN();

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1,isbn);
            ps.setString(2,CF);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doDeleteLibrobyID(String ISBN) {
        String sql = "DELETE FROM Libro WHERE ISBN = ?;";
        String sql2 = "SET foreign_key_checks = ?;";

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql); PreparedStatement pst = con.prepareStatement(sql2)) {
            ps.setString(1,ISBN);
            doDeleteAppartenenzaGenereLibro(ISBN);
            doDeleteScritturaAutoreLibro(ISBN);
            pst.setInt(1,0);
            pst.executeUpdate();
            int row = ps.executeUpdate();
            pst.setInt(1,1);
            pst.executeUpdate();
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteAppartenenzaGenereLibro(String ISBN) {
        String sql = "DELETE FROM Appartenenza WHERE ISBNLibro = ?;";

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1,ISBN);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteScritturaAutoreLibro(String ISBN) {
        String sql = "DELETE FROM Scrittura WHERE ISBNLibro = ?;";

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1,ISBN);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
