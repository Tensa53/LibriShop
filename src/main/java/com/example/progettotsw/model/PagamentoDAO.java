package com.example.progettotsw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class PagamentoDAO {

    public Pagamento doRetrieveByNumeroCarta(String numeroCarta) {
        Pagamento p = null;

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Pagamento WHERE NumeroCarta = ?");
            ps.setString(1, numeroCarta);

            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                p = new Pagamento();
                p.setNumeroCarta(numeroCarta);
                String scadenzaString = rs.getString("Scadenza");
                GregorianCalendar scadenza = new GregorianCalendar(Integer.parseInt(scadenzaString.split("-")[0]), Integer.parseInt(scadenzaString.split("-")[1]), Integer.parseInt(scadenzaString.split("-")[2]));
                p.setScadenza(scadenza);
                p.setCCV("CCV");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public int doSave (Pagamento p){
        String sql = "INSERT INTO Pagamento VALUES (?,?,?);";

        try(Connection conn=ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1,p.getNumeroCarta());
            ps.setString(2,p.getScadenzaString());
            ps.setString(3,p.getCCV());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
