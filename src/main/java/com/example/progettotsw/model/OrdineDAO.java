package com.example.progettotsw.model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class OrdineDAO {

    public List<Ordine> doRetrieveAll(){
        String sql = "SELECT * FROM Ordine;";

        ArrayList<Ordine> ordini = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                String data = rs.getString("DataOrdine");
                GregorianCalendar dataOrdine = new GregorianCalendar(Integer.parseInt(data.split("-")[0]),Integer.parseInt(data.split("-")[1]),Integer.parseInt(data.split("-")[2]));
                String via = rs.getString("Via");
                String numero = rs.getString("Numero");
                String CAP = rs.getString("CAP");
                String Citta = rs.getString("Citta");
                String Provincia = rs.getString("Provincia");
                String Stato = rs.getString("Stato");
                Indirizzo indirizzo = new Indirizzo(via,numero,CAP,Citta,Provincia,Stato);
                String numeroCarta = rs.getString("NumeroCarta");
                String scadenza = rs.getString("Scadenza");
                GregorianCalendar scadenzaCarta = new GregorianCalendar(Integer.parseInt(scadenza.split("-")[0]),Integer.parseInt(scadenza.split("-")[1]),Integer.parseInt(scadenza.split("-")[2]));
                String CCV = rs.getString("CCV");
                Pagamento pagamento = new Pagamento(numeroCarta,scadenzaCarta,CCV);
                BigDecimal totale = rs.getBigDecimal("totale");
                UtenteDAO utenteDAO = new UtenteDAO();
                String mail = rs.getString("Utente");
                Utente utente = utenteDAO.doRetrieveByMail(mail);
                Ordine ordine = new Ordine(dataOrdine,indirizzo,pagamento,totale,utente);
                ordini.add(ordine);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ordini;
    }

    public int doSave(Ordine ordine) {
        String sql = "INSERT INTO Ordine (DataOrdine,Via,Numero,CAP,Citta,Provincia,Stato,NumeroCarta,Scadenza,CCV,Totale,Utente) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

        String sql2 = "SELECT ID FROM Ordine WHERE Ordine.Utente = ? ORDER BY ID DESC LIMIT 1";

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1,ordine.getDataOrdineReversedString());
            ps.setString(2,ordine.getIndirizzo().getVia());
            ps.setString(3,ordine.getIndirizzo().getCivico());
            ps.setString(4,ordine.getIndirizzo().getCAP());
            ps.setString(5,ordine.getIndirizzo().getCitta());
            ps.setString(6,ordine.getIndirizzo().getProvincia());
            ps.setString(7,ordine.getIndirizzo().getStato());
            ps.setString(8,ordine.getPagamento().getNumeroCarta());
            ps.setString(9,ordine.getPagamento().getScadenzaReversedString());
            ps.setString(10,ordine.getPagamento().getCCV());
            ps.setBigDecimal(11,ordine.getTotale());
            ps.setString(12,ordine.getUtente().getMail());

            int row = ps.executeUpdate();

            PreparedStatement pst = conn.prepareStatement(sql2);
            pst.setString(1,ordine.getUtente().getMail());
            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                DettaglioDAO dettaglioDAO = new DettaglioDAO();
                dettaglioDAO.doSaveAllfromCarrellotoOrdineUtente(rs.getString("ID"),ordine.getUtente().getMail());
            }

            return row;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
