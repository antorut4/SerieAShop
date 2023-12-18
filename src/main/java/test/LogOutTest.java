package test;

import control.LogOut;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LogOutTest {

    private LogOut servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        servlet = new LogOut();
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        // Esegui la servlet
        servlet.doGet(request, response);

        // Verifica che la sessione sia invalidata
        verify(session).invalidate();

        // Verifica che il dispatcher della richiesta sia chiamato con la giusta pagina
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        // Esegui la servlet
        servlet.doPost(request, response);

        // Verifica che la sessione sia invalidata
        verify(session).invalidate();

        // Verifica che il dispatcher della richiesta sia chiamato con la giusta pagina
        verify(dispatcher).forward(request, response);
    }
}
