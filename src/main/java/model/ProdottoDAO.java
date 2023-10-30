package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {
    public Prodotto doRetrieveById(int id) throws java.sql.SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idProdotto, descrizione, nomeProdotto, quantita, prezzo, idSquadra, categoria FROM prodotto WHERE idProdotto=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt(1));
                p.setDescrizione(rs.getString(2));
                p.setNome(rs.getString(3));
                p.setQuantita(rs.getInt(4));
                p.setPrezzo(rs.getDouble(5));
                p.setIdSquadra(rs.getString(6));
                p.setCategoria(rs.getString(7));

                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Prodotto prodotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotto (descrizione, nomeProdotto, quantita, prezzo, idSquadra, categoria) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prodotto.getDescrizione());
            ps.setString(2, prodotto.getNome());
            ps.setInt(3, prodotto.getQuantita());
            ps.setDouble(4, prodotto.getPrezzo());
            ps.setString(5,prodotto.getIdSquadra());
            ps.setString(6, prodotto.getCategoria());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            prodotto.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetriveAll() {
        List<Prodotto> Prodottos = new ArrayList<>();
        Prodotto pr;

        ResultSet rs;
        Statement st;

        try (Connection connection = ConPool.getConnection()) {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM prodotto");

            while (rs.next()) {
                pr = new Prodotto();
                pr.setId(rs.getInt(1));
                pr.setDescrizione(rs.getString(2));
                pr.setNome(rs.getString(3));
                pr.setQuantita(rs.getInt(4));
                pr.setPrezzo(rs.getDouble(5));
                pr.setIdSquadra(rs.getString(6));
                pr.setCategoria(rs.getString(7));


                Prodottos.add(pr);
            }
            connection.close();
            return Prodottos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void doUpdateProdotto(Prodotto c) {

        try (Connection con = ConPool.getConnection()) {
            Statement st = con.createStatement();
            String query = "update prodotto set nome='" + c.getNome() + "', prezzo='" + c.getPrezzo() +
                    "', descrizione='" + c.getDescrizione() + "', quantita=" + c.getQuantita() +", idSquadra="+c.getIdSquadra()+ "', categoria='" + c.getCategoria() + "' where idProdotto=" + c.getId() + ";";
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Prodotto deleteProdotto(int id) throws SQLException {

        Prodotto prodotto = doRetrieveById(id);
        if (prodotto != null) {
            try (Connection con = ConPool.getConnection()) {

                con.setAutoCommit(false);

                String sqlCarrello = "DELETE FROM ProdottiCarrello WHERE idProdotto = ?";
                PreparedStatement ps = con.prepareStatement(sqlCarrello);
                ps.setInt(1, id);
                int rowsDeletedCarr = ps.executeUpdate();

                String sqlProdotto = "DELETE FROM prodotto WHERE idProdotto = ?";
                PreparedStatement psp = con.prepareStatement(sqlProdotto);
                psp.setInt(1, id);
                int rowsDeletedProd = psp.executeUpdate();


                if (rowsDeletedProd > 0) {  //cancellazione avvenuta correttamente
                    con.commit();
                    return prodotto;
                } else {    //cancellazione fallita e si riporta indietro la query (transazione)
                    con.rollback();
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Errore durante l'eliminazione del prodotto.", e);
            }
        } else {
            return null;
        }

    }


    public Prodotto doUpdatePrezzo(double prezzo, int id_Prodotto) throws SQLException {
        Prodotto prodotto = doRetrieveById(id_Prodotto);
        if(prodotto != null) {
            PreparedStatement st = null;
            // Aggiorna il prezzo del prodotto nel database
            try (Connection con = ConPool.getConnection()) {
                String query = "update prodotto set prezzo= ? where idProdotto= ? ";
                st = con.prepareStatement(query);
                st.setDouble(1, prezzo);
                st.setInt(2, id_Prodotto);
                int rowsUpdatedProd = st.executeUpdate();

                if (rowsUpdatedProd > 0) {
                    return prodotto;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Errore durante l'aggiornamento del prodotto.", e);
            }
        }else{
            return null;
        }
    }

    public void doUpdateQuantita(Prodotto c){
        try (Connection con = ConPool.getConnection()){
            Statement st = con.createStatement();
            String query = "update prodotto set quantita=" + c.getQuantita() + " where idProdotto=" + c.getId() + ";";
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
