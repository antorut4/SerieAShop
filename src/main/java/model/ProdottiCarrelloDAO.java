package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottiCarrelloDAO {

    public void doSaveProdottoCarrello(ProdottiCarrello prodottiCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ProdottiCarrello (quantita, idCarrello, idProdotto) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, prodottiCarrello.getQuantita());
            ps.setInt(2, prodottiCarrello.getIdCarrello());
            ps.setInt(3, prodottiCarrello.getIdProdotto());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int idProdCarr = rs.getInt(1);
            prodottiCarrello.setIdProdCarr(idProdCarr);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProdottiCarrello doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idProdCarr ,quantita, idCarrello, idProdotto FROM ProdottiCarrello WHERE idProdCarr=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ProdottiCarrello pc = new ProdottiCarrello();
                pc.setIdProdCarr(rs.getInt(1));
                pc.setQuantita(rs.getInt(2));
                pc.setIdCarrello(rs.getInt(3));
                pc.setIdProdotto(rs.getInt(4));
                return pc;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProdottiCarrello doRetrieveByCarrelloAndProdotto(int idCarrello, int idProdotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idProdCarr, quantita, idCarrello, idProdotto FROM ProdottiCarrello WHERE idCarrello=? AND idProdotto=?");
            ps.setInt(1, idCarrello);
            ps.setInt(2, idProdotto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ProdottiCarrello pc = new ProdottiCarrello();
                pc.setIdProdCarr(rs.getInt(1));
                pc.setQuantita(rs.getInt(2));
                pc.setIdCarrello(rs.getInt(3));
                pc.setIdProdotto(rs.getInt(4));
                return pc;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProdottiCarrello> doRetriveAllById(int idCarrello) {
        List<ProdottiCarrello> prodotticarrello = new ArrayList<>();
        ProdottiCarrello pc;

        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM ProdottiCarrello WHERE idCarrello=?");
            ps.setInt(1, idCarrello);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pc = new ProdottiCarrello();
                pc.setIdProdCarr(rs.getInt(1));
                pc.setQuantita(rs.getInt(2));
                pc.setIdCarrello(rs.getInt(3));
                pc.setIdProdotto(rs.getInt(4));

                prodotticarrello.add(pc);
            }

            connection.close();
            return prodotticarrello;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void doUpdateProdottiCarrello(ProdottiCarrello pc) {

        try (Connection con = ConPool.getConnection()) {
            Statement st = con.createStatement();
            String query = "update ProdottiCarrello set quantita='" + pc.getQuantita() +
                    "', idCarrello='" + pc.getIdCarrello() + "', idProdotto='" + pc.getIdProdotto() + "'where idProdCarr=" + pc.getIdProdCarr() + ";";
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProdottiCarrello deleteProdottoCarrello(int id) {

        ProdottiCarrello prodottiCarrello = doRetrieveById(id);
        if (prodottiCarrello != null) {
            try (Connection con = ConPool.getConnection()) {

                con.setAutoCommit(false);

                String sqlCarrello = "DELETE FROM ProdottiCarrello WHERE idProdotto = ?";
                PreparedStatement ps = con.prepareStatement(sqlCarrello);
                ps.setInt(1, id);
                int rowsDeletedCarr = ps.executeUpdate();

                if (rowsDeletedCarr > 0) {  //cancellazione avvenuta correttamente
                    con.commit();
                    return prodottiCarrello;
                    //System.out.println("Il prodotto è stato cancellato correttamente. id=" + id);
                } else {    //cancellazione fallita e si riporta indietro la query (transazione)
                    con.rollback();
                    return null;
                    //System.out.println("Cancellazione fallita o ID prodotto non trovato.");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Errore durante l'eliminazione del prodotto.", e);
            }
        } else {
            return null;
        }

    }


}
