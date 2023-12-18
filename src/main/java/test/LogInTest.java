package test;

import control.LogIn;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.CarrelloDAO;
import model.User;
import model.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class LogInTest {

    @InjectMocks
    private LogIn servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private UserDAO userDAO;

    @Mock
    private CarrelloDAO carrelloDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void testDoGetValidLogin() throws Exception {
        // Simula il comportamento del doRetrieveByUsername
        User mockUser = new User();
        mockUser.setUsername("mic");
        mockUser.setPassword("Password00@");
        mockUser.setAdmin(false);

        when(request.getParameter("logusername")).thenReturn("mic");
        when(request.getParameter("logpassword")).thenReturn("Password00@");

        // Esegui la servlet
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        new LogIn().doGet(request, response);

        verify(session, times(1)).setAttribute(eq("carrello"), any());
        // Verifica che la sessione sia impostata correttamente

        // Verifica che il dispatcher della richiesta sia chiamato con la giusta pagina
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoGetInvalidLogin() throws Exception {
        // Simula il comportamento del doRetrieveByUsername
        when(request.getParameter("logusername")).thenReturn("nonExistingUser");
        when(request.getParameter("logpassword")).thenReturn("wrongpassword");
        when(userDAO.doRetrieveByUsername("nonExistingUser")).thenReturn(null);

        // Esegui la servlet
        servlet.doGet(request, response);

        // Verifica che l'errore sia impostato correttamente
        verify(request).setAttribute("errorMessageLogin", "Credenziali errate o Account Inesistente. Riprova o registrati");

        // Verifica che il dispatcher della richiesta sia chiamato con la giusta pagina
        verify(dispatcher).forward(request, response);
    }
}
