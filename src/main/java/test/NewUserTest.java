package test;

import control.DeleteUser;
import control.NewUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewUserTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(request, response, session, requestDispatcher);
    }


    @Test
    void testDoGet() throws ServletException, IOException {
        // Arrange
        when(request.getParameter("nome")).thenReturn("John");
        when(request.getParameter("cognome")).thenReturn("Doe");
        when(request.getParameter("email")).thenReturn("gio@gmail.com");
        when(request.getParameter("password")).thenReturn("Password00@");
        when(request.getParameter("username")).thenReturn("ciaopashc");
        when(request.getParameter("telefono")).thenReturn("1234567890");
        when(request.getParameter("indirizzo")).thenReturn("via casa");


        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(requestDispatcher);

        // Act
        new NewUser().doGet(request, response);

        // Assert
        // You can add assertions based on the behavior you expect
        verify(session, Mockito.times(1)).setAttribute(Mockito.eq("carrello"), Mockito.any());
        verify(requestDispatcher).forward(request, response);
    }


    @Test
    void testDoGetInvalid() throws ServletException, IOException {
        // Arrange
        when(request.getParameter("nome")).thenReturn("John");
        when(request.getParameter("cognome")).thenReturn("Doe");
        when(request.getParameter("email")).thenReturn("giogmail.com");
        when(request.getParameter("password")).thenReturn("Password005");
        when(request.getParameter("username")).thenReturn("ciaopio");
        when(request.getParameter("telefono")).thenReturn("123456789");
        when(request.getParameter("indirizzo")).thenReturn("via casa");

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(requestDispatcher);

        // Act
        new NewUser().doGet(request, response);


        // Assert
        List<String> errori = (List<String>) request.getSession().getAttribute("errori");

        if(errori.isEmpty()){
            System.out.println("sono vuota");
        }

        assertEquals(1, errori.size(), "Dovrebbe esserci un errore");

        // Assicurati che gli errori attesi siano presenti nella lista
        assertTrue(errori.contains("Devi compilare correttamente il campo email"));
        assertTrue(errori.contains("Devi compilare correttamente il campo password"));
        assertTrue(errori.contains("Devi compilare correttamente il campo numero di telefono"));

        // Verifica che la richiesta sia stata inoltrata correttamente
        verify(requestDispatcher).forward(request, response);
    }


}
