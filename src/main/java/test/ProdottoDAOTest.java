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
        // Puoi inizializzare qui le risorse necessarie, ad esempio, un database di test
        prodottoDAO = new ProdottoDAO();
    }

    @AfterEach
    void tearDown() {
        // Puoi rilasciare qui le risorse utilizzate, ad esempio, chiudere la connessione al database di test
    }

    @Test
    void testDoRetrieveById() {
        try {
            // Supponiamo che ci sia almeno un prodotto nel database di test
            Prodotto prodotto = prodottoDAO.doRetrieveById(1);
            assertNotNull(prodotto);
            // Puoi aggiungere ulteriori asserzioni per verificare che i valori restituiti siano corretti
        } catch (SQLException e) {
            fail("Eccezione durante il recupero del prodotto per ID: " + e.getMessage());
        }
    }

    @Test
    void testDoSaveAndDoRetrieveAll() {
        Prodotto prodottoToSave = new Prodotto(/* Inizializza l'oggetto prodotto con valori appropriati */);

        prodottoDAO.doSave(prodottoToSave);

        // Verifica che il prodotto sia stato salvato correttamente
        List<Prodotto> prodotti = prodottoDAO.doRetriveAll();
        assertNotNull(prodotti);
        assertTrue(prodotti.size() > 0);

        // Puoi aggiungere ulteriori asserzioni per verificare che i valori siano corretti

    }

    @Test
    void testDoUpdateProdotto() {
        try {
            // Supponiamo che ci sia almeno un prodotto nel database di test
            Prodotto prodottoToUpdate = prodottoDAO.doRetrieveById(1);

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

    // Aggiungi altri test per i metodi rimanenti come doUpdatePrezzo, doUpdateQuantita, ecc.
}
