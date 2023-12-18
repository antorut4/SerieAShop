package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SquadraDAO {
    public List<Squadra> doSave() {
        List<Squadra> squadre = new ArrayList<>();

        try (Connection con = ConPool.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT idSquadra, pathLogo FROM Squadra");

            while (rs.next()) {
                String idSquadra = rs.getString("idSquadra");
                String pathLogo = rs.getString("pathLogo");
                Squadra sq= new Squadra();
                sq.setNomeSquadra(idSquadra);
                sq.setPathLogo(pathLogo);
                squadre.add(sq);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return squadre;
    }

    public boolean addSquadra(Squadra squadra) {
        String query = "INSERT INTO squadra (nome_squadra, path_logo) VALUES (?, ?)";

        try (Connection connection = ConPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Imposta i parametri della query con i valori della squadra
            preparedStatement.setString(1, squadra.getNomeSquadra());
            preparedStatement.setString(2, squadra.getPathLogo());

            // Esegue l'inserimento nel database
            int rowsAffected = preparedStatement.executeUpdate();

            // Restituisci true se almeno una riga è stata inserita, altrimenti false
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Gestisci eventuali eccezioni durante l'interazione con il database
            e.printStackTrace();
            return false;  // Ritorna false in caso di errore
        }
    }
}
