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
}
