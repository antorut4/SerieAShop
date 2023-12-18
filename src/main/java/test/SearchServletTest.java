package test;

import com.google.gson.Gson;
import control.SearchServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SearchServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ProdottoDAO prodottoDAO;

    @InjectMocks
    private SearchServlet searchServlet;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoGet() throws Exception {
        // Mock data
        List<Prodotto> allProducts = new ArrayList<>();
        Prodotto prodotto1 = new Prodotto();
        prodotto1.setNome("Prodotto1");
        prodotto1.setDescrizione("Descrizione1");
        prodotto1.setIdSquadra("Squadra1");
        allProducts.add(prodotto1);

        Prodotto prodotto2 = new Prodotto();
        prodotto2.setNome("Prodotto2");
        prodotto2.setDescrizione("Descrizione2");
        prodotto2.setIdSquadra("Squadra2");
        allProducts.add(prodotto2);

        when(prodottoDAO.doRetriveAll()).thenReturn(allProducts);

        when(request.getParameter("query")).thenReturn("Prodotto");

        // Capture the response output
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Perform the doGet
        searchServlet.doGet(request, response);

        // Verify interactions
        verify(response, times(1)).setContentType("application/json");
        verify(response, times(1)).setCharacterEncoding("UTF-8");

        // Verify that the results were written to the response
        writer.flush();
        String responseOutput = stringWriter.toString();
        List<Prodotto> searchResults = new Gson().fromJson(responseOutput, List.class);

        // Verify that the response contains the expected search results
        // You might need to adapt these assertions based on your actual implementation
        assert searchResults != null;
        assert searchResults.size() <=1;
        assert searchResults.get(0).getNome().equals("Prodotto1");
        assert searchResults.get(1).getNome().equals("Prodotto2");
    }
}

