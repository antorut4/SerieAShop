package test;

import control.UpdateProdotto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateProdottoTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private ProdottoDAO prodottoDAO;

    @InjectMocks
    private UpdateProdotto updateProdotto;

    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        prodottoDAO = mock(ProdottoDAO.class);

        updateProdotto = new UpdateProdotto(); // Aggiungi questa riga

        // Configura il comportamento del mock per getRequestDispatcher
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        // Usa riflessione per impostare la variabile prodottoDAO nell'oggetto UpdateProdotto
        try {
            Field field = UpdateProdotto.class.getDeclaredField("prodottoDAO");
            field.setAccessible(true);
            field.set(updateProdotto, prodottoDAO);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace(); // Gestisci l'eccezione in base alle tue esigenze
        }
    }



    @Test
    void testDoGet_SuccessfulUpdate() throws ServletException, IOException {
        // Arrange
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("nome")).thenReturn("NuovoNome");
        when(request.getParameter("prezzo")).thenReturn("10");
        when(request.getParameter("descrizione")).thenReturn("NuovaDescrizione");
        when(request.getParameter("categoria")).thenReturn("NuovaCategoria");
        when(request.getParameter("quantita")).thenReturn("5");

        Prodotto updatedProdotto = new Prodotto();
        updatedProdotto.setId(1);
        updatedProdotto.setNome("NuovoNome");
        updatedProdotto.setPrezzo(10.0);
        updatedProdotto.setDescrizione("NuovaDescrizione");
        updatedProdotto.setCategoria("NuovaCategoria");
        updatedProdotto.setQuantita(5);

        when(prodottoDAO.doUpdateProdotto(any(Prodotto.class))).thenReturn(updatedProdotto);

        // Act
        updateProdotto.doGet(request, response);

        // Assert
        verify(request, times(1)).getParameter("id");
        verify(request, times(1)).getParameter("nome");
        verify(request, times(1)).getParameter("prezzo");
        verify(request, times(1)).getParameter("descrizione");
        verify(request, times(1)).getParameter("categoria");
        verify(request, times(1)).getParameter("quantita");
        verify(prodottoDAO, times(1)).doUpdateProdotto(any(Prodotto.class));

        // Verifica che la servlet reindirizzi alla pagina corretta in caso di successo
        verify(response, times(1)).sendRedirect(anyString());
    }

    @Test
    void testDoGet_FailedUpdate() throws ServletException, IOException {
        // Arrange
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("nome")).thenReturn("NuovoNome");
        when(request.getParameter("prezzo")).thenReturn("10");
        when(request.getParameter("descrizione")).thenReturn("NuovaDescrizione");
        when(request.getParameter("categoria")).thenReturn("NuovaCategoria");
        when(request.getParameter("quantita")).thenReturn("5");

        when(prodottoDAO.doUpdateProdotto(any(Prodotto.class))).thenReturn(null);

        // Act
        updateProdotto.doGet(request, response);

        // Assert
        verify(request, times(1)).getParameter("id");
        verify(request, times(1)).getParameter("nome");
        verify(request, times(1)).getParameter("prezzo");
        verify(request, times(1)).getParameter("descrizione");
        verify(request, times(1)).getParameter("categoria");
        verify(request, times(1)).getParameter("quantita");
        verify(prodottoDAO, times(1)).doUpdateProdotto(any(Prodotto.class));

        // Verifica che la servlet reindirizzi alla pagina di errore in caso di fallimento
        verify(response, times(1)).sendRedirect(anyString());
    }
}
