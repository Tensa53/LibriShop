package com.example.progettotsw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComuneDAO {

    public List<Comune> doRetrieveAll() {
        String sql = "SELECT * FROM Comune";

        List<Comune> comuni = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                Comune comune = new Comune(rs.getString("nome"));
                comuni.add(comune);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comuni;
    }

    public List<Comune> doRetrieveAllbyProvincia(int id) {
        String sql = "SELECT * FROM Comune WHERE id_provincia = ?";

        List<Comune> comuni = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Comune comune = new Comune(rs.getString("nome"));
                comuni.add(comune);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comuni;
    }

}
