package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO {

    public Carrello doRetrieveById(int id) throws java.sql.SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT idCarrello FROM prodotto WHERE idCarrello=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrello c = new Carrello();
                c.setIdCarrello(rs.getInt(1));
                c.setUsername(rs.getString(2));
                c.setTotale(rs.getInt(3));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Carrello (costo_finale, id_Client) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, carrello.getTotale());
            ps.setString(2, carrello.getUsername());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int idCarr = rs.getInt(1);
            carrello.setIdCarrello(idCarr);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doCreateCarrello(Carrello carrello){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Carrello (username) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, carrello.getUsername());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int idCarr = rs.getInt(1);
            carrello.setIdCarrello(idCarr);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Carrello> doRetriveAll() {
        List<Carrello> carrelli = new ArrayList<>();
        Carrello cs;

        ResultSet rs;
        Statement st;

        try (Connection connection = ConPool.getConnection()) {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM carrello");

            while (rs.next()) {
                cs = new Carrello();
                cs.setIdCarrello(rs.getInt(1));
                cs.setUsername(rs.getString(3));
                cs.setTotale(rs.getDouble(2));


                carrelli.add(cs);
            }

            connection.close();
            return carrelli;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void doUpdateCarrello(Carrello c) {

        try (Connection con = ConPool.getConnection()) {
            Statement st = con.createStatement();
            String query = "update carrello set totale='" + c.getTotale() +
                    "', username=" + c.getUsername() + " where idCarrello=" + c.getIdCarrello() + ";";
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteCarrello(int id) {
        try (Connection con = ConPool.getConnection()) {

            con.setAutoCommit(false);

            String sqlCarrello = "DELETE FROM composto WHERE idCarrello = ?";
            PreparedStatement ps1 = con.prepareStatement(sqlCarrello);
            ps1.setInt(1, id);
            int rowsDeletedCarr = ps1.executeUpdate();

            String sql = "DELETE FROM carrello WHERE idCarrello = ?";
            PreparedStatement ps2 = con.prepareStatement(sql);
            ps2.setInt(1, id);
            int rowsDeleted = ps2.executeUpdate();

            if (rowsDeleted > 0) {  //cancellazione avvenuta correttamente
                con.commit();
            } else {    //cancellazione fallita e si riporta indietro la query (transazione)
                con.rollback();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'eliminazione del carrello.", e);
        }
    }


    public List<Prodotto> findInCarr(int id) {
        List<Prodotto> composto = new ArrayList<>();

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idProdotto FROM prodottiCarrello where idCarrello=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt(1));

                composto.add(p);

            }

            con.close();
            return composto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Carrello doRetriveByUsername(String username){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT idCarrello, username,totale FROM carrello WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrello c = new Carrello();
                c.setIdCarrello(rs.getInt(1));
                c.setUsername(rs.getString(2));
                c.setTotale(rs.getInt(3));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
