package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {

    public Ordine doRetrieveById(int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idOrdine, totale, dataOrdine, metodoDiPagamamento, indirizzoSpedizione, username, idCarrello FROM Ordine WHERE idOrdine=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ordine ordine = new Ordine();
                ordine.setId(rs.getInt(1));
                ordine.setTotale(rs.getInt(2));
                ordine.setDataOrd(rs.getDate(3));
                ordine.setPagamento(rs.getString(4));
                ordine.setSpedizione(rs.getString(5));
                ordine.setIdCarrello(rs.getInt(6));
                ordine.setIdCliente(rs.getString(7));
                return ordine;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ordine> doRetrieveByIdCliente(String username) {
        List<Ordine> ordini = new ArrayList<>();

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT idOrdine, totale, dataOrdine, metodoDiPagamamento, indirizzoSpedizione, idCarrello FROM Ordine WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ordine ordine = new Ordine();
                ordine.setId(rs.getInt(1));
                ordine.setTotale(rs.getInt(2));
                ordine.setDataOrd(rs.getDate(3));
                ordine.setPagamento(rs.getString(4));
                ordine.setSpedizione(rs.getString(5));
                ordine.setIdCarrello(rs.getInt(6));
                ordine.setIdCliente(rs.getString(7));
                ordini.add(ordine);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ordini;
    }


    public void doSave(Ordine ordine){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Ordine (PrezzoTotale, dataOrdine, metodoDiPagamento, indirizzoSpedizione, username, idCarrello) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, ordine.getTotale());

            java.sql.Date sqlData = new java.sql.Date(ordine.getDataOrd().getTime());
            ps.setDate(2, sqlData);
            //ps.setDate(2, (Date) ordine.getDataOrd());
            ps.setString(3, ordine.getPagamento());
            ps.setString(4, ordine.getSpedizione());
            ps.setInt(6, ordine.getIdCarrello());
            ps.setString(5, ordine.getIdCliente());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            ordine.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ordine> doRetriveAll(){
        List<Ordine> ordini = new ArrayList<>();
        Ordine or;

        ResultSet rs;
        Statement st;

        try(Connection connection = ConPool.getConnection()) {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM Ordine");

            while(rs.next()){
                or = new Ordine();
                or.setId(rs.getInt(1));
                or.setTotale(rs.getInt(2));
                or.setDataOrd(rs.getDate(3));
                or.setPagamento(rs.getString(4));
                or.setSpedizione(rs.getString(5));
                or.setIdCliente(rs.getString(6));
                or.setIdCarrello(rs.getInt(7));

                ordini.add(or);
            }

            connection.close();
            return ordini;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void doUpdateOrdine(Ordine o){

        try (Connection con = ConPool.getConnection()) {
            Statement st = con.createStatement();
            String query = "update Ordine set idOrdine='" + o.getId() + "', totale='" + o.getTotale() +
                    "', dataOrdine='" + o.getDataOrd() + "', metodoDipagamento='" + o.getPagamento() +
                    "', indirizzoSpedizione='" + o.getSpedizione() + "', idCarrello='" + o.getIdCarrello() +
                    "', username='" + o.getIdCliente() + "'where idOrdine=" + o.getId()+ ";";
            st.executeUpdate(query);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOrdine(int id) {
        try (Connection con = ConPool.getConnection()) {
            String sql = "DELETE FROM Ordine WHERE idOrdine = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("L'ordine è stato cancellato correttamente.");
            } else {
                System.out.println("Cancellazione fallita o id Ordine non trovato.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'eliminazione dell'ordine.", e);
        }
    }

}
