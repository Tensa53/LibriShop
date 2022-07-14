package com.example.progettotsw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaDAO {

    public List<Provincia> doRetrieveAll() {
        String sql = "SELECT * FROM Provincia ORDER BY nome";

        List<Provincia> province = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                Provincia provincia = new Provincia(rs.getString("nome"),rs.getInt("id"));
                province.add(provincia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return province;
    }

    public Provincia doRetrievebyId(int id) {
        String sql = "SELECT * FROM Provincia WHERE id = ?";

        Provincia provincia = null;

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                provincia = new Provincia(rs.getString("nome"),rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return provincia;
    }

    public Provincia doRetrievebyNome(String nome) {
        String sql = "SELECT * FROM Provincia WHERE nome = ?";

        Provincia provincia = null;

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,nome);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                provincia = new Provincia(rs.getString("nome"),rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return provincia;
    }
}
