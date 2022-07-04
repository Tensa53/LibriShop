package com.example.progettotsw.model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class OrdineDAO {

    public Ordine doRetrievebyId(int id) {
        String sql = "SELECT * FROM Ordine WHERE ID = ?;";

        Ordine ordine = null;

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                ordine = composeOrdine(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ordine;
    }

    public List<Ordine> doRetrieveAll(){
        String sql = "SELECT * FROM Ordine;";

        ArrayList<Ordine> ordini = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                Ordine ordine = composeOrdine(rs);
                ordini.add(ordine);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ordini;
    }

    public List<Ordine> doRetrieveAllbyUserMail(String mail) {
        String sql = "SELECT * FROM Ordine WHERE Utente = ?;";

        List<Ordine> ordini = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,mail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Ordine ordine = composeOrdine(rs);
                ordini.add(ordine);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ordini;
    }

    public List<String> doRetrieveAllUserMail() {
        String sql = "SELECT Utente From Ordine";

        List <String> utentiMail = new ArrayList<>();

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                utentiMail.add(rs.getString("Utente"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return utentiMail;
    }

    public int doSave(Ordine ordine) {
        String sql = "INSERT INTO Ordine (DataOrdine,Via,Numero,CAP,Citta,Provincia,NumeroCarta,Scadenza,CCV,Totale,Utente) VALUES (?,?,?,?,?,?,?,?,?,?,?);";

        String sql2 = "SELECT ID FROM Ordine WHERE Ordine.Utente = ? ORDER BY ID DESC LIMIT 1";

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
            ps.setString(2,ordine.getIndirizzo().getVia());
            ps.setString(3,ordine.getIndirizzo().getCivico());
            ps.setString(4,ordine.getIndirizzo().getCAP());
            ps.setString(5,ordine.getIndirizzo().getCitta());
            ps.setString(6,ordine.getIndirizzo().getProvincia());
            ps.setString(7,ordine.getPagamento().getNumeroCarta());
            ps.setString(8,ordine.getPagamento().getScadenzaReversedString());
            ps.setString(9,ordine.getPagamento().getCCV());
            ps.setBigDecimal(10,ordine.getTotale());
            ps.setString(11,ordine.getUtente().getMail());

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

    public int doRemovebyId(int id) {
        String sql = "DELETE FROM Ordine WHERE ID = ?";

        DettaglioDAO dettaglioDAO = new DettaglioDAO();

        try(Connection conn = ConPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1,id);

            dettaglioDAO.doRemoveFromOrdine(id);

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Ordine composeOrdine(ResultSet rs) throws SQLException {
        String timestamp = rs.getString("DataOrdine");
        System.out.println(timestamp);
        String giornoOrdine = timestamp.split(" ")[0];
        String oraOrdine = timestamp.split(" ")[1];
        System.out.println(giornoOrdine);
        System.out.println(oraOrdine);
        int anno = Integer.parseInt(giornoOrdine.split("-")[0]);
        int mese = (Integer.parseInt(giornoOrdine.split("-")[1])) - 1;
        int giorno = Integer.parseInt(giornoOrdine.split("-")[2]);
        int ore = Integer.parseInt(oraOrdine.split(":")[0]);
        int minuti = Integer.parseInt(oraOrdine.split(":")[1]);
        int secondi = Integer.parseInt(oraOrdine.split(":")[2]);
        GregorianCalendar dataOrdine = new GregorianCalendar(anno,mese,giorno,ore,minuti,secondi);
        String via = rs.getString("Via");
        String numero = rs.getString("Numero");
        String CAP = rs.getString("CAP");
        String Citta = rs.getString("Citta");
        String Provincia = rs.getString("Provincia");
        Indirizzo indirizzo = new Indirizzo(via,numero,CAP,Citta,Provincia);
        String numeroCarta = rs.getString("NumeroCarta");
        String scadenza = rs.getString("Scadenza");
        GregorianCalendar scadenzaCarta = new GregorianCalendar(Integer.parseInt(scadenza.split("-")[0]),(Integer.parseInt(scadenza.split("-")[1]))-1,Integer.parseInt(scadenza.split("-")[2]));
        String CCV = rs.getString("CCV");
        Pagamento pagamento = new Pagamento(numeroCarta,scadenzaCarta,CCV);
        BigDecimal totale = rs.getBigDecimal("totale");
        UtenteDAO utenteDAO = new UtenteDAO();
        String mail = rs.getString("Utente");
        Utente utente = utenteDAO.doRetrieveByMail(mail);
        if (utente == null)
            utente = new Utente(mail);
        Ordine ordine = new Ordine(indirizzo,pagamento,totale,utente);
        ordine.setDataOrdine(dataOrdine);
        int id = rs.getInt("ID");
        ordine.setId(id);
        DettaglioDAO dettaglioDAO = new DettaglioDAO();
        ordine.setDettagli(dettaglioDAO.doRetrievebyIdOrdine(id));
        return ordine;
    }

}
