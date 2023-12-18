package test;

import model.Squadra;
import model.SquadraDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquadraDAOTest {

    private SquadraDAO squadraDAO;

    @BeforeEach
    void setUp() {
        // Inizializza il DAO prima di ogni test
        squadraDAO = new SquadraDAO();
    }

    @Test
    void testDoSave() {
        // Test di successo: salva una squadra corretta
        Squadra squadraValida = new Squadra();
        squadraValida.setNomeSquadra("SquadraValida");
        squadraValida.setPathLogo("path/logo/valido.png");

        assertTrue(squadraDAO.addSquadra(squadraValida));

        // Test di fallimento: tenta di salvare una squadra con nome nullo
        Squadra squadraConNomeNullo = new Squadra();
        squadraValida.setPathLogo("path/logo/invalidovalido.png");

        assertFalse(squadraDAO.addSquadra(squadraConNomeNullo));
    }
}
