package test;

import control.UpdatePrezzo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import model.Prodotto;
import model.ProdottoDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class UpdatePrezzoTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private ProdottoDAO prodottoDAO;

    @Mock
    private ServletContext servletContext;

    @Mock
    private ServletConfig servletConfig;

    @InjectMocks
    private UpdatePrezzo updatePrezzo;

    @BeforeEach
    void setUp() throws ServletException {
        MockitoAnnotations.initMocks(this);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        updatePrezzo.init(servletConfig);
    }

    @Test
    void testDoGet() throws ServletException, IOException, SQLException {
        // Configura il comportamento del mock del request
        when(request.getParameter("idProdotto")).thenReturn("123");
        when(request.getParameter("prezzo")).thenReturn("45.99");

        // Crea un prodotto fittizio per simulare i risultati del database
        Prodotto mockProdotto = new Prodotto();
        when(prodottoDAO.doUpdatePrezzo(45.99, 123)).thenReturn(mockProdotto);

        // Configura il comportamento del mock del request.getRequestDispatcher()
        when(request.getRequestDispatcher("/WEB-INF/HomeAdmin.jsp")).thenReturn(requestDispatcher);

        // Esegui il metodo doGet della servlet
        updatePrezzo.doGet(request, response);

        // Verifica che il prodotto aggiornato sia impostato correttamente come attributo della richiesta
        verify(request).setAttribute("prodottoAggiornato", mockProdotto);

        // Verifica che il dispatcher sia stato chiamato con l'indirizzo corretto
        verify(requestDispatcher).forward(request, response);
    }
}
