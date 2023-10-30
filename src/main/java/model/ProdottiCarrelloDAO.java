package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottiCarrelloDAO {

    public void doSaveProdottoCarrello(ProdottiCarrello prodottiCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ProdottiCarrello (quantita, idCarrello, idProdotto, taglia) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, prodottiCarrello.getQuantita());
            ps.setInt(2, prodottiCarrello.getIdCarrello());
            ps.setInt(3, prodottiCarrello.getIdProdotto());
            ps.setString(4, prodottiCarrello.getTaglia());
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
                    con.prepareStatement("SELECT idProdCarr ,quantita, idCarrello, idProdotto, taglia FROM ProdottiCarrello WHERE idProdotto=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ProdottiCarrello pc = new ProdottiCarrello();
                pc.setIdProdCarr(rs.getInt(1));
                pc.setQuantita(rs.getInt(2));
                pc.setIdCarrello(rs.getInt(3));
                pc.setIdProdotto(rs.getInt(4));
                pc.setTaglia(rs.getString(5));
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
                    con.prepareStatement("SELECT idProdCarr, quantita, idCarrello, idProdotto, taglia FROM ProdottiCarrello WHERE idCarrello=? AND idProdotto=?");
            ps.setInt(1, idCarrello);
            ps.setInt(2, idProdotto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ProdottiCarrello pc = new ProdottiCarrello();
                pc.setIdProdCarr(rs.getInt(1));
                pc.setQuantita(rs.getInt(2));
                pc.setIdCarrello(rs.getInt(3));
                pc.setIdProdotto(rs.getInt(4));
                pc.setTaglia(rs.getString(5));
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
                pc.setTaglia(rs.getString(5));

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

    public ProdottiCarrello deleteProdottoCarrello(int id, int idCar) {

        ProdottiCarrello prodottiCarrello = doRetrieveById(id);
        if (prodottiCarrello != null) {
            System.out.println("Entro qui?");
            try (Connection con = ConPool.getConnection()) {

                con.setAutoCommit(false);

                String sqlCarrello = "DELETE FROM ProdottiCarrello WHERE idProdotto = ? and idCarrello=?";
                PreparedStatement ps = con.prepareStatement(sqlCarrello);
                ps.setInt(1, id);
                ps.setInt(2,idCar);
                int rowsDeletedCarr = ps.executeUpdate();

                if (rowsDeletedCarr > 0) {  //cancellazione avvenuta correttamente
                    con.commit();
                    System.out.println("Il prodotto è stato cancellato correttamente. id=" + id);
                } else {    //cancellazione fallita e si riporta indietro la query (transazione)
                    System.out.println("Il prodotto non è stato eliminato");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Errore durante l'eliminazione del prodotto.", e);
            }
        } else {
            return null;
        }
        return null;
    }

    public List<ProdottiCarrello> doRetrieveByCarrello(int idCarrello) {
        System.out.println(idCarrello);
        List<ProdottiCarrello> prod=new ArrayList<>();
        ProdottiCarrello pc=new ProdottiCarrello();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idProdCarr, quantita, idCarrello, idProdotto, taglia FROM ProdottiCarrello WHERE idCarrello=?");
            ps.setInt(1, idCarrello);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                pc=new ProdottiCarrello();
                pc.setIdProdCarr(rs.getInt(1));
                pc.setQuantita(rs.getInt(2));
                pc.setIdCarrello(rs.getInt(3));
                pc.setIdProdotto(rs.getInt(4));
                pc.setTaglia(rs.getString(5));
                prod.add(pc);
            }
            for(ProdottiCarrello pr: prod){
                System.out.println(pc.getIdProdotto());
            }
            return prod;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateProdottiQuantita(ProdottiCarrello pc) {

        try (Connection con = ConPool.getConnection()) {
            Statement st = con.createStatement();
            String query = "update ProdottiCarrello set quantita='"+pc.getQuantita()+"' WHERE idProdotto="+pc.getIdProdotto();
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProdottiCarrello> doRetriveAll(){
        List<ProdottiCarrello> prodotti = new ArrayList<>();
        User user;

        ResultSet rs;
        Statement st;

        try(Connection connection = ConPool.getConnection()) {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM prodotticarrello");

            while(rs.next()){
                ProdottiCarrello p = new ProdottiCarrello();
                p.setIdProdCarr(rs.getInt(1));
                p.setQuantita(rs.getInt(2));
                p.setIdCarrello(rs.getInt(3));
                p.setIdProdotto(rs.getInt(4));
                p.setTaglia(rs.getString(5));

                prodotti.add(p);
            }

            connection.close();
            return prodotti;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
