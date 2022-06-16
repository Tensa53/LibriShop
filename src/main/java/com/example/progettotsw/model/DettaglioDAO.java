package com.example.progettotsw.model;

import java.math.BigDecimal;
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
                BigDecimal prezzo = rs.getBigDecimal("Prezzo");
                String ISBN = rs.getString("ISBNLibro");
                String TitoloLibro = rs.getString("TitoloLibro");
                int id = rs.getInt("ID");

                LibroDAO libroDAO = new LibroDAO();
                Libro libro = libroDAO.doRetrieveById(ISBN);

                if(libro == null)
                    libro = new Libro(ISBN,TitoloLibro);

                Dettaglio dettaglio = new Dettaglio(quantita,prezzo,libro,id);
                dettagli.add(dettaglio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dettagli;
    }


    public void doSaveAllbyCarrelloUtente(List<Dettaglio> dettagli,String mail) {
        String sql = "INSERT INTO Dettaglio(Quantita,Prezzo,Carrello,ISBNLibro,TitoloLibro) VALUES (?,?,?,?,?);";

        for(Dettaglio d : dettagli){
            try(Connection conn = ConPool.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
                pstmt.setInt(1,d.getQuantita());
                pstmt.setBigDecimal(2,d.getPrezzo());
                pstmt.setString(3,mail);
                pstmt.setString(4,d.getLibro().getISBN());
                pstmt.setString(5,d.getLibro().getTitolo());
                pstmt.executeUpdate();
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return;
    }

    public void doSaveAllfromCarrellotoOrdineUtente(String idOrdine,String mail){
        String sql = "UPDATE Dettaglio SET Dettaglio.Ordine = ? WHERE Dettaglio.Carrello = ?";
        String sql2 = "UPDATE Dettaglio SET Dettaglio.Carrello = null WHERE Dettaglio.Ordine = ?";

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); PreparedStatement pst = conn.prepareStatement(sql2)){
            ps.setString(1,idOrdine);
            ps.setString(2,mail);
            ps.executeUpdate();

            pst.setString(1,idOrdine);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                BigDecimal prezzo = rs.getBigDecimal("Prezzo");
                String ISBN = rs.getString("ISBNLibro");
                String TitoloLibro = rs.getString("TitoloLibro");
                int id = rs.getInt("id");

                LibroDAO libroDAO = new LibroDAO();
                Libro libro = libroDAO.doRetrieveById(ISBN);

                if(libro == null)
                    libro = new Libro(ISBN,TitoloLibro);

                Dettaglio dettaglio = new Dettaglio(quantita,prezzo,libro,id);
                dettagli.add(dettaglio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dettagli;
    }

}
