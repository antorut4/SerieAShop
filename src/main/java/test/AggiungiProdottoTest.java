package test;

import control.AddProdotto;
import control.LogIn;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Prodotto;
import model.ProdottoDAO;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class AggiungiProdottoTest {
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
    private ProdottoDAO prodottoDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void doGetValidProdotto() throws ServletException, IOException {

        when(request.getParameter("nome")).thenReturn("Maglia casa");
        when(request.getParameter("descrizione")).thenReturn("maglia di casa");
        when(request.getParameter("categoria")).thenReturn("maglia");
        when(request.getParameter("idSquadra")).thenReturn("Atalanta");
        when(request.getParameter("prezzo")).thenReturn("20");

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(dispatcher);

        new AddProdotto().doGet(request , response);
        Mockito.verify(request).setAttribute(eq("prodotto"), any());
        Mockito.verify(dispatcher).forward(request , response);

    }

}
