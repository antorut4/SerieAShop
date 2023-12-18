package test;

import control.DeleteProdotto;
import jakarta.servlet.ServletException;
import model.Prodotto;
import model.ProdottoDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteProdottoTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ProdottoDAO prodottoDAO;

    @InjectMocks
    private DeleteProdotto servlet;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void testDoGet() throws ServletException, IOException, SQLException {
        // Configura il comportamento del DAO
        int prodottoId = 1;
        Prodotto prodotto = new Prodotto();
        prodotto.setId(prodottoId);
        when(request.getParameter("id")).thenReturn(String.valueOf(prodottoId));
        when(prodottoDAO.deleteProdotto(prodottoId)).thenReturn(prodotto);

        // Esegui la servlet
        servlet.doGet(request, response);

        // Verifica che gli attributi siano impostati correttamente
        verify(request).setAttribute("prodottoEliminato", prodotto);

        // Verifica che il dispatcher della richiesta sia chiamato con la giusta pagina
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoGetProdottoNonTrovato() throws ServletException, IOException, SQLException {
        // Configura il comportamento del DAO per restituire null (prodotto non trovato)
        int prodottoId = 1;
        when(request.getParameter("id")).thenReturn(String.valueOf(prodottoId));
        when(prodottoDAO.deleteProdotto(prodottoId)).thenReturn(null);

        // Esegui la servlet
        servlet.doGet(request, response);

        // Verifica che l'errore sia impostato correttamente
        verify(request).setAttribute("errorDeleteID", "Prodotto non trovato, id non presente nel database");

        // Verifica che il dispatcher della richiesta sia chiamato con la giusta pagina
        verify(dispatcher).forward(request, response);
    }
}
