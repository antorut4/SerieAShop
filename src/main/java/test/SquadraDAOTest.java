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
        // Supponiamo che ci siano almeno due squadre nel database di test
        List<Squadra> squadre = squadraDAO.doSave();

        assertNotNull(squadre);
        assertTrue(squadre.size() >= 2); // Almeno due squadre

        // Puoi aggiungere ulteriori asserzioni per verificare che i valori restituiti siano corretti
        for (Squadra squadra : squadre) {
            assertNotNull(squadra.getNomeSquadra());
            assertNotNull(squadra.getPathLogo());
        }
    }
}
