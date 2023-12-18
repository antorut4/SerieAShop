package test;

import model.Prodotto;
import model.ProdottoDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdottoDAOTest {

    private ProdottoDAO prodottoDAO;

    @BeforeEach
    void setUp() {
        // Inizializza il DAO prima di ogni test
        prodottoDAO = new ProdottoDAO();
    }

    @Test
    void testDoRetrieveById() {
        try {
            Prodotto prodotto = prodottoDAO.doRetrieveById(1);
            assertNotNull(prodotto);
        } catch (SQLException e) {
            fail("Eccezione durante il recupero del prodotto per ID: " + e.getMessage());
        }
    }

    @Test
    void testDoUpdateProdotto() {
        try {
            // Supponiamo che ci sia almeno un prodotto nel database di test
            Prodotto prodottoToUpdate = prodottoDAO.doRetrieveById(2);

            // Modifica alcune proprietà del prodotto
            prodottoToUpdate.setPrezzo(19.99);
            prodottoToUpdate.setNome("Nuovo Nome");

            Prodotto updatedProdotto = prodottoDAO.doUpdateProdotto(prodottoToUpdate);

            assertNotNull(updatedProdotto);
            assertEquals(19.99, updatedProdotto.getPrezzo());
            assertEquals("Nuovo Nome", updatedProdotto.getNome());

        } catch (SQLException e) {
            fail("Eccezione durante l'aggiornamento del prodotto: " + e.getMessage());
        }
    }


    @Test
    void testDoUpdatePrezzo() {
        try {
            Prodotto prodottoToUpdate = prodottoDAO.doRetrieveById(2);

            double newPrezzo = 19.99;
            Prodotto updatedProdotto = prodottoDAO.doUpdatePrezzo(newPrezzo, prodottoToUpdate.getId());

            assertNotNull(updatedProdotto);
            assertEquals(newPrezzo, updatedProdotto.getPrezzo());

        } catch (SQLException e) {
            fail("Eccezione durante l'aggiornamento del prezzo del prodotto: " + e.getMessage());
        }
    }

    @Test
    void testDoUpdateQuantita() {
        try {
            // Supponiamo che ci sia almeno un prodotto nel database di test
            Prodotto prodottoToUpdate = prodottoDAO.doRetrieveById(2);

            int newQuantita = 55;
            prodottoToUpdate.setQuantita(newQuantita);
            prodottoDAO.doUpdateQuantita(prodottoToUpdate);

            Prodotto updatedProdotto = prodottoDAO.doRetrieveById(1);

            assertNotNull(updatedProdotto);
            assertEquals(newQuantita, updatedProdotto.getQuantita());

        } catch (SQLException e) {
            fail("Eccezione durante l'aggiornamento della quantità del prodotto: " + e.getMessage());
        }
    }

    @Test
    void testDeleteProdotto() {
        try {
            // Supponiamo che ci sia almeno un prodotto nel database di test
            Prodotto prodottoToDelete = prodottoDAO.doRetrieveById(1);

            Prodotto deletedProdotto = prodottoDAO.deleteProdotto(prodottoToDelete.getId());

            assertNotNull(deletedProdotto);

            // Verifica che il prodotto sia stato eliminato correttamente
            Prodotto retrievedProdotto = prodottoDAO.doRetrieveById(deletedProdotto.getId());
            assertNull(retrievedProdotto);

        } catch (SQLException e) {
            fail("Eccezione durante l'eliminazione del prodotto: " + e.getMessage());
        }
    }

    @Test
    void testDoRetrieveByIdFailure() {
        try {
            // Supponiamo che non ci sia un prodotto con ID -1 nel database di test
            Prodotto prodotto = prodottoDAO.doRetrieveById(-1);
            assertNull(prodotto, "Il prodotto con ID -1 non dovrebbe esistere");
        } catch (SQLException e) {
            fail("Eccezione inaspettata durante il recupero del prodotto per ID: " + e.getMessage());
        }
    }

    @Test
    void testDoUpdateProdottoFailure() {
        try {
            // Supponiamo che ci sia un problema nell'aggiornare il prodotto con ID -1
            Prodotto prodottoToUpdate = prodottoDAO.doRetrieveById(-1);

            assertThrows(SQLException.class, () -> prodottoDAO.doUpdateProdotto(prodottoToUpdate),
                    "Dovrebbe lanciare un'eccezione SQLException durante l'aggiornamento del prodotto");
        } catch (SQLException e) {
            fail("Eccezione inaspettata durante il recupero del prodotto per l'aggiornamento: " + e.getMessage());
        }
    }

    @Test
    void testDoUpdatePrezzoFailure() {
        // Supponiamo che ci sia un problema nell'aggiornare il prezzo del prodotto con ID -1
        double newPrezzo = 19.99;

        assertThrows(SQLException.class, () -> prodottoDAO.doUpdatePrezzo(newPrezzo, -1),
                "Dovrebbe lanciare un'eccezione SQLException durante l'aggiornamento del prezzo del prodotto");
    }

    @Test
    void testDoUpdateQuantitaFailure() {
        try {
            // Supponiamo che ci sia un problema nell'aggiornare la quantità del prodotto con ID -1
            Prodotto prodottoToUpdate = prodottoDAO.doRetrieveById(-1);

            assertThrows(SQLException.class, () -> prodottoDAO.doUpdateQuantita(prodottoToUpdate),
                    "Dovrebbe lanciare un'eccezione SQLException durante l'aggiornamento della quantità del prodotto");
        } catch (SQLException e) {
            fail("Eccezione inaspettata durante l'aggiornamento della quantità del prodotto: " + e.getMessage());
        }
    }

    @Test
    void testDeleteProdottoFailure() {
        // Supponiamo che ci sia un problema nell'eliminare il prodotto con ID -1
        assertThrows(SQLException.class, () -> prodottoDAO.deleteProdotto(-1),
                "Dovrebbe lanciare un'eccezione SQLException durante l'eliminazione del prodotto");
    }
}
