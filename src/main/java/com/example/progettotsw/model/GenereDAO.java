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

    public void doSave(String nome) {
        String sql = "INSERT INTO Genere VALUES(?);";

        try(Connection con = ConPool.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1,nome);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
