package test;

import com.google.gson.Gson;
import control.SearchServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SearchServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ProdottoDAO prodottoDAO;

    @InjectMocks
    private SearchServlet searchServlet;

    @Test
    void testDoGet() throws Exception {
        // Inizializza i mock
        MockitoAnnotations.initMocks(this);

        // Configura il comportamento del mock del request
        when(request.getParameter("query")).thenReturn("exampleQuery");

        // Crea una lista mock di Prodotto per simulare i risultati del database
        List<Prodotto> mockSearchResults = createMockSearchResults();
        // Configura il comportamento del mock del ProdottoDAO
        when(prodottoDAO.doRetriveAll()).thenReturn(mockSearchResults);

        // Configura il comportamento del mock del response.getWriter()
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Esegui il metodo doGet del servlet
        searchServlet.doGet(request, response);

        // Verifica che il content type sia stato impostato correttamente
        verify(response).setContentType("application/json");
        // Verifica che l'encoding sia stato impostato correttamente
        verify(response).setCharacterEncoding("UTF-8");

        // Esegui l'analisi della risposta JSON e verifica il suo contenuto
        String jsonResponse = stringWriter.toString();
    }

    private List<Prodotto> createMockSearchResults() {
        // Crea una lista di prodotti di esempio
        List<Prodotto> mockSearchResults = new ArrayList<>();
        mockSearchResults.add(new Prodotto(1, "Prodotto1", 10.0, "Descrizione1", 10, "Squadra1", "Categoria1"));
        mockSearchResults.add(new Prodotto(2, "Prodotto2", 20.0, "Descrizione2", 20, "Squadra2", "Categoria2"));
        // Aggiungi altri prodotti di esempio se necessario
        return mockSearchResults;
    }
}
