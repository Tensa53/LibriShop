package com.example.progettotsw.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenereDAO {

    public GenereDAO() {
    }

    public List<Genere> doRetrieveAll() {
        String sql = "SELECT * FROM Genere;";

        List<Genere> generi = new ArrayList<>();

        try (Connection con = ConPool.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                generi.add(new Genere(rs.getString("Nome")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return generi;
    }

    public List<Genere> doRetrieveByISBNLibro(String isbn) {
        String sql = "SELECT G.Nome FROM Genere G,Appartenenza A WHERE (G.Nome = A.Genere) AND A.ISBNLibro = ?;";

        List<Genere> generi = new ArrayList<>();

        try (Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,isbn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                generi.add(new Genere(rs.getString("Nome")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return generi;
    }

    public int doSave(String nome) {
        String sql = "INSERT INTO Genere VALUES(?);";

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1,nome);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int doRemove(String nome) {
        String sql = "DELETE FROM Genere WHERE Nome = ?";

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1,nome);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
