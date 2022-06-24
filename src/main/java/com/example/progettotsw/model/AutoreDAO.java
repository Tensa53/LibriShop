package com.example.progettotsw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                Autore autore = new Autore(CF,Nome);
                autori.add(autore);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autori;
    }

    public Autore doRetrievebyName(String nome){
        String sql = "SELECT * FROM Autore WHERE Nome = ?;";

        Autore autore = null;

        try (Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setString(1,nome);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                autore = new Autore(rs.getString("CF"),rs.getString("Nome"));
                return autore;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autore;
    }

    public Autore doRetrievebyCF(String cf) {
        String sql = "SELECT * FROM Autore WHERE CF = ?;";

        Autore autore = null;

        try (Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setString(1,cf);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                autore = new Autore(rs.getString("CF"),rs.getString("Nome"));
                return autore;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autore;
    }

    public Autore doRetrievebyISBNLibro(String isbn){
        String sql = "SELECT A.Nome,A.CF FROM Autore A,Scrittura S WHERE (A.CF = S.Autore) AND S.ISBNLibro = ?";

        Autore autore = null;

        try (Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setString(1,isbn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                autore = new Autore(rs.getString("CF"),rs.getString("Nome"));
                return autore;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autore;
    }

    public int doUpdate(Autore autore) {
        String sql = "UPDATE Autore SET Nome = ? WHERE CF = ?;";

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1,autore.getNome());
            ps.setString(2,autore.getCF());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(Autore autore) {
        String sql = "INSERT INTO Autore VALUES (?,?);";

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1,autore.getCF());
            ps.setString(2,autore.getNome());

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doRemoveAutorebyCF(String cf) {
        String sql = "DELETE FROM Autore WHERE CF = ?;";

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1,cf);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
