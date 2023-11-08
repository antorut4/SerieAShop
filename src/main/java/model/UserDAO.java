package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public User doRetrieveById(int id) throws SQLException {
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT Username, nome, Cognome, Email, Telefono, Indirizzo, Password,admin WHERE Username=?");
            ps.setInt(


                    1,id);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                User p=new User();
                p.setUsername(rs.getString(1));
                p.setNome(rs.getString(2));
                p.setCognome(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setTelefono(rs.getString(5));
                p.setIndirizzo(rs.getString(6));
                p.setPassword(rs.getString(7));
                p.setAdmin(rs.getBoolean(8));

                return p;
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doSave(User user)throws SQLException{
        try(Connection con= ConPool.getConnection()){
            PreparedStatement ps= con.prepareStatement("INSERT INTO utente(Username, nome, Cognome, Email, Telefono, Indirizzo, Password, admin) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2,user.getNome());
            ps.setString(3,user.getCognome());
            ps.setString(4,user.getEmail());
            ps.setString(5,user.getTelefono());
            ps.setString(6,user.getIndirizzo());
            ps.setString(7,user.getPassword());
            ps.setBoolean(8,false);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConPool.getConnection();

            // Controlla se l'utente esiste
            String sql = "SELECT  Username, Nome, Cognome, Email, Telefono, Indirizzo, Password,admin FROM utente WHERE Username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                // L'utente non esiste
                System.out.println("Username non trovato");
                resultSet.close();
                return;
            }

            // Elimina l'utente
            //Elimina prima il carrello

            sql="DELETE FROM carrello WHERE username =?";
            statement=connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.executeUpdate();


            sql = "DELETE FROM utente WHERE Username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                // L'utente è stato eliminato correttamente
                System.out.println("L'utente è stato eliminato correttamente");
            } else {
                // Cancellazione fallita
                System.out.println("Cancellazione Fallita!");
            }
        } finally {
            // Chiudi il ResultSet, lo Statement e la Connection
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }
    public User doRetrieveByMail(String email) throws SQLException {
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT Username, nome, Cognome, Email, Telefono, Indirizzo, Password,admin FROM utente WHERE Email=?");
            ps.setString(1,email);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                User p=new User();
                p.setUsername(rs.getString(1));
                p.setNome(rs.getString(2));
                p.setCognome(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setTelefono(rs.getString(5));
                p.setIndirizzo(rs.getString(6));
                p.setPassword(rs.getString(7));
                p.setAdmin(rs.getBoolean(8));

                return p;
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<User> doRetriveAll(){
        List<User> utenti = new ArrayList<>();
        User user;

        ResultSet rs;
        Statement st;

        try(Connection connection = ConPool.getConnection()) {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM utente");

            while(rs.next()){
                user = new User();
                user.setUsername(rs.getString(1));
                user.setNome(rs.getString(2));
                user.setCognome(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setTelefono(rs.getString(5));
                user.setIndirizzo(rs.getString(6));
                user.setPassword(rs.getString(7));

                utenti.add(user);
            }

            connection.close();
            return utenti;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public User doRetrieveByUsername(String username) throws SQLException {
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT Username, nome, Cognome, Email, Telefono, Indirizzo, Password,admin FROM utente WHERE Username=?");
            ps.setString(1,username);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                User p=new User();
                p.setUsername(rs.getString(1));
                p.setNome(rs.getString(2));
                p.setCognome(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setTelefono(rs.getString(5));
                p.setIndirizzo(rs.getString(6));
                p.setPassword(rs.getString(7));
                p.setAdmin(rs.getBoolean(8));

                return p;
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
