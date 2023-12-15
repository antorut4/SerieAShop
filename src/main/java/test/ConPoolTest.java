package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.ConPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ConPoolTest {

    // Aggiungi le tue credenziali del database per il test
    private static final String DB_URL = "jdbc:mysql://localhost:3306/serieashop";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Napoli1926";

    @BeforeAll
    static void setUp() {
        // Inizializza il pool una volta prima di tutti i test
        ConPool.initPool(DB_URL, DB_USER, DB_PASSWORD);
    }

    @AfterAll
    static void tearDown() {
        // Chiudi il pool dopo tutti i test
        ConPool.closePool();
    }

    @Test
    void testGetConnection() {
        try (Connection connection = ConPool.getConnection()) {
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            fail("Failed to get a connection from the pool: " + e.getMessage());
        }
    }

    @Test
    void testCloseConnection() {
        try (Connection connection = ConPool.getConnection()) {
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            fail("Failed to get a connection from the pool: " + e.getMessage());
        }

        // Chiudi la connessione
        try {
            ConPool.closeConnection();
        } catch (SQLException e) {
            fail("Failed to close the connection: " + e.getMessage());
        }

        // Verifica che la connessione sia stata chiusa correttamente
        try (Connection connection = ConPool.getConnection()) {
            assertNotNull(connection);
            assertTrue(connection.isClosed());
        } catch (SQLException e) {
            fail("Failed to get a connection from the pool after closing: " + e.getMessage());
        }
    }
}
