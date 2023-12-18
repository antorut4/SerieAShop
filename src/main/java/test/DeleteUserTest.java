package test;

import control.LogIn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.UserDAO;
import control.DeleteUser;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteUserTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private UserDAO userDAO;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private DeleteUser deleteUser;

    @Test
    void testDoGet() throws ServletException, IOException, SQLException {
        // Arrange
        User user = new User();
        user.setUsername("ciao");
        when(request.getParameter("logusername")).thenReturn("ciao");
        when(request.getParameter("logpassword")).thenReturn("Password00@");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        new LogIn().doGet(request, response);


        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(requestDispatcher);

        // Act
        new DeleteUser().doGet(request, response);

        // Assert
        // Verifica che il metodo deleteUser del userDAO sia stato chiamato con l'username dell'utente
        Mockito.verify(userDAO).deleteUser(user.getUsername());

        // Verifica che la richiesta sia stata inoltrata correttamente
        Mockito.verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGetInvalid() throws ServletException, IOException, SQLException {
        // Arrange
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(null);  // Simula l'assenza di un utente in sessione

        // Act
        new DeleteUser().doGet(request, response);

        // Assert
        // Verifica che il metodo deleteUser del userDAO NON sia stato chiamato (nessun utente da eliminare)
        Mockito.verify(userDAO, Mockito.never()).deleteUser(Mockito.anyString());

        // Verifica che la richiesta sia stata inoltrata correttamente
        Mockito.verify(requestDispatcher).forward(request, response);
    }
}
